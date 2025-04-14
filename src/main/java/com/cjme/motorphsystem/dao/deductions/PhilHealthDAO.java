package com.cjme.motorphsystem.dao.deductions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JustAMob
 */
public class PhilHealthDAO {

    private Connection connection;

    // Constructor to initialize the connection
    public PhilHealthDAO(Connection connection) {
        this.connection = connection;
    }

    // Method to fetch the premium rate from the database
    public double getPremiumRate() {
        double premiumRate = 0.0000;
        
        try {
            String sql = "SELECT premium_rate FROM philhealth WHERE philhealth_id = 1";  // Adjust the query if necessary
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                premiumRate = rs.getDouble("premium_rate");
            }

        } catch (SQLException e) {
        }

        return premiumRate;
    }
}