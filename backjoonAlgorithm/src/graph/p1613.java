package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 역사
// 벨만포드 알고리즘 
public class p1613 {
	static boolean[][] history;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		
		history = new boolean[n + 1][n + 1]; // 1부터 시작 
		for(int i = 0 ; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int before = Integer.valueOf(st.nextToken());
			int after = Integer.valueOf(st.nextToken());
			history[before][after] = true;	// 단방향 그래프 
		}
		
		bellmanFord(n);
		
		StringBuilder sb = new StringBuilder();
		int s = Integer.valueOf(br.readLine());
		
		for(int i = 0; i < s; i++) {
			st = new StringTokenizer(br.readLine());
			int before = Integer.valueOf(st.nextToken());
			int after = Integer.valueOf(st.nextToken());
			
			// 역사의 정순과 역순을 전부 체크해야 함.
            boolean jung = history[before][after];
            boolean reverse = history[after][before];
            
            if(jung && !reverse) {
                sb.append("-1");
            }
            else if(!jung && reverse) {
                sb.append("1");
            }
            else if(!jung && !reverse) {
                sb.append("0");
            }
			
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	// 벨만-포드 알고리즘 수행 
	static void bellmanFord(int n) {
		for(int i = 1; i <= n; i++) {    // 경유 지점
			for(int j = 1; j <= n; j++) { // 출발 지점
                if(!history[j][i]) continue;
				for(int k = 1; k <= n; k++) { // 도착 지점
                    if(history[i][k]) history[j][k] = true;
				}
			}
		}
	}
}