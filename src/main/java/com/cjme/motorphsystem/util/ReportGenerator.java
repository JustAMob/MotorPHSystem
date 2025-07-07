package com.cjme.motorphsystem.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.sql.Connection;

public class ReportGenerator {

    private final Connection connection;

    public ReportGenerator(Connection connection) {
        this.connection = connection;
    }

    /**
     * Generates and displays the payroll report.
     */
    public void generatePayrollReport() {
        try {
            String reportPath = "src/main/resources/jrxml/payroll.jrxml";

            // Compile the JRXML file
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);

            // Fill the report with no parameters
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);

            // Display the report
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle("Payroll Report");
            viewer.setVisible(true);

        } catch (JRException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Error generating payroll report:\n" + e.getMessage(),
                "Report Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
