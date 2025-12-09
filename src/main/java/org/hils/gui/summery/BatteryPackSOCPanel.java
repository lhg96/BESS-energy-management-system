package org.hils.gui.summery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.JFrame;

import org.hils.gui.panels.BatterySummeryPanel;

/**
 * Enhanced Battery Pack SOC Display Panel with multiple battery packs
 * 배터리 팩 SOC 상세 표시 패널
 * 
 * @author hyun keun lim
 */
public class BatteryPackSOCPanel extends JPanel {
    
    private List<BatteryPackInfo> batteryPacks;
    private JPanel packsContainer;
    private JLabel totalCapacityLabel;
    private JLabel averageSOCLabel;
    private JLabel totalEnergyLabel;
    private JProgressBar systemSOCBar;
    private Timer updateTimer;
    private DecimalFormat df = new DecimalFormat("#.##");
    
    // Battery Pack Information Class
    public static class BatteryPackInfo {
        private int packId;
        private double soc;
        private double capacity; // kWh
        private double voltage;
        private double current;
        private double temperature;
        private boolean isHealthy;
        private String status;
        
        public BatteryPackInfo(int packId, double soc, double capacity) {
            this.packId = packId;
            this.soc = soc;
            this.capacity = capacity;
            this.voltage = 800.0; // Default DC voltage
            this.current = 0.0;
            this.temperature = 25.0;
            this.isHealthy = true;
            this.status = "Normal";
        }
        
        // Getters and setters
        public int getPackId() { return packId; }
        public double getSoc() { return soc; }
        public void setSoc(double soc) { this.soc = soc; }
        public double getCapacity() { return capacity; }
        public double getVoltage() { return voltage; }
        public void setVoltage(double voltage) { this.voltage = voltage; }
        public double getCurrent() { return current; }
        public void setCurrent(double current) { this.current = current; }
        public double getTemperature() { return temperature; }
        public void setTemperature(double temperature) { this.temperature = temperature; }
        public boolean isHealthy() { return isHealthy; }
        public void setHealthy(boolean healthy) { this.isHealthy = healthy; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        
        public double getAvailableEnergy() {
            return (capacity * soc) / 100.0;
        }
    }
    
    public BatteryPackSOCPanel() {
        initializeBatteryPacks();
        setupUI();
        startUpdateTimer();
    }
    
    private void initializeBatteryPacks() {
        batteryPacks = new ArrayList<>();
        // Initialize with 4 battery packs (typical BESS configuration)
        batteryPacks.add(new BatteryPackInfo(1, 85.2, 250.0)); // Pack 1: 250kWh
        batteryPacks.add(new BatteryPackInfo(2, 78.5, 250.0)); // Pack 2: 250kWh  
        batteryPacks.add(new BatteryPackInfo(3, 82.1, 250.0)); // Pack 3: 250kWh
        batteryPacks.add(new BatteryPackInfo(4, 79.8, 250.0)); // Pack 4: 250kWh
    }
    
