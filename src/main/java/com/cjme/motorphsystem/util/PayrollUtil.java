/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 *
 * @author JustAMob
 */
public class PayrollUtil {

    /**
     *
     * @param connection
     * @param payrollPeriod
     * @throws SQLException
     */
    public static void generatePayroll(Connection connection, String payrollPeriod) throws SQLException {
        String query = "INSERT INTO payroll (employee_id, payroll_period, salary, total_hours, total_deductions) " +
                       "SELECT e.id, ?, e.basic_salary, " +
                       "COALESCE(SUM(t.hours_worked), 0), " +
                       "(COALESCE(SUM(t.hours_worked), 0) / 160) * e.basic_salary " +
                       "FROM employee e " +
                       "LEFT JOIN timelog t ON e.id = t.employee_id " +
                       "AND t.date BETWEEN ? AND ? " +
                       "GROUP BY e.id";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, payrollPeriod);
            stmt.setString(2, payrollPeriod + "-01");  // Start of the payroll period (adjust as needed)
            stmt.setString(3, payrollPeriod + "-30");  // End of the payroll period (adjust as needed)
            stmt.executeUpdate();
        }
    }
    


    public static double round(double value) {
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    
}

