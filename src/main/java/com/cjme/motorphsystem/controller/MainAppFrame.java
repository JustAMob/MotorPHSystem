/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.cjme.motorphsystem.controller;

import com.cjme.motorphsystem.dao.implementations.EmployeeEntityDAOImpl;
import com.cjme.motorphsystem.dao.implementations.EmployeeProfileDAOImpl;
import com.cjme.motorphsystem.dao.implementations.GovernmentIdDAOImpl;
import com.cjme.motorphsystem.dao.implementations.SalaryDAOImpl;
import com.cjme.motorphsystem.dao.implementations.LeaveRequestDAOImpl;

import com.cjme.motorphsystem.model.EmployeeEntity;
import com.cjme.motorphsystem.model.EmployeeProfile;
import com.cjme.motorphsystem.model.GovernmentID;
import com.cjme.motorphsystem.model.Salary;
import com.cjme.motorphsystem.model.LeaveRequest;

import com.cjme.motorphsystem.service.EmployeeService;
import com.cjme.motorphsystem.service.GovernmentIDsService;
import com.cjme.motorphsystem.service.SalaryService;
import com.cjme.motorphsystem.service.UserSession;
import com.cjme.motorphsystem.service.LeaveRequestService;

import com.cjme.motorphsystem.util.DBConnection;
import com.cjme.motorphsystem.util.ReportGenerator;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 *
 * @author MYS
 */

public final class MainAppFrame extends javax.swing.JFrame {
    private final UserSession session;
    private final EmployeeService employeeService;
    private final SalaryService salaryService;
    private final LeaveRequestService leaveRequestService;
    private DefaultTableModel leaveTableModel;
    private final int loggedInEmployeeId;
    
    /**
     * Creates new form Payroll
     * @param employeeId
     * @param session
     */
    
    public MainAppFrame(UserSession session) {
       this.session = session;
       this.salaryService = new SalaryService(
        new SalaryDAOImpl()
       );
       
       this.loggedInEmployeeId = session.getEmployeeId();
       this.employeeService   = new EmployeeService(
        new EmployeeEntityDAOImpl(),
        new EmployeeProfileDAOImpl());
       
       
        this.leaveRequestService = new LeaveRequestService(
        new LeaveRequestDAOImpl()
        );

        
       
        initComponents();
        setLocationRelativeTo(null);
        setupTabs();    
        loadEmployeeInformation();
        
        initComboBoxes(); 
        initLeaveTable();       
        loadAllLeaveRequests(); 
        
    }
    
