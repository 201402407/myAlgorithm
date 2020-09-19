package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 초콜렛 자르기
// 동적 프로그래밍 -> 한 줄씩 자르는 것을 비교해서 가장 최소인 값을 dp로 삼았는데, 더 효율적인 공식이 있다.
// 정답 = n * m - 1 하면 나온다..
public class p2163 {
	static int[][] dp;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		dp = new int[n + 1][m + 1];
		
		for(int i = 1; i <= n; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
			for(int j = 1; j <= m; j++) {
				if(i == 1) {
					dp[i][j] = j - 1;
				}
				else if(j == 1) {
					dp[i][j] = i - 1;
				}
				else {
					for(int k = 1; k < j; k++) {
						dp[i][j] = Math.min(dp[i][j], dp[i][j - k] + dp[i][k] + 1);	
					}
					
				}
			}
		}
		
		System.out.println(dp[n][m]);
	}
}
