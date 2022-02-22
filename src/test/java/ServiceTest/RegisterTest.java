package ServiceTest;

import dao.DataAccessException;
import dao.Database;
import service.request.RegisterRequest;
import service.result.RegisterResult;
import service.RegisterService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterTest {

    private Database db;
    private RegisterService service;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        service = new RegisterService();
        Connection conn = db.getConnection();
        db.clearTables();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.openConnection();
        db.clearTables();
        db.closeConnection(true);
    }

    @Test
    public void registerSuccess() throws DataAccessException {
        db.closeConnection(true);
        RegisterRequest request = new RegisterRequest("test", "mypassword", "myemail", "john", "doe", "m");
        RegisterResult result = service.register(request);
        RegisterResult expected = new RegisterResult("whatever", "test", "whatever", true);
        assertEquals(result.getUsername(), expected.getUsername());
        assertTrue(result.isSuccess());
    }

    @Test
    public void registerFailure() throws DataAccessException {
        db.closeConnection(true);
        RegisterRequest badRequest = new RegisterRequest("valid", "valid", "valid", "valid", "valid", "invalid");
        RegisterResult result = service.register(badRequest);
        RegisterResult expected = new RegisterResult("Error: Request property missing or has invalid value", false);
        assertEquals(expected.getMessage(), result.getMessage());
        assertFalse(result.isSuccess());
    }

}
