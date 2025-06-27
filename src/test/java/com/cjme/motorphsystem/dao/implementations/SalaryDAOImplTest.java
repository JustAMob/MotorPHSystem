/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.model.Salary;
import java.math.BigDecimal;
import java.util.List;
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
public class SalaryDAOImplTest {
    
    public SalaryDAOImplTest() {
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
     * Test of addSalary method, of class SalaryDAOImpl.
     */
    @Test
    public void testAddSalary() throws Exception {
        System.out.println("addSalary");
        Salary salary = null;
        SalaryDAOImpl instance = new SalaryDAOImpl();
        instance.addSalary(salary);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSalaryById method, of class SalaryDAOImpl.
     */
    @Test
    public void testGetSalaryById() throws Exception {
        System.out.println("getSalaryById");
        int salaryId = 0;
        SalaryDAOImpl instance = new SalaryDAOImpl();
        Salary expResult = null;
        Salary result = instance.getSalaryById(salaryId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHourlyRateBySalaryId method, of class SalaryDAOImpl.
     */
    @Test
    public void testGetHourlyRateBySalaryId() throws Exception {
        System.out.println("getHourlyRateBySalaryId");
        int salaryId = 0;
        SalaryDAOImpl instance = new SalaryDAOImpl();
        BigDecimal expResult = null;
        BigDecimal result = instance.getHourlyRateBySalaryId(salaryId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllSalaries method, of class SalaryDAOImpl.
     */
    @Test
    public void testGetAllSalaries() throws Exception {
        System.out.println("getAllSalaries");
        SalaryDAOImpl instance = new SalaryDAOImpl();
        List<Salary> expResult = null;
        List<Salary> result = instance.getAllSalaries();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSalary method, of class SalaryDAOImpl.
     */
    @Test
    public void testUpdateSalary() throws Exception {
        System.out.println("updateSalary");
        Salary salary = null;
        SalaryDAOImpl instance = new SalaryDAOImpl();
        instance.updateSalary(salary);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteSalary method, of class SalaryDAOImpl.
     */
    @Test
    public void testDeleteSalary() throws Exception {
        System.out.println("deleteSalary");
        int salaryId = 0;
        SalaryDAOImpl instance = new SalaryDAOImpl();
        instance.deleteSalary(salaryId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
