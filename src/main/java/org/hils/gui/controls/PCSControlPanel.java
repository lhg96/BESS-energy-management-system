package org.hils.gui.controls;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JFrame;

/**
 * Enhanced PCS Control Panel
 * Enhanced PCS Control Panel for real-time power conversion system management
 * 
 * @author hyun keun lim
 */
public class PCSControlPanel extends JPanel {
    
    // PCS Control Data
    private List<PCSControlData> pcsUnits;
    private Timer updateTimer;
    private DecimalFormat df = new DecimalFormat("#.##");
    
    // UI Components
    private JLabel systemStatusLabel;
    private JLabel totalPowerLabel;
    private JLabel gridFrequencyLabel;
    private JProgressBar systemLoadBar;
    private JComboBox<String> operationModeCombo;
    private JTextField setPowerField;
    private JSlider powerSlider;
    private JCheckBox autoModeCheckBox;
    private JButton startButton;
    private JButton stopButton;
    private JButton emergencyStopButton;
    private JButton resetButton;
    
    // PCS Control Data Class
    public static class PCSControlData {
        private int pcsId;
        private boolean isOnline;
        private boolean isRunning;
        private String operationMode; // STANDBY, CHARGE, DISCHARGE, GRID_FORMING, GRID_FOLLOWING
        private double setPower; // MW
        private double actualPower; // MW
        private double dcVoltage; // V
        private double acVoltage; // V
        private double current; // A
        private double frequency; // Hz
        private double temperature; // °C
        private boolean hasFault;
        private String faultMessage;
        private double efficiency; // %
        
        public PCSControlData(int pcsId) {
            this.pcsId = pcsId;
            this.isOnline = true;
            this.isRunning = false;
            this.operationMode = "STANDBY";
            this.setPower = 0.0;
            this.actualPower = 0.0;
            this.dcVoltage = 800.0;
            this.acVoltage = 22900.0; // 22.9kV
            this.current = 0.0;
            this.frequency = 60.0;
            this.temperature = 25.0;
            this.hasFault = false;
            this.faultMessage = "";
            this.efficiency = 95.0;
        }
        
        // Getters and setters
        public int getPcsId() { return pcsId; }
        public boolean isOnline() { return isOnline; }
        public void setOnline(boolean online) { this.isOnline = online; }
        public boolean isRunning() { return isRunning; }
        public void setRunning(boolean running) { this.isRunning = running; }
        public String getOperationMode() { return operationMode; }
        public void setOperationMode(String operationMode) { this.operationMode = operationMode; }
        public double getSetPower() { return setPower; }
        public void setSetPower(double setPower) { this.setPower = setPower; }
        public double getActualPower() { return actualPower; }
        public void setActualPower(double actualPower) { this.actualPower = actualPower; }
        public double getDcVoltage() { return dcVoltage; }
        public void setDcVoltage(double dcVoltage) { this.dcVoltage = dcVoltage; }
        public double getAcVoltage() { return acVoltage; }
        public void setAcVoltage(double acVoltage) { this.acVoltage = acVoltage; }
        public double getCurrent() { return current; }
        public void setCurrent(double current) { this.current = current; }
        public double getFrequency() { return frequency; }
        public void setFrequency(double frequency) { this.frequency = frequency; }
        public double getTemperature() { return temperature; }
        public void setTemperature(double temperature) { this.temperature = temperature; }
        public boolean isHasFault() { return hasFault; }
        public void setHasFault(boolean hasFault) { this.hasFault = hasFault; }
        public String getFaultMessage() { return faultMessage; }
        public void setFaultMessage(String faultMessage) { this.faultMessage = faultMessage; }
        public double getEfficiency() { return efficiency; }
        public void setEfficiency(double efficiency) { this.efficiency = efficiency; }
    }
    
    public PCSControlPanel() {
        initializePCSData();
        setupUI();
        startUpdateTimer();
    }
    
    private void initializePCSData() {
        pcsUnits = new ArrayList<>();
        // Initialize 4 PCS units
        for (int i = 1; i <= 4; i++) {
            pcsUnits.add(new PCSControlData(i));
        }
    }
    
