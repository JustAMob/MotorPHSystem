/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.GovernmentID;

/**
 *
 * @author JustAMob
 */
public interface GovernmentIdDAO {
    int addGovernmentId(GovernmentID govId);
    GovernmentID getGovernmentIdByEmployeeId(int employeeId);
    void updateGovernmentId(GovernmentID govId);
    void deleteGovernmentId(int governmentId);
}
