package com.cjme.motorphsystem.model;

import java.sql.Date;

/**
 *
 * @author JustAMob
 */
public class LeaveRequest {
    private int leaveRequestId;
    private int employeeId;
    private String leaveType;
    private Date leaveStart;
    private Date leaveEnd;
    private String reason;
    private String leaveStatus; // Approved, Denied, Pending

    public LeaveRequest() {
    }

    public LeaveRequest(int leaveRequestId, int employeeId, String leaveType, Date leaveStart, Date leaveEnd, String reason, String leaveStatus) {
        this.leaveRequestId = leaveRequestId;
        this.employeeId = employeeId;
        this.leaveType = leaveType;
        this.leaveStart = leaveStart;
        this.leaveEnd = leaveEnd;
        this.reason = reason;
        this.leaveStatus = leaveStatus;
    }

    public void setLeaveRequestId(int leaveRequestId) {
        this.leaveRequestId = leaveRequestId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public void setLeaveStart(Date leaveStart) {
        this.leaveStart = leaveStart;
    }

    public void setLeaveEnd(Date leaveEnd) {
        this.leaveEnd = leaveEnd;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public int getLeaveRequestId() {
        return leaveRequestId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public Date getLeaveStart() {
        return leaveStart;
    }

    public Date getLeaveEnd() {
        return leaveEnd;
    }

    public String getReason() {
        return reason;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

}
    

