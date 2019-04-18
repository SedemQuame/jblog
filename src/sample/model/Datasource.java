package sample.model;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Datasource {
    //    Creating Database Constants.
//    ----------------------------
    private static final String DB_NAME = "Jblog.db";
    private static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\sedem\\Documents\\Projects\\Java\\JBlog Gui\\" + DB_NAME;


    //    User's Table Information
//    ------------------------
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_FIRSTNAME = "firstname";
    private static final String COLUMN_LASTNAME = "lastname";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONENUMBER = "phonenumber";
    private static final String COLUMN_PASSWARD = "password";
    private static final String COLUMN_USERID = "userId";

    //    Story's Table Information
//    ------------------------
    private static final String TABLE_STORIES = "stories";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_INTRODUCTION = "introduction";
    private static final String COLUMN_BODY = "body";
    private static final String COLUMN_CONCLUSION = "conclusion";
    private static final String COLUMN_STORYID = "storyId";
    private static final String COLUMN_IMAGEURL = "imageurl";


    /*Database connection name*/
    private static Connection conn;

    public static Connection getConn() {
        return conn;
    }

    //       METHODS FOR INSERTING DATA THE DATABASE.
//       ----------------------------------------
    /*Method for populating the users table.*/
    public static void populateUsersTable(String firstname, String lastname, int age, String email, int phonenumber, String password) throws SQLException {
        try (Statement statement = getConn().createStatement()) {
            statement.execute(String.format("INSERT INTO %s(%s, %s, %s, %s, %s, %s) VALUES ('%s', '%s', '%d', '%s', '%d', '%s')", TABLE_USERS, COLUMN_FIRSTNAME, COLUMN_LASTNAME, COLUMN_AGE, COLUMN_EMAIL, COLUMN_PHONENUMBER, COLUMN_PASSWARD, firstname, lastname, age, email, phonenumber, password));
        } catch (SQLException event) {
            System.out.println("Query failed: " + event.getMessage());


//            todo
//            Give user notification that connection did not happen.
        }

    }

    /*Method for populating the stories table.*/
    public static void populateStoriesTable(String title, String category, String introduction, String body, String conclusion, String imageUrl) throws SQLException {
        try (Statement statement = getConn().createStatement()) {
            statement.execute(String.format("INSERT INTO %s(%s, %s, %s, %s, %s, %s) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
                    TABLE_STORIES, COLUMN_TITLE, COLUMN_CATEGORY, COLUMN_INTRODUCTION, COLUMN_BODY, COLUMN_CONCLUSION, COLUMN_IMAGEURL, title, category, introduction, body, conclusion, imageUrl));
        } catch (SQLException event) {
            System.out.println("Query failed: " + event.getMessage());
        }
    }

    //      METHODS FOR UPDATING DATA IN THE DATABASE.
//      ------------------------------------------
    /*Method for updating the users table.*/
    public static void updateUsersTable(String firstname, String lastname, int age, String email, int phonenumber, String password, int arthurid) throws SQLException {
        try (Statement statement = getConn().createStatement()) {
            statement.execute(String.format("UPDATE %sSET %s = firstname,%s = %s, %s = %d, %s = %s, %s = %d, %s = %s, WHERE %s = %d;", TABLE_USERS, COLUMN_FIRSTNAME, COLUMN_LASTNAME, lastname, COLUMN_AGE, age, COLUMN_EMAIL, email, COLUMN_PHONENUMBER, phonenumber, COLUMN_PASSWARD, password, COLUMN_USERID, arthurid));
        } catch (SQLException event) {
            System.out.println("Query failed: " + event.getMessage());
        }
    }

    /*Method for updating the stories table.*/
    public static void updateStoriesTable(String title, String category, String introduction, String body, String conclusion, int storyid) throws SQLException {
        try (Statement statement = getConn().createStatement()) {
            statement.execute(String.format("UPDATE %s SET %s = title,%s = %s, %s = %s, %s = %s, %s = %s, WHERE %s = %d;", TABLE_STORIES, COLUMN_TITLE, COLUMN_CATEGORY, category, COLUMN_INTRODUCTION, introduction, COLUMN_BODY, body, COLUMN_CONCLUSION, conclusion, COLUMN_IMAGEURL, storyid));
        } catch (SQLException event) {
            System.out.println("Query failed: " + event.getMessage());
        }
    }

    //    Creating Methods to Delete Data From The Database.
//    --------------------------------------------------
    public static void deleteFromUsersTable(String firstname, String lastname, int age, String email, int phonenumber, String password, int arthurid) throws SQLException {
        try (Statement statement = getConn().createStatement()) {
            statement.execute(String.format("DELETE * FROM %s WHERE %s = %d ;", TABLE_USERS, COLUMN_USERID, arthurid));
        } catch (SQLException event) {
            System.out.println("Query failed: " + event.getMessage());
        }
    }

    /*Method for updating the stories table.*/
    public static void deleteFromStoriesTable(int storyid) throws SQLException {
        try (Statement statement = getConn().createStatement()) {
            statement.execute(String.format("DELETE * FROM %s WHERE %s = %d ;", TABLE_STORIES, COLUMN_STORYID, storyid));
        } catch (SQLException event) {
            System.out.println("Query failed: " + event.getMessage());
        }
//    statement.execute("DELETE * FROM " + TABLE_STORIES + " WHERE " + COLUMN_STORYID + " = " + storyid + " ;");
    }

    //    METHODS FOR IMPLEMENTING LOGIN FUNCTIONALITY.
//  ---------------------------------------------------
    public static Boolean logIntoUserAccount(String emailAddress, String password) {
        try (Statement statement = getConn().createStatement()) {
//            ResultSet results = statement.executeQuery("SELECT " + COLUMN_PASSWARD + " FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " LIKE %" + emailAddress + "%;");

            ResultSet results = statement.executeQuery("SELECT " + COLUMN_PASSWARD + " FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = \"" + emailAddress + "\";");
//          ResultSet results = statement.executeQuery(String.format("SELECT %s FROM %s WHERE %s = \"%s\";", COLUMN_PASSWARD, TABLE_USERS, COLUMN_EMAIL, emailAddress));

            System.out.println(results.getString(COLUMN_PASSWARD));
            return results.getString(COLUMN_PASSWARD).equals(password);
        } catch (SQLException event) {
            System.out.println("Query failed: " + event.getMessage());
            return false;
        }
    }

    public String getConnectionString() {
        return CONNECTION_STRING;
    }

    //    Creating Methods To Define State Of Database.
//    --------------------------------------------
    /*Open Database*/
    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException event) {
            System.out.println("Could not connect to database.");
            System.out.println("Error: " + event.getMessage());
            return false;
        }
    }

    /*Close Database*/
    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException event) {
            System.out.println("Couldn't close connection.");
            System.out.println("Error: " + event.getMessage());
        }
    }

    //    Creating Methods To Create The Appropriate Database Tables.
