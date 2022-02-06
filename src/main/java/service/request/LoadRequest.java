package service.request;

import model.*;

/**
 * A request to load user, person, and event data into the database.
 */
public class LoadRequest {

    /**
     * The array of users in the request body.
     */
    private User[] users;

    /**
     * The array of persons in the request body.
     */
    private Person[] persons;

    /**
     * The array of events in the request body.
     */
    private Event[] events;

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
    public LoadRequest(User[] users, Person[] persons, Event[] events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    // Auto-generated getters and setters, no javadoc needed
    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Person[] getPersons() {
        return persons;
    }

    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }

}