    private void setupUI() {
        setLayout(new BorderLayout());
        setBorder(new TitledBorder(null, "PCS Control System", TitledBorder.LEFT, TitledBorder.TOP,
                  new Font("맑은 고딕", Font.BOLD, 12), Color.BLUE));
        
        // Top - System Overview
        JPanel overviewPanel = createOverviewPanel();
        add(overviewPanel, BorderLayout.NORTH);
        
        // Center - Individual PCS Control
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.CENTER);
        
        // Bottom - Operation Controls
        JPanel operationPanel = createOperationPanel();
        add(operationPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createOverviewPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("System Overview"));
        panel.setPreferredSize(new java.awt.Dimension(0, 80));
        
        systemStatusLabel = new JLabel("Status: ONLINE");
        systemStatusLabel.setFont(new Font("맑은 고딕", Font.BOLD, 11));
        systemStatusLabel.setForeground(Color.GREEN);
        
        totalPowerLabel = new JLabel("Total Power: " + df.format(getTotalPower()) + " MW");
        totalPowerLabel.setFont(new Font("맑은 고딕", Font.BOLD, 11));
        
        gridFrequencyLabel = new JLabel("Grid Freq: " + df.format(getAverageFrequency()) + " Hz");
        gridFrequencyLabel.setFont(new Font("맑은 고딕", Font.BOLD, 11));
        
        systemLoadBar = new JProgressBar(0, 100);
        systemLoadBar.setValue((int)(getTotalPower() / 3.0)); // Assuming 300MW max
        systemLoadBar.setStringPainted(true);
        systemLoadBar.setString("Load: " + df.format(getTotalPower() / 3.0) + "%");
        
        panel.add(systemStatusLabel);
        panel.add(totalPowerLabel);
        panel.add(gridFrequencyLabel);
        panel.add(systemLoadBar);
        
