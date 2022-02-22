package ServiceTest;

import dao.DataAccessException;
import dao.Database;
import service.request.RegisterRequest;
import service.result.RegisterResult;
import service.RegisterService;
import service.result.ClearResult;
import service.ClearService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class ClearTest {

    private Database db;
    private ClearService service;
    private RegisterService registerService;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        service = new ClearService();
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
    public void clearWithData() throws DataAccessException {
        ClearResult result = service.clear();
        ClearResult expected = new ClearResult("Clear succeeded.", true);
        assertEquals(expected.getMessage(), result.getMessage());
        assertTrue(result.isSuccess());
    }

    @Test
    public void clearWithoutData() throws DataAccessException {
        db.openConnection();
        db.clearTables();
        db.closeConnection(true);
        ClearResult result = service.clear();
        ClearResult expected = new ClearResult("Clear succeeded.", true);
        assertEquals(expected.getMessage(), result.getMessage());
        assertTrue(result.isSuccess());
    }

}
