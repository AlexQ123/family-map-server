package model;

/**
 * A person.
 */
public class Person {

    /**
     * The unique identifier for the person.
     */
    private String personID;

    /**
     * The username of the user to which this person belongs.
     */
    private String associatedUsername;

    /**
     * The person's first name.
     */
    private String firstName;

    /**
     * The person's last name.
     */
    private String lastName;

    /**
     * The person's gender, can be "f" or "m"
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
     * Creates a "default" person with no other information.
     */
    public Person() {
        this.personID = null;
        this.associatedUsername = null;
        this.firstName = null;
        this.lastName = null;
        this.gender = null;
        this.fatherID = null;
        this.motherID = null;
        this.spouseID = null;
    }

    /**
     * Creates a person with all information.
     *
     * @param personID the person's unique identifier
     * @param associatedUsername the username associated with this person
     * @param firstName the person's first name
     * @param lastName the person's last name
     * @param gender the person's gender
     * @param fatherID the identifier of the person's father
     * @param motherID the identifier of the person's mother
     * @param spouseID the identifier of the person's spouse
     */
    public Person(String personID, String associatedUsername, String firstName, String lastName, String gender,
                  String fatherID, String motherID, String spouseID) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }

    // Auto-generated getters and setters, no javadoc needed
    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
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

    /**
     * Overriding the equals method by comparing data members.
     *
     * @param o the object to be compared to
     * @return whether this person is equal to object o
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o instanceof Person) {
            Person oPerson = (Person) o;
            return oPerson.getPersonID().equals(getPersonID()) &&
                    oPerson.getAssociatedUsername().equals(getAssociatedUsername()) &&
                    oPerson.getFirstName().equals(getFirstName()) &&
                    oPerson.getLastName().equals(getLastName()) &&
                    oPerson.getGender().equals(getGender()) &&
                    oPerson.getFatherID().equals(getFatherID()) &&
                    oPerson.getMotherID().equals(getMotherID()) &&
                    oPerson.getSpouseID().equals(getSpouseID());
        }
        else {
            return false;
        }
    }

}
