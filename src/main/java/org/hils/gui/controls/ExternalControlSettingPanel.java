package org.hils.gui.controls;

import javax.swing.JPanel;

public class ExternalControlSettingPanel extends JPanel {

	// Main method for independent execution
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
			javax.swing.JFrame frame = new javax.swing.JFrame("External Control Setting Panel");
			frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
			
			ExternalControlSettingPanel panel = new ExternalControlSettingPanel();
			frame.add(panel);
			
			frame.setSize(800, 600);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}
}
