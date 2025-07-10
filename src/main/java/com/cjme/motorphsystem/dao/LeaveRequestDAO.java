/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.LeaveRequest;
import java.util.List;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author JustAMob
 */
public interface LeaveRequestDAO {
    void addLeaveRequest(LeaveRequest request);
    LeaveRequest getLeaveRequestById(int id);
    List<LeaveRequest> getAllLeaveRequests();
    void updateLeaveRequest(LeaveRequest request);
    void deleteLeaveRequest(int id);
    public void updateLeaveRequestStatus(int requestId, String capitalize);
    
    List<LeaveRequest> searchLeaveRequests(Integer employeeId, String leaveType, String status, LocalDate startDate, LocalDate endDate) throws SQLException;
}
