/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.util.DBConnection;
import java.sql.Connection;
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
public class SssDAOTest {
    private SssDAO sssDAO;

    @Before
    public void setUp() throws SQLException {
        Connection connection = DBConnection.getConnection();
        sssDAO = new SssDAO(connection);
    }

    @Test
    public void testGetContribution_LowSalaryTier() {
        double salary = 3000.00;
        double contribution = sssDAO.getContributionForSalary(salary);
        assertEquals("Contribution for low salary tier incorrect", 135.00, contribution, 0.01);
    }

    @Test
    public void testGetContribution_MiddleSalaryTier() {
        double salary = 15000.00;
        double contribution = sssDAO.getContributionForSalary(salary);
        assertEquals("Contribution for middle salary tier incorrect", 675.00, contribution, 0.01);
    }

    @Test
    public void testGetContribution_UpperSalaryTier() {
        double salary = 22350.00;
        double contribution = sssDAO.getContributionForSalary(salary);
        assertEquals("Contribution for upper salary tier incorrect", 1012.50, contribution, 0.01);
    }

    @Test
    public void testGetContribution_OverMaxSalary() {
        double salary = 90000.00;
        double contribution = sssDAO.getContributionForSalary(salary);
        assertEquals("Contribution for over max salary incorrect", 1125.00, contribution, 0.01);
    }

    @Test
    public void testGetContribution_AtTierBoundary() {
        double salary = 6250.00;
        double contribution = sssDAO.getContributionForSalary(salary);
        assertEquals("Contribution at tier boundary incorrect", 292.50, contribution, 0.01);
    }

    @Test
    public void testGetContribution_NoMatchReturnsZero() {
        double salary = -100.00;
        double contribution = sssDAO.getContributionForSalary(salary);
        assertEquals("Invalid salary should return 0", 0.00, contribution, 0.01);
    }
}
