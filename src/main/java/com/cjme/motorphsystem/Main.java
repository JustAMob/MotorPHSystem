package com.cjme.motorphsystem;


import com.cjme.motorphsystem.controller.Login;

/**
 *
 * @author JustAMob
 */


public class Main {
    public static void main(String[] args) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login login = new Login();
                login.setVisible(true);
            }
        });
    }
}
