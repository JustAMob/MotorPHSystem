/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.model;

/**
 *
 * @author JustAMob
 */
public class EmploymentStatus {
    private int statusId;
    private String statusType;
    private String statusDescription;

    public EmploymentStatus() {
    }

    public EmploymentStatus(int statusId, String statusType, String statusDescription) {
        this.statusId = statusId;
        this.statusType = statusType;
        this.statusDescription = statusDescription;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
    
    
}
