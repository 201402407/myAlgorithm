package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 거의 최단 경로
public class p5719 {
	static StringBuilder sb = new StringBuilder();
	static List<Vertex>[] graph;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		while((str = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(str);
			int n = Integer.valueOf(st.nextToken());
			int m = Integer.valueOf(st.nextToken());
			if(n == 0 && m == 0)	// 종료
				break;
			// 초기 설정
			graph = new ArrayList[n];
			int[] distance = new int[n];
			boolean[] visited = new boolean[n];	// 거의 최단 경로이기 때문에 사용안할수도있다.
			for(int i = 0; i < n; i++) {
				graph[i] = new ArrayList<Vertex>();
			}
			Arrays.fill(distance, Integer.MAX_VALUE);
			
			st = new StringTokenizer(br.readLine());
			int start = Integer.valueOf(st.nextToken());
			int end = Integer.valueOf(st.nextToken());
			distance[start] = 0;
			// 그래프 생성
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.valueOf(st.nextToken());
				int to = Integer.valueOf(st.nextToken());
				int weight = Integer.valueOf(st.nextToken());
				graph[from].add(new Vertex(to, weight));
			}
//			dijkstra(start, end, distance, visited, graph);// 다익스트라 탐색 시작
		}
		System.out.println(sb.toString());
	}
	
	private static void find(int startPoint, int endPoint, int n) {
		int result = dijkstra(startPoint, endPoint, n);
	}
	
	// 처음에 싹 돌려서 나온 최단 경로를 
	private static int dijkstra(int startPoint, int endPoint, int n) {
		PriorityQueue<Vertex> queue = new PriorityQueue<>();	// comparable로 객체 우선순위 바꿈
		int answer = Integer.MAX_VALUE;
//		boolean isFirst = true;
		int[] distance = new int[n];
		boolean[] visited = new boolean[n];
		Arrays.fill(distance, Integer.MAX_VALUE);
//		List<Integer> result = new ArrayList<Integer>();
		queue.offer(new Vertex(startPoint, distance[startPoint]));	// 출발점 넣기
		
		while(!queue.isEmpty()) { 
			Vertex startVertex = queue.poll();
			startPoint = startVertex.getVertex();
//			graph[startPoint].remove(vertex);
			if(startPoint == endPoint) {
				return distance[endPoint];
			}
			if(visited[startPoint])
				continue;
			visited[startPoint] = true;
		
			for(Vertex vertex : graph[startPoint]) {
				int endVertex = vertex.getVertex();
				if(distance[endVertex] > distance[startPoint] + vertex.getWeight()) {
					distance[endVertex] = distance[startPoint] + vertex.getWeight();
					queue.offer(new Vertex(endVertex, distance[endVertex]));
					
					
				}
			}
			
//			for(Vertex vertex : graph[startPoint]) {
//				int endVertex = vertex.getVertex();
//				if(distance[endVertex] > distance[startPoint] + vertex.getWeight()) {
//					if(endPoint == endVertex) {
//						if(!isFirst)
//							answer = distance[endVertex];
//						else
//							isFirst = false;
//					}
//					distance[endVertex] = distance[startPoint] + vertex.getWeight();
//					
//					queue.offer(new Vertex(endVertex, distance[endVertex]));
//				}
//				else if(distance[endVertex] == distance[startPoint] + vertex.getWeight()) {
//					continue;
//				}
//				else {
//					System.out.println("endPoint : " + endPoint + " , endVertex : " + endVertex);
//					if(endPoint == endVertex) {
//						System.out.println("before : " + answer);
//						answer = Math.min(answer, distance[startPoint] + vertex.getWeight());
//						System.out.println("after : " + answer);
//					}
//				}
//			}
		}
		if(answer == Integer.MAX_VALUE)
			answer = -1;
		sb.append(answer).append("\n");
	}
}

class Vertex implements Comparable<Vertex> {
	private int vertex;
	private int weight;
	
	Vertex(int vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}
	
	public int getVertex() {
		return vertex;
	}
	public void setVertex(int vertex) {
		this.vertex = vertex;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	// 오름차순
	@Override
	public int compareTo(Vertex v) {
		if(this.vertex < v.getVertex()) {
			return -1;
		}
		else
			return 1;
	}
	
	
}
