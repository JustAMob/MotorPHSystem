/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.Attendance;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JustAMob
 */
public class AttendanceDAOTest {
    private AttendanceDAO attendanceDAO;
    private final int testEmployeeId = 10008; // Make sure this is a valid employee ID in your test DB
    private final Date testDate = Date.valueOf("2024-06-03");

    @Before
    public void setUp() throws Exception {
        attendanceDAO = new AttendanceDAO();

        // Insert test time log
        Attendance attendance = new Attendance();
        attendance.setEmployeeID(testEmployeeId);
        attendance.setLogDate(testDate);
        attendance.setTimeIn(Time.valueOf("08:00:00"));
        attendance.setTimeOut(Time.valueOf("17:00:00"));

        boolean inserted = attendanceDAO.addTimelog(attendance);
        assertTrue("Test log should be inserted", inserted);
    }

    @Test
    public void testGetTimeLogsByEmployee() throws Exception {
        List<Attendance> logs = attendanceDAO.getTimeLogsByEmployee(testEmployeeId);
        assertNotNull("Logs should not be null", logs);
        assertTrue("Should contain logs for test employee", logs.size() > 0);
    }

    @Test
    public void testGetTimeLogsByEmployeeAndMonth() throws Exception {
        List<Attendance> logs = attendanceDAO.getTimeLogsByEmployeeAndMonth(testEmployeeId, 2024, 6);
        assertNotNull(logs);
        assertTrue("Logs should contain at least one record", logs.size() > 0);
    }

    @Test
    public void testGetTimeLogsByDate() throws Exception {
        List<Attendance> logs = attendanceDAO.getTimeLogsByDate(testDate);
        assertNotNull(logs);
        assertTrue("Logs should contain at least one record", logs.size() > 0);
    }

    @Test
    public void testGetTimeLogsByRange() throws Exception {
        Date start = Date.valueOf("2024-06-01");
        Date end = Date.valueOf("2024-06-30");
        List<Attendance> logs = attendanceDAO.getTimeLogsByRange(start, end);
        assertNotNull(logs);
    }

    @Test
    public void testGetTimeLogsByEmployeeAndDate() throws Exception {
        List<Attendance> logs = attendanceDAO.getTimeLogsByEmployeeAndDate(testEmployeeId, testDate);
        assertNotNull(logs);
        assertFalse("Logs for specific date should not be empty", logs.isEmpty());
    }
}
