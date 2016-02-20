package homework.Forms;

import homework.Utils.sendEmail;
import java.awt.ComponentOrientation;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import resources.LocalizationUtil;

public class AboutPopUp extends javax.swing.JFrame {

    private final ImageIcon imgIcon = new ImageIcon(getClass().getResource("/Images/icon.gif"));
    private ImageIcon aboutIcon = new ImageIcon(getClass().getResource("/Images/aboutUs.jpg"));
    private ImageIcon paypalIcon = new ImageIcon(getClass().getResource("/Images/paypal.gif"));
    private static String Language = LocalizationUtil.localizedResourceBundle.getLocale().getLanguage();

    public AboutPopUp() {
        initComponents();

        updateCaptions();

        setIconImage(imgIcon.getImage());
        setVisible(true);
        setLocationRelativeTo(null);

        //set listeners for ESC/ENTER keys to close the form
        btnClose.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE || evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    dispose();
                }
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE || evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    dispose();
                }
            }
        });
        
        //set aboutUs icon to label
        Image imgAbout = aboutIcon.getImage();
        Image newImgAbout = imgAbout.getScaledInstance(getWidth(), 180, java.awt.Image.SCALE_SMOOTH);
        aboutIcon = new ImageIcon(newImgAbout);
        lblImageContainer.setIcon(aboutIcon);

        //set paypal icon to label
        Image imgPaypal = paypalIcon.getImage();
        Image newImgPaypal = imgPaypal.getScaledInstance(btnDonate.getWidth(), btnDonate.getHeight(), java.awt.Image.SCALE_SMOOTH);
        paypalIcon = new ImageIcon(newImgPaypal);
        btnDonate.setIcon(paypalIcon);

        btnClose.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnClose = new javax.swing.JButton();
        lblImageContainer = new javax.swing.JLabel();
        lblVersion = new javax.swing.JLabel();
        lblCopyRights = new javax.swing.JLabel();
        txtAboutContent = new javax.swing.JLabel();
        btnEmail = new javax.swing.JButton();
        btnDonate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("resources/uimessages_iw"); // NOI18N
        setTitle(bundle.getString("helpAbout")); // NOI18N
        setResizable(false);

        btnClose.setText(bundle.getString("btnClose")); // NOI18N
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        lblImageContainer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/aboutUs.jpg"))); // NOI18N

        lblVersion.setText(bundle.getString("version")); // NOI18N

        lblCopyRights.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCopyRights.setText(bundle.getString("copyRights")); // NOI18N
        lblCopyRights.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtAboutContent.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtAboutContent.setText(bundle.getString("txtAboutContent")); // NOI18N
        txtAboutContent.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        txtAboutContent.setFocusable(false);
        txtAboutContent.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        btnEmail.setBackground(new java.awt.Color(255, 255, 255));
        btnEmail.setForeground(new java.awt.Color(51, 51, 255));
        btnEmail.setText(bundle.getString("email")); // NOI18N
        btnEmail.setBorderPainted(false);
        btnEmail.setOpaque(false);
        btnEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmailActionPerformed(evt);
            }
        });

        btnDonate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/paypal.gif"))); // NOI18N
        btnDonate.setBorderPainted(false);
        btnDonate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDonateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDonate, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblCopyRights, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblVersion)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblImageContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(277, 277, 277)
                                .addComponent(btnClose)))
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtAboutContent)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImageContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAboutContent, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblVersion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCopyRights, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDonate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmailActionPerformed
        try {
            sendEmail.mailto(Arrays.asList("master010590@gmail.com"), "Homework App",
                    "");
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnEmailActionPerformed

    private void btnDonateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDonateActionPerformed
        try {
            openWebpage(new URL("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=H4AWDKAG4WBMW"));
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnDonateActionPerformed

    private void updateCaptions() {
        setTitle(LocalizationUtil.localizedResourceBundle.getString("helpAbout"));
        txtAboutContent.setText(LocalizationUtil.localizedResourceBundle.getString("txtAboutContent"));
        if (Language.equals("iw")) {
            txtAboutContent.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
           // txtAboutContent.setHorizontalAlignment(SwingConstants.RIGHT);
        } else {
            txtAboutContent.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
          //  txtAboutContent.setHorizontalAlignment(SwingConstants.LEFT);
        }
        btnClose.setText(LocalizationUtil.localizedResourceBundle.getString("btnClose"));
        lblVersion.setText(LocalizationUtil.localizedResourceBundle.getString("version"));
        lblCopyRights.setText(LocalizationUtil.localizedResourceBundle.getString("copyRights"));

    }

    public static void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void openWebpage(URL url) {
        try {
            openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

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
            java.util.logging.Logger.getLogger(AboutPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AboutPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AboutPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AboutPopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AboutPopUp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDonate;
    private javax.swing.JButton btnEmail;
    private javax.swing.JLabel lblCopyRights;
    private javax.swing.JLabel lblImageContainer;
    private javax.swing.JLabel lblVersion;
    private javax.swing.JLabel txtAboutContent;
    // End of variables declaration//GEN-END:variables
}
