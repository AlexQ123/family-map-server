package service;

import service.result.SinglePersonResult;

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
        return null;
    }

}
