package dynamic;

import java.util.Scanner;

// ���� ���α׷���
// ������
public class p1309 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		// int[][] dp = new int[n + 1][3];
		int[] lionCnt = new int[n + 1];
		//Arrays.fill(dp[1], 1);
		lionCnt[0] = 1;
		lionCnt[1] = 3;
		
//		 dp[k-1][0] = lionCnt[k-2] �� ����.
//		 ���ʿ� ���� ���� �����ʿ� ���� ����� ������ ����.
		for(int i = 2; i <= n; i++) {
//			1) ���� : dp[k][0] = dp[k-1][0] + dp[k-1][3] + dp[k-1][2]
//			-> ���� ĭ�� ���ڸ� ���� ������, ���� ĭ���� 1)���� 2)���� 3)������ ���� ����� ���� ���� �� �ִ�.
//			dp[i][0] = dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2];
//			dp[i][0] %= 9901;
			
//			2) ���� : dp[k][1] = dp[k-1][0] + dp[k-1][2]
//			-> ���� ĭ�� ���ڸ� ���� ������, ���� ĭ���� 1)���� 2)������ ����� ��
//			dp[i][1] = lionCnt[i - 2] + dp[i - 1][2];
//			dp[i][1] %= 9901;
//			3) ������ : dp[k][2] = dp[k-1][0] + dp[k-1][1]
//			-> ������ ĭ�� ���ڸ� ���� ������, ���� ĭ���� 1)���� 2)���� ����� ��
//			dp[i][2] = lionCnt[i - 2] + dp[i - 1][1];
//			dp[i][2] %= 9901;
			
			lionCnt[i] = lionCnt[i - 2] + (2 * lionCnt[i - 1]);
			lionCnt[i] %= 9901;
		}
		
		System.out.println(lionCnt[n]);
	}
}
