/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hils.gui.panels;

import org.hils.vo.summery.BMSSummery;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author user_ai
 */
public class BMSummeryPanel extends JPanel {
	String[] str = { "BMS Fault", "SOC", "SOH", "DC Voltage", "DC Current", "HB" };	
	JButton detailBTN;
	
	JLabel labelSoc;
	JLabel labelSoh;
	JLabel labelDcVoltage; // dc voltage
	JLabel labelDcCurrent; // dc current
	JLabel labelBMS_HB; // high voltage
	
	
	public BMSummeryPanel() {
		this.setLayout(new GridLayout(6, 2));
		//data load		
		
		BMSSummery summery = new BMSSummery(false,45.5, 100.0, 873.3,0.0,3);
		
		JCheckBox 	checkBox = new JCheckBox(str[0], summery.isBmsFault());
		detailBTN		= new JButton("Records");
		labelSoc 		=new JLabel(summery.getSoc()+"", SwingConstants.RIGHT);
		labelSoh		=new JLabel(summery.getSoh()+"", SwingConstants.RIGHT);
		labelDcVoltage 	=new JLabel(summery.getDcVoltage()+"", SwingConstants.RIGHT);
		labelDcCurrent 	=new JLabel(summery.getDcCurrent()+"", SwingConstants.RIGHT);
		labelBMS_HB 	=new JLabel(summery.getBmsHb()+"", SwingConstants.RIGHT);
	
		//gui				
		this.add(checkBox);
		this.add(detailBTN);
		
		this.add(new JLabel(str[1], SwingConstants.RIGHT));
		this.add(labelSoc);
		this.add(new JLabel(str[2], SwingConstants.RIGHT));
		this.add(labelSoh);
		this.add(new JLabel(str[3], SwingConstants.RIGHT));
		this.add(labelDcVoltage);
		this.add(new JLabel(str[4], SwingConstants.RIGHT));
		this.add(labelDcCurrent);
		this.add(new JLabel(str[5], SwingConstants.RIGHT));
		this.add(labelBMS_HB);	
		
	}
	
	// Main method for independent execution
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
			javax.swing.JFrame frame = new javax.swing.JFrame("BMS Summary Panel");
			frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
			
			BMSummeryPanel panel = new BMSummeryPanel();
			frame.add(panel);
			
			frame.setSize(800, 600);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}
}