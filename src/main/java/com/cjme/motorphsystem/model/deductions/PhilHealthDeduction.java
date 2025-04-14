/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.model.deductions;

import com.cjme.motorphsystem.model.Deduction;

/**
 *
 * @author JustAMob
 */

public class PhilHealthDeduction extends Deduction {

    public PhilHealthDeduction(double salary) {
        super(salary);
    }

    @Override
    public double calculate() {
        double premium = salary * 0.03; // 3% total
        return premium / 2; // employee share
    }
}
