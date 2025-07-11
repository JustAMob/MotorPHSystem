/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.cjme.motorphsystem.controller;

import com.cjme.motorphsystem.service.UserSession;
import java.awt.Window;
import javax.swing.SwingUtilities;

/**
 *
 * @author MYS
 */
public class LogOut extends javax.swing.JDialog {
    private final UserSession session;
    /**
     * Creates new form LogOut
     */
    public LogOut(java.awt.Frame parent, boolean modal, UserSession session) {
        super(parent, modal);
        this.session = session;
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LogOutPanel = new javax.swing.JPanel();
        LogOutLabel = new javax.swing.JLabel();
        YesLogOutButton = new javax.swing.JButton();
        NoGoBackButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(400, 160));
        setMinimumSize(new java.awt.Dimension(400, 160));
        setModal(true);
        setResizable(false);

        LogOutLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LogOutLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LogOutLabel.setText("Are you sure you want to log out?");

        YesLogOutButton.setBackground(new java.awt.Color(204, 0, 0));
        YesLogOutButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        YesLogOutButton.setForeground(new java.awt.Color(255, 255, 255));
        YesLogOutButton.setText("Yes, Log Out");
        YesLogOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YesLogOutButtonActionPerformed(evt);
            }
        });

        NoGoBackButton.setText("No, Go Back");
        NoGoBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoGoBackButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LogOutPanelLayout = new javax.swing.GroupLayout(LogOutPanel);
        LogOutPanel.setLayout(LogOutPanelLayout);
        LogOutPanelLayout.setHorizontalGroup(
            LogOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LogOutLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LogOutPanelLayout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addComponent(NoGoBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(YesLogOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );
        LogOutPanelLayout.setVerticalGroup(
            LogOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LogOutPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(LogOutLabel)
                .addGap(18, 18, 18)
                .addGroup(LogOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(YesLogOutButton)
                    .addComponent(NoGoBackButton))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LogOutPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LogOutPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NoGoBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoGoBackButtonActionPerformed

        // Close the current frame
        this.dispose();
    }//GEN-LAST:event_NoGoBackButtonActionPerformed

    private void YesLogOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YesLogOutButtonActionPerformed
        // TODO add your handling code here:
        Login login = new Login();
        login.setVisible(true);
        login.setLocationRelativeTo(null);
        
        Window parent = SwingUtilities.getWindowAncestor(this);
        if (parent != null) {
        parent.dispose(); // Closes MainAppFrame
        }
       
        this.dispose();
    }//GEN-LAST:event_YesLogOutButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(LogOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(LogOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(LogOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(LogOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LogOut dialog = new LogOut(new javax.swing.JFrame(), true, Usersession session);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LogOutLabel;
    private javax.swing.JPanel LogOutPanel;
    private javax.swing.JButton NoGoBackButton;
    private javax.swing.JButton YesLogOutButton;
    // End of variables declaration//GEN-END:variables
}
