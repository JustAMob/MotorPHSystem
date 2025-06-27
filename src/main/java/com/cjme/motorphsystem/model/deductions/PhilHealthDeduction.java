package com.cjme.motorphsystem.model.deductions;

import com.cjme.motorphsystem.dao.PhilHealthDAO;
import com.cjme.motorphsystem.model.Deduction;

/**
 *
 * @author JustAMob
 */

public class PhilHealthDeduction extends Deduction {
    private final PhilHealthDAO philhealth;

    public PhilHealthDeduction(double salary, PhilHealthDAO philhealth) {
        super(salary);
        this.philhealth = philhealth;
    }

    @Override
    public double calculate() {
        
        double premium = salary * philhealth.getPremiumRate(); // 3% total
        return premium / 2; // employee share
    }
}
