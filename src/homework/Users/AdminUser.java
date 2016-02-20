package homework.Users;

import homework.Interfaces.IManageUsers;
import homework.Utils.DataBase;
import homework.Entities.Task;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import homework.Interfaces.IManageTasks;

public class AdminUser extends User implements IManageTasks, IManageUsers{

    public AdminUser() {
        super();
    }

    public AdminUser(String username, String password, String email) {
        this(username, password, email, 1);
    }

    public AdminUser(String username, String password, String email, int classID) {
        this(username, password, email, classID, new HashSet<Integer>());
    }

    public AdminUser(String username, String password, String email, int classID, HashSet<Integer> doneTasks) {
        super(username, password, email, classID, 1, doneTasks);
    }

    @Override
    public String toString() {
        return super.toString() + "  ADMIN";
    }

    /**
     * *********************Manage Tasks***********************
     */
    public boolean addNewTask(String taskName, Date dealine) {
        try {
            if (DataBase.getInstance().addNewTask(taskName, dealine, getClassID())) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    public boolean editTask(Task task) {
        try {
            return DataBase.getInstance().editTask(task.getTaskID(), task.getTaskName(), task.getDeadline(), getClassID());
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }

    }

    public boolean deleteTask(int taskID) {
        try {
            if (DataBase.getInstance().deleteTask(taskID, getClassID())) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    public boolean isTaskExist(int taskID) {
        return DataBase.getInstance().isTaskExist(taskID, getClassID());
    }

    /**
     * *********************Manage Users***********************
     */
    public boolean addUserToGroup(User user) {
        try {
            if (DataBase.getInstance().addUserToGroup(user, getClassID())) {
                return true;
            }
            return false;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    public boolean removeUserFromGroup(User user) {
        try {
            if (DataBase.getInstance().removeUserFromGroup(user)) {
                return true;
            }
            return false;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    public HashSet<User> getAllUsers(int classID) {
        try {
            return DataBase.getInstance().getAllUsers(classID);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }
    }

    public AdminUser getUser(String email, String password) {
        try {
            if (DataBase.getInstance().isAdmin(email, password)) {
                AdminUser au = ((AdminUser) DataBase.getInstance().getUser(email, password, 1));
                au.fixDoneTasksList();
                au.fixSolutionsVotes();
                return au;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }
        return null;
    }

}
