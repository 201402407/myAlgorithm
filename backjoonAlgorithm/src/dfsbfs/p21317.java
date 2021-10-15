package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 징검다리 건너기
// DFS
public class p21317 {
	static int[][] jumps;
	static int[][] dp;
	static int n, k;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());
		jumps = new int[n][2];	// index 0 : 작은점프, index 1 : 큰점프
		dp = new int[n][2];	// 0 : K점프 안한경우	1 : K점프한 경우
		for(int i = 0; i < n - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int smallJump = Integer.valueOf(st.nextToken());
			int mediumJump = Integer.valueOf(st.nextToken());
			jumps[i][0] = smallJump;
			jumps[i][1] = mediumJump;
		}
		
		k = Integer.valueOf(br.readLine());
		dfs(0, 0, 0);
		int min = dp[n - 1][0] == 0 ? dp[n - 1][1] : (dp[n - 1][1] == 0 ? dp[n - 1][0] : Math.min(dp[n - 1][0], dp[n - 1][1]));
		System.out.println(min);
	}
	
	// hasBigJumped -> 0: 매우 큰 점프 사용X / 1: 매우 큰 점프 사용
	static void dfs(int stoneIndex, int energy, int hasBigJumped) {
		if(stoneIndex >= n) {	// 돌밖으로 벗어나는거면 의미없음
			return;
		}
		
		// 최소 에너지를 찾아야 하기 때문에 현재 최소 에너지보다 크면 DFS할 필요가 없다.
		if(dp[stoneIndex][hasBigJumped] == 0) {
			dp[stoneIndex][hasBigJumped] = energy;
		}
		else {
			if(dp[stoneIndex][hasBigJumped] < energy) {
				return;
			}
			
			dp[stoneIndex][hasBigJumped] = energy;
		}

		// 1) 작은점프
		dfs(stoneIndex + 1, energy + jumps[stoneIndex][0], hasBigJumped);
		// 2) 큰점프
		dfs(stoneIndex + 2, energy + jumps[stoneIndex][1], hasBigJumped);
		// 3) 매우큰점프
		if(hasBigJumped == 0) {
			dfs(stoneIndex + 3, energy + k, 1);
		}
	}
}
