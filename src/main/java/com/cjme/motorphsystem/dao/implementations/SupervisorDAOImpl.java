/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.dao.SupervisorDAO;
import com.cjme.motorphsystem.model.Supervisor;
import com.cjme.motorphsystem.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author JustAMob
 */
public class SupervisorDAOImpl implements SupervisorDAO {
    
    @Override
    public int addSupervisor(Supervisor supervisor) {
        String sql = "INSERT INTO supervisor (name) VALUES (?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, supervisor.getName());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // return generated supervisor_id
            }

        } catch (SQLException e) {
        }

        return -1;
    }

    @Override
    public Supervisor getSupervisorById(int id) {
        String sql = "SELECT * FROM supervisor WHERE supervisor_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Supervisor supervisor = new Supervisor();
                supervisor.setSupervisorId(rs.getInt("supervisor_id"));
                supervisor.setName(rs.getString("name"));
                return supervisor;
            }

        } catch (SQLException e) {
        }

        return null;
    }

    @Override
    public List<Supervisor> getAllSupervisors() {
        List<Supervisor> list = new ArrayList<>();
        String sql = "SELECT * FROM supervisor";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Supervisor supervisor = new Supervisor();
                supervisor.setSupervisorId(rs.getInt("supervisor_id"));
                supervisor.setName(rs.getString("name"));
                list.add(supervisor);
            }

        } catch (SQLException e) {
        }

        return list;
    }

    @Override
    public void updateSupervisor(Supervisor supervisor) {
        String sql = "UPDATE supervisor SET name = ? WHERE supervisor_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, supervisor.getName());
            stmt.setInt(2, supervisor.getSupervisorId());
            stmt.executeUpdate();

        } catch (SQLException e) {
        }
    }

    @Override
    public void deleteSupervisor(int id) {
        String sql = "DELETE FROM supervisor WHERE supervisor_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
        }
    }
    
    public Map<String, Integer> getSupervisorNameIdMap() throws SQLException {
    Map<String, Integer> map = new HashMap<>();
    String sql = "SELECT superviso_id, name FROM supervisor";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            map.put(rs.getString("name"), rs.getInt("supervisor_id"));
        }
    }
    return map;
    }
}
