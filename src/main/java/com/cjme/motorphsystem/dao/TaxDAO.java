package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JustAMob
 */

public class TaxDAO {

    private final Connection connection;

    public TaxDAO() throws SQLException {
        this.connection = DBConnection.getConnection();
    }

    public double calculateTax(double salary) throws SQLException {
        String sql = "SELECT base_tax, excess_rate, excess_over FROM withholding_tax " +
                     "WHERE ? BETWEEN min_salary AND max_salary";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, salary);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double baseTax = rs.getDouble("base_tax");
                double excessRate = rs.getDouble("excess_rate");
                double excessOver = rs.getDouble("excess_over");

                double excess = salary - excessOver;
                return baseTax + (excess * excessRate);
            }
        }

        return 0.0;
    }
}

