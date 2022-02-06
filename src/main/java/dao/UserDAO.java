package dao;

import model.User;

import java.sql.SQLException;

/**
 * A DAO for the user table.
 */
public class UserDAO {

    /**
     * Creates a new user account (user row in the database).
     *
     * @param user the user being inserted
     * @throws SQLException errors in the database
     */
    public void insertUser(User user) throws SQLException  {

    }

    /**
     * Deletes all data from the user table.
     *
     * @throws SQLException errors in the database
     */
    public void clearUser() throws SQLException {

    }

    /**
     * Gets the user with the specified username.
     *
     * @param username the username of the user to find
     * @return the user object
     * @throws SQLException errors in the database
     */
    public User findUser(String username) throws SQLException {
        return null;
    }

}
