package homework.Forms;

import homework.Entities.Task;
import homework.Users.AdminUser;
import homework.Users.TeacherUser;
import homework.Users.User;
import homework.Utils.ToastMessage;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.swing.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFormattedTextField.AbstractFormatter;
import org.jdesktop.xswingx.PromptSupport;
import resources.LocalizationUtil;

public class EditTaskPopUp extends JFrame implements ActionListener {

    private final ImageIcon imgIcon = new ImageIcon(getClass().getResource("/Images/icon.gif"));
    private AdminUser currentAdminUser = new AdminUser();
    private TeacherUser currentTeacherUser = new TeacherUser();
    private Task task = new Task();
    private int userType = 0;
    private JButton btnCancel;
    private JButton btnOk;
    private JTextField txtTaskName = new JTextField(30);
    private UtilDateModel model;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuMenu = new JMenu(LocalizationUtil.localizedResourceBundle.getString("menuMenu"));
    private JMenuItem btnMenuExit = new JMenuItem(LocalizationUtil.localizedResourceBundle.getString("btnExit"));

    public EditTaskPopUp(Task task, User user) {
        //set screen parameters
        super(LocalizationUtil.localizedResourceBundle.getString("editTaskMenuKey"));
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        setIconImage(imgIcon.getImage());
        setResizable(false);
        buildMenu();

        if (task != null) {
            this.task = task;
        }

        //retrieve permisions
        if (user == null) {
            return;
        }
        if (user instanceof AdminUser) {
            currentAdminUser = currentAdminUser.getUser(user.getEmail(), user.getPassword());
            userType = currentAdminUser.getUserType();
        } else if (user instanceof TeacherUser) {
            currentTeacherUser = currentTeacherUser.getUser(user.getEmail(), user.getPassword());
            userType = currentTeacherUser.getUserType();
        } else {
            ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("errErrorOccurred"), 3000);
            toastMessage.setVisible(true);
            return;
        }

        //set date picker component
        model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        
        //set components
        btnOk = new JButton(LocalizationUtil.localizedResourceBundle.getString("okKey"));
        btnCancel = new JButton(LocalizationUtil.localizedResourceBundle.getString("btnCancel"));
        txtTaskName.setText(task.getTaskName());
        model.setValue(task.getDeadline());
        add(txtTaskName);
        add(datePicker);
        add(btnOk);
        add(btnCancel);

        //set watermarks
        PromptSupport.setPrompt(LocalizationUtil.localizedResourceBundle.getString("lblTaskName"), txtTaskName);
        PromptSupport.setPrompt(LocalizationUtil.localizedResourceBundle.getString("lblTaskDeadline"), datePicker.getJFormattedTextField());

        //set Listeners
        btnOk.addActionListener(this);
        btnCancel.addActionListener(this);
        btnMenuExit.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnOk) {
            System.out.println(txtTaskName.getText());
            if (txtTaskName.getText().isEmpty()) {
                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("errEnterTaskName"), 3000);
                toastMessage.setVisible(true);
                txtTaskName.requestFocus();
                return;
            }
            if (datePicker.getJFormattedTextField().getText().isEmpty()) {
                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("errEnterTaskDeadline"), 3000);
                toastMessage.setVisible(true);
                datePicker.requestFocus();
                return;
            }
            task = new Task(txtTaskName.getText(), model.getValue(), task.getTaskID(), task.getClassID(), task.getStatus());
            if (userType == 1 && currentAdminUser.editTask(task)) {
                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("updateTaskSucc"), 3000);
                toastMessage.setVisible(true);
                AdminUserMenu adminUserMenu = new AdminUserMenu(currentAdminUser.getEmail(), currentAdminUser.getPassword());
                this.dispose();

            } else if (userType == 2 && currentTeacherUser.editTask(task)) {
                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("updateTaskSucc"), 3000);
                toastMessage.setVisible(true);
                TeacherUserMenu teacherUserMenu = new TeacherUserMenu(currentTeacherUser.getEmail(), currentTeacherUser.getPassword());
                this.dispose();
            } else {
                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("updateTaskFail"), 3000);
                toastMessage.setVisible(true);
            }

        } else if (e.getSource() == btnCancel) {
            if (userType == 1) {
                AdminUserMenu adminUserMenu = new AdminUserMenu(currentAdminUser.getEmail(), currentAdminUser.getPassword());
                this.dispose();

            } else if (userType == 2) {
                TeacherUserMenu teacherUserMenu = new TeacherUserMenu(currentTeacherUser.getEmail(), currentTeacherUser.getPassword());
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

    public class DateLabelFormatter extends AbstractFormatter {

        private String datePattern = "dd-MM-yyyy";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }

    }
}
