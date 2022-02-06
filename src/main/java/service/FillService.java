package service;

import service.request.FillRequest;
import service.result.FillResult;

/**
 * A service for filling a user's data for a certain number of generations.
 */
public class FillService {

    /**
     * Fills the server's database with generated data for the specified username after deleting any existing data.
     *
     * @param r the request body with the information for filling
     * @return the response body indicating success or error
     */
    public FillResult fill(FillRequest r) {
        return null;
    }

}
