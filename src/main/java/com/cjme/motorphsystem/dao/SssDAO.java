/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JustAMob
 */

public class SssDAO {
    private Connection connection;

    public SssDAO(Connection connection) {
        this.connection = connection;
    }

    public SssDAO() {
    }

    public double getContributionForSalary(double salary) {
        double contribution = 0.0;
        try {
            String sql = "SELECT contribution FROM sss_contribution WHERE ? BETWEEN min_salary AND max_salary";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, salary);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                contribution = rs.getDouble("contribution");
            } else {
                // Check for max_salary = 'over' case
                sql = "SELECT contribution FROM sss_contribution WHERE max_salary = 99999999 AND ? >= min_salary";
                stmt = connection.prepareStatement(sql);
                stmt.setDouble(1, salary);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    contribution = rs.getDouble("contribution");
                }
            }

        } catch (SQLException e) {
        }

        return contribution;
    }
}