    private void initLeaveTable() {
    
    leaveTableModel = new DefaultTableModel(
        new Object[]{"ID", "Emp ID", "Type", "Start Date", "End Date", "Reason", "Status"},
        0 
        )
    {
        
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    LMTable.setModel(leaveTableModel); 

    // MouseListener for LMTable to enable selection for Approve/Delete
    
    LMTable.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int selectedRow = LMTable.getSelectedRow();
            LMApproveButton.setEnabled(selectedRow != -1);
            LMDeleteButton.setEnabled(selectedRow != -1);
        }
    });
    }
    
    private void initComboBoxes() {
    LMLeaveComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
        "All",            
        "Sick",          
        "Regular",        
        "Vacation"       
    }));
    LMLeaveComboBox.setSelectedIndex(0); // Select "All" by default

   
    LMStatusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
        "All",        
        "Pending",       
        "Approved",      
        "Denied"
    }));
    LMStatusComboBox.setSelectedIndex(0); // Select "All" by default
    }
    
    // Method to load ALL leave requests (e.g., on tab open or refresh)
    private void loadAllLeaveRequests() {
    leaveTableModel.setRowCount(0); // Clear existing data in the table

    try {
        List<LeaveRequest> requests = leaveRequestService.getAllLeaveRequests();
        for (LeaveRequest request : requests) {
            // Add a row for each LeaveRequest object
            leaveTableModel.addRow(new Object[]{
                request.getLeaveRequestId(),
                request.getEmployeeId(),
                request.getLeaveType(),
                request.getLeaveStart().toString(),
                request.getLeaveEnd().toString(),   
                request.getReason(),
                request.getLeaveStatus()
            });
        }
    } catch (SQLException ex) {
        // Handle database errors
        JOptionPane.showMessageDialog(this, "Error loading all leave requests: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace(); // For debugging purposes
    }
    }  
    
    
    // Method to filter leave requests based on GUI input
    private void filterLeaveRequests() {
        
    System.out.println("DEBUG: filterLeaveRequests() called.");
    leaveTableModel.setRowCount(0); // Clear table before applying filters

    // Get filter values from GUI components
    Integer employeeId = null;
    String employeeIdText = LMEmployeeNameIDTextField.getText().trim();
    
    System.out.println("DEBUG: employeeIdText content (trimmed): '" + employeeIdText + "'");
    System.out.println("DEBUG: employeeIdText isEmpty(): " + employeeIdText.isEmpty());
    
    if (!employeeIdText.isEmpty()) { // This condition should prevent parsing if it's empty
        try {
            employeeId = Integer.parseInt(employeeIdText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric Employee ID.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            return; // Stop processing if ID is invalid
        }
    }
    
    System.out.println("DEBUG: Filter - Employee ID (from GUI): " + employeeId);

    String leaveType = (String) LMLeaveComboBox.getSelectedItem();
    // Handle "All" option from ComboBox (if present)
    if (leaveType != null && "All".equalsIgnoreCase(leaveType)) {
        leaveType = null;
    }
    
     System.out.println("DEBUG: Filter - Leave Type (from GUI): " + leaveType);

    String status = (String) LMStatusComboBox.getSelectedItem();
    // Handle "All" option from ComboBox (if present)
    if (status != null && "All".equalsIgnoreCase(status)) {
        status = null;
    }
    
     System.out.println("DEBUG: Filter - Status (from GUI): " + status);

    // Convert java.util.Date from JDateChooser to java.time.LocalDate
    
    LocalDate startDate = null;
    if (LMStartDateChooser.getDate() != null) {
        startDate = LMStartDateChooser.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
    }
    
    System.out.println("DEBUG: Filter - Start Date (from GUI): " + startDate);
    
    LocalDate endDate = null;
    if (LMEndDateChooser.getDate() != null) {
        endDate = LMEndDateChooser.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
    }
    
    System.out.println("DEBUG: Filter - End Date (from GUI): " + endDate);

    try {
        // Call the search method in your service
        List<LeaveRequest> requests = leaveRequestService.searchLeaveRequests(employeeId, leaveType, status, startDate, endDate);
        for (LeaveRequest request : requests) {
            // Add rows to the table model with filtered data
            leaveTableModel.addRow(new Object[]{
                request.getLeaveRequestId(),
                request.getEmployeeId(),
                request.getLeaveType(),
                request.getLeaveStart().toString(),
                request.getLeaveEnd().toString(),
                request.getReason(),
                request.getLeaveStatus()
            });
        }
    } catch (SQLException ex) {
        // Handle database errors during filtering
        JOptionPane.showMessageDialog(this, "Error filtering leave requests: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPanel = new javax.swing.JTabbedPane();
        EmployeeInfoPanel = new javax.swing.JPanel();
        EIWelcomePanel = new javax.swing.JPanel();
        EIWelcomeLabel = new javax.swing.JLabel();
        EIWelcomeNameLabel = new javax.swing.JLabel();
        EIEmployeeIDLabel = new javax.swing.JLabel();
        EIEmployeeIDTextField = new javax.swing.JTextField();
        EIDepartmentLabel = new javax.swing.JLabel();
        EIDepartmentTextField = new javax.swing.JTextField();
        EIDetailsIDsPanel = new javax.swing.JPanel();
        EIPersonalLabel = new javax.swing.JLabel();
        EIIDLabel = new javax.swing.JLabel();
        EIAddressLabel = new javax.swing.JLabel();
        EIPhoneLabel = new javax.swing.JLabel();
        EIBirthdayLabel = new javax.swing.JLabel();
        EIAddressTextField = new javax.swing.JTextField();
        EIBirthdayTextField = new javax.swing.JTextField();
        EIPhoneTextField = new javax.swing.JTextField();
        EISSSLabel = new javax.swing.JLabel();
        EIPhilHealthLabel = new javax.swing.JLabel();
        EISSSTextField = new javax.swing.JTextField();
        EIPhilHealthTextField = new javax.swing.JTextField();
        EIFullNameLabel = new javax.swing.JLabel();
        EIFullNameTextField = new javax.swing.JTextField();
        EIPagIBIGLabel = new javax.swing.JLabel();
        EITINLabel = new javax.swing.JLabel();
        EIPagIBIGTextField = new javax.swing.JTextField();
        EITINTextField = new javax.swing.JTextField();
        EIEmployeeInfoPanel = new javax.swing.JPanel();
        EIEILabel = new javax.swing.JLabel();
        EIPositionLabel = new javax.swing.JLabel();
        EIStatusLabel = new javax.swing.JLabel();
        EISupervisorLabel = new javax.swing.JLabel();
        EIBasicSalaryLabel = new javax.swing.JLabel();
        EIPositionTextField = new javax.swing.JTextField();
        EIStatusTextField = new javax.swing.JTextField();
        EISupervisorTextField = new javax.swing.JTextField();
        EIBasicSalaryTextField = new javax.swing.JTextField();
        EIApplyForLeaveButton = new javax.swing.JButton();
        EmployeeManagementPanel = new javax.swing.JPanel();
        EMSearchPanel = new javax.swing.JPanel();
        EMSearchLabel = new javax.swing.JLabel();
        EMEmployeeSearchTextField = new javax.swing.JTextField();
        EMEmployeeSearchButton = new javax.swing.JButton();
        EMScrollPane = new javax.swing.JScrollPane();
        EMTable = new javax.swing.JTable();
        EMButtonPanel = new javax.swing.JPanel();
        EMEditButton = new javax.swing.JButton();
        EMAddButton = new javax.swing.JButton();
        EMDeleteButton = new javax.swing.JButton();
        EMDetailPanel = new javax.swing.JPanel();
        EMDetailLabel = new javax.swing.JLabel();
        EMEmployeeIDLabel = new javax.swing.JLabel();
        EMLastNameLabel = new javax.swing.JLabel();
        EMFirstNameLabel = new javax.swing.JLabel();
        EMMiddleNameLabel = new javax.swing.JLabel();
        EMAddressIDLabel = new javax.swing.JLabel();
        EMBirthdayLabel = new javax.swing.JLabel();
        EMPhoneLabel = new javax.swing.JLabel();
        EMStatusLabel = new javax.swing.JLabel();
        EMPositionLabel = new javax.swing.JLabel();
        EMEmployeeIDTextField = new javax.swing.JTextField();
        EMLastNameTextField = new javax.swing.JTextField();
        EMFirstNameTextField = new javax.swing.JTextField();
        EMMiddleNameTextField = new javax.swing.JTextField();
        EMAddressTextField = new javax.swing.JTextField();
        EMBirthdayTextField = new javax.swing.JTextField();
        EMPhoneTextField = new javax.swing.JTextField();
        EMStatusTextField = new javax.swing.JTextField();
        EMPositionTextField = new javax.swing.JTextField();
        EMDepartmentLabel = new javax.swing.JLabel();
        EMBasicSalaryLabel = new javax.swing.JLabel();
        EMRiceAllowanceLabel = new javax.swing.JLabel();
        EMPhoneAllowanceLabel = new javax.swing.JLabel();
        EMClothingAllowanceLabel = new javax.swing.JLabel();
        EMSSSLabel = new javax.swing.JLabel();
        EMPhilHealthLabel = new javax.swing.JLabel();
        EMPagIBIGLabel = new javax.swing.JLabel();
        EMTINLabel = new javax.swing.JLabel();
        EMDepartmentTextField = new javax.swing.JTextField();
        EMBasicSalaryTextField = new javax.swing.JTextField();
        EMRiceAllowanceTextField = new javax.swing.JTextField();
        EMPhoneAllowanceTextField = new javax.swing.JTextField();
        EMClothingAllowanceTextField = new javax.swing.JTextField();
        EMSSSTextField = new javax.swing.JTextField();
        EMPhilHealthTextField = new javax.swing.JTextField();
        EMPagIBIGTextField = new javax.swing.JTextField();
        EMTINTextField = new javax.swing.JTextField();
        EMClearFormButton = new javax.swing.JButton();
        EMSaveButton = new javax.swing.JButton();
        EMBuildingLabel = new javax.swing.JLabel();
        EMStreetLabel = new javax.swing.JLabel();
        EMCityLabel = new javax.swing.JLabel();
        EMProvinceLabel = new javax.swing.JLabel();
        EMBuildingTextField = new javax.swing.JTextField();
        EMStreetTextField = new javax.swing.JTextField();
        EMCityTextField = new javax.swing.JTextField();
        EMProvinceTextField = new javax.swing.JTextField();
        EMZIPLabel = new javax.swing.JLabel();
        EMZIPTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        AttendancePanel = new javax.swing.JPanel();
        AttendanceSubPanel = new javax.swing.JPanel();
        ASearchLabel = new javax.swing.JLabel();
        AStartDateLabel = new javax.swing.JLabel();
        AEndDateLabel = new javax.swing.JLabel();
        ALoadDataButton = new javax.swing.JButton();
        ARefreshButton = new javax.swing.JButton();
        AScrollPane = new javax.swing.JScrollPane();
        ATable = new javax.swing.JTable();
        AStartDateChooser = new com.toedter.calendar.JDateChooser();
        AEndDateChooser = new com.toedter.calendar.JDateChooser();
        ASearchTextField = new javax.swing.JTextField();
        ASearchButton = new javax.swing.JButton();
        AButtonPanel = new javax.swing.JPanel();
        AAddTimeButton = new javax.swing.JButton();
        AEditTimeButton = new javax.swing.JButton();
        ADeleteTimeButton = new javax.swing.JButton();
        ARecordOvertimeButton = new javax.swing.JButton();
        PayrollPanel = new javax.swing.JPanel();
        PayrollTopPanel = new javax.swing.JPanel();
        PSearchEmployeeLabel = new javax.swing.JLabel();
        PPayrollPeriodLabel = new javax.swing.JLabel();
        PLoadDataButton = new javax.swing.JButton();
        PStartPayrollPeriodDateChooser = new com.toedter.calendar.JDateChooser();
        PSearchTextField = new javax.swing.JTextField();
        PSearchButton = new javax.swing.JButton();
        PEndPayrollPeriod = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        PayrollEarningsPanel = new javax.swing.JPanel();
        PPayrollInfoLabel = new javax.swing.JLabel();
        PEmployeeNoLabel = new javax.swing.JLabel();
        PFullNameLabel = new javax.swing.JLabel();
        PDepartmentLabel = new javax.swing.JLabel();
        PPositionLabel = new javax.swing.JLabel();
        PPayPeriodLabel = new javax.swing.JLabel();
        PGrossIncomeLabel = new javax.swing.JLabel();
        PBasicSalaryTextField = new javax.swing.JTextField();
        PRiceAllowanceTextField = new javax.swing.JTextField();
        PPhoneAllowanceTextField = new javax.swing.JTextField();
        PClothingAllowanceTextField = new javax.swing.JTextField();
        POvertimePayTextField = new javax.swing.JTextField();
        PGrossPayTextField = new javax.swing.JTextField();
        PayrollDeductionsPanel = new javax.swing.JPanel();
        PSSSLabel = new javax.swing.JLabel();
        PSSSNoLabel = new javax.swing.JLabel();
        PPhilHealthContributionLabel = new javax.swing.JLabel();
        PPagIBIGContributionLabel = new javax.swing.JLabel();
        PWithholdingTaxLabel = new javax.swing.JLabel();
        PPagIBIGNoLabel = new javax.swing.JLabel();
        PTINLabel = new javax.swing.JLabel();
        PSSSNoTextField = new javax.swing.JTextField();
        PPhilHealthContributionTextField = new javax.swing.JTextField();
        PPagIBIGContributionTextField = new javax.swing.JTextField();
        PWithholdingTaxTextField = new javax.swing.JTextField();
        PPagIBIGNoTextField = new javax.swing.JTextField();
        PTINTextField = new javax.swing.JTextField();
        PSSSContributionLabel = new javax.swing.JLabel();
        PPhilHealthNoLabel = new javax.swing.JLabel();
        PSSSContributionTextField = new javax.swing.JTextField();
        PPhilHealthNoTextField = new javax.swing.JTextField();
        PPHLabel = new javax.swing.JLabel();
        PBIRLabel = new javax.swing.JLabel();
        PPagIBIGLabel = new javax.swing.JLabel();
        PayrollButtonsPanel = new javax.swing.JPanel();
        PCalculatePayrollButton = new javax.swing.JButton();
        PGeneratePDFButton = new javax.swing.JButton();
        PSaveDataButton = new javax.swing.JButton();
        PNetPayLabel = new javax.swing.JLabel();
        PNetPayTextField = new javax.swing.JTextField();
        ReportsPanel = new javax.swing.JPanel();
        ReportsSubPanel = new javax.swing.JPanel();
        ReportsTabbedPane = new javax.swing.JTabbedPane();
        RPayrollReportsTab = new javax.swing.JPanel();
        RPayrollTopPanel = new javax.swing.JPanel();
        RPayrollPeriodLabel = new javax.swing.JLabel();
        RPDepartmentLabel = new javax.swing.JLabel();
        RPDepartmentComboBox = new javax.swing.JComboBox<>();
        RPGenerateReportButton = new javax.swing.JButton();
        RPayrollDateChooser = new com.toedter.calendar.JDateChooser();
        RPayrollScrollPane = new javax.swing.JScrollPane();
        RPayrollTable = new javax.swing.JTable();
        RPayrollBottomPanel = new javax.swing.JPanel();
        RPExportPDFButton = new javax.swing.JButton();
        RPPrintReportButton = new javax.swing.JButton();
        RAttendanceReportsTab = new javax.swing.JPanel();
        RAttendanceTopPanel = new javax.swing.JPanel();
        RAStartDateLabel = new javax.swing.JLabel();
        RAEndDateLabel = new javax.swing.JLabel();
        RAEmployeeLabel = new javax.swing.JLabel();
        RAGenerateReportButton = new javax.swing.JButton();
        RAStartDateChooser = new com.toedter.calendar.JDateChooser();
        RAEndDateChooser = new com.toedter.calendar.JDateChooser();
        REmployeeSearchTextField = new javax.swing.JTextField();
        RASearchButton = new javax.swing.JButton();
        RAttendanceScrollPane = new javax.swing.JScrollPane();
        RAttendanceTable = new javax.swing.JTable();
        RAttendanceBottomPanel = new javax.swing.JPanel();
        RAExportPDFButton = new javax.swing.JButton();
        RAPrintReportButton = new javax.swing.JButton();
        LeaveManagementPanel = new javax.swing.JPanel();
        LMTopPanel = new javax.swing.JPanel();
        LMFilterLabel = new javax.swing.JLabel();
        LMEmployeeNameIDTextField = new javax.swing.JTextField();
        LMEmployeeNameIDLabel = new javax.swing.JLabel();
        LMLeaveTypeLabel = new javax.swing.JLabel();
        LMLeaveComboBox = new javax.swing.JComboBox<>();
        LMStatusLabel = new javax.swing.JLabel();
        LMStatusComboBox = new javax.swing.JComboBox<>();
        LMStartDateLabel = new javax.swing.JLabel();
        LMStartDateChooser = new com.toedter.calendar.JDateChooser();
        LMEndDateLabel = new javax.swing.JLabel();
        LMEndDateChooser = new com.toedter.calendar.JDateChooser();
        LMApplyFiltersButton = new javax.swing.JButton();
        LMClearFiltersButton = new javax.swing.JButton();
        LMRefreshButton = new javax.swing.JButton();
        LMScrollPane = new javax.swing.JScrollPane();
        LMTable = new javax.swing.JTable();
        LMSubPanel = new javax.swing.JPanel();
        LMApproveButton = new javax.swing.JButton();
        LMDeleteButton = new javax.swing.JButton();
        SettingsPanel = new javax.swing.JPanel();
        SettingsSubPanel = new javax.swing.JPanel();
        SLogOutButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1000, 630));
        setMinimumSize(new java.awt.Dimension(1000, 630));
        setResizable(false);

        MainPanel.setMaximumSize(new java.awt.Dimension(1000, 630));
        MainPanel.setMinimumSize(new java.awt.Dimension(1000, 630));
        MainPanel.setPreferredSize(new java.awt.Dimension(1000, 630));

        EmployeeInfoPanel.setMaximumSize(new java.awt.Dimension(1000, 630));
        EmployeeInfoPanel.setMinimumSize(new java.awt.Dimension(1000, 630));
        EmployeeInfoPanel.setPreferredSize(new java.awt.Dimension(1000, 630));

        EIWelcomeLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        EIWelcomeLabel.setText("Welcome,");

        EIWelcomeNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        EIWelcomeNameLabel.setText("Name");

        EIEmployeeIDLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        EIEmployeeIDLabel.setText("Employee ID:");

        EIEmployeeIDTextField.setText(" ");
        EIEmployeeIDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EIEmployeeIDTextFieldActionPerformed(evt);
            }
        });

        EIDepartmentLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        EIDepartmentLabel.setText("Department:");

        EIDepartmentTextField.setText("jTextField34");

        javax.swing.GroupLayout EIWelcomePanelLayout = new javax.swing.GroupLayout(EIWelcomePanel);
        EIWelcomePanel.setLayout(EIWelcomePanelLayout);
        EIWelcomePanelLayout.setHorizontalGroup(
            EIWelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EIWelcomePanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(EIWelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EIWelcomePanelLayout.createSequentialGroup()
                        .addComponent(EIEmployeeIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EIEmployeeIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(EIDepartmentLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EIDepartmentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EIWelcomePanelLayout.createSequentialGroup()
                        .addComponent(EIWelcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EIWelcomeNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        EIWelcomePanelLayout.setVerticalGroup(
            EIWelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EIWelcomePanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(EIWelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EIWelcomeLabel)
                    .addComponent(EIWelcomeNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(EIWelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EIEmployeeIDLabel)
                    .addComponent(EIEmployeeIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EIDepartmentLabel)
                    .addComponent(EIDepartmentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        EIPersonalLabel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        EIPersonalLabel.setText("Personal Details");

        EIIDLabel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        EIIDLabel.setText("Statutory IDs");

        EIAddressLabel.setText("Address:");

        EIPhoneLabel.setText("Phone No.:");

        EIBirthdayLabel.setText("Birthday:");

        EIAddressTextField.setText("jTextField35");

        EIBirthdayTextField.setText("jTextField36");

        EIPhoneTextField.setText("jTextField37");

        EISSSLabel.setText("SSS No.:");

        EIPhilHealthLabel.setText("PhilHealth No.:");

        EISSSTextField.setText("jTextField38");

        EIPhilHealthTextField.setText("jTextField39");

        EIFullNameLabel.setText("Full Name:");

        EIFullNameTextField.setText("jTextField49");

        EIPagIBIGLabel.setText("Pag-IBIG No.:");

        EITINLabel.setText("TIN No.:");

        EIPagIBIGTextField.setText("jTextField40");

        EITINTextField.setText("jTextField41");

        javax.swing.GroupLayout EIDetailsIDsPanelLayout = new javax.swing.GroupLayout(EIDetailsIDsPanel);
        EIDetailsIDsPanel.setLayout(EIDetailsIDsPanelLayout);
        EIDetailsIDsPanelLayout.setHorizontalGroup(
            EIDetailsIDsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EIDetailsIDsPanelLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(EIDetailsIDsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EIDetailsIDsPanelLayout.createSequentialGroup()
                        .addGroup(EIDetailsIDsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(EIPagIBIGLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EITINLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(EIDetailsIDsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EIPagIBIGTextField)
                            .addComponent(EITINTextField)))
                    .addGroup(EIDetailsIDsPanelLayout.createSequentialGroup()
                        .addGroup(EIDetailsIDsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(EIPhilHealthLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EISSSLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EIDetailsIDsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EIPhilHealthTextField)
                            .addComponent(EISSSTextField)))
                    .addComponent(EIIDLabel)
                    .addGroup(EIDetailsIDsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(EIDetailsIDsPanelLayout.createSequentialGroup()
                            .addComponent(EIAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(EIAddressTextField))
                        .addGroup(EIDetailsIDsPanelLayout.createSequentialGroup()
                            .addGroup(EIDetailsIDsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(EIPhoneLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(EIBirthdayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(EIDetailsIDsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(EIBirthdayTextField)
                                .addComponent(EIPhoneTextField)))
                        .addGroup(EIDetailsIDsPanelLayout.createSequentialGroup()
                            .addComponent(EIFullNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(EIFullNameTextField))
                        .addGroup(EIDetailsIDsPanelLayout.createSequentialGroup()
                            .addComponent(EIPersonalLabel)
                            .addGap(294, 294, 294))))
                .addContainerGap())
        );
        EIDetailsIDsPanelLayout.setVerticalGroup(
            EIDetailsIDsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EIDetailsIDsPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(EIPersonalLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EIDetailsIDsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EIFullNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EIFullNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EIDetailsIDsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EIAddressLabel)
                    .addComponent(EIAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EIDetailsIDsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EIBirthdayLabel)
                    .addComponent(EIBirthdayTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EIDetailsIDsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EIPhoneLabel)
                    .addComponent(EIPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(EIIDLabel)
                .addGap(18, 18, 18)
                .addGroup(EIDetailsIDsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EISSSLabel)
                    .addComponent(EISSSTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EIDetailsIDsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EIPhilHealthLabel)
                    .addComponent(EIPhilHealthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EIDetailsIDsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EIPagIBIGLabel)
                    .addComponent(EIPagIBIGTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EIDetailsIDsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EITINLabel)
                    .addComponent(EITINTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        EIEILabel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        EIEILabel.setText("Employment Information");

        EIPositionLabel.setText("Position:");

        EIStatusLabel.setText("Status:");

        EISupervisorLabel.setText("Supervisor");

        EIBasicSalaryLabel.setText("Basic Salary:");

        EIPositionTextField.setText("jTextField42");

        EIStatusTextField.setText("jTextField43");

        EISupervisorTextField.setText("jTextField44");

        EIBasicSalaryTextField.setText("jTextField45");

        EIApplyForLeaveButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        EIApplyForLeaveButton.setText("Apply for Leave");
        EIApplyForLeaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EIApplyForLeaveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EIEmployeeInfoPanelLayout = new javax.swing.GroupLayout(EIEmployeeInfoPanel);
        EIEmployeeInfoPanel.setLayout(EIEmployeeInfoPanelLayout);
        EIEmployeeInfoPanelLayout.setHorizontalGroup(
            EIEmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EIEmployeeInfoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(EIEmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EIEILabel)
                    .addGroup(EIEmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(EIApplyForLeaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(EIEmployeeInfoPanelLayout.createSequentialGroup()
                            .addGroup(EIEmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(EIStatusLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(EISupervisorLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(EIBasicSalaryLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                .addComponent(EIPositionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(EIEmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(EIPositionTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                                .addComponent(EIStatusTextField)
                                .addComponent(EISupervisorTextField)
                                .addComponent(EIBasicSalaryTextField)))))
                .addGap(66, 66, 66))
        );
        EIEmployeeInfoPanelLayout.setVerticalGroup(
            EIEmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EIEmployeeInfoPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(EIEILabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EIEmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EIPositionLabel)
                    .addComponent(EIPositionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EIEmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EIStatusLabel)
                    .addComponent(EIStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EIEmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EISupervisorLabel)
                    .addComponent(EISupervisorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EIEmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EIBasicSalaryLabel)
                    .addComponent(EIBasicSalaryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                .addComponent(EIApplyForLeaveButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout EmployeeInfoPanelLayout = new javax.swing.GroupLayout(EmployeeInfoPanel);
        EmployeeInfoPanel.setLayout(EmployeeInfoPanelLayout);
        EmployeeInfoPanelLayout.setHorizontalGroup(
            EmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EIWelcomePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(EmployeeInfoPanelLayout.createSequentialGroup()
                .addComponent(EIDetailsIDsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(EIEmployeeInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );
        EmployeeInfoPanelLayout.setVerticalGroup(
            EmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeeInfoPanelLayout.createSequentialGroup()
                .addComponent(EIWelcomePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EmployeeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EIDetailsIDsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EIEmployeeInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        MainPanel.addTab("Employee Information", EmployeeInfoPanel);

        EmployeeManagementPanel.setMaximumSize(new java.awt.Dimension(1000, 630));
        EmployeeManagementPanel.setMinimumSize(new java.awt.Dimension(1000, 630));
        EmployeeManagementPanel.setPreferredSize(new java.awt.Dimension(1000, 630));

        EMSearchLabel.setText("Search Employee:");

        EMEmployeeSearchTextField.setText("Search by ID or Name...");
        EMEmployeeSearchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EMEmployeeSearchTextFieldActionPerformed(evt);
            }
        });

        EMEmployeeSearchButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        EMEmployeeSearchButton.setText("Search");

        javax.swing.GroupLayout EMSearchPanelLayout = new javax.swing.GroupLayout(EMSearchPanel);
        EMSearchPanel.setLayout(EMSearchPanelLayout);
        EMSearchPanelLayout.setHorizontalGroup(
            EMSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EMSearchPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(EMSearchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EMEmployeeSearchTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EMEmployeeSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        EMSearchPanelLayout.setVerticalGroup(
            EMSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EMSearchPanelLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(EMSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EMSearchLabel)
                    .addComponent(EMEmployeeSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EMEmployeeSearchButton))
                .addContainerGap())
        );

        EMTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Employee ID", "Last Name", "First Name", "Position", "Department"
            }
        ));
        EMScrollPane.setViewportView(EMTable);

        EMEditButton.setText("Edit Employee");

        EMAddButton.setText("Add Employee");

        EMDeleteButton.setBackground(new java.awt.Color(204, 0, 0));
        EMDeleteButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        EMDeleteButton.setForeground(new java.awt.Color(255, 255, 255));
        EMDeleteButton.setText("Delete Employee");

        javax.swing.GroupLayout EMButtonPanelLayout = new javax.swing.GroupLayout(EMButtonPanel);
        EMButtonPanel.setLayout(EMButtonPanelLayout);
        EMButtonPanelLayout.setHorizontalGroup(
            EMButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EMButtonPanelLayout.createSequentialGroup()
                .addComponent(EMDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(EMAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EMEditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        EMButtonPanelLayout.setVerticalGroup(
            EMButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EMButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(EMEditButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(EMAddButton)
                .addComponent(EMDeleteButton))
        );

        EMDetailLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        EMDetailLabel.setText("Employee Details");

        EMEmployeeIDLabel.setText("Employee ID:");

        EMLastNameLabel.setText("Last Name:");

        EMFirstNameLabel.setText("First Name:");

        EMMiddleNameLabel.setText("Middle Name:");

        EMAddressIDLabel.setText("Address ID:");

        EMBirthdayLabel.setText("Birthday:");

        EMPhoneLabel.setText("Phone No.:");

        EMStatusLabel.setText("Status:");

        EMPositionLabel.setText("Position:");

        EMEmployeeIDTextField.setText(" ");

        EMLastNameTextField.setText(" ");

        EMFirstNameTextField.setText(" ");
        EMFirstNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EMFirstNameTextFieldActionPerformed(evt);
            }
        });

        EMMiddleNameTextField.setText(" ");

        EMAddressTextField.setText(" ");

        EMBirthdayTextField.setText(" ");
        EMBirthdayTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EMBirthdayTextFieldActionPerformed(evt);
            }
        });

        EMPhoneTextField.setText(" ");

        EMStatusTextField.setText(" ");

        EMPositionTextField.setText(" ");

        EMDepartmentLabel.setText("Department:");

        EMBasicSalaryLabel.setText("Basic Salary:");

        EMRiceAllowanceLabel.setText("Rice Allowance:");

        EMPhoneAllowanceLabel.setText("Phone Allowance:");

        EMClothingAllowanceLabel.setText("Clothing Allowance:");

        EMSSSLabel.setText("SSS No.:");

        EMPhilHealthLabel.setText("PhilHealth No.:");

        EMPagIBIGLabel.setText("Pag-IBIG No.:");

        EMTINLabel.setText("TIN No.:");

        EMDepartmentTextField.setText(" ");

        EMBasicSalaryTextField.setText(" ");

        EMRiceAllowanceTextField.setText(" ");
        EMRiceAllowanceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EMRiceAllowanceTextFieldActionPerformed(evt);
            }
        });

        EMPhoneAllowanceTextField.setText(" ");

        EMClothingAllowanceTextField.setText(" ");

        EMSSSTextField.setText(" ");
        EMSSSTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EMSSSTextFieldActionPerformed(evt);
            }
        });

        EMPhilHealthTextField.setText(" ");

        EMPagIBIGTextField.setText(" ");
        EMPagIBIGTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EMPagIBIGTextFieldActionPerformed(evt);
            }
        });

        EMTINTextField.setText(" ");

        EMClearFormButton.setText("Clear Form");

        EMSaveButton.setBackground(new java.awt.Color(51, 153, 255));
        EMSaveButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        EMSaveButton.setForeground(new java.awt.Color(0, 0, 102));
        EMSaveButton.setText("Save Details");

        EMBuildingLabel.setText("Building:");

        EMStreetLabel.setText("Street:");

        EMCityLabel.setText("City:");

        EMProvinceLabel.setText("Province:");

        EMBuildingTextField.setText(" ");

        EMStreetTextField.setText(" ");
        EMStreetTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EMStreetTextFieldActionPerformed(evt);
            }
        });

        EMCityTextField.setText(" ");

        EMProvinceTextField.setText(" ");

        EMZIPLabel.setText("ZIP Code:");

        EMZIPTextField.setText(" ");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Employment Details");

        javax.swing.GroupLayout EMDetailPanelLayout = new javax.swing.GroupLayout(EMDetailPanel);
        EMDetailPanel.setLayout(EMDetailPanelLayout);
        EMDetailPanelLayout.setHorizontalGroup(
            EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EMDetailPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EMDetailPanelLayout.createSequentialGroup()
                        .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(EMLastNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EMEmployeeIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(EMEmployeeIDTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addComponent(EMLastNameTextField)))
                    .addGroup(EMDetailPanelLayout.createSequentialGroup()
                        .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(EMPositionLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EMStatusLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EMPhoneLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EMBirthdayLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EMAddressIDLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EMMiddleNameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EMFirstNameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EMDepartmentLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EMBuildingLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EMStreetLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EMCityLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EMProvinceLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EMZIPLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EMBasicSalaryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(EMStatusTextField)
                            .addComponent(EMPhoneTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EMBirthdayTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EMAddressTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EMPositionTextField)
                            .addComponent(EMDepartmentTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EMFirstNameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EMMiddleNameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EMBuildingTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EMStreetTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EMCityTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EMProvinceTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EMZIPTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EMBasicSalaryTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EMDetailPanelLayout.createSequentialGroup()
                        .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EMPhilHealthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(EMDetailPanelLayout.createSequentialGroup()
                                .addComponent(EMPhilHealthLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(113, 113, 113)))
                        .addGap(47, 47, 47))
                    .addGroup(EMDetailPanelLayout.createSequentialGroup()
                        .addComponent(EMPhoneAllowanceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addComponent(EMClothingAllowanceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EMSSSLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(EMDetailPanelLayout.createSequentialGroup()
                        .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EMRiceAllowanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EMRiceAllowanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(EMPagIBIGTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(EMPagIBIGLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(EMTINLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(EMTINTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(EMPhoneAllowanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(EMClearFormButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(EMSaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                            .addComponent(EMClothingAllowanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EMSSSTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(EMDetailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(EMDetailLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        EMDetailPanelLayout.setVerticalGroup(
            EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EMDetailPanelLayout.createSequentialGroup()
                .addComponent(EMDetailLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EMDetailPanelLayout.createSequentialGroup()
                        .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EMEmployeeIDLabel)
                            .addComponent(EMEmployeeIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EMLastNameLabel)
                            .addComponent(EMLastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EMFirstNameLabel)
                            .addComponent(EMFirstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EMMiddleNameLabel)
                            .addComponent(EMMiddleNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EMAddressIDLabel)
                            .addComponent(EMAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EMBuildingLabel)
                            .addComponent(EMBuildingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EMStreetLabel)
                            .addComponent(EMStreetTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EMCityLabel)
                            .addComponent(EMCityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EMProvinceLabel)
                            .addComponent(EMProvinceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EMZIPLabel)
                            .addComponent(EMZIPTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EMBirthdayLabel)
                            .addComponent(EMBirthdayTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EMPhoneLabel)
                            .addComponent(EMPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(EMDetailPanelLayout.createSequentialGroup()
                        .addComponent(EMRiceAllowanceLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EMRiceAllowanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EMPhoneAllowanceLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EMPhoneAllowanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EMClothingAllowanceLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EMClothingAllowanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EMSSSLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EMSSSTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EMPhilHealthLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EMPhilHealthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EMPagIBIGLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EMPagIBIGTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EMTINLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EMTINTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(EMDetailPanelLayout.createSequentialGroup()
                        .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(EMDetailPanelLayout.createSequentialGroup()
                                .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(EMStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(EMStatusLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(EMPositionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(EMPositionLabel))
                                .addGap(31, 31, 31))
                            .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(EMDepartmentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(EMDepartmentLabel)))
                        .addGap(5, 5, 5)
                        .addGroup(EMDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EMBasicSalaryLabel)
                            .addComponent(EMBasicSalaryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(EMDetailPanelLayout.createSequentialGroup()
                        .addComponent(EMClearFormButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EMSaveButton)))
                .addContainerGap())
        );

        javax.swing.GroupLayout EmployeeManagementPanelLayout = new javax.swing.GroupLayout(EmployeeManagementPanel);
        EmployeeManagementPanel.setLayout(EmployeeManagementPanelLayout);
        EmployeeManagementPanelLayout.setHorizontalGroup(
            EmployeeManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeeManagementPanelLayout.createSequentialGroup()
                .addGroup(EmployeeManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EMSearchPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(EmployeeManagementPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(EMScrollPane))
                    .addGroup(EmployeeManagementPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(EMButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EMDetailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        EmployeeManagementPanelLayout.setVerticalGroup(
            EmployeeManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeeManagementPanelLayout.createSequentialGroup()
                .addGroup(EmployeeManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(EmployeeManagementPanelLayout.createSequentialGroup()
                        .addComponent(EMSearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EMScrollPane)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EMButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EmployeeManagementPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(EMDetailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        MainPanel.addTab("Employee Management", EmployeeManagementPanel);

        AttendancePanel.setMaximumSize(new java.awt.Dimension(1000, 630));
        AttendancePanel.setMinimumSize(new java.awt.Dimension(1000, 630));
        AttendancePanel.setPreferredSize(new java.awt.Dimension(1000, 630));

        ASearchLabel.setText("Search Employee:");

        AStartDateLabel.setText("Start Date:");

        AEndDateLabel.setText("End Date:");

        ALoadDataButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ALoadDataButton.setText("Load Data");

        ARefreshButton.setText("Refresh");
        ARefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ARefreshButtonActionPerformed(evt);
            }
        });

        ATable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Date", "Time In", "Time Out", "Status", "Remarks", "Total Hours Worked"
            }
        ));
        AScrollPane.setViewportView(ATable);

        ASearchTextField.setText("Search...");

        ASearchButton.setText("Search");

        javax.swing.GroupLayout AttendanceSubPanelLayout = new javax.swing.GroupLayout(AttendanceSubPanel);
        AttendanceSubPanel.setLayout(AttendanceSubPanelLayout);
        AttendanceSubPanelLayout.setHorizontalGroup(
            AttendanceSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AttendanceSubPanelLayout.createSequentialGroup()
                .addGroup(AttendanceSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AttendanceSubPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(ASearchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ASearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ASearchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(AStartDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AStartDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AEndDateLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AEndDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(ALoadDataButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ARefreshButton))
                    .addGroup(AttendanceSubPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(AScrollPane)))
                .addContainerGap())
        );
        AttendanceSubPanelLayout.setVerticalGroup(
            AttendanceSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AttendanceSubPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(AttendanceSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AttendanceSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(AStartDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(AttendanceSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ASearchLabel)
                            .addComponent(ALoadDataButton)
                            .addComponent(ARefreshButton)
                            .addComponent(ASearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ASearchButton)))
                    .addGroup(AttendanceSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(AEndDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(AEndDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(AStartDateChooser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AAddTimeButton.setText("Add Time Entry");

        AEditTimeButton.setText("Edit Time Entry");

        ADeleteTimeButton.setBackground(new java.awt.Color(204, 0, 0));
        ADeleteTimeButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ADeleteTimeButton.setForeground(new java.awt.Color(255, 255, 255));
        ADeleteTimeButton.setText("Delete Time Entry");

        ARecordOvertimeButton.setText("Record Overtime");

        javax.swing.GroupLayout AButtonPanelLayout = new javax.swing.GroupLayout(AButtonPanel);
        AButtonPanel.setLayout(AButtonPanelLayout);
        AButtonPanelLayout.setHorizontalGroup(
            AButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AButtonPanelLayout.createSequentialGroup()
                .addComponent(ARecordOvertimeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ADeleteTimeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AEditTimeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AAddTimeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        AButtonPanelLayout.setVerticalGroup(
            AButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AButtonPanelLayout.createSequentialGroup()
                .addGroup(AButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AAddTimeButton)
                    .addComponent(AEditTimeButton)
                    .addComponent(ADeleteTimeButton)
                    .addComponent(ARecordOvertimeButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout AttendancePanelLayout = new javax.swing.GroupLayout(AttendancePanel);
        AttendancePanel.setLayout(AttendancePanelLayout);
        AttendancePanelLayout.setHorizontalGroup(
            AttendancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AttendancePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(AttendanceSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AttendancePanelLayout.setVerticalGroup(
            AttendancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AttendancePanelLayout.createSequentialGroup()
                .addComponent(AttendanceSubPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        MainPanel.addTab("Attendance", AttendancePanel);

        PayrollPanel.setMaximumSize(new java.awt.Dimension(1000, 630));
        PayrollPanel.setMinimumSize(new java.awt.Dimension(1000, 630));
        PayrollPanel.setPreferredSize(new java.awt.Dimension(1000, 630));
        PayrollPanel.setRequestFocusEnabled(false);

        PSearchEmployeeLabel.setText("Search Employee:");

        PPayrollPeriodLabel.setText("Start of Payroll Period:");

        PLoadDataButton.setText("Load Payroll Data");
        PLoadDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PLoadDataButtonActionPerformed(evt);
            }
        });

        PSearchTextField.setText("Search...");

        PSearchButton.setText("Search");

        PEndPayrollPeriod.setText("End of Payroll Period:");

        javax.swing.GroupLayout PayrollTopPanelLayout = new javax.swing.GroupLayout(PayrollTopPanel);
        PayrollTopPanel.setLayout(PayrollTopPanelLayout);
        PayrollTopPanelLayout.setHorizontalGroup(
            PayrollTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PayrollTopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PSearchEmployeeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PPayrollPeriodLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PStartPayrollPeriodDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PEndPayrollPeriod)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PLoadDataButton)
                .addGap(19, 19, 19))
        );
        PayrollTopPanelLayout.setVerticalGroup(
            PayrollTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PayrollTopPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(PayrollTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PPayrollPeriodLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PayrollTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PSearchEmployeeLabel)
                        .addComponent(PSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(PSearchButton))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PLoadDataButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PStartPayrollPeriodDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PEndPayrollPeriod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        PPayrollInfoLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        PPayrollInfoLabel.setText("Employee Payroll Information");

        PEmployeeNoLabel.setText("Employee No.:");

        PFullNameLabel.setText("Full Name:");

        PDepartmentLabel.setText("Department:");

        PPositionLabel.setText("Position:");

        PPayPeriodLabel.setText("Pay Period:");

        PGrossIncomeLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        PGrossIncomeLabel.setText("Gross Income:");

        PBasicSalaryTextField.setText("jTextField1");

        PRiceAllowanceTextField.setText("jTextField2");

        PPhoneAllowanceTextField.setText("jTextField3");

        PClothingAllowanceTextField.setText("jTextField4");

        POvertimePayTextField.setText("jTextField5");

        PGrossPayTextField.setText("jTextField6");

        javax.swing.GroupLayout PayrollEarningsPanelLayout = new javax.swing.GroupLayout(PayrollEarningsPanel);
        PayrollEarningsPanel.setLayout(PayrollEarningsPanelLayout);
        PayrollEarningsPanelLayout.setHorizontalGroup(
            PayrollEarningsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PayrollEarningsPanelLayout.createSequentialGroup()
                .addGroup(PayrollEarningsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PayrollEarningsPanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(PayrollEarningsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PayrollEarningsPanelLayout.createSequentialGroup()
                                .addComponent(PGrossIncomeLabel)
                                .addGap(18, 18, 18)
                                .addComponent(PGrossPayTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(PPayPeriodLabel)
                            .addComponent(PPositionLabel)
                            .addComponent(PDepartmentLabel)))
                    .addGroup(PayrollEarningsPanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(PayrollEarningsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PFullNameLabel)
                            .addComponent(PEmployeeNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PayrollEarningsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PBasicSalaryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PRiceAllowanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PPhoneAllowanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PClothingAllowanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(POvertimePayTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PayrollEarningsPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(PPayrollInfoLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PayrollEarningsPanelLayout.setVerticalGroup(
            PayrollEarningsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PayrollEarningsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PPayrollInfoLabel)
                .addGap(18, 18, 18)
                .addGroup(PayrollEarningsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PEmployeeNoLabel)
                    .addComponent(PBasicSalaryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PayrollEarningsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PRiceAllowanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PFullNameLabel))
                .addGap(18, 18, 18)
                .addGroup(PayrollEarningsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PPhoneAllowanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PDepartmentLabel))
                .addGap(18, 18, 18)
                .addGroup(PayrollEarningsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PPositionLabel)
                    .addComponent(PClothingAllowanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PayrollEarningsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PPayPeriodLabel)
                    .addComponent(POvertimePayTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PayrollEarningsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PGrossIncomeLabel)
                    .addComponent(PGrossPayTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        PBasicSalaryTextField.getAccessibleContext().setAccessibleName("");
        PBasicSalaryTextField.getAccessibleContext().setAccessibleDescription("");

        PSSSLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        PSSSLabel.setText("Social Security System");

        PSSSNoLabel.setText("SSS No.:");

        PPhilHealthContributionLabel.setText("PhilHealth Contributions:");

        PPagIBIGContributionLabel.setText("Pag-IBIG Contributions:");

        PWithholdingTaxLabel.setText("Withholding Tax:");

        PPagIBIGNoLabel.setText("Pag-IBIG No.:");

        PTINLabel.setText("TIN:");

        PSSSNoTextField.setText("jTextField1");

        PPhilHealthContributionTextField.setText("jTextField2");

        PPagIBIGContributionTextField.setText("jTextField3");

        PWithholdingTaxTextField.setText("jTextField4");
        PWithholdingTaxTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PWithholdingTaxTextFieldActionPerformed(evt);
            }
        });

        PPagIBIGNoTextField.setText("jTextField5");

        PTINTextField.setText("jTextField6");

        PSSSContributionLabel.setText("SSS Contributions:");

        PPhilHealthNoLabel.setText("PhilHealth No.:");

        PSSSContributionTextField.setText("jTextField1");

        PPhilHealthNoTextField.setText("jTextField2");

        PPHLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        PPHLabel.setText("PhilHealth");

        PBIRLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        PBIRLabel.setText("BIR");

        PPagIBIGLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        PPagIBIGLabel.setText("Pag-IBIG");

        javax.swing.GroupLayout PayrollDeductionsPanelLayout = new javax.swing.GroupLayout(PayrollDeductionsPanel);
        PayrollDeductionsPanel.setLayout(PayrollDeductionsPanelLayout);
        PayrollDeductionsPanelLayout.setHorizontalGroup(
            PayrollDeductionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PayrollDeductionsPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(PayrollDeductionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PayrollDeductionsPanelLayout.createSequentialGroup()
                        .addComponent(PSSSLabel)
                        .addGap(126, 126, 126)
                        .addGroup(PayrollDeductionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PBIRLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PayrollDeductionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(PayrollDeductionsPanelLayout.createSequentialGroup()
                                    .addComponent(PTINLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(PTINTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PayrollDeductionsPanelLayout.createSequentialGroup()
                                    .addComponent(PWithholdingTaxLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(PWithholdingTaxTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(34, Short.MAX_VALUE))
                    .addGroup(PayrollDeductionsPanelLayout.createSequentialGroup()
                        .addGroup(PayrollDeductionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PSSSNoLabel)
                            .addGroup(PayrollDeductionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(PayrollDeductionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(PayrollDeductionsPanelLayout.createSequentialGroup()
                                        .addGroup(PayrollDeductionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(PPagIBIGContributionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(PPagIBIGNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(PayrollDeductionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(PPagIBIGNoTextField)
                                            .addComponent(PPagIBIGContributionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(PPagIBIGLabel)
                                    .addComponent(PPHLabel)
                                    .addGroup(PayrollDeductionsPanelLayout.createSequentialGroup()
                                        .addGroup(PayrollDeductionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(PPhilHealthNoLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(PPhilHealthContributionLabel))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PayrollDeductionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(PPhilHealthContributionTextField)
                                            .addComponent(PPhilHealthNoTextField))))
                                .addGroup(PayrollDeductionsPanelLayout.createSequentialGroup()
                                    .addComponent(PSSSContributionLabel)
                                    .addGap(41, 41, 41)
                                    .addGroup(PayrollDeductionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(PSSSContributionTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                        .addComponent(PSSSNoTextField, javax.swing.GroupLayout.Alignment.LEADING)))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        PayrollDeductionsPanelLayout.setVerticalGroup(
            PayrollDeductionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PayrollDeductionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PayrollDeductionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PSSSLabel)
                    .addComponent(PBIRLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PayrollDeductionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PSSSNoLabel)
                    .addComponent(PSSSNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PTINLabel)
                    .addComponent(PTINTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PayrollDeductionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PSSSContributionLabel)
                    .addComponent(PSSSContributionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PWithholdingTaxLabel)
                    .addComponent(PWithholdingTaxTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(PPHLabel)
                .addGap(8, 8, 8)
                .addGroup(PayrollDeductionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PPhilHealthNoLabel)
                    .addComponent(PPhilHealthNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(PayrollDeductionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PPhilHealthContributionLabel)
                    .addComponent(PPhilHealthContributionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(PPagIBIGLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PayrollDeductionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PPagIBIGNoLabel)
                    .addComponent(PPagIBIGNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(PayrollDeductionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PPagIBIGContributionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PPagIBIGContributionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        PCalculatePayrollButton.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        PCalculatePayrollButton.setText("Calculate Payroll");
        PCalculatePayrollButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PCalculatePayrollButtonActionPerformed(evt);
            }
        });

        PGeneratePDFButton.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        PGeneratePDFButton.setText("Generate Payslip (PDF)");
        PGeneratePDFButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PGeneratePDFButtonActionPerformed(evt);
            }
        });

        PSaveDataButton.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        PSaveDataButton.setText("Save Payroll Data");

        PNetPayLabel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        PNetPayLabel.setText("Net Pay:");

        PNetPayTextField.setText("jTextField13");

        javax.swing.GroupLayout PayrollButtonsPanelLayout = new javax.swing.GroupLayout(PayrollButtonsPanel);
        PayrollButtonsPanel.setLayout(PayrollButtonsPanelLayout);
        PayrollButtonsPanelLayout.setHorizontalGroup(
            PayrollButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PayrollButtonsPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(PNetPayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PNetPayTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PCalculatePayrollButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PGeneratePDFButton, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(PSaveDataButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        PayrollButtonsPanelLayout.setVerticalGroup(
            PayrollButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PayrollButtonsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PayrollButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PayrollButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PCalculatePayrollButton)
                        .addComponent(PGeneratePDFButton)
                        .addComponent(PSaveDataButton))
                    .addGroup(PayrollButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PNetPayLabel)
                        .addComponent(PNetPayTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(73, 73, 73))
        );

        javax.swing.GroupLayout PayrollPanelLayout = new javax.swing.GroupLayout(PayrollPanel);
        PayrollPanel.setLayout(PayrollPanelLayout);
        PayrollPanelLayout.setHorizontalGroup(
            PayrollPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PayrollPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PayrollPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PayrollTopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PayrollPanelLayout.createSequentialGroup()
                        .addComponent(PayrollButtonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PayrollPanelLayout.createSequentialGroup()
                        .addComponent(PayrollEarningsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PayrollDeductionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))))
        );
        PayrollPanelLayout.setVerticalGroup(
            PayrollPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PayrollPanelLayout.createSequentialGroup()
                .addComponent(PayrollTopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PayrollPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PayrollEarningsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PayrollDeductionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PayrollButtonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        MainPanel.addTab("Payroll", PayrollPanel);

        ReportsPanel.setMaximumSize(new java.awt.Dimension(1000, 630));
        ReportsPanel.setMinimumSize(new java.awt.Dimension(1000, 630));
        ReportsPanel.setPreferredSize(new java.awt.Dimension(1000, 630));

        RPayrollPeriodLabel.setText("Payroll Period:");

        RPDepartmentLabel.setText("Department:");

        RPDepartmentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        RPGenerateReportButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        RPGenerateReportButton.setText("Generate Report");

        javax.swing.GroupLayout RPayrollTopPanelLayout = new javax.swing.GroupLayout(RPayrollTopPanel);
        RPayrollTopPanel.setLayout(RPayrollTopPanelLayout);
        RPayrollTopPanelLayout.setHorizontalGroup(
            RPayrollTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RPayrollTopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RPayrollPeriodLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RPayrollDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(RPDepartmentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RPDepartmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 245, Short.MAX_VALUE)
                .addComponent(RPGenerateReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        RPayrollTopPanelLayout.setVerticalGroup(
            RPayrollTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RPayrollTopPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(RPayrollTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RPayrollDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(RPayrollTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(RPayrollPeriodLabel)
                        .addComponent(RPDepartmentLabel)
                        .addComponent(RPDepartmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RPGenerateReportButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        RPayrollTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Employee ID", "Name", "Department", "Gross Pay", "Total Deductions", "Net Pay"
            }
        ));
        RPayrollScrollPane.setViewportView(RPayrollTable);

        RPExportPDFButton.setText("Export to PDF");
        RPExportPDFButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RPExportPDFButtonActionPerformed(evt);
            }
        });

        RPPrintReportButton.setText("Print Report");
        RPPrintReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RPPrintReportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RPayrollBottomPanelLayout = new javax.swing.GroupLayout(RPayrollBottomPanel);
        RPayrollBottomPanel.setLayout(RPayrollBottomPanelLayout);
        RPayrollBottomPanelLayout.setHorizontalGroup(
            RPayrollBottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RPayrollBottomPanelLayout.createSequentialGroup()
                .addGap(0, 200, Short.MAX_VALUE)
                .addComponent(RPPrintReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RPExportPDFButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        RPayrollBottomPanelLayout.setVerticalGroup(
            RPayrollBottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RPayrollBottomPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(RPayrollBottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RPExportPDFButton)
                    .addComponent(RPPrintReportButton))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout RPayrollReportsTabLayout = new javax.swing.GroupLayout(RPayrollReportsTab);
        RPayrollReportsTab.setLayout(RPayrollReportsTabLayout);
        RPayrollReportsTabLayout.setHorizontalGroup(
            RPayrollReportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RPayrollScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(RPayrollReportsTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RPayrollTopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RPayrollReportsTabLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(RPayrollBottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        RPayrollReportsTabLayout.setVerticalGroup(
            RPayrollReportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RPayrollReportsTabLayout.createSequentialGroup()
                .addComponent(RPayrollTopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RPayrollScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RPayrollBottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ReportsTabbedPane.addTab("Payroll Report", RPayrollReportsTab);

        RAStartDateLabel.setText("Start Date:");

        RAEndDateLabel.setText("End Date:");

        RAEmployeeLabel.setText("Employee:");

        RAGenerateReportButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        RAGenerateReportButton.setText("Generate Report");

        REmployeeSearchTextField.setText("jTextField1");

        RASearchButton.setText("Search");

        javax.swing.GroupLayout RAttendanceTopPanelLayout = new javax.swing.GroupLayout(RAttendanceTopPanel);
        RAttendanceTopPanel.setLayout(RAttendanceTopPanelLayout);
        RAttendanceTopPanelLayout.setHorizontalGroup(
            RAttendanceTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RAttendanceTopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RAStartDateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RAStartDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RAEndDateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RAEndDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(RAEmployeeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(REmployeeSearchTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RASearchButton)
                .addGap(18, 18, 18)
                .addComponent(RAGenerateReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        RAttendanceTopPanelLayout.setVerticalGroup(
            RAttendanceTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RAttendanceTopPanelLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(RAttendanceTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(RAEndDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RAStartDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(RAttendanceTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(RAStartDateLabel)
                        .addComponent(RAEndDateLabel)
                        .addComponent(RAEmployeeLabel)
                        .addComponent(RAGenerateReportButton)
                        .addComponent(REmployeeSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RASearchButton)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        RAttendanceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Employee ID", "Employee Name", "Department", "Total Days Worked", "Total Absences", "Total Lates (min/hrs)", "Total Overtime (hrs)", "Total Leaves Taken"
            }
        ));
        RAttendanceScrollPane.setViewportView(RAttendanceTable);

        RAExportPDFButton.setText("Export to PDF");
        RAExportPDFButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RAExportPDFButtonActionPerformed(evt);
            }
        });

        RAPrintReportButton.setText("Print Report");

        javax.swing.GroupLayout RAttendanceBottomPanelLayout = new javax.swing.GroupLayout(RAttendanceBottomPanel);
        RAttendanceBottomPanel.setLayout(RAttendanceBottomPanelLayout);
        RAttendanceBottomPanelLayout.setHorizontalGroup(
            RAttendanceBottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RAttendanceBottomPanelLayout.createSequentialGroup()
                .addGap(0, 124, Short.MAX_VALUE)
                .addComponent(RAPrintReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RAExportPDFButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        RAttendanceBottomPanelLayout.setVerticalGroup(
            RAttendanceBottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RAttendanceBottomPanelLayout.createSequentialGroup()
                .addGap(0, 2, Short.MAX_VALUE)
                .addGroup(RAttendanceBottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RAExportPDFButton)
                    .addComponent(RAPrintReportButton)))
        );

        javax.swing.GroupLayout RAttendanceReportsTabLayout = new javax.swing.GroupLayout(RAttendanceReportsTab);
        RAttendanceReportsTab.setLayout(RAttendanceReportsTabLayout);
        RAttendanceReportsTabLayout.setHorizontalGroup(
            RAttendanceReportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RAttendanceReportsTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RAttendanceReportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RAttendanceScrollPane)
                    .addGroup(RAttendanceReportsTabLayout.createSequentialGroup()
                        .addComponent(RAttendanceTopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(RAttendanceReportsTabLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(RAttendanceBottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        RAttendanceReportsTabLayout.setVerticalGroup(
            RAttendanceReportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RAttendanceReportsTabLayout.createSequentialGroup()
                .addComponent(RAttendanceTopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RAttendanceScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RAttendanceBottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ReportsTabbedPane.addTab("Attendance Report", RAttendanceReportsTab);

        javax.swing.GroupLayout ReportsSubPanelLayout = new javax.swing.GroupLayout(ReportsSubPanel);
        ReportsSubPanel.setLayout(ReportsSubPanelLayout);
        ReportsSubPanelLayout.setHorizontalGroup(
            ReportsSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportsSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ReportsTabbedPane)
                .addContainerGap())
        );
        ReportsSubPanelLayout.setVerticalGroup(
            ReportsSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportsSubPanelLayout.createSequentialGroup()
                .addComponent(ReportsTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout ReportsPanelLayout = new javax.swing.GroupLayout(ReportsPanel);
        ReportsPanel.setLayout(ReportsPanelLayout);
        ReportsPanelLayout.setHorizontalGroup(
            ReportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ReportsSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        ReportsPanelLayout.setVerticalGroup(
            ReportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReportsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ReportsSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        MainPanel.addTab("Reports", ReportsPanel);

        LMFilterLabel.setText("Filter By:");

        LMEmployeeNameIDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LMEmployeeNameIDTextFieldActionPerformed(evt);
            }
        });

        LMEmployeeNameIDLabel.setText("Employee Name / ID:");

        LMLeaveTypeLabel.setText("Leave Type:");

        LMLeaveComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        LMStatusLabel.setText("Status:");

        LMStatusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        LMStartDateLabel.setText("Start Date:");

        LMEndDateLabel.setText("End Date:");

        LMApplyFiltersButton.setText("Apply FIlters");
        LMApplyFiltersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LMApplyFiltersButtonActionPerformed(evt);
            }
        });

        LMClearFiltersButton.setText("Clear Filters");
        LMClearFiltersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LMClearFiltersButtonActionPerformed(evt);
            }
        });

        LMRefreshButton.setText("Refresh List");
        LMRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LMRefreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LMTopPanelLayout = new javax.swing.GroupLayout(LMTopPanel);
        LMTopPanel.setLayout(LMTopPanelLayout);
        LMTopPanelLayout.setHorizontalGroup(
            LMTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LMTopPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(LMTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LMFilterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(LMTopPanelLayout.createSequentialGroup()
                        .addComponent(LMEmployeeNameIDLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LMEmployeeNameIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(LMTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LMLeaveTypeLabel)
                    .addComponent(LMStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LMTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LMStatusComboBox, 0, 153, Short.MAX_VALUE)
                    .addComponent(LMLeaveComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(LMTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(LMTopPanelLayout.createSequentialGroup()
                        .addComponent(LMStartDateLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LMStartDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LMTopPanelLayout.createSequentialGroup()
                        .addComponent(LMEndDateLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LMEndDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(10, 10, 10)
                .addComponent(LMClearFiltersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LMTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LMRefreshButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LMApplyFiltersButton, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                .addContainerGap())
        );
        LMTopPanelLayout.setVerticalGroup(
            LMTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LMTopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LMTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LMTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(LMTopPanelLayout.createSequentialGroup()
                            .addComponent(LMFilterLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(LMTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(LMEmployeeNameIDLabel)
                                .addComponent(LMEmployeeNameIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, LMTopPanelLayout.createSequentialGroup()
                            .addGroup(LMTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(LMLeaveTypeLabel)
                                .addComponent(LMLeaveComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(LMTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(LMStatusLabel)
                                .addComponent(LMStatusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(LMTopPanelLayout.createSequentialGroup()
                        .addGroup(LMTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(LMStartDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LMStartDateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LMTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(LMClearFiltersButton)
                                .addComponent(LMApplyFiltersButton)))
                        .addGap(7, 7, 7)
                        .addGroup(LMTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LMEndDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LMEndDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LMRefreshButton)))))
        );

        LMTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Request ID", "Employee ID", "Employee Name", "Leave Type", "Start Date", "End Date", "Reason", "Leave Status"
            }
        ));
        LMScrollPane.setViewportView(LMTable);

        LMApproveButton.setBackground(new java.awt.Color(0, 153, 0));
        LMApproveButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LMApproveButton.setForeground(new java.awt.Color(255, 255, 255));
        LMApproveButton.setText("Approve Selected");
        LMApproveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LMApproveButtonActionPerformed(evt);
            }
        });

        LMDeleteButton.setBackground(new java.awt.Color(204, 0, 0));
        LMDeleteButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LMDeleteButton.setForeground(new java.awt.Color(255, 255, 255));
        LMDeleteButton.setText("Delete Selected");
        LMDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LMDeleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LMSubPanelLayout = new javax.swing.GroupLayout(LMSubPanel);
        LMSubPanel.setLayout(LMSubPanelLayout);
        LMSubPanelLayout.setHorizontalGroup(
            LMSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LMSubPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LMDeleteButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LMApproveButton)
                .addContainerGap())
        );
        LMSubPanelLayout.setVerticalGroup(
            LMSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LMSubPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LMSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LMApproveButton)
                    .addComponent(LMDeleteButton))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout LeaveManagementPanelLayout = new javax.swing.GroupLayout(LeaveManagementPanel);
        LeaveManagementPanel.setLayout(LeaveManagementPanelLayout);
        LeaveManagementPanelLayout.setHorizontalGroup(
            LeaveManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeaveManagementPanelLayout.createSequentialGroup()
                .addGroup(LeaveManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LMTopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(LeaveManagementPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(LMScrollPane))
                    .addComponent(LMSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        LeaveManagementPanelLayout.setVerticalGroup(
            LeaveManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeaveManagementPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LMTopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LMScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LMSubPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        MainPanel.addTab("Leave Management", LeaveManagementPanel);

        SettingsPanel.setMaximumSize(new java.awt.Dimension(1000, 630));
        SettingsPanel.setMinimumSize(new java.awt.Dimension(1000, 630));
        SettingsPanel.setPreferredSize(new java.awt.Dimension(1000, 630));

        SLogOutButton.setBackground(new java.awt.Color(204, 0, 0));
        SLogOutButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        SLogOutButton.setForeground(new java.awt.Color(255, 255, 255));
        SLogOutButton.setText("Log Out");
        SLogOutButton.setAlignmentY(0.0F);
        SLogOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SLogOutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SettingsSubPanelLayout = new javax.swing.GroupLayout(SettingsSubPanel);
        SettingsSubPanel.setLayout(SettingsSubPanelLayout);
        SettingsSubPanelLayout.setHorizontalGroup(
            SettingsSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SettingsSubPanelLayout.createSequentialGroup()
                .addContainerGap(804, Short.MAX_VALUE)
                .addComponent(SLogOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        SettingsSubPanelLayout.setVerticalGroup(
            SettingsSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SettingsSubPanelLayout.createSequentialGroup()
                .addContainerGap(513, Short.MAX_VALUE)
                .addComponent(SLogOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout SettingsPanelLayout = new javax.swing.GroupLayout(SettingsPanel);
        SettingsPanel.setLayout(SettingsPanelLayout);
        SettingsPanelLayout.setHorizontalGroup(
            SettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SettingsSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SettingsPanelLayout.setVerticalGroup(
            SettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SettingsSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        MainPanel.addTab("Setttings", SettingsPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PCalculatePayrollButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PCalculatePayrollButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PCalculatePayrollButtonActionPerformed

    private void PLoadDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PLoadDataButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PLoadDataButtonActionPerformed

    private void EMEmployeeSearchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EMEmployeeSearchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EMEmployeeSearchTextFieldActionPerformed

    private void RAExportPDFButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RAExportPDFButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RAExportPDFButtonActionPerformed

    private void RPPrintReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RPPrintReportButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RPPrintReportButtonActionPerformed

    private void ARefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ARefreshButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ARefreshButtonActionPerformed

    private void EIEmployeeIDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EIEmployeeIDTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EIEmployeeIDTextFieldActionPerformed

    private void EMStreetTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EMStreetTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EMStreetTextFieldActionPerformed

    private void EMPagIBIGTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EMPagIBIGTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EMPagIBIGTextFieldActionPerformed

    private void EMSSSTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EMSSSTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EMSSSTextFieldActionPerformed

    private void EMRiceAllowanceTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EMRiceAllowanceTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EMRiceAllowanceTextFieldActionPerformed

    private void EMBirthdayTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EMBirthdayTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EMBirthdayTextFieldActionPerformed

    private void EMFirstNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EMFirstNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EMFirstNameTextFieldActionPerformed

    private void PWithholdingTaxTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PWithholdingTaxTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PWithholdingTaxTextFieldActionPerformed

    private void LMEmployeeNameIDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LMEmployeeNameIDTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LMEmployeeNameIDTextFieldActionPerformed

    private void PGeneratePDFButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PGeneratePDFButtonActionPerformed
        // TODO add your handling code here:
        try {
            Connection conn = DBConnection.getConnection();
            new ReportGenerator(conn).generatePayslipReport();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_PGeneratePDFButtonActionPerformed

    private void RPExportPDFButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RPExportPDFButtonActionPerformed
        // TODO add your handling code here:
        try {
            Connection conn = DBConnection.getConnection();
            new ReportGenerator(conn).generatePayrollReport();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_RPExportPDFButtonActionPerformed

    private void EIApplyForLeaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EIApplyForLeaveButtonActionPerformed
        // TODO add your handling code here:
        LeaveDialog leave = new LeaveDialog(this,true); 
        leave.setLocationRelativeTo(this);
        leave.setVisible(true);
    }//GEN-LAST:event_EIApplyForLeaveButtonActionPerformed

    private void LMApplyFiltersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LMApplyFiltersButtonActionPerformed
        // TODO add your handling code here:
         System.out.println("DEBUG: filterLeaveRequests() called.");
        LMApplyFiltersButton.addActionListener(e -> filterLeaveRequests());
    }//GEN-LAST:event_LMApplyFiltersButtonActionPerformed

    private void LMClearFiltersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LMClearFiltersButtonActionPerformed
        // TODO add your handling code here:
        
        LMClearFiltersButton.addActionListener(e -> {
        // Clear all filter fields
        LMEmployeeNameIDTextField.setText("");
        LMLeaveComboBox.setSelectedIndex(0); 
        LMStatusComboBox.setSelectedIndex(0);
        LMStartDateChooser.setDate(null);
        LMEndDateChooser.setDate(null);
        loadAllLeaveRequests(); 
        });
    }//GEN-LAST:event_LMClearFiltersButtonActionPerformed

    private void LMRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LMRefreshButtonActionPerformed
        // TODO add your handling code here:
        
        LMRefreshButton.addActionListener(e -> {
    
            LMEmployeeNameIDTextField.setText("");
            LMLeaveComboBox.setSelectedIndex(0);
            LMStatusComboBox.setSelectedIndex(0);
            LMStartDateChooser.setDate(null);
            LMEndDateChooser.setDate(null);
            loadAllLeaveRequests();
        });
    }//GEN-LAST:event_LMRefreshButtonActionPerformed

    private void LMApproveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LMApproveButtonActionPerformed
        // TODO add your handling code here:
        
        LMApproveButton.addActionListener(e -> {
        int selectedRow = LMTable.getSelectedRow();
        if (selectedRow != -1) {
            
        // Get the Leave Request ID from the first column of the selected rowww
            int requestId = (int) leaveTableModel.getValueAt(selectedRow, 0); 
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to approve leave request ID: " + requestId + "?",
                "Confirm Approval",
                JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
             try {
                leaveRequestService.updateLeaveStatus(requestId, "Approved");
                JOptionPane.showMessageDialog(this, "Leave request approved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                filterLeaveRequests(); // Refresh table to show updated status
                } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error during approval: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
             } catch (IllegalArgumentException ex) {
                 JOptionPane.showMessageDialog(this, "Approval failed: " + ex.getMessage(), "Validation Error", JOptionPane.WARNING_MESSAGE);
                }
         }
         } else {
        JOptionPane.showMessageDialog(this, "Please select a leave request to approve.", "No Selection", JOptionPane.WARNING_MESSAGE);
    }
        });
        
    }//GEN-LAST:event_LMApproveButtonActionPerformed

    private void LMDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LMDeleteButtonActionPerformed
        // TODO add your handling code here:
        
            LMDeleteButton.addActionListener(e -> {
            int selectedRow = LMTable.getSelectedRow();
            if (selectedRow != -1) {
                 int requestId = (int) leaveTableModel.getValueAt(selectedRow, 0); // Assuming ID is column 0
                 int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete leave request ID: " + requestId + "? This action cannot be undone.",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);

             if (confirm == JOptionPane.YES_OPTION) {
            try {
                leaveRequestService.deleteLeaveRequest(requestId);
                JOptionPane.showMessageDialog(this, "Leave request deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                filterLeaveRequests(); // Refresh table to show changes
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error during deletion: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
             }
            } else {
         JOptionPane.showMessageDialog(this, "Please select a leave request to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
            }
        });
    }//GEN-LAST:event_LMDeleteButtonActionPerformed
                                                                                                 
       

    public void setupTabs() {
    // Add only the tabs the user has access to
    System.out.println("Session: " + session);
    if (session.hasAccess("employeeInfo", "view")) {
        MainPanel.addTab("Employee Information", EmployeeInfoPanel); 
    }
    if (session.hasAccess("employee", "view")) {
        MainPanel.addTab("Employee Management", EmployeeManagementPanel);
    }
    if (session.hasAccess("attendance", "view")) {
        MainPanel.addTab("Attendance", AttendancePanel);
    }
    if (session.hasAccess("attendance", "view")) {
        MainPanel.addTab("Leave Management", LeaveManagementPanel);
    }
    if (session.hasAccess("payroll", "view")) {
        MainPanel.addTab("Payroll", PayrollPanel);
    }
    if (session.hasAccess("reports", "view")) {
        MainPanel.addTab("Reports", ReportsPanel);
    }
    if (session.hasAccess("employeeInfo", "view")) {
        MainPanel.addTab("Settings", SettingsPanel); 
    }
    
    //Debugging
    System.out.println("Access to employeeInfo:view → " + session.hasAccess("employeeInfo", "view"));
    System.out.println("Access to employee:view → " + session.hasAccess("employee", "view"));
    System.out.println("Access to payroll:view → " + session.hasAccess("payroll", "view"));
    System.out.println("Access to attendance:view → " + session.hasAccess("attendance", "view"));
    System.out.println("Access to leave:view → " + session.hasAccess("leave", "view"));
    System.out.println("Access to reports:view → " + session.hasAccess("reports", "view"));
    System.out.println("Access to settings:view → " + session.hasAccess("settings", "view"));
    
    
    
    
    // Refresh UI
    MainPanel.revalidate();
    MainPanel.repaint();
}
     
    public void loadEmployeeInformation(){
        try {
             EmployeeProfile profile = employeeService.getEmployeeProfile(loggedInEmployeeId);
             EmployeeEntity emp = employeeService.getEmployeeById(loggedInEmployeeId);
             Salary salary = salaryService.getSalaryById(emp.getSalaryId());
             GovernmentIDsService govIdService = new GovernmentIDsService(new GovernmentIdDAOImpl());
             GovernmentID gov = govIdService.getGovernmentIdByEmployeeId(loggedInEmployeeId);

            if (profile == null) {
                JOptionPane.showMessageDialog(this,
                    "Could not load your profile.",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Header
            EIEmployeeIDTextField.setText(String.valueOf(loggedInEmployeeId));
            EIDepartmentTextField.setText(profile.getDepartmentName());
            
            //Personal Details
            EIFullNameTextField.setText(profile.getFullName());           
            EIAddressTextField.setText(profile.getFullAddress());
            EIPhoneTextField.setText(profile.getPhoneNumber());
            EIBirthdayTextField.setText(profile.getBirthday().toString());
            
            //Employee Postion
            EIPositionTextField.setText(profile.getPositionName());
            EIStatusTextField.setText(profile.getStatusName());
            EISupervisorTextField.setText(profile.getSupervisorName());
            EIBasicSalaryTextField.setText(String.valueOf(salary.getBasicSalary()));
            
            //Government IDs
            EISSSTextField.setText(gov.getSssId());
            EIPagIBIGTextField.setText(gov.getPagibigId());
            EIPhilHealthTextField.setText(gov.getPhilhealthId());
            EITINTextField.setText(gov.getTinId());
            
            
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(this,
                "Error loading employee info:\n" + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
     
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainAppFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainAppFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainAppFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainAppFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new MainAppFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AAddTimeButton;
    private javax.swing.JPanel AButtonPanel;
    private javax.swing.JButton ADeleteTimeButton;
    private javax.swing.JButton AEditTimeButton;
    private com.toedter.calendar.JDateChooser AEndDateChooser;
    private javax.swing.JLabel AEndDateLabel;
    private javax.swing.JButton ALoadDataButton;
    private javax.swing.JButton ARecordOvertimeButton;
    private javax.swing.JButton ARefreshButton;
    private javax.swing.JScrollPane AScrollPane;
    private javax.swing.JButton ASearchButton;
    private javax.swing.JLabel ASearchLabel;
    private javax.swing.JTextField ASearchTextField;
    private com.toedter.calendar.JDateChooser AStartDateChooser;
    private javax.swing.JLabel AStartDateLabel;
    private javax.swing.JTable ATable;
    private javax.swing.JPanel AttendancePanel;
    private javax.swing.JPanel AttendanceSubPanel;
    private javax.swing.JLabel EIAddressLabel;
    private javax.swing.JTextField EIAddressTextField;
    private javax.swing.JButton EIApplyForLeaveButton;
    private javax.swing.JLabel EIBasicSalaryLabel;
    private javax.swing.JTextField EIBasicSalaryTextField;
    private javax.swing.JLabel EIBirthdayLabel;
    private javax.swing.JTextField EIBirthdayTextField;
    private javax.swing.JLabel EIDepartmentLabel;
    private javax.swing.JTextField EIDepartmentTextField;
    private javax.swing.JPanel EIDetailsIDsPanel;
    private javax.swing.JLabel EIEILabel;
    private javax.swing.JLabel EIEmployeeIDLabel;
    private javax.swing.JTextField EIEmployeeIDTextField;
    private javax.swing.JPanel EIEmployeeInfoPanel;
    private javax.swing.JLabel EIFullNameLabel;
    private javax.swing.JTextField EIFullNameTextField;
    private javax.swing.JLabel EIIDLabel;
    private javax.swing.JLabel EIPagIBIGLabel;
    private javax.swing.JTextField EIPagIBIGTextField;
    private javax.swing.JLabel EIPersonalLabel;
    private javax.swing.JLabel EIPhilHealthLabel;
    private javax.swing.JTextField EIPhilHealthTextField;
    private javax.swing.JLabel EIPhoneLabel;
    private javax.swing.JTextField EIPhoneTextField;
    private javax.swing.JLabel EIPositionLabel;
    private javax.swing.JTextField EIPositionTextField;
    private javax.swing.JLabel EISSSLabel;
    private javax.swing.JTextField EISSSTextField;
    private javax.swing.JLabel EIStatusLabel;
    private javax.swing.JTextField EIStatusTextField;
    private javax.swing.JLabel EISupervisorLabel;
    private javax.swing.JTextField EISupervisorTextField;
    private javax.swing.JLabel EITINLabel;
    private javax.swing.JTextField EITINTextField;
    private javax.swing.JLabel EIWelcomeLabel;
    private javax.swing.JLabel EIWelcomeNameLabel;
    private javax.swing.JPanel EIWelcomePanel;
    private javax.swing.JButton EMAddButton;
    private javax.swing.JLabel EMAddressIDLabel;
    private javax.swing.JTextField EMAddressTextField;
    private javax.swing.JLabel EMBasicSalaryLabel;
    private javax.swing.JTextField EMBasicSalaryTextField;
    private javax.swing.JLabel EMBirthdayLabel;
    private javax.swing.JTextField EMBirthdayTextField;
    private javax.swing.JLabel EMBuildingLabel;
    private javax.swing.JTextField EMBuildingTextField;
    private javax.swing.JPanel EMButtonPanel;
    private javax.swing.JLabel EMCityLabel;
    private javax.swing.JTextField EMCityTextField;
    private javax.swing.JButton EMClearFormButton;
    private javax.swing.JLabel EMClothingAllowanceLabel;
    private javax.swing.JTextField EMClothingAllowanceTextField;
    private javax.swing.JButton EMDeleteButton;
    private javax.swing.JLabel EMDepartmentLabel;
    private javax.swing.JTextField EMDepartmentTextField;
    private javax.swing.JLabel EMDetailLabel;
    private javax.swing.JPanel EMDetailPanel;
    private javax.swing.JButton EMEditButton;
    private javax.swing.JLabel EMEmployeeIDLabel;
    private javax.swing.JTextField EMEmployeeIDTextField;
    private javax.swing.JButton EMEmployeeSearchButton;
    private javax.swing.JTextField EMEmployeeSearchTextField;
    private javax.swing.JLabel EMFirstNameLabel;
    private javax.swing.JTextField EMFirstNameTextField;
    private javax.swing.JLabel EMLastNameLabel;
    private javax.swing.JTextField EMLastNameTextField;
    private javax.swing.JLabel EMMiddleNameLabel;
    private javax.swing.JTextField EMMiddleNameTextField;
    private javax.swing.JLabel EMPagIBIGLabel;
    private javax.swing.JTextField EMPagIBIGTextField;
    private javax.swing.JLabel EMPhilHealthLabel;
    private javax.swing.JTextField EMPhilHealthTextField;
    private javax.swing.JLabel EMPhoneAllowanceLabel;
    private javax.swing.JTextField EMPhoneAllowanceTextField;
    private javax.swing.JLabel EMPhoneLabel;
    private javax.swing.JTextField EMPhoneTextField;
    private javax.swing.JLabel EMPositionLabel;
    private javax.swing.JTextField EMPositionTextField;
    private javax.swing.JLabel EMProvinceLabel;
    private javax.swing.JTextField EMProvinceTextField;
    private javax.swing.JLabel EMRiceAllowanceLabel;
    private javax.swing.JTextField EMRiceAllowanceTextField;
    private javax.swing.JLabel EMSSSLabel;
    private javax.swing.JTextField EMSSSTextField;
    private javax.swing.JButton EMSaveButton;
    private javax.swing.JScrollPane EMScrollPane;
    private javax.swing.JLabel EMSearchLabel;
    private javax.swing.JPanel EMSearchPanel;
    private javax.swing.JLabel EMStatusLabel;
    private javax.swing.JTextField EMStatusTextField;
    private javax.swing.JLabel EMStreetLabel;
    private javax.swing.JTextField EMStreetTextField;
    private javax.swing.JLabel EMTINLabel;
    private javax.swing.JTextField EMTINTextField;
    private javax.swing.JTable EMTable;
    private javax.swing.JLabel EMZIPLabel;
    private javax.swing.JTextField EMZIPTextField;
    private javax.swing.JPanel EmployeeInfoPanel;
    private javax.swing.JPanel EmployeeManagementPanel;
    private javax.swing.JButton LMApplyFiltersButton;
    private javax.swing.JButton LMApproveButton;
    private javax.swing.JButton LMClearFiltersButton;
    private javax.swing.JButton LMDeleteButton;
    private javax.swing.JLabel LMEmployeeNameIDLabel;
    private javax.swing.JTextField LMEmployeeNameIDTextField;
    private com.toedter.calendar.JDateChooser LMEndDateChooser;
    private javax.swing.JLabel LMEndDateLabel;
    private javax.swing.JLabel LMFilterLabel;
    private javax.swing.JComboBox<String> LMLeaveComboBox;
    private javax.swing.JLabel LMLeaveTypeLabel;
    private javax.swing.JButton LMRefreshButton;
    private javax.swing.JScrollPane LMScrollPane;
    private com.toedter.calendar.JDateChooser LMStartDateChooser;
    private javax.swing.JLabel LMStartDateLabel;
    private javax.swing.JComboBox<String> LMStatusComboBox;
    private javax.swing.JLabel LMStatusLabel;
    private javax.swing.JPanel LMSubPanel;
    private javax.swing.JTable LMTable;
    private javax.swing.JPanel LMTopPanel;
    private javax.swing.JPanel LeaveManagementPanel;
    private javax.swing.JTabbedPane MainPanel;
    private javax.swing.JLabel PBIRLabel;
    private javax.swing.JTextField PBasicSalaryTextField;
    private javax.swing.JButton PCalculatePayrollButton;
    private javax.swing.JTextField PClothingAllowanceTextField;
    private javax.swing.JLabel PDepartmentLabel;
    private javax.swing.JLabel PEmployeeNoLabel;
    private javax.swing.JLabel PEndPayrollPeriod;
    private javax.swing.JLabel PFullNameLabel;
    private javax.swing.JButton PGeneratePDFButton;
    private javax.swing.JLabel PGrossIncomeLabel;
    private javax.swing.JTextField PGrossPayTextField;
    private javax.swing.JButton PLoadDataButton;
    private javax.swing.JLabel PNetPayLabel;
    private javax.swing.JTextField PNetPayTextField;
    private javax.swing.JTextField POvertimePayTextField;
    private javax.swing.JLabel PPHLabel;
    private javax.swing.JLabel PPagIBIGContributionLabel;
    private javax.swing.JTextField PPagIBIGContributionTextField;
    private javax.swing.JLabel PPagIBIGLabel;
    private javax.swing.JLabel PPagIBIGNoLabel;
    private javax.swing.JTextField PPagIBIGNoTextField;
    private javax.swing.JLabel PPayPeriodLabel;
    private javax.swing.JLabel PPayrollInfoLabel;
    private javax.swing.JLabel PPayrollPeriodLabel;
    private javax.swing.JLabel PPhilHealthContributionLabel;
    private javax.swing.JTextField PPhilHealthContributionTextField;
    private javax.swing.JLabel PPhilHealthNoLabel;
    private javax.swing.JTextField PPhilHealthNoTextField;
    private javax.swing.JTextField PPhoneAllowanceTextField;
    private javax.swing.JLabel PPositionLabel;
    private javax.swing.JTextField PRiceAllowanceTextField;
    private javax.swing.JLabel PSSSContributionLabel;
    private javax.swing.JTextField PSSSContributionTextField;
    private javax.swing.JLabel PSSSLabel;
    private javax.swing.JLabel PSSSNoLabel;
    private javax.swing.JTextField PSSSNoTextField;
    private javax.swing.JButton PSaveDataButton;
    private javax.swing.JButton PSearchButton;
    private javax.swing.JLabel PSearchEmployeeLabel;
    private javax.swing.JTextField PSearchTextField;
    private com.toedter.calendar.JDateChooser PStartPayrollPeriodDateChooser;
    private javax.swing.JLabel PTINLabel;
    private javax.swing.JTextField PTINTextField;
    private javax.swing.JLabel PWithholdingTaxLabel;
    private javax.swing.JTextField PWithholdingTaxTextField;
    private javax.swing.JPanel PayrollButtonsPanel;
    private javax.swing.JPanel PayrollDeductionsPanel;
    private javax.swing.JPanel PayrollEarningsPanel;
    private javax.swing.JPanel PayrollPanel;
    private javax.swing.JPanel PayrollTopPanel;
    private javax.swing.JLabel RAEmployeeLabel;
    private com.toedter.calendar.JDateChooser RAEndDateChooser;
    private javax.swing.JLabel RAEndDateLabel;
    private javax.swing.JButton RAExportPDFButton;
    private javax.swing.JButton RAGenerateReportButton;
    private javax.swing.JButton RAPrintReportButton;
    private javax.swing.JButton RASearchButton;
    private com.toedter.calendar.JDateChooser RAStartDateChooser;
    private javax.swing.JLabel RAStartDateLabel;
    private javax.swing.JPanel RAttendanceBottomPanel;
    private javax.swing.JPanel RAttendanceReportsTab;
    private javax.swing.JScrollPane RAttendanceScrollPane;
    private javax.swing.JTable RAttendanceTable;
    private javax.swing.JPanel RAttendanceTopPanel;
    private javax.swing.JTextField REmployeeSearchTextField;
    private javax.swing.JComboBox<String> RPDepartmentComboBox;
    private javax.swing.JLabel RPDepartmentLabel;
    private javax.swing.JButton RPExportPDFButton;
    private javax.swing.JButton RPGenerateReportButton;
    private javax.swing.JButton RPPrintReportButton;
    private javax.swing.JPanel RPayrollBottomPanel;
    private com.toedter.calendar.JDateChooser RPayrollDateChooser;
    private javax.swing.JLabel RPayrollPeriodLabel;
    private javax.swing.JPanel RPayrollReportsTab;
    private javax.swing.JScrollPane RPayrollScrollPane;
    private javax.swing.JTable RPayrollTable;
    private javax.swing.JPanel RPayrollTopPanel;
    private javax.swing.JPanel ReportsPanel;
    private javax.swing.JPanel ReportsSubPanel;
    private javax.swing.JTabbedPane ReportsTabbedPane;
    private javax.swing.JButton SLogOutButton;
    private javax.swing.JPanel SettingsPanel;
    private javax.swing.JPanel SettingsSubPanel;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
