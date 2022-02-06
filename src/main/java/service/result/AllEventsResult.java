package service.result;

import model.Event;

/**
 * The result of returning all events of all family members of a user.
 */
public class AllEventsResult {

    /**
     * The array of events in the response body.
     */
    private Event[] events;

    /**
     * Whether the all events service was successful.
     */
    private boolean success;

    /**
     * The error message if all events was not successful.
     */
    private String message;

    /**
     * Create a successful response body.
     *
     * @param events response body data
     * @param success response body success
     */
    public AllEventsResult(Event[] events, boolean success) {
        this.events = events;
        this.success = success;
        this.message = null;
    }

    /**
     * Create an unsuccessful response body.
     *
     * @param success response body failure
     * @param message response body error message
     */
    public AllEventsResult(boolean success, String message) {
        this.events = null;
        this.success = success;
        this.message = message;
    }

    // Auto-generated getters and setters, no javadoc needed
    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
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
