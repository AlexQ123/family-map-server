package ServiceTest;

import dao.DataAccessException;
import dao.Database;
import dao.AuthTokenDAO;
import dao.PersonDAO;
import dao.UserDAO;
import service.result.SinglePersonResult;
import service.SinglePersonService;
import model.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class SinglePersonTest {

    private Database db;
    private SinglePersonService service;
    private UserDAO uDao;
    private AuthTokenDAO aDao;
    private PersonDAO pDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        service = new SinglePersonService();
        Connection conn = db.getConnection();
        uDao = new UserDAO(conn);
        aDao = new AuthTokenDAO(conn);
        pDao = new PersonDAO(conn);
        db.clearTables();
        User user = new User("test", "", "", "", "", "m", "goodID");
        uDao.insertUser(user);
        AuthToken authToken = new AuthToken("goodToken", "test");
        aDao.insertAuthToken(authToken);
        Person person = new Person("goodID", "test", "", "", "m");
        pDao.insertPerson(person);
        db.closeConnection(true);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.openConnection();
        db.clearTables();
        db.closeConnection(true);
    }

    @Test
    public void singlePersonSuccess() throws DataAccessException {
        SinglePersonResult result = service.singlePerson("goodID", "goodToken");
        assertTrue(result.isSuccess());
        assertEquals( "goodID", result.getPersonID());
        assertEquals( "test", result.getAssociatedUsername());
        assertNull(result.getFatherID());
        assertNull(result.getMotherID());
        assertNull(result.getSpouseID());
    }

    @Test
    public void singlePersonFailure() throws DataAccessException {
        SinglePersonResult result = service.singlePerson("goodID", "badToken");
        assertFalse(result.isSuccess());
        assertEquals("Error: Invalid authtoken", result.getMessage());
    }

}
