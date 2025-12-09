package org.hils.vo.tab;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Battery Status Value Object
 * 배터리 상태 정보를 담는 VO 클래스
 * 
 * @author hyun keun lim
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatteryStatus {
    
    // Basic battery information
    private int batteryId;                  // 배터리 ID
    private String batteryName;             // 배터리 이름
    private double soc;                     // State of Charge (충전 상태) %
    private double soh;                     // State of Health (배터리 건전성) %
    
    // Electrical parameters
    private double voltage;                 // 전압 (V)
    private double current;                 // 전류 (A)
    private double power;                   // 전력 (kW)
    private double capacity;                // 용량 (kWh)
    private double availableCapacity;       // 사용 가능 용량 (kWh)
    
    // Temperature information
    private double temperature;             // 온도 (°C)
    private double maxCellTemperature;      // 최대 셀 온도 (°C)
    private double minCellTemperature;      // 최소 셀 온도 (°C)
    
    // Cell information
    private int totalCells;                 // 총 셀 개수
    private double maxCellVoltage;          // 최대 셀 전압 (V)
    private double minCellVoltage;          // 최소 셀 전압 (V)
    private double avgCellVoltage;          // 평균 셀 전압 (V)
    
    // Status information
    private boolean isOnline;               // 온라인 상태
    private boolean isCharging;             // 충전 중 여부
    private boolean isDischarging;          // 방전 중 여부
    private boolean hasFault;               // 결함 여부
    private boolean isBalancing;            // 밸런싱 중 여부
    
    // Operational parameters
    private double chargeRate;              // 충전률 (C-rate)
    private double dischargeRate;           // 방전률 (C-rate)
    private int cycleCount;                 // 사이클 횟수
    private String operationMode;           // 운영 모드 (STANDBY, CHARGE, DISCHARGE, BALANCE)
    
    // Alarm and warning status
    private boolean hasWarning;             // 경고 여부
    private boolean hasAlarm;               // 알람 여부
    private String lastAlarmMessage;        // 마지막 알람 메시지
    private java.util.Date lastUpdateTime; // 마지막 업데이트 시간
    
    // Communication status
    private boolean communicationOk;        // 통신 상태
    private long lastCommunicationTime;     // 마지막 통신 시간
    
    /**
     * Get available energy in kWh
     * 사용 가능한 에너지 계산
     */
    public double getAvailableEnergy() {
        return (capacity * soc) / 100.0;
    }
    
    /**
     * Get remaining energy in kWh
     * 남은 에너지 계산
     */
    public double getRemainingEnergy() {
        return capacity - getAvailableEnergy();
    }
    
    /**
     * Get cell voltage deviation
     * 셀 전압 편차 계산
     */
    public double getCellVoltageDeviation() {
        return maxCellVoltage - minCellVoltage;
    }
    
    /**
     * Get temperature deviation
     * 온도 편차 계산
     */
    public double getTemperatureDeviation() {
        return maxCellTemperature - minCellTemperature;
    }
    
    /**
     * Check if battery needs balancing
     * 밸런싱 필요 여부 확인
     */
    public boolean needsBalancing() {
        return getCellVoltageDeviation() > 0.05; // 50mV 이상 차이
    }
    
    /**
     * Get overall health status
     * 전체 건전성 상태 반환
     */
    public String getHealthStatus() {
        if (hasFault) return "FAULT";
        if (hasAlarm) return "ALARM";
        if (hasWarning) return "WARNING";
        if (soh < 80) return "DEGRADED";
        if (soc < 10) return "LOW_SOC";
        return "NORMAL";
    }
    
    /**
     * Check if battery is ready for operation
     * 운영 준비 상태 확인
     */
    public boolean isReadyForOperation() {
        return isOnline && communicationOk && !hasFault && soc > 5;
    }
}
