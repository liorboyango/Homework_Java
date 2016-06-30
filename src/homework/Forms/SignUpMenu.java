package homework.Forms;

import homework.Users.AdminUser;
import homework.Users.SimpleUser;
import homework.Users.TeacherUser;
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

public class SignUpMenu extends javax.swing.JFrame {

    private final ImageIcon imgIcon = new ImageIcon(getClass().getResource("/Images/icon.gif"));
    private ImageIcon langIcon = new ImageIcon(getClass().getResource("/Images/LangIcon.png"));
    private ImageIcon signupIcon = new ImageIcon(getClass().getResource("/Images/signup.png"));
    private static String Language = LocalizationUtil.localizedResourceBundle.getLocale().getLanguage();
    private final Color colorStrong = new Color(155, 255, 155);
    private final Color colorGood = new Color(102, 153, 255);
    private final Color colorWeak = new Color(255, 101, 101);
    private final Color colorFair = new Color(255, 204, 102);
    private int userType = 0;
    private int passwordStrength = 0;

    public SignUpMenu() {
        initComponents();
        SignUpPBar.setVisible(false);
        updateCaptions();

        setIconImage(imgIcon.getImage());
        setVisible(true);
        lblPasswordStrength.setVisible(false);
        btnSignup.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    Signup();
                }
            }
        });
        btnBack.addKeyListener(new KeyAdapter() {
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

        //set signup icon to label
        Image imgSignup = signupIcon.getImage();
        Image newImgSignup = imgSignup.getScaledInstance(lblImageContainer.getWidth(), lblImageContainer.getHeight(), java.awt.Image.SCALE_SMOOTH);
        signupIcon = new ImageIcon(newImgSignup);
        lblImageContainer.setIcon(signupIcon);

        txtUsername.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsername = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnSignup = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        txtRePassword = new javax.swing.JPasswordField();
        cmbUserType = new javax.swing.JComboBox<>();
        btnLanguage = new javax.swing.JButton();
        lblPasswordStrength = new javax.swing.JLabel();
        lblPasswordDefinition = new javax.swing.JLabel();
        lblUsernameDefinition = new javax.swing.JLabel();
        lblImageContainer = new javax.swing.JLabel();
        SignUpPBar = new javax.swing.JProgressBar();
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

        btnSignup.setBackground(new java.awt.Color(153, 255, 153));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("resources/uimessages_iw"); // NOI18N
        btnSignup.setText(bundle.getString("btnSignup")); // NOI18N
        btnSignup.setMaximumSize(new java.awt.Dimension(100, 40));
        btnSignup.setMinimumSize(new java.awt.Dimension(100, 40));
        btnSignup.setPreferredSize(new java.awt.Dimension(100, 40));
        btnSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignupActionPerformed(evt);
            }
        });

        btnBack.setText(bundle.getString("btnBack")); // NOI18N
        btnBack.setMaximumSize(new java.awt.Dimension(100, 40));
        btnBack.setMinimumSize(new java.awt.Dimension(100, 40));
        btnBack.setPreferredSize(new java.awt.Dimension(100, 40));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
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

        lblImageContainer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/signup.png"))); // NOI18N

        SignUpPBar.setIndeterminate(true);
        SignUpPBar.setString("");

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
                .addComponent(lblImageContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPasswordStrength, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtRePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnSignup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblUsernameDefinition, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPasswordDefinition, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(SignUpPBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cmbUserType, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUsernameDefinition)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPasswordStrength, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPasswordDefinition)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbUserType, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblImageContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SignUpPBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignupActionPerformed
        System.out.println(txtEmail.getText());
        if (!InputValidation.isValidUsername(txtUsername.getText())) {
            JOptionPane.showMessageDialog(this, LocalizationUtil.localizedResourceBundle.getString("errInvalidUsername"));
            lblUsernameDefinition.setForeground(colorWeak);
            txtUsername.requestFocus();
            return;
        }

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
        if (!InputValidation.isValidEmail(txtEmail.getText())) {
            JOptionPane.showMessageDialog(this, LocalizationUtil.localizedResourceBundle.getString("errInvalidEmail"));
            txtEmail.requestFocus();
            return;
        }
        if (isEmailExist(txtEmail.getText())) {
            JOptionPane.showMessageDialog(this, LocalizationUtil.localizedResourceBundle.getString("errUserWithEmailExists"));
            txtEmail.requestFocus();
            return;
        }
        userType = 0;
        if (cmbUserType.getSelectedItem().toString().equals(LocalizationUtil.localizedResourceBundle.getString("userKey"))) {
            userType = 3;
        } else if (cmbUserType.getSelectedItem().toString().equals(LocalizationUtil.localizedResourceBundle.getString("teacherKey"))) {
            userType = 2;
        } else if (cmbUserType.getSelectedItem().toString().equals(LocalizationUtil.localizedResourceBundle.getString("adminKey"))) {
            userType = 1;
        }
        Signup();
    }//GEN-LAST:event_btnSignupActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        MainScreen mainMenuTest = new MainScreen();
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

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

        /*    if (result.isValid()) {
            System.out.println("Valid strong password");
            lblPasswordStrength.setVisible(true);
            lblPasswordStrength.setForeground(colorStrong);
            lblPasswordStrength.setText(LocalizationUtil.localizedResourceBundle.getString("lblPasswordStrengthStrong"));
            lblPasswordDefinition.setForeground(Color.BLACK);
            return;
        } else {
            System.out.println("Invalid strong password:");
        }

        //validate the GOOD rules
        validator = new PasswordValidator(goodRuleList);

        result = validator.validate(passwordData);

        if (result.isValid()) {
            System.out.println("Valid good password");
            lblPasswordStrength.setVisible(true);
            lblPasswordStrength.setForeground(colorGood);
            lblPasswordStrength.setText(LocalizationUtil.localizedResourceBundle.getString("lblPasswordStrengthGood"));
            lblPasswordDefinition.setForeground(Color.BLACK);
            return;
        } else {
            System.out.println("Invalid good password:");
        }

        //validate the FAIR rules
        validator = new PasswordValidator(fairRuleList);

        result = validator.validate(passwordData);

        if (result.isValid()) {
            System.out.println("Valid fair password");
            lblPasswordStrength.setVisible(true);
            lblPasswordStrength.setForeground(colorFair);
            lblPasswordStrength.setText(LocalizationUtil.localizedResourceBundle.getString("lblPasswordStrengthFair"));
            lblPasswordDefinition.setForeground(Color.BLACK);
            return;
        } else {
            System.out.println("Invalid fair password:");
        }

        //validate the WEAK rules
        validator = new PasswordValidator(weakRuleList);

        result = validator.validate(passwordData);

        if (result.isValid()) {
            System.out.println("Valid weak password");
            lblPasswordStrength.setVisible(true);
            lblPasswordStrength.setForeground(colorWeak);
            lblPasswordStrength.setText(LocalizationUtil.localizedResourceBundle.getString("lblPasswordStrengthWeak"));
            lblPasswordDefinition.setForeground(Color.BLACK);
        } else {
            System.out.println("Invalid weak password:");
            lblPasswordStrength.setVisible(false);
            lblPasswordDefinition.setForeground(colorWeak);
            for (String msg : validator.getMessages(result)) {
                System.out.println(msg);
            }
        }*/

    }//GEN-LAST:event_txtPasswordKeyReleased

    private void txtUsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyReleased
        if (InputValidation.isValidUsername(txtUsername.getText())) {
            lblUsernameDefinition.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtUsernameKeyReleased

    private void btnHelpAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpAboutActionPerformed
        AboutPopUp aboutPopUp = new AboutPopUp();
    }//GEN-LAST:event_btnHelpAboutActionPerformed

    private void Signup() {
        javax.swing.JFrame context = this;
        Thread t = new Thread() {
            public void run() {
                disableAllButtons(true);
                SignUpPBar.setVisible(true);
                switch (userType) {
                    case 1:
                        System.out.println("Admin signing up");

                        AdminUser adminUser = new AdminUser(txtUsername.getText(), txtPassword.getText(), txtEmail.getText());
                        try {
                            if (DataBase.getInstance().createUser(adminUser)) {
                                System.out.println("Successfuly Signed Up");
                                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("signupSucc"), 3000);
                                toastMessage.setVisible(true);
                                MainScreen mainScreen = new MainScreen();
                                mainScreen.setVisible(true);
                                context.dispose();
                                break;
                            } else {
                                System.out.println("Failed To Sign Up");
                                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("signupFail"), 3000);
                                toastMessage.setVisible(true);
                                break;
                            }
                        } catch (SQLException sqle) {
                            sqle.printStackTrace();
                            break;
                        }

                    case 2:
                        System.out.println("Teacher signing up");
                        TeacherUser teacherUser = new TeacherUser(txtUsername.getText(), txtPassword.getText(), txtEmail.getText());
                        try {
                            if (DataBase.getInstance().createUser(teacherUser)) {
                                System.out.println("Successfuly Signed Up");
                                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("signupSucc"), 3000);
                                toastMessage.setVisible(true);
                                MainScreen mainScreen = new MainScreen();
                                mainScreen.setVisible(true);
                                context.dispose();
                                break;
                            } else {
                                System.out.println("Failed To Sign Up");
                                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("signupFail"), 3000);
                                toastMessage.setVisible(true);
                                break;
                            }
                        } catch (SQLException sqle) {
                            sqle.printStackTrace();
                            break;
                        }

                    case 3:
                        System.out.println("SimpleUser signing up");
                        SimpleUser simpleUser = new SimpleUser(txtUsername.getText(), txtPassword.getText(), txtEmail.getText());
                        try {
                            if (DataBase.getInstance().createUser(simpleUser)) {
                                System.out.println("Successfuly Signed Up");
                                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("signupSucc"), 3000);
                                toastMessage.setVisible(true);
                                MainScreen mainScreen = new MainScreen();
                                context.dispose();
                                break;
                            } else {
                                System.out.println("Failed To Sign Up");
                                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("signupFail"), 3000);
                                toastMessage.setVisible(true);
                                break;
                            }
                        } catch (SQLException sqle) {
                            sqle.printStackTrace();
                            break;
                        }

                    default:
                        System.out.println("Error - UserType error");
                        ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("signupFail"), 3000);
                        toastMessage.setVisible(true);
                        return;

                }
                SignUpPBar.setVisible(false);
                disableAllButtons(false);
            }
        };
        t.start();

    }

    private boolean isEmailExist(String email) {
        return DataBase.getInstance().isEmailExist(email);
    }

    private void updateCaptions() {
        setTitle(LocalizationUtil.localizedResourceBundle.getString("titleKey"));
        PromptSupport.uninstall(txtUsername);
        PromptSupport.uninstall(txtPassword);
        PromptSupport.uninstall(txtRePassword);
        PromptSupport.uninstall(txtEmail);
        PromptSupport.setPrompt(LocalizationUtil.localizedResourceBundle.getString("lblUsername"), txtUsername);
        PromptSupport.setPrompt(LocalizationUtil.localizedResourceBundle.getString("lblPassword"), txtPassword);
        PromptSupport.setPrompt(LocalizationUtil.localizedResourceBundle.getString("lblRePassword"), txtRePassword);
        PromptSupport.setPrompt(LocalizationUtil.localizedResourceBundle.getString("lblEmail"), txtEmail);
        menuMenu.setText(LocalizationUtil.localizedResourceBundle.getString("menuMenu"));
        btnMenuExit.setText(LocalizationUtil.localizedResourceBundle.getString("btnExit"));
        menuHelp.setText(LocalizationUtil.localizedResourceBundle.getString("menuHelp"));
        btnHelpAbout.setText(LocalizationUtil.localizedResourceBundle.getString("helpAbout"));
        btnSignup.setText(LocalizationUtil.localizedResourceBundle.getString("btnSignup"));
        btnBack.setText(LocalizationUtil.localizedResourceBundle.getString("btnBack"));
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
        String[] userTypes = {LocalizationUtil.localizedResourceBundle.getString("userKey"),
            LocalizationUtil.localizedResourceBundle.getString("adminKey"),
            LocalizationUtil.localizedResourceBundle.getString("teacherKey")};
        cmbUserType.setModel(new DefaultComboBoxModel(userTypes));

    }

    private void disableAllButtons(boolean disable) {
        btnBack.setEnabled(!disable);
        btnHelpAbout.setEnabled(!disable);
        btnLanguage.setEnabled(!disable);
        btnMenuExit.setEnabled(!disable);
        btnSignup.setEnabled(!disable);
        cmbUserType.setEnabled(!disable);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(SignUpMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignUpMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignUpMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUpMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUpMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar SignUpPBar;
    private javax.swing.JButton btnBack;
    private javax.swing.JMenuItem btnHelpAbout;
    private javax.swing.JButton btnLanguage;
    private javax.swing.JMenuItem btnMenuExit;
    private javax.swing.JButton btnSignup;
    private javax.swing.JComboBox<String> cmbUserType;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblImageContainer;
    private javax.swing.JLabel lblPasswordDefinition;
    private javax.swing.JLabel lblPasswordStrength;
    private javax.swing.JLabel lblUsernameDefinition;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenu menuMenu;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtRePassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
