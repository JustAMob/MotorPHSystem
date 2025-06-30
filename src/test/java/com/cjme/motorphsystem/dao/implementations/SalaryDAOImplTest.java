/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.model.Salary;
import com.cjme.motorphsystem.util.DBConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JustAMob
 */
public class SalaryDAOImplTest {

    private static Connection conn;
    private SalaryDAOImpl dao;
    private static int testSalaryId = -1;

    @BeforeClass
    public static void setupClass() {
        conn = DBConnection.getConnection();
        assertNotNull("Database connection should not be null", conn);
    }

    @Before
    public void setUp() throws SQLException {
        dao = new SalaryDAOImpl();

        Salary salary = new Salary();
        salary.setBasicSalary(new BigDecimal("20000.00"));
        salary.setGrossSemiMonthlyRate(new BigDecimal("10000.00"));
        salary.setHourlyRate(new BigDecimal("125.00"));

        dao.addSalary(salary);

        // Retrieve the inserted record to get the auto-generated ID
        List<Salary> all = dao.getAllSalaries();
        testSalaryId = all.get(all.size() - 1).getSalaryId(); // Get last inserted
        assertTrue("Failed to set testSalaryId", testSalaryId > 0);
    }

    @Test
    public void testGetSalaryById() throws SQLException {
        Salary salary = dao.getSalaryById(testSalaryId);
        assertNotNull("Salary should be found", salary);
        assertEquals(new BigDecimal("20000.00"), salary.getBasicSalary());
    }

    @Test
    public void testGetHourlyRateBySalaryId() throws SQLException {
        BigDecimal hourlyRate = dao.getHourlyRateBySalaryId(testSalaryId);
        assertNotNull(hourlyRate);
        assertEquals(new BigDecimal("125.00"), hourlyRate);
    }

    @Test
    public void testUpdateSalary() throws SQLException {
        Salary salary = dao.getSalaryById(testSalaryId);
        salary.setBasicSalary(new BigDecimal("25000.00"));
        salary.setHourlyRate(new BigDecimal("150.00"));

        dao.updateSalary(salary);

        Salary updated = dao.getSalaryById(testSalaryId);
        assertEquals(new BigDecimal("25000.00"), updated.getBasicSalary());
        assertEquals(new BigDecimal("150.00"), updated.getHourlyRate());
    }

    @Test
    public void testGetAllSalaries() throws SQLException {
        List<Salary> salaries = dao.getAllSalaries();
        assertNotNull(salaries);
        assertTrue("Salary list should not be empty", !salaries.isEmpty());
    }

    @Test
    public void testDeleteSalary() throws SQLException {
        dao.deleteSalary(testSalaryId);

        Salary deleted = dao.getSalaryById(testSalaryId);
        assertNull("Salary should be deleted", deleted);

        testSalaryId = -1; // Prevent @After cleanup from re-deleting
    }

    @After
    public void tearDown() throws SQLException {
        if (testSalaryId > 0) {
            dao.deleteSalary(testSalaryId);
            testSalaryId = -1;
        }
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
