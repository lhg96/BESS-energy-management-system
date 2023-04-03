package com.hepi.hils.gui.summery;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.hepi.hils.gui.MainFrame;
import java.awt.FlowLayout;
import javax.swing.JPanel;

public class SummeryPanelTest {

	public static void main(String[] args) {
		SummeryPanelTest app = new SummeryPanelTest();
		app.showSummery();

	}

	private void showSummery() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame("HEPI HILS Manager");
					//create BatterySummeryPanel
					frame.setLayout(new BorderLayout());
                                        
                                        JPanel summeryPanel = new JPanel(new FlowLayout());
                                        
                                        PCSBMSSummery pcsbmsSummery1 = new PCSBMSSummery(1,10,100, 200);
                                        PCSBMSSummery pcsbmsSummery2 = new PCSBMSSummery(2,30,100, 200);
                                        summeryPanel.add(pcsbmsSummery1);
                                        summeryPanel.add(pcsbmsSummery2);
                                                
					frame.add(summeryPanel, BorderLayout.CENTER);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					//frame.setSize(frame.screenW, frame.screenH);					
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
