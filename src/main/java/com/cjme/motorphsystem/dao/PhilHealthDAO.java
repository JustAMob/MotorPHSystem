package com.cjme.motorphsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JustAMob
 */
public class PhilHealthDAO {

    private final Connection connection;

    public PhilHealthDAO(Connection connection) {
        this.connection = connection;
    }

    public double getPremiumRate(double salary) {
        double premiumRate = 0.0;

        try {
            String sql = """
                SELECT premium_rate 
                FROM philhealth_contribution     
                WHERE ? >= min_salary 
                ORDER BY min_salary DESC 
                LIMIT 1
            """;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, salary);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                premiumRate = rs.getDouble("premium_rate");
            }

        } catch (SQLException e) {
            // Consider logging this
            
        }

        return premiumRate;
    }
}