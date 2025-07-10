/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.dao.PositionDAO;
import com.cjme.motorphsystem.model.Position;
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
public class PositionDAOImpl implements PositionDAO {
    @Override
    public int addPosition(Position position) {
        String sql = "INSERT INTO position (position_name, department_id) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, position.getPositionName());
            stmt.setInt(2, position.getDepartmentId());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // return generated position_id
            }

        } catch (SQLException e) {
        }

        return -1;
    }

    @Override
    public Position getPositionById(int id) {
        String sql = "SELECT * FROM position WHERE position_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Position position = new Position();
                position.setPositionId(rs.getInt("position_id"));
                position.setPositionName(rs.getString("position_name"));
                position.setDepartmentId(rs.getInt("department_id"));
                return position;
            }

        } catch (SQLException e) {
        }

        return null;
    }

    @Override
    public List<Position> getAllPositions() {
        List<Position> list = new ArrayList<>();
        String sql = "SELECT * FROM position";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Position position = new Position();
                position.setPositionId(rs.getInt("position_id"));
                position.setPositionName(rs.getString("position_name"));
                position.setDepartmentId(rs.getInt("department_id"));
                list.add(position);
            }

        } catch (SQLException e) {
        }

        return list;
    }

    @Override
    public void updatePosition(Position position) {
        String sql = "UPDATE position SET position_name = ?, department_id = ? WHERE position_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, position.getPositionName());
            stmt.setInt(2, position.getDepartmentId());
            stmt.setInt(3, position.getPositionId());

            stmt.executeUpdate();

        } catch (SQLException e) {
        }
    }

    @Override
    public void deletePosition(int id) {
        String sql = "DELETE FROM position WHERE position_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
        }
    }

    public Map<String, Integer> getPositionNameIdMap() throws SQLException {
    Map<String, Integer> map = new HashMap<>();
    String sql = "SELECT position_id, position_name FROM position";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            map.put(rs.getString("position_name"), rs.getInt("position_id"));
        }
    }
    return map;
    }
}
