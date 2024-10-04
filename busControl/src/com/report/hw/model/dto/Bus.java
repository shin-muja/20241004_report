package com.report.hw.model.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Bus {
	protected String endTerminal; // 도착지
	protected String startTime; // 출발시간
	protected String company; // 운행사
	protected int price; // 요금
	// 좌석은 개선 필요, 어캐 해야할지 감이 안 잡힘...
	protected int totalSeat; // 총 좌석
	protected int remainingSeats; // 채워진 좌석
	protected String endTime; // 도착 시간
	
	public Bus() {
	}

	public Bus(String endTerminal, String startTime, String company, int price, int totalSeat, int remainingSeats,
			String endTime) {
		super();
		this.endTerminal = endTerminal;
		this.startTime = startTime;
		this.company = company;
		this.price = price;
		this.totalSeat = totalSeat;
		this.remainingSeats = remainingSeats;
		setEndTime(endTime);
	}



	public String getEndTerminal() {
		return endTerminal;
	}

	
	public void setEndTerminal(String endTerminal) {
		this.endTerminal = endTerminal;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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
	
	// 도착 예정시간 계산
	@SuppressWarnings("deprecation")
	public void setEndTime(String time) {
		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		
		try {
			Date start = format.parse(startTime);
			Date end = format.parse(time);
			Calendar cal = Calendar.getInstance();
			
			int hour =  end.getHours();
			int min =  end.getMinutes();
			cal.setTime(start);
			cal.add(Calendar.HOUR_OF_DAY, hour);
			cal.add(Calendar.MINUTE, min);
			
			end = new Date(cal.getTimeInMillis());
			
			this.endTime = format.format(end);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public String getEndTime() {
		return this.endTime;
	}
	
}
