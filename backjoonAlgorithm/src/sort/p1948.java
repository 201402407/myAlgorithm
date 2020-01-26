package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 임계경로 문제
// 위상 정렬 문제
// 시작 지점과 끝 지점이 있고, 모든 위상 경로 중 가장 오래 걸린 시간과 경로 개수를 출력하는 문제
// 경로 개수를 구하기 위해서는 (임계 경로를 구하기 위해서는) reverse로 해서 cost를 따져야 한다.
// distance[prev] == distance[next] - weight[next]
// visited 2차원 배열을 설정해서 도로 하나하나 방문 여부를 체크하고, 위 조건을 만족한 도로 중 방문 안된 도로의 개수를 더한다.
public class p1948 {
	static List<Road>[] graph, reverseGraph;
	static int[] distance, indegree, prevPoints;
	static boolean[][] visited;	// reverse의 from , to
	static int count = 0;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		graph = new ArrayList[n + 1];
		reverseGraph = new ArrayList[n + 1];
		distance = new int[n + 1];
		indegree = new int[n + 1];
		prevPoints = new int[n + 1];
		visited = new boolean[n + 1][n + 1];
		for(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<Road>();
			reverseGraph[i] = new ArrayList<Road>();
		}
	
		st = new StringTokenizer(br.readLine());
		int m = Integer.valueOf(st.nextToken());
		while(m --> 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());
			int weight = Integer.valueOf(st.nextToken());
			graph[from].add(new Road(to, weight));
			reverseGraph[to].add(new Road(from, weight));
			indegree[to]++;
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.valueOf(st.nextToken());
		int end = Integer.valueOf(st.nextToken());

		topologicalSort(start, end);
		
		// 출발 지점부터 도착 지점까지 걸리는 시간 중 가장 오래 걸리는 시간
		System.out.println(distance[end]);
		// 오래 걸리는 시간의 경로 개수
		System.out.println(count);
	}
	
	static void topologicalSort(int start, int end) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);	// 시작 지점 넣기
		
		// 위상 정렬을 통해 가장 긴 시간 찾아내기
		while(!queue.isEmpty()) {
			int point = queue.poll();
			for(Road road : graph[point]) {
				indegree[road.end]--;
				if(distance[road.end] < distance[point] + road.weight) {
					distance[road.end] = distance[point] + road.weight;
					prevPoints[road.end] = point;
				}
				if(indegree[road.end] == 0)
					queue.add(road.end);
			}
		}
		
		// 역방향 그래프를 통해 모든 경로 찾기
		queue.add(end);
		while(!queue.isEmpty()) {
			int point = queue.poll();
			for(Road road : reverseGraph[point]) {
				if((distance[point] == distance[road.end] + road.weight) && !visited[point][road.end]) {
					visited[point][road.end] = true;
					count++;
					queue.add(road.end);
				}
			}
		}
	}
}

class Road {
	int end;
	int weight;
	
	Road(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}
}
