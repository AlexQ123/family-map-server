package service.request;

import model.*;
import java.util.ArrayList;

/**
 * A request to load user, person, and event data into the database.
 */
public class LoadRequest {

    /**
     * The array of users in the request body.
     */
    private ArrayList<User> users;

    /**
     * The array of persons in the request body.
     */
    private ArrayList<Person> persons;

    /**
     * The array of events in the request body.
     */
    private ArrayList<Event> events;

    /**
     * Creates a "default" load request with no other information.
     */
    public LoadRequest() {
        this.users = null;
        this.persons = null;
        this.events = null;
    }

    /**
     * Creates a load request with all information.
     *
     * @param users request body users
     * @param persons request body persons
     * @param events request body events
     */
    public LoadRequest(ArrayList<User> users, ArrayList<Person> persons, ArrayList<Event> events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    // Auto-generated getters and setters, no javadoc needed
    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

}
