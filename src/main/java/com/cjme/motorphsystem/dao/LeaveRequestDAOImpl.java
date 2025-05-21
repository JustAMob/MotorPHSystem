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
    private final Connection connection;

    public LeaveRequestDAOImpl() throws SQLException {
        this.connection = DBConnection.getConnection();
    }

    @Override
    public void applyLeave(LeaveRequest request) throws SQLException {
        String sql = "INSERT INTO leave_requests (employee_id, leave_type, start_date, end_date, reason, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, request.getEmployeeID());
            stmt.setString(2, request.getLeaveType());
            stmt.setDate(3, request.getStartDate());
            stmt.setDate(4, request.getEndDate());
            stmt.setString(5, request.getReason());
            stmt.setString(6, request.getStatus());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<LeaveRequest> getLeaveRequestsByEmployee(int employeeId) throws SQLException {
        String sql = "SELECT * FROM leave_requests WHERE employee_id = ?";
        List<LeaveRequest> requests = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                requests.add(mapRowToLeaveRequest(rs));
            }
        }

        return requests;
    }

    @Override
    public List<LeaveRequest> getAllLeaveRequests() throws SQLException {
        String sql = "SELECT * FROM leave_requests";
        List<LeaveRequest> requests = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                requests.add(mapRowToLeaveRequest(rs));
            }
        }

        return requests;
    }

    @Override
    public void updateLeaveStatus(int requestId, String status) throws SQLException {
        String sql = "UPDATE leave_requests SET status = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, requestId);
            stmt.executeUpdate();
        }
    }

    private LeaveRequest mapRowToLeaveRequest(ResultSet rs) throws SQLException {
        LeaveRequest request = new LeaveRequest();
        request.setLeaveID(rs.getInt("id"));
        request.setEmployeeID(rs.getInt("employee_id"));
        request.setLeaveType(rs.getString("leave_type"));
        request.setStartDate(rs.getDate("start_date"));
        request.setEndDate(rs.getDate("end_date"));
        request.setReason(rs.getString("reason"));
        request.setStatus(rs.getString("status"));
        return request;
    }
    
}
