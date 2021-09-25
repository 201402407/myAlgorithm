package year2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class p3 {
	
	static HashMap<String, Integer> inCarMap;	// 주차장에 들어온 차량 정보
	static HashMap<String, Integer> carTimeMap;	// 주차장에 들어온 차량 총 시간정보
	static int minTime, minMoney, partTime, partMoney = 0;
	static final int OUT_MAX_TIME = (23 * 60) + 59;	// 23시 59분
	public static void main(String args[]) {
		int[] fees = { 180, 5000, 10, 600 };
		String[] records = { "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT" };
		
		// 0) 기본설정
		minTime = fees[0];
		minMoney = fees[1];
		partTime = fees[2];
		partMoney = fees[3];
		
		carTimeMap = new HashMap<String, Integer>();
		inCarMap = new HashMap<String, Integer>();
		carPayMap = new HashMap<String, Integer>();
		// 1) for문으로 입차는 HashMap에 넣기
		StringTokenizer st;
		for(String str : records) {
			st = new StringTokenizer(str);
			String time = st.nextToken();
			String carNumber = st.nextToken();
			String inAndOut = st.nextToken();
			
			// 시간 -> int
			String[] splitTime = time.split(":");
			String hour = splitTime[0];
			String minute = splitTime[1];
			int timeNum = (Integer.valueOf(hour) * 60) + Integer.valueOf(minute);
			if(inAndOut.equals("IN")) {	// 입차
				inCarMap.put(carNumber, timeNum);
			}
			else {	// 출차
				int inTime = inCarMap.get(carNumber);	// 무조건 입차해야 출차할 수 있으므로 존재 필수
				Integer value = carTimeMap.putIfAbsent(carNumber, timeNum - inTime);	
				if(value != null) {
					carTimeMap.put(carNumber, value + timeNum - inTime);
				}
				
				inCarMap.remove(carNumber);
			}
		}
		

		// 출차하지 않은 차량들 출차
		for(Entry<String, Integer> entry : inCarMap.entrySet()) {
			String carNum = entry.getKey();
			int inTime = entry.getValue(); 
			Integer value = carTimeMap.putIfAbsent(carNum, OUT_MAX_TIME - inTime);	
			if(value != null) {
				carTimeMap.put(carNum, value + OUT_MAX_TIME - inTime);
			}
			
//			inCarMap.remove(carNumber);	 for문이라 안해도됨
		}
		
		// 요금 일괄정산
		// 차량 요금 array로 만들기
		List<Car> carList = new ArrayList<Car>(); 
		for(Entry<String, Integer> entry : carTimeMap.entrySet()) {
			String carNum = entry.getKey();
			int time = entry.getValue(); 
			int payMoney = getPayMoney(time);
			
			carList.add(new Car(Integer.valueOf(carNum), payMoney));
		}
		
		Collections.sort(carList);
		int carListSize = carList.size();
		int[] answer = new int[carListSize];
		for(int i = 0; i < carListSize; i++) {
			answer[i] = carList.get(i).payMoney;
		}
	}
	
	static int getPayMoney(int time) {
		if(time <= minTime) {	// 1)) 만약 기본요금 주차시간 이내 주차한 경우
			return minMoney;
		}
		
		// 2)) 기본요금(기본시간) 이상 주차한 경우
		double timeGapDouble = time * 1.0;
		double minTimeDouble = minTime * 1.0;
		double partTimeDouble = partTime * 1.0;
//		int addPayMoney = (int)Math.ceil(((timeGap - minTime) / partTime));
		int addPayMoney = (int)Math.ceil(((timeGapDouble - minTimeDouble) / partTimeDouble));
		return minMoney + (addPayMoney * partMoney);
	}
}

class Car implements Comparable<Car> {
	int carNum;
	int payMoney;
	
	Car(int carNum, int payMoney) {
		this.carNum = carNum;
		this.payMoney = payMoney;
	}

	@Override
	public int compareTo(Car car) {
		if(this.carNum < car.carNum) {
			return -1;
		}
		else {
			return 1;
		}
	}
}
