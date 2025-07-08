/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.dao.EmploymentStatusDAO;
import com.cjme.motorphsystem.model.EmploymentStatus;
import com.cjme.motorphsystem.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JustAMob
 */
public class EmploymentStatusDAOImpl implements EmploymentStatusDAO {
    
     @Override
    public int addStatus(EmploymentStatus status) {
        String sql = "INSERT INTO employment_status (status_type, status_description) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, status.getStatusType());
            stmt.setString(2, status.getStatusDescription());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // return generated status_id
            }

        } catch (SQLException e) {
        }

        return -1;
    }

    @Override
    public EmploymentStatus getStatusById(int id) {
        String sql = "SELECT * FROM employment_status WHERE status_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                EmploymentStatus status = new EmploymentStatus();
                status.setStatusId(rs.getInt("status_id"));
                status.setStatusType(rs.getString("status_type"));
                status.setStatusDescription(rs.getString("status_description"));
                return status;
            }

        } catch (SQLException e) {
        }

        return null;
    }

    @Override
    public List<EmploymentStatus> getAllStatuses() {
        List<EmploymentStatus> list = new ArrayList<>();
        String sql = "SELECT * FROM employment_status";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                EmploymentStatus status = new EmploymentStatus();
                status.setStatusId(rs.getInt("status_id"));
                status.setStatusType(rs.getString("status_type"));
                status.setStatusDescription(rs.getString("status_description"));
                list.add(status);
            }

        } catch (SQLException e) {
        }

        return list;
    }

    @Override
    public void updateStatus(EmploymentStatus status) {
        String sql = "UPDATE employment_status SET status_type = ?, status_description = ? WHERE status_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status.getStatusType());
            stmt.setString(2, status.getStatusDescription());
            stmt.setInt(3, status.getStatusId());
            stmt.executeUpdate();

        } catch (SQLException e) {
        }
    }

    @Override
    public void deleteStatus(int id) {
        String sql = "DELETE FROM employment_status WHERE status_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
        }
    }
}
