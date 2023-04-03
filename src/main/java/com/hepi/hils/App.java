package com.hepi.hils;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.hepi.hils.gui.MainFrame;
import com.hepi.hils.property.HProperty;
	
public class App {

	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        HProperty.screenH = (int) screenSize.getHeight();
        HProperty.screenW = (int) screenSize.getWidth();
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    //frame.setSize(HProperty.screenW, HProperty.screenH);
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
                    //frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    frame.setResizable(true);

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
