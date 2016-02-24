package homework.Forms;

import homework.Users.AdminUser;
import homework.Users.SimpleUser;
import homework.Users.TeacherUser;
import homework.Users.User;
import homework.Utils.DataBase;
import homework.Utils.InputValidation;
import homework.Utils.ToastMessage;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.jdesktop.xswingx.PromptSupport;
import resources.LocalizationUtil;

public class EditProfile extends javax.swing.JFrame {

    private AdminUser currentAdminUser = new AdminUser();
    private TeacherUser currentTeacherUser = new TeacherUser();
    private SimpleUser currentSimpleUser = new SimpleUser();
    private final ImageIcon imgIcon = new ImageIcon(getClass().getResource("/Images/icon.gif"));
    private ImageIcon langIcon = new ImageIcon(getClass().getResource("/Images/LangIcon.png"));
    private ImageIcon profileIcon = new ImageIcon(getClass().getResource("/Images/profile.png"));
    private static String Language = LocalizationUtil.localizedResourceBundle.getLocale().getLanguage();
    private final Color colorStrong = new Color(155, 255, 155);
    private final Color colorGood = new Color(102, 153, 255);
    private final Color colorWeak = new Color(255, 101, 101);
    private final Color colorFair = new Color(255, 204, 102);
    private int userType = 0;
    private String userEmail = "";
    private int passwordStrength = 0;
    private boolean changePassword = false;

    public EditProfile(User user) {
        initComponents();

        //retrieve permisions
        if (user == null) {
            return;
        }
        userType = user.getUserType();
        userEmail = user.getEmail();
        if (userType == 1) {
            currentAdminUser = currentAdminUser.getUser(user.getEmail(), user.getPassword());
        } else if (userType == 2) {
            currentTeacherUser = currentTeacherUser.getUser(user.getEmail(), user.getPassword());
        } else if (userType == 3) {
            currentSimpleUser = currentSimpleUser.getUser(user.getEmail(), user.getPassword());
        } else {
            ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("errErrorOccurred"), 3000);
            toastMessage.setVisible(true);
            return;
        }

        updateCaptions();

