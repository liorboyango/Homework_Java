package homework.Users;

import homework.Utils.DataBase;
import java.sql.SQLException;
import java.util.HashSet;

public class SimpleUser extends User {

    public SimpleUser() {
        super();
    }

    public SimpleUser(String username, String password, String email) {
        this(username, password, email, 0);
    }

    public SimpleUser(String username, String password, String email, int classID) {
        this(username, password, email, classID, new HashSet<Integer>());
    }

    public SimpleUser(String username, String password, String email, int classID, HashSet<Integer> doneTasks) {
        super(username, password, email, classID, 3, doneTasks);
    }

    /**
     * *********************Manage Users***********************
     */
    public SimpleUser getUser(String email, String password) {
        try {
            if (DataBase.getInstance().isSimpleUser(email, password)) {
                SimpleUser su = ((SimpleUser) DataBase.getInstance().getUser(email, password, 3));
                su.fixDoneTasksList();
                su.fixSolutionsVotes();
                return su;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
        }
        return null;
    }

}
