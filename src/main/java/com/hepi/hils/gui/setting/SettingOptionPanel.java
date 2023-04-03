package com.hepi.hils.gui.setting;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

/**
 * second setting panel 
 * @author hyunkeun
 *
 */
public class SettingOptionPanel extends JPanel {	
	private String label = "운전모드";
        
	public SettingOptionPanel() {
		//multiple radio box 
		//https://stackoverflow.com/questions/18651253/get-selected-radio-button-list-in-swing
		//Border border = BorderFactory.createTitledBorder();
                Border border = BorderFactory.createEtchedBorder();
		this.setBorder(border);
		this.setLayout(new BorderLayout());
		this.add(new JLabel(label), BorderLayout.WEST);
		
		Operations oper = new Operations();
		this.add(oper,BorderLayout.CENTER);
		
	}	
}

class Operations extends JPanel {
	String[] mode = {
		"Idle",
		"Arbitrage",
		"Wind Shift",
		"Ramp Limit",
		"External(Manual)",
		"OPT",
		"G/F", //grid following 주파수 추종
		"Peak Sharving",
		"Smoothing",
		"Capacity Firming",
		"External(Profile)"
	};
	public Operations() {
		//this.setLayout(new FlowLayout());
		this.setLayout(new GridLayout(6, 2));
		
		ButtonGroup group = new ButtonGroup();
		for(int i=0;i<mode.length;i++) {
			JRadioButton button = new JRadioButton(mode[i]);
			group.add(button);
			this.add(button);
		}		
	}	
}

class SettingStatus extends JPanel{
	String[] labels = {
			"참여율",
			"PCS 대수",
			"PCS 정격",
			"Enable Log"
	};
	
	public SettingStatus() {
		
	}
	
}