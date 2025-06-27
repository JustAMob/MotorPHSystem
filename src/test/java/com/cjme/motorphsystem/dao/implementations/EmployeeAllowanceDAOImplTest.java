/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.cjme.motorphsystem.dao.implementations;

import java.math.BigDecimal;
import java.util.Map;
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
public class EmployeeAllowanceDAOImplTest {
    
    public EmployeeAllowanceDAOImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of assignAllowance method, of class EmployeeAllowanceDAOImpl.
     */
    @Test
    public void testAssignAllowance() throws Exception {
        System.out.println("assignAllowance");
        int employeeId = 0;
        int allowanceTypeId = 0;
        BigDecimal amount = null;
        EmployeeAllowanceDAOImpl instance = new EmployeeAllowanceDAOImpl();
        instance.assignAllowance(employeeId, allowanceTypeId, amount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllowancesByEmployee method, of class EmployeeAllowanceDAOImpl.
     */
    @Test
    public void testGetAllowancesByEmployee() throws Exception {
        System.out.println("getAllowancesByEmployee");
        int employeeId = 0;
        EmployeeAllowanceDAOImpl instance = new EmployeeAllowanceDAOImpl();
        Map<String, BigDecimal> expResult = null;
        Map<String, BigDecimal> result = instance.getAllowancesByEmployee(employeeId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalAllowance method, of class EmployeeAllowanceDAOImpl.
     */
    @Test
    public void testGetTotalAllowance() throws Exception {
        System.out.println("getTotalAllowance");
        int employeeId = 0;
        EmployeeAllowanceDAOImpl instance = new EmployeeAllowanceDAOImpl();
        BigDecimal expResult = null;
        BigDecimal result = instance.getTotalAllowance(employeeId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
