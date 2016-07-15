package homework.Forms;

import homework.Entities.Task;
import homework.Users.AdminUser;
import homework.Users.TeacherUser;
import homework.Users.User;
import homework.Utils.ToastMessage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import javax.swing.ImageIcon;
import org.jdesktop.xswingx.PromptSupport;
import resources.LocalizationUtil;

public class EditTaskPopUp extends javax.swing.JFrame {

    private final ImageIcon imgIcon = new ImageIcon(getClass().getResource("/Images/icon.gif"));
    private AdminUser currentAdminUser = new AdminUser();
    private TeacherUser currentTeacherUser = new TeacherUser();
    private Task task = new Task();
    private int userType = 0;

    public EditTaskPopUp(Task task, User user) {
        initComponents();
        setComponentsAvailable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setIconImage(imgIcon.getImage());

        if (task != null) {
            this.task = task;
        }

        //retrieve permisions
        if (user == null) {
            setComponentsAvailable(true);
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
            setComponentsAvailable(true);
            return;
        }

        //set watermarks
        PromptSupport.setPrompt(LocalizationUtil.localizedResourceBundle.getString("lblTaskName"), txtTaskName);

        updateCaptions();

        //set previous task details
        txtTaskName.setText(task.getTaskName());
        datePicker.setDate(task.getDeadline());

        datePicker.getDateEditor().setEnabled(false);
        datePicker.getCalendarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //set minimum date for today - only after the calendar button is clicked
                //so that if user only wants to change task name, old date can remain even if deadline expired
                datePicker.setMinSelectableDate(Calendar.getInstance().getTime());
            }
        });
        setComponentsAvailable(true);
        btnOk.setEnabled(false);
        datePicker.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent event) {
                if (datePicker.getDate().equals(task.getDeadline()) && task.getTaskName().equals(txtTaskName.getText())) {
                    btnOk.setEnabled(false);
                } else {
                    btnOk.setEnabled(true);
                }

            }
        });

        txtTaskName.requestFocus();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtTaskName = new javax.swing.JTextField();
        datePicker = new com.toedter.calendar.JDateChooser();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        EditTaskPBar = new javax.swing.JProgressBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuMenu = new javax.swing.JMenu();
        btnMenuExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtTaskName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTaskNameKeyReleased(evt);
            }
        });

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("resources/uimessages_iw"); // NOI18N
        datePicker.setToolTipText(bundle.getString("errEnterTaskDeadline")); // NOI18N
        datePicker.setDateFormatString("dd/MM/yyyy");

        btnOk.setText(bundle.getString("okKey")); // NOI18N
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnCancel.setText(bundle.getString("btnCancel")); // NOI18N
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        EditTaskPBar.setIndeterminate(true);
        EditTaskPBar.setString("");

        menuMenu.setText(bundle.getString("menuMenu")); // NOI18N

        btnMenuExit.setText(bundle.getString("btnExit")); // NOI18N
        btnMenuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuExitActionPerformed(evt);
            }
        });
        menuMenu.add(btnMenuExit);

        jMenuBar1.add(menuMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(txtTaskName, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(btnOk)
                        .addGap(61, 61, 61)
                        .addComponent(btnCancel)))
                .addContainerGap(118, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(EditTaskPBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(223, 223, 223))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(txtTaskName, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(btnCancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EditTaskPBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        javax.swing.JFrame context = this;
        Thread t = new Thread() {
            public void run() {
                setComponentsAvailable(false);
                if (txtTaskName.getText().isEmpty()) {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("errEnterTaskName"), 3000);
                    toastMessage.setVisible(true);
                    txtTaskName.requestFocus();
                    setComponentsAvailable(true);
                    return;
                }
                if (datePicker.getDate().toString().isEmpty()) {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("errEnterTaskDeadline"), 3000);
                    toastMessage.setVisible(true);
                    datePicker.requestFocus();
                    setComponentsAvailable(true);
                    return;
                }
                task = new Task(txtTaskName.getText(), datePicker.getDate(), task.getTaskID(), task.getClassID(), task.getStatus());
                if (userType == 1 && currentAdminUser.editTask(task)) {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("updateTaskSucc"), 3000);
                    toastMessage.setVisible(true);
                    AdminUserMenu adminUserMenu = new AdminUserMenu(currentAdminUser.getEmail(), currentAdminUser.getPassword());
                    context.dispose();

                } else if (userType == 2 && currentTeacherUser.editTask(task)) {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("updateTaskSucc"), 3000);
                    toastMessage.setVisible(true);
                    TeacherUserMenu teacherUserMenu = new TeacherUserMenu(currentTeacherUser.getEmail(), currentTeacherUser.getPassword());
                    context.dispose();
                } else {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("updateTaskFail"), 3000);
                    toastMessage.setVisible(true);
                    setComponentsAvailable(true);
                }
            }
        };
        t.start();
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        javax.swing.JFrame context = this;
        Thread t = new Thread() {
            public void run() {
                setComponentsAvailable(false);
                if (userType == 1) {
                    AdminUserMenu adminUserMenu = new AdminUserMenu(currentAdminUser.getEmail(), currentAdminUser.getPassword());
                    context.dispose();

                } else if (userType == 2) {
                    TeacherUserMenu teacherUserMenu = new TeacherUserMenu(currentTeacherUser.getEmail(), currentTeacherUser.getPassword());
                    context.dispose();
                } else {
                    context.dispose();
                }
            }
        };
        t.start();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnMenuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnMenuExitActionPerformed

    private void txtTaskNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTaskNameKeyReleased
        if (datePicker.getDate().equals(task.getDeadline()) && task.getTaskName().equals(txtTaskName.getText())) {
            btnOk.setEnabled(false);
        } else {
            btnOk.setEnabled(true);
        }
    }//GEN-LAST:event_txtTaskNameKeyReleased

    private void updateCaptions() {
        setTitle(LocalizationUtil.localizedResourceBundle.getString("addTaskMenuKey"));
        menuMenu.setText(LocalizationUtil.localizedResourceBundle.getString("menuMenu"));
        btnMenuExit.setText(LocalizationUtil.localizedResourceBundle.getString("btnExit"));
        btnOk.setText(LocalizationUtil.localizedResourceBundle.getString("okKey"));
        btnCancel.setText(LocalizationUtil.localizedResourceBundle.getString("btnCancel"));
        PromptSupport.setPrompt(LocalizationUtil.localizedResourceBundle.getString("lblTaskName"), txtTaskName);
        datePicker.setLocale(LocalizationUtil.localizedResourceBundle.getLocale());
    }

    private void setComponentsAvailable(boolean available) {
        EditTaskPBar.setVisible(!available);
        btnOk.setEnabled(available);
        btnCancel.setEnabled(available);
        datePicker.setEnabled(available);
    }

    public static void main(Task task, User user) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditTaskPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditTaskPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditTaskPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditTaskPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditTaskPopUp(task, user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar EditTaskPBar;
    private javax.swing.JButton btnCancel;
    private javax.swing.JMenuItem btnMenuExit;
    private javax.swing.JButton btnOk;
    private com.toedter.calendar.JDateChooser datePicker;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menuMenu;
    private javax.swing.JTextField txtTaskName;
    // End of variables declaration//GEN-END:variables
}
