package com.report.hw.model.dto;

// 고속버스 클래스
public class ExpressBus extends Bus {
	boolean nightDrive; // 야간운전 판별 비용계산
	
	public ExpressBus() {
		// TODO Auto-generated constructor stub
	}
	


	public boolean isNightDrive() {
		return nightDrive;
	}

	public void setNightDrive(boolean nightDrive) {
		this.nightDrive = nightDrive;
	}

}