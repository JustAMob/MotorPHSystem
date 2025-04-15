package com.cjme.motorphsystem.model.deductions;

/**
 *
 * @author JustAMob
 */
public class PagibigContribution {

    private final double employeeRate;
    private final double employerRate;

    public PagibigContribution(double employeeRate, double employerRate) {
        this.employeeRate = employeeRate;
        this.employerRate = employerRate;
    }

    public double getEmployeeRate() {
        return employeeRate;
    }

    public double getEmployerRate() {
        return employerRate;
    }
}
