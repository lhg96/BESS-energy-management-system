package org.hils.vo.tab;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PCSStatus {	
	public final static int Remote=0;
	public final static int Local=1;
	
	public final static  int Ready=2;
	public final static  int Not=3;
	
	public final static  int Run=4;
	public final static  int Stop=5;
	
	public final static  int Standby=6;
	public final static  int Charge=7;
	public final static  int Discharge=8;
		
	public final static  int CPMode=9;
	public final static  int CVMode=10;
	
	public final static  String[]	LABELS = {
			"Remote",
			"Local",
	
			"Ready",
			"Not",
	
			"Run",
			"Stop",
	
			"Standby",
			"Charge",
			"Discharge",
		
			"CPMode",
			"CVMode"
		};

	
	//member
	public int remoteStatus;
	public int readyStatus;
	public int runStatus;
	public int standbyStatus;
	public int modeStatus;
	
}

