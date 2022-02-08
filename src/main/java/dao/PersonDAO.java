package dao;

import model.Person;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * A DAO for the person table.
 */
public class PersonDAO {

    private final Connection conn;

    public PersonDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Creates a new person (person row in the database).
     *
     * @param person the person being inserted
     * @throws DataAccessException errors in the database
     */
    public void insertPerson(Person person) throws DataAccessException {
        String sql = "INSERT INTO Person (personID, associatedUsername, firstName, lastName, gender, fatherID, " +
                "motherID, spouseID) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getAssociatedUsername());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFatherID());
            stmt.setString(7, person.getMotherID());
            stmt.setString(8, person.getSpouseID());

            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the database");
        }
    }

    /**
     * Deletes all data from the person table.
     *
     * @throws SQLException errors in the database
     */
    public void clearPerson() throws SQLException {
        try (Statement stmt = conn.createStatement()){
            String sql = "DELETE FROM Person";
            stmt.executeUpdate(sql);
        }
        catch (SQLException e) {
            throw new SQLException();
        }
    }

    /**
     * Deletes all person objects associated with a user.
     *
     * @param username the username to remove person all info from
     * @throws DataAccessException errors in the database
     */
    public void deleteFromUser(String username) throws DataAccessException {

    }

    /**
     * Gets the person with the specified ID (if the person is associated with the current user).
     *
     * @param id the ID of the person to find
     * @return the person whose ID matches
     * @throws DataAccessException errors in the database
     */
    public Person findPerson(String id) throws DataAccessException {
        Person person;
        ResultSet rs = null;
        String sql = "SELECT * FROM Person WHERE personID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                person = new Person(rs.getString("personID"), rs.getString("associatedUsername"),
                        rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"),
                        rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID"));
                return person;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding person");
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
        return null;
    }

    /**
     * Gets all family members of the current user.
     *
     * @param username the username of the user
     * @return a list of people
     * @throws DataAccessException errors in the database
     */
    public ArrayList<Person> findFamily(String username) throws DataAccessException {
        return null;
    }

}
