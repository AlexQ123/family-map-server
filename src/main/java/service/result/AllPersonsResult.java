package service.result;

import model.Person;

public class AllPersonsResult {

    /**
     * The array of persons in the response body.
     */
    private Person[] persons;

    /**
     * Whether the all persons service was successful.
     */
    private boolean success;

    /**
     * The error message if all persons was not successful.
     */
    private String message;

    /**
     * Create a successful response body.
     *
     * @param persons response body data
     * @param success response body success
     */
    public AllPersonsResult(Person[] persons, boolean success) {
        this.persons = persons;
        this.success = success;
        this.message = null;
    }

    /**
     * Create an unsuccessful response body.
     *
     * @param success response body failure
     * @param message response body error message
     */
    public AllPersonsResult(boolean success, String message) {
        this.persons = null;
        this.success = success;
        this.message = message;
    }

    // Auto-generated getters and setters, no javadoc needed
    public Person[] getPersons() {
        return persons;
    }

    public void setPersons(Person[] persons) {
        this.persons = persons;
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
