/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.dao.EmployeeProfileDAO;
import com.cjme.motorphsystem.model.EmployeeProfile;
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
public class EmployeeProfileDAOImpl implements EmployeeProfileDAO {
     @Override
    public EmployeeProfile getEmployeeById(int employeeId) throws SQLException {
        String sql = "SELECT * FROM v_employee_profile WHERE employee_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employeeId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    EmployeeProfile emp = new EmployeeProfile();
                    emp.setEmployeeId(rs.getInt("employee_id"));
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    emp.setFullName(firstName + " " + lastName);
                    emp.setBirthday(rs.getDate("birthday"));
                    emp.setPhoneNumber(rs.getString("phone_number"));
                    emp.setFullAddress(rs.getString("full_address"));
                    emp.setDepartmentName(rs.getString("department_name"));
                    emp.setPositionName(rs.getString("position_name"));
                    emp.setSupervisorName(rs.getString("supervisor_name"));
                    emp.setStatusName(rs.getString("status_type"));
                    return emp;
                }
            }
        }
        return null;
    }

    @Override
public List<EmployeeProfile> getAllEmployees() throws SQLException {
    List<EmployeeProfile> list = new ArrayList<>();
    String sql = "SELECT * FROM v_employee_profile";
    try (Connection conn = DBConnection.getConnection();
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            EmployeeProfile emp = new EmployeeProfile();
            emp.setEmployeeId(rs.getInt("employee_id"));
            
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            emp.setFullName(firstName + " " + lastName);

            emp.setBirthday(rs.getDate("birthday"));
            emp.setPhoneNumber(rs.getString("phone_number"));
            emp.setFullAddress(rs.getString("full_address"));
            emp.setDepartmentName(rs.getString("department_name"));
            emp.setPositionName(rs.getString("position_name"));
            emp.setSupervisorName(rs.getString("supervisor_name"));
            emp.setStatusName(rs.getString("status_type"));

            list.add(emp); 
        }
    }
    return list;
}
}
