package com.report.hw.model.dto;

// 시외버스 클래스
public class IntercityBus extends Bus {
	String stopober; // 경유지
	
	public IntercityBus() {
		// TODO Auto-generated constructor stub
	}


	
	public String getStopober() {
		return stopober;
	}

	public void setStopober(String stopober) {
		this.stopober = stopober;
	}

	public IntercityBus(String stopober) {
		super();
		this.stopober = stopober;
	}
}
