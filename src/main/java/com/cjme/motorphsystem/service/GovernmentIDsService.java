/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.service;

import com.cjme.motorphsystem.dao.GovernmentIdDAO;
import com.cjme.motorphsystem.model.GovernmentID;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author JustAMob
 */
public class GovernmentIDsService {
    private final GovernmentIdDAO governmentIdDAO;

    public GovernmentIDsService(GovernmentIdDAO governmentIdDAO) {
        this.governmentIdDAO = governmentIdDAO;
    }

    // Add a new record
    public int addGovernmentId(GovernmentID gov) {
        return governmentIdDAO.addGovernmentId(gov);
    }

    // Fetch government ID details by employee_id
    public GovernmentID getGovernmentIdByEmployeeId(int employeeId) {
        return governmentIdDAO.getGovernmentIdByEmployeeId(employeeId);
    }

    // Update existing record
    public void updateGovernmentId(GovernmentID gov, Connection conn) throws SQLException {
        governmentIdDAO.updateGovernmentId(gov, conn);
    }

    // Delete record
    public void deleteGovernmentId(int governmentId) {
        governmentIdDAO.deleteGovernmentId(governmentId);
    }
}
