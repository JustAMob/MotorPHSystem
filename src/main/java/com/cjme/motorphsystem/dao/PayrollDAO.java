package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.Payroll;
import java.sql.SQLException;
import java.util.List;

public interface PayrollDAO {
    /**
     * Retrieves payroll summaries from month_summary_view, optionally filtered by pay period and department.
     * @param payPeriod
     * @param department
     * @return 
     * @throws java.sql.SQLException
     */
    List<Payroll> getMonthSummary(String payPeriod, String department) throws SQLException;
}
