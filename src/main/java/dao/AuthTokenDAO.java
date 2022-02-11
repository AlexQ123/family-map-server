package dao;

import model.AuthToken;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * A DAO for the authtoken table.
 */
public class AuthTokenDAO {

    private final Connection conn;

    public AuthTokenDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Creates a new authorization token (authtoken row in database).
     *
     * @param authtoken the token being inserted
     * @throws DataAccessException errors in the database
     */
    public void insertAuthToken(AuthToken authtoken) throws DataAccessException {
        String sql = "INSERT INTO Authtoken (authtoken, username) VALUES(?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authtoken.getAuthtoken());
            stmt.setString(2, authtoken.getUsername());

            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the database");
        }
    }

    /**
     * Deletes all data from the authtoken table.
     *
     * @throws SQLException errors in the database
     */
    public void clearAuthToken() throws SQLException {
        try (Statement stmt = conn.createStatement()){
            String sql = "DELETE FROM Authtoken";
            stmt.executeUpdate(sql);
        }
        catch (SQLException e) {
            throw new SQLException();
        }
    }

    /**
     * Checks if the provided authtoken is valid.
     *
     * @param toValidate the authtoken being checked
     * @return true if the provided authtoken is valid, false if not
     * @throws DataAccessException errors in the database
     */
    public boolean validateAuthToken(AuthToken toValidate) throws DataAccessException {
        boolean success = false;
        ResultSet rs = null;
        String sql = "SELECT * FROM Authtoken WHERE authtoken = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, toValidate.getAuthtoken());
            rs = stmt.executeQuery();
            if (rs.next()) {
                success = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while validating authorization token");
        }
        finally {
            if(rs != null) {
                try {
                    rs.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return success;
    }

}
