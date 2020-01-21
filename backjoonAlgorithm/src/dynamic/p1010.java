package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

// ���̳��� ���α׷���
// �ٸ� ���� ����
// ���� ���� => mCn ���ϱ�. mCn = m-1Cn + m-1Cn-1
public class p1010 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int t = Integer.valueOf(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		// �̸� dp�� ���س���
		BigInteger[][] dp = new BigInteger[30][30]; // 1 �̻� 30 �̸��̱� ������(1 ~ 29). mCn�� ��. y : n, x : m
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
//				for(int k = j; k >= i; k--) {	// n����Ʈ �� �ϳ��� m����Ʈ �� �ϳ��� �����ϰ� �ֱ� ������ 1�� ���� �����Ѵ�. 
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
