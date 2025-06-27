/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.service;

import com.cjme.motorphsystem.dao.AllowanceTypeDAO;
import com.cjme.motorphsystem.dao.EmployeeAllowanceDAO;

import com.cjme.motorphsystem.model.AllowanceType;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author JustAMob
 */
public class AllowanceService {
    private final EmployeeAllowanceDAO employeeAllowanceDAO;
    private final AllowanceTypeDAO allowanceTypeDAO;

    public AllowanceService(EmployeeAllowanceDAO employeeAllowanceDAO, AllowanceTypeDAO allowanceTypeDAO) {
        this.employeeAllowanceDAO = employeeAllowanceDAO;
        this.allowanceTypeDAO =  allowanceTypeDAO;
    }

    
    
    public void assignAllowanceToEmployee(int employeeId, int allowanceTypeId, BigDecimal amount) throws SQLException {
        employeeAllowanceDAO.assignAllowance(employeeId, allowanceTypeId, amount);
    }

    public Map<String, BigDecimal> getAllowancesForEmployee(int employeeId) throws SQLException {
        return employeeAllowanceDAO.getAllowancesByEmployee(employeeId);
    }

    public BigDecimal getTotalAllowanceAmount(int employeeId) throws SQLException {
        return employeeAllowanceDAO.getTotalAllowance(employeeId);
    }
        // Add a new allowance type
    public void addAllowanceType(AllowanceType allowanceType) throws SQLException {
        allowanceTypeDAO.addAllowanceType(allowanceType);
    }

    // Get all allowance types
    public List<AllowanceType> getAllAllowanceTypes() throws SQLException {
        return allowanceTypeDAO.getAllAllowanceTypes();
    }

    // Get a specific allowance type by ID
    public AllowanceType getAllowanceTypeById(int id) throws SQLException {
        return allowanceTypeDAO.getAllowanceTypeById(id);
    }

    // Update an existing allowance type
    public void updateAllowanceType(AllowanceType allowanceType) throws SQLException {
        allowanceTypeDAO.updateAllowanceType(allowanceType);
    }
}
