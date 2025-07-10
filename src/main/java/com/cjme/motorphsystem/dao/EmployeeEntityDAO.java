
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author JustAMob
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.EmployeeEntity;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeEntityDAO {
    int addEmployee(EmployeeEntity emp, Connection conn) throws SQLException;
    EmployeeEntity getEmployeeById(int id);
    List<EmployeeEntity> getAllEmployees();
    void updateEmployee(EmployeeEntity employee);
    void deleteEmployee(int id);
}

