package org.hils;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import org.hils.gui.MainForm;
import org.hils.property.HProperty;
	
/**
 * 
 * @author hyun keun lim since 20230301
 *
 */
public class App {
	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        HProperty.screenH = (int) screenSize.getHeight();
        HProperty.screenW = (int) screenSize.getWidth();
        
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});			

	}

}
