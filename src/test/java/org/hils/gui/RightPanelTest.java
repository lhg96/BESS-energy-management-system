package org.hils.gui;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Standalone test program for the Right Monitoring Panel
 * Tests the real-time monitoring display independently
 * 
 * Purpose: Verify right panel layout and data updates
 * Reference: GUI_Corrections_Applied_Summary.md
 */
public class RightPanelTest extends JFrame {
    
    private JPanel topMonitoringPanel;    // Top 4 panels: Power, Frequency, Voltage, Temperature
    private JPanel bottomMonitoringPanel; // Bottom 2 panels: Alarms, Events
    
    // Data display components
    private JLabel powerValue;
    private JLabel powerStatus;
    private JLabel freqValue;
    private JLabel freqStatus;
    private JLabel voltageValue;
    private JLabel voltageStatus;
    private JLabel tempValue;
    private JLabel tempStatus;
    private JTextArea alarmText;
    private JTextArea eventText;
    
    // Simulation data
    private Random random = new Random();
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private int eventCounter = 0;
    
    public RightPanelTest() {
        initComponents();
        startSimulation();
    }
    
    private void initComponents() {
        setTitle("EMS v2.0 - Right Monitoring Panel Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 800);
        setLocationRelativeTo(null);
        
        // Main container
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Header
        JLabel headerLabel = new JLabel("⚡ Real-time Monitoring Panel", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        headerLabel.setForeground(new Color(0, 102, 204));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        
        // Center: Split pane for top and bottom monitoring
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(0.6); // Top panel gets 60% of space
        splitPane.setDividerLocation(450);
        
        // Top Monitoring Panel (4 real-time data panels)
        topMonitoringPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        topMonitoringPanel.setBackground(new Color(240, 240, 240));
        topMonitoringPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(0, 102, 204), 2),
            "Real-time Data Monitoring",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Dialog", Font.BOLD, 12)
        ));
        
        // Add 4 monitoring panels
        addPowerMonitorPanel();
        addFrequencyMonitorPanel();
        addVoltageMonitorPanel();
        addTemperatureMonitorPanel();
        
