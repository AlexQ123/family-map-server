package service;

import service.result.AllEventsResult;

import dao.*;
import model.*;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * A service for returning all events of all family members of a user
 */
public class AllEventsService {

    /**
     * Gets all events of all family members of a user (the current user is determined by the provided authtoken).
     *
     * @param authtoken the authorization token
     * @return the response body indicating success or error
     */
    public AllEventsResult allEvents(String authtoken) {
        Database db = new Database();
        try {
            db.openConnection();
            Connection conn = db.getConnection();

            AuthTokenDAO aDao = new AuthTokenDAO(conn);
            AuthToken authtokenToFind = new AuthToken(authtoken, "test");
            if (!aDao.validateAuthToken(authtokenToFind)) {
                db.closeConnection(false);
                return new AllEventsResult("Error: Invalid authtoken", false);
            }
            AuthToken foundAuthtoken = aDao.getAuthToken(authtoken);
            String username = foundAuthtoken.getUsername();

            EventDAO eDao = new EventDAO(conn);
            ArrayList<Event> events = eDao.findAllEvents(username);

            db.closeConnection(true);
            return new AllEventsResult(events, true);
        }
        catch (DataAccessException e) {
            e.printStackTrace();
            try {
                db.closeConnection(false);
            }
            catch (DataAccessException ex) {
                // at this point, the exception that closeConnection might throw has already caused the fatal error
            }
            return new AllEventsResult("Error: Internal server error", false);
        }
    }

}
