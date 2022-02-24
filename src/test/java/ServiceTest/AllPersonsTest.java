package ServiceTest;

import dao.DataAccessException;
import dao.Database;
import dao.AuthTokenDAO;
import dao.PersonDAO;
import dao.UserDAO;
import service.result.AllPersonsResult;
import service.AllPersonsService;
import model.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class AllPersonsTest {

    private Database db;
    private AllPersonsService service;
    private UserDAO uDao;
    private AuthTokenDAO aDao;
    private PersonDAO pDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        service = new AllPersonsService();
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
        Person person2 = new Person("goodID2", "test", "", "", "f");
        pDao.insertPerson(person);
        pDao.insertPerson(person2);
        db.closeConnection(true);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.openConnection();
        db.clearTables();
        db.closeConnection(true);
    }

    @Test
    public void allPersonsSuccess() throws DataAccessException {
        AllPersonsResult result = service.allPersons("goodToken");
        assertTrue(result.isSuccess());
        assertEquals("goodID", result.getData().get(0).getPersonID());
        assertEquals("goodID2", result.getData().get(1).getPersonID());
        assertThrows(IndexOutOfBoundsException.class, () -> result.getData().get(2).getPersonID());
    }

    @Test
    public void allPersonsFailure() throws DataAccessException {
        AllPersonsResult result = service.allPersons("badToken");
        assertFalse(result.isSuccess());
        assertEquals("Error: Invalid authtoken", result.getMessage());
    }

}
