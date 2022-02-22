package ServiceTest;

import dao.DataAccessException;
import dao.Database;
import service.request.RegisterRequest;
import service.result.RegisterResult;
import service.RegisterService;
import service.request.LoginRequest;
import service.result.LoginResult;
import service.LoginService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private Database db;
    private LoginService service;
    private RegisterService registerService;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        service = new LoginService();
        registerService = new RegisterService();
        Connection conn = db.getConnection();
        db.clearTables();
        db.closeConnection(true);
        RegisterRequest registerRequest = new RegisterRequest("username", "password", "email", "first", "last", "m");
        RegisterResult registerResult = registerService.register(registerRequest);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.openConnection();
        db.clearTables();
        db.closeConnection(true);
    }

    @Test
    public void loginSuccess() throws DataAccessException {
        LoginRequest request = new LoginRequest("username", "password");
        LoginResult result = service.login(request);
        LoginResult expected = new LoginResult("whatever", "username", "whatever", true);
        assertEquals(expected.getUsername(), result.getUsername());
        assertTrue(result.isSuccess());
    }

    @Test
    public void loginFailure() throws DataAccessException {
        LoginRequest request = new LoginRequest("username", "wrong");
        LoginResult result = service.login(request);
        LoginResult expected = new LoginResult("Error: Incorrect password", false);
        assertEquals(expected.getMessage(), result.getMessage());
        assertFalse(result.isSuccess());
    }

}
