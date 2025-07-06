    package com.cjme.motorphsystem.service;

    import com.cjme.motorphsystem.dao.implementations.AllowanceTypeDAOImpl;
    import com.cjme.motorphsystem.dao.AttendanceDAO;
    import com.cjme.motorphsystem.dao.DeductionDAO;
    import com.cjme.motorphsystem.dao.implementations.EmployeeEntityDAOImpl;
    import com.cjme.motorphsystem.dao.implementations.SalaryDAOImpl;
    import com.cjme.motorphsystem.dao.TaxDAO;
import com.cjme.motorphsystem.dao.implementations.EmployeeAllowanceDAOImpl;
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
            int employeeId = 10008;
            int month = 7;
            int year = 2024;

            // Setup DAO & Service objects
            TaxDAO taxDAO = new TaxDAO();
            EmployeeAllowanceDAOImpl employeeAllowanceDAO = new EmployeeAllowanceDAOImpl();
            AllowanceTypeDAOImpl allowanceTypeDAO = new AllowanceTypeDAOImpl();
            AllowanceService allowanceService = new AllowanceService(employeeAllowanceDAO, allowanceTypeDAO);
            AttendanceDAO attendanceDAO = new AttendanceDAO(); // implement or mock
            AttendanceTracker attendance = new AttendanceTracker(attendanceDAO);
            DeductionDAO deductionDAO = new DeductionDAO(DBConnection.getConnection());
            EmployeeEntityDAOImpl employeeDAO = new EmployeeEntityDAOImpl();
            SalaryDAOImpl salaryDAO = new SalaryDAOImpl();

            // Instantiate PayrollCalculator
            PayrollCalculator calculator = new PayrollCalculator(
                taxDAO,
                allowanceService,
                attendance,
                deductionDAO,
                employeeDAO,
                salaryDAO,
                employeeId
            );

            // When: Calculating net pay
            double netPay = calculator.calculateNetPay(month, year);

            // Then: Should return a non-negative number
            System.out.println("Net Pay for Employee ID " + employeeId + ": " + netPay);
            assertTrue("Net pay should be non-negative", netPay >= 0);
        }
    }