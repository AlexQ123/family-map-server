package service.result;

/**
 * The result of returning a single event object.
 */
public class SingleEventResult {

    /**
     * The event's associated username in the response body.
     */
    private String associatedUsername;

    /**
     * The event ID in the response body.
     */
    private String eventID;

    /**
     * The event's associated person ID in the response body.
     */
    private String personID;

    /**
     * The event's latitude in the response body.
     */
    private Float latitude;

    /**
     * The event's longitude in the response body.
     */
    private Float longitude;

    /**
     * The country in which the event took place in the response body.
     */
    private String country;

    /**
     * The city in which the event took place in the response body.
     */
    private String city;

    /**
     * The event type in the response body.
     */
    private String eventType;

    /**
     * The year the event took place in the response body.
     */
    private Integer year;

    /**
     * The error message if single event was not successful.
     */
    private String message;

    /**
     * Whether the single event service was successful.
     */
    private boolean success;

    /**
     * Default constructor
     */
    public SingleEventResult() {

    }

    /**
     * Create a successful response body.
     *
     * @param associatedUsername response body username
     * @param eventID response body event ID
     * @param personID response body person ID
     * @param latitude response body latitude
     * @param longitude response body longitude
     * @param country response body country
     * @param city response body city
     * @param eventType response body event type
     * @param year response body year
     * @param success response body success
     */
    public SingleEventResult(String associatedUsername, String eventID, String personID, float latitude,
                             float longitude, String country, String city, String eventType, int year,
                             boolean success) {
        this.associatedUsername = associatedUsername;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
        this.message = null;
        this.success = success;
    }

    /**
     * Create an unsuccessful response body.
     *
     * @param message response body error message
     * @param success response body failure
     */
    public SingleEventResult(String message, boolean success) {
        this.associatedUsername = null;
        this.eventID = null;
        this.personID = null;
        this.latitude = null;
        this.longitude = null;
        this.country = null;
        this.city = null;
        this.eventType = null;
        this.year = null;
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

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
