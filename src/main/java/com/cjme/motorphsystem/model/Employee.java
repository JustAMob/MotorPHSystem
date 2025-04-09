package com.cjme.motorphsystem.model;

import java.sql.Date;

/**
 *
 * @author JustAMob
 */
public class Employee {
    private final int employeeID;
    private String firstName;
    private String lastName;
    private java.sql.Date birthday;
    private String address;
    private String phoneNum;
    private final String sssNum;
    private final String philHealthNum;
    private final String pagibigNum;
    private final String tinNum;
    private String status;
    private String position;
    private String immediateSupervisor;

    public Employee(int employeeID, String firstName, String lastName, Date birthday, String address, String phoneNum, String sssNum, String philHealthNum, String pagibigNum, String tinNum,String status, String position, String immediateSupervisor) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address = address;
        this.phoneNum = phoneNum;
        this.sssNum = sssNum;
        this.philHealthNum = philHealthNum;
        this.pagibigNum = pagibigNum;
        this.tinNum = tinNum;
        this.status = status;
        this.position = position;
        this.immediateSupervisor = immediateSupervisor;
    }

    public Employee(String firstName, String lastName, Date birthday, String address, String phoneNum, String sssNum, String philHealthNum, String pagibigNum, String tinNum, String status, String position, String immediateSupervisor) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address = address;
        this.phoneNum = phoneNum;
        this.sssNum = sssNum;
        this.philHealthNum = philHealthNum;
        this.pagibigNum = pagibigNum;
        this.tinNum = tinNum;
        this.status = status;
        this.position = position;
        this.immediateSupervisor = immediateSupervisor;
        this.employeeID = 0;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getSssNum() {
        return sssNum;
    }

    public String getPhilHealthNum() {
        return philHealthNum;
    }

    public String getPagibigNum() {
        return pagibigNum;
    }

    public String getTinNum() {
        return tinNum;
    }

    public String getStatus() {
        return status;
    }

    public String getPosition() {
        return position;
    }

    public String getImmediateSupervisor() {
        return immediateSupervisor;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setImmediateSupervisor(String immediateSupervisor) {
        this.immediateSupervisor = immediateSupervisor;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", name='" + firstName + " " + lastName + '\'' +
                ", birthday=" + birthday +
                ", position=" + position +
                '}';
    }

    
    
    
    
    
}
