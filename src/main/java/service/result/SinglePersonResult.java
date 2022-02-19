package service.result;

/**
 * The result of returning a single person object.
 */
public class SinglePersonResult {

    /**
     * The person's associated username in the response body.
     */
    private String associatedUsername;

    /**
     * The person ID in the response body.
     */
    private String personID;

    /**
     * The person's first name in the response body.
     */
    private String firstName;

    /**
     * The person's last name in the response body.
     */
    private String lastName;

    /**
     * The person's gender in the response body.
     */
    private String gender;

    /**
     * The personID of the person's father, may be null.
     */
    private String fatherID;

    /**
     * The personID of the person's mother, may be null.
     */
    private String motherID;

    /**
     * The personID of the person's spouse, may be null.
     */
    private String spouseID;

    /**
     * The error message if single person was not successful.
     */
    private String message;

    /**
     * Whether the single person service was successful.
     */
    private boolean success;

    /**
     * Default constructor
     */
    public SinglePersonResult() {

    }

    /**
     * Create a successful response body.
     *
     * @param associatedUsername response body username
     * @param personID response body person ID
     * @param firstName response body first name
     * @param lastName response body last name
     * @param gender response body gender
     * @param fatherID response body father ID
     * @param motherID response body mother ID
     * @param spouseID response body spouse ID
     * @param success response body success
     */
    public SinglePersonResult(String associatedUsername, String personID, String firstName, String lastName,
                              String gender, String fatherID, String motherID, String spouseID, boolean success) {
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
        this.message = null;
        this.success = success;
    }

    /**
     * Create an unsuccessful response body.
     *
     * @param message response body error message
     * @param success response body failure
     */
    public SinglePersonResult(String message, boolean success) {
        this.associatedUsername = null;
        this.personID = null;
        this.firstName = null;
        this.lastName = null;
        this.gender = null;
        this.fatherID = null;
        this.motherID = null;
        this.spouseID = null;
        this.message = message;
        this.success = success;
    }

    // Auto-generated getters and setters, no javadoc needed
    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
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

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
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
