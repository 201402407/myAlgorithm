package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p2470 {
	static ArrayList<Integer> plusList, minusList;
	
//	public static void main(String args[]) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		
//		int n = Integer.valueOf(st.nextToken());
//		plusList = new ArrayList<Integer>();
//		minusList = new ArrayList<Integer>(); // 음수는 내림차순해야 0에 가까운 음수가 나온다.
//		
//		st = new StringTokenizer(br.readLine());
//		for(int i = 0; i < n; i++) {
//			int num = Integer.valueOf(st.nextToken());
//			if(num > 0) {	// 양수
//				plusList.add(num);
//			}
//			if(num < 0) {	// 음수
//				minusList.add(num);
//			}
//		}
//		
//		Collections.sort(plusList);	// 오름차순
//		Collections.sort(minusList);	// 내림차순
//		Collections.reverse(minusList);
//		
//		int[] result = new int[2];
//		int min = Integer.MAX_VALUE;
//		
//		// 양수 <-> 음수 비교
//		if(plusList.size() > 0 && minusList.size() > 0) {
//			int plusIndex = 0;
//			int minusIndex = 0;
//			
//			// 양수 기준
//			for(int i = 0; i < plusList.size(); i++) {
//				if(minusIndex >= minusList.size()) {
//					break;
//				}
//				
//				int plusEle = plusList.get(i);
////				int temp = minusList.get(minusIndex);
//				while(plusEle >= Math.abs(minusList.get(minusIndex))) {
//					int minusEle = minusList.get(minusIndex);
//					
//					if(min > Math.abs(plusEle + minusEle)) {
//						min = Math.abs(plusEle + minusEle);
//						result[0] = minusEle;
//						result[1] = plusEle;
//					}
//					
//					minusIndex++;
//					if(minusIndex >= minusList.size()) {
//						break;
//					}
//				}
//			}
//			
//			// 음수 기준
//			for(int i = 0; i < minusList.size(); i++) {
//				if(plusIndex >= plusList.size()) {
//					break;
//				}
//				
//				int minusEle = minusList.get(i);
//				
//				while(Math.abs(minusEle) >= plusList.get(plusIndex)) {
//					int plusEle = plusList.get(plusIndex);
//					
//					if(min > Math.abs(plusEle + minusEle)) {
//						min = Math.abs(plusEle + minusEle);
//						result[0] = minusEle;
//						result[1] = plusEle;
//					}
//					
//					plusIndex++;
//					if(plusIndex >= plusList.size()) {
//						break;
//					}
//				}
//			}
//		}
//		
//		// 양수 <-> 양수 비교
//		if(plusList.size() >= 2) {
//			if(min > plusList.get(0) + plusList.get(1)) {
//				min = plusList.get(0) + plusList.get(1);
//				result[0] = plusList.get(0);
//				result[1] = plusList.get(1);
//			}
//		}
//		
//		// 음수 <-> 음수  비교
//		if(minusList.size() >= 2) {
//			if(min > Math.abs(minusList.get(0) + minusList.get(1))) {
//				min = Math.abs(minusList.get(0) + minusList.get(1));
//				result[0] = minusList.get(0);
//				result[1] = minusList.get(1);
//			}
//		}
//		
//		// 출력 시 오름차순 출력
//		Arrays.sort(result);
//		System.out.println(result[0] + " " + result[1]);
//	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int i = 0;
		int j = arr.length - 1;
		
		int gap = Integer.MAX_VALUE;
		int ans1 = 0;
		int ans2 = 0;
		
		int temp;
		int sum;
		while (i < j) {
			sum = arr[i] + arr[j];
			temp = Math.abs(sum);
			if (temp < gap) {
				gap = temp;
				ans1 = arr[i];
				ans2 = arr[j];
			}
			if (sum > 0)
				j--;
			else
				i++;
		}
		
		System.out.println(ans1 + " " + ans2);
	}
}


