package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 스티커
// 동적 프로그래밍 -> RGB 문제와 유사?! -> 풀이 조금 봄
// 연속 for문으로 2차원 배열 탐색 시 y축보다 x축을 연속으로 탐색하는 것이 더 효율적
public class p9465 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.valueOf(st.nextToken());
		for(int tc = 0; tc < t; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			int[][] sticker = new int[2][n + 1];
			int[][] dp = new int[2][n + 1];	// [][0] : 그 스티커까지 선택했을 때의 최대 값. [][1] : 이 스티커까지 선택했을 때의 최대 값.
			// 입력
			// 첫 번째 줄
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= n; i++) {
				int upElement = Integer.valueOf(st.nextToken());
				sticker[0][i] = upElement;
			}
			// 두 번째 줄
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= n; i++) {
				int downElement = Integer.valueOf(st.nextToken());
				sticker[1][i] = downElement;
			}
			
			// 초기 값 설정
			dp[0][1] = sticker[0][1];
			dp[1][1] = sticker[1][1];
			// dp 계싼
			for(int i = 2; i <= n; i++) {
				// 1번째 전에서 선택을 안하기 때문에 2번째 전에서 아무거나 선택해도 상관이 없다.
				// □□■           /  ■□■
				// ■□□           /  □■□
				// 이 두 가지의 경우 중 큰 경우로 dp를 고정
				dp[0][i] = Math.max(dp[1][i - 2], dp[1][i - 1]) + sticker[0][i];
				dp[1][i] = Math.max(dp[0][i - 2], dp[0][i - 1]) + sticker[1][i];
			}
//			System.out.println(Math.max(dp[n][0], dp[n][1]));
			sb.append(Math.max(dp[0][n], dp[1][n])).append("\n");
		}
		System.out.println(sb.toString());
	}
}
