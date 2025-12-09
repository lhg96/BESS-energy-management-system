package org.hils.gui.controls;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class ControlPanel extends JPanel {
	public ControlPanel() {
		setBorder(new TitledBorder(null, "Control", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(3, 2, 2, 2));
		
		JCheckBox chckbxTotalPcsComm = new JCheckBox("Total PCS Comm Error");
		add(chckbxTotalPcsComm);
		
		JButton btnNewButton = new JButton("PCS Control Screen");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton.setIcon(new ImageIcon("res\\low-battery1.png"));
		add(btnNewButton);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Total BMS Comm Error");
		add(chckbxNewCheckBox);
		
		JButton btnBmsControlScreen = new JButton("BMS Control Screen");
		btnBmsControlScreen.setHorizontalAlignment(SwingConstants.LEADING);
		btnBmsControlScreen.setIcon(new ImageIcon("res\\electric.PNG"));
		btnBmsControlScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnBmsControlScreen);
	}
	
	// Main method for independent execution
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
			javax.swing.JFrame frame = new javax.swing.JFrame("Control Panel");
			frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
			
			ControlPanel panel = new ControlPanel();
			frame.add(panel);
			
			frame.setSize(800, 600);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}
}

