package com.hils.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;

public class MainGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 838, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel topPanel = new JPanel();
		topPanel.setBorder(null);
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		FlowLayout fl_centerPanel = new FlowLayout(FlowLayout.LEFT, 5, 5);
		centerPanel.setLayout(fl_centerPanel);
		
		JPanel summeryPanel = new JPanel();
		summeryPanel.setBorder(new TitledBorder(null, "PCS/BMS Summery", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		centerPanel.add(summeryPanel);
		
		JPanel operationSettingPanel = new JPanel();
		operationSettingPanel.setBorder(new TitledBorder(null, "BESS Operation Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		centerPanel.add(operationSettingPanel);
	}

}
