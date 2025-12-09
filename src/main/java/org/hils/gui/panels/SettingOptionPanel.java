package org.hils.gui.panels;

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
	
	// Main method for independent execution
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
			javax.swing.JFrame frame = new javax.swing.JFrame("Setting Option Panel");
			frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
			
			SettingOptionPanel panel = new SettingOptionPanel();
			frame.add(panel);
			
			frame.setSize(800, 600);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
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

