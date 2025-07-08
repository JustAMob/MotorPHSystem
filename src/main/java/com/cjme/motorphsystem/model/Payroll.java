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
public class Payroll {
    private int payPeriodId;
    private int employeeId;
    private String employeeFullName;
    private String positon;
    private String department;
    private String grossIncome;
    private int socialSecurityNo;
    private BigDecimal socialSecurityContribution;
    private int philhealthNo;
    private BigDecimal philhealthContribution;
    private int pagibigNo;
    private BigDecimal pabigibContribution;
    private int tin;
    private BigDecimal withholdingTax;
    private BigDecimal netPay;

    public Payroll() {
    }

    public Payroll(int payPeriodId, int employeeId, String employeeFullName, String positon, String department, String grossIncome, int socialSecurityNo, BigDecimal socialSecurityContribution, int philhealthNo, BigDecimal philhealthContribution, int pagibigNo, BigDecimal pabigibContribution, int tin, BigDecimal withholdingTax, BigDecimal netPay) {
        this.payPeriodId = payPeriodId;
        this.employeeId = employeeId;
        this.employeeFullName = employeeFullName;
        this.positon = positon;
        this.department = department;
        this.grossIncome = grossIncome;
        this.socialSecurityNo = socialSecurityNo;
        this.socialSecurityContribution = socialSecurityContribution;
        this.philhealthNo = philhealthNo;
        this.philhealthContribution = philhealthContribution;
        this.pagibigNo = pagibigNo;
        this.pabigibContribution = pabigibContribution;
        this.tin = tin;
        this.withholdingTax = withholdingTax;
        this.netPay = netPay;
    }

    public int getPayPeriodId() {
        return payPeriodId;
    }

    public void setPayPeriodId(int payPeriodId) {
        this.payPeriodId = payPeriodId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    public String getPositon() {
        return positon;
    }

    public void setPositon(String positon) {
        this.positon = positon;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(String grossIncome) {
        this.grossIncome = grossIncome;
    }

    public int getSocialSecurityNo() {
        return socialSecurityNo;
    }

    public void setSocialSecurityNo(int socialSecurityNo) {
        this.socialSecurityNo = socialSecurityNo;
    }

    public BigDecimal getSocialSecurityContribution() {
        return socialSecurityContribution;
    }

    public void setSocialSecurityContribution(BigDecimal socialSecurityContribution) {
        this.socialSecurityContribution = socialSecurityContribution;
    }

    public int getPhilhealthNo() {
        return philhealthNo;
    }

    public void setPhilhealthNo(int philhealthNo) {
        this.philhealthNo = philhealthNo;
    }

    public BigDecimal getPhilhealthContribution() {
        return philhealthContribution;
    }

    public void setPhilhealthContribution(BigDecimal philhealthContribution) {
        this.philhealthContribution = philhealthContribution;
    }

    public int getPagibigNo() {
        return pagibigNo;
    }

    public void setPagibigNo(int pagibigNo) {
        this.pagibigNo = pagibigNo;
    }

    public BigDecimal getPabigibContribution() {
        return pabigibContribution;
    }

    public void setPabigibContribution(BigDecimal pabigibContribution) {
        this.pabigibContribution = pabigibContribution;
    }

    public int getTin() {
        return tin;
    }

    public void setTin(int tin) {
        this.tin = tin;
    }

    public BigDecimal getWithholdingTax() {
        return withholdingTax;
    }

    public void setWithholdingTax(BigDecimal withholdingTax) {
        this.withholdingTax = withholdingTax;
    }

    public BigDecimal getNetPay() {
        return netPay;
    }

    public void setNetPay(BigDecimal netPay) {
        this.netPay = netPay;
    }
    
    
    
}
