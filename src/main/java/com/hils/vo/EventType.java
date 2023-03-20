package com.hils.vo;

public enum EventType {
	ESS_Event("ESS_Event"), 
	COMMUNICATION_FAULT("Communication_Fault"),
	TEMPERATURE_FAULT("TEMPERATURE_FAULT"),
	VOLTAGE_FAULT("VOLTAGE_FAULT"),
		
	GENERAL_STATUS("GENERAL_STATUS"),
	CHARGE_COMPLETE("CHARGE_COMPLETE"), 
	DISCHARGE_COMPLETE("DISCHARGE_COMPLETE"), 
	IDLE("IDLE"),
	SHUTDOWN("SHUTDOWN"),
	
	PCS_Event("PCS_Event"), 
	BMS_Event("BMS_Event"),
	PMS_Event("PMS_Event")
		
	;
	//TEMPERATURE_FAULT, VOLTAGE_FAULT, SHUTDOWN, CHARGE_COMPLETE, DISCHARGE_COMPLETE, IDLE
	

	final private String name;

	public String getName() {
		return name;
	}
	private EventType(String name) {
		this.name = name;
	}
	

}
