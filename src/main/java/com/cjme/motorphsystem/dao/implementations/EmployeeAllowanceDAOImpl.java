/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.dao.EmployeeAllowanceDAO;
import com.cjme.motorphsystem.util.DBConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author JustAMob
 */
public class EmployeeAllowanceDAOImpl implements EmployeeAllowanceDAO {
    private final Connection conn;

    public EmployeeAllowanceDAOImpl() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public void assignAllowance(int employeeId, int allowanceTypeId, BigDecimal amount) throws SQLException {
       String sql = """
        INSERT INTO employee_allowance (employee_id, allowance_type_id, amount)
        VALUES (?, ?, ?)
        ON DUPLICATE KEY UPDATE amount = VALUES(amount)
        """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.setInt(2, allowanceTypeId);
            stmt.setBigDecimal(3, amount);
            stmt.executeUpdate();
        }
    }

    @Override
    public Map<String, BigDecimal> getAllowancesByEmployee(int employeeId) throws SQLException {
        Map<String, BigDecimal> map = new HashMap<>();
        String sql = """
            SELECT at.code, ea.amount
            FROM employee_allowance ea
            JOIN allowance_type at ON ea.allowance_type_id = at.allowance_type_id
            WHERE ea.employee_id = ?
        """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                map.put(rs.getString("code"), rs.getBigDecimal("amount"));
            }
        }
        return map;
    }

    @Override
    public BigDecimal getTotalAllowance(int employeeId) throws SQLException {
        String sql = "SELECT SUM(amount) FROM employee_allowance WHERE employee_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getBigDecimal(1);
        }
        return BigDecimal.ZERO;
    }
}
