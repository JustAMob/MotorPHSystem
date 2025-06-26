/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.controller;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Lance
 */
class AdminViewProfileFrame extends ViewProfileFrame {

    // Constants for button coloring changes
    private static final java.awt.Color WHITE = new java.awt.Color(255, 255, 255);
    private static final java.awt.Color GRAY = new java.awt.Color(242, 242, 242);

    private JButton btnDeleteInfo, btnUpdateInfo;
    private boolean deleteButtonClicked = false;
    private EmployeeSearchPage employeeSearchPage;
    private String role;

    public AdminViewProfileFrame( String role, EmployeeSearchPage employeeSearchPage) {
        this.role = role;
        this.employeeSearchPage = employeeSearchPage;
        addAdminControls();
    }

    private void addAdminControls()  {
        btnDeleteInfo = new JButton("Delete Information");
        btnUpdateInfo = new JButton("Update Information");
        btnDeleteInfo.setBackground(new Color(199, 73, 73));
        btnUpdateInfo.setBackground(new Color(73, 199, 73));
        btnDeleteInfo.setFont(new Font("Leelawadee UI", Font.PLAIN, 12));
        btnUpdateInfo.setFont(new Font("Leelawadee UI", Font.PLAIN, 12));
        btnDeleteInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnUpdateInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDeleteInfo.addActionListener(evt -> {
            
        });
        btnUpdateInfo.addActionListener(this::btnUpdateInfoActionPerformed);
        JPanel panel = new JPanel();
        panel.add(btnDeleteInfo);
        panel.add(btnUpdateInfo);
        add(panel, BorderLayout.SOUTH);
    }

    private void btnUpdateInfoActionPerformed(ActionEvent evt) {
        deleteButtonClicked = false;
        btnUpdateInfo.setText("Save");
        btnUpdateInfo.setBackground(Color.WHITE);
        btnUpdateInfo.removeActionListener(btnUpdateInfo.getActionListeners()[0]);
        btnUpdateInfo.addActionListener(e -> {
            
        });
        setFieldsEditable(true);
    }

    private void btnDeleteInfoActionPerformed(ActionEvent evt) {
        int confirmation = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this employee's record?", "Confirm Deletion",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                int employeeNumber = Integer.parseInt(txtEmployeeNumber.getText());
                deleteEmployeeRecord(employeeNumber);
                JOptionPane.showMessageDialog(this, "Employee record deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                if (employeeSearchPage != null) {
                    employeeSearchPage.refreshEmployeeTable();
                }
                dispose();
            } catch (IOException | NumberFormatException e) {
                showErrorDialog("Error deleting employee record: " + e.getMessage());
            }
        }
    }

    private void deleteEmployeeRecord(int employeeNumber) throws IOException {
        File inputFile = new File("src/main/resources/data/employee_information.csv");
        File tempFile = new File("src/main/resources/data/temp_employee_information.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String header = reader.readLine();
            writer.write(header + "\n");

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(",");
                String employeeNumString = data[0].replaceAll("\"", "").trim();
                if (!employeeNumString.equals(String.valueOf(employeeNumber))) {
                    writer.write(currentLine + "\n");
                }
            }
        }

        if (!inputFile.delete()) {
            throw new IOException("Could not delete the original file.");
        }
        if (!tempFile.renameTo(inputFile)) {
            throw new IOException("Could not rename the temporary file.");
        }
    }

    private void updateEmployeeInformation()  {
        try {
            int employeeNumber = Integer.parseInt(txtEmployeeNumber.getText());
            List<String> updatedEmployeeInfo = gatherEmployeeInformationFromFields();

            setFieldsEditable(false);
            btnUpdateInfo.setText("Update Info");
            btnUpdateInfo.setBackground(new Color(34, 177, 76));

            btnUpdateInfo.removeActionListener(btnUpdateInfo.getActionListeners()[0]);
            btnUpdateInfo.addActionListener(this::btnUpdateInfoActionPerformed);

            if (employeeSearchPage != null) {
                employeeSearchPage.refreshEmployeeTable();
            }

            deleteButtonClicked = false;
        } catch (IllegalArgumentException | ParseException e) {
            showErrorDialog(e.getMessage());
        }
    }

    /**
     * Sets the text fields editable or non-editable.
     *
     * @param allowed If true, fields become editable; otherwise, they are
     * disabled.
     */
    private void setFieldsEditable(boolean allowed) {
        // Define text fields that should be editable
        JTextField[] editableFields = {
            txtLastName, txtFirstName, txtBirthdate, txtAddress, txtPhoneNumber,
            txtStatus, txtPosition, txtImmediateSupervisor, txtBasicSalary,
            txtRiceSubsidy, txtPhoneAllowance, txtClothingAllowance,
            txtGrossSemimonthlyRate, txtHourlyRate
        };

        // Define text fields that should always remain non-editable
        JTextField[] nonEditableFields = {
            txtSssNumber, txtPhilHealthNumber, txtTinNumber, txtPagIbigNumber
        };

        // Set editable fields to the given allowed value
        for (JTextField field : editableFields) {
            field.setBackground(allowed ? WHITE : GRAY);
            field.setCursor(allowed ? new Cursor(Cursor.TEXT_CURSOR) : new Cursor(Cursor.DEFAULT_CURSOR));
            field.setEditable(allowed);
            field.setFocusable(allowed);
        }

        // Set non-editable fields explicitly
        for (JTextField field : nonEditableFields) {
            field.setBackground(GRAY);
            field.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            field.setEditable(false);
            field.setFocusable(false);
        }
    }

    /**
     * Gathers updated employee information from UI fields.
     *
     * @return A list of strings representing the employee's updated details.
     */
    private List<String> gatherEmployeeInformationFromFields() throws ParseException {
        List<String> employeeInfo = new ArrayList<>();

        // Add the text of each field to the list
        employeeInfo.add(txtLastName.getText());
        employeeInfo.add(txtFirstName.getText());
        employeeInfo.add(formatDateForCsv(txtBirthdate.getText()));
        employeeInfo.add(txtAddress.getText());
        employeeInfo.add(txtPhoneNumber.getText());
        employeeInfo.add(txtSssNumber.getText());
        employeeInfo.add(txtPhilHealthNumber.getText());
        employeeInfo.add(txtTinNumber.getText());
        employeeInfo.add(txtPagIbigNumber.getText());
        employeeInfo.add(txtStatus.getText());
        employeeInfo.add(txtPosition.getText());
        employeeInfo.add(txtImmediateSupervisor.getText());
        employeeInfo.add(txtBasicSalary.getText());
        employeeInfo.add(txtRiceSubsidy.getText());
        employeeInfo.add(txtPhoneAllowance.getText());
        employeeInfo.add(txtClothingAllowance.getText());
        employeeInfo.add(txtGrossSemimonthlyRate.getText());
        employeeInfo.add(txtHourlyRate.getText());

        return employeeInfo;
    }

    private String formatDateForCsv(String rawDate) throws ParseException {
        // Match Java's default Date.toString() format
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

        // Convert to the format expected in EmployeeDataReader ("MM/dd/yyyy")
        SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");

        // Parse and reformat the date
        Date date = inputFormat.parse(rawDate);
        return outputFormat.format(date);
    }

}