    private void setupUI() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), 
            "Battery Pack SOC Status", 
            TitledBorder.LEFT, 
            TitledBorder.TOP,
            new Font("맑은 고딕", Font.BOLD, 12)
        ));
        
        // Top summary panel
        JPanel summaryPanel = createSummaryPanel();
        add(summaryPanel, BorderLayout.NORTH);
        
        // Battery packs detail panel
        packsContainer = new JPanel(new GridLayout(0, 2, 5, 5));
        packsContainer.setBorder(BorderFactory.createTitledBorder("Individual Pack Status"));

        for (BatteryPackInfo pack : batteryPacks) {
            JPanel packPanel = createBatteryPackPanel(pack);
            packsContainer.add(packPanel);
        }

        // Wrap pack list in a scroll pane to support many packs
        JScrollPane packsScroll = new JScrollPane(packsContainer);
        packsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        packsScroll.setPreferredSize(new Dimension(0, 260));

        add(packsScroll, BorderLayout.CENTER);
        
        // Control buttons panel
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createSummaryPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 5));
        panel.setBorder(BorderFactory.createTitledBorder("System Summary"));
        panel.setPreferredSize(new Dimension(0, 80));
        
        // Total capacity
        totalCapacityLabel = new JLabel("Total Capacity: " + getTotalCapacity() + " kWh");
        totalCapacityLabel.setFont(new Font("맑은 고딕", Font.BOLD, 11));
        
        // Average SOC
        averageSOCLabel = new JLabel("Average SOC: " + df.format(getAverageSOC()) + "%");
        averageSOCLabel.setFont(new Font("맑은 고딕", Font.BOLD, 11));
        averageSOCLabel.setForeground(getSOCColor(getAverageSOC()));
        
        // Total available energy
        totalEnergyLabel = new JLabel("Available Energy: " + df.format(getTotalAvailableEnergy()) + " kWh");
        totalEnergyLabel.setFont(new Font("맑은 고딕", Font.BOLD, 11));
        
        // System SOC progress bar
        systemSOCBar = new JProgressBar(0, 100);
        systemSOCBar.setValue((int) getAverageSOC());
        systemSOCBar.setStringPainted(true);
        systemSOCBar.setString("System SOC: " + df.format(getAverageSOC()) + "%");
        systemSOCBar.setForeground(getSOCColor(getAverageSOC()));
        
        panel.add(totalCapacityLabel);
        panel.add(averageSOCLabel);
        panel.add(totalEnergyLabel);
        panel.add(systemSOCBar);
        
        return panel;
    }
    
    private JPanel createBatteryPackPanel(BatteryPackInfo pack) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Pack #" + pack.getPackId()));
        panel.setPreferredSize(new Dimension(120, 120));
        
        // SOC display with visual battery
        BatterySummeryPanel batteryVisual = new BatterySummeryPanel(pack.getPackId(), pack.getSoc(), 40, 60);
        
        // Pack details
    JPanel detailsPanel = new JPanel(new GridLayout(4, 1, 2, 2));
        
        JLabel socLabel = new JLabel("SOC: " + df.format(pack.getSoc()) + "%");
        socLabel.setForeground(getSOCColor(pack.getSoc()));
        socLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
        socLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel voltageLabel = new JLabel("V: " + df.format(pack.getVoltage()) + "V");
        voltageLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
        voltageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel currentLabel = new JLabel("I: " + df.format(pack.getCurrent()) + "A");
        currentLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
        currentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel statusLabel = new JLabel(pack.getStatus());
        statusLabel.setFont(new Font("맑은 고딕", Font.BOLD, 10));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setForeground(pack.isHealthy() ? Color.GREEN : Color.RED);
        
        detailsPanel.add(socLabel);
        detailsPanel.add(voltageLabel);
        detailsPanel.add(currentLabel);
        detailsPanel.add(statusLabel);
        
        // Add simple per-pack control: slider + Charge/Discharge toggle
        JPanel controlSmall = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 2));
        JSlider packSlider = new JSlider(0, 100, (int)pack.getSoc());
        packSlider.setPreferredSize(new Dimension(100, 20));
        packSlider.addChangeListener(e -> {
            // reflect to label while sliding
            socLabel.setText("SOC: " + df.format(packSlider.getValue()) + "%");
            socLabel.setForeground(getSOCColor(packSlider.getValue()));
        });

        JToggleButton chargeToggle = new JToggleButton("Charge");
        chargeToggle.setPreferredSize(new Dimension(80, 20));
        chargeToggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chargeToggle.isSelected()) {
                    chargeToggle.setText("Charging");
                    javax.swing.JOptionPane.showMessageDialog(panel, "Pack #" + pack.getPackId() + " : Charge ON");
                } else {
                    chargeToggle.setText("Charge");
                    javax.swing.JOptionPane.showMessageDialog(panel, "Pack #" + pack.getPackId() + " : Charge OFF");
                }
            }
        });

        JButton packApply = new JButton("Apply");
        packApply.setPreferredSize(new Dimension(70, 20));
        packApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // apply slider SOC as a simulated setpoint
                pack.setSoc(packSlider.getValue());
                updateDisplay();
                javax.swing.JOptionPane.showMessageDialog(panel, "Pack #" + pack.getPackId() + " SOC set to " + packSlider.getValue() + "%");
            }
        });

        controlSmall.add(packSlider);
        controlSmall.add(chargeToggle);
        controlSmall.add(packApply);

        panel.add(batteryVisual, BorderLayout.WEST);
        panel.add(detailsPanel, BorderLayout.CENTER);
        panel.add(controlSmall, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel.setPreferredSize(new Dimension(0, 40));
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
        refreshButton.addActionListener(e -> updateDisplay());
        
        JButton balanceButton = new JButton("Balance");
        balanceButton.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
        balanceButton.addActionListener(e -> {
            // Battery balancing logic
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Battery balancing initiated for all packs", 
                "Battery Management", 
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
        });
        
        JButton diagnosticsButton = new JButton("Diagnostics");
        diagnosticsButton.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
        diagnosticsButton.addActionListener(e -> showDiagnostics());
        
        panel.add(refreshButton);
        panel.add(balanceButton);
        panel.add(diagnosticsButton);
        
        return panel;
    }
    
    private void startUpdateTimer() {
        updateTimer = new Timer(5000, e -> {
            // Simulate battery data changes
            simulateDataUpdate();
            updateDisplay();
        });
        updateTimer.start();
    }
    
    private void simulateDataUpdate() {
        java.util.Random random = new java.util.Random();
        
        for (BatteryPackInfo pack : batteryPacks) {
            // Small SOC variations
            double socChange = (random.nextDouble() - 0.5) * 0.5; // ±0.25%
            double newSOC = Math.max(0, Math.min(100, pack.getSoc() + socChange));
            pack.setSoc(newSOC);
            
            // Voltage variations
            double voltageChange = (random.nextDouble() - 0.5) * 5; // ±2.5V
            pack.setVoltage(800 + voltageChange);
            
            // Current variations
            double currentChange = (random.nextDouble() - 0.5) * 20; // ±10A
            pack.setCurrent(currentChange);
            
            // Update health status
            pack.setHealthy(pack.getSoc() > 10 && pack.getVoltage() > 750);
            pack.setStatus(pack.isHealthy() ? "Normal" : "Warning");
        }
    }
    
    private void updateDisplay() {
        // Update summary
        totalCapacityLabel.setText("Total Capacity: " + getTotalCapacity() + " kWh");
        averageSOCLabel.setText("Average SOC: " + df.format(getAverageSOC()) + "%");
        averageSOCLabel.setForeground(getSOCColor(getAverageSOC()));
        totalEnergyLabel.setText("Available Energy: " + df.format(getTotalAvailableEnergy()) + " kWh");
        
        systemSOCBar.setValue((int) getAverageSOC());
        systemSOCBar.setString("System SOC: " + df.format(getAverageSOC()) + "%");
        systemSOCBar.setForeground(getSOCColor(getAverageSOC()));
        
        // Refresh the packs container
        packsContainer.removeAll();
        for (BatteryPackInfo pack : batteryPacks) {
            JPanel packPanel = createBatteryPackPanel(pack);
            packsContainer.add(packPanel);
        }
        packsContainer.revalidate();
        packsContainer.repaint();
    }
    
    private void showDiagnostics() {
        StringBuilder diagnostics = new StringBuilder();
        diagnostics.append("Battery Pack Diagnostics Report\n");
        diagnostics.append("=====================================\n\n");
        
        for (BatteryPackInfo pack : batteryPacks) {
            diagnostics.append("Pack #").append(pack.getPackId()).append(":\n");
            diagnostics.append("  SOC: ").append(df.format(pack.getSoc())).append("%\n");
            diagnostics.append("  Voltage: ").append(df.format(pack.getVoltage())).append("V\n");
            diagnostics.append("  Current: ").append(df.format(pack.getCurrent())).append("A\n");
            diagnostics.append("  Temperature: ").append(df.format(pack.getTemperature())).append("°C\n");
            diagnostics.append("  Status: ").append(pack.getStatus()).append("\n");
            diagnostics.append("  Available Energy: ").append(df.format(pack.getAvailableEnergy())).append(" kWh\n\n");
        }
        
        diagnostics.append("System Summary:\n");
        diagnostics.append("  Average SOC: ").append(df.format(getAverageSOC())).append("%\n");
        diagnostics.append("  Total Available Energy: ").append(df.format(getTotalAvailableEnergy())).append(" kWh\n");
        
        javax.swing.JOptionPane.showMessageDialog(this, 
            diagnostics.toString(), 
            "Battery Diagnostics", 
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Utility methods
    private double getTotalCapacity() {
        return batteryPacks.stream().mapToDouble(BatteryPackInfo::getCapacity).sum();
    }
    
    private double getAverageSOC() {
        return batteryPacks.stream().mapToDouble(BatteryPackInfo::getSoc).average().orElse(0.0);
    }
    
    private double getTotalAvailableEnergy() {
        return batteryPacks.stream().mapToDouble(BatteryPackInfo::getAvailableEnergy).sum();
    }
    
    private Color getSOCColor(double soc) {
        if (soc >= 80) return Color.GREEN;
        else if (soc >= 50) return Color.ORANGE;
        else if (soc >= 20) return new Color(255, 165, 0);
        else return Color.RED;
    }
    
    public void stopTimer() {
        if (updateTimer != null) {
            updateTimer.stop();
        }
    }
    
    public List<BatteryPackInfo> getBatteryPacks() {
        return batteryPacks;
    }
    
    // Main method for independent execution
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Battery Pack SOC Control Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            BatteryPackSOCPanel panel = new BatteryPackSOCPanel();
            frame.add(panel);
            
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
            // Handle window closing to stop timer
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    panel.stopTimer();
                }
            });
        });
    }
}