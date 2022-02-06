package service;

import service.result.SingleEventResult;

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
        return null;
    }

}
