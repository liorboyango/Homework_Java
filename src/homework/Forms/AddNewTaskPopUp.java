package homework.Forms;

import homework.Users.AdminUser;
import homework.Users.TeacherUser;
import homework.Users.User;
import homework.Utils.ToastMessage;
import java.awt.ComponentOrientation;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import org.jdesktop.xswingx.PromptSupport;
import resources.LocalizationUtil;

public class AddNewTaskPopUp extends javax.swing.JFrame {

    private final ImageIcon imgIcon = new ImageIcon(getClass().getResource("/Images/icon.gif"));
    private AdminUser currentAdminUser = new AdminUser();
    private TeacherUser currentTeacherUser = new TeacherUser();
    private int userType = 0;

    public AddNewTaskPopUp(User user) {
        Thread t = new Thread() {
            public void run() {
                initComponents();
                setComponentsAvailable(false);
                setIconImage(imgIcon.getImage());
                setVisible(true);
                setLocationRelativeTo(null);

                //retrieve permisions
                if (user == null) {
                    AddNewTaskPBar.setVisible(false);
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
                    AddNewTaskPBar.setVisible(false);
                    return;
                }
                //set watermarks
                PromptSupport.setPrompt(LocalizationUtil.localizedResourceBundle.getString("lblTaskName"), txtTaskName);
                // PromptSupport.setPrompt(LocalizationUtil.localizedResourceBundle.getString("lblTaskDeadline"), datePicker.getJFormattedTextField());
                updateCaptions();

                datePicker.getDateEditor().setEnabled(false);
                datePicker.setMinSelectableDate(Calendar.getInstance().getTime());  //set minimum date for today
                txtTaskName.requestFocus();
                setComponentsAvailable(true);
            }
        };
        t.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtTaskName = new javax.swing.JTextField();
        datePicker = new com.toedter.calendar.JDateChooser();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        AddNewTaskPBar = new javax.swing.JProgressBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuMenu = new javax.swing.JMenu();
        btnMenuExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        AddNewTaskPBar.setIndeterminate(true);
        AddNewTaskPBar.setString("");

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
                .addContainerGap(121, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtTaskName, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(btnOk)
                                .addGap(62, 62, 62)
                                .addComponent(btnCancel))
                            .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(151, 151, 151))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(AddNewTaskPBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(221, 221, 221))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(txtTaskName, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(btnCancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AddNewTaskPBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
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
                if (datePicker.getDate() == null) {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("errEnterTaskDeadline"), 3000);
                    toastMessage.setVisible(true);
                    datePicker.getCalendarButton().requestFocus();
                    setComponentsAvailable(true);
                    return;
                }

                if (userType == 1 && currentAdminUser.addNewTask(txtTaskName.getText(), datePicker.getDate())) {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("addTaskSucc"), 3000);
                    toastMessage.setVisible(true);
                    AdminUserMenu adminUserMenu = new AdminUserMenu(currentAdminUser.getEmail(), currentAdminUser.getPassword());
                    context.dispose();

                } else if (userType == 2 && currentTeacherUser.addNewTask(txtTaskName.getText(), datePicker.getDate())) {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("addTaskSucc"), 3000);
                    toastMessage.setVisible(true);
                    TeacherUserMenu teacherUserMenu = new TeacherUserMenu(currentTeacherUser.getEmail(), currentTeacherUser.getPassword());
                    context.dispose();
                } else {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("addTaskFail"), 3000);
                    toastMessage.setVisible(true);
                }
            }
        };
        t.start();
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        setComponentsAvailable(false);
        if (userType == 1) {
            AdminUserMenu adminUserMenu = new AdminUserMenu(currentAdminUser.getEmail(), currentAdminUser.getPassword());
            this.dispose();

        } else if (userType == 2) {
            TeacherUserMenu teacherUserMenu = new TeacherUserMenu(currentTeacherUser.getEmail(), currentTeacherUser.getPassword());
            this.dispose();
        } else {
            this.dispose();
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnMenuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnMenuExitActionPerformed

    private void updateCaptions() {
        setTitle(LocalizationUtil.localizedResourceBundle.getString("addTaskMenuKey"));
        menuMenu.setText(LocalizationUtil.localizedResourceBundle.getString("menuMenu"));
        btnMenuExit.setText(LocalizationUtil.localizedResourceBundle.getString("btnExit"));
        btnOk.setText(LocalizationUtil.localizedResourceBundle.getString("okKey"));
        btnCancel.setText(LocalizationUtil.localizedResourceBundle.getString("btnCancel"));
        PromptSupport.setPrompt(LocalizationUtil.localizedResourceBundle.getString("lblTaskName"), txtTaskName);
        datePicker.setLocale(LocalizationUtil.localizedResourceBundle.getLocale());
    }
    
    private void setComponentsAvailable(boolean available){
         AddNewTaskPBar.setVisible(!available);
         btnOk.setEnabled(available);
         btnCancel.setEnabled(available);
         datePicker.setEnabled(available);
    }

    public static void main(User user) {
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
            java.util.logging.Logger.getLogger(AddNewTaskPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddNewTaskPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddNewTaskPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddNewTaskPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddNewTaskPopUp(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar AddNewTaskPBar;
    private javax.swing.JButton btnCancel;
    private javax.swing.JMenuItem btnMenuExit;
    private javax.swing.JButton btnOk;
    private com.toedter.calendar.JDateChooser datePicker;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menuMenu;
    private javax.swing.JTextField txtTaskName;
    // End of variables declaration//GEN-END:variables
}
