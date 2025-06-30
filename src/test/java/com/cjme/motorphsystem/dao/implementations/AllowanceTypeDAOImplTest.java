/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.dao.implementations.AllowanceTypeDAOImpl;
import com.cjme.motorphsystem.model.AllowanceType;
import com.cjme.motorphsystem.util.DBConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
public class AllowanceTypeDAOImplTest {

    private static Connection connection;
    private AllowanceTypeDAOImpl dao;

    @Before
    public void setUp() throws SQLException {
        connection = DBConnection.getConnection();
        connection.setAutoCommit(false); 
        dao = new AllowanceTypeDAOImpl(connection);
        clearTable();
    }

    /**
     *
     * @throws SQLException
     */
    @After
    public void tearDown() throws SQLException {
        connection.rollback(); // undo any DB changes made during test
        connection.setAutoCommit(true);
        connection.close();
    }
    private void clearTable() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM employee_allowance"); // child table first
            stmt.executeUpdate("DELETE FROM position_allowance_type"); // also child
            stmt.executeUpdate("DELETE FROM allowance_type");      // then parent table
        }
    }

    @Test
    public void testAddAndGetAllowanceType() throws SQLException {
        AllowanceType a = new AllowanceType();
        a.setCode("TEST");
        a.setCategory("Test Category");
        a.setDescription("JUnit test desc");
        a.setAmount(new BigDecimal("1000.00"));

        dao.addAllowanceType(a);

        List<AllowanceType> all = dao.getAllAllowanceTypes();
        assertEquals(1, all.size());

        AllowanceType fromDb = all.get(0);
        assertEquals("TEST", fromDb.getCode());
        assertEquals("Test Category", fromDb.getCategory());
        assertEquals("JUnit test desc", fromDb.getDescription());
        assertEquals(new BigDecimal("1000.00"), fromDb.getAmount());

        AllowanceType byId = dao.getAllowanceTypeById(fromDb.getAllowanceTypeId());
        assertNotNull(byId);
        assertEquals(fromDb.getCode(), byId.getCode());
    }

    @Test
    public void testUpdateAllowanceType() throws SQLException {
        // Insert initial record
        AllowanceType a = new AllowanceType();
        a.setCode("OT");
        a.setCategory("Overtime");
        a.setDescription("OT Rate");
        a.setAmount(new BigDecimal("200.00"));
        dao.addAllowanceType(a);

        // Get inserted record to fetch auto-incremented ID
        AllowanceType inserted = dao.getAllAllowanceTypes().get(0);
        inserted.setCode("OT_UPDATED");
        inserted.setCategory("Updated Category");
        inserted.setDescription("Updated desc");
        inserted.setAmount(new BigDecimal("250.00"));

        dao.updateAllowanceType(inserted);

        AllowanceType updated = dao.getAllowanceTypeById(inserted.getAllowanceTypeId());
        assertEquals("OT_UPDATED", updated.getCode());
        assertEquals("Updated Category", updated.getCategory());
        assertEquals("Updated desc", updated.getDescription());
        assertEquals(new BigDecimal("250.00"), updated.getAmount());
    }
}