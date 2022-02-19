package generated;

import dao.*;
import model.*;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.FileReader;
import java.util.UUID;
import java.util.Random;
import java.sql.Connection;

public class AncestorGenerator {

    private LocationData locations;
    private FemaleNames fNames;
    private MaleNames mNames;
    private Surnames sNames;

    private String username;
    private String userFirstName;
    private String userLastName;

    private PersonDAO pDao;
    private EventDAO eDao;

    private int maxGenerations;

    public Person generatePerson(String gender, int generations) {
        parseJSON();

        Person mother = null;
        Person father = null;

        // recursively add generations of ancestors and events to the database
        if (generations > 0) {
            mother = generatePerson("f", generations - 1);
            father = generatePerson("m", generations - 1);

            mother.setSpouseID(father.getPersonID());
            father.setSpouseID(mother.getPersonID());

            // everyone but the user's person needs to be inserted here because of setting spouseID
            insertPerson(mother);
            insertPerson(father);

            addMarriageEvent(mother.getPersonID(), father.getPersonID(), generations);
        }

        // set person's properties
        Person person = new Person();
        person.setPersonID(UUID.randomUUID().toString());
        person.setAssociatedUsername(username);
        if (generations == maxGenerations) {
            person.setFirstName(userFirstName);
            person.setLastName(userLastName);
        }
        else {
            if (gender.equals("f")) {
                person.setFirstName(getFemaleFirstName());
            }
            else {
                person.setFirstName(getMaleFirstName());
            }
            person.setLastName(getRandomSurname());
        }
        person.setGender(gender);
        if (generations > 0) {
            person.setFatherID(father.getPersonID());
            person.setMotherID(mother.getPersonID());
        }

        // generate birth and death events for person
        addBirthEvent(person.getPersonID(), generations);
        if (generations != maxGenerations) {
            addDeathEvent(person.getPersonID(), generations);
        }

        // add the user's person to database
        if (generations == maxGenerations) {
            insertPerson(person);
        }

        return person;
    }

    private void parseJSON() {
        Gson gson = new Gson();
        try {
            Reader lReader = new FileReader("json/locations.json");
            locations = (LocationData)gson.fromJson(lReader, LocationData.class);

            Reader fReader = new FileReader("json/fnames.json");
            fNames = (FemaleNames)gson.fromJson(fReader, FemaleNames.class);

            Reader mReader = new FileReader("json/mnames.json");
            mNames = (MaleNames)gson.fromJson(mReader, MaleNames.class);

            Reader sReader = new FileReader("json/snames.json");
            sNames = (Surnames)gson.fromJson(sReader, Surnames.class);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error occurred while parsing json files");
        }
    }

    private void addMarriageEvent(String motherPersonID, String fatherPersonID, int generation) {
        Location location = getLocation();
        float latitude = Float.parseFloat(location.getLatitude());
        float longitude = Float.parseFloat(location.getLongitude());
        int year = 2000 - (20 * (maxGenerations - generation));
        Event motherEvent = new Event(UUID.randomUUID().toString(), username, motherPersonID, latitude, longitude, location.getCountry(),
                location.getCity(), "marriage", year);
        Event fatherEvent = new Event(UUID.randomUUID().toString(), username, fatherPersonID, latitude, longitude, location.getCountry(),
                location.getCity(), "marriage", year);
        insertEvent(motherEvent);
        insertEvent(fatherEvent);
    }

    private Location getLocation() {
        int random = new Random().nextInt(locations.data.length);
        return locations.data[random];
    }

    private String getFemaleFirstName() {
        int random = new Random().nextInt(fNames.data.length);
        return fNames.data[random];
    }

    private String getMaleFirstName() {
        int random = new Random().nextInt(mNames.data.length);
        return mNames.data[random];
    }

    private String getRandomSurname() {
        int random = new Random().nextInt(sNames.data.length);
        return sNames.data[random];
    }


    private void addBirthEvent(String personID, int generation) {
        Location location = getLocation();
        float latitude = Float.parseFloat(location.getLatitude());
        float longitude = Float.parseFloat(location.getLongitude());
        int year = 2000 - (20 * (maxGenerations - generation));
        Event event = new Event(UUID.randomUUID().toString(), username, personID, latitude, longitude, location.getCountry(),
                location.getCity(), "birth", year);
        insertEvent(event);
    }

    private void addDeathEvent(String personID, int generation) {
        Location location = getLocation();
        float latitude = Float.parseFloat(location.getLatitude());
        float longitude = Float.parseFloat(location.getLongitude());
        int year = 2020 - (20 * (maxGenerations - generation - 1));
        Event event = new Event(UUID.randomUUID().toString(), username, personID, latitude, longitude, location.getCountry(),
                location.getCity(), "death", year);
        insertEvent(event);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public void setMaxGenerations(int maxGenerations) {
        this.maxGenerations = maxGenerations;
    }

    private void insertEvent(Event event) {
        Database db = new Database();
        try {
            db.openConnection();
            Connection conn = db.getConnection();

            eDao = new EventDAO(conn);
            eDao.insertEvent(event);

            db.closeConnection(true);
        }
        catch (DataAccessException e) {
            e.printStackTrace();
            try {
                db.closeConnection(false);
            }
            catch (DataAccessException ex) {
                // at this point, the exception that closeConnection might throw has already caused the fatal error
            }
            System.out.println("Error occurred while generating event");
        }
    }

    private void insertPerson(Person person) {
        Database db = new Database();
        try {
            db.openConnection();
            Connection conn = db.getConnection();

            pDao = new PersonDAO(conn);
            pDao.insertPerson(person);

            db.closeConnection(true);
        }
        catch (DataAccessException e) {
            e.printStackTrace();
            try {
                db.closeConnection(false);
            }
            catch (DataAccessException ex) {
                // at this point, the exception that closeConnection might throw has already caused the fatal error
            }
            System.out.println("Error occurred while generating person");
        }
    }

}
