package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

// 다이나믹 프로그래밍
// 다리 놓기 문제
// 조합 문제 => mCn 구하기. mCn = m-1Cn + m-1Cn-1
public class p1010 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int t = Integer.valueOf(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		// 미리 dp로 구해놓기
		BigInteger[][] dp = new BigInteger[30][30]; // 1 이상 30 미만이기 때문에(1 ~ 29). mCn의 값. y : n, x : m
		dp[1][0] = BigInteger.ONE;
		dp[1][1] = BigInteger.ONE;
//		for(int i = 1; i < 30; i++) {
//			Arrays.fill(dp[i], BigInteger.ZERO); 
//		}
		for(int i = 2; i < 30; i++) {
			dp[i][0] = BigInteger.ONE;
			
			for(int j = 1; j <= i; j++) {
				if(j == i) {
					dp[i][j] = BigInteger.ONE;
				}
				else {
					dp[i][j] = dp[i - 1][j - 1].add(dp[i - 1][j]);
				}
			}
		}
		
//		for(int i = 1; i < 30; i++) {	// n
//			dp[i][i] = 1;
//			for(int j = i + 1; j < 30; j++) {	// m
//				for(int k = j; k >= i; k--) {	// n사이트 중 하나가 m사이트 중 하나를 연결하고 있기 때문에 1을 빼고 시작한다. 
//					dp[i][j] += dp[i - 1][k];
//				}
//			}
//		}
		
		for(int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			int m = Integer.valueOf(st.nextToken());
			sb.append(dp[m][n]).append("\n");
		}
		System.out.println(sb.toString());
	}
}
