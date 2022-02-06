package service;

import service.result.AllEventsResult;

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
        return null;
    }

}
