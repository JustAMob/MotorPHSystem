/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.Supervisor;
import java.util.List;

/**
 *
 * @author JustAMob
 */
public interface SupervisorDAO {
    int addSupervisor(Supervisor supervisor);
    Supervisor getSupervisorById(int id);
    List<Supervisor> getAllSupervisors();
    void updateSupervisor(Supervisor supervisor);
    void deleteSupervisor(int id);
}
