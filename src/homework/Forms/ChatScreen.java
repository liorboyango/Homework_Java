/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework.Forms;

import homework.Users.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import resources.LocalizationUtil;

/**
 *
 * @author master1590
 */
public class ChatScreen extends javax.swing.JFrame {

    BufferedReader in;
    PrintWriter out;
    User user;

    public ChatScreen(User user) {
        initComponents();
        setVisible(true);

         updateCaptions();
        if (user != null) {
            this.user = user;
        } else {
            System.out.println("bad User");
            return;
        }
        
        txtChatMessage.setEditable(false);
        txtChatBox.setEditable(false);
        try {
            run();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSendChatMessage = new javax.swing.JButton();
        txtChatMessage = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtChatBox = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuMenu = new javax.swing.JMenu();
        btnMenuExit = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        btnHelpAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("resources/uimessages"); // NOI18N
        btnSendChatMessage.setText(bundle.getString("btnSendChatMessage")); // NOI18N
        btnSendChatMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendChatMessageActionPerformed(evt);
            }
        });

        txtChatMessage.setToolTipText("");
        txtChatMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChatMessageActionPerformed(evt);
            }
        });

        txtChatBox.setColumns(20);
        txtChatBox.setRows(5);
        jScrollPane1.setViewportView(txtChatBox);

        java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("resources/uimessages_iw"); // NOI18N
        menuMenu.setText(bundle1.getString("menuMenu")); // NOI18N

        btnMenuExit.setText(bundle1.getString("btnExit")); // NOI18N
        btnMenuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuExitActionPerformed(evt);
            }
        });
        menuMenu.add(btnMenuExit);

        jMenuBar1.add(menuMenu);

        menuHelp.setText(bundle1.getString("menuHelp")); // NOI18N

        btnHelpAbout.setText(bundle1.getString("helpAbout")); // NOI18N
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
                .addContainerGap(147, Short.MAX_VALUE)
                .addComponent(txtChatMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145))
            .addGroup(layout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(btnSendChatMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtChatMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSendChatMessage)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuExitActionPerformed
        dispose();
    }//GEN-LAST:event_btnMenuExitActionPerformed

    private void btnHelpAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpAboutActionPerformed
        AboutPopUp aboutPopUp = new AboutPopUp();
    }//GEN-LAST:event_btnHelpAboutActionPerformed

    private void btnSendChatMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendChatMessageActionPerformed
        out.println(txtChatMessage.getText());
        txtChatMessage.setText("");
    }//GEN-LAST:event_btnSendChatMessageActionPerformed

    private void txtChatMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChatMessageActionPerformed
        out.println(txtChatMessage.getText());
        txtChatMessage.setText("");
    }//GEN-LAST:event_txtChatMessageActionPerformed

     private void updateCaptions() {
        setTitle(LocalizationUtil.localizedResourceBundle.getString("btnChat"));
        menuMenu.setText(LocalizationUtil.localizedResourceBundle.getString("menuMenu"));
        btnMenuExit.setText(LocalizationUtil.localizedResourceBundle.getString("btnExit"));
        menuHelp.setText(LocalizationUtil.localizedResourceBundle.getString("menuHelp"));
        btnHelpAbout.setText(LocalizationUtil.localizedResourceBundle.getString("helpAbout"));
        btnSendChatMessage.setText(LocalizationUtil.localizedResourceBundle.getString("btnSendChatMessage"));
    }
    
    private void run() throws IOException {

        // Make connection and initialize streams
        String serverAddress = "127.0.0.1";
        Socket socket = new Socket(serverAddress, 55555);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        try {
            // Process all messages from server, according to the protocol.
            while (true) {

                String line = in.readLine();
                if (line.startsWith("SUBMITNAME")) {
                    out.println(user.getUsername());
                } else if (line.startsWith("NAMEACCEPTED")) {
                    txtChatMessage.setEditable(true);
                } else if (line.startsWith("MESSAGE")) {
                    txtChatBox.append(line.substring(8) + "\n");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(ChatScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatScreen(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnHelpAbout;
    private javax.swing.JMenuItem btnMenuExit;
    private javax.swing.JButton btnSendChatMessage;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenu menuMenu;
    private javax.swing.JTextArea txtChatBox;
    private javax.swing.JTextField txtChatMessage;
    // End of variables declaration//GEN-END:variables
}
