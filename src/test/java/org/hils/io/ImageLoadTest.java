/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hils.io;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author user_ai
 */
public class ImageLoadTest {

    public void loadImage() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame("Sample");
                    try {
                        Image logoImage = ImageIO.read(new File("res/logo.png"));
                        JLabel label = new JLabel(new ImageIcon(logoImage));
                        System.out.println(logoImage.toString());
                        frame.add(label);
                    } catch (IOException ex) {
                        Logger.getLogger(ImageLoadTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    frame.pack();
                    frame.setVisible(true);
                    
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
        ImageLoadTest app = new ImageLoadTest();
        app.loadImage();

    }
}
