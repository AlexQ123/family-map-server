package service;

import service.request.LoadRequest;
import service.result.LoadResult;

/**
 * A service for loading user, person, and event data into the database.
 */
public class LoadService {

    /**
     * Clears the database, and then loads new user, person, and event data into it.
     *
     * @param r the request body with the loading information
     * @return the response body indicating success or error
     */
    public LoadResult load(LoadRequest r) {
        return null;
    }

}
