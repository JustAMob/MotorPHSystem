/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.Payslip;

/**
 *
 * @author JustAMob
 */
public interface PayslipDAO {
    Payslip getPayslipByEmployeeIdAndMonth(int employeeId, int month, int year);
    void addPayslip(Payslip payslip);
}
