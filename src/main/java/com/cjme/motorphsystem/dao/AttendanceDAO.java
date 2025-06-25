package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.Attendance;
import com.cjme.motorphsystem.util.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JustAMob
 */
public class AttendanceDAO {
    private final Connection connection;

    public AttendanceDAO() throws SQLException {
        this.connection = DBConnection.getConnection();
    }
    
    //CREATE
    public boolean addTimelog (Attendance attendance) throws SQLException {
        String sql = "INSERT INTO attendace (employee_id, attendance_date, time_in, time_out) VALUES(?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1,attendance.getEmployeeID());
            stmt.setDate(2,attendance.getLogDate());
            stmt.setTime(3,attendance.getTimeIn());
            stmt.setTime(4,attendance.getTimeOut());
            return stmt.executeUpdate()>0;
        }
    }
    
    //Gets all timelogs for a specific employee.
    public List<Attendance> getTimeLogsByEmployee(int employeeID) throws SQLException {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM attendance WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Attendance attendance = new Attendance (
                        rs.getInt("attendance_id"),
                        rs.getInt("employee_id"),
                        rs.getDate("attendance_date"),
                        rs.getTime("time_in"),
                        rs.getTime("time_out")
                    );
                list.add(attendance);
            }
        }
        return list;
    }
    
    //Gets timelogs of an employee for a specific month and year
    public List<Attendance> getTimeLogsByEmployeeAndMonth(int employeeID, int year, int month) throws SQLException {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM attendance WHERE employee_id = ? AND YEAR(attendance_date) = ? AND MONTH(attendance_date) = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeID);
            stmt.setInt(2, year);
            stmt.setInt(3, month);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Attendance attendance = new Attendance (
                        rs.getInt("attendance_id"),
                        rs.getInt("employee_id"),
                        rs.getDate("attendance_date"),
                        rs.getTime("time_in"),
                        rs.getTime("time_out")
                    );
                list.add(attendance);
            }
        }
        return list;
    }
  
    //Gets all timelogs for a specific date    
    public List<Attendance> getTimeLogsByDate(Date date) throws SQLException {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM attendance WHERE attendance_date = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Attendance attendance = new Attendance (
                        rs.getInt("attendance_id"),
                        rs.getInt("employee_id"),
                        rs.getDate("attendance_date"),
                        rs.getTime("time_in"),
                        rs.getTime("time_out")
                    );
                list.add(attendance);
            }
        }
        return list;
    }
    
    //Gets all timelogs within a date range
    public List<Attendance> getTimeLogsByRange(Date startDate, Date endDate) throws SQLException {
        List<Attendance> list = new ArrayList<>();
            String sql = "SELECT * FROM attendace WHERE attendance_date BETWEEN ? AND ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setDate(1, startDate);
                stmt.setDate(2, endDate);

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Attendance attendance = new Attendance (
                            rs.getInt("attendance_id"),
                            rs.getInt("employee_id"),
                            rs.getDate("attendance_date"),
                            rs.getTime("time_in"),
                            rs.getTime("time_out")
                        );
                    list.add(attendance);
                }
            }
            return list;
    }
        
    //Gets all timelogs for an employee on a specific day
    public List<Attendance> getTimeLogsByEmployeeAndDate (int employeeID, Date date) throws SQLException {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM attendance WHERE employee_id = ? AND DATE(attendance_date) = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeID);
            stmt.setDate(2, date);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Attendance attendance = new Attendance (
                        rs.getInt("attendance_id"),
                        rs.getInt("employee_id"),
                        rs.getDate("attendance_date"),
                        rs.getTime("time_in"),
                        rs.getTime("time_out")
                    );
                list.add(attendance);
            }
        }
        return list;
   }
}
