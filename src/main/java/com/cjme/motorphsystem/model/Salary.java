package com.cjme.motorphsystem.model;

import java.math.BigDecimal;

/**
 *
 * @author JustAMob
 */
public class Salary {
    private int salaryId;
    private BigDecimal basicSalary;
    private BigDecimal grossSemiMonthlyRate;
    private BigDecimal hourlyRate;

    public Salary() {
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public void setGrossSemiMonthlyRate(BigDecimal grossSemiMonthlyRate) {
        this.grossSemiMonthlyRate = grossSemiMonthlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getSalaryId() {
        return salaryId;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public BigDecimal getGrossSemiMonthlyRate() {
        return grossSemiMonthlyRate;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    
}
