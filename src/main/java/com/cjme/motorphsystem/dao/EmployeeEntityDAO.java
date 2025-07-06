
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
import java.util.List;

public interface EmployeeEntityDAO {
    int addEmployee(EmployeeEntity employee, String role);
    EmployeeEntity getEmployeeById(int id);
    List<EmployeeEntity> getAllEmployees();
    void updateEmployee(EmployeeEntity employee, String role);
    void deleteEmployee(int id, String role);
}

