/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.service;

import com.cjme.motorphsystem.dao.PayslipDAO;
import com.cjme.motorphsystem.dao.implementations.PayslipDAOImpl;
import com.cjme.motorphsystem.model.Payslip;
import com.cjme.motorphsystem.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.SQLException;

/**
 *
 * @author JustAMob
 */
public class PayslipService {
    private final PayslipDAO payslipDAO;
    

    public PayslipService(PayslipDAO payslipDAO) {
        this.payslipDAO = payslipDAO;
    }

    public PayslipService() {
        this.payslipDAO = new PayslipDAOImpl();
    }

   
     
    public Payslip getPayslip(int employeeId, int month, int year) {
        return payslipDAO.getPayslipByEmployeeIdAndMonth(employeeId, month, year);
    }
      

   

     public Payslip generatePayslipForEmployee(int employeeId, int month, int year) {
        Payslip payslip = null;

        String sql = "SELECT * FROM employee_payslip_summary " +
                     "WHERE employee_id = ? " +
                     "AND month_covered = ? " +
                     "AND year_covered = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            stmt.setInt(2, month);
            stmt.setInt(3, year);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                payslip = new Payslip();
                payslip.setEmployeeId(rs.getInt("employee_id"));
                payslip.setPeriodStartDate(rs.getDate("period_start_date"));
                payslip.setPeriodEndDate(rs.getDate("period_end_date"));
                payslip.setMonthlyRate(rs.getBigDecimal("monthly_rate"));
                payslip.setGrossIncome(rs.getBigDecimal("gross_income"));
                payslip.setBenefitsTotal(rs.getBigDecimal("benefits_total"));
                payslip.setSocialSecuritySystem(rs.getBigDecimal("social_security_system"));
                payslip.setPhilhealth(rs.getBigDecimal("philhealth"));
                payslip.setPagibig(rs.getBigDecimal("pagibig"));
                payslip.setWithholdingTax(rs.getBigDecimal("withholding_tax"));
                payslip.setTotalDeductions(rs.getBigDecimal("total_deductions"));
                payslip.setTakeHomePay(rs.getBigDecimal("take_home_pay"));

                // Optional fields
                payslip.setEmployeeName(rs.getString("employee_name"));
                payslip.setEmployeePositionDepartment(rs.getString("employee_position_department"));
            }

        } catch (SQLException e) {
            // Replace with logging framework in production
            
        }

        return payslip;
    }

    public void savePayslip(Payslip payslip) {
        payslipDAO.addPayslip(payslip);
    }

    public void generateAndSavePayslip(int employeeId, int month, int year) {
        Payslip payslip = generatePayslipForEmployee(employeeId, month, year);
        if (payslip != null) {
            savePayslip(payslip);
        } else {
            System.out.println("No payslip data found for Employee ID: " + employeeId + " in " + month + "/" + year);
        }
    }

}
