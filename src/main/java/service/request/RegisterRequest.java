package service.request;

/**
 * A request sent from the client when registering a user.
 */
public class RegisterRequest {

    /**
     * The user's username in the request body.
     */
    private String username;

    /**
     * The user's password in the request body.
     */
    private String password;

    /**
     * The user's email in the request body.
     */
    private String email;

    /**
     * The user's first name in the request body.
     */
    private String firstName;

    /**
     * The user's last name in the request body.
     */
    private String lastName;

    /**
     * The user's gender in the request body.
     */
    private String gender;

    /**
     * Creates a "default" register request with no other information.
     */
    public RegisterRequest() {
        this.username = null;
        this.password = null;
        this.email = null;
        this.firstName = null;
        this.lastName = null;
        this.gender = null;
    }

    /**
     * Creates a register request with all information.
     *
     * @param username request body username
     * @param password request body password
     * @param email request body email
     * @param firstName request body first name
     * @param lastName request body last name
     * @param gender request body gender
     */
    public RegisterRequest(String username, String password, String email, String firstName, String lastName,
                           String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
