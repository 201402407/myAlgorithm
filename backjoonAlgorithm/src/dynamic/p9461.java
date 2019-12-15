package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// �ĵ��� ����
// ���� ���α׷��� - ��Ģ ã��
// �׻� �̷��� ������ üũ�غ���
public class p9461 {
	static long[] dp;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int tc = Integer.valueOf(st.nextToken());
		dp = new long[101];	// 1 <= N <= 100 �̹Ƿ�
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;
		dp[4] = 2;
		for(int i = 5; i <= 100; i++) {
			dp[i] = dp[i - 1] + dp[i - 5];
		}
		while(tc > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			sb.append(dp[n]).append("\n");
			tc--;
		}
		System.out.println(sb.toString());
	}
}
