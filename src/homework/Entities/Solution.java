package homework.Entities;

import resources.LocalizationUtil;

public class Solution {

    private int solutionID;
    private String solutionContent;
    private int TaskID;
    private int votes;
    private String UserEmail;
    private int ClassID;
    private boolean statusApproved;

    public Solution() {
    }

    public Solution(int solutionID, String solutionContent, int TaskID, int votes,
            String UserEmail, int ClassID, boolean statusApproved) {

        setSolutionID(solutionID);
        setSolutionContent(solutionContent);
        setTaskID(TaskID);
        setVotes(votes);
        setUserEmail(UserEmail);
        setClassID(ClassID);
        setStatusApproved(statusApproved);

    }

    public Solution(String solutionContent, int TaskID,
            String UserEmail, int ClassID) {

        setSolutionContent(solutionContent);
        setTaskID(TaskID);
        setUserEmail(UserEmail);
        setClassID(ClassID);
    }

    public int getSolutionID() {
        return solutionID;
    }

    public void setSolutionID(int solutionID) {
        this.solutionID = solutionID;
    }

    public String getSolutionContent() {
        return solutionContent;
    }

    public void setSolutionContent(String solutionContent) {
        this.solutionContent = solutionContent;
    }

    public int getTaskID() {
        return TaskID;
    }

    public void setTaskID(int TaskID) {
        this.TaskID = TaskID;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String UserEmail) {
        this.UserEmail = UserEmail;
    }

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int ClassID) {
        this.ClassID = ClassID;
    }

    public boolean isApproved() {
        return statusApproved;
    }

    public void setStatusApproved(boolean statusApproved) {
        this.statusApproved = statusApproved;
    }

    @Override
    public String toString() {
        return LocalizationUtil.localizedResourceBundle.getString("lblSolutionBy")+ getUserEmail() + ": *** " +
                getSolutionContent() + " *** " +LocalizationUtil.localizedResourceBundle.getString("lblSolutionVotes") + getVotes();
    }
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Solution))
            return false;
        if(getSolutionID()==((Solution)obj).getSolutionID())
            return true;
        return false;
    }
    
    @Override
    public int hashCode(){
        return getSolutionID();
    }

}
