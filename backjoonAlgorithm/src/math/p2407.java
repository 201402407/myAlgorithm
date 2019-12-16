package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

// 조합 문제
// 수학 - 동적 프로그래밍 사용
// Bottom-up 
public class p2407 {
	static BigInteger[][] dp;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		dp = new BigInteger[n + 1][n + 1];
		for(int i = 0; i <= n; i++) {
			Arrays.fill(dp[i], BigInteger.ZERO);	
		}
		
		dp[1][0] = BigInteger.ONE;
		dp[1][1] = BigInteger.ONE;
		
		for(int i = 2; i <= n; i++) {
			dp[i][0] = BigInteger.ONE;
			int index = i;
			if(i == n) {
				index = m;
			}
			for(int j = 1; j <= index; j++) {
				if(j == i) {
					dp[i][j] = BigInteger.ONE;
				}
				else {
					dp[i][j] = dp[i - 1][j - 1].add(dp[i - 1][j]);	
				}
			}
		}
		
		// 이런 방식도 가능
		// nCr = nPr / r! 이므로 
//		BigInteger answer = new BigInteger("1");
//        for(int i=n; i> m; --i){
//            answer = answer.multiply(BigInteger.valueOf(i));
//        }
//        for(int i=n-m; i>1; --i){
//            answer = answer.divide(BigInteger.valueOf(i));
//        }
		System.out.println(dp[n][m]);
	}
}
