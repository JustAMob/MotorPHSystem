package com.cjme.motorphsystem.model;

import java.sql.Date;

/**
 *
 * @author JustAMob
 */
public class EmployeeEntity {
    private int employeeId;
    private String firstName;
    private String lastName;
    private int addressId;
    private int phoneNumber;
    private int governmentId;
    private int departmentId;
    private int salaryId;
    private int supervisorId;
    private int statusId;
    private int positionId;
    private Date birthday;

    public EmployeeEntity() {
    }

    public EmployeeEntity(int employeeId, String firstName, String lastName, int addressId, int phoneNumber, int governmentId, int departmentId, int salaryId, int supervisorId, int statusId, int positionId, Date birthday) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressId = addressId;
        this.phoneNumber = phoneNumber;
        this.governmentId = governmentId;
        this.departmentId = departmentId;
        this.salaryId = salaryId;
        this.supervisorId = supervisorId;
        this.statusId = statusId;
        this.positionId = positionId;
        this.birthday = birthday;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAddressId() {
        return addressId;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getGovernmentId() {
        return governmentId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public int getSalaryId() {
        return salaryId;
    }

    public int getSupervisorId() {
        return supervisorId;
    }

    public int getStatusId() {
        return statusId;
    }

    public int getPositionId() {
        return positionId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGovernmentId(int governmentId) {
        this.governmentId = governmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    
    
    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeId +
                ", name='" + firstName + " " + lastName + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    
    
    
    
    
}
