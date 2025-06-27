package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.AllowanceType;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author JustAMob
 */
public interface AllowanceTypeDAO {
    void addAllowanceType(AllowanceType allowance) throws SQLException;
    List<AllowanceType> getAllAllowanceTypes() throws SQLException;
    AllowanceType getAllowanceTypeById(int id) throws SQLException;
    void updateAllowanceType(AllowanceType allowance) throws SQLException;
}
