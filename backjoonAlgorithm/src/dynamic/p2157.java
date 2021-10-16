package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 여행
// DP
public class p2157 {
	static int n, m, k;
	static int[][] graph;
	static int[][] dp;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		k = Integer.valueOf(st.nextToken());
		graph = new int[n + 1][n + 1];
		dp = new int[n + 1][n + 1];	// y: 해당 도시 포함 방문한 도시 개수, x: 현재 도시, value: 가장 높은 점수
		
		// 입력 값 받기
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.valueOf(st.nextToken());
			int end = Integer.valueOf(st.nextToken());
			int score = Integer.valueOf(st.nextToken());
			graph[start][end] = Math.max(graph[start][end], score);
		}
		
		setDP();
		int result = 0;
		for(int i = 1; i <= m; i++) {
			result = Math.max(result, dp[i][n]);
		}
		
		System.out.println(result);
	}
	
	static void setDP() {
		for(int i = 0; i < n; i++) {
			Arrays.fill(dp[i], -1);	// 미방문(또는 불가능)한 건 -1로 정의
		}
		
		dp[1][1] = 0;	// 초기 시작값
		
		for(int start = 1; start < n; start++) {	// 시작지점. 첫 출발이 아닌 경유지점도 해당됨. 즉, 여기 지점에서 다른 지점으로 이동하는 경우
			for(int cityCount = 1; cityCount < m; cityCount++) {	// m개 이하의 도시이므로, m-1개까지 돌려야 도착지 1개 도시를 포함해 m개 도시 이하가 가능하다
				if(dp[cityCount][start] == -1) {
					continue;	// 즉, cityCount만큼 도시를 방문했을 때, start지점에 올 수 없으면 굳이 시작점을 잡고 개수파악할 필요가 없다.
				}
				
				for(int end = start + 1; end <= n; end++) {
					if(graph[start][end] == 0) {
						continue;	// 비행기 이동이 불가능한 항로면 패스
					}
					
					// dp세팅
					dp[cityCount + 1][end] = Math.max(dp[cityCount + 1][end], dp[cityCount][start] + graph[start][end]);
				}
			}
			
		}
		
		
//		if(cityCount >= m) {
//			return;
//		}
//		if(start == n) {	// 도착 도시에 도착
//			return;
//		}
//		
//		for(int end = start + 1; end <= n; end++) {
//			int graphScore = graph[start][end];
//
//			if(dp[cityCount + 1][end] > score + graphScore) {
//				continue;
//			}
//			
//			dp[cityCount + 1][end] = score + graphScore;
//			setDP(end, cityCount + 1, score + graphScore);
//		}
	}
}
