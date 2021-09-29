package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Acka
// 백트래킹(DP?)
public class p12996 {
	static long[][][][] dp;
	static final int MOD = 1000000007;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int a = Integer.valueOf(st.nextToken());
		int b = Integer.valueOf(st.nextToken());
		int c = Integer.valueOf(st.nextToken());
		dp = new long[n + 1][a + 1][b + 1][c + 1];	// 중복 방지
		
		if(n > a + b + c) {
			System.out.println(0);
			return;
		}
		
		for(long ele[][][] : dp) {
			for(long ele2[][] : ele) {
				for(long ele3[] : ele2) {
					Arrays.fill(ele3, -1);	// -1로 초기화하지 않으면 사용여부를 세밀하게 파악할 수 없다
				}
			}
		}
		
		System.out.println(backTracking(n, a, b, c));
	}
	
	static long backTracking(int n, int a, int b, int c) {
		if(a < 0 || b < 0 || c < 0) {
			return 0;	// 불가능함
		}
		
		if(n <= 0) {
			if(a == 0 && b == 0 && c == 0) {	// 모두 노래를 불러야 한다?
				return 1;	// 드뎌 하나의 앨범을 만들어따!
			}
			
			return 0;	// 불가능함
		}
		
		if(dp[n][a][b][c] > -1) {
			return dp[n][a][b][c];
		}
		
		long count = 0;
		// 하나의 곡을 만드는 방법은 3명이니까 총 7가지(A, B, C, AB, AC, BC, ABC)
		count += backTracking(n - 1, a - 1, b, c);			// A
		count += backTracking(n - 1, a, b - 1, c);			// B
		count += backTracking(n - 1, a, b, c - 1);			// C
		count += backTracking(n - 1, a - 1, b - 1, c);		// AB
		count += backTracking(n - 1, a - 1, b, c - 1);		// AC
		count += backTracking(n - 1, a, b - 1, c - 1);		// BC
		count += backTracking(n - 1, a - 1, b - 1, c - 1);	// ABC
		count %= MOD;
		
		dp[n][a][b][c] = count;
		return count;
	}
}
