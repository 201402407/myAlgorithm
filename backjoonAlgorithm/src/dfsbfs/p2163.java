package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// ���ݷ� �ڸ���
// ���� ���α׷��� -> �� �پ� �ڸ��� ���� ���ؼ� ���� �ּ��� ���� dp�� ��Ҵµ�, �� ȿ������ ������ �ִ�.
// ���� = n * m - 1 �ϸ� ���´�..
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
