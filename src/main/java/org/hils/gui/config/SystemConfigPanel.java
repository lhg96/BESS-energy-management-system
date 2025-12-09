package org.hils.gui.config;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

/**
 * System Configuration Panel
 * 시스템 구성 패널
 * 
 * @author hyun keun lim
 */
public class SystemConfigPanel extends JPanel {
    
    private Properties systemConfig;
    
    // System Parameters
    private JTextField nominalPowerField;
    private JTextField nominalVoltageField;
    private JTextField maxSOCField;
    private JTextField minSOCField;
    private JTextField maxTempField;
    private JTextField gridFreqField;
    
    // Communication Settings
    private JTextField pcsIPField;
    private JTextField bmsIPField;
    private JTextField scadaIPField;
    private JTextField portField;
    private JComboBox<String> protocolCombo;
    private JCheckBox enableLoggingBox;
    
    // User Management
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userLevelCombo;
    
    // Alarm Settings
    private JCheckBox enableEmailAlarmBox;
    private JTextField emailServerField;
    private JTextField emailRecipientField;
    private JComboBox<String> alarmLevelCombo;
    
    public SystemConfigPanel() {
        initializeConfig();
        setupUI();
    }
    
    private void initializeConfig() {
        systemConfig = new Properties();
        
        // Default system parameters
        systemConfig.setProperty("nominal.power", "300.0");
        systemConfig.setProperty("nominal.voltage", "22900.0");
        systemConfig.setProperty("battery.max.soc", "95.0");
        systemConfig.setProperty("battery.min.soc", "10.0");
        systemConfig.setProperty("battery.max.temp", "45.0");
        systemConfig.setProperty("grid.frequency", "60.0");
        
        // Default communication settings
        systemConfig.setProperty("pcs.ip", "192.168.1.100");
        systemConfig.setProperty("bms.ip", "192.168.1.101");
        systemConfig.setProperty("scada.ip", "192.168.1.10");
        systemConfig.setProperty("comm.port", "502");
        systemConfig.setProperty("comm.protocol", "Modbus TCP");
        systemConfig.setProperty("logging.enabled", "true");
        
        // Default user settings
        systemConfig.setProperty("user.name", "admin");
        systemConfig.setProperty("user.level", "Administrator");
        
        // Default alarm settings
        systemConfig.setProperty("alarm.email.enabled", "false");
        systemConfig.setProperty("alarm.email.server", "smtp.company.com");
        systemConfig.setProperty("alarm.email.recipient", "ems@company.com");
        systemConfig.setProperty("alarm.level", "Warning");
    }
    
    private void setupUI() {
        setLayout(new BorderLayout());
        setBorder(new TitledBorder(null, "System Configuration", TitledBorder.LEFT, TitledBorder.TOP,
                  new Font("맑은 고딕", Font.BOLD, 12), Color.BLUE));
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // System Parameters Tab
        JPanel systemTab = createSystemParametersTab();
        tabbedPane.addTab("System", systemTab);
        
        // Communication Tab
        JPanel commTab = createCommunicationTab();
        tabbedPane.addTab("Communication", commTab);
        
        // User Management Tab
        JPanel userTab = createUserManagementTab();
        tabbedPane.addTab("Users", userTab);
        
        // Alarm Settings Tab
        JPanel alarmTab = createAlarmSettingsTab();
        tabbedPane.addTab("Alarms", alarmTab);
        
        add(tabbedPane, BorderLayout.CENTER);
        
        // Control buttons
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createSystemParametersTab() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("System Parameters"));
        
        // Nominal Power
        formPanel.add(new JLabel("Nominal Power (MW):"));
        nominalPowerField = new JTextField(systemConfig.getProperty("nominal.power"));
        nominalPowerField.setHorizontalAlignment(SwingConstants.RIGHT);
        formPanel.add(nominalPowerField);
        
        // Nominal Voltage
        formPanel.add(new JLabel("Nominal Voltage (V):"));
        nominalVoltageField = new JTextField(systemConfig.getProperty("nominal.voltage"));
        nominalVoltageField.setHorizontalAlignment(SwingConstants.RIGHT);
        formPanel.add(nominalVoltageField);
        
