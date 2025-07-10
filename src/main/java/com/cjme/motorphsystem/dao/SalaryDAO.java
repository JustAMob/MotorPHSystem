/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cjme.motorphsystem.dao;

/**
 *
 * @author JustAMob
 */

import com.cjme.motorphsystem.model.Salary;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface SalaryDAO {
    int addSalary(Salary salary) throws SQLException;
    int addSalary(Salary salary, Connection conn) throws SQLException;
    Salary getSalaryById(int salaryId) throws SQLException;
    void updateSalary(Salary salary, Connection conn) throws SQLException;
    List<Salary> getAllSalaries() throws SQLException;
    void deleteSalary(int salaryId) throws SQLException;
    Map<String, Integer> getSalaryDescriptionIdMap() throws SQLException;
}

