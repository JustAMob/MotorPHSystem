/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.model.LeaveRequest;
import com.cjme.motorphsystem.util.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
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
    private static LeaveRequestDAOImpl dao;
    private static Connection conn;

    @BeforeClass
    public static void setupClass() throws SQLException {
        conn = DBConnection.getConnection();
        dao = new LeaveRequestDAOImpl();
        clearTestData();
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        clearTestData();
        conn.close();
    }

    private static void clearTestData() throws SQLException {
        conn.prepareStatement("DELETE FROM leave_request WHERE reason LIKE 'Test Reason%'").executeUpdate();
    }

    @Test
    public void testAddAndGetLeaveRequest() {
        LeaveRequest request = new LeaveRequest();
        request.setEmployeeId(10001); 
        request.setLeaveType("Vacation");
        request.setLeaveStart(Date.valueOf("2025-07-01"));
        request.setLeaveEnd(Date.valueOf("2025-07-05"));
        request.setReason("Test Reason Add");
        request.setLeaveStatus("Pending");



        List<LeaveRequest> all = dao.getAllLeaveRequests();
        boolean found = all.stream().anyMatch(lr -> "Test Reason Add".equals(lr.getReason()));
        assertTrue(found);
    }

    @Test(expected = SecurityException.class)
    public void testUnauthorizedAddLeaveRequest() {
        LeaveRequest request = new LeaveRequest();
        request.setEmployeeId(1);
        request.setLeaveType("Sick");
        request.setLeaveStart(Date.valueOf("2025-07-01"));
        request.setLeaveEnd(Date.valueOf("2025-07-02"));
        request.setReason("Test Reason Unauthorized");
        request.setLeaveStatus("Pending");


    }

    @Test
    public void testUpdateLeaveRequestStatus() {
        LeaveRequest request = new LeaveRequest();
        request.setEmployeeId(10001);
        request.setLeaveType("Vacation");
        request.setLeaveStart(Date.valueOf("2025-07-10"));
        request.setLeaveEnd(Date.valueOf("2025-07-15"));
        request.setReason("Test Reason Update");
        request.setLeaveStatus("Pending");


        LeaveRequest inserted = dao.getAllLeaveRequests().stream()
                .filter(lr -> "Test Reason Update".equals(lr.getReason()))
                .findFirst().orElse(null);

        assertNotNull(inserted);

        LeaveRequest updated = dao.getLeaveRequestById(inserted.getLeaveRequestId());
        assertEquals("Approved", updated.getLeaveStatus());
    }

    @Test
    public void testDeleteLeaveRequest() {
        LeaveRequest request = new LeaveRequest();
        request.setEmployeeId(10001);
        request.setLeaveType("Sick");
        request.setLeaveStart(Date.valueOf("2025-07-20"));
        request.setLeaveEnd(Date.valueOf("2025-07-22"));
        request.setReason("Test Reason Delete");
        request.setLeaveStatus("Pending");

  

        LeaveRequest inserted = dao.getAllLeaveRequests().stream()
                .filter(lr -> "Test Reason Delete".equals(lr.getReason()))
                .findFirst().orElse(null);

        assertNotNull(inserted);


        LeaveRequest deleted = dao.getLeaveRequestById(inserted.getLeaveRequestId());
        assertNull(deleted);
    }
}
