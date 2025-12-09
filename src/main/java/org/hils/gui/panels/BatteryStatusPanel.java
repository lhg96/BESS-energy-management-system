package org.hils.gui.panels;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Color;

public class BatteryStatusPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public BatteryStatusPanel() {
		initGUI();		
	}
	
	public void initGUI() {
		setBorder(new TitledBorder(null, "Battery Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));		
		setLayout(new java.awt.BorderLayout());
		
		// Create SOC display panel at the top
		JPanel socPanel = createSOCDisplayPanel();
		add(socPanel, java.awt.BorderLayout.NORTH);
		
		// Create status checkboxes panel
		JPanel statusPanel = new JPanel(new GridLayout(3, 5, 2, 2));
		
		JCheckBox chk0 = new JCheckBox("Warning");
		statusPanel.add(chk0);
		
		JCheckBox chk1 = new JCheckBox("Fault");
		statusPanel.add(chk1);
		
		JCheckBox chk2 = new JCheckBox("Online");
		statusPanel.add(chk2);
		
		JCheckBox chk3 = new JCheckBox("Contact Control");
		statusPanel.add(chk3);
		
		JCheckBox chk4 = new JCheckBox("Idle");
		statusPanel.add(chk4);
		
		JCheckBox chk5 = new JCheckBox("Discharging");
		statusPanel.add(chk5);
		
		JCheckBox chk6 = new JCheckBox("Charging");
		statusPanel.add(chk6);
		
		JCheckBox chk7 = new JCheckBox("Low SOC Alarm");
		statusPanel.add(chk7);
		
		JCheckBox chk8 = new JCheckBox("High SOC Alarm");
		statusPanel.add(chk8);
		
		JCheckBox chk9 = new JCheckBox("Comm Error");
		statusPanel.add(chk9);
		
		JLabel lblNewLabel = new JLabel("Online Racks");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("0 of 14");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		statusPanel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("DS Closed Racks");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		statusPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("0 of 14");
		lblNewLabel_1_1_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		statusPanel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		statusPanel.add(lblNewLabel_1_1_2);
		
		// Add the status panel to the main panel
		add(statusPanel, java.awt.BorderLayout.CENTER);
		/*
		batteryStatusPanel.add(new JCheckBox("1"));
		batteryStatusPanel.add(new JCheckBox("2"));
		batteryStatusPanel.add(new JCheckBox("3"));
		batteryStatusPanel.add(new JCheckBox("4"));
		batteryStatusPanel.add(new JCheckBox("5"));
		batteryStatusPanel.add(new JCheckBox("6"));
		batteryStatusPanel.add(new JCheckBox("7"));
		batteryStatusPanel.add(new JCheckBox("8"));
		batteryStatusPanel.add(new JCheckBox("9"));
		batteryStatusPanel.add(new JCheckBox("10"));
		batteryStatusPanel.add(new JLabel("11"));
		batteryStatusPanel.add(new JLabel("12"));
		batteryStatusPanel.add(new JLabel("13"));
		batteryStatusPanel.add(new JLabel("14"));*/
	}
	
	/**
	 * Create SOC display panel with battery pack information
	 */
	private JPanel createSOCDisplayPanel() {
		JPanel socPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 5));
		socPanel.setBorder(BorderFactory.createTitledBorder("Battery Pack SOC"));
		socPanel.setPreferredSize(new java.awt.Dimension(0, 100));
		
		// Create battery visual indicators for 4 packs
		for (int i = 1; i <= 4; i++) {
			JPanel packPanel = new JPanel(new java.awt.BorderLayout());
			packPanel.setBorder(BorderFactory.createEtchedBorder());
			packPanel.setPreferredSize(new java.awt.Dimension(80, 80));
			
			// Battery visual using existing BatterySummeryPanel
			BatterySummeryPanel batteryVisual = 
				new BatterySummeryPanel(i, 80 + (i * 2), 40, 50);
			
			// SOC information
			JPanel infoPanel = new JPanel(new java.awt.GridLayout(2, 1));
			JLabel socLabel = new JLabel("SOC:" + (80 + i*2) + "%");
			socLabel.setFont(new java.awt.Font("맑은 고딕", java.awt.Font.BOLD, 10));
			socLabel.setHorizontalAlignment(SwingConstants.CENTER);
			socLabel.setForeground(getSOCColor(80 + i*2));
			
			JLabel statusLabel = new JLabel("NORMAL");
			statusLabel.setFont(new java.awt.Font("맑은 고딕", java.awt.Font.PLAIN, 9));
			statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
			statusLabel.setForeground(Color.GREEN);
			
			infoPanel.add(socLabel);
			infoPanel.add(statusLabel);
			
			packPanel.add(batteryVisual, java.awt.BorderLayout.WEST);
			packPanel.add(infoPanel, java.awt.BorderLayout.CENTER);
			
			socPanel.add(packPanel);
		}
		
		return socPanel;
	}
	
	/**
	 * Get color based on SOC level
	 */
	private Color getSOCColor(double soc) {
		if (soc >= 80) return Color.GREEN;
		else if (soc >= 50) return Color.ORANGE;
		else if (soc >= 20) return new Color(255, 165, 0);
		else return Color.RED;
	}
	
	// Main method for independent execution
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
			javax.swing.JFrame frame = new javax.swing.JFrame("Battery Status Panel");
			frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
			
			BatteryStatusPanel panel = new BatteryStatusPanel();
			frame.add(panel);
			
			frame.setSize(800, 600);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}
}
