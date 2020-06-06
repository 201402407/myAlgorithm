package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이진탐색
// 입국심사
public class p3079 {
	static int[] tables;
	static long maxHigh = 0;
	static long min = Long.MAX_VALUE;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		tables = new int[n];
		
		for(int i = 0; i < n; i++) {
			tables[i] = Integer.parseInt(br.readLine());
			maxHigh = Math.max(maxHigh, tables[i]);
		}
		
		getMinTime(n, m);
		System.out.println(min);
	}
	
	// 최소 시간 얻는 함수
	static void getMinTime(int n, int m) {
		long low = 0;
		long high = maxHigh * m;
		
		// 가능 시간 범위를 구하기 위한 이진탐색
		while(low <= high) {
			long mid = (low + high) / 2;
			long sum = 0;
			
			for(int ele : tables) {
				long needPeopleCount = mid / ele;
				
				// 굳이 정렬해서 0이 나오는 경우만 break할 필요 없이 
				// sum >= m 이 되면 더해봤자 의미 없으므로 바로 반복문을 종료시킨다.
				if(sum >= m) {
					break;
				}
				
				sum += needPeopleCount;
			}
			
			
			
			if(sum >= m) {
				System.out.println(mid + ", " + sum);
				min = Math.min(min, mid);
				high = mid - 1;
			}
			else {
				low = mid + 1;
			}
		}
	}
}