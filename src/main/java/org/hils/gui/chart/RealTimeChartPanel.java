package org.hils.gui.chart;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

/**
 * Real-time Chart Panel for EMS Monitoring
 * EMS 실시간 차트 패널
 * 
 * @author hyun keun lim
 */
public class RealTimeChartPanel extends JPanel {
    
    private static final int MAX_DATA_POINTS = 100;
    private static final int CHART_MARGIN = 40;
    
    private List<DataPoint> powerData;
    private List<DataPoint> socData;
    private List<DataPoint> frequencyData;
    
    private Timer updateTimer;
    private DecimalFormat df = new DecimalFormat("#.##");
    
    private JLabel powerLabel;
    private JLabel socLabel;
    private JLabel frequencyLabel;
    
    // Data Point Class
    public static class DataPoint {
        private long timestamp;
        private double value;
        
        public DataPoint(long timestamp, double value) {
            this.timestamp = timestamp;
            this.value = value;
        }
        
        public long getTimestamp() { return timestamp; }
        public double getValue() { return value; }
    }
    
    public RealTimeChartPanel() {
        initializeData();
        setupUI();
        startUpdateTimer();
    }
    
    private void initializeData() {
        powerData = new ArrayList<>();
        socData = new ArrayList<>();
        frequencyData = new ArrayList<>();
        
        // Initialize with some sample data
        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            powerData.add(new DataPoint(currentTime - (20 - i) * 2000, Math.random() * 100));
            socData.add(new DataPoint(currentTime - (20 - i) * 2000, 80 + Math.random() * 20));
            frequencyData.add(new DataPoint(currentTime - (20 - i) * 2000, 59.8 + Math.random() * 0.4));
        }
    }
    
    private void setupUI() {
        setLayout(new BorderLayout());
        setBorder(new TitledBorder(null, "Real-time Monitoring Charts", TitledBorder.LEFT, TitledBorder.TOP,
                  new Font("맑은 고딕", Font.BOLD, 12), Color.BLUE));
        setPreferredSize(new Dimension(400, 300));
        setBackground(Color.WHITE);
        
        // Status labels
        JPanel statusPanel = new JPanel(new java.awt.GridLayout(1, 3));
        statusPanel.setPreferredSize(new Dimension(0, 30));
        
        powerLabel = new JLabel("Power: 0.0 MW", SwingConstants.CENTER);
        powerLabel.setFont(new Font("맑은 고딕", Font.BOLD, 10));
        powerLabel.setForeground(Color.RED);
        
        socLabel = new JLabel("SOC: 0.0%", SwingConstants.CENTER);
        socLabel.setFont(new Font("맑은 고딕", Font.BOLD, 10));
        socLabel.setForeground(Color.BLUE);
        
        frequencyLabel = new JLabel("Freq: 60.0 Hz", SwingConstants.CENTER);
        frequencyLabel.setFont(new Font("맑은 고딕", Font.BOLD, 10));
        frequencyLabel.setForeground(Color.GREEN);
        
        statusPanel.add(powerLabel);
        statusPanel.add(socLabel);
        statusPanel.add(frequencyLabel);
        
        add(statusPanel, BorderLayout.NORTH);
    }
    
    private void startUpdateTimer() {
        updateTimer = new Timer(2000, e -> {
            updateData();
            updateLabels();
            repaint();
        });
        updateTimer.start();
    }
    
    private void updateData() {
        long currentTime = System.currentTimeMillis();
        
        // Add new data points
        powerData.add(new DataPoint(currentTime, Math.random() * 150)); // 0-150 MW
        socData.add(new DataPoint(currentTime, 75 + Math.random() * 25)); // 75-100%
        frequencyData.add(new DataPoint(currentTime, 59.8 + Math.random() * 0.4)); // 59.8-60.2 Hz
        
        // Remove old data points to maintain MAX_DATA_POINTS
        if (powerData.size() > MAX_DATA_POINTS) {
            powerData.remove(0);
        }
        if (socData.size() > MAX_DATA_POINTS) {
            socData.remove(0);
        }
        if (frequencyData.size() > MAX_DATA_POINTS) {
            frequencyData.remove(0);
        }
    }
    
    private void updateLabels() {
        if (!powerData.isEmpty()) {
            double currentPower = powerData.get(powerData.size() - 1).getValue();
            powerLabel.setText("Power: " + df.format(currentPower) + " MW");
        }
        
        if (!socData.isEmpty()) {
            double currentSOC = socData.get(socData.size() - 1).getValue();
            socLabel.setText("SOC: " + df.format(currentSOC) + "%");
        }
        
        if (!frequencyData.isEmpty()) {
            double currentFreq = frequencyData.get(frequencyData.size() - 1).getValue();
            frequencyLabel.setText("Freq: " + df.format(currentFreq) + " Hz");
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int width = getWidth();
        int height = getHeight() - 30; // Account for status panel
        int chartHeight = (height - CHART_MARGIN * 2) / 3; // Three charts
        
        // Draw three separate charts
        drawChart(g2d, powerData, "Power (MW)", Color.RED, 
                 CHART_MARGIN, 30 + CHART_MARGIN, width - CHART_MARGIN * 2, chartHeight, 0, 150);
        
        drawChart(g2d, socData, "SOC (%)", Color.BLUE, 
                 CHART_MARGIN, 30 + CHART_MARGIN + chartHeight + 10, width - CHART_MARGIN * 2, chartHeight, 70, 100);
        
        drawChart(g2d, frequencyData, "Frequency (Hz)", Color.GREEN, 
                 CHART_MARGIN, 30 + CHART_MARGIN + (chartHeight + 10) * 2, width - CHART_MARGIN * 2, chartHeight, 59.5, 60.5);
        
        g2d.dispose();
    }
    
    private void drawChart(Graphics2D g2d, List<DataPoint> data, String title, Color color,
                          int x, int y, int width, int height, double minValue, double maxValue) {
        if (data.isEmpty()) return;
        
        // Draw chart border
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawRect(x, y, width, height);
        
        // Draw title
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("맑은 고딕", Font.BOLD, 10));
        g2d.drawString(title, x + 5, y - 5);
        
        // Draw grid lines
        g2d.setColor(Color.LIGHT_GRAY);
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0);
        g2d.setStroke(dashed);
        
        // Horizontal grid lines
        for (int i = 1; i < 5; i++) {
            int gridY = y + (height * i) / 5;
            g2d.drawLine(x, gridY, x + width, gridY);
        }
        
        // Vertical grid lines
        for (int i = 1; i < 5; i++) {
            int gridX = x + (width * i) / 5;
            g2d.drawLine(gridX, y, gridX, y + height);
        }
        
        // Draw data line
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(2));
        
        if (data.size() > 1) {
            for (int i = 1; i < data.size(); i++) {
                DataPoint prev = data.get(i - 1);
                DataPoint curr = data.get(i);
                
                int x1 = x + (width * (i - 1)) / (data.size() - 1);
                int y1 = y + height - (int)((prev.getValue() - minValue) / (maxValue - minValue) * height);
                int x2 = x + (width * i) / (data.size() - 1);
                int y2 = y + height - (int)((curr.getValue() - minValue) / (maxValue - minValue) * height);
                
                g2d.drawLine(x1, y1, x2, y2);
            }
        }
        
        // Draw current value
        if (!data.isEmpty()) {
            DataPoint current = data.get(data.size() - 1);
            int currentX = x + width - 5;
            int currentY = y + height - (int)((current.getValue() - minValue) / (maxValue - minValue) * height);
            
            g2d.setColor(color);
            g2d.fillOval(currentX - 3, currentY - 3, 6, 6);
            
            // Draw value text
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("맑은 고딕", Font.PLAIN, 9));
            String valueText = df.format(current.getValue());
            int textWidth = g2d.getFontMetrics().stringWidth(valueText);
            g2d.drawString(valueText, currentX - textWidth - 8, currentY + 3);
        }
        
        // Draw min/max labels
        g2d.setColor(Color.DARK_GRAY);
        g2d.setFont(new Font("맑은 고딕", Font.PLAIN, 8));
        g2d.drawString(df.format(maxValue), x - 25, y + 5);
        g2d.drawString(df.format(minValue), x - 25, y + height + 5);
    }
    
    public void addPowerData(double power) {
        powerData.add(new DataPoint(System.currentTimeMillis(), power));
        if (powerData.size() > MAX_DATA_POINTS) {
            powerData.remove(0);
        }
    }
    
    public void addSOCData(double soc) {
        socData.add(new DataPoint(System.currentTimeMillis(), soc));
        if (socData.size() > MAX_DATA_POINTS) {
            socData.remove(0);
        }
    }
    
    public void addFrequencyData(double frequency) {
        frequencyData.add(new DataPoint(System.currentTimeMillis(), frequency));
        if (frequencyData.size() > MAX_DATA_POINTS) {
            frequencyData.remove(0);
        }
    }
    
    public void stopTimer() {
        if (updateTimer != null) {
            updateTimer.stop();
        }
    }
    
    public List<DataPoint> getPowerData() { return new ArrayList<>(powerData); }
    public List<DataPoint> getSOCData() { return new ArrayList<>(socData); }
    public List<DataPoint> getFrequencyData() { return new ArrayList<>(frequencyData); }
}