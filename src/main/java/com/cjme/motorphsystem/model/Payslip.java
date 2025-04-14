package com.cjme.motorphsystem.model;

import java.time.LocalDate;

/**
 *
 * @author JustAMob
 */
public class Payslip {
    private String employeeName;
    private String position;
    private LocalDate payrollDate;

    private double basicSalary;
    private double hourlyRate;

    private double riceAllowance;
    private double phoneAllowance;
    private double clothingAllowance;
    private double totalAllowance;

    private double sssDeduction;
    private double philhealthDeduction;
    private double pagibigDeduction;
    private double totalDeduction;

    private double tax;
    private double netPay;

    public String getEmployeeName() {
        return employeeName;
    }

    public String getPosition() {
        return position;
    }

    public LocalDate getPayrollDate() {
        return payrollDate;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getRiceAllowance() {
        return riceAllowance;
    }

    public double getPhoneAllowance() {
        return phoneAllowance;
    }

    public double getClothingAllowance() {
        return clothingAllowance;
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

    public double getNetPay() {
        return netPay;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPayrollDate(LocalDate payrollDate) {
        this.payrollDate = payrollDate;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setRiceAllowance(double riceAllowance) {
        this.riceAllowance = riceAllowance;
    }

    public void setPhoneAllowance(double phoneAllowance) {
        this.phoneAllowance = phoneAllowance;
    }

    public void setClothingAllowance(double clothingAllowance) {
        this.clothingAllowance = clothingAllowance;
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

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    @Override
    public String toString() {
        return "Payslip{" + "employeeName=" + employeeName + ", position=" + position + ", payrollDate=" + payrollDate + ", basicSalary=" + basicSalary + ", hourlyRate=" + hourlyRate + ", riceAllowance=" + riceAllowance + ", phoneAllowance=" + phoneAllowance + ", clothingAllowance=" + clothingAllowance + ", totalAllowance=" + totalAllowance + ", sssDeduction=" + sssDeduction + ", philhealthDeduction=" + philhealthDeduction + ", pagibigDeduction=" + pagibigDeduction + ", totalDeduction=" + totalDeduction + ", tax=" + tax + ", netPay=" + netPay + '}';
    }

    
}

