package com.report.hw.model.Servise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.report.hw.model.dto.Bus;
import com.report.hw.model.dto.ExpressBus;
import com.report.hw.model.dto.IntercityBus;

// 동서울 기준 고속 시외 종합 시간표 관리
public class BusService {
	List<Bus> busList = new ArrayList<Bus>();
	Scanner sc = new Scanner(System.in);
	Map<String, String> interCityEstTime = new HashMap<>(); // 시외 버스 예상 소요시간
	Map<String, String> eperssCityEstTime = new HashMap<>(); // 고속 버스 예상 소요시간
	
	public BusService() {
		
		interCityEstTime.put("속초", "02:55");
		interCityEstTime.put("부산", "04:50");
		interCityEstTime.put("익산", "03:00");
		interCityEstTime.put("경주", "04:00");
		interCityEstTime.put("아산", "02:00");
		
		eperssCityEstTime.put("속초", "02:30");
		eperssCityEstTime.put("동대구", "03:30");
		eperssCityEstTime.put("전주", "02:50");
		eperssCityEstTime.put("청주", "01:40");
		eperssCityEstTime.put("부산", "04:15");
		
		busList.add(new IntercityBus("속초", "08:15", "금강고속", 22000, 17, 28, interCityEstTime.get("속초")));
		busList.add(new IntercityBus("익산", "09:15", "금강고속", 44000, 24, 41, "인제 -> 원주", interCityEstTime.get("익산")));
		busList.add(new IntercityBus("아산", "06:55", "용남고속", 13000, 15, 27, "천안", interCityEstTime.get("아산")));
		busList.add(new ExpressBus("속초", "09:40", "동부고속", 16000, 2, 28, false,"프리미엄", eperssCityEstTime.get("속초")));
		busList.add(new IntercityBus("부산", "16:30", "경남고속", 54000, 15, 21, "양산 -> 해운대", interCityEstTime.get("부산")));
		busList.add(new ExpressBus("청주", "13:30", "속리산고속", 15000, 24, 28, false,"우등", eperssCityEstTime.get("청주")));
		busList.add(new ExpressBus("부산", "22:00", "중앙고속", 41000, 17, 28, true, "우등", eperssCityEstTime.get("부산")));
		busList.add(new IntercityBus("경주", "14:10", "천마고속", 34000, 25, 28, interCityEstTime.get("경주")));
		
		startTimeSort();
	}
	
