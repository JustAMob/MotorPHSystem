package com.cjme.motorphsystem.service;

import com.cjme.motorphsystem.dao.AllowanceDAO;
import com.cjme.motorphsystem.dao.AllowanceDAOImpl;
import com.cjme.motorphsystem.dao.AttendanceDAO;
import com.cjme.motorphsystem.dao.DeductionDAO;
import com.cjme.motorphsystem.dao.EmployeeDAO;
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
        int employeeId = 10008;
        int month = 7;
        int year = 2024;

        // DAO instances 
        TaxDAO taxDAO = new TaxDAO();
        AllowanceDAO allowanceDAO = new AllowanceDAOImpl(); 
        DeductionDAO deductionDAO = new DeductionDAO(DBConnection.getConnection());
        EmployeeDAO employeeDAO = new EmployeeDAO(); 
        AttendanceDAO attendanceDAO = new AttendanceDAO(); 

        AttendanceTracker attendance = new AttendanceTracker(attendanceDAO);

        PayrollCalculator payrollCalculator = new PayrollCalculator(
            taxDAO,
            allowanceDAO,
            attendance,
            deductionDAO,
            employeeDAO,
            employeeId
        );

        double netPay = payrollCalculator.calculateNetPay(month, year);
        System.out.println("Net Pay for Employee " + employeeId + ": " + netPay);

        assertTrue(netPay >= 0); 
    }
}