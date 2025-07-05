/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.security;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author JustAMob
 */
public class SecurityManager {
    public static void hideTabIfNoAccess(JTabbedPane tabbedPane, JPanel tabPanel, boolean hasAccess) {
    int index = tabbedPane.indexOfComponent(tabPanel);
    if (index != -1) {
        if (!hasAccess) {
            tabbedPane.setEnabledAt(index, false);              // Disable the tab
            tabbedPane.setTitleAt(index, "");                   // Blank out the tab title
            tabPanel.setVisible(false);                         // Hide the content (optional)
        } else {
            tabbedPane.setEnabledAt(index, true);               // Enable if access granted
            // Optional: restore title if you stored it somewhere
            tabPanel.setVisible(true);
        }
    }
}
}