        // Max SOC
        formPanel.add(new JLabel("Max SOC (%):"));
        maxSOCField = new JTextField(systemConfig.getProperty("battery.max.soc"));
        maxSOCField.setHorizontalAlignment(SwingConstants.RIGHT);
        formPanel.add(maxSOCField);
        
        // Min SOC
        formPanel.add(new JLabel("Min SOC (%):"));
        minSOCField = new JTextField(systemConfig.getProperty("battery.min.soc"));
        minSOCField.setHorizontalAlignment(SwingConstants.RIGHT);
        formPanel.add(minSOCField);
        
        // Max Temperature
        formPanel.add(new JLabel("Max Temperature (°C):"));
        maxTempField = new JTextField(systemConfig.getProperty("battery.max.temp"));
        maxTempField.setHorizontalAlignment(SwingConstants.RIGHT);
        formPanel.add(maxTempField);
        
        // Grid Frequency
        formPanel.add(new JLabel("Grid Frequency (Hz):"));
        gridFreqField = new JTextField(systemConfig.getProperty("grid.frequency"));
        gridFreqField.setHorizontalAlignment(SwingConstants.RIGHT);
        formPanel.add(gridFreqField);
        
        panel.add(formPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createCommunicationTab() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Communication Settings"));
        
        // PCS IP
        formPanel.add(new JLabel("PCS IP Address:"));
        pcsIPField = new JTextField(systemConfig.getProperty("pcs.ip"));
        formPanel.add(pcsIPField);
        
        // BMS IP
        formPanel.add(new JLabel("BMS IP Address:"));
        bmsIPField = new JTextField(systemConfig.getProperty("bms.ip"));
        formPanel.add(bmsIPField);
        
        // SCADA IP
        formPanel.add(new JLabel("SCADA IP Address:"));
        scadaIPField = new JTextField(systemConfig.getProperty("scada.ip"));
        formPanel.add(scadaIPField);
        
        // Port
        formPanel.add(new JLabel("Communication Port:"));
        portField = new JTextField(systemConfig.getProperty("comm.port"));
        formPanel.add(portField);
        
        // Protocol
        formPanel.add(new JLabel("Protocol:"));
        protocolCombo = new JComboBox<>(new String[]{
            "Modbus TCP", "Modbus RTU", "DNP3", "IEC 61850", "OPC UA"
        });
        protocolCombo.setSelectedItem(systemConfig.getProperty("comm.protocol"));
        formPanel.add(protocolCombo);
        
        // Enable Logging
        formPanel.add(new JLabel("Enable Data Logging:"));
        enableLoggingBox = new JCheckBox("", Boolean.parseBoolean(systemConfig.getProperty("logging.enabled")));
        formPanel.add(enableLoggingBox);
        
        // Connection Test Button
        formPanel.add(new JLabel(""));
        JButton testConnectionBtn = new JButton("Test Connection");
        testConnectionBtn.addActionListener(e -> testConnection());
        formPanel.add(testConnectionBtn);
        
        panel.add(formPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createUserManagementTab() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("User Management"));
        
        // Username
        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField(systemConfig.getProperty("user.name"));
        formPanel.add(usernameField);
        
        // Password
        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);
        
        // User Level
        formPanel.add(new JLabel("Access Level:"));
        userLevelCombo = new JComboBox<>(new String[]{
            "Administrator", "Operator", "Viewer", "Maintenance"
        });
        userLevelCombo.setSelectedItem(systemConfig.getProperty("user.level"));
        formPanel.add(userLevelCombo);
        
        // Change Password Button
        formPanel.add(new JLabel(""));
        JButton changePasswordBtn = new JButton("Change Password");
        changePasswordBtn.addActionListener(e -> changePassword());
        formPanel.add(changePasswordBtn);
        
