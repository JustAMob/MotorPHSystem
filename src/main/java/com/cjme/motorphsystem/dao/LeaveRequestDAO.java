/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.LeaveRequest;
import java.util.List;

/**
 *
 * @author JustAMob
 */
public interface LeaveRequestDAO {
    void addLeaveRequest(LeaveRequest request, String role);
    LeaveRequest getLeaveRequestById(int id);
    List<LeaveRequest> getAllLeaveRequests();
    void updateLeaveRequest(LeaveRequest request, String role);
    void deleteLeaveRequest(int id, String role);
}
