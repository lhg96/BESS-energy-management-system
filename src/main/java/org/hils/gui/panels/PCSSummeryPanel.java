/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hils.gui.panels;

import org.hils.vo.summery.PCSSummery;
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
public class PCSSummeryPanel extends JPanel {
	String[] str = { "PCS Fault", "Refer Value", "Active Power", "DC Voltage", "DC Current", "Fault" };
	JButton 	detailBTN;
	
	JLabel labelReferValue; // refer value
	JLabel labelActivePower;// active power
	JLabel labelDcVoltage; 	// dc voltage
	JLabel labelDcCurrent; 	// dc current
	JLabel labelFault; 		// fault count
	
	
	public PCSSummeryPanel() {
		this.setLayout(new GridLayout(6, 2));
		//data load
		//sample data
		
		PCSSummery summery = new PCSSummery(true,0.0, 0.0, 876.7,1.7,1);
		
		JCheckBox 	checkBox = new JCheckBox(str[0], summery.isPcsFault());	
		detailBTN = new JButton("Records");
		labelReferValue =new JLabel(summery.getReferValue()+"", SwingConstants.RIGHT);
		labelActivePower=new JLabel(summery.getActivePower()+"", SwingConstants.RIGHT);
		labelDcVoltage 	=new JLabel(summery.getDcVoltage()+"", SwingConstants.RIGHT);
		labelDcCurrent 	=new JLabel(summery.getDcCurrent()+"", SwingConstants.RIGHT);
		labelFault 		=new JLabel(summery.getFault()+"", SwingConstants.RIGHT);
		
		//gui	
		this.add(checkBox);
		this.add(detailBTN);
		
		this.add(new JLabel(str[1], SwingConstants.RIGHT));
		this.add(labelReferValue);
		this.add(new JLabel(str[2], SwingConstants.RIGHT));
		this.add(labelActivePower);
		this.add(new JLabel(str[3], SwingConstants.RIGHT));
		this.add(labelDcVoltage);
		this.add(new JLabel(str[4], SwingConstants.RIGHT));
		this.add(labelDcCurrent);
		this.add(new JLabel(str[5], SwingConstants.RIGHT));
		this.add(labelFault);			
	}
	
	// Main method for independent execution
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
			javax.swing.JFrame frame = new javax.swing.JFrame("PCS Summary Panel");
			frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
			
			PCSSummeryPanel panel = new PCSSummeryPanel();
			frame.add(panel);
			
			frame.setSize(800, 600);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}
}