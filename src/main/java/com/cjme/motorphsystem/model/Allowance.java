package com.cjme.motorphsystem.model;

/**
 *
 * @author JustAMob
 */
public class Allowance {
    private int allowanceId;
    private int employeeId;
    private double riceAllowance;
    private double phoneAllowance;
    private double clothingAllowance;
 
    public Allowance() {}

    public Allowance(int employeeId, double riceAllowance, double phoneAllowance, double clothingAllowance) {
        this.employeeId = employeeId;
        this.riceAllowance = riceAllowance;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
    }

    public Allowance(int allowanceId, int employeeId, double riceAllowance, double phoneAllowance, double clothingAllowance) {
        this.allowanceId = allowanceId;
        this.employeeId = employeeId;
        this.riceAllowance = riceAllowance;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
    }

    public int getAllowanceId() {
        return allowanceId;
    }

    public void setAllowanceId(int allowanceId) {
        this.allowanceId = allowanceId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getRiceAllowance() {
        return riceAllowance;
    }

    public void setRiceAllowance(double riceAllowance) {
        this.riceAllowance = riceAllowance;
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
        return riceAllowance + phoneAllowance + clothingAllowance;
    }
}
