package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 카드 구매하기 문제
// 동적 프로그래밍 - knapsack 0-1 알고리즘 삘??? 
// 가격의 최댓값 구하기 -> n개의 카드 개수를 정확히 맞춰야함
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
		for(int i = 1; i <= n; i++) {	// y축 : 카드팩 인덱스
			for(int j = 1; j <= n; j++) {	// x축 : 카드의 개수가 j인 경우
				if(j < i) {	// 최대 카드 개수보다 카드팩에 들어있는 카드 개수가 작으면
					dp[i][j] = dp[i - 1][j];
				}
				else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - i] + cardPack[i]);	
				}
			}
		}
		
		// bottom-up -> 일차원 배열로도 해결 가능한 문제
//        for(int i=2; i<=n; i++) {
//            for(int j=1; j<i; j++) {
//                if(d[i] < d[i-j] + d[j]) {
//                    d[i] = d[i-j] + d[j];
//                }
//            }
//        }
	}
}
