package dao;

import model.Person;

import java.sql.SQLException;
import java.util.List;

public class PersonDAO {

    /**
     * Creates a new person (person row in the database).
     *
     * @param person the person being inserted
     * @throws SQLException errors in the database
     */
    public void insertPerson(Person person) throws SQLException {

    }

    /**
     * Deletes all data from the person table.
     *
     * @throws SQLException errors in the database
     */
    public void clearPerson() throws SQLException {

    }

    /**
     * Deletes all person objects associated with a user.
     *
     * @param username the username to remove person all info from
     * @throws SQLException errors in the database
     */
    public void deleteFromUser(String username) throws SQLException {

    }

    /**
     * Gets the person with the specified ID (if the person is associated with the current user).
     *
     * @param id the ID of the person to find
     * @return the person whose ID matches
     * @throws SQLException errors in the database
     */
    public Person findPerson(String id) throws SQLException {
        return null;
    }

    /**
     * Gets all family members of the current user.
     *
     * @param username the username of the user
     * @return a list of people
     * @throws SQLException errors in the database
     */
    public List<Person> findFamily(String username) throws SQLException {
        return null;
    }

}
