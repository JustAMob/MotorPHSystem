/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.model;

import com.cjme.motorphsystem.security.Role;
import java.time.LocalDateTime;

/**
 *
 * @author JustAMob
 */
public class Authentication {
    private int userId;
    private String username;
    private String passwordHash;
    private Role role;
    private LocalDateTime lastLogin;
    private int failedAttempts;
    private int accountStatusId;

    public Authentication() {
    }

    public Authentication(int userId, String passwordHash, Role role, LocalDateTime lastLogin, int failedAttempts, int accountStatusId) {
        this.userId = userId;
        this.passwordHash = passwordHash;
        this.role = role;
        this.lastLogin = lastLogin;
        this.failedAttempts = failedAttempts;
        this.accountStatusId = accountStatusId;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public void setAccountStatusId(int accountStatusId) {
        this.accountStatusId = accountStatusId;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public int getAccountStatusId() {
        return accountStatusId;
    }

  
    public void setUserId(int userId) {
        this.userId = userId;
    }


    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
  
    
    public int getUserId() {
        return userId;
    }


    public String getPasswordHash() {
        return passwordHash;
    }

    public Role getRole() {
        return role;
    }
    
    
    
    
    
    
    
}
