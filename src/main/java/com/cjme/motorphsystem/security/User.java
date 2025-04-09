package com.cjme.motorphsystem.security;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author JustAMob
 */
public class User {
    private String username;
    private Set<Role> roles;
    
    public User(String username){
        this.username = username;
        this.roles = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public Set<Role> getRoles() {
        return roles;
    }
    
    public void addRole(Role role){
        this.roles.add(role);
    }
    
    public boolean hasPermission(Permission permission) {
        for (Role role : roles) {
            if (role.getPermissions().contains(permission)) {
                return true;
            }
        }
        return false;
    }

}
