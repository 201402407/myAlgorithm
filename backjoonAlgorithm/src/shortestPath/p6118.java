package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 숨바꼭질
// 최단경로 -> 다익스트라 삘
// 방향 없고 가는 길은 무조건 1씩
// 거리가 먼 것 -> 최대 거리 값 구하기
public class p6118 {
	static List<Integer>[] graph;
//	static boolean[] visited;
	static int[] distance;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		graph = new ArrayList[n + 1];	// 인덱스 1 ~ n
//		visited = new boolean[n + 1];
		distance = new int[n + 1];
		for(int i = 0; i <= n; i++) {	// 초기화
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.valueOf(st.nextToken());
			int end = Integer.valueOf(st.nextToken());
			graph[start].add(end);
			graph[end].add(start);
		}
		
		dijkstra();
		int count = 1;
		int maxIndex = 1;
		for(int i = 2; i <= n; i++) {
			if(distance[i] > distance[maxIndex]) {
				maxIndex = i;
				count = 1;
			}
			else if(distance[i] == distance[maxIndex]) {
				count++;
			}
		}
		System.out.println(maxIndex + " " + distance[maxIndex] + " " + count);
	}
	
	static void dijkstra() {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		Arrays.fill(distance, Integer.MAX_VALUE);
		int start = 1;
		distance[1] = 0;
		queue.offer(1);
		while(!queue.isEmpty()) {
			start = queue.poll(	);
			for(int vertex : graph[start]) {
				if(distance[vertex] > distance[start] + 1) {
					distance[vertex] = distance[start] + 1;
					queue.offer(vertex);
				}
			}
		}
	}
}
