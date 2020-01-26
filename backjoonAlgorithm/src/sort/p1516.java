package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 위상 정렬
// 게임 개발 문제
// 선행작업 있고, 각 건물이 완성되기까지의 최소 시간 -> 선행 작업 최대 비용 distance에 저장하기.
public class p1516 {
	static List<Integer>[] graph;
	static int[] distance;
	static int[] indegree;
	static int[] weights;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		distance = new int[n + 1];
		weights = new int[n + 1];
		indegree = new int[n + 1];
		graph = new ArrayList[n + 1];
		for(int i = 0; i <= n; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.valueOf(st.nextToken());
			weights[i] = weight;
			int count = st.countTokens() - 1;
			indegree[i] = count;	// countTokens 개수 확인해보기
			while(count --> 0) {
				int ele = Integer.valueOf(st.nextToken());
				graph[ele].add(i);
			}
		}
		
		topologicalSort();
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n; i++) {
			sb.append(distance[i]).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void topologicalSort() {
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i = 1; i < graph.length; i++) {	// 선행작업이 없는 작업들 큐에 넣기
			if(indegree[i] == 0) {
				queue.add(i);
				distance[i] = weights[i];
			}
		}
		if(queue.isEmpty()) {	// 위상 정렬이 불가능한지 따지는 조건
			
		}
		while(!queue.isEmpty()) {
			int point = queue.poll();
			for(int nextPoint : graph[point]) {
				indegree[nextPoint]--;
				// 첫 시작부터 distance를 정했기 때문에 다음 지점의 weight를 더하면 된다.
				distance[nextPoint] = Math.max(distance[nextPoint], distance[point] + weights[nextPoint]);
				if(indegree[nextPoint] == 0) {
					queue.add(nextPoint);
				}
			}
		}
	}
}
