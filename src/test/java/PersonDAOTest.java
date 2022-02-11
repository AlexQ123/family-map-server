import dao.DataAccessException;
import dao.Database;
import dao.PersonDAO;
import dao.UserDAO;
import model.Person;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDAOTest {

    private Database db;
    private Person person;
    private Person person2;
    private Person person3;
    private User user;
    private PersonDAO pDao;
    private UserDAO uDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        person = new Person("r4nd0m", "johnny123", "Beth", "Appleseed", "f",
                null, null, "f4k3");
        person2 = new Person("a", "johnny123", "", "", "", "", "", "");
        person3 = new Person("b", "johnny123", "", "", "", "", "", "");
        user = new User("johnny123", "", "", "", "", "", "");
        Connection conn = db.getConnection();
        db.clearTables();
        pDao = new PersonDAO(conn);
        uDao = new UserDAO(conn);
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

    @Test
    public void deleteFromUserTest() throws DataAccessException {
        uDao.insertUser(user);
        pDao.insertPerson(person);
        pDao.deleteFromUser("bob");
        Person comparePerson = pDao.findPerson(person.getPersonID());
        assertEquals(person, comparePerson);
        pDao.deleteFromUser(user.getUsername());
        Person isNull = pDao.findPerson(person.getPersonID());
        assertNull(isNull);
    }

    @Test
    public void findFamilyTest() throws DataAccessException {
        uDao.insertUser(user);
        pDao.insertPerson(person);
        pDao.insertPerson(person2);
        pDao.insertPerson(person3);
        ArrayList<Person> expected = new ArrayList<>();
        expected.add(person);
        expected.add(person2);
        expected.add(person3);
        ArrayList<Person> actual = pDao.findFamily(user.getUsername());
        assertEquals(expected, actual);
    }

}
