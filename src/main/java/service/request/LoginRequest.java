package service.request;

/**
 * A request sent from the client when a user attempts to log in.
 */
public class LoginRequest {

    /**
     * The user's username in the request body.
     */
    private String username;

    /**
     * The user's password in the request body.
     */
    private String password;

    /**
     * Creates a "default" login request with no other information.
     */
    public LoginRequest() {
        this.username = null;
        this.password = null;
    }

    /**
     * Creates a login request with all information.
     *
     * @param username request body username
     * @param password request body password
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Auto-generated getters and setters, no javadoc needed
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
