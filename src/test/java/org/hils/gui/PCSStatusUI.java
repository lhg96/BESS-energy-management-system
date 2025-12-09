package org.hils.gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PCSStatusUI extends JFrame {

    public PCSStatusUI() {
        setTitle("EMS - PCS Status");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLayout(new BorderLayout());

        // 상단 패널 (상태 표시)
        JPanel topPanel = new JPanel(new GridLayout(2, 6, 10, 10));
        topPanel.setBorder(new TitledBorder("PCS 상태"));
        topPanel.add(createLabelPanel("Remote/Local", "Remote"));
        topPanel.add(createLabelPanel("Ready", "none"));
        topPanel.add(createLabelPanel("Run/Stop", "Run"));
        topPanel.add(createLabelPanel("Standby", "None"));
        topPanel.add(createLabelPanel("CP/CV Mode", "CP"));
        topPanel.add(createLabelPanel("Status", "Online"));

        add(topPanel, BorderLayout.NORTH);

        // 중앙 좌측 (GRID → PCS → DC 배터리)
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));

        // 좌측: 전력 흐름
        JPanel gridPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        gridPanel.setBorder(new TitledBorder("GRID / PCS / DC"));
        gridPanel.add(new JLabel("AC 전압"));
        gridPanel.add(new JLabel("448.5 V"));
        gridPanel.add(new JLabel("전류"));
        gridPanel.add(new JLabel("19.2 A"));
        gridPanel.add(new JLabel("전력"));
        gridPanel.add(new JLabel("0.0 kW"));
        gridPanel.add(new JLabel("DC 전압"));
        gridPanel.add(new JLabel("876.7 V"));
        gridPanel.add(new JLabel("전류"));
        gridPanel.add(new JLabel("1.7 A"));
        gridPanel.add(new JLabel("전력"));
        gridPanel.add(new JLabel("1.5 kW"));

        // 우측: 배터리 SOC
        JPanel batteryPanel = new JPanel(new BorderLayout());
        batteryPanel.setBorder(new TitledBorder("Battery"));

        JLabel socLabel = new JLabel("SOC: 45.5 %", SwingConstants.CENTER);
        socLabel.setFont(new Font("Arial", Font.BOLD, 20));
        socLabel.setOpaque(true);
        socLabel.setBackground(Color.GREEN);
        socLabel.setForeground(Color.BLACK);
        batteryPanel.add(socLabel, BorderLayout.CENTER);

        centerPanel.add(gridPanel);
        centerPanel.add(batteryPanel);
        add(centerPanel, BorderLayout.CENTER);

        // 우측 패널: PCS 상세 상태
        JPanel rightPanel = new JPanel(new GridLayout(12, 2, 5, 5));
        rightPanel.setBorder(new TitledBorder("PCS Status"));
        rightPanel.add(new JLabel("AC 저압단 U-V"));
        rightPanel.add(new JLabel("448.5 V"));
        rightPanel.add(new JLabel("AC 저압단 V-W"));
        rightPanel.add(new JLabel("432.7 V"));
        rightPanel.add(new JLabel("AC 저압단 W-U"));
        rightPanel.add(new JLabel("431.4 V"));
        rightPanel.add(new JLabel("PCS 효율"));
        rightPanel.add(new JLabel("0.00 %"));
        rightPanel.add(new JLabel("PCS 출력 가능 유효전력"));
        rightPanel.add(new JLabel("1000.0 kW"));
        rightPanel.add(new JLabel("PCS 출력 가능 무효전력"));
        rightPanel.add(new JLabel("1000.0 kVar"));

        add(rightPanel, BorderLayout.EAST);

        // 하단 패널 (제어 버튼)
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBorder(new TitledBorder("PCS Control Command Status"));
        JButton runBtn = new JButton("Run");
        JButton stopBtn = new JButton("Stop");
        JButton emgBtn = new JButton("Emergency Stop");

        bottomPanel.add(runBtn);
        bottomPanel.add(stopBtn);
        bottomPanel.add(emgBtn);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createLabelPanel(String title, String value) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(valueLabel, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PCSStatusUI ui = new PCSStatusUI();
            ui.setVisible(true);
        });
    }
}
