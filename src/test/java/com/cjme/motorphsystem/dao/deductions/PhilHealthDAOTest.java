package com.cjme.motorphsystem.dao.deductions;

import com.cjme.motorphsystem.dao.PhilHealthDAO;
import com.cjme.motorphsystem.util.DBConnection;
import java.sql.Connection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JustAMob
 */


public class PhilHealthDAOTest {
    
    @Test
    public void testGetPremiumRate() throws Exception {
        Connection connection = DBConnection.getConnection();
        PhilHealthDAO dao = new PhilHealthDAO(connection);

        double expected = 0.0300; 
        double actual = dao.getPremiumRate();

        assertEquals(expected, actual, 0.0001);
    }
}
