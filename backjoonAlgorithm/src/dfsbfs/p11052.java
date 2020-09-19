package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ī�� �����ϱ� ����
// ���� ���α׷��� - knapsack 0-1 �˰��� ��??? 
// ������ �ִ� ���ϱ� -> n���� ī�� ������ ��Ȯ�� �������
public class p11052 {
	static int[][] dp;
	static int[] cardPack;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		cardPack = new int[n + 1];
		dp = new int[n + 1][n + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			int price = Integer.valueOf(st.nextToken());
			cardPack[i] = price;
		}
		
		knapsack(n);
		System.out.println(dp[n][n]);
	}
	
	public static void knapsack(int n) {
		for(int i = 1; i <= n; i++) {	// y�� : ī���� �ε���
			for(int j = 1; j <= n; j++) {	// x�� : ī���� ������ j�� ���
				if(j < i) {	// �ִ� ī�� �������� ī���ѿ� ����ִ� ī�� ������ ������
					dp[i][j] = dp[i - 1][j];
				}
				else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - i] + cardPack[i]);	
				}
			}
		}
		
		// bottom-up -> ������ �迭�ε� �ذ� ������ ����
//        for(int i=2; i<=n; i++) {
//            for(int j=1; j<i; j++) {
//                if(d[i] < d[i-j] + d[j]) {
//                    d[i] = d[i-j] + d[j];
//                }
//            }
//        }
	}
}
