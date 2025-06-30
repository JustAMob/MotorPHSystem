package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.deductions.PagibigContribution;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JustAMob
 */
public class PagibigDAO {

    private final Connection connection;

    
    public PagibigDAO(Connection connection) {
        this.connection = connection;
    }

    
    
    // Method to fetch the employee rate and employer rate based on salary
    public PagibigContribution getRatesForSalary(double salary) {
    PagibigContribution contribution = null;

    try {
        String sql = """
            SELECT employee_contribution_rate, employer_contribution_rate, contribution
            FROM pagibig_contribution
            WHERE ? BETWEEN min_salary AND max_salary
        """;

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setDouble(1, salary);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            contribution = new PagibigContribution(
                rs.getDouble("employee_contribution_rate"),
                rs.getDouble("employer_contribution_rate")
            );
        } else {
            // Fallback to catch high salaries
            sql = """
                SELECT employee_contribution_rate, employer_contribution_rate, contribution
                FROM pagibig_contribution
                WHERE max_salary = 999999 AND ? >= min_salary
            """;
            stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, salary);
            rs = stmt.executeQuery();

            if (rs.next()) {
                contribution = new PagibigContribution(
                    rs.getDouble("employee_contribution_rate"),
                    rs.getDouble("employer_contribution_rate")

                );
            }
        }
    } catch (SQLException e) {
        
    }

    return contribution;
    }
}
    
