package service;

import service.result.ClearResult;

import dao.*;

/**
 * A service for clearing the database.
 */
public class ClearService {

    /**
     * Clears the database.
     *
     * @return the response body indicating success or error
     */
    public ClearResult clear() {
        Database db = new Database();
        try {
            db.openConnection();

            db.clearTables();

            db.closeConnection(true);

            ClearResult result = new ClearResult("Clear succeeded.", true);
            return result;
        }
        catch (DataAccessException e) {
            e.printStackTrace();
            try {
                db.closeConnection(false);
            }
            catch (DataAccessException ex) {
                // at this point, the exception that closeConnection might throw has already caused the fatal error
            }
            return new ClearResult("Error: Internal server error", false);
        }
    }

}
