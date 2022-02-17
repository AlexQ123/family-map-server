package service;

import service.request.RegisterRequest;
import service.result.RegisterResult;

import dao.*;
import model.*;
import generated.*;

import java.sql.Connection;
import java.util.UUID;

/**
 * A service for registering a user.
 */
public class RegisterService {

    UserDAO uDao;
    AuthTokenDAO aDao;

    /**
     * Registers a user into the database, creates 4 generations of ancestor data, and logs them in.
     *
     * @param r the request body with registering information
     * @return the response body indicating success or error
     */
    public RegisterResult register(RegisterRequest r) {
        // make sure the request is valid
        if (!isValidRequest(r)) {
            return new RegisterResult(false, "Request property missing or has invalid value");
        }

        Database db = new Database();
        try {
            // open the connection
            db.openConnection();
            Connection conn = db.getConnection();

            // make sure the username isn't already taken
            uDao = new UserDAO(conn);
            User test = uDao.findUser(r.getUsername());
            if (test != null) {
                return new RegisterResult(false, "Username already taken");
            }

            // after error checking, perform the register service
            else {
                AncestorGenerator generator = new AncestorGenerator();

                // generate 4 generations of ancestor data, this returns the person associated with the user
                Person person = generator.generatePerson(r.getGender(), 4);

                // create the user account (user row in database)
                uDao.insertUser(new User(r.getUsername(), r.getPassword(), r.getEmail(), r.getFirstName(), r.getLastName(),
                        r.getGender(), person.getPersonID()));

                // logs user in
                AuthToken authtoken = new AuthToken(UUID.randomUUID().toString(), r.getUsername());
                aDao = new AuthTokenDAO(conn);
                aDao.insertAuthToken(authtoken);

                // close the connection and return result object
                db.closeConnection(true);
                RegisterResult result = new RegisterResult(authtoken.getAuthtoken(), r.getUsername(), person.getPersonID(), true);
                return result;
            }
        }
        catch (DataAccessException e) {
            e.printStackTrace();
            try {
                db.closeConnection(false);
            }
            catch (DataAccessException ex) {
                // at this point, the exception that closeConnection might throw has already caused the fatal error
            }
            return new RegisterResult(false, "Internal server error");
        }

    }

    private boolean isValidRequest(RegisterRequest r) {
        return (r.getUsername() != null && r.getPassword() != null && r.getEmail() != null && r.getFirstName() != null
        && r.getLastName() != null && (r.getGender().equals("f") || r.getGender().equals("m")));
    }

}