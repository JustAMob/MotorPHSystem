/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.util;

import com.cjme.motorphsystem.dao.implementations.EmployeeDAOImpl;
import com.cjme.motorphsystem.dao.implementations.SalaryDAOImpl;
import com.cjme.motorphsystem.model.Employee;
import com.cjme.motorphsystem.model.Payslip;
import com.cjme.motorphsystem.model.Salary;
import com.cjme.motorphsystem.service.PayslipService;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author JustAMob
 */
public class PayslipGenerator {
     public static void generatePayslipsForMonth(int month, int year) throws SQLException {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        PayslipService payslipService = new PayslipService();
        SalaryDAOImpl salaryDAO = new SalaryDAOImpl();
        
        List<Employee> employees = employeeDAO.getAllEmployees();

        LocalDate periodStart = LocalDate.of(year, month, 1);
        LocalDate periodEnd = periodStart.withDayOfMonth(periodStart.lengthOfMonth());

        for (Employee emp : employees) {
            Salary salary = salaryDAO.getSalaryById(emp.getSalaryId());
             if (salary == null) {
                System.out.println("No salary record found for employee ID: " + emp.getEmployeeId());
                continue; 
            }
            BigDecimal basicSalary = salary.getBasicSalary(); 

            Payslip payslip = new Payslip();
            payslip.setEmployeeId(emp.getEmployeeId());
            payslip.setPeriodStartDate(Date.valueOf(periodStart));
            payslip.setPeriodEndDate(Date.valueOf(periodEnd));
            payslip.setMonthlyRate(basicSalary);

            payslipService.savePayslip(payslip);
        }

        System.out.println("Payslips generated for " + month + "/" + year);
    }
}
