package com.cjme.motorphsystem.dao.deductions;

import com.cjme.motorphsystem.util.DBConnection;
import org.junit.Test;
import java.sql.Connection;
import static org.junit.Assert.*;

/**
 *
 * @author JustAMob
 */

public class SssDAOTest {


    @Test
    public void testGetContributionForSalary() {
        Connection connection = DBConnection.getConnection();
        SssDAO sssDAO = new SssDAO(connection);
        double salary = 1000;
        double expected = 135.00;
        double actual = sssDAO.getContributionForSalary(salary);

        assertEquals(expected, actual, 0.01);
    }
}
