package service.result;

import model.Event;
import java.util.ArrayList;

/**
 * The result of returning all events of all family members of a user.
 */
public class AllEventsResult {

    /**
     * The array of events in the response body.
     */
    private ArrayList<Event> data;

    /**
     * The error message if all events was not successful.
     */
    private String message;

    /**
     * Whether the all events service was successful.
     */
    private boolean success;

    /**
     * Default constructor
     */
    public AllEventsResult() {

    }

    /**
     * Create a successful response body.
     *
     * @param events response body data
     * @param success response body success
     */
    public AllEventsResult(ArrayList<Event> events, boolean success) {
        this.data = events;
        this.message = null;
        this.success = success;
    }

    /**
     * Create an unsuccessful response body.
     *
     * @param message response body error message
     * @param success response body failure
     */
    public AllEventsResult(String message, boolean success) {
        this.data = null;
        this.message = message;
        this.success = success;
    }

    // Auto-generated getters and setters, no javadoc needed
    public ArrayList<Event> getData() {
        return data;
    }

    public void setData(ArrayList<Event> events) {
        this.data = events;
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
