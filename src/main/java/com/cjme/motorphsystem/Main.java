package com.cjme.motorphsystem;


import com.cjme.motorphsystem.controller.Login;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import javax.swing.UIManager;

/**
 *
 * @author JustAMob
 */


public class Main {
    public static void main(String[] args) {
        
        try {
            FlatMacDarkLaf.setup(); 
        } catch (Exception e) {
            
            System.err.println("Failed to initialize FlatLaf Cupertino Dark: " + e);
           
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                
            }
        }
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login login = new Login();
                login.setVisible(true);
            }
            
        });
    }
}
