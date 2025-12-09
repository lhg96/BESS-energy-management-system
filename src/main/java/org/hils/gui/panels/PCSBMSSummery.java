/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hils.gui.panels;

import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author user_ai
 */
public class PCSBMSSummery extends JPanel {
    public PCSBMSSummery(int batteryID, int soc, int w, int h) {
        Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        this.setBorder(border);
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);

        BatterySummeryPanel battery = new BatterySummeryPanel(batteryID, soc, w, h);
        this.add(battery);
        
        PCSSummeryPanel pcs = new PCSSummeryPanel();
        this.add(pcs);
        BMSummeryPanel bms = new BMSummeryPanel();
        this.add(bms);

    }
    
    // Main method for independent execution
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            javax.swing.JFrame frame = new javax.swing.JFrame("PCS BMS Summary");
            frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            
            PCSBMSSummery panel = new PCSBMSSummery(1, 75, 100, 200);
            frame.add(panel);
            
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
