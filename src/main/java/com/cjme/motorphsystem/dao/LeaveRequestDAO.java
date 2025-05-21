/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.LeaveRequest;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author JustAMob
 */
public interface LeaveRequestDAO {
    void applyLeave(LeaveRequest request) throws SQLException;
    List<LeaveRequest> getLeaveRequestsByEmployee(int employeeId) throws SQLException;
    List<LeaveRequest> getAllLeaveRequests() throws SQLException;
    void updateLeaveStatus(int requestId, String status) throws SQLException;
    
}
