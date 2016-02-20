package homework.Entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
//import java.util.Date;

public class Task {
    
    private int taskID;
    private int classID;
    private String taskName;
    private Date deadline;
    private boolean status = false;
    private HashSet<Solution> solutions = new HashSet<>();
    
    public Task(){}
    
    public Task(String taskName, Date deadline) {
        this(taskName, deadline, 0);
    }

    public Task(String taskName, Date deadline, int taskID) {
        this(taskName, deadline, taskID, 0);
    }
    
    public Task(String taskName, Date deadline, int taskID, int classID) {
        this(taskName, deadline, taskID, classID, false);
    }

    public Task(String taskName, Date deadline, int taskID, int classID, boolean status) {
        setTaskName(taskName);
        setDeadline(deadline);
        setTaskID(taskID);
        setStatus(status);
        setClassID(classID);

    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date Deadline) {
        this.deadline = Deadline;
    }

    public HashSet<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(HashSet<Solution> solutions) {
        HashSet<Solution> temp = new HashSet<>();
        for(Solution s : solutions)
            temp.add(s);
        this.solutions = temp;
    }
    
    public int getSolutionID(String userEmail){
        for(Solution s : solutions){
            if(s.getUserEmail().equals(userEmail))
                return s.getSolutionID();
        }
        return -1;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(getDeadline());
        return "ID: " + getTaskID() + " Task Name: " + getTaskName() + " Deadline: " + date
                + "       Status: " + getStatus();
    }

    @Override
    public boolean equals(Object obj) {
        if (getTaskID()==(((Task) obj).getTaskID())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
