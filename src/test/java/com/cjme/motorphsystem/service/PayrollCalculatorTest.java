/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
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

        // DAO instances with real DB connections
        TaxDAO taxDAO = new TaxDAO();
        AllowanceDAO allowanceDAO = new AllowanceDAOImpl(); // assuming this doesn't need a connection
        DeductionDAO deductionDAO = new DeductionDAO(DBConnection.getConnection());
        EmployeeDAO employeeDAO = new EmployeeDAO(); // assuming no connection needed
        AttendanceDAO attendanceDAO = new AttendanceDAO(); // assuming no connection needed

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

        assertTrue(netPay >= 0); // or assertEquals(expected, netPay, 0.01) if you know the expected value
    }
}