	public void displayMenu() {
		int input = 0;
		try {
			do {
				menu();
				
				System.out.print("\n이용하실 항목을 입력해주세요 : ");
				input = sc.nextInt(); sc.nextLine();
				
				switch(input) {
				case 1:	
					System.out.println("\n********* 버스 시간표 조회 *********");
					selectTimeTable(); break;
				case 2: addBus(); break;
				case 3: deleteBus(); break;
				case 4: updateBus(); break;
				case 0: System.out.println("시스템을 종료합니다. 이용해주셔서 감사합니다"); return;
				default : System.out.println("잘못 입력하셨습니다 다시 입력해주세요"); break;
				}
			} while(input != 0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			input = -1;
		}
	}
	
	// 시간표 조회
	public void selectTimeTable() throws Exception {
		
		System.out.print("시외, 고속 버스별 시간표로 보시겠습니까? (Y/N) : ");
		char ch = sc.next().toUpperCase().charAt(0);
		
		if( ch == 'Y') { // Y시 시외 고속 버스 나눠서 보여주기
			selectTimeTableY();
		} else if( ch == 'N') { // N시 통합으로 보여주기
			selectTimeTableN();
		} else {
			System.out.println("잘못 입력하셨습니다 Y나 N둘중 하나만 입력해주세요.");
		}
		
		System.out.println();
	}
	
	
	// 시간표에서 버스 추가
	public void addBus() throws Exception{

		System.out.println("------------------------------------------");
		System.out.print("(1. 시외/ 2. 고속)추가할 버스 종류를 선택해주세요 : ");
		int input = sc.nextInt(); sc.nextLine();
		
		if( input != 1 && input != 2) {
			System.out.println("1이나 2 입력해주세요"); return;
		}
		
		if( input == 1) {
			System.out.println(interCityEstTime.keySet().toString() + "외에 다른 지역은 아직 정보가 없습니다. ");
		} else if (input == 2) {
			System.out.println(eperssCityEstTime.keySet().toString() + "외에 다른 지역은 아직 정보가 없습니다. ");
		}
		System.out.print("도착지를 입력해주세요 : ");
		String endTerminal = sc.nextLine();
		
		System.out.print("출발시간을 입력해주세요(형식 : hh:mm) : ");
		String startTime = sc.nextLine();
		boolean flag = false;
		
		int night = Integer.parseInt(startTime.substring(0, 1));
		
		if( night > 23 || night < 0 ) {
			System.out.println("범위를 벗어났습니다");
			return;
		} else if ( night > 21 || night < 6) {
			flag = true;
		}
		
		System.out.print("운행사를 입력해주세요 : ");
		String company = sc.nextLine();
		
		System.out.print("요금을 입력해주세요 : ");
		int price = sc.nextInt();
		
		System.out.print("채워진 좌석을 입력해주세요 : ");
		int remainingSeats = sc.nextInt();
		
		System.out.print("총 좌석를 입력해주세요 : ");
		int totalSeat = sc.nextInt(); sc.nextLine();
		
		Bus bus = null;
		
		
		if( input == 1) {
			System.out.print("경유지를 입력해주세요(없으면 내려쓰기) : ");
			String str = sc.nextLine();
			
			System.out.println("입력된 정보를 확인 후 수정할 정보가 있을시 수정해주세요");
			
			if( str.length() == 0 ) { // 경유지 입력 없을시
				bus = new IntercityBus(endTerminal, startTime, company, price,
						totalSeat, remainingSeats, interCityEstTime.get(endTerminal));
				intercityUpdateBus((IntercityBus)bus);
			} else { // 입력 있을시
				bus = new IntercityBus(endTerminal, startTime, company, price,
						totalSeat, remainingSeats, interCityEstTime.get(endTerminal), str);
				// 입력된 정보 확인용 메서드 사용 잘못 입력 시 바로 수정
				intercityUpdateBus((IntercityBus)bus);
			}
		} else if (input == 2) {
			System.out.println("입력된 정보를 확인 후 수정할 정보가 있을시 수정해주세요");
			String str;
			
			while(true) {
				System.out.print("등급을 입력해주세요(우등/프리미엄) : ");
				str = sc.nextLine();
				if( !str.equals("우등") || !str.equals("프리미엄")) {
					System.out.println("잘못 입력하셨습니다");
				} else {
					break;
				}
			}
			
			bus = new ExpressBus(endTerminal, startTime, company, price, totalSeat,
					remainingSeats, flag, str, eperssCityEstTime.get(endTerminal));
			// 입력된 정보 확인용 메서드 사용 잘못 입력 시 바로 수정
			expressUpdateBus((ExpressBus)bus);
			
		}
		
		// 추가 후 정렬
		startTimeSort();
		selectTable();
	}
	
	// 시간표에서 버스삭제
	public void deleteBus() throws Exception{
		selectTable();
		
		System.out.print("제거할 버스를 선택해주세요. 인덱스를 선택해주세요");
		int input = sc.nextInt();sc.nextLine();
		System.out.println();
		
		while(true) {
			System.out.print(busList.get(input-1) + "\n해당 버스의 정보를 정말 삭제하시겠습니까? (y/n)");
			char ch = sc.nextLine().toLowerCase().charAt(0);
			
			if( ch == 'y') {
				busList.remove(input-1);
				break;
			} if ( ch == 'n' ) {
				System.out.println("삭제를 취소하셨습니다.\n");
				break;
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}
	
	// 버스 정보수정, 시외 / 고속
	public void updateBus() throws Exception {
		selectTable();
		System.out.print("정보를 수정할 버스를 선택해주세요 : ");
		int index = sc.nextInt(); sc.nextLine();
		
		if( busList.get(index-1) instanceof IntercityBus) {
			intercityUpdateBus((IntercityBus)busList.get(index-1));
		} else {
			expressUpdateBus((ExpressBus)busList.get(index-1));
		}
		
		System.out.println("정보 수정을 완료하였습니다.");
	}
	
	// 시외버스 정보 수정
	/** 취소 전까지 계속 부름
	 */
	public void intercityUpdateBus(IntercityBus bus) throws Exception{
		System.out.println("-------------------------------------------------");
		System.out.println("\n" + bus);
		
		// 도착시간은 도착지와 출발시간을 변경시 자동으로 변경
		// 좌석은 귀찮음으로 pass
		System.out.println();
		System.out.println("1. 도착지");
		System.out.println("2. 출발시간");
		System.out.println("3. 운행사");
		System.out.println("4. 요금");
		System.out.println("5. 경유지");
		System.out.println("0. 취소");
		System.out.print("수정할 정보의 번호를 입력해주세요 : ");
		int input = sc.nextInt(); sc.nextLine();
		
		switch(input) {
		
		case 1 : System.out.println(interCityEstTime.keySet().toString() + "외에 다른 지역은 아직 정보가 없습니다. "
				+ "입력하제 말아주세요");
				System.out.print("수정할 도착지를 입력해주세요 : ");
				String endTerminal = sc.nextLine();
				if(!interCityEstTime.containsKey(endTerminal)) {
					System.out.println("도착지 목록에 없습니다. 취소됩니다.");
					return;
				}
				bus.setEndTerminal(endTerminal);
				bus.setEndTime(interCityEstTime.get(endTerminal));
				intercityUpdateBus(bus);
		break;
		case 2 : System.out.print("수정할 출발시간를 입력해주세요(형식 : hh:mm) : ");
				String startTime = sc.nextLine();
				bus.setStartTime(startTime);
				bus.setEndTime(interCityEstTime.get(bus.getEndTerminal()));
				intercityUpdateBus(bus);
		break;
		case 3 : System.out.print("수정할 운행사를 입력해주세요 : ");
				String company = sc.nextLine();
				bus.setCompany(company);
				intercityUpdateBus(bus);
		break;
		case 4 : System.out.print("수정할 요금를 입력해주세요 : ");
				int price = sc.nextInt(); sc.nextLine();
				bus.setPrice(price);
				intercityUpdateBus(bus);
		break;
		case 5 : System.out.print("수정할 경유지를 입력해주세요 : ");
				String stop = sc.nextLine();
				bus.setStopober(stop);
				intercityUpdateBus(bus);
		break;
		case 0 : System.out.println("취소합니다");break;
		default : System.out.println("잘못 입력하셨습니다. 취소합니다");break;
		
		}
	}
	
	// 고속버스 정보 수정
	public void expressUpdateBus(ExpressBus bus) throws Exception{
		System.out.println("-------------------------------------------------");
		System.out.println("\n" + bus);
		// 도착시간은 도착지와 출발시간을 변경시 자동으로 변경
		// 좌석은 귀찮음으로 pass
		System.out.println();
		System.out.println("1. 도착지");
		System.out.println("2. 출발시간");
		System.out.println("3. 운행사");
		System.out.println("4. 요금");
		System.out.println("5. 등급");
		System.out.println("0. 취소");
		System.out.print("수정할 정보의 번호를 입력해주세요 : ");
		int input = sc.nextInt(); sc.nextLine();
		
		switch(input) {
		
		case 1 : System.out.println(eperssCityEstTime.keySet().toString() + "외에 다른 지역은 아직 정보가 없습니다. "
				+ "입력하제 말아주세요");
				System.out.print("수정할 도착지를 입력해주세요 : ");
				String endTerminal = sc.nextLine();
				if(!eperssCityEstTime.containsKey(endTerminal)) {
					System.out.println("도착지 목록에 없습니다. 취소됩니다.");
					return;
				}
				bus.setEndTerminal(endTerminal);
				bus.setEndTime(eperssCityEstTime.get(endTerminal));
				expressUpdateBus(bus);
		break;
		case 2 : System.out.print("수정할 출발시간를 입력해주세요(형식 : hh:mm) : ");
				String startTime = sc.nextLine();
				
				int night = Integer.parseInt(startTime.substring(0, 1));
				if( night >23 || night < 0 ) {
					System.out.println("범위를 벗어났습니다");
				} else if ( night > 21 || night < 6) {
					bus.setNightDrive(true);
				}
				
				bus.setStartTime(startTime);
				bus.setEndTime(eperssCityEstTime.get(bus.getEndTerminal()));
				expressUpdateBus(bus);
		break;
		case 3 : System.out.print("수정할 운행사를 입력해주세요 : ");
				String company = sc.nextLine();
				bus.setCompany(company);
				expressUpdateBus(bus);
		break;
		case 4 : System.out.print("수정할 요금를 입력해주세요 : ");
				int price = sc.nextInt(); sc.nextLine();
				bus.setPrice(price);
				expressUpdateBus(bus);
		break;
		case 5 : System.out.print("수정할 등급을 입력해주세요 : ");
				String rank = sc.nextLine();
				bus.setRank(rank);
				expressUpdateBus(bus);
		break;
		case 0 : System.out.println("취소합니다");break;
		default : System.out.println("잘못 입력하셨습니다. 취소합니다");break;
		
		}
	}
	
	public void menu() {
		System.out.println("**************동서울 종합 터미널 시간표**************");
		System.out.println("1. 시간표 조회");
		System.out.println("2. 운행 버스 추가");
		System.out.println("3. 운행 버스 취소");
		System.out.println("4. 운행 버스 정보 수정");
		System.out.println("0. 종료");
	}
	
	// busList 정렬
	public void startTimeSort() {

		busList.sort(Comparator.comparing(Bus::getStartTime));
	}
	
	// 조회용 버스리스트 Y
	public void selectTimeTableY() {
		Map<String, List<Bus>> busMap = new HashMap<>();
		
		for(Bus bus : busList ) {
			String str = "";
			
			if( bus instanceof IntercityBus) {
				str = "시외";
			} else {
				str = "고속";
			}
			
			busMap.putIfAbsent(str, new ArrayList<Bus>());
			busMap.get(str).add(bus);
		}
		
		for(Entry<String, List<Bus>> bus :busMap.entrySet()) {
			String str= bus.getKey();
			
			System.out.println("[" + str + "]");
			
			for(Bus b : bus.getValue()) {
				System.out.println(b);
			}
			System.out.println();
		}
	}
	
	// 조회용 버스리스트 N
	public void selectTimeTableN() {
		for(Bus bus : busList ) {
			if( bus instanceof IntercityBus) System.out.println("[시외] " + bus);
			
			if( bus instanceof ExpressBus) System.out.println("[고속] " + bus);
		}
	}
	
	// 정보 변경용 버스 리스트
	public void selectTable() {
		System.out.println("*********** 버스 목록 ***********");
		int count = 1;
		for(Bus bus : busList ) {
			if( bus instanceof IntercityBus) System.out.println(count++ + ". [시외] " + bus);
			
			if( bus instanceof ExpressBus) System.out.println(count++ + ". [고속] " + bus);
		}
		System.out.println();
	}
	
	
}












