/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.cjme.motorphsystem.dao.deductions;

import com.cjme.motorphsystem.model.deductions.PagibigContribution;
import com.cjme.motorphsystem.util.DBConnection;
import java.sql.Connection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JustAMob
 */
public class PagibigDAOTest {

    @Test
    public void testGetRatesForSalary() throws Exception {
        // Use your utility class to get the database connection
        Connection connection = DBConnection.getConnection();
        PagibigDAO pagibigDAO = new PagibigDAO(connection);

        // Salary for the test
        double salary = 2000;

        // Get the rates from the DAO
        PagibigContribution contribution = pagibigDAO.getRatesForSalary(salary);

        // Expected rates (from your database; these should match what's in the database)
        double expectedEmployeeRate = 0.02; // Example rate (replace with actual)
        double expectedEmployerRate = 0.02; // Example rate (replace with actual)

        // Test if the rates are correct
        assertEquals(expectedEmployeeRate, contribution.getEmployeeRate(), 0.0001);
        assertEquals(expectedEmployerRate, contribution.getEmployerRate(), 0.0001);
    }
}