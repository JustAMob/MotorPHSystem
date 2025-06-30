/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import java.sql.SQLException;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author JustAMob
 */
public class TaxDAOTest {

    private TaxDAO taxDAO;
    
    @Before
    public void setUp() {
        try {
            
            taxDAO = new TaxDAO();
        } catch (SQLException e) {
            fail("Failed to initialize TaxDAO: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        // Clean up if necessary after each test
        taxDAO = null;
    }

    @Test
    public void testCalculateTax() throws SQLException {
        double salary = 35000.0;

       
        double calculatedTax = taxDAO.calculateTax(salary);

        double expectedTax = 2916.75;

        assertEquals(expectedTax, calculatedTax, 0.01);
    }

    @Test
    public void testCalculateTaxForEdgeSalary() throws SQLException {
        // Test for an edge case where salary is exactly at an income threshold
        double salary = 66667.0;

        double calculatedTax = taxDAO.calculateTax(salary);

        double expectedTax = 10833.5;

        assertEquals(expectedTax, calculatedTax, 0.01);
    }

    @Test
    public void testCalculateTaxWithLowSalary() throws SQLException {
        double salary = 15000.0;

        double calculatedTax = taxDAO.calculateTax(salary);

        double expectedTax = 0.0; 

        assertEquals(expectedTax, calculatedTax, 0.01);
    }
}