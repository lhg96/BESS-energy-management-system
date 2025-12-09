package org.hils.gui.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.hils.gui.controls.ControlPanel;
import org.hils.gui.controls.ExternalControlSettingPanel;
import org.hils.gui.controls.PCSControlCommandPanel;


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
		topPanel.setLayout(new GridLayout(0, 3, 0, 0));
		//JPanel topPanel = new JPanel(new GridLayout(1,3));
		PCSStatusPanel pcsStatusPanel = new PCSStatusPanel();
		topPanel.add(pcsStatusPanel);
		

		BatteryStatusPanel batteryPanel = new BatteryStatusPanel();
		topPanel.add(batteryPanel);		
		
		ControlPanel controlPanel = new ControlPanel();
		topPanel.add(controlPanel);
		this.add(topPanel, BorderLayout.NORTH);
		//center panel
		JPanel centerPanel 	= new JPanel(new BorderLayout());
		GridMonitorPanel 						gridPanel 			   = new GridMonitorPanel();
		PCSDetailStausPanel 			pcsPanel 			   = new PCSDetailStausPanel();
		//right
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		PCSControlCommandPanel 			pcsControlCommandPanel = new PCSControlCommandPanel();
		ExternalControlSettingPanel 	externalControlSettingPanel = new ExternalControlSettingPanel();
		rightPanel.add(pcsControlCommandPanel);
		rightPanel.add(externalControlSettingPanel);
		
		
		centerPanel.add(gridPanel, BorderLayout.WEST);
		centerPanel.add(pcsPanel, BorderLayout.CENTER);
		centerPanel.add(rightPanel, BorderLayout.EAST);
		
		this.add(centerPanel,BorderLayout.CENTER);
		
		
		
	}
	
	
	public void initial() {
		// Initialization code
	}
	
	// Main method for independent execution
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
			javax.swing.JFrame frame = new javax.swing.JFrame("Tab Panel");
			frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
			
			TabPanel panel = new TabPanel("Main Tab");
			frame.add(panel);
			
			frame.setSize(1200, 800);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}
}
