package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 오름세
// LIS 알고리즘(이분탐색 사용)
public class p3745 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = null;
		while((str = br.readLine()) != null) {
			str = str.trim();
			if(str.equals("") || str.length() == 0) {
				break;
			}
			
			int n = Integer.valueOf(str);
//			int[] stock = new int[n];	// 1부터 시작
			int[] dp = new int[n + 1];	 
			int dpLen = 1;
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			for(int i = 0; i < n; i++) {
				int ele = Integer.parseInt(st.nextToken());
//				stock[i] = ele;
				
				if(dp[dpLen - 1] < ele) {
					dp[dpLen++] = ele;
				}
				else {
					binarySearch(dp, ele, dpLen);
				}
//				if(stock[i] )
			}
			
			sb.append(dpLen - 1).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void binarySearch(int[] dp, int stock, int dpLen) {
		int start = 0;
		int end = dpLen;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			if(dp[mid] < stock) {
				start = mid + 1;
			}
			else {
				end = mid - 1;
			}
		}
		
		dp[start] = stock;
	}
}
