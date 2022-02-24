package model;

/**
 * An event.
 */
public class Event {

    /**
     * The username of the user to which this event belongs.
     */
    private String associatedUsername;

    /**
     * The unique identifier for the event.
     */
    private String eventID;


    /**
     * The ID of the person to which this event belongs.
     */
    private String personID;

    /**
     * The latitude of the event's location.
     */
    private float latitude;

    /**
     * The longitude of the event's location.
     */
    private float longitude;

    /**
     * The country in which the event occurred.
     */
    private String country;

    /**
     * The city in which the event occurred.
     */
    private String city;

    /**
     * The type of event.
     */
    private String eventType;

    /**
     * The year in which the event occurred.
     */
    private int year;

    /**
     * Creates a "default" event with no other information.
     */
    public Event() {
        this.eventID = null;
        this.associatedUsername = null;
        this.personID = null;
        this.latitude = 0.0f;
        this.longitude = 0.0f;
        this.country = null;
        this.city = null;
        this.eventType = null;
        this.year = 0;
    }

    /**
     * Creates an event with all information.
     *
     * @param eventID the event's unique identifier
     * @param associatedUsername the username associated with the event
     * @param personID the identifier of the person to which this event belongs
     * @param latitude the latitude of the event's location
     * @param longitude the longitude of the event's location
     * @param country the country in which the event occurred
     * @param city the city in which the event occurred
     * @param eventType the event type
     * @param year the year in which the event occurred
     */
    public Event(String eventID, String associatedUsername, String personID, float latitude, float longitude,
                 String country, String city, String eventType, int year) {
        this.associatedUsername = associatedUsername;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    // Auto-generated getters and setters, no javadoc needed
    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

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

    /**
     * Overriding the equals method by comparing data members.
     *
     * @param o the object to be compared to
     * @return whether this event is equal to object o
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o instanceof Event) {
            Event oEvent = (Event) o;
            return oEvent.getEventID().equals(getEventID()) &&
                    oEvent.getAssociatedUsername().equals(getAssociatedUsername()) &&
                    oEvent.getPersonID().equals(getPersonID()) &&
                    oEvent.getLatitude() == (getLatitude()) &&
                    oEvent.getLongitude() == (getLongitude()) &&
                    oEvent.getCountry().equals(getCountry()) &&
                    oEvent.getCity().equals(getCity()) &&
                    oEvent.getEventType().equals(getEventType()) &&
                    oEvent.getYear() == (getYear());
        }
        else {
            return false;
        }
    }

}
