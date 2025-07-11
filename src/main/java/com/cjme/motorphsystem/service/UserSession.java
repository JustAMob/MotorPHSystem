package com.cjme.motorphsystem.service;



import org.json.JSONObject;
import org.json.JSONException;
/**
 *
 * @author JustAMob
 */
public class UserSession {
    private static UserSession currentSession; 
    private final JSONObject accessPermissions;
    private final int employeeId;

    public UserSession(int employeeId, JSONObject accessPermissions) {
        this.employeeId = employeeId;
        this.accessPermissions = accessPermissions;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public boolean hasAccess(String module, String action) {
        try {
            return accessPermissions.getJSONObject(module).getBoolean(action);
        } catch (JSONException e) {
            return false;
        }
    }

    // ──────────────── Static management ────────────────
    public static void setCurrentSession(UserSession session) {
        currentSession = session;
    }

    public static UserSession getCurrentSession() {
        return currentSession;
    }

    public static void clearSession() {
        currentSession = null;
    }
    
    @Override
    public String toString() {
        return "UserSession{employeeId=" + employeeId + ", roles=" + accessPermissions + "}";
    }

}

