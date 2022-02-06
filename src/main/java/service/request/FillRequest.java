package service.request;

/**
 * A request sent from the client to generate data for the specified user.
 */
public class FillRequest {

    /**
     * The user's username found in the url.
     */
    private String username;

    /**
     * The number of generations to fill data for, found in the url.
     */
    private int generations;

    /**
     * Creates a "default" fill request with no other information.
     */
    public FillRequest() {
        this.username = null;
        this.generations = 4;
    }

    /**
     * Creates a fill request with all information.
     *
     * @param username the username in the url
     * @param generations the generations in the url
     */
    public FillRequest(String username, int generations) {
        this.username = username;
        this.generations = generations;
    }

    // Auto-generated getters and setters, no javadoc needed
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGenerations() {
        return generations;
    }

    public void setGenerations(int generations) {
        this.generations = generations;
    }

}
