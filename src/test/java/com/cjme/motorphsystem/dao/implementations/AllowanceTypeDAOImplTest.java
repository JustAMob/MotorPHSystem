/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.model.AllowanceType;
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
    
    public AllowanceTypeDAOImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addAllowanceType method, of class AllowanceTypeDAOImpl.
     */
    @Test
    public void testAddAllowanceType() throws Exception {
        System.out.println("addAllowanceType");
        AllowanceType allowance = null;
        AllowanceTypeDAOImpl instance = new AllowanceTypeDAOImpl();
        instance.addAllowanceType(allowance);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllAllowanceTypes method, of class AllowanceTypeDAOImpl.
     */
    @Test
    public void testGetAllAllowanceTypes() throws Exception {
        System.out.println("getAllAllowanceTypes");
        AllowanceTypeDAOImpl instance = new AllowanceTypeDAOImpl();
        List<AllowanceType> expResult = null;
        List<AllowanceType> result = instance.getAllAllowanceTypes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllowanceTypeById method, of class AllowanceTypeDAOImpl.
     */
    @Test
    public void testGetAllowanceTypeById() throws Exception {
        System.out.println("getAllowanceTypeById");
        int id = 0;
        AllowanceTypeDAOImpl instance = new AllowanceTypeDAOImpl();
        AllowanceType expResult = null;
        AllowanceType result = instance.getAllowanceTypeById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateAllowanceType method, of class AllowanceTypeDAOImpl.
     */
    @Test
    public void testUpdateAllowanceType() throws Exception {
        System.out.println("updateAllowanceType");
        AllowanceType allowance = null;
        AllowanceTypeDAOImpl instance = new AllowanceTypeDAOImpl();
        instance.updateAllowanceType(allowance);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
