package com.report.hw.model.dto;

// 고속버스 클래스
public class ExpressBus extends Bus {
	private boolean nightDrive; // 심야 할증 계산
	private String rank;
	
	public ExpressBus() {
		// TODO Auto-generated constructor stub
	}

	public ExpressBus(String endTerminal, String startTime, String company, int price, int totalSeat,
			int remainingSeats, boolean nightDrive, String rank, String endTime) {
		super(endTerminal, startTime, company, price, totalSeat, remainingSeats, endTime);
		
		this.nightDrive = nightDrive;
		this.rank = rank;
		
		// 프리미엄 금액 계산식 : 계산식 문제 제대로 내림처림 못함
		if( rank.equals("프리미엄")) {
			super.price = (int) (super.price * 1.3) % 1000 != 0 ? (int) (super.price * 1.3)  : (int)(super.price * 1.3) - (int)((super.price * 1.3) / 1000);
		}
		
		
		// 심야 금액 계산 및 표시추가 : 계산식 문제 제대로 내림처림 못함
		if( nightDrive == true ) {
			super.price = (int)(super.price * 1.1) % 1000 != 0 ? (int) (super.price * 1.1)  : (int)(super.price * 1.1) - (int)((super.price * 1.1) / 1000);
			this.rank = "심야" + this.rank;
		}
	}

	public boolean isNightDrive() {
		return nightDrive;
	}

	public void setNightDrive(boolean nightDrive) {
		this.nightDrive = nightDrive;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		String str = String.format("도착지 : %s / 출발시간 : %s / 도착시간 : %s / 등급 : %s / 운행사 : %s / 요금 : %d원 / 좌석 : %d/%d",
				endTerminal, startTime,	endTime, rank, company, price, totalSeat, remainingSeats);
		return str;
	}
	
	
	

}