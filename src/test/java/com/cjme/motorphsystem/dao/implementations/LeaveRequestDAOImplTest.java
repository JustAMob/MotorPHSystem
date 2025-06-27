/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.model.LeaveRequest;
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
public class LeaveRequestDAOImplTest {
    
    public LeaveRequestDAOImplTest() {
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
     * Test of addLeaveRequest method, of class LeaveRequestDAOImpl.
     */
    @Test
    public void testAddLeaveRequest() {
        System.out.println("addLeaveRequest");
        LeaveRequest request = null;
        String role = "";
        LeaveRequestDAOImpl instance = new LeaveRequestDAOImpl();
        instance.addLeaveRequest(request, role);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLeaveRequestById method, of class LeaveRequestDAOImpl.
     */
    @Test
    public void testGetLeaveRequestById() {
        System.out.println("getLeaveRequestById");
        int id = 0;
        LeaveRequestDAOImpl instance = new LeaveRequestDAOImpl();
        LeaveRequest expResult = null;
        LeaveRequest result = instance.getLeaveRequestById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllLeaveRequests method, of class LeaveRequestDAOImpl.
     */
    @Test
    public void testGetAllLeaveRequests() {
        System.out.println("getAllLeaveRequests");
        LeaveRequestDAOImpl instance = new LeaveRequestDAOImpl();
        List<LeaveRequest> expResult = null;
        List<LeaveRequest> result = instance.getAllLeaveRequests();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateLeaveRequestStatus method, of class LeaveRequestDAOImpl.
     */
    @Test
    public void testUpdateLeaveRequestStatus() {
        System.out.println("updateLeaveRequestStatus");
        int requestId = 0;
        String newStatus = "";
        String role = "";
        LeaveRequestDAOImpl instance = new LeaveRequestDAOImpl();
        instance.updateLeaveRequestStatus(requestId, newStatus, role);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateLeaveRequest method, of class LeaveRequestDAOImpl.
     */
    @Test
    public void testUpdateLeaveRequest() {
        System.out.println("updateLeaveRequest");
        LeaveRequest request = null;
        String role = "";
        LeaveRequestDAOImpl instance = new LeaveRequestDAOImpl();
        instance.updateLeaveRequest(request, role);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteLeaveRequest method, of class LeaveRequestDAOImpl.
     */
    @Test
    public void testDeleteLeaveRequest() {
        System.out.println("deleteLeaveRequest");
        int id = 0;
        String role = "";
        LeaveRequestDAOImpl instance = new LeaveRequestDAOImpl();
        instance.deleteLeaveRequest(id, role);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
