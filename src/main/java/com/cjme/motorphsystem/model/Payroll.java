/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.model;

/**
 *
 * @author JustAMob
 */
public class Payroll {
    private String payPeriodId;
    private int employeeNo;
    private String employeeFullName;
    private String position;
    private String department;
    private double grossIncome;
    private String socialSecurityNo;
    private double socialSecurityContribution;
    private String philhealthNo;
    private double philhealthContribution;
    private String pagibigNo;
    private double pagibigContribution;
    private String tin;
    private double withholdingTax;
    private double summaryDeductions;
    private double netPay;

    public Payroll() {
    }

    public Payroll(String payPeriodId, int employeeNo, String employeeFullName, String position, String department, double grossIncome, String socialSecurityNo, double socialSecurityContribution, String philhealthNo, double philhealthContribution, String pagibigNo, double pagibigContribution, String tin, double withholdingTax, double summaryDeductions, double netPay) {
        this.payPeriodId = payPeriodId;
        this.employeeNo = employeeNo;
        this.employeeFullName = employeeFullName;
        this.position = position;
        this.department = department;
        this.grossIncome = grossIncome;
        this.socialSecurityNo = socialSecurityNo;
        this.socialSecurityContribution = socialSecurityContribution;
        this.philhealthNo = philhealthNo;
        this.philhealthContribution = philhealthContribution;
        this.pagibigNo = pagibigNo;
        this.pagibigContribution = pagibigContribution;
        this.tin = tin;
        this.withholdingTax = withholdingTax;
        this.summaryDeductions = summaryDeductions;
        this.netPay = netPay;
    }

    public String getPayPeriodId() {
        return payPeriodId;
    }

    public void setPayPeriodId(String payPeriodId) {
        this.payPeriodId = payPeriodId;
    }

    public int getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(int employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(double grossIncome) {
        this.grossIncome = grossIncome;
    }

    public String getSocialSecurityNo() {
        return socialSecurityNo;
    }

    public void setSocialSecurityNo(String socialSecurityNo) {
        this.socialSecurityNo = socialSecurityNo;
    }

    public double getSocialSecurityContribution() {
        return socialSecurityContribution;
    }

    public void setSocialSecurityContribution(double socialSecurityContribution) {
        this.socialSecurityContribution = socialSecurityContribution;
    }

    public String getPhilhealthNo() {
        return philhealthNo;
    }

    public void setPhilhealthNo(String philhealthNo) {
        this.philhealthNo = philhealthNo;
    }

    public double getPhilhealthContribution() {
        return philhealthContribution;
    }

    public void setPhilhealthContribution(double philhealthContribution) {
        this.philhealthContribution = philhealthContribution;
    }

    public String getPagibigNo() {
        return pagibigNo;
    }

    public void setPagibigNo(String pagibigNo) {
        this.pagibigNo = pagibigNo;
    }

    public double getPagibigContribution() {
        return pagibigContribution;
    }

    public void setPagibigContribution(double pagibigContribution) {
        this.pagibigContribution = pagibigContribution;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public double getWithholdingTax() {
        return withholdingTax;
    }

    public void setWithholdingTax(double withholdingTax) {
        this.withholdingTax = withholdingTax;
    }

    public double getSummaryDeductions() {
        return summaryDeductions;
    }

    public void setSummaryDeductions(double summaryDeductions) {
        this.summaryDeductions = summaryDeductions;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }
}
