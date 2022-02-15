package DAOTest;

import dao.DataAccessException;
import dao.Database;
import dao.UserDAO;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {

    private Database db;
    private User user;
    private UserDAO uDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        user = new User("johnny123", "notsecure", "johnny123@johnny.com", "Johnny",
                "Appleseed", "m", "70ja70");
        Connection conn = db.getConnection();
        db.clearTables();
        uDao = new UserDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        uDao.insertUser(user);
        User compareUser = uDao.findUser(user.getUsername());
        assertNotNull(compareUser);
        assertEquals(user, compareUser);
    }

    @Test
    public void insertFail() throws DataAccessException {
        uDao.insertUser(user);
        assertThrows(DataAccessException.class, () -> uDao.insertUser(user));
    }

    @Test
    public void findPass() throws DataAccessException {
        uDao.insertUser(user);
        User compareUser = uDao.findUser(user.getUsername());
        assertEquals(user, compareUser);
    }

    @Test
    public void findFail() throws DataAccessException {
        uDao.insertUser(user);
        User compareUser = uDao.findUser("fail");
        assertNull(compareUser);
    }

    @Test
    public void clearTest() throws DataAccessException {
        uDao.insertUser(user);
        try {
            uDao.clearUser();
            User testUser = uDao.findUser(user.getUsername());
            assertNull(testUser);
        }
        catch (SQLException e) {
            fail(e.getMessage());
        }
    }

}
