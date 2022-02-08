package dao;

import model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

/**
 * A DAO for the user table.
 */
public class UserDAO {

    private final Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Creates a new user account (user row in the database).
     *
     * @param user the user being inserted
     * @throws DataAccessException errors in the database
     */
    public void insertUser(User user) throws DataAccessException  {
        String sql = "INSERT INTO User (username, password, email, firstName, lastName, gender, personID) " +
                "VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getGender());
            stmt.setString(7, user.getPersonID());

            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the database");
        }
    }

    /**
     * Deletes all data from the user table.
     *
     * @throws SQLException errors in the database
     */
    public void clearUser() throws SQLException {
        try (Statement stmt = conn.createStatement()){
            String sql = "DELETE FROM User";
            stmt.executeUpdate(sql);
        }
        catch (SQLException e) {
            throw new SQLException();
        }
    }

    /**
     * Gets the user with the specified username.
     *
     * @param username the username of the user to find
     * @return the user object
     * @throws DataAccessException errors in the database
     */
    public User findUser(String username) throws DataAccessException {
        return null;
    }

}
