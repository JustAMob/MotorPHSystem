package com.cjme.motorphsystem.util;

/**
 *
 * @author JustAMob
 */
import com.cjme.motorphsystem.dao.PagibigDAO;
import com.cjme.motorphsystem.dao.PhilHealthDAO;
import com.cjme.motorphsystem.dao.SssDAO;
import com.cjme.motorphsystem.model.deductions.PagibigDeduction;
import com.cjme.motorphsystem.model.deductions.PhilHealthDeduction;
import com.cjme.motorphsystem.model.deductions.SssDeduction;
import java.sql.*;

public class DeductionUtil {


    public static void fillUpDeductions(Connection connection) {
    String employeeQuery = "SELECT employee_id, basic_salary FROM employee";
    String insertDeductionQuery = "INSERT INTO deductions (employee_id, sss_deduction, philhealth_deduction, pagibig_deduction) VALUES (?, ?, ?, ?)";

        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(employeeQuery);
            PreparedStatement ps = connection.prepareStatement(insertDeductionQuery)) {

            SssDAO sssDAO = new SssDAO(connection); 
            PhilHealthDAO philDAO = new PhilHealthDAO(connection);
            PagibigDAO pagibigDAO = new PagibigDAO(connection);
            
            connection.setAutoCommit(true);

            while (rs.next()) {
                int employeeId = rs.getInt("employee_id");
                double basicSalary = rs.getDouble("basic_salary");

                double sssDeduction = new SssDeduction(basicSalary, sssDAO).calculate();
                double philDeduction = new PhilHealthDeduction(basicSalary, philDAO).calculate();
                double pagibigDeduction = new PagibigDeduction(basicSalary, pagibigDAO).calculate();

                ps.setInt(1, employeeId);
                ps.setDouble(2, sssDeduction);
                ps.setDouble(3, philDeduction);
                ps.setDouble(4, pagibigDeduction);


                int rows = ps.executeUpdate();
                System.out.println("Inserted deduction for Employee ID: " + employeeId + " | Rows affected: " + rows);
            }

             System.out.println("✅ Deductions filled successfully.");

        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
}

