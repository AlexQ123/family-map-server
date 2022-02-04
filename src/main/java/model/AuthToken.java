package model;

/**
 * An authorization token.
 */
public class AuthToken {

    /**
     * The unique authorization token.
     */
    private String authtoken = "";

    /**
     * The username that is associated with the authorization token.
     */
    private String username = "";

    /**
     * Creates a "default" authorization token with no other information.
     */
    public AuthToken() {
        this.authtoken = null;
        this.username = null;
    }

    /**
     * Creates an authorization token with all information
     *
     * @param authtoken the unique authorization token
     * @param username the username associated with the authorization token
     */
    public AuthToken(String authtoken, String username) {
        this.authtoken = authtoken;
        this.username = username;
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

    /**
     * Overriding the equals method by comparing data members.
     *
     * @param o the object to be compared to
     * @return whether this authorization token is equal to object o
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o instanceof AuthToken) {
            AuthToken oAuthToken = (AuthToken) o;
            return oAuthToken.getUsername().equals(getUsername()) &&
                    oAuthToken.getAuthtoken().equals(getAuthtoken());
        }
        else {
            return false;
        }
    }

}
