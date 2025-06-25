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
import java.sql.SQLException;
import java.util.List;

public interface SalaryDAO {
    void addSalary(Salary salary) throws SQLException;
    Salary getSalaryById(int salaryId) throws SQLException;
    List<Salary> getAllSalaries() throws SQLException;
    void updateSalary(Salary salary) throws SQLException;
    void deleteSalary(int salaryId) throws SQLException;
}

