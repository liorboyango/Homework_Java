
package ServerUtils;

import homework.Users.User;
import java.util.Date;


public class ChatMessage {
    
    private String message;
    private Date time;
    private User user;
    
    public ChatMessage(){
        
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public String toString(){
        return getUser().getUsername()+": "+getMessage()+"       at: "+getTime().toString();
    }
    
}
