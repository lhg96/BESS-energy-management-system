package com.hepi.hils.gui.tab;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.hepi.hils.vo.tab.PCSStatus;


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
		
		this.setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(0, 2, 0, 0));
		//JPanel topPanel = new JPanel(new GridLayout(1,3));
		PCSStatusPanel pcsStatusPanel = new PCSStatusPanel();
		topPanel.add(pcsStatusPanel);
		

		BatteryStatusPanel batteryPanel = new BatteryStatusPanel();
		topPanel.add(batteryPanel);	
		
		this.add(topPanel, BorderLayout.NORTH);
		
	}
	
	
	public void initial() {
		PCSStatus pcsStatus = new PCSStatus(
				PCSStatus.Remote,
				PCSStatus.Ready,
				PCSStatus.Run,
				PCSStatus.Standby,
				PCSStatus.CPMode
		);
	}
}