        return panel;
    }
    
    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("PCS Units Control"));
        
        // Individual PCS status
        JPanel pcsStatusPanel = new JPanel(new GridLayout(0, 1, 2, 2));

        for (PCSControlData pcs : pcsUnits) {
            JPanel pcsRow = createPCSRowPanel(pcs);
            pcsStatusPanel.add(pcsRow);
        }

        // Make the PCS list scrollable to support many units
        JScrollPane pcsScroll = new JScrollPane(pcsStatusPanel);
        pcsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pcsScroll.setPreferredSize(new java.awt.Dimension(0, 140));

        panel.add(pcsScroll, BorderLayout.CENTER);
        
        // Power control
        JPanel powerControlPanel = createPowerControlPanel();
        panel.add(powerControlPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createPCSRowPanel(PCSControlData pcs) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 2));
        panel.setPreferredSize(new java.awt.Dimension(0, 25));
        
        JLabel idLabel = new JLabel("PCS#" + pcs.getPcsId());
        idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 10));
        idLabel.setPreferredSize(new java.awt.Dimension(40, 20));
        
        JCheckBox onlineBox = new JCheckBox("", pcs.isOnline());
        onlineBox.setEnabled(false);
        onlineBox.setToolTipText("Online Status");
        
        JLabel statusLabel = new JLabel(pcs.getOperationMode());
        statusLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 9));
        statusLabel.setPreferredSize(new java.awt.Dimension(60, 20));
        statusLabel.setForeground(getOperationModeColor(pcs.getOperationMode()));
        
        JLabel powerLabel = new JLabel(df.format(pcs.getActualPower()) + "MW");
        powerLabel.setFont(new Font("맑은 고딕", Font.BOLD, 9));
        powerLabel.setPreferredSize(new java.awt.Dimension(50, 20));
        
        JLabel effLabel = new JLabel(df.format(pcs.getEfficiency()) + "%");
        effLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 9));
        effLabel.setPreferredSize(new java.awt.Dimension(35, 20));
        
        JLabel faultLabel = new JLabel(pcs.isHasFault() ? "FAULT" : "OK");
        faultLabel.setFont(new Font("맑은 고딕", Font.BOLD, 9));
        faultLabel.setForeground(pcs.isHasFault() ? Color.RED : Color.GREEN);
        faultLabel.setPreferredSize(new java.awt.Dimension(35, 20));
        
        // Per-PCS power slider and set button
        JSlider pcsPowerSlider = new JSlider(0, 75, (int)pcs.getSetPower());
        pcsPowerSlider.setPreferredSize(new java.awt.Dimension(120, 20));
        pcsPowerSlider.setMajorTickSpacing(25);
        pcsPowerSlider.setMinorTickSpacing(5);
        pcsPowerSlider.setPaintTicks(false);

        JTextField pcsPowerField = new JTextField(String.valueOf((int)pcs.getSetPower()));
        pcsPowerField.setPreferredSize(new java.awt.Dimension(50, 20));
        pcsPowerField.setHorizontalAlignment(SwingConstants.RIGHT);

        JButton setPcsPowerButton = new JButton("Set");
        setPcsPowerButton.setFont(new Font("맑은 고딕", Font.PLAIN, 9));
        setPcsPowerButton.setPreferredSize(new java.awt.Dimension(50, 20));

        // sync slider -> text field
        pcsPowerSlider.addChangeListener(e -> pcsPowerField.setText(String.valueOf(pcsPowerSlider.getValue())));
        
        // set button action: apply to this PCS
        setPcsPowerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double v = Double.parseDouble(pcsPowerField.getText());
                    pcs.setSetPower(Math.max(0, Math.min(75, v)));
                    javax.swing.JOptionPane.showMessageDialog(panel, "PCS#" + pcs.getPcsId() + " set to " + v + " MW");
                } catch (NumberFormatException ex) {
                    javax.swing.JOptionPane.showMessageDialog(panel, "Invalid input", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(idLabel);
        panel.add(onlineBox);
        panel.add(statusLabel);
        panel.add(powerLabel);
        panel.add(effLabel);
        panel.add(faultLabel);
        panel.add(pcsPowerSlider);
        panel.add(pcsPowerField);
        panel.add(setPcsPowerButton);
        
        return panel;
    }
    
    private JPanel createPowerControlPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 3, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Power Control"));
        panel.setPreferredSize(new java.awt.Dimension(0, 80));
        
        JLabel setPowerLabel = new JLabel("Set Power (MW):");
        setPowerLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
        
        setPowerField = new JTextField("0.0");
        setPowerField.setHorizontalAlignment(SwingConstants.RIGHT);
        setPowerField.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
        
        powerSlider = new JSlider(0, 300, 0);
        powerSlider.setMajorTickSpacing(100);
        powerSlider.setMinorTickSpacing(25);
        powerSlider.setPaintTicks(true);
        powerSlider.setPaintLabels(true);
        powerSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setPowerField.setText(String.valueOf(powerSlider.getValue()));
            }
        });
        
        JLabel modeLabel = new JLabel("Mode:");
        modeLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
        
        operationModeCombo = new JComboBox<>(new String[]{
            "STANDBY", "CHARGE", "DISCHARGE", "GRID_FORMING", "GRID_FOLLOWING"
        });
        operationModeCombo.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
        
        autoModeCheckBox = new JCheckBox("Auto Mode");
        autoModeCheckBox.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
        
        panel.add(setPowerLabel);
        panel.add(setPowerField);
        panel.add(powerSlider);
        panel.add(modeLabel);
        panel.add(operationModeCombo);
        panel.add(autoModeCheckBox);
        
        return panel;
    }
    
    private JPanel createOperationPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel.setPreferredSize(new java.awt.Dimension(0, 40));
        
        startButton = new JButton("START");
        startButton.setFont(new Font("맑은 고딕", Font.BOLD, 10));
        startButton.setBackground(Color.GREEN);
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(e -> startPCSOperation());
        
        stopButton = new JButton("STOP");
        stopButton.setFont(new Font("맑은 고딕", Font.BOLD, 10));
        stopButton.setBackground(Color.ORANGE);
        stopButton.setForeground(Color.WHITE);
        stopButton.addActionListener(e -> stopPCSOperation());
        
        emergencyStopButton = new JButton("E-STOP");
        emergencyStopButton.setFont(new Font("맑은 고딕", Font.BOLD, 10));
        emergencyStopButton.setBackground(Color.RED);
        emergencyStopButton.setForeground(Color.WHITE);
        emergencyStopButton.addActionListener(e -> emergencyStop());
        
        resetButton = new JButton("RESET");
        resetButton.setFont(new Font("맑은 고딕", Font.BOLD, 10));
        resetButton.addActionListener(e -> resetPCSSystem());
        
        JButton diagnosticsButton = new JButton("Diagnostics");
        diagnosticsButton.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
        diagnosticsButton.addActionListener(e -> showDiagnostics());
        
        panel.add(startButton);
        panel.add(stopButton);
        panel.add(emergencyStopButton);
        panel.add(resetButton);
        panel.add(diagnosticsButton);
        
        return panel;
    }
    
    private void startUpdateTimer() {
        updateTimer = new Timer(2000, e -> {
            simulateDataUpdate();
            updateDisplay();
        });
        updateTimer.start();
    }
    
    private void simulateDataUpdate() {
        java.util.Random random = new java.util.Random();
        
        for (PCSControlData pcs : pcsUnits) {
            if (pcs.isRunning()) {
                // Simulate power tracking to set point
                double targetPower = pcs.getSetPower();
                double currentPower = pcs.getActualPower();
                double powerDiff = targetPower - currentPower;
                double newPower = currentPower + (powerDiff * 0.1) + (random.nextDouble() - 0.5) * 0.5;
                pcs.setActualPower(Math.max(0, Math.min(75, newPower))); // Max 75MW per PCS
                
                // Update other parameters
                pcs.setFrequency(59.9 + random.nextDouble() * 0.2);
                pcs.setTemperature(25 + random.nextDouble() * 10);
                pcs.setEfficiency(94 + random.nextDouble() * 3);
            }
        }
    }
    
    private void updateDisplay() {
        // Update overview
        boolean systemOnline = pcsUnits.stream().anyMatch(PCSControlData::isOnline);
        systemStatusLabel.setText("Status: " + (systemOnline ? "ONLINE" : "OFFLINE"));
        systemStatusLabel.setForeground(systemOnline ? Color.GREEN : Color.RED);
        
        totalPowerLabel.setText("Total Power: " + df.format(getTotalPower()) + " MW");
        gridFrequencyLabel.setText("Grid Freq: " + df.format(getAverageFrequency()) + " Hz");
        
        systemLoadBar.setValue((int)(getTotalPower() / 3.0));
        systemLoadBar.setString("Load: " + df.format(getTotalPower() / 3.0) + "%");
        
        repaint();
    }
    
    private void startPCSOperation() {
        try {
            double setPower = Double.parseDouble(setPowerField.getText());
            String mode = (String) operationModeCombo.getSelectedItem();
            
            for (PCSControlData pcs : pcsUnits) {
                if (pcs.isOnline() && !pcs.isHasFault()) {
                    pcs.setRunning(true);
                    pcs.setOperationMode(mode);
                    pcs.setSetPower(setPower / pcsUnits.size()); // Distribute power equally
                }
            }
            
            javax.swing.JOptionPane.showMessageDialog(this,
                "PCS System started successfully!\nMode: " + mode + "\nSet Power: " + setPower + " MW",
                "PCS Control",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Invalid power value. Please enter a valid number.",
                "Input Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void stopPCSOperation() {
        for (PCSControlData pcs : pcsUnits) {
            pcs.setRunning(false);
            pcs.setOperationMode("STANDBY");
            pcs.setSetPower(0.0);
            pcs.setActualPower(0.0);
        }
        
        javax.swing.JOptionPane.showMessageDialog(this,
            "PCS System stopped successfully!",
            "PCS Control",
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void emergencyStop() {
        int result = javax.swing.JOptionPane.showConfirmDialog(this,
            "Execute EMERGENCY STOP for all PCS units?",
            "Emergency Stop Confirmation",
            javax.swing.JOptionPane.YES_NO_OPTION,
            javax.swing.JOptionPane.WARNING_MESSAGE);
        
        if (result == javax.swing.JOptionPane.YES_OPTION) {
            for (PCSControlData pcs : pcsUnits) {
                pcs.setRunning(false);
                pcs.setOperationMode("E-STOP");
                pcs.setSetPower(0.0);
                pcs.setActualPower(0.0);
            }
            
            javax.swing.JOptionPane.showMessageDialog(this,
                "EMERGENCY STOP EXECUTED!\nAll PCS units have been shut down.",
                "Emergency Stop",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void resetPCSSystem() {
        for (PCSControlData pcs : pcsUnits) {
            pcs.setRunning(false);
            pcs.setOperationMode("STANDBY");
            pcs.setSetPower(0.0);
            pcs.setActualPower(0.0);
            pcs.setHasFault(false);
            pcs.setFaultMessage("");
        }
        
        setPowerField.setText("0.0");
        powerSlider.setValue(0);
        operationModeCombo.setSelectedIndex(0);
        autoModeCheckBox.setSelected(false);
        
        javax.swing.JOptionPane.showMessageDialog(this,
            "PCS System reset successfully!",
            "System Reset",
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showDiagnostics() {
        StringBuilder diagnostics = new StringBuilder();
        diagnostics.append("PCS System Diagnostics Report\n");
        diagnostics.append("================================\n\n");
        
        for (PCSControlData pcs : pcsUnits) {
            diagnostics.append("PCS Unit #").append(pcs.getPcsId()).append(":\n");
            diagnostics.append("  Online: ").append(pcs.isOnline() ? "YES" : "NO").append("\n");
            diagnostics.append("  Running: ").append(pcs.isRunning() ? "YES" : "NO").append("\n");
            diagnostics.append("  Mode: ").append(pcs.getOperationMode()).append("\n");
            diagnostics.append("  Set Power: ").append(df.format(pcs.getSetPower())).append(" MW\n");
            diagnostics.append("  Actual Power: ").append(df.format(pcs.getActualPower())).append(" MW\n");
            diagnostics.append("  DC Voltage: ").append(df.format(pcs.getDcVoltage())).append(" V\n");
            diagnostics.append("  AC Voltage: ").append(df.format(pcs.getAcVoltage())).append(" V\n");
            diagnostics.append("  Frequency: ").append(df.format(pcs.getFrequency())).append(" Hz\n");
            diagnostics.append("  Temperature: ").append(df.format(pcs.getTemperature())).append(" °C\n");
            diagnostics.append("  Efficiency: ").append(df.format(pcs.getEfficiency())).append(" %\n");
            diagnostics.append("  Fault Status: ").append(pcs.isHasFault() ? "FAULT" : "OK").append("\n");
            if (pcs.isHasFault()) {
                diagnostics.append("  Fault Message: ").append(pcs.getFaultMessage()).append("\n");
            }
            diagnostics.append("\n");
        }
        
        diagnostics.append("System Summary:\n");
        diagnostics.append("  Total Power: ").append(df.format(getTotalPower())).append(" MW\n");
        diagnostics.append("  Average Frequency: ").append(df.format(getAverageFrequency())).append(" Hz\n");
        diagnostics.append("  System Load: ").append(df.format(getTotalPower() / 3.0)).append(" %\n");
        
        javax.swing.JOptionPane.showMessageDialog(this,
            diagnostics.toString(),
            "PCS Diagnostics",
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Utility methods
    private double getTotalPower() {
        return pcsUnits.stream().mapToDouble(PCSControlData::getActualPower).sum();
    }
    
    private double getAverageFrequency() {
        return pcsUnits.stream().mapToDouble(PCSControlData::getFrequency).average().orElse(60.0);
    }
    
    private Color getOperationModeColor(String mode) {
        switch (mode) {
            case "STANDBY": return Color.GRAY;
            case "CHARGE": return Color.BLUE;
            case "DISCHARGE": return Color.GREEN;
            case "GRID_FORMING": return Color.MAGENTA;
            case "GRID_FOLLOWING": return Color.CYAN;
            case "E-STOP": return Color.RED;
            default: return Color.BLACK;
        }
    }
    
    public void stopTimer() {
        if (updateTimer != null) {
            updateTimer.stop();
        }
    }
    
    public List<PCSControlData> getPcsUnits() {
        return pcsUnits;
    }
    
    // Main method for independent execution
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("PCS Control Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            PCSControlPanel panel = new PCSControlPanel();
            frame.add(panel);
            
            frame.setSize(900, 700);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
            // Handle window closing to stop timer
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    panel.stopTimer();
                }
            });
        });
    }
}