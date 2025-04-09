package com.cjme.motorphsystem.security;

import java.security.Permission;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author JustAMob
 */
public class Role {
    private String roleName;
    private Set<Permission> permissions;

    public Role() {
        this.permissions = new HashSet<>();
    }

    // Constructor with role name
    public Role(String roleName) {
        this.roleName = roleName;
        this.permissions = new HashSet<>();
    }

    public String getRoleName() {
        return roleName;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
 
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public void addPermission(Permission permission) {
        this.permissions.add(permission);
    }

    public void removePermission(Permission permission) {
        this.permissions.remove(permission);
    }
}
