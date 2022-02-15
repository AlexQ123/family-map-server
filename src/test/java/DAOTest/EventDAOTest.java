package DAOTest;

import dao.DataAccessException;
import dao.Database;
import dao.EventDAO;
import dao.UserDAO;
import model.Event;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EventDAOTest {

    private Database db;
    private Event event;
    private Event event2;
    private Event event3;
    private User user;
    private EventDAO eDao;
    private UserDAO uDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        event = new Event("a", "johnny123", "p", 0, 0, "", "", "", 0);
        event2 = new Event("b", "johnny123", "p", 0, 0, "", "", "", 0);
        event3 = new Event("c", "johnny123", "p", 0, 0, "", "", "", 0);
        user = new User("johnny123", "", "", "", "", "", "");
        Connection conn = db.getConnection();
        db.clearTables();
        eDao = new EventDAO(conn);
        uDao = new UserDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        eDao.insertEvent(event);
        Event compareEvent = eDao.findEvent(event.getEventID());
        assertNotNull(compareEvent);
        assertEquals(event, compareEvent);
    }

    @Test
    public void insertFail() throws DataAccessException {
        eDao.insertEvent(event);
        assertThrows(DataAccessException.class, () -> eDao.insertEvent(event));
    }

    @Test
    public void findPass() throws DataAccessException {
        eDao.insertEvent(event);
        Event compareEvent = eDao.findEvent(event.getEventID());
        assertEquals(event, compareEvent);
    }

    @Test
    public void findFail() throws DataAccessException {
        eDao.insertEvent(event);
        Event compareEvent = eDao.findEvent("fail");
        assertNull(compareEvent);
    }

    @Test
    public void clearTest() throws DataAccessException {
        eDao.insertEvent(event);
        try {
            eDao.clearEvent();
            Event testEvent = eDao.findEvent(event.getEventID());
            assertNull(testEvent);
        }
        catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void deleteFromUserTest() throws DataAccessException {
        uDao.insertUser(user);
        eDao.insertEvent(event);
        eDao.deleteFromUser("bob");
        Event compareEvent = eDao.findEvent(event.getEventID());
        assertEquals(event, compareEvent);
        eDao.deleteFromUser(user.getUsername());
        Event isNull = eDao.findEvent(event.getEventID());
        assertNull(isNull);
    }

    @Test
    public void findAllEventsTest() throws DataAccessException {
        uDao.insertUser(user);
        eDao.insertEvent(event);
        eDao.insertEvent(event2);
        eDao.insertEvent(event3);
        ArrayList<Event> expected = new ArrayList<>();
        expected.add(event);
        expected.add(event2);
        expected.add(event3);
        ArrayList<Event> actual = eDao.findAllEvents(user.getUsername());
        assertEquals(expected, actual);
    }

}
