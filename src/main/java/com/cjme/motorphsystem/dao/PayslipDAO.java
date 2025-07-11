package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.Payslip;
import java.sql.Date;
import java.sql.SQLException;

public interface PayslipDAO {
    /**
     * Fetches a payslip for the report by employee search (name or ID) and period.
     * @param searchEmployee Employee name or ID as String
     * @param periodStartDate Start date of the pay period
     * @param periodEndDate End date of the pay period
     * @return Payslip object or null if not found
     * @throws SQLException if a database access error occurs
     */
    Payslip getPayslipForReport(String searchEmployee, Date periodStartDate, Date periodEndDate) throws SQLException;
}
