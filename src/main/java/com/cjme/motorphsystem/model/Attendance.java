package com.cjme.motorphsystem.model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author JustAMob
 */
public class Attendance {
    private int logID;
    private int employeeID;
    private Date logDate;
    private Time timeIn;
    private Time timeOut;

    public Attendance(int logID, int employeeID, Date logDate, Time timeIn, Time timeOut) {
        this.logID = logID;
        this.employeeID = employeeID;
        this.logDate = logDate;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    public Attendance(int employeeID, Date logDate, Time timeIn, Time timeOut) {
        this.employeeID = employeeID;
        this.logDate = logDate;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    public Attendance() {
    }

    public int getLogID() {
        return logID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public Date getLogDate() {
        return logDate;
    }

    public Time getTimeIn() {
        return timeIn;
    }

    public Time getTimeOut() {
        return timeOut;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public void setTimeIn(Time timeIn) {
        this.timeIn = timeIn;
    }

    public void setTimeOut(Time timeOut) {
        this.timeOut = timeOut;
    }
    
    @Override
    public String toString(){
        return "Attendance:{"+
                "attendanceID" + logID +
                "employeeID" + employeeID +
                "logdate" + logDate +
                "timeIn" + timeIn +
                "timeOut" + timeOut +
                '}';
    }
    
    
    
    
    
}
