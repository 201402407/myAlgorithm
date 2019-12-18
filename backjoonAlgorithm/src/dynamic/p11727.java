package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ���� ���α׷��� 2
// 2 x n Ÿ�ϸ� 2
// ���� ���� ä��� ����� ||�� =, �� �� �ִµ�, =�� ���� i-2���� 2�� ���� ����̰�, ||�� i-1���� | �ϳ��� �߰��� ���� �����Ƿ� �� ���� ���� ���� �����̴�.
public class p11727 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		long[] dp = new long[n + 1];
		dp[1] = 1;
		if(n >= 2) {
			dp[2] = 3;	
		}
		for(int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + (dp[i - 2] * 2);
			dp[i] %= 10007;
		}
		System.out.println(dp[n]);
	}
}
