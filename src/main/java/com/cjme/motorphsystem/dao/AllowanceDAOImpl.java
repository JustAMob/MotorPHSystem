package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.Allowance;
import com.cjme.motorphsystem.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author JustAMob
 */
public class AllowanceDAOImpl implements AllowanceDAO {

    @Override
    public boolean addAllowance(Allowance allowance, int employeeId) throws SQLException {
        String sql = "INSERT INTO allowances (employee_id, rice_subsidy, phone_allowance, clothing_allowance) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.setDouble(2, allowance.getRiceAllowance());
            stmt.setDouble(3, allowance.getPhoneAllowance());
            stmt.setDouble(4, allowance.getClothingAllowance());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public List<Allowance> getAllAllowances() throws SQLException {
        List<Allowance> allowances = new ArrayList<>();
        String sql = "SELECT * FROM allowances";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                allowances.add(mapRowToAllowance(rs));
            }
        }
        return allowances;
    }

    @Override
    public List<Allowance> getAllowancesByEmployeeId(int employeeId) throws SQLException {
        List<Allowance> allowances = new ArrayList<>();
        String sql = "SELECT * FROM allowances WHERE employee_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    allowances.add(mapRowToAllowance(rs));
                }
            }
        }
        return allowances;
    }
    
    @Override
    public double getTotalAllowanceByEmployeeID(int employeeID) throws SQLException {
    String sql = "SELECT total_allowance FROM allowances WHERE employee_id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, employeeID);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getDouble("total_allowance");
        }
    }
    return 0.0; // Default if no result found
}

    private Allowance mapRowToAllowance(ResultSet rs) throws SQLException {
        Allowance allowance = new Allowance();
        allowance.setAllowanceId(rs.getInt("allowance_id"));
        allowance.setEmployeeId(rs.getInt("employee_id"));
        allowance.setRiceAllowance(rs.getDouble("rice_allowance"));
        allowance.setPhoneAllowance(rs.getDouble("phone_allowance"));
        allowance.setClothingAllowance(rs.getDouble("clothing_allowance"));
        return allowance;
    }
}
