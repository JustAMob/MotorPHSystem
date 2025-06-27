/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author JustAMob
 */
public interface EmployeeAllowanceDAO {
    void assignAllowance(int employeeId, int allowanceTypeId, BigDecimal amount) throws SQLException;
    Map<String, BigDecimal> getAllowancesByEmployee(int employeeId) throws SQLException;
    BigDecimal getTotalAllowance(int employeeId) throws SQLException;
}
    

