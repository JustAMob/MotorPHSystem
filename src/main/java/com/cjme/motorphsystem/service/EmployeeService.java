package com.cjme.motorphsystem.service;

/**
 *
 * @author JustAMob
 */
import com.cjme.motorphsystem.dao.EmployeeEntityDAO;
import com.cjme.motorphsystem.dao.EmployeeProfileDAO;
import com.cjme.motorphsystem.model.EmployeeEntity;
import com.cjme.motorphsystem.model.EmployeeProfile;
import com.cjme.motorphsystem.util.DBConnection;
import java.sql.Connection;


import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
private final EmployeeEntityDAO entityDAO;
    private final EmployeeProfileDAO profileDAO;

    public EmployeeService(EmployeeEntityDAO entityDAO, EmployeeProfileDAO profileDAO) {
        this.entityDAO = entityDAO;
        this.profileDAO = profileDAO;
    }

    /**
     * Creates a new employee record using the provided entity data and role for authorization.
     * All operations occur in a single transaction.
     *
     * @param emp  the EmployeeEntity containing FK IDs and basic info
     * @param role the role of the user performing the operation (for authorization)
     * @return generated employee ID
     * @throws SQLException on DB errors or authorization failure
     */
    public int createEmployee(EmployeeEntity emp, String role) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                int newId = entityDAO.addEmployee(emp);
                conn.commit();
                return newId;
            } catch (SQLException | SecurityException ex) {
                conn.rollback();
                throw ex;
            } finally {
                conn.setAutoCommit(true);
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