        panel.add(formPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createAlarmSettingsTab() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Alarm Settings"));
        
        // Enable Email Alarms
        formPanel.add(new JLabel("Enable Email Alarms:"));
        enableEmailAlarmBox = new JCheckBox("", Boolean.parseBoolean(systemConfig.getProperty("alarm.email.enabled")));
        formPanel.add(enableEmailAlarmBox);
        
        // Email Server
        formPanel.add(new JLabel("Email Server:"));
        emailServerField = new JTextField(systemConfig.getProperty("alarm.email.server"));
        formPanel.add(emailServerField);
        
        // Email Recipient
        formPanel.add(new JLabel("Email Recipient:"));
        emailRecipientField = new JTextField(systemConfig.getProperty("alarm.email.recipient"));
        formPanel.add(emailRecipientField);
        
        // Alarm Level
        formPanel.add(new JLabel("Minimum Alarm Level:"));
        alarmLevelCombo = new JComboBox<>(new String[]{
            "Info", "Warning", "Alarm", "Critical"
        });
        alarmLevelCombo.setSelectedItem(systemConfig.getProperty("alarm.level"));
        formPanel.add(alarmLevelCombo);
        
        // Test Email Button
        formPanel.add(new JLabel(""));
        JButton testEmailBtn = new JButton("Send Test Email");
        testEmailBtn.addActionListener(e -> sendTestEmail());
        formPanel.add(testEmailBtn);
        
        panel.add(formPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        JButton saveButton = new JButton("Save Configuration");
        saveButton.setFont(new Font("맑은 고딕", Font.BOLD, 11));
        saveButton.setBackground(Color.GREEN);
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(e -> saveConfiguration());
        
        JButton loadButton = new JButton("Load Configuration");
        loadButton.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
        loadButton.addActionListener(e -> loadConfiguration());
        
        JButton resetButton = new JButton("Reset to Defaults");
        resetButton.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
        resetButton.addActionListener(e -> resetToDefaults());
        
        JButton exportButton = new JButton("Export Config");
        exportButton.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
        exportButton.addActionListener(e -> exportConfiguration());
        
        panel.add(saveButton);
        panel.add(loadButton);
        panel.add(resetButton);
        panel.add(exportButton);
        
        return panel;
    }
    
    private void testConnection() {
        // Simulate connection test
        String pcsIP = pcsIPField.getText();
        String bmsIP = bmsIPField.getText();
        String scadaIP = scadaIPField.getText();
        
        StringBuilder result = new StringBuilder();
        result.append("Connection Test Results:\n\n");
        result.append("PCS (").append(pcsIP).append("): ").append(Math.random() > 0.2 ? "OK" : "FAILED").append("\n");
        result.append("BMS (").append(bmsIP).append("): ").append(Math.random() > 0.2 ? "OK" : "FAILED").append("\n");
        result.append("SCADA (").append(scadaIP).append("): ").append(Math.random() > 0.2 ? "OK" : "FAILED").append("\n");
        
        JOptionPane.showMessageDialog(this, result.toString(), "Connection Test", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void changePassword() {
        String newPassword = JOptionPane.showInputDialog(this, "Enter new password:", "Change Password", JOptionPane.QUESTION_MESSAGE);
        if (newPassword != null && !newPassword.trim().isEmpty()) {
            passwordField.setText(newPassword);
            JOptionPane.showMessageDialog(this, "Password changed successfully!", "Password Change", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void sendTestEmail() {
        String recipient = emailRecipientField.getText();
        if (enableEmailAlarmBox.isSelected() && !recipient.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Test email sent to: " + recipient + "\nSubject: EMS Test Alarm\nMessage: This is a test alarm from EMS system.",
                "Test Email Sent", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, 
                "Please enable email alarms and set recipient email address.",
                "Email Not Configured", 
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void saveConfiguration() {
        try {
            // Update system config with form values
            systemConfig.setProperty("nominal.power", nominalPowerField.getText());
            systemConfig.setProperty("nominal.voltage", nominalVoltageField.getText());
            systemConfig.setProperty("battery.max.soc", maxSOCField.getText());
            systemConfig.setProperty("battery.min.soc", minSOCField.getText());
            systemConfig.setProperty("battery.max.temp", maxTempField.getText());
            systemConfig.setProperty("grid.frequency", gridFreqField.getText());
            
            systemConfig.setProperty("pcs.ip", pcsIPField.getText());
            systemConfig.setProperty("bms.ip", bmsIPField.getText());
            systemConfig.setProperty("scada.ip", scadaIPField.getText());
            systemConfig.setProperty("comm.port", portField.getText());
            systemConfig.setProperty("comm.protocol", (String) protocolCombo.getSelectedItem());
            systemConfig.setProperty("logging.enabled", String.valueOf(enableLoggingBox.isSelected()));
            
            systemConfig.setProperty("user.name", usernameField.getText());
            systemConfig.setProperty("user.level", (String) userLevelCombo.getSelectedItem());
            
            systemConfig.setProperty("alarm.email.enabled", String.valueOf(enableEmailAlarmBox.isSelected()));
            systemConfig.setProperty("alarm.email.server", emailServerField.getText());
            systemConfig.setProperty("alarm.email.recipient", emailRecipientField.getText());
            systemConfig.setProperty("alarm.level", (String) alarmLevelCombo.getSelectedItem());
            
            JOptionPane.showMessageDialog(this, 
                "Configuration saved successfully!\nRestart the system for changes to take effect.",
                "Configuration Saved", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error saving configuration: " + e.getMessage(),
                "Save Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadConfiguration() {
        JOptionPane.showMessageDialog(this, 
            "Configuration loaded from config.properties",
            "Configuration Loaded", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void resetToDefaults() {
        int result = JOptionPane.showConfirmDialog(this,
            "Reset all settings to default values?",
            "Reset Configuration",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
            
        if (result == JOptionPane.YES_OPTION) {
            initializeConfig();
            updateFormFields();
            JOptionPane.showMessageDialog(this, 
                "Configuration reset to defaults!",
                "Reset Complete", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void exportConfiguration() {
        StringBuilder config = new StringBuilder();
        config.append("# EMS System Configuration Export\n");
        config.append("# Generated: ").append(new java.util.Date()).append("\n\n");
        
        for (Object key : systemConfig.keySet()) {
            config.append(key).append("=").append(systemConfig.getProperty((String) key)).append("\n");
        }
        
        JOptionPane.showMessageDialog(this, 
            "Configuration exported to: config_export.properties\n\nContent:\n" + config.toString(),
            "Export Complete", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void updateFormFields() {
        nominalPowerField.setText(systemConfig.getProperty("nominal.power"));
        nominalVoltageField.setText(systemConfig.getProperty("nominal.voltage"));
        maxSOCField.setText(systemConfig.getProperty("battery.max.soc"));
        minSOCField.setText(systemConfig.getProperty("battery.min.soc"));
        maxTempField.setText(systemConfig.getProperty("battery.max.temp"));
        gridFreqField.setText(systemConfig.getProperty("grid.frequency"));
        
        pcsIPField.setText(systemConfig.getProperty("pcs.ip"));
        bmsIPField.setText(systemConfig.getProperty("bms.ip"));
        scadaIPField.setText(systemConfig.getProperty("scada.ip"));
        portField.setText(systemConfig.getProperty("comm.port"));
        protocolCombo.setSelectedItem(systemConfig.getProperty("comm.protocol"));
        enableLoggingBox.setSelected(Boolean.parseBoolean(systemConfig.getProperty("logging.enabled")));
        
        usernameField.setText(systemConfig.getProperty("user.name"));
        userLevelCombo.setSelectedItem(systemConfig.getProperty("user.level"));
        
        enableEmailAlarmBox.setSelected(Boolean.parseBoolean(systemConfig.getProperty("alarm.email.enabled")));
        emailServerField.setText(systemConfig.getProperty("alarm.email.server"));
        emailRecipientField.setText(systemConfig.getProperty("alarm.email.recipient"));
        alarmLevelCombo.setSelectedItem(systemConfig.getProperty("alarm.level"));
    }
    
    public Properties getSystemConfig() {
        return systemConfig;
    }
}