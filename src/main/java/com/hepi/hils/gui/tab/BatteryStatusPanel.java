package com.hepi.hils.gui.tab;

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
		setLayout(new GridLayout(3, 5, 0, 0));
		
		JCheckBox chk0 = new JCheckBox("Warning");
		add(chk0);
		
		JCheckBox chk1 = new JCheckBox("Fault");
		add(chk1);
		
		JCheckBox chk2 = new JCheckBox("Online");
		add(chk2);
		
		JCheckBox chk3 = new JCheckBox("Contact Control");
		add(chk3);
		
		JCheckBox chk4 = new JCheckBox("Idle");
		add(chk4);
		
		JCheckBox chk5 = new JCheckBox("Discharging");
		add(chk5);
		
		JCheckBox chk6 = new JCheckBox("Charging");
		add(chk6);
		
		JCheckBox chk7 = new JCheckBox("Low SOC Alarm");
		add(chk7);
		
		JCheckBox chk8 = new JCheckBox("High SOC Alarm");
		add(chk8);
		
		JCheckBox chk9 = new JCheckBox("Comm Error");
		add(chk9);
		
		JLabel lblNewLabel = new JLabel("Online Racks");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("0 of 14");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("DS Closed Racks");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("0 of 14");
		lblNewLabel_1_1_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNewLabel_1_1_2);
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

}
