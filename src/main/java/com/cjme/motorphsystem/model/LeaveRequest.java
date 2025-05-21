package com.cjme.motorphsystem.model;

import java.sql.Date;

/**
 *
 * @author JustAMob
 */
public class LeaveRequest {
    private int leaveID;
    private int employeeID;
    private String leaveType;
    private Date startDate;
    private Date endDate;
    private String status;
    private String reason;
    private Date dateRequested;

    public LeaveRequest() {
    }

    public LeaveRequest(int employeeID, String leaveType, Date startDate, Date endDate, String status, String reason, Date dateRequested) {
        this.employeeID = employeeID;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.reason = reason;
        this.dateRequested = dateRequested;
    }
    
    

    public int getLeaveID() {
        return leaveID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public Date getDateRequested() {
        return dateRequested;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setDateRequested(Date dateRequested) {
        this.dateRequested = dateRequested;
    }

    public void setLeaveID(int leaveID) {
        this.leaveID = leaveID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
    
    
    
    
}
