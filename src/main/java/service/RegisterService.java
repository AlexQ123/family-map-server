package service;

import service.request.RegisterRequest;
import service.result.RegisterResult;

/**
 * A service for registering a user.
 */
public class RegisterService {

    /**
     * Registers a user into the database, creates 4 generations of ancestor data, and logs them in.
     *
     * @param r the request body with registering information
     * @return the response body indicating success or error
     */
    public RegisterResult register(RegisterRequest r) {
        return null;
    }

}
