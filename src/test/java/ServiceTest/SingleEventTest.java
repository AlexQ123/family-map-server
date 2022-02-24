package ServiceTest;

import dao.DataAccessException;
import dao.Database;
import dao.AuthTokenDAO;
import dao.EventDAO;
import dao.UserDAO;
import service.result.SingleEventResult;
import service.SingleEventService;
import model.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class SingleEventTest {

    private Database db;
    private SingleEventService service;
    private UserDAO uDao;
    private AuthTokenDAO aDao;
    private EventDAO eDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        service = new SingleEventService();
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
        eDao.insertEvent(event);
        db.closeConnection(true);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.openConnection();
        db.clearTables();
        db.closeConnection(true);
    }

    @Test
    public void singleEventSuccess() throws DataAccessException {
        SingleEventResult result = service.singleEvent("goodID", "goodToken");
        assertTrue(result.isSuccess());
        assertEquals("goodID", result.getEventID());
        assertEquals("test", result.getAssociatedUsername());
    }

    @Test
    public void singleEventFailure() throws DataAccessException {
        SingleEventResult result = service.singleEvent("badID", "goodToken");
        assertFalse(result.isSuccess());
        assertEquals("Error: Invalid eventID parameter", result.getMessage());
    }

}
