package com.report.hw.model.dto;

// 시외버스 클래스
public class IntercityBus extends Bus {
	private String stopober; // 경유지
	
	public IntercityBus() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public IntercityBus(String endTerminal, String startTime, String company, int price, int totalSeat,
			int remainingSeats, String endTime) {
		super(endTerminal, startTime, company, price, totalSeat, remainingSeats, endTime);
	}



	public IntercityBus(String endTerminal, String startTime, String company, int price, int totalSeat,
			int remainingSeats, String stopober, String endTime) {
		super(endTerminal, startTime, company, price, totalSeat, remainingSeats, endTime);
		this.stopober = stopober;
	}

	


	public String getStopober() {
		return stopober;
	}

	public void setStopober(String stopober) {
		this.stopober = stopober;
	}

	@Override
	public String toString() {
		String str = String.format("도착지 : %s / 출발시간 : %s / 도착시간 : %s / 운행사 : %s / 요금 : %d원 / 좌석 : %d/%d", 
				endTerminal, startTime, endTime, company, price, totalSeat, remainingSeats);
		
		if ( stopober != null ) {
			str += " / 경유지 : " + stopober;
		}
		return str;
	}
	
}
