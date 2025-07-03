/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.dao.PayslipDAO;
import com.cjme.motorphsystem.model.Payslip;
import com.cjme.motorphsystem.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JustAMob
 */
public class PayslipDAOImpl implements PayslipDAO {
    @Override
    public Payslip getPayslipByEmployeeIdAndMonth(int employeeId, int month, int year) {
        Payslip payslip = null;

        String sql = "SELECT * FROM v_employee_payslip_summary " +
                     "WHERE employee_id = ? AND month_covered = ? AND year_covered = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            stmt.setInt(2, month);
            stmt.setInt(3, year);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                payslip = new Payslip();
                payslip.setPayslipNo(rs.getInt("Payslip_No"));
                payslip.setEmployeeId(rs.getInt("Employee_ID"));
                payslip.setEmployeeName(rs.getString("Employee_Name"));
                payslip.setPeriodStartDate(rs.getDate("Period_Start_Date"));
                payslip.setPeriodEndDate(rs.getDate("Period_End_Date"));
                payslip.setEmployeePositionDepartment(rs.getString("Employee_Position_Department"));
                payslip.setMonthlyRate(rs.getBigDecimal("Monthly_Rate"));
                payslip.setHourlyRate(rs.getBigDecimal("Hourly_Rate"));
                payslip.setHoursWorked(rs.getBigDecimal("Total_Hours_Worked"));
                payslip.setOvertimeIncome(rs.getBigDecimal("Overtime_Income"));
                payslip.setGrossIncome(rs.getBigDecimal("Gross_Income"));
                payslip.setRiceSubsidy(rs.getBigDecimal("rice_subsidy"));
                payslip.setPhoneAllowance(rs.getBigDecimal("phone_allowance"));
                payslip.setClothingAllowance(rs.getBigDecimal("clothing_allowance"));
                payslip.setBenefitsTotal(rs.getBigDecimal("Benefits_Total"));
                payslip.setSocialSecuritySystem(rs.getBigDecimal("Social_Security_System"));
                payslip.setPhilhealth(rs.getBigDecimal("Philhealth"));
                payslip.setPagibig(rs.getBigDecimal("Pagibig"));
                payslip.setWithholdingTax(rs.getBigDecimal("Withholding_Tax"));
                payslip.setTotalDeductions(rs.getBigDecimal("Total_Deductions"));
                payslip.setSummaryGross(rs.getBigDecimal("Summary_Gross"));
                payslip.setSummaryBenefits(rs.getBigDecimal("Summary_Benefits"));
                payslip.setSummaryDeductions(rs.getBigDecimal("Summary_Deductions"));
                payslip.setTakeHomePay(rs.getBigDecimal("Take_Home_Pay"));
            }

        } catch (SQLException e) {
           
            
        }

        return payslip;
    }
    
    @Override
    public void addPayslip(Payslip payslip) {
    String sql = "INSERT INTO payslip (employee_id, payroll_id, period_start_date, period_end_date, basic_salary)" +
            "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, payslip.getEmployeeId());
            stmt.setDate(2, payslip.getPeriodStartDate()); 
            stmt.setDate(3, payslip.getPeriodEndDate());  
            stmt.setBigDecimal(4, payslip.getMonthlyRate());

            int rows = stmt.executeUpdate();
            System.out.println("Rows inserted: " + rows);
        } catch (SQLException e) {
            e.printStackTrace(); 
            System.out.println("Failed to insert payslip for employee ID: " + payslip.getEmployeeId());
        }
    }
}
