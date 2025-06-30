package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.dao.EmployeeDAO;
import com.cjme.motorphsystem.model.Employee;
import com.cjme.motorphsystem.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.UIManager.getInt;

public class EmployeeDAOImpl implements EmployeeDAO {

    private boolean isAuthorized(String role) {
        return role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("hr");
    }

    @Override
    public int addEmployee(Employee emp, String role) {
        if (!isAuthorized(role)) {
            throw new SecurityException("Unauthorized to add employee.");
        }

        String sql = "INSERT INTO employee (first_name, last_name, address_id, phone_number, government_id, department_id, salary_id, supervisor_id, status_id, position_id, birthday) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            stmt.setString(1, emp.getFirstName());
            stmt.setString(2, emp.getLastName());
            stmt.setInt(3, emp.getAddressId());
            stmt.setInt(4, emp.getPhoneNumber());
            stmt.setInt(5, emp.getGovernmentId());
            stmt.setInt(6, emp.getDepartmentId());
            stmt.setInt(7, emp.getSalaryId());
            stmt.setInt(8, emp.getSupervisorId());
            stmt.setInt(9, emp.getStatusId());
            stmt.setInt(10, emp.getPositionId());
            stmt.setDate(11, emp.getBirthday());

            int rows = stmt.executeUpdate();
            if (rows == 0) {
                System.out.println("Insert returned 0 rows.");
            }

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                System.out.println("Generated ID: " + rs.getInt(1));
                return rs.getInt(1);
            } else {
                System.out.println("No generated key returned.");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // SHOW the real cause
        }
        return -1;
    }

    @Override
    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employee WHERE employee_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Employee emp = new Employee();
                emp.setEmployeeId(rs.getInt("employee_id"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setAddressId(rs.getInt("address_id"));
                emp.setPhoneNumber(rs.getInt("phone_number"));
                emp.setGovernmentId(rs.getInt("government_id"));
                emp.setDepartmentId(rs.getInt("department_id"));
                emp.setSalaryId(rs.getInt("salary_id"));
                emp.setSupervisorId(rs.getInt("supervisor_id"));
                emp.setStatusId(rs.getInt("status_id"));
                emp.setPositionId(rs.getInt("position_id"));
                emp.setBirthday(rs.getDate("birthday"));
                return emp;
            }

        } catch (SQLException e) {
        }

        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employee";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmployeeId(rs.getInt("employee_id"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setAddressId(rs.getInt("address_id"));
                emp.setPhoneNumber(rs.getInt("phone_number"));
                emp.setGovernmentId(rs.getInt("government_id"));
                emp.setDepartmentId(rs.getInt("department_id"));
                emp.setSalaryId(rs.getInt("salary_id"));
                emp.setSupervisorId(rs.getInt("supervisor_id"));
                emp.setStatusId(rs.getInt("status_id"));
                emp.setPositionId(rs.getInt("position_id"));
                emp.setBirthday(rs.getDate("birthday"));
                list.add(emp);
            }

        } catch (SQLException e) {
        }

        return list;
    }

    @Override
    public void updateEmployee(Employee emp, String role) {
        if (!isAuthorized(role)) {
            throw new SecurityException("Unauthorized to update employee.");
        }

        String sql = "UPDATE employee SET first_name = ?, last_name = ?, address_id = ?, phone_number = ?, government_id = ?, department_id = ?, salary_id = ?, supervisor_id = ?, status_id = ?, position_id = ?, birthday = ? WHERE employee_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, emp.getFirstName());
            stmt.setString(2, emp.getLastName());
            stmt.setInt(3, emp.getAddressId());
            stmt.setInt(4, emp.getPhoneNumber());
            stmt.setInt(5, emp.getGovernmentId());
            stmt.setInt(6, emp.getDepartmentId());
            stmt.setInt(7, emp.getSalaryId());
            stmt.setInt(8, emp.getSupervisorId());
            stmt.setInt(9, emp.getStatusId());
            stmt.setInt(10, emp.getPositionId());
            stmt.setDate(11, emp.getBirthday());
            stmt.setInt(12, emp.getEmployeeId());

            stmt.executeUpdate();

        } catch (SQLException e) {
        }
    }

    @Override
    public void deleteEmployee(int id, String role) {
        if (!isAuthorized(role)) {
            throw new SecurityException("Unauthorized to delete employee.");
        }

        String sql = "DELETE FROM employee WHERE employee_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
        }
    }
}
