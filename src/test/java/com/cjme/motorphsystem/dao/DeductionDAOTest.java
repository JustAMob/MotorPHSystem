/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.dao.DeductionDAO.DeductionRecord;
import com.cjme.motorphsystem.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class DeductionDAOTest {

    private static Connection connection;
    private DeductionDAO deductionDAO;
    private static final int TEST_EMPLOYEE_ID = 9999;

    @BeforeClass
    public static void setupDatabaseConnection() throws SQLException {
        connection = DBConnection.getConnection();
    }

    @AfterClass
    public static void closeDatabaseConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Before
    public void setUp() throws SQLException {
        deductionDAO = new DeductionDAO(connection);

        // Ensure test employee exists
        try (PreparedStatement insertEmp = connection.prepareStatement(
                "INSERT IGNORE INTO employee (employee_id, first_name, last_name, position_id) VALUES (?, 'Test', 'User', 1)")) {
            insertEmp.setInt(1, TEST_EMPLOYEE_ID);
            insertEmp.executeUpdate();
        }

        // Clean up any existing deduction rows for test employee
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM deduction WHERE employee_id = ?")) {
            stmt.setInt(1, TEST_EMPLOYEE_ID);
            stmt.executeUpdate();
        }
    }


 

    @Test
    public void testGetTotalDeductions_NoRecord() throws SQLException {
        double total = deductionDAO.getTotalDeductions(123456);
        assertEquals(0.0, total, 0.001);
    }

    @Test
    public void testGetDetailedDeductions_NoRecord() throws SQLException {
        DeductionRecord record = deductionDAO.getDetailedDeductions(123456); 
        assertNull(record);
    }
}
