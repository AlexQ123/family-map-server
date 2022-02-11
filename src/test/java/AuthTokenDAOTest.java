import dao.DataAccessException;
import dao.Database;
import dao.AuthTokenDAO;
import dao.PersonDAO;
import model.AuthToken;
import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class AuthTokenDAOTest {

    private Database db;
    private AuthToken authtoken;
    private AuthTokenDAO aDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        authtoken = new AuthToken("12345", "jack");
        Connection conn = db.getConnection();
        db.clearTables();
        aDao = new AuthTokenDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        aDao.insertAuthToken(authtoken);
        boolean found = aDao.validateAuthToken(authtoken);
        assertTrue(found);
    }

    @Test
    public void insertFail() throws DataAccessException {
        aDao.insertAuthToken(authtoken);
        assertThrows(DataAccessException.class, () -> aDao.insertAuthToken(authtoken));
    }

}
