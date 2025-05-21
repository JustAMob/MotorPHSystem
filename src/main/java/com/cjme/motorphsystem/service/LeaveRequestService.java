/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.service;

import com.cjme.motorphsystem.dao.LeaveRequestDAO;
import com.cjme.motorphsystem.model.LeaveRequest;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author JustAMob
 */

public class LeaveRequestService {

    private final LeaveRequestDAO leaveRequestDAO;

    public LeaveRequestService(LeaveRequestDAO leaveRequestDAO) {
        this.leaveRequestDAO = leaveRequestDAO;
    }

    // Apply for leave
    public void applyLeave(LeaveRequest request) throws SQLException {
        // Optional business logic:
        // - Check if dates are valid (start before end)
        // - Check leave balance
        // - Auto-approve if criteria are met

        if (request.getStartDate().after(request.getEndDate())) {
            throw new IllegalArgumentException("Start date must be before end date.");
        }

        // Set default status
        request.setStatus("PENDING");

        leaveRequestDAO.applyLeave(request);
    }

    // Get all leave requests for an employee
    public List<LeaveRequest> getLeaveRequestsByEmployee(int employeeId) throws SQLException {
        return leaveRequestDAO.getLeaveRequestsByEmployee(employeeId);
    }

    // Admin: View all leave requests
    public List<LeaveRequest> getAllLeaveRequests() throws SQLException {
        return leaveRequestDAO.getAllLeaveRequests();
    }

    // Admin: Approve or reject leave
    public void updateLeaveStatus(int requestId, String status) throws SQLException {
        if (!status.equalsIgnoreCase("APPROVED") && !status.equalsIgnoreCase("REJECTED")) {
            throw new IllegalArgumentException("Invalid status. Must be APPROVED or REJECTED.");
        }

        leaveRequestDAO.updateLeaveStatus(requestId, status);
    }
}

