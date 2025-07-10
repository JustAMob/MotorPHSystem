/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.GovernmentID;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author JustAMob
 */
public interface GovernmentIdDAO {
    int addGovernmentId(GovernmentID govId);
    int addGovernmentId(GovernmentID gov, Connection conn) throws SQLException;
    GovernmentID getGovernmentIdByEmployeeId(int employeeId);
    void updateGovernmentId(GovernmentID govId);
    void deleteGovernmentId(int governmentId);
}
