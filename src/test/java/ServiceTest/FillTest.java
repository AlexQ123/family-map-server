package ServiceTest;

import dao.DataAccessException;
import dao.Database;
import service.request.RegisterRequest;
import service.result.RegisterResult;
import service.RegisterService;
import service.result.FillResult;
import service.FillService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class FillTest {

    private Database db;
    private RegisterService registerService;
    private FillService service;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        registerService = new RegisterService();
        service = new FillService();
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
    public void fillSuccess() throws DataAccessException {
        FillResult result = service.fill("username", 2);
        assertEquals("Successfully added 7 persons and 19 events to the database.", result.getMessage());
        assertTrue(result.isSuccess());
    }

    @Test
    public void fillFailureUsername() throws DataAccessException {
        FillResult result = service.fill("invalid", 2);
        assertEquals("Error: Username not found", result.getMessage());
        assertFalse(result.isSuccess());
    }

    @Test
    public void fillFailureGenerations() throws DataAccessException {
        FillResult result = service.fill("username", -1);
        assertEquals("Error: The generations parameter cannot be negative", result.getMessage());
        assertFalse(result.isSuccess());
    }
}
