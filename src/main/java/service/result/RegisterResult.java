package service.result;

/**
 * The result of registering a user.
 */
public class RegisterResult {

    /**
     * The authorization token in the response body.
     */
    private String authtoken;

    /**
     * The username in the response body.
     */
    private String username;

    /**
     * The person ID in the response body.
     */
    private String personID;

    /**
     * Whether the register service was successful.
     */
    private boolean success;

    /**
     * The error message if registering was not successful.
     */
    private String message;

    /**
     * Default constructor
     */
    public RegisterResult() {

    }

    /**
     * Create a successful response body.
     *
     * @param authtoken response body authorization token
     * @param username response body username
     * @param personID response body person ID
     * @param success response body success
     */
    public RegisterResult(String authtoken, String username, String personID, boolean success) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        this.success = success;
        this.message = null;
    }

    /**
     * Create an unsuccessful response body.
     *
     * @param success response body failure
     * @param message response body error message
     */
    public RegisterResult(boolean success, String message) {
        this.authtoken = null;
        this.username = null;
        this.personID = null;
        this.success = success;
        this.message = message;
    }

    // Auto-generated getters and setters, no javadoc needed
    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}