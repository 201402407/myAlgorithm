package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 플로이드 - 와샬
// 회의 준비
public class p2610 {
	static final int INF = 10000000;
	static int n, m;
	static int dfsGroupIndex = 0;
	static int[][] graph;
	static int[] dfs;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.valueOf(br.readLine());
		m = Integer.valueOf(br.readLine());
		graph = new int[n + 1][n + 1];
		dfs = new int[n + 1];
		
		for(int i = 1; i <= n; i++) {
			Arrays.fill(graph[i], INF);
			graph[i][i] = 0;
		}
		
		StringTokenizer st;
		for(int i = 0; i < m; i++) { // index 1부터 시작
			st = new StringTokenizer(br.readLine());
			int first = Integer.valueOf(st.nextToken());
			int last = Integer.valueOf(st.nextToken());
				
			// 양방향 그래프
			graph[first][last] = graph[last][first] = 1;
		}
		
		// 1) 플로이드 알고리즘
		floyd();
		
		// 2) DFS 알고리즘으로 그룹 구분
		for(int i = 1; i <= n; i++) {
			if(dfs[i] != 0) {
				continue;
			}
			
			dfsGroupIndex++;
			dfs(i);
		}
		
		// 3) 구분진 그룹 내 대표 선출
		List<Integer> resultList = new ArrayList<Integer>();
		for(int i = 1; i <= dfsGroupIndex; i++) {
			int result = getRepresentation(i);
			resultList.add(result);
		}
		
		// 4) 출력
		System.out.println(dfsGroupIndex);
		Collections.sort(resultList);
		for(int ele : resultList) {
			System.out.println(ele);
		}
		
//		for(int i = 1; i <= n; i++) {
//			for(int j = 1; j <= n; j++) {
//				System.out.print(graph[i][j] + " ");
//			}
//			System.out.println();
//		}
	}
	
	// DFS탐색
	static void dfs(int start) {
		if(dfs[start] != 0) {	// 이미 그룹에 속해있으면 종료
			return;
		}
		dfs[start] = dfsGroupIndex;
		
		for(int i = 1; i <= n; i++) {
			if(graph[start][i] == 0 || graph[start][i] == INF) {	// 어차피 graph는 2차원 대칭행렬이기 때문에, 하나만 보고 판단해도 된다
				continue;
			}
			
			dfs(i);
		}
		
	}
	
	static int getRepresentation(int groupIndex) {
		int result = INF;
		int resultIndex = 1;
		
		for(int i = 1; i <= n; i++) {	// 전체 사람 중 같은 그룹인 사람을 찾기 위해 순회
			if(dfs[i] != groupIndex) {
				continue;
			}
			
			int distance = 0;
			for(int j = 1; j <= n; j++) {	// 해당 사람과 다른 사람의 의견 전달시간을 비교해서 최소인 시간을 찾기
				if(graph[i][j] == INF) {
					continue;
				}
				
				distance = Math.max(distance, graph[i][j]);	// Math.max()인 이유는 해당 사람이 그룹 내에서 다른 사람과의 의견 전달속도가 최대인 값을 찾아야지 그룹 사람들 중 가장 최소로 전달하는 사람을 대표로 선출할 수 있어서
			}
			
			if(result > distance) {
				result = distance;
				resultIndex = i;	// 해당 사람을 우선 대표라고 생각하자
			}
		}
		
		return resultIndex;
	}
	
	static void floyd() {
		for(int k = 1; k <= n; k++) {	// 경유 지점
			for(int i = 1; i <= n; i++) {	// 출발 지점
				for(int j = 1; j <= n; j++) {	// 도착 지점
					if (graph[i][k] == INF || graph[k][j] == INF) 
						continue;
					
					if(i != j) {
						if(graph[i][j] > graph[i][k] + graph[k][j]) {
							graph[i][j] = graph[i][k] + graph[k][j];
						}
					}
				}
			}
		}
	}
}
