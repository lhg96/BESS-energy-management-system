/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hepi.hils.vo.summery;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author user_ai
 */
@AllArgsConstructor
@Data
public class BMSSummery {
	boolean BMS_FAULT;
	double soc;
	double soh;
	double dcVoltage; // dc전압
	double dcCurrent; // dc전류
	int BMS_HB; // guess high voltage
}

