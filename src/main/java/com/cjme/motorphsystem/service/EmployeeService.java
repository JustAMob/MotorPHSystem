package com.cjme.motorphsystem.service;

/**
 *
 * @author JustAMob
 */
import com.cjme.motorphsystem.dao.AddressDAO;
import com.cjme.motorphsystem.dao.DepartmentDAO;
import com.cjme.motorphsystem.dao.EmployeeEntityDAO;
import com.cjme.motorphsystem.dao.EmployeeProfileDAO;
import com.cjme.motorphsystem.dao.EmploymentStatusDAO;
import com.cjme.motorphsystem.dao.GovernmentIdDAO;
import com.cjme.motorphsystem.dao.PositionDAO;
import com.cjme.motorphsystem.dao.SalaryDAO;
import com.cjme.motorphsystem.dao.SupervisorDAO;
import com.cjme.motorphsystem.dao.implementations.DepartmentDAOImpl;
import com.cjme.motorphsystem.dao.implementations.EmploymentStatusDAOImpl;
import com.cjme.motorphsystem.dao.implementations.PositionDAOImpl;
import com.cjme.motorphsystem.model.Address;
import com.cjme.motorphsystem.model.Department;
import com.cjme.motorphsystem.model.EmployeeEntity;
import com.cjme.motorphsystem.model.EmployeeProfile;
import com.cjme.motorphsystem.model.EmploymentStatus;
import com.cjme.motorphsystem.model.GovernmentID;
import com.cjme.motorphsystem.model.Position;
import com.cjme.motorphsystem.model.Salary;
import com.cjme.motorphsystem.model.Supervisor;
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
    private final SupervisorDAO supervisorDAO;
    private static final DepartmentDAO departmentDAO = new DepartmentDAOImpl();
    private static final PositionDAO positionDAO = new PositionDAOImpl();
    private static final EmploymentStatusDAO statusDAO = new EmploymentStatusDAOImpl();

    public EmployeeService(EmployeeEntityDAO entityDAO, EmployeeProfileDAO profileDAO, AddressDAO addressDAO, GovernmentIdDAO governmentDAO, SalaryDAO salaryDAO, SupervisorDAO supervisorDAO) {
        this.entityDAO = entityDAO;
        this.profileDAO = profileDAO;
        this.addressDAO = addressDAO;
        this.governmentDAO = governmentDAO;
        this.salaryDAO = salaryDAO;
        this.supervisorDAO = supervisorDAO;
    }







    public int insertNewEmployee( Address address, GovernmentID govId, Salary salary,EmployeeEntity employee) throws SQLException {
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
    public void updateEmployee( Address address, GovernmentID govId, Salary salary,EmployeeEntity emp) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                
                addressDAO.updateAddress(address, conn);
                governmentDAO.updateGovernmentId(govId, conn); 
                salaryDAO.updateSalary(salary, conn); 
                entityDAO.updateEmployee(emp, conn);
                conn.commit();
            } catch (SQLException | SecurityException ex) {
                conn.rollback();
                throw ex;
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
    public Address getAddressByEmployeeId(int employeeId) throws SQLException {
        EmployeeEntity emp = entityDAO.getEmployeeById(employeeId);
        if (emp != null) {
            return addressDAO.getAddressById(emp.getAddressId());
        }
        return null;
    }

    public GovernmentID getGovernmentIdByEmployeeId(int employeeId) throws SQLException {
        EmployeeEntity emp = entityDAO.getEmployeeById(employeeId);
        if (emp != null) {
            return governmentDAO.getGovernmentIdByEmployeeId(employeeId);
        }
        return null;
    }

    public Salary getSalaryByEmployeeId(int employeeId) throws SQLException {
        EmployeeEntity emp = entityDAO.getEmployeeById(employeeId);
        if (emp != null) {
            return salaryDAO.getSalaryById(emp.getSalaryId());
        }
        return null;
    }
    public Supervisor getSupervisorByEmployeeId(int employeeId) throws SQLException {
        EmployeeEntity emp = entityDAO.getEmployeeById(employeeId);
        if (emp != null) {
            return supervisorDAO.getSupervisorById(emp.getSupervisorId());
        }
        return null;
    }
    
    public static int getDepartmentIdByName(String departmentName) throws SQLException {
        Department department = departmentDAO.getDepartmentByName(departmentName);
        if (department == null) {
            throw new IllegalArgumentException("Department not found: " + departmentName);
        }
        return department.getDepartmentId();
    }

    public static int getPositionIdByName(String positionName) throws SQLException {
        Position position = positionDAO.getPositionByName(positionName);
        if (position == null) {
            throw new IllegalArgumentException("Position not found: " + positionName);
        }
        return position.getPositionId();
    }

    public static int getStatusIdByName(String statusName) throws SQLException {
        EmploymentStatus status = statusDAO.getStatusByName(statusName);
        if (status == null) {
            throw new IllegalArgumentException("Employment Status not found: " + statusName);
        }
        return status.getStatusId();
    }
    
    
    
    
}
