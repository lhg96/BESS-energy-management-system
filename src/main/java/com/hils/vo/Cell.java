package com.hils.vo;

import lombok.Data;

/**
 * 
 * @author hyunkeun
 *
 */
@Data
public class Cell{
	private Long 	id;
    private String 	name;
    private Module 	module;
    private Rack 	rack;
    private double 	temperature;
    private double 	voltage;
    private double 	current;
}
