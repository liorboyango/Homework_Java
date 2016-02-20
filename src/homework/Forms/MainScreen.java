package homework.Forms;

import homework.Utils.DataBase;
import homework.Utils.InputValidation;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
        jMenuBar1 = new javax.swing.JMenuBar();
        menuMenu = new javax.swing.JMenu();
        btnMenuExit = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        btnHelpAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Homework");
        setBackground(new java.awt.Color(153, 204, 255));
        setResizable(false);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("resources/uimessages_iw"); // NOI18N
        btnLogin.setText(bundle.getString("btnLogin")); // NOI18N
        btnLogin.setMaximumSize(new java.awt.Dimension(100, 40));
        btnLogin.setMinimumSize(new java.awt.Dimension(100, 40));
        btnLogin.setPreferredSize(new java.awt.Dimension(100, 40));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

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
                .addGap(444, 606, Short.MAX_VALUE)
                .addComponent(btnSignup))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(623, Short.MAX_VALUE)
                        .addComponent(btnLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(277, 277, 277)
                                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(139, 139, 139)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblImageContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtEmail)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblImageContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addComponent(btnSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
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
        System.out.println(txtEmail.getText());
        if (!InputValidation.isValidEmail(txtEmail.getText())) {
            JOptionPane.showMessageDialog(this, LocalizationUtil.localizedResourceBundle.getString("errInvalidEmail"));
            txtEmail.requestFocus();
            return;
        }
        if (!InputValidation.isValidPassword(txtPassword.getText())) {
            JOptionPane.showMessageDialog(this, LocalizationUtil.localizedResourceBundle.getString("errInvalidPassword"));
            txtPassword.requestFocus();
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
            JOptionPane.showMessageDialog(this, LocalizationUtil.localizedResourceBundle.getString("errIntenetConnection"));
        }
        switch (userType) {
            case 0:
                System.out.println("Email/Password was wrong, try again");
                JOptionPane.showMessageDialog(this, LocalizationUtil.localizedResourceBundle.getString("errEmailPasswordWrong"));
                break;

            case 1:
                System.out.println("Admin logged in");
                AdminUserMenu adminUserMenu = new AdminUserMenu(txtEmail.getText(), txtPassword.getText());
                this.dispose();
                break;

            case 2:
                System.out.println("Teacher logged in");
                TeacherUserMenu teacherUserMenu = new TeacherUserMenu(txtEmail.getText(), txtPassword.getText());
                this.dispose();
                break;

            case 3:
                System.out.println("SimpleUser logged in");
                SimpleUserMenu userMenu = new SimpleUserMenu(txtEmail.getText(), txtPassword.getText());
                this.dispose();
                break;

            default:
                break;
        }
    }

    private void Signup() {
        SignUpMenu signUpMenu = new SignUpMenu();
        signUpMenu.setVisible(true);
        this.dispose();
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

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
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
