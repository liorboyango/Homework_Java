package homework.Interfaces;

import homework.Entities.Task;
import java.util.Date;

public interface IManageTasks {

    public boolean addNewTask(String taskName, Date dealine);

    public boolean editTask(Task task);

    public boolean deleteTask(int taskID);
}
