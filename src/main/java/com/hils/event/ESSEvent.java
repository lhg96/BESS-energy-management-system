package com.hils.event;
import com.hils.vo.EventType;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * event infomation 
 * 
 * @author admin
 *
 */
@AllArgsConstructor
@Data
public class ESSEvent {	
	public EventType Type;
	public Long 	 cellID;
	
}
