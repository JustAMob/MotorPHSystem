package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.dao.PayslipDAO;
import com.cjme.motorphsystem.model.Payslip;
import com.cjme.motorphsystem.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for payslip data access, used for report generation.
 */
public class PayslipDAOImpl implements PayslipDAO {
    private final Connection conn;

    /**
     * Constructor with external connection.
     * @param conn the database connection
     */
    public PayslipDAOImpl(Connection conn) {
        this.conn = conn;
    }

    /**
     * Default constructor using DBConnection utility.
     */
    public PayslipDAOImpl() {
        this(DBConnection.getConnection());
    }

    /**
     * Fetches a payslip for the report by employee search (name or ID) and period.
     * @param searchEmployee Employee name or ID as String
     * @param periodStartDate Start date of the pay period
     * @param periodEndDate End date of the pay period
     * @return Payslip object or null if not found
     */
@Override
public Payslip getPayslipForReport(String searchEmployee, java.sql.Date periodStartDate, java.sql.Date periodEndDate) {
    Payslip payslip = null;
    StringBuilder sql = new StringBuilder("SELECT * FROM employee_payslip_summary WHERE 1=1");

    List<Object> parameters = new ArrayList<>();

    if (searchEmployee != null && !searchEmployee.trim().isEmpty()) {
        sql.append(" AND (CAST(Employee_ID AS CHAR) = ? OR LOWER(Employee_Name) LIKE LOWER(CONCAT('%', ?, '%')))");
        parameters.add(searchEmployee);
        parameters.add(searchEmployee);
    }

    if (periodStartDate != null) {
        sql.append(" AND Period_Start_Date = ?");
        parameters.add(periodStartDate);
    }

    if (periodEndDate != null) {
        sql.append(" AND Period_End_Date = ?");
        parameters.add(periodEndDate);
    }

    try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
        for (int i = 0; i < parameters.size(); i++) {
            stmt.setObject(i + 1, parameters.get(i));
        }

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                payslip = new Payslip();
                payslip.setPayslipNo(rs.getInt("Payslip_No"));
                payslip.setEmployeeId(rs.getInt("Employee_ID"));
                payslip.setPeriodStartDate(rs.getDate("Period_Start_Date"));
                payslip.setPeriodEndDate(rs.getDate("Period_End_Date"));
                payslip.setEmployeeName(rs.getString("Employee_Name"));
                payslip.setEmployeePositionDepartment(rs.getString("Employee_Position_Department"));
                payslip.setMonthCovered(rs.getInt("month_covered"));
                payslip.setYearCovered(rs.getInt("year_covered"));
                payslip.setMonthlyRate(rs.getDouble("Monthly_Rate"));
                payslip.setHourlyRate(rs.getDouble("Hourly_Rate"));
                payslip.setRegularHours(rs.getDouble("Regular_Hours"));
                payslip.setOvertimeHours(rs.getDouble("Overtime_Hours"));
                payslip.setTotalHoursWorked(rs.getDouble("Total_Hours_Worked"));
                payslip.setOvertimeIncome(rs.getDouble("Overtime_Income"));
                payslip.setGrossIncome(rs.getDouble("Gross_Income"));
                payslip.setRiceSubsidy(rs.getDouble("rice_subsidy"));
                payslip.setPhoneAllowance(rs.getDouble("phone_allowance"));
                payslip.setClothingAllowance(rs.getDouble("clothing_allowance"));
                payslip.setTotalAllowance(rs.getDouble("Benefits_Total"));
                payslip.setSocialSecuritySystem(rs.getDouble("Social_Security_System"));
                payslip.setPhilhealth(rs.getDouble("Philhealth"));
                payslip.setPagibig(rs.getDouble("Pagibig"));
                payslip.setWitholdingtax(rs.getDouble("Withholding_Tax"));
                payslip.setTotalDeduction(rs.getDouble("Total_Deductions"));
                payslip.setSummaryGross(rs.getDouble("Summary_Gross"));
                payslip.setSummaryBenefits(rs.getDouble("Summary_Benefits"));
                payslip.setSummaryDeductions(rs.getDouble("Summary_Deductions"));
                payslip.setTakeHomePay(rs.getDouble("Take_Home_Pay"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return payslip;
}
}
