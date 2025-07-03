package com.cjme.motorphsystem.model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author JustAMob
 */
public class Payslip {
private int payslipNo;
    private int employeeId;
    private String employeeName;
    private Date periodStartDate;
    private Date periodEndDate;
    private String employeePositionDepartment;
    private BigDecimal monthlyRate;
    private BigDecimal hourlyRate;
    private BigDecimal hoursWorked;
    private BigDecimal overtimeIncome;
    private BigDecimal grossIncome;
    private BigDecimal riceSubsidy;
    private BigDecimal phoneAllowance;
    private BigDecimal clothingAllowance;
    private BigDecimal benefitsTotal;
    private BigDecimal socialSecuritySystem;
    private BigDecimal philhealth;
    private BigDecimal pagibig;
    private BigDecimal withholdingTax;
    private BigDecimal totalDeductions;
    private BigDecimal summaryGross;
    private BigDecimal summaryBenefits;
    private BigDecimal summaryDeductions;
    private BigDecimal takeHomePay; 

    public void setPayslipNo(int payslipNo) {
        this.payslipNo = payslipNo;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setPeriodStartDate(Date periodStartDate) {
        this.periodStartDate = periodStartDate;
    }

    public void setPeriodEndDate(Date periodEndDate) {
        this.periodEndDate = periodEndDate;
    }

    public void setEmployeePositionDepartment(String employeePositionDepartment) {
        this.employeePositionDepartment = employeePositionDepartment;
    }

    public void setMonthlyRate(BigDecimal monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public void setHourlyRate(BigDecimal dailyRate) {
        this.hourlyRate = dailyRate;
    }

    public void setHoursWorked(BigDecimal hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void setOvertimeIncome(BigDecimal overtimeIncome) {
        this.overtimeIncome = overtimeIncome;
    }

    public void setGrossIncome(BigDecimal grossIncome) {
        this.grossIncome = grossIncome;
    }

    public void setRiceSubsidy(BigDecimal riceSubsidy) {
        this.riceSubsidy = riceSubsidy;
    }

    public void setPhoneAllowance(BigDecimal phoneAllowance) {
        this.phoneAllowance = phoneAllowance;
    }

    public void setClothingAllowance(BigDecimal clothingAllowance) {
        this.clothingAllowance = clothingAllowance;
    }

    public void setBenefitsTotal(BigDecimal benefitsTotal) {
        this.benefitsTotal = benefitsTotal;
    }

    public void setSocialSecuritySystem(BigDecimal socialSecuritySystem) {
        this.socialSecuritySystem = socialSecuritySystem;
    }

    public void setPhilhealth(BigDecimal philhealth) {
        this.philhealth = philhealth;
    }

    public void setPagibig(BigDecimal pagibig) {
        this.pagibig = pagibig;
    }

    public void setWithholdingTax(BigDecimal withholdingTax) {
        this.withholdingTax = withholdingTax;
    }

    public void setTotalDeductions(BigDecimal totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public void setSummaryGross(BigDecimal summaryGross) {
        this.summaryGross = summaryGross;
    }

    public void setSummaryBenefits(BigDecimal summaryBenefits) {
        this.summaryBenefits = summaryBenefits;
    }

    public void setSummaryDeductions(BigDecimal summaryDeductions) {
        this.summaryDeductions = summaryDeductions;
    }

    public void setTakeHomePay(BigDecimal takeHomePay) {
        this.takeHomePay = takeHomePay;
    }

    public int getPayslipNo() {
        return payslipNo;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Date getPeriodStartDate() {
        return periodStartDate;
    }

    public Date getPeriodEndDate() {
        return periodEndDate;
    }

    public String getEmployeePositionDepartment() {
        return employeePositionDepartment;
    }

    public BigDecimal getMonthlyRate() {
        return monthlyRate;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public BigDecimal getHoursWorked() {
        return hoursWorked;
    }

    public BigDecimal getOvertimeIncome() {
        return overtimeIncome;
    }

    public BigDecimal getGrossIncome() {
        return grossIncome;
    }

    public BigDecimal getRiceSubsidy() {
        return riceSubsidy;
    }

    public BigDecimal getPhoneAllowance() {
        return phoneAllowance;
    }

    public BigDecimal getClothingAllowance() {
        return clothingAllowance;
    }

    public BigDecimal getBenefitsTotal() {
        return benefitsTotal;
    }

    public BigDecimal getSocialSecuritySystem() {
        return socialSecuritySystem;
    }

    public BigDecimal getPhilhealth() {
        return philhealth;
    }

    public BigDecimal getPagibig() {
        return pagibig;
    }

    public BigDecimal getWithholdingTax() {
        return withholdingTax;
    }

    public BigDecimal getTotalDeductions() {
        return totalDeductions;
    }

    public BigDecimal getSummaryGross() {
        return summaryGross;
    }

    public BigDecimal getSummaryBenefits() {
        return summaryBenefits;
    }

    public BigDecimal getSummaryDeductions() {
        return summaryDeductions;
    }

    public BigDecimal getTakeHomePay() {
        return takeHomePay;
    }
    
    
    
    
    
    
}