//    -----------------------------------------------------------
    public void createTables() {
        try (Statement statement = conn.createStatement()) {
            statement.execute(String.format("CREATE TABLE IF NOT EXISTS %s(%s TEXT, %s TEXT, %s INTEGER, %s TEXT,%s INTEGER , %s TEXT)", TABLE_USERS, COLUMN_FIRSTNAME, COLUMN_LASTNAME, COLUMN_AGE, COLUMN_EMAIL, COLUMN_PHONENUMBER, COLUMN_PASSWARD));

            statement.execute(String.format("CREATE TABLE IF NOT EXISTS %s(%s TEXT, %s TEXT, %s TEXT, %s TEXT,%s TEXT , %s INTEGER , %s INTEGER)", TABLE_STORIES, COLUMN_TITLE, COLUMN_CATEGORY, COLUMN_INTRODUCTION, COLUMN_BODY, COLUMN_CONCLUSION, COLUMN_IMAGEURL, COLUMN_STORYID));
        } catch (SQLException event) {
            System.out.println("Query failed: " + event.getMessage());
        }
    }

//    Creating Methods to Insert Data Into The Database.
//    --------------------------------------------------

    //    Creating Methods to Query/Read Database For Information.
//    ---------------------------------------------------
    /*Return All User's Data*/
    public List<User> queryAllUsers() {
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_USERS)) {

            List<User> users = new ArrayList<>();
            while (results.next()) {
                User user = searchUser(results);
                users.add(user);
            }

            return users;

        } catch (SQLException event) {
            System.out.println("Query failed: " + event.getMessage());
            return null;
        }
    }

    /*Return A Searched User's Data*/
    public User queryUsers(String searchString) {
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_FIRSTNAME + " LIKE '%" + searchString + "%'")) {
            return searchUser(results);
        } catch (SQLException event) {
            System.out.println("Query failed: " + event.getMessage());
            return null;
        }
    }


//    Creating Methods to Update Data In The Database.
//    ------------------------------------------------

    /*Return All Story's Data*/
    public List<Story> queryAllStories() {
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_STORIES)) {
            List<Story> stories = new ArrayList<>();
            while (results.next()) {
                Story story = searchStory(results);
                stories.add(story);
            }
            return stories;
        } catch (SQLException event) {
            System.out.println("Query failed: " + event.getMessage());
            return null;
        }
    }

    /*Return A Searched User's Data*/
    public Story queryStories(String storyCategory) {
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(String.format("SELECT * FROM %s WHERE %s LIKE %%%s%%", TABLE_STORIES, COLUMN_CATEGORY, storyCategory))) {
            if (results.next()) {
                do return searchStory(results); while (results.next());
            }
            return null;
        } catch (SQLException event) {
            System.out.println("Query failed: " + event.getMessage());
            return null;
        }
    }

    //       METHODS FOR QUERYING THE DATABASE.
//       ----------------------------------
    /*Function to query database for searched story.*/
    public Story searchStory(ResultSet results) throws SQLException {
        Story story = new Story();
        story.setTitle(results.getString(COLUMN_TITLE));
        story.setCategory(results.getString(COLUMN_CATEGORY));
        story.setIntroduction(results.getString(COLUMN_INTRODUCTION));
        story.setBody(results.getString(COLUMN_BODY));
        story.setConclusion(results.getString(COLUMN_CONCLUSION));
        story.setArthurId(results.getInt(COLUMN_IMAGEURL));
        story.setStoryId(results.getInt(COLUMN_STORYID));
        return story;
    }

    /*Function to query database for a searched user.*/
    public User searchUser(ResultSet results) throws SQLException {
        User user = new User();
        user.setFirstname(results.getString(COLUMN_FIRSTNAME));
        user.setLastname(results.getString(COLUMN_LASTNAME));
        user.setEmail(results.getString(COLUMN_EMAIL));
        user.setAge(results.getInt((COLUMN_AGE)));
        user.setPhonenumber(results.getInt((COLUMN_PHONENUMBER)));
        user.setPassword(results.getString(COLUMN_PASSWARD));       //PASSWORD.
        user.setUserId(results.getInt((COLUMN_USERID)));
        return user;
    }


//    Creating Methods to Implement Login Functionality.
//    --------------------------------------------------

    public User searchUserPassword(ResultSet results) throws SQLException {
        User user = new User();
        user.setPassword(results.getString(COLUMN_PASSWARD));       //PASSWORD.
        return user;
    }

} 
