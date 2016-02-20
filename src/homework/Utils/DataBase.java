package homework.Utils;

import homework.Entities.Solution;
import homework.Users.AdminUser;
import homework.Users.SimpleUser;
import homework.Entities.Task;
import homework.Users.TeacherUser;
import homework.Users.User;
import java.util.HashSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBase {

    private DataBase() {
    }
    private static DataBase instance = null;

    /*       TOGGLE TO THESE IF ONLINE:         */
    //   static final String jdbcUrl = "jdbc:mysql://sql4.freesqldatabase.com:3306/sql4103014?useUnicode=yes&characterEncoding=UTF-8";
    //   static final String jdbcUser = "sql4103014";
    //   static final String jdbcPassword = "KcqdZxYZg1";

    /*                                                          */
 /*       TOGGLE TO THESE IF OFFLINE (MySQL WorkBench):     */
    static final String jdbcUrl = "jdbc:mysql://localhost:3306/Homework?useUnicode=yes&characterEncoding=UTF-8";
    static final String jdbcUser = "root";
    static final String jdbcPassword = "master1590";
    // static final String jdbcPassword = "";

    /*                                                                                  */
    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    /**
     ************************************Manage*Tasks*********************************
     */
    public boolean addNewTask(String taskName, Date deadline, int classID) throws SQLException {
        PreparedStatement pStatement = null;
        Connection connection = null;
        String queryUsers = "Insert into Tasks values (?,?,?,?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection
                    = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

            pStatement = connection.prepareStatement(queryUsers);
            pStatement.setInt(1, 0);
            pStatement.setString(2, taskName);
            pStatement.setDate(3, new java.sql.Date(deadline.getTime()));
            pStatement.setInt(4, classID);

            int resultSet = pStatement.executeUpdate();

            if (resultSet > 0) {

                System.out.println("Successfuly inserted Task");
                return true;
            } else {
                System.out.println("Failed to insert Task");
                return false;

            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            pStatement.close();		// close statement and resultSet
            connection.close();
            System.out.println("addNewTask connection closed");
        }

    }

    public boolean deleteTask(int taskID, int classID) throws SQLException {
        PreparedStatement pStatement = null;
        Connection connection = null;
        String queryUsers = "DELETE FROM Tasks WHERE ClassID = ? AND TaskID = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection
                    = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

            pStatement = connection.prepareStatement(queryUsers);
            pStatement.setInt(1, classID);
            pStatement.setInt(2, taskID);

            int resultSet = pStatement.executeUpdate();

            if (resultSet > 0) {

                System.out.println("Successfuly deleted Task");
                if (deleteTaskSolutions(taskID)) {
                    System.out.println("Successfuly deleted Task's solutions");
                } else {
                    System.out.println("Failed to delete Task's solutions");
                }
                return true;
            } else {
                System.out.println("Failed to delete Task");
                return false;

            }
        } catch (SQLException sqle) {
            System.out.println("Failed to delete Task");
            sqle.printStackTrace();
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to delete Task");
            e.printStackTrace();
            return false;
        } finally {
            pStatement.close();		// close statement and resultSet
            connection.close();
            System.out.println("deleteTask connection closed");
        }

    }

    public boolean editTask(int taskID, String taskName, Date deadline, int classID) throws SQLException {
        PreparedStatement pStatement = null;
        Connection connection = null;
        String queryUsers = "Update Tasks SET TaskNAme = ?, Deadline = ? WHERE TaskID = ? AND ClassID = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection
                    = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

            pStatement = connection.prepareStatement(queryUsers);
            pStatement.setString(1, taskName);
            pStatement.setDate(2, new java.sql.Date(deadline.getTime()));
            pStatement.setInt(3, taskID);
            pStatement.setInt(4, classID);

            int resultSet = pStatement.executeUpdate();

            if (resultSet > 0) {

                System.out.println("Successfully updated Task");
                return true;
            } else {
                System.out.println("Failed to update Task");
                return false;

            }
        } catch (SQLException sqle) {
            System.out.println("Failed to update Task");
            sqle.printStackTrace();
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to update Task");
            e.printStackTrace();
            return false;
        } finally {
            pStatement.close();		// close statement and resultSet
            connection.close();
            System.out.println("editTask connection closed");
        }
    }

    public HashSet<Task> getAllTasks(int classID) throws SQLException {
        HashSet<Task> temp = new HashSet<Task>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM Tasks WHERE ClassID='" + classID + "';";
        if (classID == -1) {
            query = "SELECT * FROM Tasks;";
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection
                    = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                temp.add(new Task(resultSet.getString("TaskName"), resultSet.getDate("Deadline"), resultSet.getInt("TaskID"), resultSet.getInt("ClassID")));
            }
            return temp;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            statement.close();		// close statement and resultSet
            connection.close();
            System.out.println("getAllTasks connection closed");
        }
    }

    public boolean isTaskExist(int id, int classID) {
        HashSet<Task> allTasks;
        try {
            allTasks = getAllTasks(-1);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
        for (Task t : allTasks) {
            if (t.getTaskID() == id && t.getClassID() == classID) {
                return true;
            }
        }
        return false;
    }

    private boolean deleteTaskSolutions(int taskID) throws SQLException {
        PreparedStatement pStatement = null;
        Connection connection = null;
        String queryUsers = "DELETE FROM solutions WHERE TaskID = ? ;";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection
                    = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

            pStatement = connection.prepareStatement(queryUsers);
            pStatement.setInt(1, taskID);

            int resultSet = pStatement.executeUpdate();

            if (resultSet > 0) {
                return true;
            } else {
                return false;

            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            pStatement.close();		// close statement and resultSet
            connection.close();
            System.out.println("deleteTaskSolutions connection closed");
        }
    }

    /**
     **********************************Manage*Users***********************************
     */
    public boolean createUser(User user) throws SQLException {
        if (!(user == null)) {
            PreparedStatement pStatement = null;
            Connection connection = null;
            String queryUsers = "INSERT INTO Users VALUES (?,?,?,?,?,?,?)";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection
                        = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

                pStatement = connection.prepareStatement(queryUsers);
                pStatement.setString(1, user.getUsername());
                pStatement.setString(2, user.getPassword());
                pStatement.setString(3, user.getEmail());
                pStatement.setInt(4, user.getUserType());
                if (user.getUserType() == 1) {
                    pStatement.setInt(5, getMaximumUserClassID() + 1);
                } else {
                    pStatement.setInt(5, 0);
                }
                pStatement.setString(6, "");
                pStatement.setString(7, "");

                int resultSet = pStatement.executeUpdate();

                if (resultSet > 0) {

                    System.out.println("Successfuly inserted User");
                    return true;
                } else {
                    System.out.println("Failed to  insert User");
                    return false;

                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                System.out.println("SQLException: " + sqle.getMessage());
                System.out.println("Vendor Error: " + sqle.getErrorCode());
                return false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            } finally {
                pStatement.close();		// close statement and resultSet
                connection.close();
                System.out.println("addUser connection closed");
            }

        }
        return false;
    }

    public boolean addUserToGroup(User user, int classId) throws SQLException {
        if (!(user == null)) {
            PreparedStatement pStatement = null;
            Connection connection = null;
            String queryUsers = "UPDATE Users SET ClassID = ? WHERE email = ?;";

            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection
                        = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

                pStatement = connection.prepareStatement(queryUsers);
                pStatement.setInt(1, classId);
                pStatement.setString(2, user.getEmail());

                int resultSet = pStatement.executeUpdate();

                if (resultSet > 0) {

                    System.out.println("Successfuly added User to group");
                    return true;
                } else {
                    System.out.println("Failed to add User to group");
                    return false;

                }
            } catch (SQLException sqle) {
                System.out.println("Failed to add User to group");
                sqle.printStackTrace();
                System.out.println("SQLException: " + sqle.getMessage());
                System.out.println("Vendor Error: " + sqle.getErrorCode());
                return false;
            } catch (ClassNotFoundException e) {
                System.out.println("Failed to add User to group");
                e.printStackTrace();
                return false;
            } finally {
                pStatement.close();		// close statement and resultSet
                connection.close();
                System.out.println("addUserToGroup connection closed");
            }

        }
        return false;
    }

    public boolean addUserDoneTask(User user, int taskID) throws SQLException {
        if (!(user == null)) {
            PreparedStatement pStatement = null;
            Connection connection = null;
            String queryUsers = "INSERT INTO userDoneTasks VALUES (? ,?);";

            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection
                        = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

                pStatement = connection.prepareStatement(queryUsers);

                pStatement.setString(1, user.getEmail());
                pStatement.setInt(2, taskID);

                int resultSet = pStatement.executeUpdate();

                if (resultSet > 0) {

                    System.out.println("Successfuly added User done task");
                    return true;
                } else {
                    System.out.println("Failed to add User done task");
                    return false;

                }
            } catch (SQLException sqle) {
                System.out.println("Failed to add User done task");
                sqle.printStackTrace();
                System.out.println("SQLException: " + sqle.getMessage());
                System.out.println("Vendor Error: " + sqle.getErrorCode());
                return false;
            } catch (ClassNotFoundException e) {
                System.out.println("Failed to add User done task");
                e.printStackTrace();
                return false;
            } finally {
                pStatement.close();		// close statement and resultSet
                connection.close();
                System.out.println("addUserDoneTask connection closed");
            }

        }
        return false;

    }

    public boolean removeUserDoneTask(User user, int taskID) throws SQLException {
        if (!(user == null)) {
            PreparedStatement pStatement = null;
            Connection connection = null;
            String queryUsers = "DELETE FROM userDoneTasks WHERE Email = ? AND TaskID = ?;";

            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection
                        = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

                pStatement = connection.prepareStatement(queryUsers);

                pStatement.setString(1, user.getEmail());
                pStatement.setInt(2, taskID);

                int resultSet = pStatement.executeUpdate();

                if (resultSet > 0) {

                    System.out.println("Successfuly removed User done task");
                    return true;
                } else {
                    System.out.println("Failed to remove User done task");
                    return false;

                }
            } catch (SQLException sqle) {
                System.out.println("Failed to remove User done task");
                sqle.printStackTrace();
                System.out.println("SQLException: " + sqle.getMessage());
                System.out.println("Vendor Error: " + sqle.getErrorCode());
                return false;
            } catch (ClassNotFoundException e) {
                System.out.println("Failed to remove User done task");
                e.printStackTrace();
                return false;
            } finally {
                pStatement.close();		// close statement and resultSet
                connection.close();
                System.out.println("removeUserDoneTask connection closed");
            }

        }
        return false;

    }

    public boolean removeUserFromGroup(User user) throws SQLException {
        if (!(user == null)) {
            PreparedStatement pStatement = null;
            Connection connection = null;
            String queryUsers = "UPDATE Users SET ClassID = 0 WHERE email = ?";

            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection
                        = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

                pStatement = connection.prepareStatement(queryUsers);
                pStatement.setString(1, user.getEmail());

                int resultSet = pStatement.executeUpdate();

                if (resultSet > 0) {

                    System.out.println("Successfully deleted User");
                    return true;
                } else {
                    System.out.println("Failed to delete User");
                    return false;

                }
            } catch (SQLException sqle) {
                System.out.println("Failed to delete User");
                sqle.printStackTrace();
                System.out.println("SQLException: " + sqle.getMessage());
                System.out.println("Vendor Error: " + sqle.getErrorCode());
                return false;
            } catch (ClassNotFoundException e) {
                System.out.println("Failed to delete User");
                e.printStackTrace();
                return false;
            } finally {
                pStatement.close();		// close statement and resultSet
                connection.close();
                System.out.println("removeUserFromGroup connection closed");
            }

        }
        return false;
    }

    public HashSet<Integer> getDoneTasks(String email) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        HashSet<Integer> doneTasks = new HashSet<>();
        String query = "SELECT TaskID FROM userDoneTasks WHERE email='" + email + "';";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection
                    = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                doneTasks.add(resultSet.getInt("TaskID"));
            }
            return doneTasks;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            statement.close();		// close statement and resultSet
            connection.close();
            System.out.println("getDoneTasks connection closed");
        }
    }

    public HashSet<User> getAllUsers(int classId) throws SQLException {
        HashSet<User> temp = new HashSet<User>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM Users WHERE ClassID='" + classId + "';";
        if (classId == -1) {
            query = "SELECT * FROM Users;";
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection
                    = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                if (resultSet.getInt("userType") == 1) {
                    temp.add(new AdminUser(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getInt("ClassID")));
                } else if (resultSet.getInt("userType") == 2) {
                    temp.add(new TeacherUser(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getInt("ClassID")));
                } else if (resultSet.getInt("userType") == 3) {
                    temp.add(new SimpleUser(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getInt("ClassID")));
                }
            }
            return temp;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            statement.close();		// close statement and resultSet
            connection.close();
            System.out.println("getAllUsers connection closed");
        }
    }

    public User getUser(String email, String password, int userType) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        HashSet<Integer> doneTasks = new HashSet<>();
        String query = "SELECT * FROM Users WHERE email='" + email + "' AND password= '" + password + "';";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection
                    = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (!(resultSet.getInt("userType") == userType)) {
                    break;
                }
                if (!(resultSet.getString("email").equals(email))) {
                    break;
                }
                if (!(resultSet.getString("password").equals(password))) {
                    break;
                }
                doneTasks = getDoneTasks(email);
                if (userType == 1) {
                    return new AdminUser(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getInt("ClassID"), doneTasks);
                } else if (userType == 2) {
                    return new TeacherUser(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getInt("ClassID"), doneTasks);
                } else if (userType == 3) {
                    return new SimpleUser(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getInt("ClassID"), doneTasks);
                }
            }
        } catch (SQLException sqle) {
            System.out.println("Failed to get user");
            sqle.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to get user");
            e.printStackTrace();
            return null;
        } finally {
            statement.close();		// close statement and resultSet
            connection.close();
            System.out.println("getUser connection closed");
        }
        return null;

    }

    private int getMaximumUserClassID() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT MAX(ClassID) FROM Users;";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection
                    = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                return resultSet.getInt("MAX(ClassID)");

            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return 0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        } finally {
            statement.close();		// close statement and resultSet
            connection.close();
            System.out.println("getMaximumClassID connection closed");
        }
        return 0;

    }

    public boolean isEmailExist(String email) {
        HashSet<User> allUsers;
        try {
            allUsers = getAllUsers(-1);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
        for (User u : allUsers) {
            if (u.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean addVoteToUser(String email, int solutionID) throws SQLException {
        PreparedStatement pStatement = null;
        Connection connection = null;
        String queryUsers = "INSERT INTO userVotes VALUES(?, ?);";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection
                    = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

            pStatement = connection.prepareStatement(queryUsers);
            pStatement.setString(1, email);
            pStatement.setInt(2, solutionID);

            int resultSet = pStatement.executeUpdate();

            if (resultSet > 0) {

                System.out.println("Successfuly added Vote");
                return true;
            } else {
                System.out.println("Failed to add Vote");
                return false;

            }
        } catch (SQLException sqle) {
            System.out.println("Failed to add Vote");
            sqle.printStackTrace();
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to add Vote");
            e.printStackTrace();
            return false;
        } finally {
            pStatement.close();		// close statement and resultSet
            connection.close();
            System.out.println("addVoteToUser connection closed");
        }
    }

    public boolean removeVoteFromUser(String email, int solutionID) throws SQLException {
        PreparedStatement pStatement = null;
        Connection connection = null;
        String queryUsers = "DELETE FROM userVotes WHERE Email = ? AND SolutionID = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection
                    = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

            pStatement = connection.prepareStatement(queryUsers);
            pStatement.setString(1, email);
            pStatement.setInt(2, solutionID);

            int resultSet = pStatement.executeUpdate();

            if (resultSet > 0) {

                System.out.println("Successfuly removed Vote from User");
                return true;
            } else {
                System.out.println("Failed to remove Vote from User");
                return false;

            }
        } catch (SQLException sqle) {
            System.out.println("Failed to remove Vote from User");
            sqle.printStackTrace();
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to remove Vote from User");
            e.printStackTrace();
            return false;
        } finally {
            pStatement.close();		// close statement and resultSet
            connection.close();
            System.out.println("removeVoteFromUser connection closed");
        }
    }

    public HashSet<Integer> getUserVotes(String email) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT SolutionID FROM userVotes WHERE email ='" + email + "';";
        HashSet<Integer> resultVotes = new HashSet<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection
                    = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                resultVotes.add(resultSet.getInt("SolutionID"));
            }
            return resultVotes;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            statement.close();		// close statement and resultSet
            connection.close();
            System.out.println("getUservotes connection closed");
        }
    }

    public boolean isAlreadyVoted(int solutionID, String email) throws SQLException {
        HashSet<Integer> userVotes = getUserVotes(email);
        for (Integer v : userVotes) {
            if (v == solutionID) {
                return true;
            }
        }
        return false;

    }

    /**
     ************************************Manage*Solutions*********************************
     */
    public boolean approveSolution(Solution solution, boolean statusToChange) throws SQLException {
        if (!(solution == null)) {
            PreparedStatement pStatement = null;
            Connection connection = null;
            String queryUsers = "Update solutions SET StatusApproved = ? WHERE SolutionID = ?";
            int status = 0;
            if (statusToChange) {
                status = 1;
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection
                        = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

                pStatement = connection.prepareStatement(queryUsers);
                pStatement.setInt(1, status);
                pStatement.setInt(2, solution.getSolutionID());

                int resultSet = pStatement.executeUpdate();

                if (resultSet > 0) {

                    System.out.println("Successfuly updated StatusApproved");
                    return true;
                } else {
                    System.out.println("Failed to  update StatusApproved");
                    return false;

                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                System.out.println("SQLException: " + sqle.getMessage());
                System.out.println("Vendor Error: " + sqle.getErrorCode());
                return false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            } finally {
                pStatement.close();		// close statement and resultSet
                connection.close();
                System.out.println("approveSolution connection closed");
            }

        }
        return false;
    }

    public boolean addSuggestSolution(Solution solution) throws SQLException {
        if (!(solution == null)) {
            PreparedStatement pStatement = null;
            Connection connection = null;
            String queryUsers = "Insert into solutions (SolutionContent,TaskID,UserEmail,ClassID) values (?,?,?,?)";

            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection
                        = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

                pStatement = connection.prepareStatement(queryUsers);
                pStatement.setString(1, solution.getSolutionContent());
                pStatement.setInt(2, solution.getTaskID());
                pStatement.setString(3, solution.getUserEmail());
                pStatement.setInt(4, solution.getClassID());

                int resultSet = pStatement.executeUpdate();

                if (resultSet > 0) {

                    System.out.println("Successfuly inserted Solution");
                    return true;
                } else {
                    System.out.println("Failed to  insert Solution");
                    return false;

                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                System.out.println("SQLException: " + sqle.getMessage());
                System.out.println("Vendor Error: " + sqle.getErrorCode());
                return false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            } finally {
                pStatement.close();		// close statement and resultSet
                connection.close();
                System.out.println("addSuggestSolution connection closed");
            }

        }
        return false;
    }

    public boolean editSuggestSolution(Solution solution) throws SQLException {
        if (!(solution == null)) {
            PreparedStatement pStatement = null;
            Connection connection = null;
            String queryUsers = "Update solutions SET SolutionContent = ? WHERE SolutionID = ?";

            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection
                        = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

                pStatement = connection.prepareStatement(queryUsers);
                pStatement.setString(1, solution.getSolutionContent());
                pStatement.setInt(2, solution.getSolutionID());

                int resultSet = pStatement.executeUpdate();

                if (resultSet > 0) {

                    System.out.println("Successfuly updated Solution");
                    return true;
                } else {
                    System.out.println("Failed to  update Solution");
                    return false;

                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                System.out.println("SQLException: " + sqle.getMessage());
                System.out.println("Vendor Error: " + sqle.getErrorCode());
                return false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            } finally {
                pStatement.close();		// close statement and resultSet
                connection.close();
                System.out.println("editSuggestSolution connection closed");
            }

        }
        return false;
    }

    public boolean deleteSuggestSolution(int solutionID, int classID) throws SQLException {
        PreparedStatement pStatement = null;
        Connection connection = null;
        String queryUsers = "DELETE FROM solutions WHERE ClassID = ? AND SolutionID = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection
                    = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

            pStatement = connection.prepareStatement(queryUsers);
            pStatement.setInt(1, classID);
            pStatement.setInt(2, solutionID);

            int resultSet = pStatement.executeUpdate();

            if (resultSet > 0) {

                System.out.println("Successfuly deleted Solution");
                return true;
            } else {
                System.out.println("Failed to delete Solution");
                return false;

            }
        } catch (SQLException sqle) {
            System.out.println("Failed to delete Solution");
            sqle.printStackTrace();
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to delete Solution");
            e.printStackTrace();
            return false;
        } finally {
            pStatement.close();		// close statement and resultSet
            connection.close();
            System.out.println("deleteSuggestSolution connection closed");
        }
    }

    public HashSet<Solution> getSolutions(int classID) throws SQLException {
        HashSet<Solution> temp = new HashSet<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM solutions WHERE ClassID='" + classID + "';";
        if (classID == -1) {
            query = "SELECT * FROM solutions;";
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection
                    = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int statusInt = resultSet.getInt("StatusApproved");
                boolean StatusApproved;
                if (statusInt == 0) {
                    StatusApproved = false;
                } else {
                    StatusApproved = true;
                }

                temp.add(new Solution(resultSet.getInt("SolutionID"), resultSet.getString("SolutionContent"),
                        resultSet.getInt("TaskID"), resultSet.getInt("Votes"), resultSet.getString("UserEmail"),
                        resultSet.getInt("ClassID"), StatusApproved));
            }
            return temp;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            statement.close();		// close statement and resultSet
            connection.close();
            System.out.println("getSolutions connection closed");
        }
    }

    public boolean voteSolution(int solutionID, int classID, String email) throws SQLException {
        PreparedStatement pStatement = null;
        Connection connection = null;
        String queryUsers = "Update solutions SET Votes = Votes + 1 WHERE ClassID = ? AND SolutionID = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection
                    = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

            pStatement = connection.prepareStatement(queryUsers);
            pStatement.setInt(1, classID);
            pStatement.setInt(2, solutionID);

            int resultSet = pStatement.executeUpdate();

            if (resultSet > 0) {
                if (addVoteToUser(email, solutionID)) {
                    System.out.println("Successfuly added Vote");
                    return true;
                } else {
                    System.out.println("Failed to add Vote");
                    return false;
                }
            } else {
                System.out.println("Failed to add Vote");
                return false;

            }
        } catch (SQLException sqle) {
            System.out.println("Failed to add Vote");
            sqle.printStackTrace();
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to add Vote");
            e.printStackTrace();
            return false;
        } finally {
            pStatement.close();		// close statement and resultSet
            connection.close();
            System.out.println("voteSolution connection closed");
        }
    }

    public boolean unVoteSolution(int solutionID, int classID, String email) throws SQLException {
        PreparedStatement pStatement = null;
        Connection connection = null;
        String queryUsers = "Update solutions SET Votes = Votes - 1 WHERE ClassID = ? AND SolutionID = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection
                    = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

            pStatement = connection.prepareStatement(queryUsers);
            pStatement.setInt(1, classID);
            pStatement.setInt(2, solutionID);

            int resultSet = pStatement.executeUpdate();

            if (resultSet > 0) {
                if (removeVoteFromUser(email, solutionID)) {
                    System.out.println("Successfuly removed Vote");
                    return true;
                } else {
                    System.out.println("Failed to remove Vote");
                    return false;
                }
            } else {
                System.out.println("Failed to remove Vote");
                return false;

            }
        } catch (SQLException sqle) {
            System.out.println("Failed to remove Vote");
            sqle.printStackTrace();
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to remove Vote");
            e.printStackTrace();
            return false;
        } finally {
            pStatement.close();		// close statement and resultSet
            connection.close();
            System.out.println("unVoteSolution connection closed");
        }
    }

    /**
     **********************************Manage*Logins************************************
     */
    public boolean isAdmin(String email, String password) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Users WHERE email='" + email + "' AND password= '" + password + "';";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection
                    = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (resultSet.getInt("userType") == 1) {
                    if (resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
                        return true;
                    }
                }
            }

            return false;

        } catch (SQLException sqle) {
            System.out.println("Internet Connection Error");
            sqle.printStackTrace();
            System.exit(0);
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Internet Connection Error");
            e.printStackTrace();
            return false;
        } finally {
            statement.close();		// close statement and resultSet
            connection.close();
            System.out.println("isAdmin connection closed");
        }

        /*  for(AdminUser admin : admins)
            if (admin != null) {
                if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
                    return admin.getEmail();
            }
        }*/
    }

    public boolean isTeacher(String email, String password) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Users WHERE email='" + email + "' AND password= '" + password + "';";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection
                    = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (resultSet.getInt("userType") == 2) {
                    if (resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
                        return true;
                    }
                }
            }

            return false;

        } catch (SQLException sqle) {
            System.out.println("Internet Connection Error");
            sqle.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Internet Connection Error");
            e.printStackTrace();
            return false;
        } finally {
            statement.close();		// close statement and resultSet
            connection.close();
            System.out.println("isTeacher connection closed");
        }

    }

    public boolean isSimpleUser(String email, String password) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Users WHERE email='" + email + "' AND password= '" + password + "';";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection
                    = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (resultSet.getInt("userType") == 3) {
                    if (resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
                        return true;
                    }
                }
            }

            return false;

        } catch (SQLException sqle) {
            System.out.println("Internet Connection Error");
            sqle.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Internet Connection Error");
            e.printStackTrace();
            return false;
        } finally {
            statement.close();		// close statement and resultSet
            connection.close();
            System.out.println("isSimpleUser connection closed");
        }
    }

}
