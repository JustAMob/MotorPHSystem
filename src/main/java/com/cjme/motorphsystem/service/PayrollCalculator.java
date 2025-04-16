package com.cjme.motorphsystem.service;

import com.cjme.motorphsystem.dao.AllowanceDAO;
import com.cjme.motorphsystem.dao.DeductionDAO;
import com.cjme.motorphsystem.dao.EmployeeDAO;
import com.cjme.motorphsystem.dao.TaxDAO;
import static com.cjme.motorphsystem.util.PayrollUtil.round;


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
        double totalHours = round(attendance.calculateTotalHoursWorked(employeeId, year, month));
        System.out.println("Total Hours Worked: "+ totalHours);

        // 2. Get hourly rate
        Object hourlyRateObj = employeeDAO.getColumnValueByEmployeeId(employeeId, "hourly_rate");
        double hourlyRate = hourlyRateObj != null ? (double) hourlyRateObj : 0.0;
        System.out.println("Hourly Rate: " + hourlyRate);
        
        // 3. Calculate gross pay
        double grossPay = round(totalHours * hourlyRate);
        System.out.println("Gross Pay: " + grossPay);
        
        // 4. Get total deductions
        double totalDeduction = round(deductionDAO.getTotalDeductions(employeeId));
        System.out.println("Total Deductions: "+ totalDeduction);
        
        // 5. Calculate tax
        double deductedSalary = round(grossPay - totalDeduction);
        System.out.println("Deducted Salary: " + deductedSalary);
        
        // 6. Get taxed 
        double taxed = round(taxDAO.calculateTax(deductedSalary));
        System.out.println("Tax: " + taxed);
        
        // 7. Get total allowance
        double totalAllowance = round(allowanceDAO.getTotalAllowanceByEmployeeID(employeeId));
        System.out.println("Total Allowance: "+ totalAllowance);

        // 8. Calculate net pay
        double netPay = round(grossPay + totalAllowance - (taxed + totalDeduction));
        System.out.println("Net Pay: " + netPay);
        
        return netPay;
    }
}


