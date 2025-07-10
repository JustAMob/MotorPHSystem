package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.dao.LeaveRequestDAO;
import com.cjme.motorphsystem.model.LeaveRequest;
import com.cjme.motorphsystem.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author JustAMob
 */
public class LeaveRequestDAOImpl implements LeaveRequestDAO {


    @Override
    public void addLeaveRequest(LeaveRequest request) {


        String sql = "INSERT INTO leave_request (employee_id, leave_type, leave_start, leave_end, reason, leave_status) VALUES (?, ?, ?, ?, ?, ?)";

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
        String sql = "SELECT * FROM leave_request WHERE leave_request_id = ?";
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
        String sql = "SELECT * FROM leave_request";

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
    
    /**
     *
     * @param requestId
     * @param newStatus
     */
    @Override
    public void updateLeaveRequestStatus(int requestId, String newStatus){
      

        String sql = "UPDATE leave_request SET leave_status = ? WHERE leave_request_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newStatus);
            stmt.setInt(2, requestId);
            stmt.executeUpdate();

        } catch (SQLException e) {
           
        }
    }
    
    @Override
    public void updateLeaveRequest(LeaveRequest request) {


        String sql = "UPDATE leave_request SET employee_id = ?, leave_type = ?, leave_start = ?, leave_end = ?, reason = ?, leave_status = ? WHERE leave_request_id = ?";

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
    public void deleteLeaveRequest(int id) {
       

        String sql = "DELETE FROM leave_request WHERE leave_request_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
        }
    }
    
    @Override 
    public List<LeaveRequest> searchLeaveRequests(Integer employeeId, String leaveType, String status, LocalDate startDate, LocalDate endDate) throws SQLException {
        List<LeaveRequest> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM leave_request WHERE 1=1");

       
        if (employeeId != null && employeeId > 0) {
            sql.append(" AND employee_id = ?");
        }
        if (leaveType != null && !leaveType.isEmpty() && !"All".equalsIgnoreCase(leaveType)) {
            sql.append(" AND leave_type = ?");
        }
        if (status != null && !status.isEmpty() && !"All".equalsIgnoreCase(status)) {
            sql.append(" AND leave_status = ?");
        }
        if (startDate != null) {
            sql.append(" AND leave_start >= ?");
        }
        if (endDate != null) {
            sql.append(" AND leave_end <= ?");
        }

        sql.append(" ORDER BY leave_start DESC, employee_id ASC");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            if (employeeId != null && employeeId > 0) {
                stmt.setInt(paramIndex++, employeeId);
            }
            if (leaveType != null && !leaveType.isEmpty() && !"All".equalsIgnoreCase(leaveType)) {
                stmt.setString(paramIndex++, leaveType);
            }
            if (status != null && !status.isEmpty() && !"All".equalsIgnoreCase(status)) {
                stmt.setString(paramIndex++, status);
            }
            if (startDate != null) {
                stmt.setDate(paramIndex++, Date.valueOf(startDate));
            }
            if (endDate != null) {
                stmt.setDate(paramIndex++, Date.valueOf(endDate));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToLeaveRequest(rs));
                }
            }

        } catch (SQLException e) {
            System.err.println("Database error during leave request search: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
        return list;
    }
    
    private LeaveRequest mapResultSetToLeaveRequest(ResultSet rs) throws SQLException {
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

   
}