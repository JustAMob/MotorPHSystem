package com.cjme.motorphsystem.service;

import com.cjme.motorphsystem.dao.AllowanceDAO;
import com.cjme.motorphsystem.dao.AllowanceDAOImpl;
import com.cjme.motorphsystem.dao.AttendanceDAO;
import com.cjme.motorphsystem.dao.DeductionDAO;
import com.cjme.motorphsystem.dao.EmployeeDAOImpl;
import com.cjme.motorphsystem.dao.SalaryDAOImpl;
import com.cjme.motorphsystem.dao.TaxDAO;
import com.cjme.motorphsystem.util.DBConnection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JustAMob
 */
public class PayrollCalculatorTest {

 @Test
    public void testCalculateNetPay() throws Exception {
        // Test parameters
        int employeeId = 10008;  // Make sure this exists in your test DB
        int month = 7;
        int year = 2024;

        // Initialize DAO dependencies
        TaxDAO taxDAO = new TaxDAO();
        AllowanceDAO allowanceDAO = new AllowanceDAOImpl();
        DeductionDAO deductionDAO = new DeductionDAO(DBConnection.getConnection());
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        SalaryDAOImpl salaryDAO = new SalaryDAOImpl();
        AttendanceDAO attendanceDAO = new AttendanceDAO();

        AttendanceTracker attendance = new AttendanceTracker(attendanceDAO);

        // Inject all dependencies into the PayrollCalculator
        PayrollCalculator payrollCalculator = new PayrollCalculator(
            taxDAO,
            allowanceDAO,
            attendance,
            deductionDAO,
            employeeDAO,
            salaryDAO,
            employeeId
        );

        // Act
        double netPay = payrollCalculator.calculateNetPay(month, year);
        System.out.println("Net Pay for Employee " + employeeId + ": " + netPay);

        // Assert
        assertTrue("Net pay should not be negative", netPay >= 0);
    }
}