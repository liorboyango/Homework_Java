package homework.Users;

import homework.Entities.Solution;
import homework.Entities.Task;
import homework.Utils.DataBase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class User {

    private String username;
    private String password;
    private String email;
    private int classId;
    private int userType = 3;
    private HashSet<Integer> doneTasks = new HashSet<>();
    private HashSet<Task> localTasks = new HashSet<Task>();

    public User() {
    }

    public User(String username, String password, String email) {
        this(username, password, email, 0, 3);
    }

    public User(String username, String password, String email, int classID, int userType) {
        this(username, password, email, classID, userType, new HashSet<Integer>());
    }

    public User(String username, String password, String email, int classID, int userType, HashSet<Integer> doneTasks) {

        setUsername(username);

        setPassword(password);

        setEmail(email);

        setClassID(classID);

        setUserType(userType);

        setDoneTasks(doneTasks);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getClassID() {
        return classId;
    }

    public void setClassID(int classId) {
        this.classId = classId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "UserName: " + getUsername() + " Email: " + getEmail();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            if (getEmail().equals(((User) obj).getEmail())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    /**
     ************************************Manage*Tasks*********************************
     */
    public HashSet<Task> getLocalTasks() {
        return localTasks;
    }

    public void fillTasks() {
        try {
            localTasks = DataBase.getInstance().getAllTasks(getClassID());
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return;
        }
        for (Task t : localTasks) {
            for (Integer id : getLocalDoneTasks()) {
                if (t.getTaskID() == id) {
                    t.setStatus(true);
                }
            }
        }
    }

    public HashSet<Integer> getLocalDoneTasks() {
        return doneTasks;
    }

    public void setDoneTasks(HashSet<Integer> doneTasks) {
        this.doneTasks = doneTasks;
    }

    public HashSet<Integer> getDBDoneTasks() {
        try {
            return DataBase.getInstance().getDoneTasks(getEmail());
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }
    }

    public void fixDoneTasksList() {
        HashSet<Integer> userTasks = new HashSet<>();
        try {
            userTasks = DataBase.getInstance().getDoneTasks(getEmail());
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        fillTasks();
        Task t = new Task();
        for (Integer i : userTasks) {        //go through user's doneTasks
            t.setTaskID(i);
            if (!getLocalTasks().contains(t)) {    //check if user's doneTasks are irrelevent since not found in local tasks
                markTask(t.getTaskID(), false);
                getLocalDoneTasks().remove(t.getTaskID());          // remove the irrelevent doneTask
            }
        }
    }

    public boolean markTask(int taskID, boolean markAs) {
        try {
            if (markAs) {
                return DataBase.getInstance().addUserDoneTask(this, taskID);
            } else {
                return DataBase.getInstance().removeUserDoneTask(this, taskID);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    /**
     ************************************Manage*Solutions*********************************
     */
    public HashSet<Solution> getSolutions() {
        try {
            return DataBase.getInstance().getSolutions(getClassID());
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }
    }

    public boolean addSuggestSolution(Solution solution) {
        try {

            return DataBase.getInstance().addSuggestSolution(solution);

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    public boolean editSuggestSolution(Solution solution) {
        try {
            return DataBase.getInstance().editSuggestSolution(solution);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    public boolean deleteSuggestSolution(int solutionID) {

        try {
            return DataBase.getInstance().deleteSuggestSolution(solutionID, getClassID());
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }

    }

    public boolean voteSolution(int solutionID) {
        try {
            return DataBase.getInstance().voteSolution(solutionID, getClassID(), getEmail());
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    public boolean unVoteSolution(int solutionID) {
        try {
            return DataBase.getInstance().unVoteSolution(solutionID, getClassID(), getEmail());
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    public boolean removeVoteFromUser(int solutionID) {
        try {
            return DataBase.getInstance().removeVoteFromUser(getEmail(), solutionID);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    public boolean isAlreadyVoted(int solutionID) {
        try {
            return DataBase.getInstance().isAlreadyVoted(solutionID, getEmail());
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }

    }

    public void fixSolutionsVotes() {
        HashSet<Integer> userVotes = new HashSet<>();
        try {
            userVotes = DataBase.getInstance().getUserVotes(getEmail());
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        for (Integer i : userVotes) {
            Solution s = new Solution();
            s.setSolutionID(i);
            if (!getSolutions().contains(s)) {
                removeVoteFromUser(i);
            }
        }
    }

    /**
     ************************************Manage*Users*********************************
     */
    protected abstract User getUser(String email, String password); //each user type overrides this method differently

    public void createUser(User user) {
        try {
            DataBase.getInstance().createUser(user);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }   //create the user in DB

    public boolean editUser(String email, String username, String password, int userType) {
        try {
            if (DataBase.getInstance().editUser(email, username, password, userType)) {
                setUsername(username);
                setPassword(password);
                setUserType(userType);
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

}
