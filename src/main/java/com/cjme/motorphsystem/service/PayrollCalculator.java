package com.cjme.motorphsystem.service;

import com.cjme.motorphsystem.dao.DeductionDAO;
import com.cjme.motorphsystem.dao.implementations.EmployeeDAOImpl;
import com.cjme.motorphsystem.dao.SalaryDAO;
import com.cjme.motorphsystem.dao.implementations.SalaryDAOImpl;
import com.cjme.motorphsystem.dao.TaxDAO;
import com.cjme.motorphsystem.model.Employee;
import com.cjme.motorphsystem.model.Salary;
import static com.cjme.motorphsystem.util.PayrollUtil.round;



/**
 *
 * @author JustAMob
 */

public class PayrollCalculator {
    private final TaxDAO taxDAO;
    private final AllowanceService allowanceService;
    private final AttendanceTracker attendance;
    private final DeductionDAO deductionDAO;
    private final EmployeeDAOImpl employeeDAO;
    private final SalaryDAO salaryDAOImpl;
    private final int employeeId;

    public PayrollCalculator(
            TaxDAO taxDAO,
            AllowanceService allowanceService,
            AttendanceTracker attendance,
            DeductionDAO deductionDAO,
            EmployeeDAOImpl employeeDAO,
            SalaryDAOImpl salaryDAOImpl,
            int employeeId
    ) {
        this.taxDAO = taxDAO;
        this.allowanceService = allowanceService;
        this.attendance = attendance;
        this.deductionDAO = deductionDAO;
        this.employeeDAO = employeeDAO;
        this.salaryDAOImpl = salaryDAOImpl;
        this.employeeId = employeeId;
    }

    public double calculateNetPay(int month, int year) throws Exception {
        // 1. Get total hours worked
        double totalHours = round(attendance.calculateTotalHoursWorked(employeeId, year, month));
        System.out.println("Total Hours Worked: " + totalHours);

        // 2. Get salary_id from employee
        Employee employee = employeeDAO.getEmployeeById(employeeId);
        int salaryId = employee.getSalaryId();

        // 3. Get hourly rate from Salary table
        Salary salary = salaryDAOImpl.getSalaryById(salaryId);
        double hourlyRate = (salary != null && salary.getHourlyRate() != null)
                ? salary.getHourlyRate().doubleValue()
                : 0.0;
        System.out.println("Hourly Rate: " + hourlyRate);

        // 4. Calculate gross pay
        double grossPay = round(totalHours * hourlyRate);
        System.out.println("Gross Pay: " + grossPay);

        // 5. Get total deductions
        double totalDeduction = round(deductionDAO.getTotalDeductions(employeeId));
        System.out.println("Total Deductions: " + totalDeduction);

        // 6. Calculate salary after deductions
        double deductedSalary = round(grossPay - totalDeduction);
        System.out.println("Deducted Salary: " + deductedSalary);

        // 7. Calculate tax
        double taxed = round(taxDAO.calculateTax(deductedSalary));
        System.out.println("Tax: " + taxed);

        // 8. Get total allowance
        double totalAllowance = round(allowanceService.getTotalAllowanceAmount(employeeId).doubleValue());
        System.out.println("Total Allowance: " + totalAllowance);

        // 9. Calculate net pay
        double netPay = round(grossPay + totalAllowance - (taxed + totalDeduction));
        System.out.println("Net Pay: " + netPay);

        return netPay;
    }
}


