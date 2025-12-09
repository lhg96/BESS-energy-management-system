package org.hils.gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class BESSMonitoringUI extends JFrame {

    // --- 상태 표시 ---
    private JLabel[] faultIndicators = new JLabel[8];
    private JLabel[] socLabels = new JLabel[8];
    private JProgressBar socProgressBar;
    private JLabel clockLabel;
    private JTable alarmTable;
    private DefaultTableModel alarmModel;

    // --- 그래프 ---
    private XYSeries freqSeries = new XYSeries("Frequency");

    // --- 제어 ---
    private JTextField powerSetpointField;
    private JComboBox<String> modeCombo;
    private JRadioButton chargeBtn, dischargeBtn, idleBtn;

    public BESSMonitoringUI() {
        setTitle("28MW BESS Control - LPMS HMI#3");
        setSize(1600, 950);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // FlatLaf 다크 테마 적용 (선택적)
        // FlatDarkLaf.setup();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Overview", createOverviewPanel());
        tabbedPane.addTab("Alarms", createAlarmPanel());
        tabbedPane.addTab("Settings", createSettingsPanel());

        add(tabbedPane, BorderLayout.CENTER);
        add(createHeader(), BorderLayout.NORTH);

        startRealTimeUpdates();
        setVisible(true);
    }

    private JPanel createHeader() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(30, 50, 80));
        panel.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        JLabel title = new JLabel("28MW BESS Control", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setForeground(Color.CYAN);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        right.setOpaque(false);

        clockLabel = new JLabel();
        clockLabel.setFont(new Font("Consolas", Font.BOLD, 16));
        clockLabel.setForeground(Color.YELLOW);
        right.add(clockLabel);

        JLabel user = new JLabel("BESSMain ●");
        user.setForeground(Color.GREEN);
        right.add(user);

        JLabel local = new JLabel("LOCAL");
        local.setForeground(Color.ORANGE);
        right.add(local);

        panel.add(title, BorderLayout.CENTER);
        panel.add(right, BorderLayout.EAST);
        return panel;
    }

    private JPanel createOverviewPanel() {
        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(new Color(40, 40, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        // 좌측: PCS 요약 + 제어
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        content.add(createLeftPanel(), gbc);

        // 중앙: PCS 상세 + SOC 바
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.gridwidth = 2; gbc.gridheight = 1;
        gbc.weightx = 1.0; gbc.weighty = 0.7;
        gbc.fill = GridBagConstraints.BOTH;
        content.add(createPCSTableWithSOC(), gbc);

        // 우측: 그래프
        gbc.gridx = 3; gbc.gridy = 0;
        gbc.gridwidth = 1; gbc.gridheight = 2;
        gbc.weightx = 0.8;
        content.add(createGraphPanel(), gbc);

        // JScrollPane으로 감싸기
        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // 최종 wrapper 패널 반환
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(scrollPane, BorderLayout.CENTER);
        return wrapper;
    }

    private JPanel createLeftPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.ORANGE), "PCS Summary & Control",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 12), Color.ORANGE));
        panel.setBackground(new Color(50, 50, 50));

        JPanel summary = new JPanel(new GridBagLayout());
        summary.setBackground(new Color(50, 50, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < 8; i++) {
            gbc.gridx = 0; gbc.gridy = i;
            JLabel name = new JLabel("PCS#" + (i+1));
            name.setForeground(Color.WHITE);
            summary.add(name, gbc);

            gbc.gridx = 1;
            faultIndicators[i] = createLEDLight(Color.GREEN);
            summary.add(faultIndicators[i], gbc);

            gbc.gridx = 2;
            socLabels[i] = new JLabel("50.0%", SwingConstants.RIGHT);
            socLabels[i].setForeground(Color.CYAN);
            socLabels[i].setToolTipText("State of Charge");
            summary.add(socLabels[i], gbc);
        }

        // 제어 패널
        JPanel control = createControlPanel();
        control.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        panel.add(summary, BorderLayout.CENTER);
        panel.add(control, BorderLayout.SOUTH);

        return panel;
    }

    private JLabel createLEDLight(Color color) {
        JLabel led = new JLabel("●");
        led.setFont(new Font("SansSerif", Font.BOLD, 20));
        led.setForeground(color);
        led.setToolTipText(color == Color.GREEN ? "Normal" : color == Color.RED ? "Fault" : "Offline");
        return led;
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(60, 60, 60));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);

        // Mode
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Mode:"), gbc);
        modeCombo = new JComboBox<>(new String[]{"GF", "Peak Shaving", "Smoothing", "Volt Control"});
        modeCombo.setSelectedIndex(0);
        gbc.gridx = 1;
        panel.add(modeCombo, gbc);

        // Radio buttons
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Operation:"), gbc);

        chargeBtn = new JRadioButton("Charge");
        dischargeBtn = new JRadioButton("Discharge");
        idleBtn = new JRadioButton("Idle", true);
        ButtonGroup group = new ButtonGroup();
        group.add(chargeBtn); group.add(dischargeBtn); group.add(idleBtn);

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        radioPanel.setOpaque(false);
        radioPanel.add(chargeBtn); radioPanel.add(dischargeBtn); radioPanel.add(idleBtn);
        gbc.gridx = 1;
        panel.add(radioPanel, gbc);

        // Power Setpoint
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Setpoint (kW):"), gbc);
        powerSetpointField = new JTextField("1.0", 6);
        gbc.gridx = 1;
        panel.add(powerSetpointField, gbc);

        // Buttons
        JButton startBtn = new JButton("START");
        startBtn.setBackground(new Color(0, 150, 0));
        startBtn.setForeground(Color.WHITE);
        JButton stopBtn = new JButton("STOP");
        stopBtn.setBackground(Color.RED);
        stopBtn.setForeground(Color.WHITE);
        JButton resetBtn = new JButton("RESET");
        resetBtn.setBackground(Color.ORANGE);
        resetBtn.setForeground(Color.WHITE);

        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.setOpaque(false);
        btnPanel.add(startBtn); btnPanel.add(stopBtn); btnPanel.add(resetBtn);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(btnPanel, gbc);

        return panel;
    }

    private JPanel createPCSTableWithSOC() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.CYAN), "PCS Detailed Status",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 12), Color.CYAN));

        // 테이블
        String[] columns = {"", "PCS#1", "PCS#2", "PCS#3", "PCS#4"};
        String[][] data = {
                {"Remote/Local", "Remote", "Remote", "Ready", "Run"},
                {"Status", "Run", "Run", "none", "Run"},
                {"GRID", "445.5 V", "444.2 V", "445.0 V", "445.7 V"},
                {"AC", "192 A", "188 A", "0 A", "190 A"},
                {"DC", "801.7 V", "800.3 V", "0 V", "802.1 V"},
                {"PCS", "0.0 kW", "0.0 kW", "0.0 kW", "0.0 kW"}
        };

        JTable table = new JTable(data, columns);
        table.setEnabled(false);
        table.getTableHeader().setBackground(new Color(70, 70, 70));
        table.getTableHeader().setForeground(Color.YELLOW);
        table.setRowHeight(24);
        table.setFont(new Font("Consolas", Font.PLAIN, 12));

        JScrollPane scroll = new JScrollPane(table);
        panel.add(scroll, BorderLayout.CENTER);

        // SOC Progress Bar
        JPanel socPanel = new JPanel(new BorderLayout());
        socPanel.setBorder(BorderFactory.createTitledBorder("Total SOC"));
        socProgressBar = new JProgressBar(0, 100);
        socProgressBar.setValue(50);
        socProgressBar.setStringPainted(true);
        socProgressBar.setForeground(Color.GREEN);
        socProgressBar.setBackground(Color.DARK_GRAY);
        socProgressBar.setFont(new Font("SansSerif", Font.BOLD, 16));
        socPanel.add(socProgressBar, BorderLayout.CENTER);
        panel.add(socPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createGraphPanel() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(freqSeries);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Frequency & Wind Power", "Time", "Value", dataset,
                PlotOrientation.VERTICAL, true, true, false);
        chart.setBackgroundPaint(new Color(40, 40, 40));
        chart.getTitle().setPaint(Color.WHITE);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(new Color(40, 40, 40));

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GREEN), "Real-time Monitoring",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 12), Color.GREEN));
        panel.add(chartPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createAlarmPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Alarm History"));

        String[] cols = {"Time", "Type", "PCS", "Message"};
        alarmModel = new DefaultTableModel(cols, 0);
        alarmTable = new JTable(alarmModel);
        alarmTable.setRowHeight(22);
        JScrollPane scroll = new JScrollPane(alarmTable);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createSettingsPanel() {
        return new JPanel() {{
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            add(new JLabel("Settings Panel - To be implemented"));
            add(Box.createVerticalStrut(20));
            add(new JButton("Save Configuration"));
        }};
    }

    // --- 실시간 업데이트 ---
    private void startRealTimeUpdates() {
        Random rand = new Random();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        // 커스텀 색상 정의
        final Color DARK_RED = new Color(100, 0, 0);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int sec = 0;
            @Override
            public void run() {
                clockLabel.setText(sdf.format(new Date()));

                double totalSOC = 0;
                for (int i = 0; i < 8; i++) {
                    double soc = 30 + rand.nextDouble() * 50;
                    socLabels[i].setText(String.format("%.1f%%", soc));
                    totalSOC += soc;

                    // LED 깜빡임 (Fault 시 빨강/어두운 빨강 교차)
                    Color c = (i == 2) ? (sec % 2 == 0 ? Color.RED : DARK_RED) :
                            (i == 4) ? Color.GRAY : Color.GREEN;
                    faultIndicators[i].setForeground(c);
                }
                socProgressBar.setValue((int)(totalSOC / 8));

                // 그래프 업데이트
                double freq = 59.8 + rand.nextDouble() * 0.4;
                freqSeries.addOrUpdate(sec, freq);
                if (freqSeries.getItemCount() > 60) freqSeries.remove(0);

                // 알람 추가
                if (rand.nextInt(20) == 0) {
                    alarmModel.insertRow(0, new Object[]{
                        sdf.format(new Date()), "Fault", "PCS#3", "Over Temperature"
                    });
                }

                sec++;
            }
        }, 0, 1000);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BESSMonitoringUI::new);
    }
}