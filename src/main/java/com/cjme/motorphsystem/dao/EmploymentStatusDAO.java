/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.EmploymentStatus;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author JustAMob
 */
public interface EmploymentStatusDAO {
    int addStatus(EmploymentStatus status);
    EmploymentStatus getStatusById(int id);
    List<EmploymentStatus> getAllStatuses();
    void updateStatus(EmploymentStatus status);
    void deleteStatus(int id);

    public Map<String, Integer> getEmploymentStatusNameIdMap()throws SQLException;
}
