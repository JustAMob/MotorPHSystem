package com.cjme.motorphsystem.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ReportGenerator {

    private final Connection connection;

    public ReportGenerator(Connection connection) {
        this.connection = connection;
    }

    /**
     * Generates and displays the payroll report.
     * @param payrollPeriod The payroll period to filter by (can be null)
     * @param department The department to filter by (can be null)
     */
    public void generatePayrollReport(String payrollPeriod, String department) {
        Map<String, Object> parameters = new HashMap<>();

        try {
            if (payrollPeriod != null && !payrollPeriod.isEmpty()) {
                // Convert "yyyy-MM" to a full date like "yyyy-MM-01"
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate = sdf.parse(payrollPeriod + "-01");
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                parameters.put("PayrollPeriod", sqlDate); 
            } else {
                parameters.put("PayrollPeriod", null);
            }

            parameters.put("Department", department);
            generateReport("src/main/resources/jrxml/payroll.jrxml", "Payroll Report", parameters);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                null,
                "Error generating PDF report: " + e.getMessage(),
                "Export Error",
                JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    /**
     * Generates and displays the payslip report.
     * @param employeeId The employee ID to generate payslip for
     */
    public void generatePayslipReport(String employeeId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("EmployeeId", employeeId);
        generateReport("src/main/resources/jrxml/payslip.jrxml", "Employee Payslip", parameters);
    }

    /**
     * Compiles, fills, and displays a JasperReport with parameters.
     *
     * @param reportPath Path to the JRXML file
     * @param title      Title for the report viewer window
     * @param parameters Map of parameters to pass to the report
     */
    private void generateReport(String reportPath, String title, Map<String, Object> parameters) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters != null ? parameters : new HashMap<>(),
                    connection
            );

            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle(title);
            viewer.setVisible(true);

        } catch (JRException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error generating report:\n" + e.getMessage(),
                "Jasper Report Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
