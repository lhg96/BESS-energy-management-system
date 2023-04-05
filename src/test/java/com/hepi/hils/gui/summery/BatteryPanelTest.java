package com.hepi.hils.gui.summery;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.hepi.hils.gui.MainForm;

import com.hepi.hils.gui.summery.BatterySummeryPanel;
import com.hepi.hils.property.HProperty;

public class BatteryPanelTest {
	
	private void showBattery() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//MainFrame frame = new MainFrame("HEPI HILS Manager");
					JFrame frame = new JFrame();
					//create BatterySummeryPanel
					frame.setLayout(new GridLayout(1, 10));
					frame.add(new BatterySummeryPanel(1,10));
					frame.add(new BatterySummeryPanel(2,50));
					frame.add(new BatterySummeryPanel(3,60));
					frame.add(new BatterySummeryPanel(4,70));
					frame.add(new BatterySummeryPanel(5,80));					
					frame.add(new BatterySummeryPanel(6,90));
					frame.add(new BatterySummeryPanel(7,100));
					
					
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setSize(HProperty.screenW, HProperty.screenH);					
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.setResizable(true);	
					//frame.pack();
					
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
	
	public static void main(String[] args) {		
		BatteryPanelTest app = new BatteryPanelTest();
		app.showBattery();
	}

}
