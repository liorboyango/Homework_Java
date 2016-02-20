package homework.Forms;

import homework.Entities.Solution;
import homework.Users.AdminUser;
import homework.Users.SimpleUser;
import homework.Users.TeacherUser;
import homework.Users.User;
import homework.Utils.ToastMessage;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.*;

import org.jdesktop.xswingx.PromptSupport;
import resources.LocalizationUtil;

public class AddNewSolutionPopUp extends JFrame implements ActionListener {

    private final ImageIcon imgIcon = new ImageIcon(getClass().getResource("/Images/icon.gif"));
    private AdminUser currentAdminUser = new AdminUser();
    private TeacherUser currentTeacherUser = new TeacherUser();
    private SimpleUser currentSimpleUser = new SimpleUser();
    private int userType = 0;
    private int taskID = 0;
    private String userEmail = "";
    private JButton btnCancel;
    private JButton btnOk;
    private JTextField txtSolutionContent = new JTextField(30);
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuMenu = new JMenu(LocalizationUtil.localizedResourceBundle.getString("menuMenu"));
    private JMenuItem btnMenuExit = new JMenuItem(LocalizationUtil.localizedResourceBundle.getString("btnExit"));

    public AddNewSolutionPopUp(User user, int taskID) {
        //set screen parameters
        super(LocalizationUtil.localizedResourceBundle.getString("addSolutionMenuKey"));
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        setIconImage(imgIcon.getImage());
        setResizable(false);
        buildMenu();

        //retrieve permisions
        if (user == null) {
            return;
        }
        if (user instanceof AdminUser) {
            currentAdminUser = currentAdminUser.getUser(user.getEmail(), user.getPassword());
            userType = currentAdminUser.getUserType();
            userEmail = currentAdminUser.getEmail();
        } else if (user instanceof TeacherUser) {
            currentTeacherUser = currentTeacherUser.getUser(user.getEmail(), user.getPassword());
            userType = currentTeacherUser.getUserType();
            userEmail = currentTeacherUser.getEmail();
        } else if (user instanceof SimpleUser) {
            currentSimpleUser = currentSimpleUser.getUser(user.getEmail(), user.getPassword());
            userType = currentSimpleUser.getUserType();
            userEmail = currentSimpleUser.getEmail();
        } else {
            ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("errErrorOccurred"), 3000);
            toastMessage.setVisible(true);
            return;
        }
        this.taskID = taskID;

        //set components
        btnOk = new JButton(LocalizationUtil.localizedResourceBundle.getString("okKey"));
        btnCancel = new JButton(LocalizationUtil.localizedResourceBundle.getString("btnCancel"));
        add(txtSolutionContent);
        add(btnOk);
        add(btnCancel);

        //set watermarks
        PromptSupport.setPrompt(LocalizationUtil.localizedResourceBundle.getString("enterSolution"), txtSolutionContent);

        //set Listeners
        btnOk.addActionListener(this);
        btnCancel.addActionListener(this);
        btnMenuExit.addActionListener(this);

        txtSolutionContent.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnOk) {
            System.out.println(txtSolutionContent.getText());
            if (txtSolutionContent.getText().isEmpty()) {
                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("enterSolution"), 3000);
                toastMessage.setVisible(true);
                txtSolutionContent.requestFocus();
                return;
            }
            Solution solution;
            if (userType == 1) {
                solution = new Solution(txtSolutionContent.getText(), taskID, userEmail, currentAdminUser.getClassID());
            } else if (userType == 2) {
                solution = new Solution(txtSolutionContent.getText(), taskID, userEmail, currentTeacherUser.getClassID());
            } else {
                solution = new Solution(txtSolutionContent.getText(), taskID, userEmail, currentSimpleUser.getClassID());
            }
            if (userType == 1 && currentAdminUser.addSuggestSolution(solution)) {
                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("addSolutionSucc"), 3000);
                toastMessage.setVisible(true);
                AdminUserMenu adminUserMenu = new AdminUserMenu(currentAdminUser.getEmail(), currentAdminUser.getPassword());
                this.dispose();

            } else if (userType == 2 && currentTeacherUser.addSuggestSolution(solution)) {
                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("addSolutionSucc"), 3000);
                toastMessage.setVisible(true);
                TeacherUserMenu teacherUserMenu = new TeacherUserMenu(currentTeacherUser.getEmail(), currentTeacherUser.getPassword());
                this.dispose();
            } else if (userType == 3 && currentSimpleUser.addSuggestSolution(solution)) {
                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("addSolutionSucc"), 3000);
                toastMessage.setVisible(true);
                SimpleUserMenu simpleUserMenu = new SimpleUserMenu(currentSimpleUser.getEmail(), currentSimpleUser.getPassword());
                this.dispose();
            } else {
                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("addSolutionFail"), 3000);
                toastMessage.setVisible(true);
            }

        } else if (e.getSource() == btnCancel) {
            if (userType == 1) {
                AdminUserMenu adminUserMenu = new AdminUserMenu(currentAdminUser.getEmail(), currentAdminUser.getPassword());
                this.dispose();

            } else if (userType == 2) {
                TeacherUserMenu teacherUserMenu = new TeacherUserMenu(currentTeacherUser.getEmail(), currentTeacherUser.getPassword());
                this.dispose();
            } else if (userType == 3) {
                SimpleUserMenu simpleUserMenu = new SimpleUserMenu(currentSimpleUser.getEmail(), currentSimpleUser.getPassword());
                this.dispose();
            } else {
                this.dispose();
            }

        } else if (e.getSource() == btnMenuExit) {
            System.exit(0);
        }
    }

    private void buildMenu() {
        menuMenu.add(btnMenuExit);
        menuBar.add(menuMenu);
        setJMenuBar(menuBar);
    }

}
