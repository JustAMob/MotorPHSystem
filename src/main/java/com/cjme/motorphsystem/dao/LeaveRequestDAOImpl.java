package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.LeaveRequest;
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
public class LeaveRequestDAOImpl implements LeaveRequestDAO {

    private boolean isAuthorized(String role) {
        return role.equalsIgnoreCase("admin") || 
               role.equalsIgnoreCase("hr") || 
               role.equalsIgnoreCase("supervisor");
    }

    @Override
    public void addLeaveRequest(LeaveRequest request, String role) {
        if (!isAuthorized(role)) {
            throw new SecurityException("Unauthorized to add leave requests.");
        }

        String sql = "INSERT INTO Leave_Request (employee_id, leave_type, leave_start, leave_end, reason, leave_status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, request.getEmployeeId());
            stmt.setString(2, request.getLeaveType());
            stmt.setDate(3, request.getLeaveStart());
            stmt.setDate(4, request.getLeaveEnd());
            stmt.setString(5, request.getReason());
            stmt.setString(6, request.getLeaveStatus());

            stmt.executeUpdate();

        } catch (SQLException e) {
        }
    }

    @Override
    public LeaveRequest getLeaveRequestById(int id) {
        String sql = "SELECT * FROM Leave_Request WHERE leave_request_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                LeaveRequest request = new LeaveRequest();
                request.setLeaveRequestId(rs.getInt("leave_request_id"));
                request.setEmployeeId(rs.getInt("employee_id"));
                request.setLeaveType(rs.getString("leave_type"));
                request.setLeaveStart(rs.getDate("leave_start"));
                request.setLeaveEnd(rs.getDate("leave_end"));
                request.setReason(rs.getString("reason"));
                request.setLeaveStatus(rs.getString("leave_status"));
                return request;
            }

        } catch (SQLException e) {
        }

        return null;
    }

    @Override
    public List<LeaveRequest> getAllLeaveRequests() {
        List<LeaveRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM Leave_Request";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                LeaveRequest request = new LeaveRequest();
                request.setLeaveRequestId(rs.getInt("leave_request_id"));
                request.setEmployeeId(rs.getInt("employee_id"));
                request.setLeaveType(rs.getString("leave_type"));
                request.setLeaveStart(rs.getDate("leave_start"));
                request.setLeaveEnd(rs.getDate("leave_end"));
                request.setReason(rs.getString("reason"));
                request.setLeaveStatus(rs.getString("leave_status"));
                list.add(request);
            }

        } catch (SQLException e) {
        }

        return list;
    }

    @Override
    public void updateLeaveRequest(LeaveRequest request, String role) {
        if (!isAuthorized(role)) {
            throw new SecurityException("Unauthorized to update leave requests.");
        }

        String sql = "UPDATE Leave_Request SET employee_id = ?, leave_type = ?, leave_start = ?, leave_end = ?, reason = ?, leave_status = ? WHERE leave_request_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, request.getEmployeeId());
            stmt.setString(2, request.getLeaveType());
            stmt.setDate(3, request.getLeaveStart());
            stmt.setDate(4, request.getLeaveEnd());
            stmt.setString(5, request.getReason());
            stmt.setString(6, request.getLeaveStatus());
            stmt.setInt(7, request.getLeaveRequestId());

            stmt.executeUpdate();

        } catch (SQLException e) {
        }
    }

    
    @Override
    public void deleteLeaveRequest(int id, String role) {
        if (!isAuthorized(role)) {
            throw new SecurityException("Unauthorized to delete leave requests.");
        }

        String sql = "DELETE FROM Leave_Request WHERE leave_request_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
        }
    }

   
}