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
public class PCSSummery {
	boolean PCS_FAULT;

	double referValue; // 지령값
	double activePower; // 유효전력
	double dcVoltage; // dc전압
	double dcCurrent; // dc전류
	int fault; // 누락 카운트
}