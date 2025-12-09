package org.hils.gui.setting;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.hils.gui.panels.SettingInfoPanel;
import org.hils.property.HProperty;

public class SettingPanelTest {
	int w = 500;
	int h = 200;
	
	public static void main(String[] args) {
		SettingPanelTest app = new SettingPanelTest();
		app.showSettings();
	}

	private void showSettings() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					// create BatterySummeryPanel
					frame.setLayout(new BorderLayout());
					frame.setPreferredSize(new Dimension(w, h));;
					//frame.add(new SettingOptionPanel(), BorderLayout.CENTER);
					frame.add(new SettingInfoPanel(), BorderLayout.CENTER);

					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setSize(HProperty.screenW, HProperty.screenH);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.setResizable(true);
					frame.pack();

					frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							System.exit(0);
						}
					});

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
