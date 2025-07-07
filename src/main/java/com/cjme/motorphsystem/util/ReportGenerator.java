package com.cjme.motorphsystem.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.sql.Connection;

/**
 * Utility class for generating and displaying JasperReports.
 */
public class ReportGenerator {

    private final Connection connection;

    public ReportGenerator(Connection connection) {
        this.connection = connection;
    }

    /**
     * Generates and displays the payroll report.
     */
    public void generatePayrollReport() {
        generateReport("src/main/resources/jrxml/payroll.jrxml", "Payroll Report");
    }

    /**
     * Generates and displays the payslip report.
     */
    public void generatePayslipReport() {
        generateReport("src/main/resources/jrxml/payslip.jrxml", "Employee Payslip");
    }

    /**
     * Compiles, fills, and displays a JasperReport with no parameters.
     *
     * @param reportPath Path to the JRXML file
     * @param title      Title for the report viewer window
     */
    private void generateReport(String reportPath, String title) {
        try {
            // Compile the JRXML file to a JasperReport
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);

            // Fill the report with the database connection (no parameters)
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new java.util.HashMap<>(), connection);

            // Display the report
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle(title);
            viewer.setVisible(true);

        } catch (JRException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error generating report:\n" + e.getMessage(),
                "Report Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
