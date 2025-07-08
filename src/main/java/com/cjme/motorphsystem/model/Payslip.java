package com.cjme.motorphsystem.model;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author JustAMob
 */
public class Payslip {
    private int payslipNo;
    private int employeeId;
    private Date periodStartDate;
    private Date periodEndDate;
    private String employeeName;
    private String employeePositionDepartment;
    private int monthCovered;
    private int yearCovered;
    
    private double monthlyRate;
    private double hourlyRate;

    private double regularHours;
    private double overtimeHours;
    private double totalHoursWorked;
    
    private double overtimeIncome;
    private double grossIncome;
    
    
    private double riceSubsidy;
    private double phoneAllowance;
    private double clothingAllowance;
    private double totalAllowance;
    
    private double socialSecuritySystem;
    private double pagibig;
    private double philhealth;
    private double witholdingtax;
    private double totalDeduction;

    private double summaryGross;
    private double summaryBenefits;
    private double summaryDeductions;
    private double takeHomePay;

    public Payslip() {
    }

    public Payslip(int payslipNo, int employeeId, Date periodStartDate, Date periodEndDate, String employeeName, String employeePositionDepartment, int monthCovered, int yearCovered, double monthlyRate, double hourlyRate, double regularHours, double overtimeHours, double totalHoursWorked, double overtimeIncome, double grossIncome, double riceSubsidy, double phoneAllowance, double clothingAllowance, double totalAllowance, double socialSecuritySystem, double pagibig, double philhealth, double witholdingtax, double totalDeduction, double summaryGross, double summaryBenefits, double summaryDeductions, double takeHomePay) {
        this.payslipNo = payslipNo;
        this.employeeId = employeeId;
        this.periodStartDate = periodStartDate;
        this.periodEndDate = periodEndDate;
        this.employeeName = employeeName;
        this.employeePositionDepartment = employeePositionDepartment;
        this.monthCovered = monthCovered;
        this.yearCovered = yearCovered;
        this.monthlyRate = monthlyRate;
        this.hourlyRate = hourlyRate;
        this.regularHours = regularHours;
        this.overtimeHours = overtimeHours;
        this.totalHoursWorked = totalHoursWorked;
        this.overtimeIncome = overtimeIncome;
        this.grossIncome = grossIncome;
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
        this.totalAllowance = totalAllowance;
        this.socialSecuritySystem = socialSecuritySystem;
        this.pagibig = pagibig;
        this.philhealth = philhealth;
        this.witholdingtax = witholdingtax;
        this.totalDeduction = totalDeduction;
        this.summaryGross = summaryGross;
        this.summaryBenefits = summaryBenefits;
        this.summaryDeductions = summaryDeductions;
        this.takeHomePay = takeHomePay;
    }

    public int getPayslipNo() {
        return payslipNo;
    }

    public void setPayslipNo(int payslipNo) {
        this.payslipNo = payslipNo;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getPeriodStartDate() {
        return periodStartDate;
    }

    public void setPeriodStartDate(Date periodStartDate) {
        this.periodStartDate = periodStartDate;
    }

    public Date getPeriodEndDate() {
        return periodEndDate;
    }

    public void setPeriodEndDate(Date periodEndDate) {
        this.periodEndDate = periodEndDate;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePositionDepartment() {
        return employeePositionDepartment;
    }

    public void setEmployeePositionDepartment(String employeePositionDepartment) {
        this.employeePositionDepartment = employeePositionDepartment;
    }

    public int getMonthCovered() {
        return monthCovered;
    }

    public void setMonthCovered(int monthCovered) {
        this.monthCovered = monthCovered;
    }

    public int getYearCovered() {
        return yearCovered;
    }

    public void setYearCovered(int yearCovered) {
        this.yearCovered = yearCovered;
    }

    public double getMonthlyRate() {
        return monthlyRate;
    }

    public void setMonthlyRate(double monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getRegularHours() {
        return regularHours;
    }

    public void setRegularHours(double regularHours) {
        this.regularHours = regularHours;
    }

    public double getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public double getTotalHoursWorked() {
        return totalHoursWorked;
    }

    public void setTotalHoursWorked(double totalHoursWorked) {
        this.totalHoursWorked = totalHoursWorked;
    }

    public double getOvertimeIncome() {
        return overtimeIncome;
    }

    public void setOvertimeIncome(double overtimeIncome) {
        this.overtimeIncome = overtimeIncome;
    }

    public double getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(double grossIncome) {
        this.grossIncome = grossIncome;
    }

    public double getRiceSubsidy() {
        return riceSubsidy;
    }

    public void setRiceSubsidy(double riceSubsidy) {
        this.riceSubsidy = riceSubsidy;
    }

    public double getPhoneAllowance() {
        return phoneAllowance;
    }

    public void setPhoneAllowance(double phoneAllowance) {
        this.phoneAllowance = phoneAllowance;
    }

    public double getClothingAllowance() {
        return clothingAllowance;
    }

    public void setClothingAllowance(double clothingAllowance) {
        this.clothingAllowance = clothingAllowance;
    }

    public double getTotalAllowance() {
        return totalAllowance;
    }

    public void setTotalAllowance(double totalAllowance) {
        this.totalAllowance = totalAllowance;
    }

    public double getSocialSecuritySystem() {
        return socialSecuritySystem;
    }

    public void setSocialSecuritySystem(double socialSecuritySystem) {
        this.socialSecuritySystem = socialSecuritySystem;
    }

    public double getPagibig() {
        return pagibig;
    }

    public void setPagibig(double pagibig) {
        this.pagibig = pagibig;
    }

    public double getPhilhealth() {
        return philhealth;
    }

    public void setPhilhealth(double philhealth) {
        this.philhealth = philhealth;
    }

    public double getWitholdingtax() {
        return witholdingtax;
    }

    public void setWitholdingtax(double witholdingtax) {
        this.witholdingtax = witholdingtax;
    }

    public double getTotalDeduction() {
        return totalDeduction;
    }

    public void setTotalDeduction(double totalDeduction) {
        this.totalDeduction = totalDeduction;
    }

    public double getSummaryGross() {
        return summaryGross;
    }

    public void setSummaryGross(double summaryGross) {
        this.summaryGross = summaryGross;
    }

    public double getSummaryBenefits() {
        return summaryBenefits;
    }

    public void setSummaryBenefits(double summaryBenefits) {
        this.summaryBenefits = summaryBenefits;
    }

    public double getSummaryDeductions() {
        return summaryDeductions;
    }

    public void setSummaryDeductions(double summaryDeductions) {
        this.summaryDeductions = summaryDeductions;
    }

    public double getTakeHomePay() {
        return takeHomePay;
    }

    public void setTakeHomePay(double takeHomePay) {
        this.takeHomePay = takeHomePay;
    }

    
}

