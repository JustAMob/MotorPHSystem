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
public class PhilHealthDAOTest {
     private PhilHealthDAO philHealthDAO;
     
    @Before
    public void setUp() throws SQLException {
        Connection connection = DBConnection.getConnection();
        philHealthDAO = new PhilHealthDAO(connection);
    }

    @Test
    public void testGetPremiumRate_AboveMinimumThreshold() {
        double salary = 15000.00; 
        double rate = philHealthDAO.getPremiumRate(salary);

        assertEquals("Expected premium rate to be 0 for below minimum salary", 0.03, rate, 0.0001);
    }

    @Test
    public void testGetPremiumRate_AtMinimumThreshold() {
        double salary = 10000.00;
        double rate = philHealthDAO.getPremiumRate(salary);

         assertEquals("Expected premium rate to be 0 for below minimum salary", 0.03, rate, 0.0001);
    }

    @Test
    public void testGetPremiumRate_BelowMinimumThreshold() {
        double salary = 100.00;
        double rate = philHealthDAO.getPremiumRate(salary);

        assertEquals("Expected premium rate to be 0 for below minimum salary", 0.0, rate, 0.0001);
    }

}
