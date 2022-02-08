import dao.DataAccessException;
import dao.Database;
import dao.PersonDAO;
import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDAOTest {

    private Database db;
    private Person person;
    private PersonDAO pDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        person = new Person("r4nd0m", "johnny123", "Beth", "Appleseed", "f",
                null, null, "f4k3");
        Connection conn = db.getConnection();
        db.clearTables();
        pDao = new PersonDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        pDao.insertPerson(person);
        Person comparePerson = pDao.findPerson(person.getPersonID());
        assertNotNull(comparePerson);
        assertEquals(person, comparePerson);
    }

    @Test
    public void insertFail() throws DataAccessException {
        pDao.insertPerson(person);
        assertThrows(DataAccessException.class, () -> pDao.insertPerson(person));
    }

    @Test
    public void findPass() throws DataAccessException {
        pDao.insertPerson(person);
        Person comparePerson = pDao.findPerson(person.getPersonID());
        assertEquals(person, comparePerson);
    }

    @Test
    public void findFail() throws DataAccessException {
        pDao.insertPerson(person);
        Person comparePerson = pDao.findPerson("fail");
        assertNull(comparePerson);
    }

    @Test
    public void clearTest() throws DataAccessException {
        pDao.insertPerson(person);
        try {
            pDao.clearPerson();
            Person testPerson = pDao.findPerson(person.getPersonID());
            assertNull(testPerson);
        }
        catch (SQLException e) {
            fail(e.getMessage());
        }
    }

}
