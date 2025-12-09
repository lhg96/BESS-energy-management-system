/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.hils.vo.summery;

/**
 *
 * @author user_ai
 */
public class BMSSummery {
	boolean bmsFault;
	double soc;
	double soh;
	double dcVoltage; // dc voltage
	double dcCurrent; // dc current
	int bmsHb; // high voltage
	
	public BMSSummery() {}
	
	public BMSSummery(boolean bmsFault, double soc, double soh, double dcVoltage, double dcCurrent, int bmsHb) {
		this.bmsFault = bmsFault;
		this.soc = soc;
		this.soh = soh;
		this.dcVoltage = dcVoltage;
		this.dcCurrent = dcCurrent;
		this.bmsHb = bmsHb;
	}
	
	public boolean isBmsFault() { return bmsFault; }
	public void setBmsFault(boolean bmsFault) { this.bmsFault = bmsFault; }
	
	public double getSoc() { return soc; }
	public void setSoc(double soc) { this.soc = soc; }
	
	public double getSoh() { return soh; }
	public void setSoh(double soh) { this.soh = soh; }
	
	public double getDcVoltage() { return dcVoltage; }
	public void setDcVoltage(double dcVoltage) { this.dcVoltage = dcVoltage; }
	
	public double getDcCurrent() { return dcCurrent; }
	public void setDcCurrent(double dcCurrent) { this.dcCurrent = dcCurrent; }
	
	public int getBmsHb() { return bmsHb; }
	public void setBmsHb(int bmsHb) { this.bmsHb = bmsHb; }
}

