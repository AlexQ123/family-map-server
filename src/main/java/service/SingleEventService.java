package service;

import service.result.SingleEventResult;

import dao.*;
import model.*;
import service.result.SinglePersonResult;

import java.sql.Connection;

/**
 * A service for returning a single event.
 */
public class SingleEventService {

    /**
     * Gets the single event with the specified ID if the person is associated with the current user
     * (the current user is determined by the provided authtoken).
     *
     * @param eventID the specified event ID
     * @param authtoken the authorization token
     * @return the response body indicating success or error
     */
    public SingleEventResult singleEvent(String eventID, String authtoken) {
        Database db = new Database();
        try {
            db.openConnection();
            Connection conn = db.getConnection();

            AuthTokenDAO aDao = new AuthTokenDAO(conn);
            AuthToken authtokenToFind = new AuthToken(authtoken, "test");
            if (!aDao.validateAuthToken(authtokenToFind)) {
                db.closeConnection(false);
                return new SingleEventResult("Error: Invalid authtoken", false);
            }
            AuthToken foundAuthtoken = aDao.getAuthToken(authtoken);

            EventDAO eDao = new EventDAO(conn);
            Event eventToFind = eDao.findEvent(eventID);
            if (eventToFind == null) {
                db.closeConnection(false);
                return new SingleEventResult("Error: Invalid eventID parameter", false);
            }

            if (!eventToFind.getAssociatedUsername().equals(foundAuthtoken.getUsername())) {
                db.closeConnection(false);
                return new SingleEventResult("Error: Requested event does not belong to this user", false);
            }

            db.closeConnection(true);
            return new SingleEventResult(eventToFind.getAssociatedUsername(), eventToFind.getEventID(), eventToFind.getPersonID(),
                    eventToFind.getLatitude(), eventToFind.getLongitude(), eventToFind.getCountry(), eventToFind.getCity(),
                    eventToFind.getEventType(), eventToFind.getYear(), true);
        }
        catch (DataAccessException e) {
            e.printStackTrace();
            try {
                db.closeConnection(false);
            }
            catch (DataAccessException ex) {
                // at this point, the exception that closeConnection might throw has already caused the fatal error
            }
            return new SingleEventResult("Error: Internal server error", false);
        }
    }

}
