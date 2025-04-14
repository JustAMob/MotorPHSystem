/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.model;

/**
 *
 * @author JustAMob
 */

public abstract class Deduction {
    protected double salary;

    public Deduction(double salary) {
        this.salary = salary;
    }

    public abstract double calculate(); // each subclass must implement this
}

