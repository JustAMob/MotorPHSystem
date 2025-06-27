/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.model.Employee;
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
public class EmployeeDAOImplTest {
    
    public EmployeeDAOImplTest() {
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
     * Test of addEmployee method, of class EmployeeDAOImpl.
     */
    @Test
    public void testAddEmployee() {
        System.out.println("addEmployee");
        Employee emp = null;
        String role = "";
        EmployeeDAOImpl instance = new EmployeeDAOImpl();
        instance.addEmployee(emp, role);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmployeeById method, of class EmployeeDAOImpl.
     */
    @Test
    public void testGetEmployeeById() {
        System.out.println("getEmployeeById");
        int id = 0;
        EmployeeDAOImpl instance = new EmployeeDAOImpl();
        Employee expResult = null;
        Employee result = instance.getEmployeeById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllEmployees method, of class EmployeeDAOImpl.
     */
    @Test
    public void testGetAllEmployees() {
        System.out.println("getAllEmployees");
        EmployeeDAOImpl instance = new EmployeeDAOImpl();
        List<Employee> expResult = null;
        List<Employee> result = instance.getAllEmployees();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateEmployee method, of class EmployeeDAOImpl.
     */
    @Test
    public void testUpdateEmployee() {
        System.out.println("updateEmployee");
        Employee emp = null;
        String role = "";
        EmployeeDAOImpl instance = new EmployeeDAOImpl();
        instance.updateEmployee(emp, role);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteEmployee method, of class EmployeeDAOImpl.
     */
    @Test
    public void testDeleteEmployee() {
        System.out.println("deleteEmployee");
        int id = 0;
        String role = "";
        EmployeeDAOImpl instance = new EmployeeDAOImpl();
        instance.deleteEmployee(id, role);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
