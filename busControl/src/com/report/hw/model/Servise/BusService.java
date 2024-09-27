package com.report.hw.model.Servise;

import java.util.*;

import com.report.hw.model.dto.Bus;
import com.report.hw.model.dto.IntercityBus;

public class BusService {
	List<Bus> busList = new ArrayList<Bus>();
	// List<Bus> expressBusList = new ArrayList<Bus>();
	// List<Bus> intercityBusList = new ArrayList<Bus>();
	
	public BusService() {
		busList.add(new IntercityBus());
	}
	
	public void displayMenu() {
		
	}
}