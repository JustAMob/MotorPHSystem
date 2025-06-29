/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.cjme.motorphsystem.controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * PayrollComputationFrame provides a GUI for computing payroll.
 *
 * @author Lance
 */
class PayrollComputationFrame extends javax.swing.JFrame {

    // Constants for button coloring changes
    private static final java.awt.Color LIGHT_BLUE = new java.awt.Color(203, 203, 239);
    private static final java.awt.Color WHITE = new java.awt.Color(255, 255, 255);

    private int employeeNumber;

    /**
     * Creates a PayrollComputationFrame for a given employee.
     *
     * @param employeeNumber The ID of the employee for whom payroll is being
     * computed.
     */
    public PayrollComputationFrame(int employeeNumber) {
        initComponents();
        this.employeeNumber = employeeNumber;
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
        lblPayrollComputationHeader = new javax.swing.JLabel();
        lblMonth = new javax.swing.JLabel();
        txtDaysWorked = new javax.swing.JTextField();
        lblDaysWorked = new javax.swing.JLabel();
        lblGrossWage = new javax.swing.JLabel();
        txtGrossWage = new javax.swing.JTextField();
        cmbMonth = new javax.swing.JComboBox<>();
        txtSssDeduction = new javax.swing.JTextField();
        lblSssDeduction = new javax.swing.JLabel();
        txtPhilHealthDeduction = new javax.swing.JTextField();
        lblPhilHealthDeduction = new javax.swing.JLabel();
        txtPagIbigDeduction = new javax.swing.JTextField();
        lblPagIbigDeduction = new javax.swing.JLabel();
        txtWithholdingTax = new javax.swing.JTextField();
        lblWithholdingTax = new javax.swing.JLabel();
        txtLateArrivalDeduction = new javax.swing.JTextField();
        lblLateArrivalDeduction = new javax.swing.JLabel();
        txtTotalDeductions = new javax.swing.JTextField();
        lblTotalDeductions = new javax.swing.JLabel();
        txtNetWage = new javax.swing.JTextField();
        lblNetWage = new javax.swing.JLabel();
        btnCompute = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblBottomSeparator = new javax.swing.JLabel();
        txtHourlyRate = new javax.swing.JTextField();
        lblHourlyRate = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Payroll System Main Menu");
        setResizable(false);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));

        lblMotorPhHeader.setBackground(new java.awt.Color(255, 255, 255));
        lblMotorPhHeader.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        lblMotorPhHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMotorPhHeader.setText("MotorPH Payroll System");
        lblMotorPhHeader.setOpaque(true);

        lblPayrollComputationHeader.setBackground(new java.awt.Color(223, 54, 54));
        lblPayrollComputationHeader.setFont(new java.awt.Font("Leelawadee", 1, 16)); // NOI18N
        lblPayrollComputationHeader.setForeground(new java.awt.Color(255, 255, 255));
        lblPayrollComputationHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPayrollComputationHeader.setText("Payroll Computation");
        lblPayrollComputationHeader.setOpaque(true);

        lblMonth.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblMonth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMonth.setText("Month:");
        lblMonth.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblMonth.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblMonth.setMaximumSize(new java.awt.Dimension(93, 25));
        lblMonth.setMinimumSize(new java.awt.Dimension(93, 25));
        lblMonth.setOpaque(true);

        txtDaysWorked.setEditable(false);
        txtDaysWorked.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDaysWorked.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtDaysWorked.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtDaysWorked.setFocusable(false);

        lblDaysWorked.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblDaysWorked.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDaysWorked.setText("Days Worked:");
        lblDaysWorked.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblDaysWorked.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblDaysWorked.setMaximumSize(new java.awt.Dimension(93, 25));
        lblDaysWorked.setMinimumSize(new java.awt.Dimension(93, 25));
        lblDaysWorked.setOpaque(true);

        lblGrossWage.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblGrossWage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGrossWage.setText("Gross Wage:");
        lblGrossWage.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblGrossWage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblGrossWage.setMaximumSize(new java.awt.Dimension(93, 25));
        lblGrossWage.setMinimumSize(new java.awt.Dimension(93, 25));
        lblGrossWage.setOpaque(true);

        txtGrossWage.setEditable(false);
        txtGrossWage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGrossWage.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtGrossWage.setFocusable(false);

        cmbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        cmbMonth.setFocusable(false);

        txtSssDeduction.setEditable(false);
        txtSssDeduction.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSssDeduction.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtSssDeduction.setFocusable(false);

        lblSssDeduction.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblSssDeduction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSssDeduction.setText("SSS Deduction:");
        lblSssDeduction.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblSssDeduction.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblSssDeduction.setMaximumSize(new java.awt.Dimension(93, 25));
        lblSssDeduction.setMinimumSize(new java.awt.Dimension(93, 25));
        lblSssDeduction.setOpaque(true);

        txtPhilHealthDeduction.setEditable(false);
        txtPhilHealthDeduction.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPhilHealthDeduction.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtPhilHealthDeduction.setFocusable(false);

        lblPhilHealthDeduction.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblPhilHealthDeduction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhilHealthDeduction.setText("PhilHealth Deduction:");
        lblPhilHealthDeduction.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblPhilHealthDeduction.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblPhilHealthDeduction.setMaximumSize(new java.awt.Dimension(93, 25));
        lblPhilHealthDeduction.setMinimumSize(new java.awt.Dimension(93, 25));
        lblPhilHealthDeduction.setOpaque(true);

        txtPagIbigDeduction.setEditable(false);
        txtPagIbigDeduction.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPagIbigDeduction.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtPagIbigDeduction.setFocusable(false);

        lblPagIbigDeduction.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblPagIbigDeduction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPagIbigDeduction.setText("Pag-IBIG Deduction:");
        lblPagIbigDeduction.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblPagIbigDeduction.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblPagIbigDeduction.setMaximumSize(new java.awt.Dimension(93, 25));
        lblPagIbigDeduction.setMinimumSize(new java.awt.Dimension(93, 25));
        lblPagIbigDeduction.setOpaque(true);

        txtWithholdingTax.setEditable(false);
        txtWithholdingTax.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtWithholdingTax.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtWithholdingTax.setFocusable(false);

        lblWithholdingTax.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblWithholdingTax.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWithholdingTax.setText("Withholding Tax:");
        lblWithholdingTax.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblWithholdingTax.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblWithholdingTax.setMaximumSize(new java.awt.Dimension(93, 25));
        lblWithholdingTax.setMinimumSize(new java.awt.Dimension(93, 25));
        lblWithholdingTax.setOpaque(true);

        txtLateArrivalDeduction.setEditable(false);
        txtLateArrivalDeduction.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLateArrivalDeduction.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtLateArrivalDeduction.setFocusable(false);

        lblLateArrivalDeduction.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblLateArrivalDeduction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLateArrivalDeduction.setText("Late Arrival Deduction:");
        lblLateArrivalDeduction.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblLateArrivalDeduction.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblLateArrivalDeduction.setMaximumSize(new java.awt.Dimension(93, 25));
        lblLateArrivalDeduction.setMinimumSize(new java.awt.Dimension(93, 25));
        lblLateArrivalDeduction.setOpaque(true);

        txtTotalDeductions.setEditable(false);
        txtTotalDeductions.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalDeductions.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtTotalDeductions.setFocusable(false);

        lblTotalDeductions.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblTotalDeductions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalDeductions.setText("Total Deductions:");
        lblTotalDeductions.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblTotalDeductions.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTotalDeductions.setMaximumSize(new java.awt.Dimension(93, 25));
        lblTotalDeductions.setMinimumSize(new java.awt.Dimension(93, 25));
        lblTotalDeductions.setOpaque(true);

        txtNetWage.setEditable(false);
        txtNetWage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNetWage.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtNetWage.setFocusable(false);

        lblNetWage.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblNetWage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNetWage.setText("Net Wage:");
        lblNetWage.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblNetWage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblNetWage.setMaximumSize(new java.awt.Dimension(93, 25));
        lblNetWage.setMinimumSize(new java.awt.Dimension(93, 25));
        lblNetWage.setOpaque(true);

        btnCompute.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        btnCompute.setText("Compute");
        btnCompute.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnCompute.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCompute.setFocusable(false);
        btnCompute.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnComputeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnComputeMouseExited(evt);
            }
        });
        btnCompute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComputeActionPerformed(evt);
            }
        });

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

        lblBottomSeparator.setBackground(new java.awt.Color(51, 51, 51));
        lblBottomSeparator.setFont(new java.awt.Font("Leelawadee", 1, 16)); // NOI18N
        lblBottomSeparator.setForeground(new java.awt.Color(255, 255, 255));
        lblBottomSeparator.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBottomSeparator.setOpaque(true);

        txtHourlyRate.setEditable(false);
        txtHourlyRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHourlyRate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtHourlyRate.setFocusable(false);

        lblHourlyRate.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblHourlyRate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHourlyRate.setText("Hourly Rate:");
        lblHourlyRate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        lblHourlyRate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblHourlyRate.setMaximumSize(new java.awt.Dimension(93, 25));
        lblHourlyRate.setMinimumSize(new java.awt.Dimension(93, 25));
        lblHourlyRate.setOpaque(true);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPayrollComputationHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblMotorPhHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(btnCompute, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblBottomSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbMonth, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblGrossWage, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGrossWage))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblDaysWorked, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDaysWorked))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblSssDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSssDeduction))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblPhilHealthDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhilHealthDeduction))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblPagIbigDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPagIbigDeduction))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblWithholdingTax, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtWithholdingTax))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addComponent(lblLateArrivalDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLateArrivalDeduction))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblTotalDeductions, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalDeductions))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addComponent(lblNetWage, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNetWage))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(162, 162, 162))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblHourlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHourlyRate)))
                .addContainerGap())
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMotorPhHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPayrollComputationHeader)
                .addGap(15, 15, 15)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHourlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHourlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDaysWorked, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDaysWorked, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGrossWage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGrossWage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSssDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSssDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhilHealthDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhilHealthDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPagIbigDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPagIbigDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWithholdingTax, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtWithholdingTax, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLateArrivalDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLateArrivalDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalDeductions, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalDeductions, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNetWage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNetWage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(btnCompute, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lblBottomSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnComputeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComputeActionPerformed
        computeSalary();
    }//GEN-LAST:event_btnComputeActionPerformed

    /**
     * Handles mouse hover event on the compute button by changing its
     * background color.
     */
    private void btnComputeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComputeMouseEntered
        btnCompute.setBackground(LIGHT_BLUE);
    }//GEN-LAST:event_btnComputeMouseEntered

    /**
     * Handles mouse exit event on the compute button by resetting its
     * background color.
     */
    private void btnComputeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComputeMouseExited
        btnCompute.setBackground(WHITE);
    }//GEN-LAST:event_btnComputeMouseExited

    /**
     * Handles the action event of the back button to close the current page.
     */
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // Close the current page
        dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * Handles mouse hover event on the back button by changing its background
     * color.
     */
    private void btnBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseEntered
        // TODO add your handling code here:
        btnBack.setBackground(LIGHT_BLUE);
    }//GEN-LAST:event_btnBackMouseEntered

    /**
     * Handles mouse exit event on the back button by resetting its background
     * color.
     */
    private void btnBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseExited
        btnBack.setBackground(WHITE);
    }//GEN-LAST:event_btnBackMouseExited

    /**
     * Computes the salary for the selected month and updates the displayed
     * values.
     */
    private void computeSalary() {
        String selectedMonth = String.format("%02d", cmbMonth.getSelectedIndex() + 1); // Convert month index to two-digit format

            // Create a SalaryComputationManager instance for processing salary details

            JTextField[] textFields = {txtGrossWage, txtSssDeduction, txtPhilHealthDeduction, txtPagIbigDeduction, txtWithholdingTax, txtLateArrivalDeduction,
                txtTotalDeductions, txtNetWage};

            for (int i = 0; i < textFields.length; i++) {
            }

            // Refresh UI to reflect the updated values
            pnlMain.revalidate();
            pnlMain.repaint();

            // Show error message if computation fails
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCompute;
    private javax.swing.JComboBox<String> cmbMonth;
    private javax.swing.JLabel lblBottomSeparator;
    private javax.swing.JLabel lblDaysWorked;
    private javax.swing.JLabel lblGrossWage;
    private javax.swing.JLabel lblHourlyRate;
    private javax.swing.JLabel lblLateArrivalDeduction;
    private javax.swing.JLabel lblMonth;
    private javax.swing.JLabel lblMotorPhHeader;
    private javax.swing.JLabel lblNetWage;
    private javax.swing.JLabel lblPagIbigDeduction;
    private javax.swing.JLabel lblPayrollComputationHeader;
    private javax.swing.JLabel lblPhilHealthDeduction;
    private javax.swing.JLabel lblSssDeduction;
    private javax.swing.JLabel lblTotalDeductions;
    private javax.swing.JLabel lblWithholdingTax;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTextField txtDaysWorked;
    private javax.swing.JTextField txtGrossWage;
    private javax.swing.JTextField txtHourlyRate;
    private javax.swing.JTextField txtLateArrivalDeduction;
    private javax.swing.JTextField txtNetWage;
    private javax.swing.JTextField txtPagIbigDeduction;
    private javax.swing.JTextField txtPhilHealthDeduction;
    private javax.swing.JTextField txtSssDeduction;
    private javax.swing.JTextField txtTotalDeductions;
    private javax.swing.JTextField txtWithholdingTax;
    // End of variables declaration//GEN-END:variables
}
