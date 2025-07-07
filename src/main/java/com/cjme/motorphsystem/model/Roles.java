/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.model;

/**
 *
 * @author JustAMob
 */
public class Roles {
    private int roleId;
    private String roleName;
    private String access;

    public Roles() {
    }

    public Roles(int roleId, String roleName, String access) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.access = access;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getAccess() {
        return access;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setAccess(String access) {
        this.access = access;
    }
    
    
    
}
