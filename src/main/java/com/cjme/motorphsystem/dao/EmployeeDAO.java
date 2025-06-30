
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author JustAMob
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.Employee;
import java.util.List;

public interface EmployeeDAO {
    int addEmployee(Employee employee, String role);
    Employee getEmployeeById(int id);
    List<Employee> getAllEmployees();
    void updateEmployee(Employee employee, String role);
    void deleteEmployee(int id, String role);
}

