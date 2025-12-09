package org.hils.gui.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class PCSStatusPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	/**
	 * Create the panel.
	 */
	public PCSStatusPanel() {
		setBorder(new TitledBorder(null, "PCS Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridLayout gridLayout = new GridLayout(3,6);
		gridLayout.setVgap(2);
		gridLayout.setHgap(2);
		//gridLayout.setVgap(5);
		//gridLayout.setHgap(5);
		this.setLayout(gridLayout);
		
		JLabel lblNewLabel_1 = new JLabel("Remote/Local");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNewLabel_1);
		
		textField = new JTextField();		
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		add(textField);
		textField.setColumns(5);
		
		JLabel lblNewLabel_2 = new JLabel("Ready");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setColumns(5);
		add(textField_1);
		
		JLabel lblNewLabel_3 = new JLabel("Run/Stop");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setColumns(5);
		add(textField_2);
		
		JLabel lblNewLabel = new JLabel("Standby");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNewLabel, BorderLayout.NORTH);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setColumns(5);
		add(textField_3);
		
		JLabel lblCpcvMode = new JLabel("CP/CV mode");
		lblCpcvMode.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCpcvMode);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setColumns(5);
		add(textField_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("");
		add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("");
		add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("");
		add(lblNewLabel_4_1_1_1);
		
		JLabel lblNewLabel_4_1_1_1_1 = new JLabel("");
		add(lblNewLabel_4_1_1_1_1);
		
		JLabel lblNewLabel_4_1_1_1_1_1 = new JLabel("");
		add(lblNewLabel_4_1_1_1_1_1);
		
		
		add(new JLabel(""));
	}
	
	// Main method for independent execution
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
			javax.swing.JFrame frame = new javax.swing.JFrame("PCS Status Panel");
			frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
			
			PCSStatusPanel panel = new PCSStatusPanel();
			frame.add(panel);
			
			frame.setSize(800, 600);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}
}
