package com.cjme.motorphsystem.service;

import com.cjme.motorphsystem.dao.AttendanceDAO;
import com.cjme.motorphsystem.model.Attendance;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

/**
 *
 * @author JustAMob
 */
public class AttendanceTrackerTest {

    private AttendanceDAO attendanceDAO;
    private AttendanceTracker attendanceTracker;

    @Before
    public void setUp() {
        attendanceDAO = Mockito.mock(AttendanceDAO.class);
        
        attendanceTracker = new AttendanceTracker(attendanceDAO);
    }

    @Test
    public void testCalculateTotalHoursWorked() throws Exception {
        int employeeId = 1;
        int year = 2025;
        int month = 6;

        // Create a list of Attendance logs (timeIn and timeOut)
        Attendance attendance1 = new Attendance();
        attendance1.setTimeIn(Time.valueOf("08:00:00"));
        attendance1.setTimeOut(Time.valueOf("17:00:00"));

        Attendance attendance2 = new Attendance();
        attendance2.setTimeIn(Time.valueOf("09:00:00"));
        attendance2.setTimeOut(Time.valueOf("18:00:00"));

        List<Attendance> attendanceLogs = Arrays.asList(attendance1, attendance2);

        // When: The DAO mock returns the attendance logs for the given employee and month
        when(attendanceDAO.getTimeLogsByEmployeeAndMonth(employeeId, year, month)).thenReturn(attendanceLogs);

        // Then: Calculate the total hours worked and verify the result
        double expectedHours = 18.0; // 9 hours from the first day and 9 hours from the second day
        double actualHours = attendanceTracker.calculateTotalHoursWorked(employeeId, year, month);

        // Assert: Verify that the result is correct
        assertEquals(expectedHours, actualHours, 0.01);  
    }
}
