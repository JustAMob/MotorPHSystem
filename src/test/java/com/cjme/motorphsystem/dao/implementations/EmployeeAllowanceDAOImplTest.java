/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.util.DBConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JustAMob
 */
public class EmployeeAllowanceDAOImplTest {

    private EmployeeAllowanceDAOImpl dao;
    private final int employeeId = 10016; 
    private final int allowanceTypeId = 1; 
    private final String allowanceRice = "rice_allowance"; 
    private final String allowanceClothRankFile = "clothing_allowance"; 

    @Before
    public void setUp() throws SQLException {
        dao = new EmployeeAllowanceDAOImpl();

        // Clean up before each test
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "DELETE FROM employee_allowance WHERE employee_id = ?")) {
            stmt.setInt(1, employeeId);
            stmt.executeUpdate();
        }
    }

    @Test
    public void testAssignAndRetrieveAllowance() throws SQLException {
        BigDecimal amount = new BigDecimal("1500.00");

        dao.assignAllowance(employeeId, allowanceTypeId, amount);

        Map<String, BigDecimal> allowances = dao.getAllowancesByEmployee(employeeId);

        assertTrue("Allowance map should contain key: " + allowanceRice, allowances.containsKey(allowanceRice));
        assertEquals("Allowance amount should match", amount, allowances.get(allowanceRice));
    }

    @Test
    public void testTotalAllowanceComputation() throws SQLException {
        // Assign two allowances for total test (could be same or different allowance types)
        dao.assignAllowance(employeeId, allowanceTypeId, new BigDecimal("1000.00"));
        dao.assignAllowance(employeeId, allowanceTypeId, new BigDecimal("1000.00")); 

        BigDecimal total = dao.getTotalAllowance(employeeId);

        assertNotNull("Total allowance should not be null", total);
        assertEquals(new BigDecimal("1000.00"), total); // last write should override previous
    }

    @Test
    public void testZeroAllowanceWhenNoData() throws SQLException {
        BigDecimal total = dao.getTotalAllowance(9999); // nonexistent employee
        assertNotNull("Should return zero, not null", total);
        assertTrue("Expected zero allowance", BigDecimal.ZERO.compareTo(total) == 0);
    }
}