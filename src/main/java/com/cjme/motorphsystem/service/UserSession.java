package com.cjme.motorphsystem.service;



import java.sql.Connection;
import org.json.JSONObject;
import org.json.JSONException;
/**
 *
 * @author JustAMob
 */
public class UserSession {
  private final JSONObject accessPermissions;
  
  
    public UserSession(JSONObject accessPermissions) {
        this.accessPermissions = accessPermissions;
        
    }
   
    public boolean hasAccess(String module, String action) {
        try {
            return accessPermissions.getJSONObject(module).getBoolean(action);
        } catch (JSONException e) {
            return false;
        }
    }
   
}

