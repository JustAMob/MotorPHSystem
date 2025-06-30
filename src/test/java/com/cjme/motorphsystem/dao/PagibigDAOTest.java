/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.deductions.PagibigContribution;
import com.cjme.motorphsystem.util.DBConnection;
import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JustAMob
 */
public class PagibigDAOTest {

    private PagibigDAO pagibigDAO;

    @Before
    public void setUp() throws Exception {
        Connection connection = DBConnection.getConnection();
        pagibigDAO = new PagibigDAO(connection);
    }

    @Test
    public void testGetRatesForValidSalary() {
        double testSalary = 900.00; // Ensure your database has a matching range
        PagibigContribution contribution = pagibigDAO.getRatesForSalary(testSalary);
        double contributionRate = 0;
     
        assertEquals( contribution.getEmployerRate(),  contributionRate, 0.01);
        assertEquals( contribution.getEmployeeRate(),  contributionRate, 0.01);
    }

    @Test
    public void testGetRatesForVeryHighSalary() {
        double highSalary = 9999999; // Should match the fallback case
        PagibigContribution contribution = pagibigDAO.getRatesForSalary(highSalary);
        double contributionRate = 0.02;

        assertTrue("Employee rate should be non-negative", contribution.getEmployeeRate() >= 0);
        assertTrue("Employer rate should be non-negative", contribution.getEmployerRate() >= 0);
        assertEquals( contribution.getEmployeeRate(),  contributionRate, 0.01);
        assertEquals( contribution.getEmployeeRate(),  contributionRate, 0.01);
    }

    @Test
    public void testGetRatesForOutOfRangeSalary() {
        double invalidSalary = -1000.00; // Invalid test case
        PagibigContribution contribution = pagibigDAO.getRatesForSalary(invalidSalary);

        assertNull("Contribution should be null for invalid salary", contribution);
    }
}
