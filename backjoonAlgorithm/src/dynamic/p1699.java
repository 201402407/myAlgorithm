package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 동적 프로그래밍 
// 제곱수의 합
// dp[n] = (a = n-1 ~ n/2, b = n - a) 일 때, dp[a] + dp[b] 의 최솟값  
public class p1699 {
	static int[] dp;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		dp = new int[100001];
		
//		double sqrt = Math.sqrt((double) n);
		
		// 제곱근 존재하는 수만 먼저 설정 -> 안해도된다.  
		// 대신, 1이 몇 개 존재하는지 초기 설정을 해준다. (최댓값으로 초기 설정)
		for(int i = 1; i <= n; i++) {
			dp[i] = i;
		}
		
		for(int i = 1; i <= n; i++) {
			
			// 최소가 되는 제곱수 = n의 이전 수 들 중 제곱수 + n - 제곱수의 최수항 개수 
			for(int j = 1; j * j <= i; j++) {
				if(dp[i] > dp[i - (j * j)] + 1) { // 
					dp[i] = dp[i - (j * j)] + 1;
				}
			}
			
//			int min = Integer.MAX_VALUE;
//			for(int a = i / 2; a <= i - 1; a++) {
//				int b = i - a;
//				
//				if(dp[a] + dp[b] < min) {
//					min = dp[a] + dp[b];
//				}
//			}
//			
//			dp[i] = min;
		}
		
		System.out.println(dp[n]);
	}
}
