package ServiceTest;

import dao.DataAccessException;
import dao.Database;
import dao.AuthTokenDAO;
import dao.EventDAO;
import dao.UserDAO;
import service.result.AllEventsResult;
import service.AllEventsService;
import model.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class AllEventsTest {

    private Database db;
    private AllEventsService service;
    private UserDAO uDao;
    private AuthTokenDAO aDao;
    private EventDAO eDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        service = new AllEventsService();
        Connection conn = db.getConnection();
        uDao = new UserDAO(conn);
        aDao = new AuthTokenDAO(conn);
        eDao = new EventDAO(conn);
        db.clearTables();
        User user = new User("test", "", "", "", "", "m", "");
        uDao.insertUser(user);
        AuthToken authToken = new AuthToken("goodToken", "test");
        aDao.insertAuthToken(authToken);
        Event event = new Event("goodID", "test", "", 10.9f, 9.10f, "", "", "", 0);
        Event event2 = new Event("goodID2", "test", "", 10.10f, 9.9f, "", "", "", 0);
        eDao.insertEvent(event);
        eDao.insertEvent(event2);
        db.closeConnection(true);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.openConnection();
        db.clearTables();
        db.closeConnection(true);
    }

    @Test
    public void allEventsSuccess() throws DataAccessException {
        AllEventsResult result = service.allEvents("goodToken");
        assertTrue(result.isSuccess());
        assertEquals("goodID", result.getData().get(0).getEventID());
        assertEquals("goodID2", result.getData().get(1).getEventID());
        assertThrows(IndexOutOfBoundsException.class, () -> result.getData().get(2).getEventID());
    }

    @Test
    public void allEventsFailure() throws DataAccessException {
        AllEventsResult result = service.allEvents("badToken");
        assertFalse(result.isSuccess());
        assertEquals("Error: Invalid authtoken", result.getMessage());
    }

}
