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

