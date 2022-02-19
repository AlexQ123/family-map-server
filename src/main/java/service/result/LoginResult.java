package service.result;

/**
 * The result of a user login.
 */
public class LoginResult {

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
     * The error message if logging in was not successful.
     */
    private String message;

    /**
     * Whether the login service was successful.
     */
    private boolean success;

    /**
     * Default constructor
     */
    public LoginResult() {

    }

    /**
     * Create a successful response body.
     *
     * @param authtoken response body authorization token
     * @param username response body username
     * @param personID response body person ID
     * @param success repsonse body success
     */
    public LoginResult(String authtoken, String username, String personID, boolean success) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        this.message = null;
        this.success = success;
    }

    /**
     * Create an unsuccessful response body.
     *
     * @param message response body error message
     * @param success response body failure
     */
    public LoginResult(String message, boolean success) {
        this.authtoken = null;
        this.username = null;
        this.personID = null;
        this.message = message;
        this.success = success;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
