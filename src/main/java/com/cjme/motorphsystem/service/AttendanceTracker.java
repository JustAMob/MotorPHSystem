package com.cjme.motorphsystem.service;

import com.cjme.motorphsystem.dao.AttendanceDAO;
import com.cjme.motorphsystem.model.Attendance;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author JustAMob
 */
public class AttendanceTracker {
    private final AttendanceDAO attendanceDAO;

    public AttendanceTracker (AttendanceDAO attendanceDAO) {
        this.attendanceDAO = attendanceDAO;
    }

    public double calculateTotalHoursWorked(int employeeId, int year, int month) throws SQLException {
        List<Attendance> logs = attendanceDAO.getTimeLogsByEmployeeAndMonth(employeeId, year, month);
        double totalHours = 0.0;

        for (Attendance log : logs) {
            Time timeIn = log.getTimeIn();
            Time timeOut = log.getTimeOut();

            if (timeIn != null && timeOut != null) {
                LocalTime in = timeIn.toLocalTime();
                LocalTime out = timeOut.toLocalTime();

                Duration duration = Duration.between(in, out);
                totalHours += duration.toMinutes() / 60.0;  
            }
        }

        return totalHours;
    }
    
}
