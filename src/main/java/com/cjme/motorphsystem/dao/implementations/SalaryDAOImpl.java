/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.dao.implementations;

/**
 *
 * @author JustAMob
 */
import com.cjme.motorphsystem.dao.SalaryDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.cjme.motorphsystem.model.Salary;
import com.cjme.motorphsystem.util.DBConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAOImpl implements SalaryDAO {

    @Override
    public void addSalary(Salary salary) throws SQLException {
        String sql = "INSERT INTO salary (basic_salary, gross_semi_monthly_rate, hourly_rate) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBigDecimal(1, salary.getBasicSalary());
            stmt.setBigDecimal(2, salary.getGrossSemiMonthlyRate());
            stmt.setBigDecimal(3, salary.getHourlyRate());

            stmt.executeUpdate();
        }
    }

    @Override
    public Salary getSalaryById(int salaryId) throws SQLException {
        String sql = "SELECT * FROM salary WHERE salary_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, salaryId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Salary salary = new Salary();
                salary.setSalaryId(rs.getInt("salary_id"));
                salary.setBasicSalary(rs.getBigDecimal("basic_salary"));
                salary.setGrossSemiMonthlyRate(rs.getBigDecimal("gross_semi_monthly_rate"));
                salary.setHourlyRate(rs.getBigDecimal("hourly_rate"));
                return salary;
            }
        }

        return null;
    }
    public BigDecimal getHourlyRateBySalaryId(int salaryId) throws SQLException {
        String sql = "SELECT hourly_rate FROM Salary WHERE salary_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, salaryId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getBigDecimal("hourly_rate");
            }

        } catch (SQLException e) {
            throw e;
        }

        return BigDecimal.ZERO; // or null, depending on how you want to handle missing records
    }
    @Override
    public List<Salary> getAllSalaries() throws SQLException {
        List<Salary> salaries = new ArrayList<>();
        String sql = "SELECT * FROM Salary";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Salary salary = new Salary();
                salary.setSalaryId(rs.getInt("salary_id"));
                salary.setBasicSalary(rs.getBigDecimal("basic_salary"));
                salary.setGrossSemiMonthlyRate(rs.getBigDecimal("gross_semi_monthly_rate"));
                salary.setHourlyRate(rs.getBigDecimal("hourly_rate"));
                salaries.add(salary);
            }
        }

        return salaries;
    }

    @Override
    public void updateSalary(Salary salary) throws SQLException {
        String sql = "UPDATE Salary SET basic_salary = ?, gross_semi_monthly_rate = ?, hourly_rate = ? WHERE salary_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBigDecimal(1, salary.getBasicSalary());
            stmt.setBigDecimal(2, salary.getGrossSemiMonthlyRate());
            stmt.setBigDecimal(3, salary.getHourlyRate());
            stmt.setInt(4, salary.getSalaryId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteSalary(int salaryId) throws SQLException {
        String sql = "DELETE FROM Salary WHERE salary_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, salaryId);
            stmt.executeUpdate();
        }
    }
}
