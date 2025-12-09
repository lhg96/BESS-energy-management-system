/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hils.vo.summery;

/**
 *
 * @author user_ai
 */
public class PCSSummery {
	boolean pcsFault;
	double referValue; // refer value
	double activePower; // active power
	double dcVoltage; // dc voltage
	double dcCurrent; // dc current
	int fault; // fault count
	
	public PCSSummery() {}
	
	public PCSSummery(boolean pcsFault, double referValue, double activePower, double dcVoltage, double dcCurrent, int fault) {
		this.pcsFault = pcsFault;
		this.referValue = referValue;
		this.activePower = activePower;
		this.dcVoltage = dcVoltage;
		this.dcCurrent = dcCurrent;
		this.fault = fault;
	}
	
	public boolean isPcsFault() { return pcsFault; }
	public void setPcsFault(boolean pcsFault) { this.pcsFault = pcsFault; }
	
	public double getReferValue() { return referValue; }
	public void setReferValue(double referValue) { this.referValue = referValue; }
	
	public double getActivePower() { return activePower; }
	public void setActivePower(double activePower) { this.activePower = activePower; }
	
	public double getDcVoltage() { return dcVoltage; }
	public void setDcVoltage(double dcVoltage) { this.dcVoltage = dcVoltage; }
	
	public double getDcCurrent() { return dcCurrent; }
	public void setDcCurrent(double dcCurrent) { this.dcCurrent = dcCurrent; }
	
	public int getFault() { return fault; }
	public void setFault(int fault) { this.fault = fault; }
}