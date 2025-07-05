package com.cjme.motorphsystem.security;

import org.json.JSONObject;

/**
 *
 * @author JustAMob
 */
public class Role {
    private int roleId;
    private String roleName;
    private JSONObject access;

    public Role() {}

    public Role(int roleId, String roleName, JSONObject access) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.access = access;
    }

    // Getters and setters
    public int getRoleId() { return roleId; }
    public void setRoleId(int roleId) { this.roleId = roleId; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    public JSONObject getAccess() { return access; }
    public void setAccess(JSONObject access) { this.access = access; }
}
