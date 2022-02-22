package service;

import service.result.FillResult;

import dao.*;
import model.*;
import generated.*;
import service.result.RegisterResult;

import java.sql.Connection;

/**
 * A service for filling a user's data for a certain number of generations.
 */
public class FillService {

    private UserDAO uDao;
    private PersonDAO pDao;
    private EventDAO eDao;

    /**
     * Fills the server's database with generated data for the specified username after deleting any existing data.
     *
     * @param username the username to fill data for
     * @param generations the specified number of generations
     * @return the response body indicating success or error
     */
    public FillResult fill(String username, int generations) {
        if (generations < 0) {
            return new FillResult("Error: The generations parameter cannot be negative", false);
        }

        Database db = new Database();
        try {
            db.openConnection();
            Connection conn = db.getConnection();

            uDao = new UserDAO(conn);
            User test = uDao.findUser(username);
            if (test == null) {
                db.closeConnection(false);
                return new FillResult("Error: Username not found", false);
            }

            pDao = new PersonDAO(conn);
            pDao.deleteFromUser(username);
            eDao = new EventDAO(conn);
            eDao.deleteFromUser(username);

            AncestorGenerator generator = new AncestorGenerator();
            generator.setMaxGenerations(generations);
            generator.setUsername(test.getUsername());
            generator.setUserFirstName(test.getFirstName());
            generator.setUserLastName(test.getLastName());
            db.closeConnection(true);
            Person person = generator.generatePerson(test.getGender(), generations);

            int numPersons = (int) Math.pow(2, (generations + 1)) - 1;
            int numEvents = numPersons * 3 - 2;
            String outputMessage = "Successfully added " + numPersons + " persons and " + numEvents + " events to the database.";
            FillResult result = new FillResult(outputMessage, true);
            return result;
        }
        catch (DataAccessException e) {
            e.printStackTrace();
            try {
                db.closeConnection(false);
            }
            catch (DataAccessException ex) {
                // at this point, the exception that closeConnection might throw has already caused the fatal error
            }
            return new FillResult("Error: Internal server error", false);
        }
    }

}
