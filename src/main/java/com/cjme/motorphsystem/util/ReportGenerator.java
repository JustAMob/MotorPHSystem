package com.cjme.motorphsystem.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.sql.Connection;

public class ReportGenerator {

    public static void generatePayrollReport(Connection conn) {
        try {
            String reportPath = "src/main/resources/jrxml/payroll.jrxml";

            // Compile and fill the report
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

            // Show the report
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setVisible(true);

            // Prompt the user if they want to export to PDF
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Do you want to export this report to PDF?",
                    "Export to PDF",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                exportReportToPdf(jasperPrint);
            }

        } catch (JRException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error generating report: " + e.getMessage());
        }
    }

    private static void exportReportToPdf(JasperPrint jasperPrint) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Report as PDF");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            // Ensure .pdf extension
            String pdfPath = fileToSave.getAbsolutePath();
            if (!pdfPath.toLowerCase().endsWith(".pdf")) {
                pdfPath += ".pdf";
            }

            try {
                JasperExportManager.exportReportToPdfFile(jasperPrint, pdfPath);
                JOptionPane.showMessageDialog(null, "PDF exported to:\n" + pdfPath);
            } catch (JRException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to export PDF: " + e.getMessage());
            }
        }
    }
}
