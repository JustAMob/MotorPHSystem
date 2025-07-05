/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.Authentication;
import java.sql.SQLException;

/**
 *
 * @author JustAMob
 */
public interface AuthenticationDAO {
   Authentication getAuthenticationByEmployeeId(int employeeId) throws SQLException; 
}
