package service.result;

/**
 * The result of filling a user's data.
 */
public class FillResult {

    /**
     * Success or error message.
     */
    private String message;

    /**
     * Whether filling was successful.
     */
    private boolean success;

    /**
     * Default constructor
     */
    public FillResult() {

    }

    /**
     * Create a response body.
     *
     * @param message response body message
     * @param success response body success indicator
     */
    public FillResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    // Auto-generated getters and setters, no javadoc needed
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
