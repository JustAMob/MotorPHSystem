/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.model;

import java.sql.Date;

/**
 *
 * @author JustAMob
 */
public class EmployeeProfile {
    private int employeeId;
    private String fullName;         //  first + last
    private String fullAddress;
    private String phoneNumber;
    private String departmentName;
    private String positionName;
    private String supervisorName;
    private String statusName;
    private Date birthday;

    public EmployeeProfile() {
    }

    public EmployeeProfile(int employeeId, String fullName, String fullAddress, String phoneNumber, String departmentName, String positionName, String supervisorName, String statusName, Date birthday) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.fullAddress = fullAddress;
        this.phoneNumber = phoneNumber;
        this.departmentName = departmentName;
        this.positionName = positionName;
        this.supervisorName = supervisorName;
        this.statusName = statusName;
        this.birthday = birthday;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getPositionName() {
        return positionName;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public String getStatusName() {
        return statusName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
}
