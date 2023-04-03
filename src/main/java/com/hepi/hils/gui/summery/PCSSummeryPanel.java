/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hepi.hils.gui.summery;

import com.hepi.hils.vo.summery.PCSSummery;
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
	String[] str = { "PCS Fault", "지령값", "유효전력", "DC전압", "DC전류", "Fault" };
	JButton 	detailBTN = new JButton("...");
	
	JLabel labelReferValue; // 지령값
	JLabel labelActivePower;// 유효전력
	JLabel labelDcVoltage; 	// dc전압
	JLabel labelDcCurrent; 	// dc전류
	JLabel labelFault; 		// 누락 카운트
	
	
	public PCSSummeryPanel() {
		this.setLayout(new GridLayout(6, 2));
		//data load
		//sample data
		PCSSummery summery = new PCSSummery(true,0.0, 0.0, 876.7,1.7,1);
		
		JCheckBox 	checkBox = new JCheckBox(str[0], summery.isPCS_FAULT());	
		
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

}