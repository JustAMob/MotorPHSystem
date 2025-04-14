package com.cjme.motorphsystem.model;

import java.time.LocalDate;

/**
 *
 * @author JustAMob
 */
public class Salary {
    private int payrollID;
    private int employeeID;
    private LocalDate payrollDate;

    private double bscSalary;
    private double hourlyRate;

    private double totalAllowance;

    private double sssDeduction;
    private double philhealthDeduction;
    private double pagibigDeduction;
    private double totalDeduction;

    private double tax;
    private double netpay;

    public Salary() {
    }

    public int getPayrollID() {
        return payrollID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public LocalDate getPayrollDate() {
        return payrollDate;
    }

    public double getBscSalary() {
        return bscSalary;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getTotalAllowance() {
        return totalAllowance;
    }

    public double getSssDeduction() {
        return sssDeduction;
    }

    public double getPhilhealthDeduction() {
        return philhealthDeduction;
    }

    public double getPagibigDeduction() {
        return pagibigDeduction;
    }

    public double getTotalDeduction() {
        return totalDeduction;
    }

    public double getTax() {
        return tax;
    }

    public double getNetpay() {
        return netpay;
    }

    public void setPayrollDate(LocalDate payrollDate) {
        this.payrollDate = payrollDate;
    }

    public void setBscSalary(double bscSalary) {
        this.bscSalary = bscSalary;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setTotalAllowance(double totalAllowance) {
        this.totalAllowance = totalAllowance;
    }

    public void setSssDeduction(double sssDeduction) {
        this.sssDeduction = sssDeduction;
    }

    public void setPhilhealthDeduction(double philhealthDeduction) {
        this.philhealthDeduction = philhealthDeduction;
    }

    public void setPagibigDeduction(double pagibigDeduction) {
        this.pagibigDeduction = pagibigDeduction;
    }

    public void setTotalDeduction(double totalDeduction) {
        this.totalDeduction = totalDeduction;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void setNetpay(double netpay) {
        this.netpay = netpay;
    }
    
    
    

    
}
