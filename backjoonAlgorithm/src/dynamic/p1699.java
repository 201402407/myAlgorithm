package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// ���� ���α׷��� 
// �������� ��
// dp[n] = (a = n-1 ~ n/2, b = n - a) �� ��, dp[a] + dp[b] �� �ּڰ�  
public class p1699 {
	static int[] dp;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		dp = new int[100001];
		
//		double sqrt = Math.sqrt((double) n);
		
		// ������ �����ϴ� ���� ���� ���� -> ���ص��ȴ�.  
		// ���, 1�� �� �� �����ϴ��� �ʱ� ������ ���ش�. (�ִ����� �ʱ� ����)
		for(int i = 1; i <= n; i++) {
			dp[i] = i;
		}
		
		for(int i = 1; i <= n; i++) {
			
			// �ּҰ� �Ǵ� ������ = n�� ���� �� �� �� ������ + n - �������� �ּ��� ���� 
			for(int j = 1; j * j <= i; j++) {
				if(dp[i] > dp[i - (j * j)] + 1) { // 
					dp[i] = dp[i - (j * j)] + 1;
				}
			}
			
//			int min = Integer.MAX_VALUE;
//			for(int a = i / 2; a <= i - 1; a++) {
//				int b = i - a;
//				
//				if(dp[a] + dp[b] < min) {
//					min = dp[a] + dp[b];
//				}
//			}
//			
//			dp[i] = min;
		}
		
		System.out.println(dp[n]);
	}
}
