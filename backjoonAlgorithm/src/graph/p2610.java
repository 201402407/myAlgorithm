package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 플로이드 - 와샬
// 회의 준비
public class p2610 {
	static int n, m;
	static int[][] graph;
	static int[][] distances;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.valueOf(br.readLine());
		m = Integer.valueOf(br.readLine());
		graph = new int[n][n];
		distances = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			Arrays.fill(graph[i], 10000000);
			graph[i][i] = 0;
		}
		
		StringTokenizer st;
		for(int i = 0; i < m; i++) { // index 1부터 시작
			st = new StringTokenizer(br.readLine());
			int first = Integer.valueOf(st.nextToken()) - 1;
			int last = Integer.valueOf(st.nextToken()) - 1;
				
			// 양방향 그래프
			graph[first][last] = 1;
			graph[last][first] = 1;
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		floyd();
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void floyd() {
		for(int k = 0; k < n; k++) {	// 경유 지점
			for(int i = 0; i < n; i++) {	// 출발 지점
				if(k == i) {
					continue;
				}
				
				for(int j = 0; j < n; j++) {	// 도착 지점
					if(i != j && j != k) {
						if(graph[i][j] > graph[i][k] + graph[k][j]) {
							graph[i][j] = graph[i][k] + graph[k][j];
						}
					}
				}
			}
		}
	}
}
