package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이진탐색(LIS 알고리즘(DP) + 이진탐색)
// 가장 긴 증가하는 부분 수열 3
public class p12738 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.valueOf(st.nextToken());
		}
	
		System.out.println(binarySearch(arr, n));
	}
	
	static int binarySearch(int[] arr, int size) {
		int[] dp = new int[size + 1];
		int dpLen = 0;
		dp[dpLen++] = arr[0];	// 초기 시작 값 설정
		
		for(int i = 1; i < size; i++) {
			if(dp[dpLen - 1] < arr[i]) {	// 만약, 해당 인덱스의 값이 dp 배열에 저장된 최대 값보다 크면 맨 오른쪽에 추가
				dp[dpLen++] = arr[i];
			}
			// 이분탐색으로 적절한 증가하는 부분수열 위치 찾기
			// 증가하는 부분수열이기 때문에 오름차순 정렬이 되어있다. -> 이분탐색 가능
			else {
				int start = 0;
				int end = dpLen;
				
				while(start <= end) {
					int mid = (start + end) / 2;
					
					if(dp[mid] < arr[i]) {
						start = mid + 1;
					}
					else {
						end = mid - 1;
					}
				}
				
				dp[start] = arr[i];
			}
		}
		
		return dpLen;
	}
}
