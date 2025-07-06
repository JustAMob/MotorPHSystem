/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.EmployeeProfile;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author JustAMob
 */
public interface EmployeeProfileDAO {
     EmployeeProfile getEmployeeById(int employeeId) throws SQLException;
    List<EmployeeProfile> getAllEmployees() throws SQLException;
}
