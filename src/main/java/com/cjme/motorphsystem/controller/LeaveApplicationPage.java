/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.cjme.motorphsystem.controller;

import java.awt.Color;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * LeaveApplicationPage provides a user interface for employees to apply for
 * leave.
 *
 * @author Lance
 */
class LeaveApplicationPage extends javax.swing.JFrame implements EmployeeInformationManager {

    // Constants for button color changes
    private static final java.awt.Color LIGHT_BLUE = new java.awt.Color(203, 203, 239);
    private static final java.awt.Color WHITE = new java.awt.Color(255, 255, 255);
    private static final java.awt.Color RED = new java.awt.Color(191, 47, 47);

    /**
     * Constructs a LeaveApplicationPage for the given employee ID.
     *
     * @param employeeId The ID of the employee applying for leave.
     */
    public LeaveApplicationPage(int employeeId) {
        initComponents();
        txtEmployeeNumber.setText(String.valueOf(employeeId));
        setupDateChoosers();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblMotorPhHeader = new javax.swing.JLabel();
        lblLeaveApplicationHeader = new javax.swing.JLabel();
        lblEmployeeNumber = new javax.swing.JLabel();
        txtEmployeeNumber = new javax.swing.JTextField();
        lblStartDate = new javax.swing.JLabel();
        lblEndDate = new javax.swing.JLabel();
        btnApply = new javax.swing.JButton();
        lblBottomSeparator = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        lblLeaveType = new javax.swing.JLabel();
        cmbLeaveType = new javax.swing.JComboBox<>();
        lblReasonForLeave = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaReasonForLeave = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Leave Application");

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));

        lblMotorPhHeader.setBackground(new java.awt.Color(255, 255, 255));
        lblMotorPhHeader.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        lblMotorPhHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMotorPhHeader.setText("MotorPH Payroll System");
        lblMotorPhHeader.setOpaque(true);

        lblLeaveApplicationHeader.setBackground(new java.awt.Color(223, 54, 54));
        lblLeaveApplicationHeader.setFont(new java.awt.Font("Leelawadee", 1, 16)); // NOI18N
        lblLeaveApplicationHeader.setForeground(new java.awt.Color(255, 255, 255));
        lblLeaveApplicationHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLeaveApplicationHeader.setText("Leave Application");
        lblLeaveApplicationHeader.setOpaque(true);

        lblEmployeeNumber.setBackground(new java.awt.Color(255, 255, 255));
        lblEmployeeNumber.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        lblEmployeeNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmployeeNumber.setText("Employee #:");
        lblEmployeeNumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        lblEmployeeNumber.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblEmployeeNumber.setMaximumSize(new java.awt.Dimension(93, 25));
        lblEmployeeNumber.setMinimumSize(new java.awt.Dimension(93, 25));
        lblEmployeeNumber.setOpaque(true);

        txtEmployeeNumber.setEditable(false);
        txtEmployeeNumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEmployeeNumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        txtEmployeeNumber.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtEmployeeNumber.setFocusable(false);

        lblStartDate.setBackground(new java.awt.Color(255, 255, 255));
        lblStartDate.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        lblStartDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStartDate.setText("Start Date (MM/DD):");
        lblStartDate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        lblStartDate.setMaximumSize(new java.awt.Dimension(93, 25));
        lblStartDate.setMinimumSize(new java.awt.Dimension(93, 25));
        lblStartDate.setOpaque(true);

        lblEndDate.setBackground(new java.awt.Color(255, 255, 255));
        lblEndDate.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        lblEndDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEndDate.setText("End Date (MM/DD):");
        lblEndDate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        lblEndDate.setMaximumSize(new java.awt.Dimension(93, 25));
        lblEndDate.setMinimumSize(new java.awt.Dimension(93, 25));
        lblEndDate.setOpaque(true);

        btnApply.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        btnApply.setText("Apply");
        btnApply.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnApply.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnApply.setFocusable(false);
        btnApply.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnApplyMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnApplyMouseExited(evt);
            }
        });
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });

        lblBottomSeparator.setBackground(new java.awt.Color(51, 51, 51));
        lblBottomSeparator.setFont(new java.awt.Font("Leelawadee", 1, 16)); // NOI18N
        lblBottomSeparator.setForeground(new java.awt.Color(255, 255, 255));
        lblBottomSeparator.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBottomSeparator.setOpaque(true);

        btnBack.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        btnBack.setText("Back");
        btnBack.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.setFocusable(false);
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBackMouseExited(evt);
            }
        });
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblLeaveType.setBackground(new java.awt.Color(255, 255, 255));
        lblLeaveType.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        lblLeaveType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLeaveType.setText("Leave Type");
        lblLeaveType.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        lblLeaveType.setMaximumSize(new java.awt.Dimension(93, 25));
        lblLeaveType.setMinimumSize(new java.awt.Dimension(93, 25));
        lblLeaveType.setOpaque(true);

        cmbLeaveType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sick Leave", "Vacation Leave", "Emergency Leave" }));
        cmbLeaveType.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblReasonForLeave.setBackground(new java.awt.Color(255, 255, 255));
        lblReasonForLeave.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        lblReasonForLeave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReasonForLeave.setText("Reason for Leave");
        lblReasonForLeave.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        lblReasonForLeave.setMaximumSize(new java.awt.Dimension(93, 25));
        lblReasonForLeave.setMinimumSize(new java.awt.Dimension(93, 25));
        lblReasonForLeave.setOpaque(true);

        txaReasonForLeave.setColumns(20);
        txaReasonForLeave.setLineWrap(true);
        txaReasonForLeave.setRows(5);
        txaReasonForLeave.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        txaReasonForLeave.setMinimumSize(new java.awt.Dimension(105, 18));
        jScrollPane1.setViewportView(txaReasonForLeave);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMotorPhHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblLeaveApplicationHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblBottomSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblLeaveType, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbLeaveType, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnApply, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblEmployeeNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmployeeNumber))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblReasonForLeave, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblEndDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblStartDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMotorPhHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLeaveApplicationHeader)
                .addGap(15, 15, 15)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmployeeNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmployeeNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLeaveType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbLeaveType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblReasonForLeave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(btnApply, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lblBottomSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Handles the action event of the back button to close the current page.
     */
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // Close the current page
        dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * Handles mouse exit event on the back button by resetting its background
     * color.
     */
    private void btnBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseExited
        btnBack.setBackground(WHITE);
    }//GEN-LAST:event_btnBackMouseExited

    /**
     * Handles mouse hover event on the back button by changing its background
     * color.
     */
    private void btnBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseEntered
        btnBack.setBackground(LIGHT_BLUE);
    }//GEN-LAST:event_btnBackMouseEntered

    /**
     * Handles mouse hover event on the apply button by resetting its background
     * color.
     */
    private void btnApplyMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnApplyMouseExited
        btnApply.setBackground(WHITE);
    }//GEN-LAST:event_btnApplyMouseExited

    /**
     * Handles mouse hover event on the apply button by changing its background
     * color.
     */
    private void btnApplyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnApplyMouseEntered
        btnApply.setBackground(LIGHT_BLUE);
    }//GEN-LAST:event_btnApplyMouseEntered

    /**
     * Handles the action event of the apply button to apply for leave.
     */
    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
        try {
            applyLeave();

            LeaveTablePage leaveTablePage = new LeaveTablePage();
            leaveTablePage.refreshTable();  // Refresh data
        } catch (IOException ex) {
            Logger.getLogger(LeaveApplicationPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnApplyActionPerformed

    private void txtEmployeeNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmployeeNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmployeeNumberActionPerformed

    /**
     * Displays an error dialog with the provided error message.
     *
     * @param errorMessage The error message to display.
     */
    @Override
    public void showErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(pnlMain, "Error submitting leave application: " + errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Sets up date choosers to use MM/dd format and disables direct text
     * editing.
     */
    private void setupDateChoosers() {

    }

    /**
     * Configures a JDateChooser to be non-editable and have a white background.
     *
     * @param dateChooser The JDateChooser to be configured.
     */
    private void configureDateChooser( ) {
    }

    /**
     * Submits a leave application based on user inputs.
     */
    private void applyLeave() {
        try {
            int employeeNumber = Integer.parseInt(txtEmployeeNumber.getText());
            if (employeeNumber == 0 || employeeNumber > 34) {
                showErrorDialog("Employee is not found.");
                return;
            }

            String leaveType = (String) cmbLeaveType.getSelectedItem();
           

            String reason = txaReasonForLeave.getText().trim();
            if (reason.isEmpty()) {
                showErrorDialog("Please input a reason for leave.");
                return;
            }


            showInformationDialog();
        } catch (IllegalArgumentException e) {
            showErrorDialog(e.getMessage());
        }
    }

    /**
     * Formats a given date into MM/dd format.
     *
     * @param date The date to format.
     * @return The formatted date string.
     */
    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
        return sdf.format(date);
    }

    /**
     * Displays an information dialog to confirm successful leave application.
     */
    private void showInformationDialog() {
        JOptionPane.showMessageDialog(pnlMain, "Leave application submitted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnBack;
    private javax.swing.JComboBox<String> cmbLeaveType;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBottomSeparator;
    private javax.swing.JLabel lblEmployeeNumber;
    private javax.swing.JLabel lblEndDate;
    private javax.swing.JLabel lblLeaveApplicationHeader;
    private javax.swing.JLabel lblLeaveType;
    private javax.swing.JLabel lblMotorPhHeader;
    private javax.swing.JLabel lblReasonForLeave;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTextArea txaReasonForLeave;
    private javax.swing.JTextField txtEmployeeNumber;
    // End of variables declaration//GEN-END:variables
}
