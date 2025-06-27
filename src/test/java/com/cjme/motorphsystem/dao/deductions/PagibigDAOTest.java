package com.cjme.motorphsystem.dao.deductions;

import com.cjme.motorphsystem.dao.PagibigDAO;
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
        Connection connection = DBConnection.getConnection();
        PagibigDAO pagibigDAO = new PagibigDAO(connection);

        // Salary for the test
        double salary = 2000;

        // Get the rates from the DAO
        PagibigContribution contribution = pagibigDAO.getRatesForSalary(salary);

        // Expected rates 
        double expectedEmployeeRate = 0.02; 
        double expectedEmployerRate = 0.02; 

        // Test if the rates are correct
        assertEquals(expectedEmployeeRate, contribution.getEmployeeRate(), 0.0001);
        assertEquals(expectedEmployerRate, contribution.getEmployerRate(), 0.0001);
    }
}