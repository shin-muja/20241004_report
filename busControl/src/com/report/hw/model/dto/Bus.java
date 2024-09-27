package com.report.hw.model.dto;

public class Bus {
	String startTime; // 출발 시간
	String endTime; // 도착 예정시간
	String company; // 운행사
	String rank;
	int price; // 요금
	int totalSeat; // 총 좌석
	int remainingSeats; // 남은 좌석
	
	public Bus() {
		// TODO Auto-generated constructor stub
	}

	

	public Bus(String startTime, String endTime, String company, String rank, int price, int totalSeat,
			int remainingSeats) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.company = company;
		this.rank = rank;
		this.price = price;
		this.totalSeat = totalSeat;
		this.remainingSeats = remainingSeats;
	}



	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTotalSeat() {
		return totalSeat;
	}

	public void setTotalSeat(int totalSeat) {
		this.totalSeat = totalSeat;
	}

	public int getRemainingSeats() {
		return remainingSeats;
	}

	public void setRemainingSeats(int remainingSeats) {
		this.remainingSeats = remainingSeats;
	}

	@Override
	public String toString() {
		return String.format("출발 시각 : %s / ", startTime );
	}
	
	
	
	
	
}
