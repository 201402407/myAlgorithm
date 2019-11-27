package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 최소비용 구하기
// 다익스트라 알고리즘
public class p1916 {
	static boolean[] visited;
	static int[] distance;
	static List<Edge>[] graph;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.valueOf(st.nextToken()); // vertex
		
		st = new StringTokenizer(br.readLine());
		int e = Integer.valueOf(st.nextToken());
		
		distance = new int[v + 1];	// 인덱스가 1 ~ v까지
		visited = new boolean[v + 1];
		graph = new ArrayList[v + 1];
		
		// 초기화
		for(int i = 0; i <= v; i++) {
			graph[i] = new ArrayList<Edge>();
		}
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());
			int weight = Integer.valueOf(st.nextToken());
			Edge edge = new Edge(to, weight);
			graph[from].add(edge);
		}
		st = new StringTokenizer(br.readLine());
		int startPoint = Integer.valueOf(st.nextToken());
		int endPoint = Integer.valueOf(st.nextToken());
		dijkstra(v, startPoint, endPoint);
		System.out.println(distance[endPoint]);
		
	}
	
	private static void dijkstra(int v, int startPoint, int endPoint) {
		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		distance[startPoint] = 0;
		Edge startEdge = new Edge(startPoint, distance[startPoint]);
		q.offer(startEdge);
		
		while(!q.isEmpty()) {
			Edge fromEdge = q.poll();
			int fromVertex = fromEdge.getEndPoint();
			int fromWeight = fromEdge.getWeight();
			visited[fromVertex] = true;
			for(Edge edge : graph[fromVertex]) {
				int toVertex = edge.getEndPoint();
				if(!visited[toVertex] && distance[toVertex] > distance[fromVertex] + edge.getWeight()) {
					distance[toVertex] = distance[fromVertex] + edge.getWeight();
					q.offer(new Edge(toVertex, distance[toVertex]));
				}
			}
		}
	}
}

class Edge implements Comparable<Edge> {
	private int endPoint;
	private int weight;
	
	
	Edge(int endPoint, int weight) {
		this.weight = weight;
		this.endPoint = endPoint;
	}
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(int endPoint) {
		this.endPoint = endPoint;
	}

	// 이거에 따라 우선순위 큐의 우선순위도 바뀜
	// 내림차순 : 우선순위는 높은 거부터
	// 오름차순 : 우선순위는 낮은 거부터
	@Override
	public int compareTo(Edge o) {
		if(this.weight < o.getWeight()) {
			return -1;
		}
		else
			return 1;
	}
}