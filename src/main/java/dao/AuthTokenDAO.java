package dao;

import model.AuthToken;

import java.sql.SQLException;

/**
 * A DAO for the authtoken table.
 */
public class AuthTokenDAO {

    /**
     * Creates a new authorization token (authtoken row in database).
     *
     * @param authtoken the token being inserted
     * @throws SQLException errors in the database
     */
    public void insertAuthToken(AuthToken authtoken) throws SQLException {

    }

    /**
     * Deletes all data from the authtoken table.
     *
     * @throws SQLException errors in the database.
     */
    public void clearAuthToken() throws SQLException {

    }

    /**
     *
     *
     * @param toValidate
     * @return true if the provided authtoken is valid, false if not
     * @throws SQLException
     */
    public boolean validateAuthToken(AuthToken toValidate) throws SQLException {
        return false;
    }

}
