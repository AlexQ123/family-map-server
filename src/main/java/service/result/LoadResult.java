package service.result;

/**
 * The result of loading data into the database.
 */
public class LoadResult {

    /**
     * Success or error message.
     */
    private String message;

    /**
     * Whether loading was successful.
     */
    private boolean success;

    /**
     * Create a response body.
     *
     * @param message response body message
     * @param success response body success indicator
     */
    public LoadResult(String message, boolean success) {
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
