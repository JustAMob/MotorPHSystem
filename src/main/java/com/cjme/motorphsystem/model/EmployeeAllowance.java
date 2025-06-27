/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.model;

import java.math.BigDecimal;

/**
 *
 * @author JustAMob
 */

public class EmployeeAllowance {
    private int employeeId;
    private int allowanceTypeId;
    private BigDecimal amount;

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setAllowanceTypeId(int allowanceTypeId) {
        this.allowanceTypeId = allowanceTypeId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getAllowanceTypeId() {
        return allowanceTypeId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    
}