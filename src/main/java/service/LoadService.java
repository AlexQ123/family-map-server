package service;

import service.request.LoadRequest;
import service.result.LoadResult;

import dao.*;
import model.*;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * A service for loading user, person, and event data into the database.
 */
public class LoadService {

    /**
     * Clears the database, and then loads new user, person, and event data into it.
     *
     * @param r the request body with the loading information
     * @return the response body indicating success or error
     */
    public LoadResult load(LoadRequest r) {
        if (!isValidRequest(r)) {
            return new LoadResult("Error: Request property missing or has invalid value", false);
        }

        Database db = new Database();
        try {
            db.openConnection();
            Connection conn = db.getConnection();

            db.clearTables();

            UserDAO uDao = new UserDAO(conn);
            ArrayList<User> users = r.getUsers();
            for (User user : users) {
                uDao.insertUser(user);
            }

            PersonDAO pDao = new PersonDAO(conn);
            ArrayList<Person> persons = r.getPersons();
            for (Person person : persons) {
                pDao.insertPerson(person);
            }

            EventDAO eDao = new EventDAO(conn);
            ArrayList<Event> events = r.getEvents();
            for (Event event : events) {
                eDao.insertEvent(event);
            }

            db.closeConnection(true);

            String outputMessage = "Successfully added " + users.size() + " users, " + persons.size() + " persons, and " +
                    events.size() + " events to the database.";
            LoadResult result = new LoadResult(outputMessage, true);
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
            return new LoadResult("Error: Internal server error", false);
        }
    }

    private boolean isValidRequest(LoadRequest r) {
        return (areUsersValid(r.getUsers()) && arePersonsValid(r.getPersons()) && areEventsValid(r.getEvents()));
    }

    private boolean areUsersValid(ArrayList<User> users) {
        if (users == null) {
            return false;
        }
        for (User user : users) {
            if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null || user.getFirstName() == null
            || user.getLastName() == null || user.getPersonID() == null || (!user.getGender().equals("m") && !user.getGender().equals("f"))) {
                return false;
            }
        }
        return true;
    }

    private boolean arePersonsValid(ArrayList<Person> persons) {
        if (persons == null) {
            return false;
        }
        for (Person person : persons) {
            if (person.getPersonID() == null || person.getAssociatedUsername() == null || person.getFirstName() == null ||
            person.getLastName() == null || (!person.getGender().equals("m") && !person.getGender().equals("f"))) {
                return false;
            }
        }
        return true;
    }

    private boolean areEventsValid(ArrayList<Event> events) {
        if (events == null) {
            return false;
        }
        for (Event event : events) {
            if (event.getEventID() == null || event.getAssociatedUsername() == null || event.getPersonID() == null ||
            event.getCountry() == null || event.getCity() == null || event.getEventType() == null) {
                return false;
            }
        }
        return true;
    }

}
