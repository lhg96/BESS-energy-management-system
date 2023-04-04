package com.hepi.hils.gui.tab;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * control tab panel
 * @author hyun keun lim 
 *
 */
public class TabPanel extends JPanel{	
	public String title;
	
	/**
	//top panel
	//PCS 상태, Battery Status, Control
	//body panel		
	 * gui inital 모양 변경
	*/
	
	public TabPanel(String tab1Title) {
		this.title	= tab1Title;
		
		this.setLayout(new BorderLayout(5,5));
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pcsStatusPanel = new JPanel(new GridLayout(2,6));
		pcsStatusPanel.setBorder(BorderFactory.createTitledBorder("PCS Status"));
		pcsStatusPanel.add(new JLabel("1"));
		pcsStatusPanel.add(new JLabel("2"));
		pcsStatusPanel.add(new JLabel("3"));
		pcsStatusPanel.add(new JLabel("4"));
		pcsStatusPanel.add(new JLabel("5"));
		pcsStatusPanel.add(new JLabel("6"));
		pcsStatusPanel.add(new JLabel("7"));
		pcsStatusPanel.add(new JLabel("8"));
		pcsStatusPanel.add(new JLabel("9"));
		pcsStatusPanel.add(new JLabel("10"));
		pcsStatusPanel.add(new JLabel("11"));
		pcsStatusPanel.add(new JLabel("12"));		
		topPanel.add(pcsStatusPanel);
		
		JPanel batteryStatusPanel = new JPanel(new GridLayout(3,5));
		batteryStatusPanel.setBorder(BorderFactory.createTitledBorder("Battery Status"));
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
		batteryStatusPanel.add(new JLabel("14"));
		topPanel.add(batteryStatusPanel);	
		
		this.add(topPanel, BorderLayout.NORTH);
		
	}
}
