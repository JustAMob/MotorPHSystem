package com.cjme.motorphsystem.service;

/**
 *
 * @author JustAMob
 */
import com.cjme.motorphsystem.dao.EmployeeDAOImpl;
import com.cjme.motorphsystem.model.Employee;


import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private final EmployeeDAOImpl employeeDAO;

    public EmployeeService() throws SQLException {
        this.employeeDAO = new EmployeeDAOImpl();
    }

    public void addEmployee(Employee employee, String role) throws SQLException {
        // Validation should be handled in GUI/controller before calling this
        employeeDAO.addEmployee(employee, role);
    }

    public List<Employee> getAllEmployees() throws SQLException {
        return employeeDAO.getAllEmployees();
    }

    public Employee getEmployeeById(int id) throws SQLException {
        return employeeDAO.getEmployeeById(id);
    }

    public void updateEmployee(Employee employee, String role) throws SQLException {
        employeeDAO.updateEmployee(employee, role);
    }

    public void deleteEmployee(int id, String role) throws SQLException {
        employeeDAO.deleteEmployee(id, role);
    }
}
