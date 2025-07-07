/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.dao.GovernmentIdDAO;
import com.cjme.motorphsystem.model.GovernmentID;
import com.cjme.motorphsystem.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author JustAMob
 */
public class GovernmentIdDAOImpl implements GovernmentIdDAO {
    
    @Override
    public int addGovernmentId(GovernmentID gov) {
        String sql = "INSERT INTO government_id_table (employee_id, sss_id, pagibig_id, philhealth_id, tin_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, gov.getEmployeeId());
            stmt.setString(2, gov.getSssId());
            stmt.setString(3, gov.getPagibigId());
            stmt.setString(4, gov.getPhilhealthId());
            stmt.setString(5, gov.getTinId());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // return generated government_id
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public GovernmentID getGovernmentIdByEmployeeId(int employeeId) {
        String sql = "SELECT * FROM government_id_table WHERE employee_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                GovernmentID gov = new GovernmentID();
                gov.setGovernmentId(rs.getInt("government_id"));
                gov.setEmployeeId(rs.getInt("employee_id"));
                gov.setSssId(rs.getString("sss_id"));
                gov.setPagibigId(rs.getString("pagibig_id"));
                gov.setPhilhealthId(rs.getString("philhealth_id"));
                gov.setTinId(rs.getString("tin_id"));
                return gov;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateGovernmentId(GovernmentID gov) {
        String sql = "UPDATE government_id_table SET sss_id = ?, pagibig_id = ?, philhealth_id = ?, tin_id = ? WHERE government_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, gov.getSssId());
            stmt.setString(2, gov.getPagibigId());
            stmt.setString(3, gov.getPhilhealthId());
            stmt.setString(4, gov.getTinId());
            stmt.setInt(5, gov.getGovernmentId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGovernmentId(int governmentId) {
        String sql = "DELETE FROM government_id_table WHERE government_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, governmentId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
