package com.cjme.motorphsystem.dao;
/**
 *
 * @author JustAMob
 */
import com.cjme.motorphsystem.model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private Connection connection;

    public EmployeeDAO(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public boolean addEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee (employee_id, first_name, last_name, birthday, address, phone_num, sss_num, philhealth_num, pagibig_num, tin_num, status, position, immediate_supervisor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employee.getEmployeeID());
            stmt.setString(2, employee.getFirstName());
            stmt.setString(3, employee.getLastName());
            stmt.setDate(4, employee.getBirthday());
            stmt.setString(5, employee.getAddress());
            stmt.setString(6, employee.getPhoneNum());
            stmt.setString(7, employee.getSssNum());
            stmt.setString(8, employee.getPhilHealthNum());
            stmt.setString(9, employee.getPagibigNum());
            stmt.setString(10, employee.getTinNum());
            stmt.setString(11, employee.getStatus());
            stmt.setString(11, employee.getPosition());
            stmt.setString(12, employee.getImmediateSupervisor());
            return stmt.executeUpdate() > 0;
        }
    }

    // READ (Single Employee)
    public Employee getEmployeeById(int id) throws SQLException {
        String sql = "SELECT * FROM employee WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Employee(
                    rs.getInt("employee_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDate("birthday"),
                    rs.getString("address"),
                    rs.getString("phone_num"),
                    rs.getString("sss_num"),
                    rs.getString("philhealth_num"),
                    rs.getString("pagibig_num"),
                    rs.getString("tin_num"),
                    rs.getString("status"), 
                    rs.getString("position"),
                    rs.getString("immediate_supervisor")
                );
            }
        }
        return null;
    }

    // READ (All Employees)
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Employee employee = new Employee(
                    rs.getInt("employee_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDate("birthday"),
                    rs.getString("address"),
                    rs.getString("phone_num"),
                    rs.getString("sss_num"),
                    rs.getString("philhealth_num"),
                    rs.getString("pagibig_num"),
                    rs.getString("tin_num"),
                    rs.getString("status"),
                    rs.getString("position"),
                    rs.getString("immediate_supervisor")
                );
                list.add(employee);
            }
        }
        return list;
    }

    // UPDATE
    public boolean updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET first_name=?, last_name=?, birthday=?, address=?, phone_num=?, sss_num=?, philhealth_num=?, pagibig_num=?, tin_num=?, position=?, immediate_supervisor=? WHERE employee_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setDate(3, employee.getBirthday());
            stmt.setString(4, employee.getAddress());
            stmt.setString(5, employee.getPhoneNum());
            stmt.setString(6, employee.getSssNum());
            stmt.setString(7, employee.getPhilHealthNum());
            stmt.setString(8, employee.getPagibigNum());
            stmt.setString(9, employee.getTinNum());
            stmt.setString(10, employee.getStatus());
            stmt.setString(10, employee.getPosition());
            stmt.setString(11, employee.getImmediateSupervisor());
            stmt.setInt(12, employee.getEmployeeID());
            return stmt.executeUpdate() > 0;
        }
    }

    // DELETE
    public boolean deleteEmployee(int id) throws SQLException {
        String sql = "DELETE FROM employee WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
}

