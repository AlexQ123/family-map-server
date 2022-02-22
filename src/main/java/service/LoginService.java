package service;

import service.request.LoginRequest;
import service.result.LoginResult;

import dao.*;
import model.*;

import java.sql.Connection;
import java.util.UUID;

/**
 * A service for logging a user in.
 */
public class LoginService {

    private UserDAO uDao;
    private AuthTokenDAO aDao;

    /**
     * Logs a user in.
     *
     * @param r the request body with login information
     * @return the response body indicating success or error
     */
    public LoginResult login(LoginRequest r) {
        if (!isValidRequest(r)) {
            return new LoginResult("Error: Request property missing or has invalid value", false);
        }

        Database db = new Database();
        try {
            db.openConnection();
            Connection conn = db.getConnection();

            uDao = new UserDAO(conn);
            User toFind = uDao.findUser(r.getUsername());
            if (toFind == null) {
                db.closeConnection(false);
                return new LoginResult("Error: Username not found", false);
            }
            else if (!toFind.getPassword().equals(r.getPassword())) {
                db.closeConnection(false);
                return new LoginResult("Error: Incorrect password", false);
            }
            else {
                AuthToken authtoken = new AuthToken(UUID.randomUUID().toString(), r.getUsername());
                aDao = new AuthTokenDAO(conn);
                aDao.insertAuthToken(authtoken);

                db.closeConnection(true);
                LoginResult result = new LoginResult(authtoken.getAuthtoken(), toFind.getUsername(), toFind.getPersonID(),
                        true);
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
            return new LoginResult("Error: Internal server error", false);
        }
    }

    private boolean isValidRequest(LoginRequest r) {
        return (r.getUsername() != null && r.getPassword() != null);
    }

}
