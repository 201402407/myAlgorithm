package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// RGB 거리
// 동적 프로그래밍
public class p1149 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int[][] map = new int[n + 1][3];
		int[][] dp = new int[n + 1][3];
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				int num = Integer.valueOf(st.nextToken());
				map[i][j] = num;
			}
		}
		// dp 시작
		for(int i = 1; i <= n; i++) {
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + map[i][0];
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + map[i][1];
			dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + map[i][2];
		}
		
		int result = Integer.MAX_VALUE;
		for(int i = 0; i < 3; i++) {
			result = Math.min(result, dp[n][i]);
		}
		System.out.println(result);
		
	}
}
