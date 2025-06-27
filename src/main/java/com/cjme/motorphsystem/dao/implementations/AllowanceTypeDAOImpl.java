package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.model.AllowanceType;
import com.cjme.motorphsystem.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.cjme.motorphsystem.dao.AllowanceTypeDAO;
/**
 *
 * @author JustAMob
 */
public class AllowanceTypeDAOImpl implements AllowanceTypeDAO {
    private final Connection conn;

    public AllowanceTypeDAOImpl() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public void addAllowanceType(AllowanceType allowance) throws SQLException {
        String sql = "INSERT INTO allowance_type (code, category, description, amount) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, allowance.getCode());
            stmt.setString(2, allowance.getCategory());
            stmt.setString(3, allowance.getDescription());
            stmt.setBigDecimal(4, allowance.getAmount());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<AllowanceType> getAllAllowanceTypes() throws SQLException {
        List<AllowanceType> list = new ArrayList<>();
        String sql = "SELECT * FROM allowance_type";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                AllowanceType a = new AllowanceType();
                a.setAllowanceTypeId(rs.getInt("allowance_type_id"));
                a.setCode(rs.getString("code"));
                a.setCategory(rs.getString("category"));
                a.setDescription(rs.getString("description"));
                a.setAmount(rs.getBigDecimal("amount"));
                list.add(a);
            }
        }
        return list;
    }

    @Override
    public AllowanceType getAllowanceTypeById(int id) throws SQLException {
        String sql = "SELECT * FROM allowance_type WHERE allowance_type_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                AllowanceType a = new AllowanceType();
                a.setAllowanceTypeId(rs.getInt("allowance_type_id"));
                a.setCode(rs.getString("code"));
                a.setCategory(rs.getString("category"));
                a.setDescription(rs.getString("description"));
                a.setAmount(rs.getBigDecimal("amount"));
                return a;
            }
        }
        return null;
    }
    
    @Override
    public void updateAllowanceType(AllowanceType allowance) throws SQLException {
        String sql = """
            UPDATE allowance_type 
            SET code = ?, category = ?, description = ?, amount = ?
            WHERE allowance_type_id = ?
        """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, allowance.getCode());
            stmt.setString(2, allowance.getCategory());
            stmt.setString(3, allowance.getDescription());
            stmt.setBigDecimal(4, allowance.getAmount());
            stmt.setInt(5, allowance.getAllowanceTypeId());
            stmt.executeUpdate();
        }
    }
}