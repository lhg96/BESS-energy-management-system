package org.hils.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.Timer;

import org.hils.gui.panels.SettingInfoPanel;
import org.hils.gui.panels.SettingOptionPanel;
import org.hils.gui.panels.PCSBMSSummery;
import org.hils.gui.panels.TabPanel;
import org.hils.gui.controls.PCSControlPanel;


import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;


/**
 * main gui
 * @author hyun  keun lim since 2023 03 01
 *
 */
public class MainForm {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		if (args.length > 0) {
			switch (args[0].toLowerCase()) {
				case "top":
					mainTopPanel(args);
					break;
				case "center":
					mainCenterPanel(args);
					break;
				case "east":
					mainEastPanel(args);
					break;
				case "west":
					mainWestPanel(args);
					break;
				default:
					// Default: launch full application
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								MainForm window = new MainForm();
								window.frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					break;
			}
		} else {
			// Default: launch full application
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MainForm window = new MainForm();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	public JFrame frame;
	
	private javax.swing.JPanel centerPanel;
    private javax.swing.JPanel eastPanel;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane tabbedPanel;
    private javax.swing.JPanel middlePanel;
    private javax.swing.JPanel summeryPanel;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel topPanel;
	//custom code
    public PCSBMSSummery pcsbmsSummery1;
    
    // Right panel data labels for real-time updates
    private javax.swing.JLabel powerValue;
    private javax.swing.JLabel powerStatus;
    private javax.swing.JLabel freqValue;
    private javax.swing.JLabel freqStatus;
    private javax.swing.JLabel voltageValue;
    private javax.swing.JLabel voltageStatus;
    private javax.swing.JLabel tempValue;
    private javax.swing.JLabel tempStatus;
    private javax.swing.JTextArea alarmText;
    private javax.swing.JTextArea eventText;
    
    // Data simulation
    private java.util.Random random = new java.util.Random();
    private SimpleDateFormat eventTimeFormat = new SimpleDateFormat("HH:mm:ss");
	public PCSBMSSummery pcsbmsSummery2;

	/**
	 * Create the application.
	 */
	public MainForm() {
		initialize();
		//custom
		initGUI();
	}

	/**
	* Initialize GUI components with modern EMS layout
	*/
	private void initGUI() {
        // Setup real-time clock in header
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timeSTR = df.format(new Date());
                timeLabel.setText(timeSTR);
            }
        }).start();
        
        // Initialize summary panels for system overview tab
        pcsbmsSummery1 = new PCSBMSSummery(1, 10, 100, 200);
        pcsbmsSummery2 = new PCSBMSSummery(2, 30, 100, 200);
        
        // Add summary panels to the overview tab
        jPanel3.add(pcsbmsSummery1);
        jPanel3.add(pcsbmsSummery2);
        
        // Add setting panels to the overview tab
        SettingOptionPanel settingOptionPanel = new SettingOptionPanel();     
        SettingInfoPanel settingInfoPanel = new SettingInfoPanel();        
        jPanel12.add(settingOptionPanel);
        jPanel12.add(settingInfoPanel);
        
        // Setup monitoring panels in right panel
        setupRightPanelContent();
        
        // Initialize data monitoring system
        initializeDataMonitoring();
        
        // Configure window properties
        configureWindow();
    }
    
    /**
     * Setup right panel content with real-time monitoring
     */
    private void setupRightPanelContent() {
        // Add real-time data panels to jPanel13 (top 4 panels)
        addPowerMonitorPanel();
        addFrequencyMonitorPanel();
        addVoltageMonitorPanel();
        addTemperatureMonitorPanel();
        
        // Add alarm and event panels to jPanel14 (bottom 2 panels)
        addAlarmPanel();
        addEventLogPanel();
    }
    
    /**
     * Add power monitoring panel (Panel 1/4)
     */
    private void addPowerMonitorPanel() {
        javax.swing.JPanel powerPanel = new javax.swing.JPanel(new BorderLayout());
        powerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)),
            "⚡ Power Status", 
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
            javax.swing.border.TitledBorder.DEFAULT_POSITION, 
            new java.awt.Font("Dialog", java.awt.Font.BOLD, 11)
        ));
        powerPanel.setBackground(java.awt.Color.WHITE);
        
        powerValue = new javax.swing.JLabel("150.5 MW", javax.swing.SwingConstants.CENTER);
        powerValue.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 20));
        powerValue.setForeground(new java.awt.Color(0, 153, 204));
        
        powerStatus = new javax.swing.JLabel("Charging", javax.swing.SwingConstants.CENTER);
        powerStatus.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));
        powerStatus.setForeground(new java.awt.Color(0, 153, 51));
        
        javax.swing.JPanel valuePanel = new javax.swing.JPanel(new java.awt.GridLayout(2, 1));
        valuePanel.setBackground(java.awt.Color.WHITE);
        valuePanel.add(powerValue);
        valuePanel.add(powerStatus);
        
        powerPanel.add(valuePanel, BorderLayout.CENTER);
        jPanel13.add(powerPanel);
    }
    
    /**
     * Add frequency monitoring panel (Panel 2/4)
     */
    private void addFrequencyMonitorPanel() {
        javax.swing.JPanel freqPanel = new javax.swing.JPanel(new BorderLayout());
        freqPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 102, 0)),
            "📊 Frequency", 
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
            javax.swing.border.TitledBorder.DEFAULT_POSITION, 
            new java.awt.Font("Dialog", java.awt.Font.BOLD, 11)
        ));
        freqPanel.setBackground(java.awt.Color.WHITE);
        
        freqValue = new javax.swing.JLabel("60.05 Hz", javax.swing.SwingConstants.CENTER);
        freqValue.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 20));
        freqValue.setForeground(new java.awt.Color(153, 102, 0));
        
        freqStatus = new javax.swing.JLabel("Normal", javax.swing.SwingConstants.CENTER);
        freqStatus.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));
        freqStatus.setForeground(new java.awt.Color(0, 153, 51));
        
        javax.swing.JPanel valuePanel = new javax.swing.JPanel(new java.awt.GridLayout(2, 1));
        valuePanel.setBackground(java.awt.Color.WHITE);
        valuePanel.add(freqValue);
        valuePanel.add(freqStatus);
        
        freqPanel.add(valuePanel, BorderLayout.CENTER);
        jPanel13.add(freqPanel);
    }
    
    /**
     * Add voltage monitoring panel (Panel 3/4)
     */
    private void addVoltageMonitorPanel() {
        javax.swing.JPanel voltagePanel = new javax.swing.JPanel(new BorderLayout());
        voltagePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 153)),
            "🔌 Voltage", 
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
            javax.swing.border.TitledBorder.DEFAULT_POSITION, 
            new java.awt.Font("Dialog", java.awt.Font.BOLD, 11)
        ));
        voltagePanel.setBackground(java.awt.Color.WHITE);
        
        voltageValue = new javax.swing.JLabel("22.9 kV", javax.swing.SwingConstants.CENTER);
        voltageValue.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 20));
        voltageValue.setForeground(new java.awt.Color(153, 0, 153));
        
        voltageStatus = new javax.swing.JLabel("Normal", javax.swing.SwingConstants.CENTER);
        voltageStatus.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));
        voltageStatus.setForeground(new java.awt.Color(0, 153, 51));
        
        javax.swing.JPanel valuePanel = new javax.swing.JPanel(new java.awt.GridLayout(2, 1));
        valuePanel.setBackground(java.awt.Color.WHITE);
        valuePanel.add(voltageValue);
        valuePanel.add(voltageStatus);
        
        voltagePanel.add(valuePanel, BorderLayout.CENTER);
        jPanel13.add(voltagePanel);
    }
    
    /**
     * Add temperature monitoring panel (Panel 4/4)
     */
    private void addTemperatureMonitorPanel() {
        javax.swing.JPanel tempPanel = new javax.swing.JPanel(new BorderLayout());
        tempPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)),
            "🌡️ Temperature", 
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
            javax.swing.border.TitledBorder.DEFAULT_POSITION, 
            new java.awt.Font("Dialog", java.awt.Font.BOLD, 11)
        ));
        tempPanel.setBackground(java.awt.Color.WHITE);
        
        tempValue = new javax.swing.JLabel("25.3°C", javax.swing.SwingConstants.CENTER);
        tempValue.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 20));
        tempValue.setForeground(new java.awt.Color(204, 0, 0));
        
        tempStatus = new javax.swing.JLabel("Normal", javax.swing.SwingConstants.CENTER);
        tempStatus.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));
        tempStatus.setForeground(new java.awt.Color(0, 153, 51));
        
        javax.swing.JPanel valuePanel = new javax.swing.JPanel(new java.awt.GridLayout(2, 1));
        valuePanel.setBackground(java.awt.Color.WHITE);
        valuePanel.add(tempValue);
        valuePanel.add(tempStatus);
        
        tempPanel.add(valuePanel, BorderLayout.CENTER);
        jPanel13.add(tempPanel);
    }
    
    /**
     * Add alarm monitoring panel (Bottom Panel 1/2)
     */
    private void addAlarmPanel() {
        javax.swing.JPanel alarmPanel = new javax.swing.JPanel(new BorderLayout());
        alarmPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0)),
            "⚠️ Alarm Status", 
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
            javax.swing.border.TitledBorder.DEFAULT_POSITION, 
            new java.awt.Font("Dialog", java.awt.Font.BOLD, 11)
        ));
        alarmPanel.setBackground(java.awt.Color.WHITE);
        
        alarmText = new javax.swing.JTextArea("No Active Alarms\nSystem Operating Normally");
        alarmText.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));
        alarmText.setForeground(new java.awt.Color(0, 153, 51));
        alarmText.setEditable(false);
        alarmText.setOpaque(false);
        alarmText.setLineWrap(true);
        alarmText.setWrapStyleWord(true);
        
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(alarmText);
        scrollPane.setBorder(null);
        alarmPanel.add(scrollPane, BorderLayout.CENTER);
        jPanel14.add(alarmPanel);
    }
    
    /**
     * Add event log panel (Bottom Panel 2/2)
     */
    private void addEventLogPanel() {
        javax.swing.JPanel eventPanel = new javax.swing.JPanel(new BorderLayout());
        eventPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)),
            "📋 Event Log", 
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
            javax.swing.border.TitledBorder.DEFAULT_POSITION, 
            new java.awt.Font("Dialog", java.awt.Font.BOLD, 11)
        ));
        eventPanel.setBackground(java.awt.Color.WHITE);
        
        eventText = new javax.swing.JTextArea();
        eventText.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 11));
        eventText.setForeground(new java.awt.Color(102, 102, 102));
        eventText.setEditable(false);
        eventText.setLineWrap(true);
        eventText.setWrapStyleWord(true);
        
        // Initial events
        addEvent("System Initialized");
        addEvent("Monitoring Started");
        addEvent("All Sensors Connected");
        
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(eventText);
        scrollPane.setBorder(null);
        eventPanel.add(scrollPane, BorderLayout.CENTER);
        jPanel14.add(eventPanel);
        
        String tab4Title = "System Status"; 
        TabPanel tab4 = new TabPanel(tab4Title);
        tabbedPanel.addTab(tab4Title, tab4);
        
        // Add real-time charts tab
        String tab5Title = "Charts"; 
        org.hils.gui.chart.RealTimeChartPanel chartPanel = new org.hils.gui.chart.RealTimeChartPanel();
        tabbedPanel.addTab(tab5Title, chartPanel);
        
        // Add system configuration tab
        String tab6Title = "Config"; 
        org.hils.gui.config.SystemConfigPanel configPanel = new org.hils.gui.config.SystemConfigPanel();
        tabbedPanel.addTab(tab6Title, configPanel);
        
        // REMOVED: setupSOCPowerPanel() - This method was overwriting the right monitoring panel
        // The right panel should contain ONLY monitoring displays, not controls
        // Battery and PCS controls are already in the left panel (jPanel16)
        // Reference: GUI_Issues_And_Corrections.md - Critical Issue #1
        // setupSOCPowerPanel();
        
	}
	
	/**
	 * Setup SOC and Power setting panel
	 */
	private void setupSOCPowerPanel() {
		// SOC Display Panel
		javax.swing.JPanel socPanel = new javax.swing.JPanel(new java.awt.GridLayout(3, 2, 5, 5));
		socPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Battery SOC Status"));
		
		javax.swing.JLabel soc1Label = new javax.swing.JLabel("Battery #1 SOC:");
		javax.swing.JLabel soc1Value = new javax.swing.JLabel("85%");
		soc1Value.setForeground(java.awt.Color.BLUE);
		soc1Value.setFont(new java.awt.Font("Dialog", 1, 14));
		
		javax.swing.JLabel soc2Label = new javax.swing.JLabel("Battery #2 SOC:");
		javax.swing.JLabel soc2Value = new javax.swing.JLabel("78%");
		soc2Value.setForeground(java.awt.Color.BLUE);
		soc2Value.setFont(new java.awt.Font("Dialog", 1, 14));
		
		javax.swing.JLabel avgSocLabel = new javax.swing.JLabel("Average SOC:");
		javax.swing.JLabel avgSocValue = new javax.swing.JLabel("81.5%");
		avgSocValue.setForeground(java.awt.Color.GREEN);
		avgSocValue.setFont(new java.awt.Font("Dialog", 1, 14));
		
		socPanel.add(soc1Label);
		socPanel.add(soc1Value);
		socPanel.add(soc2Label);
		socPanel.add(soc2Value);
		socPanel.add(avgSocLabel);
		socPanel.add(avgSocValue);
		
		// Power Setting Panel
		javax.swing.JPanel powerSetPanel = new javax.swing.JPanel(new java.awt.GridLayout(4, 2, 5, 5));
		powerSetPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Power Setting"));
		
		javax.swing.JLabel setPowerLabel = new javax.swing.JLabel("Set Power (MW):");
		javax.swing.JTextField setPowerField = new javax.swing.JTextField("0.0");
		setPowerField.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		
		javax.swing.JLabel setFreqLabel = new javax.swing.JLabel("Target Frequency (Hz):");
		javax.swing.JTextField setFreqField = new javax.swing.JTextField("60.0");
		setFreqField.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		
		javax.swing.JLabel modeLabel = new javax.swing.JLabel("Operation Mode:");
		javax.swing.JComboBox<String> modeCombo = new javax.swing.JComboBox<>(
			new String[]{"Standby", "Charge", "Discharge", "Auto"}
		);
		
		javax.swing.JButton applyButton = new javax.swing.JButton("Apply Settings");
		applyButton.setBackground(java.awt.Color.CYAN);
		applyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Apply power settings logic
				javax.swing.JOptionPane.showMessageDialog(frame, 
					"Power settings applied successfully!", 
					"EMS Control", 
					javax.swing.JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		javax.swing.JButton emergencyStopButton = new javax.swing.JButton("EMERGENCY STOP");
		emergencyStopButton.setBackground(java.awt.Color.RED);
		emergencyStopButton.setForeground(java.awt.Color.WHITE);
		emergencyStopButton.setFont(new java.awt.Font("Dialog", 1, 12));
		emergencyStopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = javax.swing.JOptionPane.showConfirmDialog(frame,
					"Are you sure you want to execute EMERGENCY STOP?",
					"Emergency Stop Confirmation",
					javax.swing.JOptionPane.YES_NO_OPTION,
					javax.swing.JOptionPane.WARNING_MESSAGE);
				if (result == javax.swing.JOptionPane.YES_OPTION) {
					// Emergency stop logic
					javax.swing.JOptionPane.showMessageDialog(frame,
						"EMERGENCY STOP EXECUTED!",
						"EMS Control",
						javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		powerSetPanel.add(setPowerLabel);
		powerSetPanel.add(setPowerField);
		powerSetPanel.add(setFreqLabel);
		powerSetPanel.add(setFreqField);
		powerSetPanel.add(modeLabel);
		powerSetPanel.add(modeCombo);
		powerSetPanel.add(applyButton);
		powerSetPanel.add(emergencyStopButton);
		
		// Add panels to the main SOC panel container
		jPanel13.setLayout(new BorderLayout());
		jPanel13.add(socPanel, BorderLayout.CENTER);
		
		jPanel14.setLayout(new BorderLayout());
		jPanel14.add(powerSetPanel, BorderLayout.CENTER);
		
		// Add comprehensive battery pack SOC panel to the bottom left
		setupBatteryPackSOCPanel();
	}
	
	/**
	 * Update system status and monitoring data
	 */
	public void updateSystemStatus() {
		// This method can be called periodically to update system data
		// Update SOC values, power readings, frequency, etc.
		
		// Template for real-time data updates
		// Individual panels handle their own data simulation and updates
	}
	
	/**
	 * Initialize data monitoring and periodic updates
	 */
	private void initializeDataMonitoring() {
		// Start periodic data update timer
		Timer dataUpdateTimer = new Timer(5000, e -> updateSystemStatus());
		dataUpdateTimer.start();
		
		// Start real-time data simulation for right panel
		startDataSimulation();
		
		// Initialize communication with external systems
		// PCS communication, BMS communication, Grid interface, etc.
	}
	
	/**
	 * Setup comprehensive battery pack SOC panel in the left bottom area
	 */
	private void setupBatteryPackSOCPanel() {
		// Create enhanced battery pack SOC display panel
		org.hils.gui.controls.BatteryPackSOCPanel batteryPackSOCPanel = 
			new org.hils.gui.controls.BatteryPackSOCPanel();
		
		// Create a dedicated panel for the bottom left battery SOC display
		javax.swing.JPanel batterySOCContainer = new javax.swing.JPanel(new BorderLayout());
		batterySOCContainer.setBorder(javax.swing.BorderFactory.createTitledBorder("Battery Pack SOC Monitor"));
		batterySOCContainer.setPreferredSize(new java.awt.Dimension(300, 200));
		
		batterySOCContainer.add(batteryPackSOCPanel, BorderLayout.CENTER);
		
		// Add quick control buttons
		javax.swing.JPanel quickControlPanel = new javax.swing.JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		quickControlPanel.setPreferredSize(new java.awt.Dimension(0, 35));
		
		javax.swing.JButton quickBalanceBtn = new javax.swing.JButton("Quick Balance");
		quickBalanceBtn.setFont(new java.awt.Font("Dialog", 0, 10));
		quickBalanceBtn.addActionListener(e -> {
			javax.swing.JOptionPane.showMessageDialog(frame,
				"Quick battery balancing initiated for all packs.",
				"Battery Management",
				javax.swing.JOptionPane.INFORMATION_MESSAGE);
		});
		
		javax.swing.JButton emergencyIsolateBtn = new javax.swing.JButton("Emergency Isolate");
		emergencyIsolateBtn.setFont(new java.awt.Font("Dialog", 0, 10));
		emergencyIsolateBtn.setBackground(java.awt.Color.RED);
		emergencyIsolateBtn.setForeground(java.awt.Color.WHITE);
		emergencyIsolateBtn.addActionListener(e -> {
			int result = javax.swing.JOptionPane.showConfirmDialog(frame,
				"Are you sure you want to isolate all battery packs?",
				"Emergency Isolation",
				javax.swing.JOptionPane.YES_NO_OPTION,
				javax.swing.JOptionPane.WARNING_MESSAGE);
			if (result == javax.swing.JOptionPane.YES_OPTION) {
				javax.swing.JOptionPane.showMessageDialog(frame,
					"All battery packs have been isolated for safety!",
					"Emergency Isolation",
					javax.swing.JOptionPane.WARNING_MESSAGE);
			}
		});
		
		javax.swing.JButton systemResetBtn = new javax.swing.JButton("System Reset");
		systemResetBtn.setFont(new java.awt.Font("Dialog", 0, 10));
		systemResetBtn.addActionListener(e -> {
			int result = javax.swing.JOptionPane.showConfirmDialog(frame,
				"Reset battery management system?",
				"System Reset",
				javax.swing.JOptionPane.YES_NO_OPTION);
			if (result == javax.swing.JOptionPane.YES_OPTION) {
				javax.swing.JOptionPane.showMessageDialog(frame,
					"Battery management system reset successfully!",
					"System Reset",
					javax.swing.JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		quickControlPanel.add(quickBalanceBtn);
		quickControlPanel.add(emergencyIsolateBtn);
		quickControlPanel.add(systemResetBtn);
		
		batterySOCContainer.add(quickControlPanel, BorderLayout.SOUTH);
		
		// Add to the existing scroll panel structure
		// Using jPanel13 as the main battery SOC display area (top)
		jPanel13.removeAll();
		jPanel13.setLayout(new BorderLayout());
		jPanel13.add(batterySOCContainer, BorderLayout.CENTER);
		
		// Add enhanced PCS control panel to jPanel14 (bottom)
		jPanel14.removeAll();
		jPanel14.setLayout(new BorderLayout());
		
		PCSControlPanel enhancedPCSControl = new PCSControlPanel();
		jPanel14.add(enhancedPCSControl, BorderLayout.CENTER);
	}



	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(50, 50, 1400, 900);  // Optimized window size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("EMS - Energy Management System v2.0");
		
		// Initialize all panel components
		topPanel = new javax.swing.JPanel();
        iconLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        centerPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        summeryPanel = new javax.swing.JPanel();
        middlePanel = new javax.swing.JPanel();
        tabbedPanel = new javax.swing.JTabbedPane();
        eastPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();

        // Setup main layout with margins
        frame.getContentPane().setLayout(new BorderLayout(5, 5));
        
        // Setup header panel with modern design
        setupHeaderPanel();
        
        // Setup main content area
        setupMainLayout();
        
        // Setup status bar
        setupStatusBar();
        
        // Add components to frame
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.getContentPane().add(jPanel15, BorderLayout.SOUTH);
    }
    
    /**
     * Header Panel (Top)
     * Purpose: System identification, status, and time information display
     * Components: EMS icon, system title, subtitle, status indicator, real-time clock
     * Update Cycle: Clock updates every 1 second, status updates every 5 seconds
     * Reference: EMS_Screen_Configuration_Analysis_Consolidated.md Section 2.1
     */
    private void setupHeaderPanel() {
        topPanel.setLayout(new BorderLayout(10, 0));
        topPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createEtchedBorder(),
            javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        topPanel.setBackground(new java.awt.Color(240, 248, 255));
        topPanel.setPreferredSize(new java.awt.Dimension(0, 70));

        // Left side: System icon and name
        javax.swing.JPanel leftHeader = new javax.swing.JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        leftHeader.setBackground(new java.awt.Color(240, 248, 255));
        
        iconLabel.setText("🔋 EMS");
        iconLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 22));
        iconLabel.setForeground(new java.awt.Color(0, 102, 204));
        iconLabel.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        leftHeader.add(iconLabel);

        // Center: System title with subtitle
        javax.swing.JPanel centerHeader = new javax.swing.JPanel();
        centerHeader.setLayout(new javax.swing.BoxLayout(centerHeader, javax.swing.BoxLayout.Y_AXIS));
        centerHeader.setBackground(new java.awt.Color(240, 248, 255));
        
        titleLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("300MW BESS Energy Management System");
        titleLabel.setForeground(new java.awt.Color(51, 51, 51));
        titleLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        
        javax.swing.JLabel subTitle = new javax.swing.JLabel("Real-time Monitoring & Control System");
        subTitle.setFont(new java.awt.Font("Dialog", 0, 12));
        subTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subTitle.setForeground(new java.awt.Color(102, 102, 102));
        subTitle.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        
        centerHeader.add(titleLabel);
        centerHeader.add(javax.swing.Box.createVerticalStrut(3));
        centerHeader.add(subTitle);

        // Right side: System status and time
        javax.swing.JPanel rightHeader = new javax.swing.JPanel();
        rightHeader.setLayout(new javax.swing.BoxLayout(rightHeader, javax.swing.BoxLayout.Y_AXIS));
        rightHeader.setBackground(new java.awt.Color(240, 248, 255));
        
        javax.swing.JLabel systemStatus = new javax.swing.JLabel("🟢 System Normal");
        systemStatus.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 11));
        systemStatus.setForeground(new java.awt.Color(0, 153, 51));
        systemStatus.setAlignmentX(java.awt.Component.RIGHT_ALIGNMENT);
        
        timeLabel.setFont(new java.awt.Font("Dialog", 0, 12));
        timeLabel.setForeground(new java.awt.Color(102, 102, 102));
        timeLabel.setAlignmentX(java.awt.Component.RIGHT_ALIGNMENT);
        
        rightHeader.add(systemStatus);
        rightHeader.add(javax.swing.Box.createVerticalStrut(5));
        rightHeader.add(timeLabel);

        topPanel.add(leftHeader, BorderLayout.WEST);
        topPanel.add(centerHeader, BorderLayout.CENTER);
        topPanel.add(rightHeader, BorderLayout.EAST);
    }
    
    /**
     * Setup main content layout with three-panel design
     */
    private void setupMainLayout() {
        centerPanel.setLayout(new BorderLayout(10, 10));
        centerPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 5, 10));
        
        // Left Panel: Battery & PCS Control (320px width)
        setupLeftControlPanel();
        
        // Center Panel: Main tabbed content
        setupCenterTabbedPanel();
        
        // Right Panel: Real-time monitoring (350px width) 
        setupRightMonitorPanel();
        
        centerPanel.add(jPanel16, BorderLayout.WEST);   // Left control panel
        centerPanel.add(tabbedPanel, BorderLayout.CENTER);  // Center tabs
        centerPanel.add(eastPanel, BorderLayout.EAST);   // Right monitoring panel
    }
    
    /**
     * Left Control Panel (West - jPanel16)
     * Purpose: Real-time direct battery and PCS control
     * Layout: Vertical JSplitPane - Top 55% Battery, Bottom 45% PCS
     * Width: 350px
     * Reference: EMS_Screen_Configuration_Analysis_Consolidated.md Section 2.2
     */
    private void setupLeftControlPanel() {
        jPanel16.setLayout(new BorderLayout(5, 5));
        jPanel16.setPreferredSize(new java.awt.Dimension(350, 0));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(
            javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.LIGHT_GRAY, java.awt.Color.GRAY), 
            "⚡ Battery & PCS Control System", 
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
            javax.swing.border.TitledBorder.DEFAULT_POSITION, 
            new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
            new java.awt.Color(0, 102, 204)
        ));
        jPanel16.setBackground(new java.awt.Color(248, 252, 255));
        
        // Create vertical split pane for battery control (top) and PCS control (bottom)
        javax.swing.JSplitPane leftSplitPane = new javax.swing.JSplitPane(
            javax.swing.JSplitPane.VERTICAL_SPLIT, true);
        leftSplitPane.setDividerLocation(450);
        leftSplitPane.setResizeWeight(0.55);
        leftSplitPane.setBorder(null);
        leftSplitPane.setBackground(new java.awt.Color(248, 252, 255));
        
        // Battery Control Panel
        javax.swing.JPanel batteryControlContainer = new org.hils.gui.controls.BatteryPackSOCPanel();
        
        // PCS Control Panel
        javax.swing.JPanel pcsControlContainer = new org.hils.gui.controls.PCSControlPanel();
        
        leftSplitPane.setTopComponent(batteryControlContainer);
        leftSplitPane.setBottomComponent(pcsControlContainer);
        
        jPanel16.add(leftSplitPane, BorderLayout.CENTER);
    }
    
    /**
     * Center Tab Panel (Center - tabbedPanel)
     * Purpose: Main content area with 6 tabs
     * Tabs:
     *   1. System Overview - PCS/BMS summary and additional controls
     *   2. Battery Status - Detailed battery system monitoring
     *   3. PCS Detail Control - PCS system detailed control and monitoring
     *   4. Grid Monitor - Power grid interconnection monitoring
     *   5. Real-time Charts - Power, SOC, frequency visualization
     *   6. System Configuration - System parameters and settings management
     * Reference: EMS_Screen_Configuration_Analysis_Consolidated.md Section 2.3
     */
    private void setupCenterTabbedPanel() {
        tabbedPanel.setFont(new java.awt.Font("Dialog", 0, 12));
        tabbedPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.LIGHT_GRAY, java.awt.Color.GRAY));
        tabbedPanel.setBackground(java.awt.Color.WHITE);
        
        // Tab 1: System Overview
        setupSystemOverviewTab();
        
        // Tab 2: Battery Status  
        String tab2Title = "Battery Status";
        org.hils.gui.panels.BatteryStatusPanel tab2 = new org.hils.gui.panels.BatteryStatusPanel();
        tabbedPanel.addTab(tab2Title, new javax.swing.ImageIcon(), tab2, "Battery System Status Monitoring");
        
        // Tab 3: PCS Control Detail
        String tab3Title = "PCS Detail Control";
        org.hils.gui.panels.PCSDetailStausPanel tab3 = new org.hils.gui.panels.PCSDetailStausPanel();
        tabbedPanel.addTab(tab3Title, new javax.swing.ImageIcon(), tab3, "PCS Detailed Control & Monitoring");
        
        // Tab 4: Grid Monitor
        String tab4Title = "Grid Monitor";
        org.hils.gui.panels.GridMonitorPanel tab4 = new org.hils.gui.panels.GridMonitorPanel();
        tabbedPanel.addTab(tab4Title, new javax.swing.ImageIcon(), tab4, "Power Grid Monitoring");
        
        // Tab 5: Real-time Charts
        String tab5Title = "Real-time Charts"; 
        try {
            org.hils.gui.chart.RealTimeChartPanel chartPanel = 
                new org.hils.gui.chart.RealTimeChartPanel();
            tabbedPanel.addTab(tab5Title, new javax.swing.ImageIcon(), chartPanel, "Real-time Data Charts");
        } catch (Exception e) {
            javax.swing.JLabel errorLabel = new javax.swing.JLabel("Loading Chart Panel...", javax.swing.SwingConstants.CENTER);
            tabbedPanel.addTab(tab5Title, errorLabel);
        }
        
        // Tab 6: System Configuration
        String tab6Title = "System Config"; 
        try {
            org.hils.gui.config.SystemConfigPanel configPanel = 
                new org.hils.gui.config.SystemConfigPanel();
            tabbedPanel.addTab(tab6Title, new javax.swing.ImageIcon(), configPanel, "System Configuration");
        } catch (Exception e) {
            javax.swing.JLabel errorLabel = new javax.swing.JLabel("Loading Configuration Panel...", javax.swing.SwingConstants.CENTER);
            tabbedPanel.addTab(tab6Title, errorLabel);
        }
    }
    
    /**
     * Setup system overview tab with enhanced layout
     */
    private void setupSystemOverviewTab() {
        middlePanel.setLayout(new BorderLayout(10, 10));
        middlePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        middlePanel.setBackground(new java.awt.Color(248, 248, 255));
        
        summeryPanel.setLayout(new BorderLayout(8, 8));
        summeryPanel.setBackground(new java.awt.Color(248, 248, 255));
        
        // Top section: 4 summary panels in 2x2 grid
        jPanel1.setLayout(new BorderLayout(5, 5));
        jPanel3.setLayout(new java.awt.GridLayout(2, 2, 15, 15));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel3.setBackground(new java.awt.Color(248, 248, 255));
        jPanel1.add(jPanel3, BorderLayout.CENTER);
        
        // Bottom section: Additional control panels
        jPanel4.setLayout(new BorderLayout(5, 5));
        jPanel12.setLayout(new java.awt.GridLayout(1, 2, 15, 10));
        jPanel12.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel12.setBackground(new java.awt.Color(248, 248, 255));
        jPanel4.add(jPanel12, BorderLayout.CENTER);
        
        summeryPanel.add(jPanel1, BorderLayout.CENTER);
        summeryPanel.add(jPanel4, BorderLayout.SOUTH);
        
        middlePanel.add(summeryPanel, BorderLayout.CENTER);
        tabbedPanel.addTab("System Overview", new javax.swing.ImageIcon(), middlePanel, "Overall System Status Overview");
    }
    
    /**
     * Right Monitoring Panel (East - eastPanel)
     * Purpose: Real-time data monitoring ONLY (no controls)
     * Width: 350px
     * Layout:
     *   - jPanel13 (Top): 4 real-time data panels in GridLayout(4,1)
     *     1. Power Monitoring
     *     2. Frequency Monitoring
     *     3. Voltage Monitoring
     *     4. Temperature Monitoring
     *   - jPanel14 (Bottom): 2 panels in GridLayout(2,1)
     *     1. Real-time Alarms
     *     2. System Events
     * Reference: EMS_Screen_Configuration_Analysis_Consolidated.md Section 2.4
     * WARNING: This panel should NOT contain control elements (those are in left panel)
     */
    private void setupRightMonitorPanel() {
        eastPanel.setLayout(new BorderLayout(5, 5));
        eastPanel.setPreferredSize(new java.awt.Dimension(350, 0));
        eastPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(
            javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.LIGHT_GRAY, java.awt.Color.GRAY), 
            "Real-time Monitoring", 
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
            javax.swing.border.TitledBorder.DEFAULT_POSITION, 
            new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
            new Color(153, 0, 153)
        ));
        eastPanel.setBackground(new java.awt.Color(255, 250, 255));
        
        // Top section: Real-time data displays (4 panels)
        jPanel13.setLayout(new java.awt.GridLayout(4, 1, 5, 8));
        jPanel13.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 5, 10));
        jPanel13.setBackground(new java.awt.Color(255, 250, 255));
        
        // Bottom section: Alarm and event panels (2 panels)  
        jPanel14.setLayout(new java.awt.GridLayout(2, 1, 5, 8));
        jPanel14.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 10, 10));
        jPanel14.setBackground(new java.awt.Color(255, 250, 255));
        
        eastPanel.add(jPanel13, BorderLayout.CENTER);
        eastPanel.add(jPanel14, BorderLayout.SOUTH);
    }
    
    /**
     * Status Bar (Bottom - jPanel15)
     * Purpose: Connection status and system information display
     * Left: Connection status, Data status, Alarm status
     * Right: Version information, System ready status
     * Reference: EMS_Screen_Configuration_Analysis_Consolidated.md Section 2.5
     */
    private void setupStatusBar() {
        jPanel15.setLayout(new BorderLayout(10, 0));
        jPanel15.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createEtchedBorder(),
            javax.swing.BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        jPanel15.setBackground(new java.awt.Color(245, 245, 250));
        jPanel15.setPreferredSize(new java.awt.Dimension(0, 35));
        
        // Left side: Connection and data status
        javax.swing.JPanel statusLeft = new javax.swing.JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        statusLeft.setBackground(new java.awt.Color(245, 245, 250));
        
        javax.swing.JLabel connectionStatus = new javax.swing.JLabel("🔌 Connected");
        connectionStatus.setFont(new java.awt.Font("Dialog", 0, 11));
        connectionStatus.setForeground(new java.awt.Color(0, 153, 51));
        
        javax.swing.JLabel separator1 = new javax.swing.JLabel("  |  ");
        separator1.setForeground(java.awt.Color.GRAY);
        
        javax.swing.JLabel dataStatus = new javax.swing.JLabel("📊 Data Normal");
        dataStatus.setFont(new java.awt.Font("Dialog", 0, 11));
        dataStatus.setForeground(new java.awt.Color(0, 102, 204));
        
        javax.swing.JLabel separator2 = new javax.swing.JLabel("  |  ");
        separator2.setForeground(java.awt.Color.GRAY);
        
        javax.swing.JLabel alarmStatus = new javax.swing.JLabel("⚠️ No Alarms");
        alarmStatus.setFont(new java.awt.Font("Dialog", 0, 11));
        alarmStatus.setForeground(new java.awt.Color(102, 102, 102));
        
        statusLeft.add(connectionStatus);
        statusLeft.add(separator1);
        statusLeft.add(dataStatus);
        statusLeft.add(separator2);
        statusLeft.add(alarmStatus);
        
        // Right side: Version and ready status
        javax.swing.JPanel statusRight = new javax.swing.JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        statusRight.setBackground(new java.awt.Color(245, 245, 250));
        
        javax.swing.JLabel versionLabel = new javax.swing.JLabel("EMS v2.0  |  System Ready");
        versionLabel.setFont(new java.awt.Font("Dialog", 0, 11));
        versionLabel.setForeground(new java.awt.Color(102, 102, 102));
        statusRight.add(versionLabel);
        
        jPanel15.add(statusLeft, BorderLayout.WEST);
        jPanel15.add(statusRight, BorderLayout.EAST);
    }
    
    /**
     * Configure window properties and add event listeners
     */
    private void configureWindow() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Add cleanup logic here if needed
                System.out.println("EMS System shutting down...");
                System.exit(0);
            }
        });
    }

    // Independent execution methods for each panel

    /**
     * Launch the Top Panel independently.
     */
    private static void mainTopPanel(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainForm window = new MainForm();
                    JFrame frame = new JFrame("EMS Top Panel - Header");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.add(window.topPanel);
                    frame.pack();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Launch the Center Panel independently.
     */
    private static void mainCenterPanel(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainForm window = new MainForm();
                    JFrame frame = new JFrame("EMS Center Panel - Tabs");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.add(window.centerPanel);
                    frame.setSize(800, 600);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Launch the East Panel independently.
     */
    private static void mainEastPanel(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainForm window = new MainForm();
                    JFrame frame = new JFrame("EMS East Panel - Monitoring");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.add(window.eastPanel);
                    frame.pack();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Launch the West Panel independently.
     */
    private static void mainWestPanel(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainForm window = new MainForm();
                    JFrame frame = new JFrame("EMS West Panel - Control");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.add(window.jPanel16); // West control panel
                    frame.pack();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Add event to event log with timestamp
     */
    private void addEvent(String event) {
        if (eventText == null) return;
        
        String timestamp = eventTimeFormat.format(new Date());
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
     * Start real-time data simulation for right panel
     */
    private void startDataSimulation() {
        Timer simulationTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSimulatedData();
            }
        });
        simulationTimer.start();
    }
    
    /**
     * Update simulated data values
     */
    private void updateSimulatedData() {
        if (powerValue == null) return; // Panels not initialized yet
        
        try {
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
        } catch (Exception ex) {
            // Silently ignore any update errors
        }
    }
}
