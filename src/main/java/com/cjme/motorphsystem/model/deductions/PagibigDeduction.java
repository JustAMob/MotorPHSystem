package com.cjme.motorphsystem.model.deductions;

import com.cjme.motorphsystem.dao.deductions.PagibigDAO;
import com.cjme.motorphsystem.model.Deduction;

/**
 *
 * @author JustAMob
 */
public class PagibigDeduction extends Deduction {

    private PagibigDAO pagibigDAO;

    public PagibigDeduction(double salary, PagibigDAO pagibigDAO) {
        super(salary);
        this.pagibigDAO = pagibigDAO;
    }

    @Override
    public double calculate() {
        PagibigContribution contribution = pagibigDAO.getRatesForSalary(salary);
        
        // Calculate the contributions for employee and employer
        double employeeContribution = salary * contribution.getEmployeeRate();
        double employerContribution = salary * contribution.getEmployerRate();

        return Math.min(employeeContribution, 100);
    }
}
