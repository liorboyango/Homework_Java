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
import javax.swing.*;
import org.jdesktop.xswingx.PromptSupport;
import resources.LocalizationUtil;

public class EditSolutionPopUp extends JFrame implements ActionListener {

    private final ImageIcon imgIcon = new ImageIcon(getClass().getResource("/Images/icon.gif"));
    private AdminUser currentAdminUser = new AdminUser();
    private TeacherUser currentTeacherUser = new TeacherUser();
    private SimpleUser currentSimpleUser = new SimpleUser();
    private Solution solution = new Solution();
    private int userType = 0;
    private int classID = 0;
    private String userEmail = "";
    private JButton btnCancel;
    private JButton btnOk;
    private JButton btnDelete;
    private JTextField txtSolutionContent = new JTextField(30);
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuMenu = new JMenu(LocalizationUtil.localizedResourceBundle.getString("menuMenu"));
    private JMenuItem btnMenuExit = new JMenuItem(LocalizationUtil.localizedResourceBundle.getString("btnExit"));

    public EditSolutionPopUp(Solution solution, User user) {
        //set screen parameters
        super(LocalizationUtil.localizedResourceBundle.getString("editSolutionMenuKey"));
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
            classID = currentAdminUser.getClassID();
        } else if (user instanceof TeacherUser) {
            currentTeacherUser = currentTeacherUser.getUser(user.getEmail(), user.getPassword());
            userType = currentTeacherUser.getUserType();
            userEmail = currentTeacherUser.getEmail();
            classID = currentTeacherUser.getClassID();
        } else if (user instanceof SimpleUser) {
            currentSimpleUser = currentSimpleUser.getUser(user.getEmail(), user.getPassword());
            userType = currentSimpleUser.getUserType();
            userEmail = currentSimpleUser.getEmail();
            classID = currentSimpleUser.getClassID();
        } else {
            ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("errErrorOccurred"), 3000);
            toastMessage.setVisible(true);
            return;
        }
        this.solution = solution;

        //set components
        btnOk = new JButton(LocalizationUtil.localizedResourceBundle.getString("okKey"));
        btnDelete = new JButton(LocalizationUtil.localizedResourceBundle.getString("btnDelete"));
        btnCancel = new JButton(LocalizationUtil.localizedResourceBundle.getString("btnCancel"));
        txtSolutionContent.setText(solution.getSolutionContent());
        add(txtSolutionContent);
        add(btnOk);
        add(btnDelete);
        add(btnCancel);

        //set watermarks
        PromptSupport.setPrompt(LocalizationUtil.localizedResourceBundle.getString("enterSolution"), txtSolutionContent);

        //set Listeners
        btnOk.addActionListener(this);
        btnCancel.addActionListener(this);
        btnDelete.addActionListener(this);
        btnMenuExit.addActionListener(this);

        txtSolutionContent.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        javax.swing.JFrame context = this;
        Thread t = new Thread() {
            public void run() {
                if (e.getSource() == btnOk) {
                    setComponentsAvailable(false);
                    System.out.println(txtSolutionContent.getText());
                    if (txtSolutionContent.getText().isEmpty()) {
                        ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("enterSolution"), 3000);
                        toastMessage.setVisible(true);
                        txtSolutionContent.requestFocus();
                        setComponentsAvailable(true);
                        return;
                    }

                    Solution tempSolution = new Solution(solution.getSolutionID(), txtSolutionContent.getText(), solution.getTaskID(), solution.getVotes(), userEmail, classID, solution.isApproved());
                    if (userType == 1 && currentAdminUser.editSuggestSolution(tempSolution)) {
                        ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("editSolutionSucc"), 3000);
                        toastMessage.setVisible(true);
                        AdminUserMenu adminUserMenu = new AdminUserMenu(currentAdminUser.getEmail(), currentAdminUser.getPassword());
                        context.dispose();

                    } else if (userType == 2 && currentTeacherUser.editSuggestSolution(tempSolution)) {
                        ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("editSolutionSucc"), 3000);
                        toastMessage.setVisible(true);
                        TeacherUserMenu teacherUserMenu = new TeacherUserMenu(currentTeacherUser.getEmail(), currentTeacherUser.getPassword());
                        context.dispose();
                    } else if (userType == 3 && currentSimpleUser.editSuggestSolution(tempSolution)) {
                        ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("editSolutionSucc"), 3000);
                        toastMessage.setVisible(true);
                        SimpleUserMenu simpleUserMenu = new SimpleUserMenu(currentSimpleUser.getEmail(), currentSimpleUser.getPassword());
                        context.dispose();
                    } else {
                        ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("editSolutionFail"), 3000);
                        toastMessage.setVisible(true);
                        setComponentsAvailable(true);
                    }

                } else if (e.getSource() == btnCancel) {
                    setComponentsAvailable(false);
                    if (userType == 1) {
                        AdminUserMenu adminUserMenu = new AdminUserMenu(currentAdminUser.getEmail(), currentAdminUser.getPassword());
                        context.dispose();

                    } else if (userType == 2) {
                        TeacherUserMenu teacherUserMenu = new TeacherUserMenu(currentTeacherUser.getEmail(), currentTeacherUser.getPassword());
                        context.dispose();
                    } else if (userType == 3) {
                        SimpleUserMenu simpleUserMenu = new SimpleUserMenu(currentSimpleUser.getEmail(), currentSimpleUser.getPassword());
                        context.dispose();
                    } else {
                        context.dispose();
                    }

                } else if (e.getSource() == btnDelete) {
                    setComponentsAvailable(false);
                    switch (userType) {
                        case 1:
                            if (currentAdminUser.deleteSuggestSolution(solution.getSolutionID())) {
                                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("deleteSolutionSucc"), 3000);
                                toastMessage.setVisible(true);
                                AdminUserMenu adminUserMenu = new AdminUserMenu(currentAdminUser.getEmail(), currentAdminUser.getPassword());
                                context.dispose();
                            } else {
                                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("deleteSolutionFail"), 3000);
                                toastMessage.setVisible(true);
                                setComponentsAvailable(true);
                            }
                            break;
                        case 2:
                            if (currentTeacherUser.deleteSuggestSolution(solution.getSolutionID())) {
                                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("deleteSolutionSucc"), 3000);
                                toastMessage.setVisible(true);
                                TeacherUserMenu teacherUserMenu = new TeacherUserMenu(currentTeacherUser.getEmail(), currentTeacherUser.getPassword());
                                context.dispose();
                            } else {
                                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("deleteSolutionFail"), 3000);
                                toastMessage.setVisible(true);
                                setComponentsAvailable(true);
                            }
                            break;
                        case 3:
                            if (currentSimpleUser.deleteSuggestSolution(solution.getSolutionID())) {
                                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("deleteSolutionSucc"), 3000);
                                toastMessage.setVisible(true);
                                SimpleUserMenu simpleUserMenu = new SimpleUserMenu(currentSimpleUser.getEmail(), currentSimpleUser.getPassword());
                                context.dispose();
                            } else {
                                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("deleteSolutionFail"), 3000);
                                toastMessage.setVisible(true);
                                setComponentsAvailable(true);
                            }
                            break;
                        default:
                            setComponentsAvailable(true);
                            break;
                    }

                } else if (e.getSource() == btnMenuExit) {
                    System.exit(0);
                }
            }
        };
        t.start();
    }

    private void buildMenu() {
        menuMenu.add(btnMenuExit);
        menuBar.add(menuMenu);
        setJMenuBar(menuBar);
    }

    private void setComponentsAvailable(boolean available) {
        btnOk.setEnabled(available);
        btnCancel.setEnabled(available);
    }

}
