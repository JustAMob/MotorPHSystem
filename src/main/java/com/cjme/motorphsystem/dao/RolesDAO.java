/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.Roles;
import java.util.List;

/**
 *
 * @author JustAMob
 */
public interface RolesDAO {
    int addRole(Roles role);
    Roles getRoleById(int id);
    List<Roles> getAllRoles();
    void updateEmployee(Roles role);
    void deleteRole(int id);
    
}