        setIconImage(imgIcon.getImage());
        setVisible(true);
        lblPasswordStrength.setVisible(false);
        btnEdit.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    Edit();
                }
            }
        });
        btnCancel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    MainScreen mainMenuTest = new MainScreen();
                    dispose();
                }
            }
        });

        //set language icon to button
        Image img = langIcon.getImage();
        Image newimg = img.getScaledInstance(btnLanguage.getWidth(), btnLanguage.getHeight(), java.awt.Image.SCALE_SMOOTH);
        langIcon = new ImageIcon(newimg);
        btnLanguage.setIcon(langIcon);

        //set profile icon to label
        Image imgProfile = profileIcon.getImage();
        Image newImgProfile = imgProfile.getScaledInstance(lblImageContainer.getWidth(), lblImageContainer.getHeight(), java.awt.Image.SCALE_SMOOTH);
        profileIcon = new ImageIcon(newImgProfile);
        lblImageContainer.setIcon(profileIcon);

        //set previous details
        txtUsername.setText(user.getUsername());
        cmbUserType.setSelectedIndex(userType-1);

        txtCurrentPassword.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsername = new javax.swing.JTextField();
        txtCurrentPassword = new javax.swing.JTextField();
        btnEdit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        txtRePassword = new javax.swing.JPasswordField();
        cmbUserType = new javax.swing.JComboBox<>();
        btnLanguage = new javax.swing.JButton();
        lblPasswordStrength = new javax.swing.JLabel();
        lblPasswordDefinition = new javax.swing.JLabel();
        lblUsernameDefinition = new javax.swing.JLabel();
        lblImageContainer = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuMenu = new javax.swing.JMenu();
        btnMenuExit = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        btnHelpAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sign Up");
        setMinimumSize(new java.awt.Dimension(736, 417));
        setResizable(false);

        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsernameKeyReleased(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(153, 255, 153));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("resources/uimessages_iw"); // NOI18N
        btnEdit.setText(bundle.getString("btnEdit")); // NOI18N
        btnEdit.setMaximumSize(new java.awt.Dimension(100, 40));
        btnEdit.setMinimumSize(new java.awt.Dimension(100, 40));
        btnEdit.setPreferredSize(new java.awt.Dimension(100, 40));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnCancel.setText(bundle.getString("btnCancel")); // NOI18N
        btnCancel.setMaximumSize(new java.awt.Dimension(100, 40));
        btnCancel.setMinimumSize(new java.awt.Dimension(100, 40));
        btnCancel.setPreferredSize(new java.awt.Dimension(100, 40));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordKeyReleased(evt);
            }
        });

        btnLanguage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/homework/Images/LangIcon.png"))); // NOI18N
        btnLanguage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLanguageActionPerformed(evt);
            }
        });

        lblPasswordStrength.setForeground(new java.awt.Color(255, 102, 102));
        lblPasswordStrength.setText(bundle.getString("lblPasswordStrengthWeak")); // NOI18N

        lblPasswordDefinition.setText(bundle.getString("lblPasswordDefinition")); // NOI18N

        lblUsernameDefinition.setText(bundle.getString("lblUsernameDefinition")); // NOI18N

        lblImageContainer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/profile.png"))); // NOI18N

        menuMenu.setText(bundle.getString("menuMenu")); // NOI18N

        btnMenuExit.setText(bundle.getString("btnExit")); // NOI18N
        btnMenuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuExitActionPerformed(evt);
            }
        });
        menuMenu.add(btnMenuExit);

        jMenuBar1.add(menuMenu);

        menuHelp.setText(bundle.getString("menuHelp")); // NOI18N

        btnHelpAbout.setText(bundle.getString("helpAbout")); // NOI18N
        btnHelpAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpAboutActionPerformed(evt);
            }
        });
        menuHelp.add(btnHelpAbout);

        jMenuBar1.add(menuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblImageContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(175, 175, 175)
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblUsernameDefinition, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCurrentPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPasswordDefinition, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPasswordStrength, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(126, 126, 126)
                                .addComponent(cmbUserType, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 105, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImageContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUsernameDefinition)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCurrentPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPasswordStrength, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPasswordDefinition)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(cmbUserType, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(btnLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (!InputValidation.isValidUsername(txtUsername.getText())) {  //check for valid username
            JOptionPane.showMessageDialog(this, LocalizationUtil.localizedResourceBundle.getString("errInvalidUsername"));
            lblUsernameDefinition.setForeground(colorWeak);
            txtUsername.requestFocus();
            return;
        }
        if (!InputValidation.isValidPassword(txtCurrentPassword.getText())) {   //check for valid current password
            JOptionPane.showMessageDialog(this, LocalizationUtil.localizedResourceBundle.getString("errInvalidPassword"));
            txtCurrentPassword.requestFocus();
            return;
        }
        switch (userType) { //check if current password is correct
            case 1:
                try {
                    if (!DataBase.getInstance().isAdmin(userEmail, txtCurrentPassword.getText())) {
                        JOptionPane.showMessageDialog(this, LocalizationUtil.localizedResourceBundle.getString("errCurrentPasswordDontMatch"));
                        return;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return;
                }
                break;
            case 2:
                try {
                    if (!DataBase.getInstance().isTeacher(userEmail, txtCurrentPassword.getText())) {
                        JOptionPane.showMessageDialog(this, LocalizationUtil.localizedResourceBundle.getString("errCurrentPasswordDontMatch"));
                        return;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return;
                }
                break;
            case 3:
                try {
                    if (!DataBase.getInstance().isSimpleUser(userEmail, txtCurrentPassword.getText())) {
                        JOptionPane.showMessageDialog(this, LocalizationUtil.localizedResourceBundle.getString("errCurrentPasswordDontMatch"));
                        return;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return;
                }
                break;
        }
        changePassword = false;
        if (!(txtPassword.getText().isEmpty() && txtRePassword.getText().isEmpty())) {  //if new password is entered - validatate
            changePassword = true;
            if (passwordStrength < 1) {
                JOptionPane.showMessageDialog(this, LocalizationUtil.localizedResourceBundle.getString("errInvalidPassword"));
                lblPasswordDefinition.setForeground(colorWeak);
                txtPassword.requestFocus();
                return;
            }
            if (!txtPassword.getText().equals(txtRePassword.getText())) {
                JOptionPane.showMessageDialog(this, LocalizationUtil.localizedResourceBundle.getString("errPasswordsDontMatch"));
                txtRePassword.requestFocus();
                return;
            }
        }
        int tempUserType = userType; 
        userType = 0;
        if (cmbUserType.getSelectedItem().toString().equals(LocalizationUtil.localizedResourceBundle.getString("userKey"))) {
            userType = 3;
        } else if (cmbUserType.getSelectedItem().toString().equals(LocalizationUtil.localizedResourceBundle.getString("teacherKey"))) {
            userType = 2;
        } else if (cmbUserType.getSelectedItem().toString().equals(LocalizationUtil.localizedResourceBundle.getString("adminKey"))) {
            userType = 1;
        }
        if(tempUserType==1 && userType > tempUserType)  //if admin tries to become teacher or simple user
            if(isOnlyAdmin()){  //if admin is the only admin in the group
                JOptionPane.showMessageDialog(this, LocalizationUtil.localizedResourceBundle.getString("errAdminRemove"));
                cmbUserType.requestFocus();
                userType=tempUserType;
                return;
            }
                
        Edit();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        switch (userType) {
            case 1:

                AdminUserMenu adminUserMenu = new AdminUserMenu(currentAdminUser.getEmail(), currentAdminUser.getPassword());
                this.dispose();
                break;

            case 2:

                TeacherUserMenu teacherUserMenu = new TeacherUserMenu(currentTeacherUser.getEmail(), currentTeacherUser.getPassword());
                this.dispose();
                break;

            case 3:

                SimpleUserMenu simpleUserMenu = new SimpleUserMenu(currentSimpleUser.getEmail(), currentSimpleUser.getPassword());
                this.dispose();
                break;

            default:
                MainScreen mainMenu = new MainScreen();
                this.dispose();
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnMenuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnMenuExitActionPerformed

    private void btnLanguageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanguageActionPerformed

        if (Language.equals("iw")) {
            Language = "en";
        } else if (Language.equals("en")) {
            Language = "iw";
        }
        LocalizationUtil.localizedResourceBundle = ResourceBundle.getBundle("resources.uimessages", new Locale(Language));
        updateCaptions();
    }//GEN-LAST:event_btnLanguageActionPerformed

    private void txtPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyReleased
        passwordStrength = InputValidation.getPasswordStrength(txtPassword.getText());
        switch (passwordStrength) {

            case 0:
                lblPasswordStrength.setVisible(false);
                lblPasswordDefinition.setForeground(colorWeak);
                break;
            case 4: //STRONG
                lblPasswordStrength.setVisible(true);
                lblPasswordStrength.setForeground(colorStrong);
                lblPasswordStrength.setText(LocalizationUtil.localizedResourceBundle.getString("lblPasswordStrengthStrong"));
                lblPasswordDefinition.setForeground(Color.BLACK);
                break;
            case 3: //GOOD
                lblPasswordStrength.setVisible(true);
                lblPasswordStrength.setForeground(colorGood);
                lblPasswordStrength.setText(LocalizationUtil.localizedResourceBundle.getString("lblPasswordStrengthGood"));
                lblPasswordDefinition.setForeground(Color.BLACK);
                break;
            case 2: //FAIR
                lblPasswordStrength.setVisible(true);
                lblPasswordStrength.setForeground(colorFair);
                lblPasswordStrength.setText(LocalizationUtil.localizedResourceBundle.getString("lblPasswordStrengthFair"));
                lblPasswordDefinition.setForeground(Color.BLACK);
                break;
            case 1: //WEAK
                lblPasswordStrength.setVisible(true);
                lblPasswordStrength.setForeground(colorWeak);
                lblPasswordStrength.setText(LocalizationUtil.localizedResourceBundle.getString("lblPasswordStrengthWeak"));
                lblPasswordDefinition.setForeground(Color.BLACK);
                break;
            default:
                lblPasswordStrength.setVisible(false);
                lblPasswordDefinition.setForeground(colorWeak);
                break;

        }
    }//GEN-LAST:event_txtPasswordKeyReleased

    private void txtUsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyReleased
        if (InputValidation.isValidUsername(txtUsername.getText())) {
            lblUsernameDefinition.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtUsernameKeyReleased

    private void btnHelpAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpAboutActionPerformed
        AboutPopUp aboutPopUp = new AboutPopUp();
    }//GEN-LAST:event_btnHelpAboutActionPerformed

    private void Edit() {
        String username = txtUsername.getText();
        String password = txtCurrentPassword.getText();
        if (changePassword) {
            password = txtPassword.getText();
        }
        switch (userType) {
            case 1:
                System.out.println("Admin editing profile");
                if (currentAdminUser.editUser(userEmail, username, password, userType)) {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("editProfileSucc"), 3000);
                    toastMessage.setVisible(true);
                    AdminUserMenu adminUserMenu = new AdminUserMenu(userEmail, password);
                    this.dispose();
                } else {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("editProfileFail"), 3000);
                    toastMessage.setVisible(true);
                }
                break;
            case 2:
                System.out.println("Teacher editing profile");
                if (currentTeacherUser.editUser(userEmail, username, password, userType)) {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("editProfileSucc"), 3000);
                    toastMessage.setVisible(true);
                    TeacherUserMenu teacherUserMenu = new TeacherUserMenu(userEmail, password);
                    this.dispose();
                } else {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("editProfileFail"), 3000);
                    toastMessage.setVisible(true);
                }

                break;

            case 3:
                System.out.println("SimpleUser editing profile");
                if (currentSimpleUser.editUser(userEmail, username, password, userType)) {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("editProfileSucc"), 3000);
                    toastMessage.setVisible(true);
                    SimpleUserMenu simpleUserMenu = new SimpleUserMenu(userEmail, password);
                    this.dispose();
                } else {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("editProfileFail"), 3000);
                    toastMessage.setVisible(true);
                }
                break;

            default:
                System.out.println("Error - UserType error");
                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("editProfileFail"), 3000);
                toastMessage.setVisible(true);
        }

    }

    private void updateCaptions() {
        setTitle(LocalizationUtil.localizedResourceBundle.getString("titleKey"));
        PromptSupport.uninstall(txtUsername);
        PromptSupport.uninstall(txtPassword);
        PromptSupport.uninstall(txtRePassword);
        PromptSupport.uninstall(txtCurrentPassword);
        PromptSupport.setPrompt(LocalizationUtil.localizedResourceBundle.getString("lblUsername"), txtUsername);
        PromptSupport.setPrompt(LocalizationUtil.localizedResourceBundle.getString("lblNewPassword"), txtPassword);
        PromptSupport.setPrompt(LocalizationUtil.localizedResourceBundle.getString("lblNewRePassword"), txtRePassword);
        PromptSupport.setPrompt(LocalizationUtil.localizedResourceBundle.getString("lblCurrentPassword"), txtCurrentPassword);
        menuMenu.setText(LocalizationUtil.localizedResourceBundle.getString("menuMenu"));
        btnMenuExit.setText(LocalizationUtil.localizedResourceBundle.getString("btnExit"));
        menuHelp.setText(LocalizationUtil.localizedResourceBundle.getString("menuHelp"));
        btnHelpAbout.setText(LocalizationUtil.localizedResourceBundle.getString("helpAbout"));
        btnEdit.setText(LocalizationUtil.localizedResourceBundle.getString("btnEdit"));
        btnCancel.setText(LocalizationUtil.localizedResourceBundle.getString("btnBack"));
        lblPasswordDefinition.setText(LocalizationUtil.localizedResourceBundle.getString("lblPasswordDefinition"));
        lblUsernameDefinition.setText(LocalizationUtil.localizedResourceBundle.getString("lblUsernameDefinition"));
        if (passwordStrength <= 1) {
            lblPasswordStrength.setText(LocalizationUtil.localizedResourceBundle.getString("lblPasswordStrengthWeak"));
        } else if (passwordStrength == 2) {
            lblPasswordStrength.setText(LocalizationUtil.localizedResourceBundle.getString("lblPasswordStrengthFair"));
        } else if (passwordStrength == 3) {
            lblPasswordStrength.setText(LocalizationUtil.localizedResourceBundle.getString("lblPasswordStrengthGood"));
        } else if (passwordStrength == 4) {
            lblPasswordStrength.setText(LocalizationUtil.localizedResourceBundle.getString("lblPasswordStrengthStrong"));
        }
        //set the usertypes combobox
        String[] userTypes = {LocalizationUtil.localizedResourceBundle.getString("adminKey"),
            LocalizationUtil.localizedResourceBundle.getString("teacherKey"),
            LocalizationUtil.localizedResourceBundle.getString("userKey")};
        cmbUserType.setModel(new DefaultComboBoxModel(userTypes));

    }

    private boolean isOnlyAdmin(){
        for(User u : currentAdminUser.getAllUsers(currentAdminUser.getClassID())){
            if(!u.getEmail().equals(currentAdminUser.getEmail()) && u.getUserType()==1)
                return false;
        }
        return true;
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
            java.util.logging.Logger.getLogger(EditProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditProfile(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JMenuItem btnHelpAbout;
    private javax.swing.JButton btnLanguage;
    private javax.swing.JMenuItem btnMenuExit;
    private javax.swing.JComboBox<String> cmbUserType;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblImageContainer;
    private javax.swing.JLabel lblPasswordDefinition;
    private javax.swing.JLabel lblPasswordStrength;
    private javax.swing.JLabel lblUsernameDefinition;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenu menuMenu;
    private javax.swing.JTextField txtCurrentPassword;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtRePassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
