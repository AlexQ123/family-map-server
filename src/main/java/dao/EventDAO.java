package dao;

import model.Event;

import java.sql.SQLException;
import java.util.List;

/**
 * A DAO for the event table.
 */
public class EventDAO {

    /**
     * Creates a new event (event row in the database).
     *
     * @param event the event being inserted
     * @throws SQLException errors in the database
     */
    public void insertEvent(Event event) throws SQLException {

    }

    /**
     * Deletes all data from the event table.
     *
     * @throws SQLException errors in the database
     */
    public void clearEvent() throws SQLException {

    }

    /**
     * Deletes all event objects associated with a user.
     *
     * @param username to username to remove all info from
     * @throws SQLException errors in the database
     */
    public void deleteFromUser(String username) throws SQLException {

    }

    /**
     * Gets the event with the specified ID (if the event is associated with the current user).
     *
     * @param id the ID of the event to find
     * @return the event whose ID matches
     * @throws SQLException errors in the database
     */
    public Event findEvent(String id) throws SQLException {
        return null;
    }

    /**
     * Gets all events for all family members of the current user.
     *
     * @param username the username of the user
     * @return a list of events
     * @throws SQLException errors in the database
     */
    public List<Event> findAllEvents(String username) throws SQLException {
        return null;
    }

}
