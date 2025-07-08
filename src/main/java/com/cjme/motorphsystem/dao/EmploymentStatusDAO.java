/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.EmploymentStatus;
import java.util.List;

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
}
