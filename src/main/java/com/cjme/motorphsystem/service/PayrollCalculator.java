package com.cjme.motorphsystem.service;

import com.cjme.motorphsystem.dao.AllowanceDAO;
import com.cjme.motorphsystem.dao.DeductionDAO;
import com.cjme.motorphsystem.dao.EmployeeDAO;
import com.cjme.motorphsystem.dao.TaxDAO;

/**
 *
 * @author JustAMob
 */

public class PayrollCalculator {
    private final TaxDAO taxDAO;
    private final AllowanceDAO allowanceDAO;
    private final AttendanceTracker attendance;
    private final DeductionDAO deductionDAO;
    private final EmployeeDAO employeeDAO;
    private final int employeeId;

    public PayrollCalculator(TaxDAO taxDAO, AllowanceDAO allowanceDAO, AttendanceTracker attendance, DeductionDAO deductionDAO, EmployeeDAO employeeDAO, int employeeId) {
        this.taxDAO = taxDAO;
        this.allowanceDAO = allowanceDAO;
        this.attendance = attendance;
        this.deductionDAO = deductionDAO;
        this.employeeDAO = employeeDAO;
        this.employeeId = employeeId;
    }

  

    public double calculateNetPay(int month, int year) throws Exception {
        // 1. Get total hours worked
        double totalHours = attendance.calculateTotalHoursWorked(employeeId, month, year);

        // 2. Get hourly rate
        Object hourlyRateObj = employeeDAO.getColumnValueByEmployeeId(employeeId, "hourly_rate");
        double hourlyRate = hourlyRateObj != null ? (double) hourlyRateObj : 0.0;

        // 3. Calculate gross pay
        double grossPay = totalHours * hourlyRate;
        
        // 4. Get total deductions
        double totalDeduction = deductionDAO.getTotalDeductions(employeeId);
        
        // 5. Calculate tax
        double deductedSalary = grossPay - totalDeduction;

        // 6. Get taxed Salary
        double taxedSalary = taxDAO.calculateTax(deductedSalary);
        
        // 7. Get total allowance
        double totalAllowance = allowanceDAO.getTotalAllowanceByEmployeeID(employeeId);


        // 8. Calculate net pay
        double netPay = grossPay + totalAllowance - (taxedSalary + totalDeduction);

        return netPay;
    }
}


