package service;

import service.result.SinglePersonResult;

import dao.*;
import model.*;

import java.sql.Connection;

/**
 * A service for returning a single person.
 */
public class SinglePersonService {

    /**
     * Gets the single person with the specified ID if the person is associated with the current user
     * (the current user is determined by the provided authtoken).
     *
     * @param personID the specified person ID
     * @param authtoken the authorization token
     * @return the response body indicating success or error
     */
    public SinglePersonResult singlePerson(String personID, String authtoken) {
        Database db = new Database();
        try {
            db.openConnection();
            Connection conn = db.getConnection();

            AuthTokenDAO aDao = new AuthTokenDAO(conn);
            AuthToken authtokenToFind = new AuthToken(authtoken, "test");
            if (!aDao.validateAuthToken(authtokenToFind)) {
                db.closeConnection(false);
                return new SinglePersonResult("Error: Invalid authtoken", false);
            }
            AuthToken foundAuthtoken = aDao.getAuthToken(authtoken);

            PersonDAO pDao = new PersonDAO(conn);
            Person personToFind = pDao.findPerson(personID);
            if (personToFind == null) {
                db.closeConnection(false);
                return new SinglePersonResult("Error: Invalid personID parameter", false);
            }

            if (!personToFind.getAssociatedUsername().equals(foundAuthtoken.getUsername())) {
                db.closeConnection(false);
                return new SinglePersonResult("Error: Requested person does not belong to this user", false);
            }

            db.closeConnection(true);
            return new SinglePersonResult(personToFind.getAssociatedUsername(), personToFind.getPersonID(), personToFind.getFirstName(),
                    personToFind.getLastName(), personToFind.getGender(), personToFind.getFatherID(), personToFind.getMotherID(),
                    personToFind.getSpouseID(), true);

        }
        catch (DataAccessException e) {
            e.printStackTrace();
            try {
                db.closeConnection(false);
            }
            catch (DataAccessException ex) {
                // at this point, the exception that closeConnection might throw has already caused the fatal error
            }
            return new SinglePersonResult("Error: Internal server error", false);
        }
    }

}
