package service.result;

import model.Person;
import java.util.ArrayList;

/**
 * The result of returning all family members of a user.
 */
public class AllPersonsResult {

    /**
     * The array of persons in the response body.
     */
    private ArrayList<Person> persons;

    /**
     * The error message if all persons was not successful.
     */
    private String message;

    /**
     * Whether the all persons service was successful.
     */
    private boolean success;

    /**
     * Default constructor
     */
    public AllPersonsResult() {

    }

    /**
     * Create a successful response body.
     *
     * @param persons response body data
     * @param success response body success
     */
    public AllPersonsResult(ArrayList<Person> persons, boolean success) {
        this.persons = persons;
        this.message = null;
        this.success = success;
    }

    /**
     * Create an unsuccessful response body.
     *
     * @param success response body failure
     * @param message response body error message
     */
    public AllPersonsResult(boolean success, String message) {
        this.persons = null;
        this.message = message;
        this.success = success;
    }

    // Auto-generated getters and setters, no javadoc needed
    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
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
