/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.model;

/**
 *
 * @author JustAMob
 */
public class Supervisor {
    private int supervisorId;
    private String name;

    public Supervisor() {
    }

    public Supervisor(int supervisorId, String name) {
        this.supervisorId = supervisorId;
        this.name = name;
    }

    public int getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
