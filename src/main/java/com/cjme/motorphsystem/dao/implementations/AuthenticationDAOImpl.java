/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.dao.AuthenticationDAO;
import com.cjme.motorphsystem.model.Authentication;
import com.cjme.motorphsystem.security.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONObject;
/**
 *
 * @author JustAMob
 */
public class AuthenticationDAOImpl implements AuthenticationDAO {
     private final Connection connection;

    public AuthenticationDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Authentication getAuthenticationByEmployeeId(int employeeId) throws SQLException {
        String sql = """
            SELECT a.employee_id, a.password_hash, a.last_login, a.failed_attempts, a.account_status_id,
                   r.role_id, r.role_name, r.access
            FROM Authentication a
            JOIN Roles r ON a.role_id = r.role_id
            WHERE a.employee_id = ?
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Role role = new Role(
                    rs.getInt("role_id"),
                    rs.getString("role_name"),
                    new JSONObject(rs.getString("access"))
                );

                return new Authentication(
                    rs.getInt("employee_id"),
                    rs.getString("password_hash"),
                    role,
                    rs.getTimestamp("last_login") != null ? rs.getTimestamp("last_login").toLocalDateTime() : null,
                    rs.getInt("failed_attempts"),
                    rs.getInt("account_status_id")
                );
            }
        }

        return null;
    }
}
