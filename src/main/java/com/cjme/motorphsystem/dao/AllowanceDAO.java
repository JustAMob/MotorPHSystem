package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.Allowance;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author JustAMob
 */
public interface AllowanceDAO {
    boolean addAllowance(Allowance allowance, int employeeId) throws SQLException;
    List<Allowance> getAllAllowances() throws SQLException;
    List<Allowance> getAllowancesByEmployeeId(int employeeId) throws SQLException;
    double getTotalAllowanceByEmployeeID(int employeeId) throws SQLException;
}
