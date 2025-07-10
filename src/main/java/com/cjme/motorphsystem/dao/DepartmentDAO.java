/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.Department;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author JustAMob
 */
public interface DepartmentDAO {
    int addDepartment(Department department);
    Department getDepartmentById(int id);
    List<Department> getAllDepartments();
    void updateDepartment(Department department);
    void deleteDepartment(int id);

    public Map<String, Integer> getDepartmentNameIdMap()throws SQLException;


}
