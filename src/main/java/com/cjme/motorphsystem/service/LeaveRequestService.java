/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.service;

import com.cjme.motorphsystem.dao.LeaveRequestDAO;
import com.cjme.motorphsystem.model.LeaveRequest;
import java.sql.SQLException;
import java.time.LocalDate;
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

    /**  
     * Employee applies for leave.  
     * @param request
     * @throws IllegalArgumentException if dates are invalid  
     * @throws SQLException on DB errors  
     */
    public void applyLeave(LeaveRequest request) throws SQLException {
        // 1. Validate dates
        if (request.getLeaveStart().after(request.getLeaveEnd())) {
            throw new IllegalArgumentException("Start date must be on or before end date.");
        }

        // 2. Default status
        request.setLeaveStatus("Pending");

        // Call the DAO to persist the leave request
        leaveRequestDAO.addLeaveRequest(request);

    }
    
   
    /** Admin/HR: view all leave requests.
     * @return 
     * @throws java.sql.SQLException */
    public List<LeaveRequest> getAllLeaveRequests() throws SQLException {
        return leaveRequestDAO.getAllLeaveRequests();
    }
    
    public List<LeaveRequest> searchLeaveRequests (Integer employeeId, String leaveType, String status, LocalDate startDate, LocalDate endDate) throws SQLException {
        
    return leaveRequestDAO.searchLeaveRequests(employeeId, leaveType, status, startDate, endDate);
    
    }

    /** Admin/HR/Supervisor: approve or deny a request.
     * @param requestId
     * @param newStatus
     * @throws java.sql.SQLException */
    
    public void updateLeaveStatus(int requestId, String newStatus) throws SQLException {
        String normalized = newStatus.trim().toLowerCase();
        if (!normalized.equals("approved") && !normalized.equals("denied") && !normalized.equals("pending")) {
            throw new IllegalArgumentException("Status must be one of: Approved, Denied, Pending");
        }
        leaveRequestDAO.updateLeaveRequestStatus(requestId, capitalize(normalized));
    }
    
    /**
     * Admin/HR: Deletes a leave request by its ID.
     * @param requestId The ID of the leave request to delete.
     * @throws SQLException on database errors.
     */
    public void deleteLeaveRequest(int requestId) throws SQLException {
        leaveRequestDAO.deleteLeaveRequest(requestId);
    }

    /**
     * Admin/HR: Updates all fields of an existing leave request.
     * @param request The LeaveRequest object with updated details, including its ID.
     * @throws SQLException on database errors.
     */
    public void updateLeaveRequest(LeaveRequest request) throws SQLException {
        leaveRequestDAO.updateLeaveRequest(request);
    }


    /** Helper to capitalize first letter. */
    private String capitalize(String s) {
        return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
    }
    }

