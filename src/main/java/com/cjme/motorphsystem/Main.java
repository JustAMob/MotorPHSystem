package com.cjme.motorphsystem;

import com.cjme.motorphsystem.dao.EmployeeDAO;
import com.cjme.motorphsystem.model.Attendance;
import com.cjme.motorphsystem.model.Employee;
import com.cjme.motorphsystem.util.DBConnection;
import com.cjme.motorphsystem.util.DeductionUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author JustAMob
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
       
        
        
        
//        try (Connection connection = DBConnection.getConnection()) {
//            DeductionUtil.fillUpDeductions(connection);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        

        // TODO code application logic here
//        Connection conn = DBConnection.getConnection();
//        if (conn != null) {
//            System.out.println("Connected to DB!");
//        } else {
//            System.out.println("Failed to connect.");
//        }
//         try {
//
//            if (conn != null) {
//                System.out.println("Connected to DB!");
//
//                EmployeeDAO employeeDAO = new EmployeeDAO(); // Pass the connection
//
//                List<Employee> employees = employeeDAO.getAllEmployees();
//
//                for (Employee emp : employees) {
//                    System.out.println(emp); // This uses the toString() method in your Employee class
//                }
//
//            } else {
//                System.out.println("Failed to connect.");
//            }
//
//        } catch (SQLException e) {
//        }
      }
    
}
