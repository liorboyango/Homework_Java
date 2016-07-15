package homework.Forms;

import homework.Utils.DataBase;
import homework.Utils.InputValidation;
import homework.Utils.ToastMessage;
import homework.Utils.sendEmail;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.jdesktop.xswingx.PromptSupport;
import resources.LocalizationUtil;

public class MainScreen extends javax.swing.JFrame {

    private final ImageIcon imgIcon = new ImageIcon(getClass().getResource("/Images/icon.gif"));
    private ImageIcon langIcon = new ImageIcon(getClass().getResource("/Images/LangIcon.png"));
    private ImageIcon loginIcon = new ImageIcon(getClass().getResource("/Images/login.png"));
    private static String Language = LocalizationUtil.localizedResourceBundle.getLocale().getLanguage();

    public MainScreen() {
        initComponents();
        MainScreenPBar.setVisible(false);
        updateCaptions();
        setIconImage(imgIcon.getImage());
        setVisible(true);
        btnLogin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    Login();
                }
            }
        });
        btnSignup.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    Signup();
                }
            }
        });
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    Login();
                }
            }
        });
        txtEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    Login();
                }
            }
        });

        //set language icon to button
        Image imgLang = langIcon.getImage();
        Image newImgLang = imgLang.getScaledInstance(btnLanguage.getWidth(), btnLanguage.getHeight(), java.awt.Image.SCALE_SMOOTH);
        langIcon = new ImageIcon(newImgLang);
        btnLanguage.setIcon(langIcon);

        //set login icon to label
        Image imgLogin = loginIcon.getImage();
        Image newImgLogin = imgLogin.getScaledInstance(lblImageContainer.getWidth(), lblImageContainer.getHeight(), java.awt.Image.SCALE_SMOOTH);
        loginIcon = new ImageIcon(newImgLogin);
        lblImageContainer.setIcon(loginIcon);

        txtEmail.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtEmail = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        btnSignup = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        btnLanguage = new javax.swing.JButton();
        lblImageContainer = new javax.swing.JLabel();
        btnForgotPassword = new javax.swing.JButton();
        MainScreenPBar = new javax.swing.JProgressBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuMenu = new javax.swing.JMenu();
        btnMenuExit = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        btnHelpAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Homework");
        setBackground(new java.awt.Color(153, 204, 255));
        setResizable(false);

        txtEmail.setNextFocusableComponent(txtPassword);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("resources/uimessages_iw"); // NOI18N
        btnLogin.setText(bundle.getString("btnLogin")); // NOI18N
        btnLogin.setMaximumSize(new java.awt.Dimension(100, 40));
        btnLogin.setMinimumSize(new java.awt.Dimension(100, 40));
        btnLogin.setNextFocusableComponent(btnForgotPassword);
        btnLogin.setPreferredSize(new java.awt.Dimension(100, 40));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnSignup.setBackground(new java.awt.Color(155, 255, 155));
        btnSignup.setText(bundle.getString("btnSignup")); // NOI18N
        btnSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignupActionPerformed(evt);
            }
        });

        txtPassword.setPreferredSize(new java.awt.Dimension(52, 24));

        btnLanguage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/homework/Images/LangIcon.png"))); // NOI18N
        btnLanguage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLanguageActionPerformed(evt);
            }
        });

        lblImageContainer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/login.png"))); // NOI18N

        btnForgotPassword.setBackground(new java.awt.Color(255, 255, 255));
        btnForgotPassword.setForeground(new java.awt.Color(51, 51, 255));
        btnForgotPassword.setText(bundle.getString("btnForgotPassword")); // NOI18N
        btnForgotPassword.setBorderPainted(false);
        btnForgotPassword.setNextFocusableComponent(btnSignup);
        btnForgotPassword.setOpaque(false);
        btnForgotPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForgotPasswordActionPerformed(evt);
            }
        });

        MainScreenPBar.setIndeterminate(true);
        MainScreenPBar.setString("");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(444, 619, Short.MAX_VALUE)
                .addComponent(btnSignup))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblImageContainer)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnForgotPassword)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(293, 293, 293))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(MainScreenPBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(271, 271, 271))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblImageContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addComponent(btnSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnForgotPassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MainScreenPBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btnLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        Login();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignupActionPerformed
        Signup();
    }//GEN-LAST:event_btnSignupActionPerformed

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

    private void btnHelpAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpAboutActionPerformed
        AboutPopUp aboutPopUp = new AboutPopUp();
    }//GEN-LAST:event_btnHelpAboutActionPerformed

    private void btnForgotPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForgotPasswordActionPerformed
        if (!InputValidation.isValidEmail(txtEmail.getText())) {    //check for valid email input
            JOptionPane.showMessageDialog(this, LocalizationUtil.localizedResourceBundle.getString("errInvalidEmail"));
            txtEmail.requestFocus();
            return;
        }
        if (!DataBase.getInstance().isEmailExist(txtEmail.getText())) { //check email is in DB
            JOptionPane.showMessageDialog(this, LocalizationUtil.localizedResourceBundle.getString("errNoSuchEmail"));
            txtEmail.requestFocus();
            return;
        }
        String message = LocalizationUtil.localizedResourceBundle.getString("msgSendEmailBody") + txtEmail.getText();
        String title = LocalizationUtil.localizedResourceBundle.getString("msgSendEmailTitle");
        String[] buttons = {LocalizationUtil.localizedResourceBundle.getString("okKey"), LocalizationUtil.localizedResourceBundle.getString("btnCancel")};
        //confirm prompt
        if (JOptionPane.showOptionDialog(this, message, title, JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, buttons, "default") == 0) {
            try {
                if (sendEmail.send(txtEmail.getText())) {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("msgEmailWasSent"), 3000);
                    toastMessage.setVisible(true);
                } else {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("msgEmailWasNotSent"), 3000);
                    toastMessage.setVisible(true);
                }
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("msgEmailWasNotSent"), 3000);
                toastMessage.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnForgotPasswordActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }

    private void Login() {
        javax.swing.JFrame context = this;
        Thread t = new Thread() {
            public void run() {
                MainScreenPBar.setVisible(true);
                disableAllButtons(true);
                System.out.println(txtEmail.getText());
                if (!InputValidation.isValidEmail(txtEmail.getText())) {
                    JOptionPane.showMessageDialog(context, LocalizationUtil.localizedResourceBundle.getString("errInvalidEmail"));
                    txtEmail.requestFocus();
                    MainScreenPBar.setVisible(false);
                      disableAllButtons(false);
                    return;
                }
                if (!InputValidation.isValidPassword(txtPassword.getText())) {
                    JOptionPane.showMessageDialog(context, LocalizationUtil.localizedResourceBundle.getString("errInvalidPassword"));
                    txtPassword.requestFocus();
                    MainScreenPBar.setVisible(false);
                    disableAllButtons(false);
                    return;
                }
                int userType = 0;

                try {
                    if (DataBase.getInstance().isAdmin(txtEmail.getText(), txtPassword.getText())) {
                        userType = 1;
                    } else if (DataBase.getInstance().isTeacher(txtEmail.getText(), txtPassword.getText())) {
                        userType = 2;
                    } else if (DataBase.getInstance().isSimpleUser(txtEmail.getText(), txtPassword.getText())) {
                        userType = 3;
                    }
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    System.out.println("Internet Connection Error");
                    JOptionPane.showMessageDialog(context, LocalizationUtil.localizedResourceBundle.getString("errIntenetConnection"));
                }
                switch (userType) {
                    case 0:
                        System.out.println("Email/Password was wrong, try again");
                        JOptionPane.showMessageDialog(context, LocalizationUtil.localizedResourceBundle.getString("errEmailPasswordWrong"));
                        break;

                    case 1:
                        System.out.println("Admin logged in");
                        AdminUserMenu adminUserMenu = new AdminUserMenu(txtEmail.getText(), txtPassword.getText());
                        context.dispose();
                        break;

                    case 2:
                        System.out.println("Teacher logged in");
                        TeacherUserMenu teacherUserMenu = new TeacherUserMenu(txtEmail.getText(), txtPassword.getText());
                        context.dispose();
                        break;

                    case 3:
                        System.out.println("SimpleUser logged in");
                        SimpleUserMenu userMenu = new SimpleUserMenu(txtEmail.getText(), txtPassword.getText());
                        context.dispose();
                        break;

                    default:
                        break;
                }
                MainScreenPBar.setVisible(false);
                disableAllButtons(false);
            }
        };
        t.start();

    }

    private void Signup() {
        SignUpMenu signUpMenu = new SignUpMenu();
        signUpMenu.setVisible(true);
        this.dispose();
    }

    private void disableAllButtons(boolean disable) {
        btnForgotPassword.setEnabled(!disable);
        btnHelpAbout.setEnabled(!disable);
        btnLanguage.setEnabled(!disable);
        btnLogin.setEnabled(!disable);
        btnMenuExit.setEnabled(!disable);
        btnSignup.setEnabled(!disable);
    }

    private void updateCaptions() {
        PromptSupport.uninstall(txtPassword);
        PromptSupport.uninstall(txtEmail);
        PromptSupport.setPrompt(LocalizationUtil.localizedResourceBundle.getString("lblPassword"), txtPassword);
        PromptSupport.setPrompt(LocalizationUtil.localizedResourceBundle.getString("lblEmail"), txtEmail);
        setTitle(LocalizationUtil.localizedResourceBundle.getString("titleKey"));
        menuMenu.setText(LocalizationUtil.localizedResourceBundle.getString("menuMenu"));
        PromptSupport.setPrompt(LocalizationUtil.localizedResourceBundle.getString("lblEmail"), txtEmail);
        PromptSupport.setPrompt(LocalizationUtil.localizedResourceBundle.getString("lblPassword"), txtPassword);
        btnLogin.setText(LocalizationUtil.localizedResourceBundle.getString("btnLogin"));
        btnSignup.setText(LocalizationUtil.localizedResourceBundle.getString("btnSignup"));
        btnMenuExit.setText(LocalizationUtil.localizedResourceBundle.getString("btnExit"));
        menuHelp.setText(LocalizationUtil.localizedResourceBundle.getString("menuHelp"));
        btnHelpAbout.setText(LocalizationUtil.localizedResourceBundle.getString("helpAbout"));
        btnForgotPassword.setText(LocalizationUtil.localizedResourceBundle.getString("btnForgotPassword"));

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar MainScreenPBar;
    private javax.swing.JButton btnForgotPassword;
    private javax.swing.JMenuItem btnHelpAbout;
    private javax.swing.JButton btnLanguage;
    private javax.swing.JButton btnLogin;
    private javax.swing.JMenuItem btnMenuExit;
    private javax.swing.JButton btnSignup;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblImageContainer;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenu menuMenu;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
