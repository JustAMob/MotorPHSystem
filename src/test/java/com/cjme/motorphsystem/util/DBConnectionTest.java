/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.cjme.motorphsystem.util;

import org.junit.Test;
import java.sql.Connection;
import static org.junit.Assert.*;

public class DBConnectionTest {

    @Test
    public void testDatabaseConnection() {
        Connection conn = DBConnection.getConnection();

        assertNotNull("❌ Database connection should not be null", conn);

        if (conn != null) {
            System.out.println("✅ Successfully connected to the database.");
        }
    }
}

