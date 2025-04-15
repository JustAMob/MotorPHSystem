package com.cjme.motorphsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JustAMob
 */

public class DeductionDAO {

    private final Connection connection;

    public DeductionDAO(Connection connection) {
        this.connection = connection;
    }

    // Insert deductions fom an employee ID
    public void addDeductions(int employeeID, double sss, double philHealth, double pagibig) throws SQLException {
        String sql = "INSERT INTO deductions (employee_id, sss_deduction, philhealth_deduction, pagibig_deduction) " +
                     "VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeID);
            stmt.setDouble(2, sss);
            stmt.setDouble(3, philHealth);
            stmt.setDouble(4, pagibig);
            stmt.executeUpdate();
        }
    }

    // Get total deductions from employee ID
    public double getTotalDeductions(int employeeID) throws SQLException {
        String sql = "SELECT total_deductions FROM deductions WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total_deductions");
            }
        }
        return 0.0;
    }

    //Get detailed deductions by type
    public DeductionRecord getDetailedDeductions(int employeeID) throws SQLException {
        String sql = "SELECT sss_deduction, philhealth_deduction, pagibig_deduction FROM deductions WHERE payroll_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new DeductionRecord(
                        rs.getDouble("sss_deduction"),
                        rs.getDouble("philhealth_deduction"),
                        rs.getDouble("pagibig_deduction")
                );
            }
        }
        return null;
    }

    // Helper class for holding data (if needed)
    public static class DeductionRecord {
        public double sss;
        public double philHealth;
        public double pagibig;

        public DeductionRecord(double sss, double philHealth, double pagibig) {
            this.sss = sss;
            this.philHealth = philHealth;
            this.pagibig = pagibig;
        }
    }
}

