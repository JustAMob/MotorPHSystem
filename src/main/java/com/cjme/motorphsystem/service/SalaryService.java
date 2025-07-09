/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.service;

import com.cjme.motorphsystem.dao.SalaryDAO;
import com.cjme.motorphsystem.model.Salary;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author JustAMob
 */
public class SalaryService {

    final SalaryDAO salaryDAO;

    public SalaryService(SalaryDAO salaryDAO) {
        this.salaryDAO = salaryDAO;
    }

    public void addSalary(Salary salary) {
        try {
            salaryDAO.addSalary(salary);
        } catch (SQLException e) {
        }
    }

    public Salary getSalaryById(int salaryId) {
        try {
            return salaryDAO.getSalaryById(salaryId);
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Salary> getAllSalaries() {
        try {
            return salaryDAO.getAllSalaries();
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    public void updateSalary(Salary salary) {
        try {
            salaryDAO.updateSalary(salary);
        } catch (SQLException e) {
        }
    }

    public void deleteSalary(int salaryId) {
        try {
            salaryDAO.deleteSalary(salaryId);
        } catch (SQLException e) {
        }
    }
}