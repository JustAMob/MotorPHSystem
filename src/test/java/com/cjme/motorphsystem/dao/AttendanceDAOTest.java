/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.Attendance;
import java.sql.Date;
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
public class AttendanceDAOTest {
    
    public AttendanceDAOTest() {
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
     * Test of addTimelog method, of class AttendanceDAO.
     */
    @Test
    public void testAddTimelog() throws Exception {
        System.out.println("addTimelog");
        Attendance attendance = null;
        AttendanceDAO instance = new AttendanceDAO();
        boolean expResult = false;
        boolean result = instance.addTimelog(attendance);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeLogsByEmployee method, of class AttendanceDAO.
     */
    @Test
    public void testGetTimeLogsByEmployee() throws Exception {
        System.out.println("getTimeLogsByEmployee");
        int employeeID = 0;
        AttendanceDAO instance = new AttendanceDAO();
        List<Attendance> expResult = null;
        List<Attendance> result = instance.getTimeLogsByEmployee(employeeID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeLogsByEmployeeAndMonth method, of class AttendanceDAO.
     */
    @Test
    public void testGetTimeLogsByEmployeeAndMonth() throws Exception {
        System.out.println("getTimeLogsByEmployeeAndMonth");
        int employeeID = 0;
        int year = 0;
        int month = 0;
        AttendanceDAO instance = new AttendanceDAO();
        List<Attendance> expResult = null;
        List<Attendance> result = instance.getTimeLogsByEmployeeAndMonth(employeeID, year, month);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeLogsByDate method, of class AttendanceDAO.
     */
    @Test
    public void testGetTimeLogsByDate() throws Exception {
        System.out.println("getTimeLogsByDate");
        Date date = null;
        AttendanceDAO instance = new AttendanceDAO();
        List<Attendance> expResult = null;
        List<Attendance> result = instance.getTimeLogsByDate(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeLogsByRange method, of class AttendanceDAO.
     */
    @Test
    public void testGetTimeLogsByRange() throws Exception {
        System.out.println("getTimeLogsByRange");
        Date startDate = null;
        Date endDate = null;
        AttendanceDAO instance = new AttendanceDAO();
        List<Attendance> expResult = null;
        List<Attendance> result = instance.getTimeLogsByRange(startDate, endDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeLogsByEmployeeAndDate method, of class AttendanceDAO.
     */
    @Test
    public void testGetTimeLogsByEmployeeAndDate() throws Exception {
        System.out.println("getTimeLogsByEmployeeAndDate");
        int employeeID = 0;
        Date date = null;
        AttendanceDAO instance = new AttendanceDAO();
        List<Attendance> expResult = null;
        List<Attendance> result = instance.getTimeLogsByEmployeeAndDate(employeeID, date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
