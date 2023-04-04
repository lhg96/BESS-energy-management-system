package com.hepi.hils.gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

import com.hepi.hils.gui.setting.SettingInfoPanel;
import com.hepi.hils.gui.setting.SettingOptionPanel;
import com.hepi.hils.gui.summery.PCSBMSSummery;
import com.hepi.hils.gui.tab.TabPanel;
import com.hepi.hils.property.HProperty;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.BoxLayout;

/**
 * main gui
 * @author hyun  keun lim since 2023 03 01
 *
 */
public class MainForm {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	public JFrame frame;
	
	private javax.swing.JPanel centerPanel;
    private javax.swing.JPanel eastPanel;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane tabbedPanel;
    private javax.swing.JPanel middlePanel;
    private javax.swing.JPanel summeryPanel;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel topPanel;
	//custom code
    public PCSBMSSummery pcsbmsSummery1;
	public PCSBMSSummery pcsbmsSummery2;

	/**
	 * Create the application.
	 */
	public MainForm() {
		initialize();
		//custom
		initGUI();
	}

	/**
	* custom code 
	*/
	private void initGUI() {
		//title bar
        Image logoImage = null;
        int imageH = 0;
        int imageW = 0;
        try {
            logoImage = ImageIO.read(new File("res/logo.png"));
            imageW = logoImage.getWidth(null);
            imageH = logoImage.getHeight(null);
            iconLabel.setSize(imageW, imageH);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        iconLabel.setIcon(new ImageIcon(logoImage));
        //topPanel.setSize(HProperty.screenW, imageH+5);
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timeSTR = df.format(new Date());
                timeLabel.setText(timeSTR);
            }
        }).start();
        titleLabel.setText(HProperty.supplyPower + "MW BESS Control");
        //----------------summery---------------------------------------
        //mfix how many group        
        pcsbmsSummery1 = new PCSBMSSummery(1,10,100, 200);
        pcsbmsSummery2 = new PCSBMSSummery(2,30,100, 200);
        
        summeryPanel.add(pcsbmsSummery1);
        summeryPanel.add(pcsbmsSummery2);
        FlowLayout fl_middlePanel = new FlowLayout(FlowLayout.LEFT, 5, 5);
        middlePanel.setLayout(fl_middlePanel);
        
        SettingOptionPanel settingOptionPanel = new SettingOptionPanel();
        middlePanel.add(settingOptionPanel);
        SettingInfoPanel settingInfoPanel = new SettingInfoPanel();
        middlePanel.add(settingInfoPanel);
        
        String tab1Title = "PCS/BMS #1"; 
        TabPanel tab1 = new TabPanel(tab1Title);
        tabbedPanel.addTab(tab1Title, tab1);
        
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 990, 663);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		topPanel = new javax.swing.JPanel();
        iconLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        centerPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        summeryPanel = new javax.swing.JPanel();
        middlePanel = new javax.swing.JPanel();
        tabbedPanel = new javax.swing.JTabbedPane();
        eastPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();

        topPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        topPanel.setMinimumSize(new java.awt.Dimension(616, 50));
        topPanel.setPreferredSize(new java.awt.Dimension(500, 80));
        topPanel.setLayout(new java.awt.GridLayout(1, 0));

        iconLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        iconLabel.setToolTipText("");
        iconLabel.setPreferredSize(new java.awt.Dimension(50, 60));
        topPanel.add(iconLabel);

        titleLabel.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("MW BESS Control");
        topPanel.add(titleLabel);

        timeLabel.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        timeLabel.setText("23/03/27 12:00:00");
        topPanel.add(timeLabel);

        frame.getContentPane().add(topPanel, java.awt.BorderLayout.NORTH);

        centerPanel.setPreferredSize(new java.awt.Dimension(300, 500));
        centerPanel.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        summeryPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("PCS/BMS Summery"));
        summeryPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel1.add(summeryPanel);

        middlePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("BESS Operation Settings"));
        jPanel1.add(middlePanel);

        centerPanel.add(jPanel1, java.awt.BorderLayout.NORTH);

        centerPanel.add(tabbedPanel, java.awt.BorderLayout.CENTER);
        tabbedPanel.getAccessibleContext().setAccessibleName("");

        frame.getContentPane().add(centerPanel, java.awt.BorderLayout.CENTER);

        eastPanel.setPreferredSize(new java.awt.Dimension(300, 500));
        eastPanel.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(300, 200));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Frequency&Power"));
        jPanel4.setPreferredSize(new java.awt.Dimension(300, 200));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel15.setPreferredSize(new java.awt.Dimension(100, 200));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 176, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel15, java.awt.BorderLayout.WEST);

        jPanel16.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 176, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel16, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel4, java.awt.BorderLayout.NORTH);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(302, 600));

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("SOC&Set P"));
        jPanel12.setPreferredSize(new java.awt.Dimension(300, 600));
        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.Y_AXIS));

        jPanel13.setPreferredSize(new java.awt.Dimension(300, 150));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );

        jPanel12.add(jPanel13);

        jPanel14.setPreferredSize(new java.awt.Dimension(300, 150));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );

        jPanel12.add(jPanel14);

        jScrollPane1.setViewportView(jPanel12);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        eastPanel.add(jPanel3, java.awt.BorderLayout.CENTER);

        frame.getContentPane().add(eastPanel, java.awt.BorderLayout.EAST);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        //frame.pack();		
        frame.setVisible(true);
        frame.setResizable(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
	}

}
