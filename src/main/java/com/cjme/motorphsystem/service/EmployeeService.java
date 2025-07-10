package com.cjme.motorphsystem.service;

/**
 *
 * @author JustAMob
 */
import com.cjme.motorphsystem.dao.AddressDAO;
import com.cjme.motorphsystem.dao.EmployeeEntityDAO;
import com.cjme.motorphsystem.dao.EmployeeProfileDAO;
import com.cjme.motorphsystem.dao.GovernmentIdDAO;
import com.cjme.motorphsystem.dao.SalaryDAO;
import com.cjme.motorphsystem.model.Address;
import com.cjme.motorphsystem.model.EmployeeEntity;
import com.cjme.motorphsystem.model.EmployeeProfile;
import com.cjme.motorphsystem.model.GovernmentID;
import com.cjme.motorphsystem.model.Salary;
import com.cjme.motorphsystem.util.DBConnection;
import java.sql.Connection;


import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private final EmployeeEntityDAO entityDAO;
    private final EmployeeProfileDAO profileDAO;
    private final AddressDAO addressDAO;
    private final GovernmentIdDAO governmentDAO;
    private final SalaryDAO salaryDAO;

    public EmployeeService(EmployeeEntityDAO entityDAO, EmployeeProfileDAO profileDAO, AddressDAO addressDAO, GovernmentIdDAO governmentDAO, SalaryDAO salaryDAO) {
        this.entityDAO = entityDAO;
        this.profileDAO = profileDAO;
        this.addressDAO = addressDAO;
        this.governmentDAO = governmentDAO;
        this.salaryDAO = salaryDAO;
    }





    public int insertNewEmployee(EmployeeEntity employee, Address address, GovernmentID govId, Salary salary) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                int addressId = addressDAO.addAddress(address, conn);
                int govIdId = governmentDAO.addGovernmentId(govId, conn);
                int salaryId = salaryDAO.addSalary(salary, conn);

                employee.setAddressId(addressId);
                employee.setGovernmentId(govIdId);
                employee.setSalaryId(salaryId);

                int empId = entityDAO.addEmployee(employee, conn);
                conn.commit();
                return empId;

            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        }
    }

    /**
     * Updates an existing employee record.
     *
     * @param emp  the EmployeeEntity with updated values
     * @param role the role of the user performing the operation
     * @throws SQLException on DB errors or authorization failure
     */
    public void updateEmployee(EmployeeEntity emp, String role) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                entityDAO.updateEmployee(emp);
                conn.commit();
            } catch (SQLException | SecurityException ex) {
                conn.rollback();
                throw ex;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    /**
     * Deletes an employee record by ID.
     *
     * @param employeeId the ID of the employee to delete
     * @param role       the role of the user performing the operation
     * @throws SQLException on DB errors or authorization failure
     */
    public void deleteEmployee(int employeeId, String role) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                entityDAO.deleteEmployee(employeeId);
                conn.commit();
            } catch (SQLException | SecurityException ex) {
                conn.rollback();
                throw ex;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }
    
    public EmployeeEntity getEmployeeById(int employeeId) {
        return entityDAO.getEmployeeById(employeeId);
    }
    
    /**
     * Retrieves a full employee profile for display.
     *
     * @param employeeId the ID of the employee
     * @return EmployeeProfile or null if not found
     * @throws SQLException on DB errors
     */
    public EmployeeProfile getEmployeeProfile(int employeeId) throws SQLException {
        return profileDAO.getEmployeeById(employeeId);
    }

    /**
     * Retrieves all employee profiles.
     *
     * @return list of EmployeeProfile
     * @throws SQLException on DB errors
     */
    public List<EmployeeProfile> getAllEmployeeProfiles() throws SQLException {
        return profileDAO.getAllEmployees();
    }
}
