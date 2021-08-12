package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 꼬인 전깃줄 == 2565번 전깃줄
// 이분탐색 + LIS(가장 긴 증가부분수열)
public class p1365 {
	static int n;
	static int[] powerPoles, dpMap;	// 전깃줄 연결된 전봇대
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.valueOf(br.readLine());
		powerPoles = new int[n + 1];	// 최대 n개가 될 수 있으므로, index pointer가 n까지 갈 수 있다.
		dpMap = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			int num = Integer.valueOf(st.nextToken());
			powerPoles[i] = num;
		}
		
//		즉, 연속으로 증가하는 부분수열의 길이를 구하고(최대)
//		전체 전봇대 개수 - 최대 길이를 하면 잘라야하는 전선의 개수를 알 수 있겠다.
		
//		1. 임의의 배열(길이는 N)을 만들기 -> 해당 배열은 무조건 오름차순으로 해놔야함
//		2. 첫 번째 값을 넣고 다음 값부터 진행
		int start = 0;
		dpMap[start++] = powerPoles[0];
		
		int result = binarySearch(start);
		System.out.println(n - result);
	}

	// 이분탐색
	// 가장 긴 증가하는 부분수열 길이 구하기
	private static int binarySearch(int start) {
		for(int i = 1; i < n; i++) {
//			3-1. 해당 값이 배열의 최댓값보다 크다 -> 배열에 넣기
			if(dpMap[start - 1] < powerPoles[i]) {
				dpMap[start++] = powerPoles[i];
			}
//			3-2. 해당 값이 배열 최솟값 <= x <= 배열 최댓값이거나 최솟값보다 작다 -> 이분탐색으로 
			else {
				int left = 0;
				int right = start - 1;
				while(left <= right) {
					int mid = (left + right) / 2;
					if(dpMap[mid] < powerPoles[i]) {
						left = mid + 1;
					}
					else {
						right = mid - 1;
					}
				}
//				start > end가 될때 
//				start 값(즉, 현재 배열에 있는 특정 인덱스의 원소가 가장 가까울 때, 특정 인덱스 + 1의 원소와 교체
				dpMap[left] = powerPoles[i];
			}
		}
		
		return start;
	}
}
