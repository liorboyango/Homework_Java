package homework.Users;

import homework.Entities.Solution;
import homework.Entities.Task;
import homework.Utils.DataBase;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import homework.Interfaces.IManageTasks;

public class TeacherUser extends User implements IManageTasks{

    public TeacherUser() {
        super();
    }

    public TeacherUser(String username, String password, String email) {
        this(username, password, email, 0);
    }

    public TeacherUser(String username, String password, String email, int classID) {
        this(username, password, email, classID, new HashSet<Integer>());
    }

    public TeacherUser(String username, String password, String email, int classID, HashSet<Integer> doneTasks) {
        super(username, password, email, classID, 2, doneTasks);
    }

    @Override
    public String toString() {
        return super.toString() + "  TEACHER";
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
     ************************************Manage*Solutions*********************************
     */
    public boolean approveSolution(Solution solution, boolean statusToChange) {
        try {
            return DataBase.getInstance().approveSolution(solution, statusToChange);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    /**
     * *********************Manage Users***********************
     */
    public HashSet<User> getAllUsers(int classID) {
        try {
            return DataBase.getInstance().getAllUsers(classID);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }
    }

    public TeacherUser getUser(String email, String password) {
        try {
            if (DataBase.getInstance().isTeacher(email, password)) {
                TeacherUser tu = ((TeacherUser) DataBase.getInstance().getUser(email, password, 2));
                tu.fixDoneTasksList();
                tu.fixSolutionsVotes();
                return tu;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }
        return null;
    }
}
