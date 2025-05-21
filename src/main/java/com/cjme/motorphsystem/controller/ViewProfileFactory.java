/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.controller;

import com.mycompany.motorph.manager.RBACManager;
import com.mycompany.motorph.model.Employee;

/**
 * ViewProfileFactory is responsible for creating appropriate profile view
 * frames based on the user's role and permissions.
 *
 * @author Lance
 */
public class ViewProfileFactory {

    private static EmployeeSearchPage employeeSearchPage;

    /**
     * Creates a profile view frame based on user role and permissions.
     *
     * @param role The role of the user.
     * @param employeeInformation The employee details to be displayed.
     * @return An appropriate ViewProfileFrame based on the user's permissions.
     * @throws com.mycompany.motorph.manager.RBACManager.InvalidRoleException
     */
    public static ViewProfileFrame createViewProfile(String role, Employee employeeInformation) throws RBACManager.InvalidRoleException {
        // Check if the user has permission to UPDATE or DELETE employee information
        if (RBACManager.hasPermission(role, "UPDATE") || RBACManager.hasPermission(role, "DELETE")) {
            return new AdminViewProfileFrame(employeeInformation, role, employeeSearchPage);
        } else {
            return new EmployeeViewProfileFrame(employeeInformation);
        }
    }
}
