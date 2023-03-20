package com.hils.vo;

import java.util.List;

import com.hils.event.ESSEvent;
import com.hils.event.EventListener;

/**
 * BMS Manager
 * @author admin
 *	
 */
public class BMS {	
	//member 
	private List<Cell> cells;
	
	private final PCS pcs;
	private final PMS pms;
	
	private double MAX_TEMPERATURE  = 70.0;
	private double MIN_VOLTAGE		= 3.0;
	
	private EventListener eventListener;

	public BMS(List<Cell> cells, final PCS pcs, final PMS pms) {
		this.cells = cells;
		this.pcs = pcs;
		this.pms = pms;
	}

	public void setEventListener(EventListener eventListener) {
		this.eventListener = eventListener;
	}

	public void start() {
		// Start the runtime engine
		while (true) {
			// Check each cell for faults
			for (Cell cell : cells) {
				if (cell.getTemperature() > MAX_TEMPERATURE) {
					// Temperature fault detected
					if (eventListener != null) {
						ESSEvent essEvent = new ESSEvent(EventType.TEMPERATURE_FAULT, cell.getId());						
						eventListener.onEvent(essEvent);						
					}
					// Perform fault mitigation action
					pcs.discharge();
				} else if (cell.getVoltage() < MIN_VOLTAGE) {
					// Voltage fault detected
					if (eventListener != null) {						
						ESSEvent essEvent = new ESSEvent(EventType.VOLTAGE_FAULT, cell.getId());						
						eventListener.onEvent(essEvent);						
					}
					// Perform fault mitigation action
					pcs.charge();
				} else {
					// No fault detected
					pcs.idle();
				}
			}

			// Check BMS state
			if (pms.isShuttingDown()) {
				// BMS is shutting down
				if (eventListener != null) {
					ESSEvent essEvent = new ESSEvent(EventType.SHUTDOWN, null);						
					eventListener.onEvent(essEvent);
				}
				// Stop the runtime engine
				break;
			}
			// Wait for next cycle
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