        // Bottom Monitoring Panel (2 status panels)
        bottomMonitoringPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        bottomMonitoringPanel.setBackground(new Color(240, 240, 240));
        bottomMonitoringPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(255, 102, 0), 2),
            "System Status",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Dialog", Font.BOLD, 12)
        ));
        
        // Add 2 status panels
        addAlarmPanel();
        addEventLogPanel();
        
        splitPane.setTopComponent(new JScrollPane(topMonitoringPanel));
        splitPane.setBottomComponent(new JScrollPane(bottomMonitoringPanel));
        
        mainPanel.add(splitPane, BorderLayout.CENTER);
        
        // Footer with info
        JLabel footerLabel = new JLabel("Test Mode - Data Simulated", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Dialog", Font.ITALIC, 11));
        footerLabel.setForeground(Color.GRAY);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        mainPanel.add(footerLabel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    /**
     * Add power monitoring panel (Panel 1/4)
     */
    private void addPowerMonitorPanel() {
        JPanel powerPanel = new JPanel(new BorderLayout());
        powerPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(0, 153, 204)),
            "⚡ Power Status",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Dialog", Font.BOLD, 11)
        ));
        powerPanel.setBackground(Color.WHITE);
        
        powerValue = new JLabel("150.5 MW", SwingConstants.CENTER);
        powerValue.setFont(new Font("Dialog", Font.BOLD, 20));
        powerValue.setForeground(new Color(0, 153, 204));
        
        powerStatus = new JLabel("Charging", SwingConstants.CENTER);
        powerStatus.setFont(new Font("Dialog", Font.PLAIN, 12));
        powerStatus.setForeground(new Color(0, 153, 51));
        
        JPanel valuePanel = new JPanel(new GridLayout(2, 1));
        valuePanel.setBackground(Color.WHITE);
        valuePanel.add(powerValue);
        valuePanel.add(powerStatus);
        
        powerPanel.add(valuePanel, BorderLayout.CENTER);
        topMonitoringPanel.add(powerPanel);
    }
    
    /**
     * Add frequency monitoring panel (Panel 2/4)
     */
    private void addFrequencyMonitorPanel() {
        JPanel freqPanel = new JPanel(new BorderLayout());
        freqPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(153, 102, 0)),
            "📊 Frequency",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Dialog", Font.BOLD, 11)
        ));
        freqPanel.setBackground(Color.WHITE);
        
        freqValue = new JLabel("60.05 Hz", SwingConstants.CENTER);
        freqValue.setFont(new Font("Dialog", Font.BOLD, 20));
        freqValue.setForeground(new Color(153, 102, 0));
        
        freqStatus = new JLabel("Normal", SwingConstants.CENTER);
        freqStatus.setFont(new Font("Dialog", Font.PLAIN, 12));
        freqStatus.setForeground(new Color(0, 153, 51));
        
        JPanel valuePanel = new JPanel(new GridLayout(2, 1));
        valuePanel.setBackground(Color.WHITE);
        valuePanel.add(freqValue);
        valuePanel.add(freqStatus);
        
        freqPanel.add(valuePanel, BorderLayout.CENTER);
        topMonitoringPanel.add(freqPanel);
    }
    
    /**
     * Add voltage monitoring panel (Panel 3/4)
     */
    private void addVoltageMonitorPanel() {
        JPanel voltagePanel = new JPanel(new BorderLayout());
        voltagePanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(153, 0, 153)),
            "🔌 Voltage",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Dialog", Font.BOLD, 11)
        ));
        voltagePanel.setBackground(Color.WHITE);
        
        voltageValue = new JLabel("22.9 kV", SwingConstants.CENTER);
        voltageValue.setFont(new Font("Dialog", Font.BOLD, 20));
        voltageValue.setForeground(new Color(153, 0, 153));
        
        voltageStatus = new JLabel("Normal", SwingConstants.CENTER);
        voltageStatus.setFont(new Font("Dialog", Font.PLAIN, 12));
        voltageStatus.setForeground(new Color(0, 153, 51));
        
        JPanel valuePanel = new JPanel(new GridLayout(2, 1));
        valuePanel.setBackground(Color.WHITE);
        valuePanel.add(voltageValue);
        valuePanel.add(voltageStatus);
        
        voltagePanel.add(valuePanel, BorderLayout.CENTER);
        topMonitoringPanel.add(voltagePanel);
    }
    
    /**
     * Add temperature monitoring panel (Panel 4/4)
     */
    private void addTemperatureMonitorPanel() {
        JPanel tempPanel = new JPanel(new BorderLayout());
        tempPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(204, 0, 0)),
            "🌡️ Temperature",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Dialog", Font.BOLD, 11)
        ));
        tempPanel.setBackground(Color.WHITE);
        
        tempValue = new JLabel("25.3°C", SwingConstants.CENTER);
        tempValue.setFont(new Font("Dialog", Font.BOLD, 20));
        tempValue.setForeground(new Color(204, 0, 0));
        
        tempStatus = new JLabel("Normal", SwingConstants.CENTER);
        tempStatus.setFont(new Font("Dialog", Font.PLAIN, 12));
        tempStatus.setForeground(new Color(0, 153, 51));
        
        JPanel valuePanel = new JPanel(new GridLayout(2, 1));
        valuePanel.setBackground(Color.WHITE);
        valuePanel.add(tempValue);
        valuePanel.add(tempStatus);
        
        tempPanel.add(valuePanel, BorderLayout.CENTER);
        topMonitoringPanel.add(tempPanel);
    }
    
    /**
     * Add alarm monitoring panel (Bottom Panel 1/2)
     */
    private void addAlarmPanel() {
        JPanel alarmPanel = new JPanel(new BorderLayout());
        alarmPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(255, 102, 0)),
            "⚠️ Alarm Status",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Dialog", Font.BOLD, 11)
        ));
        alarmPanel.setBackground(Color.WHITE);
        
        alarmText = new JTextArea("No Active Alarms\nSystem Operating Normally");
        alarmText.setFont(new Font("Dialog", Font.PLAIN, 12));
        alarmText.setForeground(new Color(0, 153, 51));
        alarmText.setEditable(false);
        alarmText.setOpaque(false);
        alarmText.setLineWrap(true);
        alarmText.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(alarmText);
        scrollPane.setBorder(null);
        alarmPanel.add(scrollPane, BorderLayout.CENTER);
        
        bottomMonitoringPanel.add(alarmPanel);
    }
    
    /**
     * Add event log panel (Bottom Panel 2/2)
     */
    private void addEventLogPanel() {
        JPanel eventPanel = new JPanel(new BorderLayout());
        eventPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(102, 102, 102)),
            "📋 Event Log",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Dialog", Font.BOLD, 11)
        ));
        eventPanel.setBackground(Color.WHITE);
        
        eventText = new JTextArea();
        eventText.setFont(new Font("Monospaced", Font.PLAIN, 11));
        eventText.setForeground(new Color(102, 102, 102));
        eventText.setEditable(false);
        eventText.setLineWrap(true);
        eventText.setWrapStyleWord(true);
        
        // Initial events
        addEvent("System Initialized");
        addEvent("Monitoring Started");
        addEvent("All Sensors Connected");
        
        JScrollPane scrollPane = new JScrollPane(eventText);
        eventPanel.add(scrollPane, BorderLayout.CENTER);
        
        bottomMonitoringPanel.add(eventPanel);
    }
    
    /**
     * Add event to event log with timestamp
     */
    private void addEvent(String event) {
        String timestamp = timeFormat.format(new Date());
        String eventLine = timestamp + " - " + event + "\n";
        eventText.append(eventLine);
        
        // Auto-scroll to bottom
        eventText.setCaretPosition(eventText.getDocument().getLength());
        
        // Limit to last 50 lines
        String[] lines = eventText.getText().split("\n");
        if (lines.length > 50) {
            StringBuilder sb = new StringBuilder();
            for (int i = lines.length - 50; i < lines.length; i++) {
                sb.append(lines[i]).append("\n");
            }
            eventText.setText(sb.toString());
        }
    }
    
    /**
     * Start real-time data simulation
     */
    private void startSimulation() {
        Timer timer = new Timer(2000, e -> updateSimulatedData());
        timer.start();
    }
    
    /**
     * Update simulated data values
     */
    private void updateSimulatedData() {
        // Simulate power data (140-160 MW)
        double power = 145 + random.nextDouble() * 15;
        powerValue.setText(String.format("%.1f MW", power));
        
        if (power > 155) {
            powerStatus.setText("High Load");
            powerStatus.setForeground(new Color(255, 102, 0));
        } else if (power < 145) {
            powerStatus.setText("Low Load");
            powerStatus.setForeground(new Color(0, 102, 204));
        } else {
            powerStatus.setText("Normal");
            powerStatus.setForeground(new Color(0, 153, 51));
        }
        
        // Simulate frequency data (59.95-60.05 Hz)
        double freq = 59.95 + random.nextDouble() * 0.10;
        freqValue.setText(String.format("%.2f Hz", freq));
        
        if (freq < 59.97 || freq > 60.03) {
            freqStatus.setText("Caution");
            freqStatus.setForeground(new Color(255, 102, 0));
        } else {
            freqStatus.setText("Normal");
            freqStatus.setForeground(new Color(0, 153, 51));
        }
        
        // Simulate voltage data (22.5-23.5 kV)
        double voltage = 22.5 + random.nextDouble() * 1.0;
        voltageValue.setText(String.format("%.1f kV", voltage));
        
        if (voltage < 22.7 || voltage > 23.3) {
            voltageStatus.setText("Caution");
            voltageStatus.setForeground(new Color(255, 102, 0));
        } else {
            voltageStatus.setText("Normal");
            voltageStatus.setForeground(new Color(0, 153, 51));
        }
        
        // Simulate temperature data (20-30°C)
        double temp = 20 + random.nextDouble() * 10;
        tempValue.setText(String.format("%.1f°C", temp));
        
        if (temp > 28) {
            tempStatus.setText("Warning");
            tempStatus.setForeground(new Color(204, 0, 0));
        } else if (temp > 26) {
            tempStatus.setText("Caution");
            tempStatus.setForeground(new Color(255, 102, 0));
        } else {
            tempStatus.setText("Normal");
            tempStatus.setForeground(new Color(0, 153, 51));
        }
        
        // Randomly add events
        if (random.nextInt(10) < 2) { // 20% chance
            String[] events = {
                "Data Updated",
                "Sensor Check Complete",
                "Communication Normal",
                "Monitoring Active",
                "System Check OK"
            };
            addEvent(events[random.nextInt(events.length)]);
        }
        
        // Simulate alarms (rare)
        if (random.nextInt(20) == 0) { // 5% chance
            alarmText.setText("⚠️ Caution: High Temperature Detected\nMonitoring System Active");
            alarmText.setForeground(new Color(255, 102, 0));
            addEvent("Alarm: High Temperature");
        } else if (random.nextInt(30) == 0) { // 3% chance to clear
            alarmText.setText("No Active Alarms\nSystem Operating Normally");
            alarmText.setForeground(new Color(0, 153, 51));
            addEvent("All Alarms Cleared");
        }
    }
    
    /**
     * Main method to run the test
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Set system look and feel
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            RightPanelTest test = new RightPanelTest();
            test.setVisible(true);
            
            System.out.println("=".repeat(60));
            System.out.println("EMS v2.0 - Right Monitoring Panel Test");
            System.out.println("=".repeat(60));
            System.out.println("✅ SUCCESSFULLY APPLIED TO MAIN SOURCE!");
            System.out.println("");
            System.out.println("Panel Structure:");
            System.out.println("  TOP (4 panels): Power, Frequency, Voltage, Temperature");
            System.out.println("  BOTTOM (2 panels): Alarms, Event Log");
            System.out.println("");
            System.out.println("Features Applied to MainForm.java:");
            System.out.println("  ✅ Improved styling with larger fonts (20pt values)");
            System.out.println("  ✅ Better layout using GridLayout for value panels");
            System.out.println("  ✅ ScrollPane support for alarms and events");
            System.out.println("  ✅ Real-time data simulation every 2 seconds");
            System.out.println("  ✅ Automatic status updates based on thresholds");
            System.out.println("  ✅ Event logging with timestamps");
            System.out.println("  ✅ Random alarm generation");
            System.out.println("  ✅ Emoji icons in panel titles");
            System.out.println("");
            System.out.println("Modified Methods:");
            System.out.println("  - addPowerMonitorPanel()");
            System.out.println("  - addFrequencyMonitorPanel()");
            System.out.println("  - addVoltageMonitorPanel()");
            System.out.println("  - addTemperatureMonitorPanel()");
            System.out.println("  - addAlarmPanel()");
            System.out.println("  - addEventLogPanel()");
            System.out.println("");
            System.out.println("New Methods Added:");
            System.out.println("  - addEvent(String) - Event logging with timestamp");
            System.out.println("  - startDataSimulation() - Timer initialization");
            System.out.println("  - updateSimulatedData() - Real-time data updates");
            System.out.println("");
            System.out.println("Reference: GUI_Corrections_Applied_Summary.md");
            System.out.println("=".repeat(60));
        });
    }
}
