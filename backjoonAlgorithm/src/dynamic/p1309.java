package dynamic;

import java.util.Scanner;

// 동적 프로그래밍
// 동물원
public class p1309 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		// int[][] dp = new int[n + 1][3];
		int[] lionCnt = new int[n + 1];
		//Arrays.fill(dp[1], 1);
		lionCnt[0] = 1;
		lionCnt[1] = 3;
		
//		 dp[k-1][0] = lionCnt[k-2] 과 같다.
//		 왼쪽에 놓는 경우와 오른쪽에 놓는 경우의 개수는 같다.
		for(int i = 2; i <= n; i++) {
//			1) 양쪽 : dp[k][0] = dp[k-1][0] + dp[k-1][3] + dp[k-1][2]
//			-> 양쪽 칸에 사자를 넣지 않으면, 이전 칸에서 1)양쪽 2)왼쪽 3)오른쪽 전부 경우의 수를 더할 수 있다.
//			dp[i][0] = dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2];
//			dp[i][0] %= 9901;
			
//			2) 왼쪽 : dp[k][1] = dp[k-1][0] + dp[k-1][2]
//			-> 왼쪽 칸에 사자를 넣지 않으면, 이전 칸에서 1)양쪽 2)오른쪽 경우의 수
//			dp[i][1] = lionCnt[i - 2] + dp[i - 1][2];
//			dp[i][1] %= 9901;
//			3) 오른쪽 : dp[k][2] = dp[k-1][0] + dp[k-1][1]
//			-> 오른쪽 칸에 사자를 넣지 않으면, 이전 칸에서 1)양쪽 2)왼쪽 경우의 수
//			dp[i][2] = lionCnt[i - 2] + dp[i - 1][1];
//			dp[i][2] %= 9901;
			
			lionCnt[i] = lionCnt[i - 2] + (2 * lionCnt[i - 1]);
			lionCnt[i] %= 9901;
		}
		
		System.out.println(lionCnt[n]);
	}
}
