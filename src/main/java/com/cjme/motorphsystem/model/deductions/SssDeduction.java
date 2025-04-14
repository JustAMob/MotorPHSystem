package com.cjme.motorphsystem.model.deductions;

import com.cjme.motorphsystem.dao.deductions.SssDAO;
import com.cjme.motorphsystem.model.Deduction;

public class SssDeduction extends Deduction {
    private SssDAO sssDAO;

    public SssDeduction(double salary, SssDAO sssDAO) {
        super(salary);
        this.sssDAO = sssDAO;
    }

    @Override
    public double calculate() {
        return sssDAO.getContributionForSalary(salary);
    }
}
