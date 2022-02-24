package service;

import service.result.AllPersonsResult;

import dao.*;
import model.*;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * A service for returning all family members of a user.
 */
public class AllPersonsService {

    /**
     * Gets all family members of a user (the current user is determined by the provided authtoken).
     *
     * @param authtoken the authorization token
     * @return the response body indicating success or error
     */
    public AllPersonsResult allPersons(String authtoken) {
        Database db = new Database();
        try {
            db.openConnection();
            Connection conn = db.getConnection();

            AuthTokenDAO aDao = new AuthTokenDAO(conn);
            AuthToken authtokenToFind = new AuthToken(authtoken, "test");
            if (!aDao.validateAuthToken(authtokenToFind)) {
                db.closeConnection(false);
                return new AllPersonsResult("Error: Invalid authtoken", false);
            }
            AuthToken foundAuthtoken = aDao.getAuthToken(authtoken);
            String username = foundAuthtoken.getUsername();

            PersonDAO pDao = new PersonDAO(conn);
            ArrayList<Person> persons = pDao.findFamily(username);

            db.closeConnection(true);
            return new AllPersonsResult(persons, true);
        }
        catch (DataAccessException e) {
            e.printStackTrace();
            try {
                db.closeConnection(false);
            }
            catch (DataAccessException ex) {
                // at this point, the exception that closeConnection might throw has already caused the fatal error
            }
            return new AllPersonsResult("Error: Internal server error", false);
        }
    }

}
