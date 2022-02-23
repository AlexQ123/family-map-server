package ServiceTest;

import dao.DataAccessException;
import dao.Database;
import service.request.LoadRequest;
import service.result.LoadResult;
import service.LoadService;
import model.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LoadTest {

    private Database db;
    private LoadService service;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        service = new LoadService();
        Connection conn = db.getConnection();
        db.clearTables();
        db.closeConnection(true);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.openConnection();
        db.clearTables();
        db.closeConnection(true);
    }

    @Test
    public void loadSuccess() throws DataAccessException {
        ArrayList<User> users = new ArrayList<>();
        User user1 = new User("user1", "", "", "", "", "m", "");
        User user2 = new User("user2", "", "", "", "", "m", "");
        User user3 = new User("user3", "", "", "", "", "m", "");
        users.add(user1);
        users.add(user2);
        users.add(user3);

        ArrayList<Person> persons = new ArrayList<>();
        Person person1 = new Person("person1", "", "", "", "f", "", "", "");
        Person person2 = new Person("person2", "", "", "", "m", "", "", "");
        persons.add(person1);
        persons.add(person2);

        ArrayList<Event> events = new ArrayList<>();
        Event event1 = new Event("event1", "", "", 0.9f, 0.99f, "", "", "", 0);
        events.add(event1);

        LoadRequest request = new LoadRequest(users, persons, events);
        LoadResult result = service.load(request);
        assertEquals("Successfully added 3 users, 2 persons, and 1 events to the database.", result.getMessage());
        assertTrue(result.isSuccess());
    }

    @Test
    public void loadFailure() throws DataAccessException {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Person> persons = new ArrayList<>();
        ArrayList<Event> events = null;
        LoadRequest request = new LoadRequest(users, persons, events);
        LoadResult result = service.load(request);
        assertEquals("Error: Request property missing or has invalid value", result.getMessage());
        assertFalse(result.isSuccess());
    }

}
