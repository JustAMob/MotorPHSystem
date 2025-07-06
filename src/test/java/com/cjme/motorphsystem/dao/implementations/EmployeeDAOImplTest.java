/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.model.EmployeeEntity;
import com.cjme.motorphsystem.util.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Assume;

/**
 *
 * @author JustAMob
 */
public class EmployeeDAOImplTest {

    private static Connection conn;
    private EmployeeEntityDAOImpl dao;
    private static int testEmployeeId = 101;

    @BeforeClass
    public static void setupDB() {
        conn = DBConnection.getConnection();
        assertNotNull("DB connection should not be null", conn);
    }

    @Before
    public void setUp() {
        dao = new EmployeeEntityDAOImpl();
        testCreateEmployee();
    }

    @After
    public void cleanupEachTest() throws SQLException {
        if (testEmployeeId > 0) {
            dao.deleteEmployee(testEmployeeId, "admin");
            testEmployeeId = -1;
        }
    }

    
    public void testCreateEmployee() {
        EmployeeEntity emp = new EmployeeEntity();
        emp.setFirstName("John");
        emp.setLastName("Doe");
        emp.setAddressId(111);
        emp.setPhoneNumber(123456789);
        emp.setGovernmentId(111);
        emp.setDepartmentId(1);
        emp.setSalaryId(1);
        emp.setSupervisorId(1);
        emp.setStatusId(1);
        emp.setPositionId(1);
        emp.setBirthday(Date.valueOf("1990-01-01"));

        int generatedId = dao.addEmployee(emp, "admin");
        assertTrue("Failed to insert employee", generatedId > 0);
        
        testEmployeeId = generatedId;
         
        EmployeeEntity fromDb = dao.getEmployeeById(generatedId);
        assertNotNull("Inserted employee should be retrievable", fromDb);
        assertEquals("First name mismatch", "John", fromDb.getFirstName());
    }

    @Test
    public void testReadEmployee() {
        Assume.assumeTrue(testEmployeeId != -1);

        EmployeeEntity emp = dao.getEmployeeById(testEmployeeId);
        assertNotNull(emp);
        assertEquals("John", emp.getFirstName());
    }

    @Test
    public void testUpdateEmployee() {
        Assume.assumeTrue(testEmployeeId != -1);

        EmployeeEntity emp = dao.getEmployeeById(testEmployeeId);
        emp.setFirstName("Jane");
        emp.setLastName("Smith");

        dao.updateEmployee(emp, "hr");

        EmployeeEntity updated = dao.getEmployeeById(testEmployeeId);
        assertEquals("Jane", updated.getFirstName());
        assertEquals("Smith", updated.getLastName());
    }

    @Test
    public void testDeleteEmployee() {
        Assume.assumeTrue(testEmployeeId != -1);

        dao.deleteEmployee(testEmployeeId, "admin");
        EmployeeEntity deleted = dao.getEmployeeById(testEmployeeId);
        assertNull(deleted);

        testEmployeeId = -1; // Reset to avoid false positives
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        if (testEmployeeId != -1) {
            try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM employee WHERE employee_id = ?")) {
                stmt.setInt(1, testEmployeeId);
                stmt.executeUpdate();
            }
        }

        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}