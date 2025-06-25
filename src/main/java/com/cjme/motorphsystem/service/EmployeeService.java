package com.cjme.motorphsystem.service;

/**
 *
 * @author JustAMob
 */
import com.cjme.motorphsystem.dao.EmployeeDAOImpl;
import com.cjme.motorphsystem.model.Employee;
import com.cjme.motorphsystem.util.ValidationUtils;
import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private final EmployeeDAOImpl employeeDAO;
    
    public EmployeeService() throws SQLException {
        this.employeeDAO = new EmployeeDAOImpl();
    }

    public boolean addEmployee(Employee employee) throws SQLException {
        ValidationUtils.validateEmployeeData(employee);
        
        return employeeDAO.addEmployee(employee);
    }

    public List<Employee> getAllEmployees() throws SQLException {
        return employeeDAO.getAllEmployees();
    }

    public Employee getEmployeeById(int id) throws SQLException {
        return employeeDAO.getEmployeeById(id);
    }

    public boolean updateEmployee(Employee employee) throws SQLException {
        ValidationUtils.validateEmployeeData(employee);
        
        return employeeDAO.updateEmployee(employee);
    }

    public boolean deleteEmployee(int id) throws SQLException {
        
        return employeeDAO.deleteEmployee(id);
    }


}
