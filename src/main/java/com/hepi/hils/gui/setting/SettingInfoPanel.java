/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hepi.hils.gui.setting;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author hyun
 */
public class SettingInfoPanel extends JPanel {

    String[] str = {
        "참여율",
        "PCS 정격",
        "PCS 대수",
        "Enable Log"
    };

    GridBagConstraints c;

    public SettingInfoPanel() {
        Border border = BorderFactory.createEtchedBorder();
        this.setBorder(border);
        this.setLayout(new GridLayout(6,2));
        this.add(new JLabel(str[0]));
        this.add(new JTextField(10));
        this.add(new JLabel(str[1]));
        
        Font font = new Font("맑은 고딕", Font.BOLD,12);
        this.add(new JLabel("2 KW"));
        
        JLabel l2 = new JLabel(str[2]);
        this.add(l2);
        this.add(new JLabel("2 Set"));
        this.add(new JCheckBox(str[3]));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        
        /*
        this.setLayout(new GridBagLayout());
        
        
        c = new GridBagConstraints();
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        
        layout(new JLabel(str[0]),0,0,1,1);
        layout(new JTextField(10),1,0,1,1);
        layout(new JLabel(str[1]),2,0,1,1);
        layout(new JLabel("2 KW"),3,0,1,1);
        
        layout(new JLabel(str[2]),0,1,1,1);
        layout(new JLabel(str[3]),1,1,1,1);
        */
        
    }

    public void layout(Component obj, int x, int y, int width, int height) {
        c.gridx = x; // 시작위치 x
        c.gridy = y; // 시작위치 y
        c.gridwidth = width; // 컨테이너 너비
        c.gridheight = height;  // 컨테이너 높이
        add(obj, c);
    }
    
   

}
