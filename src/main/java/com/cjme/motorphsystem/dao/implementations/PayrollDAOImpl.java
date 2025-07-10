package com.cjme.motorphsystem.dao.implementations;

import com.cjme.motorphsystem.dao.PayrollDAO;
import com.cjme.motorphsystem.model.Payroll;
import com.cjme.motorphsystem.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PayrollDAOImpl implements PayrollDAO {
    private final Connection conn;

    public PayrollDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public PayrollDAOImpl() {
        this(DBConnection.getConnection());
    }

    @Override
    public List<Payroll> getMonthSummary(String payPeriod, String department) throws SQLException {
        List<Payroll> payrollList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM month_summary_view WHERE 1=1");
        if (payPeriod != null && !payPeriod.isEmpty()) {
            sql.append(" AND pay_period_id = ?");
        }
        if (department != null && !department.isEmpty()) {
            sql.append(" AND department = ?");
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int idx = 1;
            if (payPeriod != null && !payPeriod.isEmpty()) {
                stmt.setString(idx++, payPeriod);
            }
            if (department != null && !department.isEmpty()) {
                stmt.setString(idx++, department);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Payroll payroll = new Payroll();
                payroll.setPayPeriodId(rs.getString("pay_period_id"));
                payroll.setEmployeeNo(rs.getInt("employee_no"));
                payroll.setEmployeeFullName(rs.getString("employee_full_name"));
                payroll.setPosition(rs.getString("position"));
                payroll.setDepartment(rs.getString("department"));
                payroll.setGrossIncome(rs.getDouble("gross_income"));
                payroll.setSocialSecurityNo(rs.getString("social_security_no"));
                payroll.setSocialSecurityContribution(rs.getDouble("social_security_contribution"));
                payroll.setPhilhealthNo(rs.getString("philhealth_no"));
                payroll.setPhilhealthContribution(rs.getDouble("philhealth_contribution"));
                payroll.setPagibigNo(rs.getString("pagibig_no"));
                payroll.setPagibigContribution(rs.getDouble("pagibig_contribution"));
                payroll.setTin(rs.getString("tin"));
                payroll.setWithholdingTax(rs.getDouble("withholding_tax"));
                payroll.setSummaryDeductions(rs.getDouble("summary_deductions"));
                payroll.setNetPay(rs.getDouble("net_pay"));
                payrollList.add(payroll);
            }
        }
        return payrollList;
    }
}
