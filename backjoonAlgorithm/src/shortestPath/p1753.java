package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 최단 경로 문제
// 다익스트라 알고리즘 ?
// 선형 탐색으로 다음 출발점을 찾는 방법 대신에 우선순위 큐를 사용해서 최단거리 갱신과 동시에 큐에 넣음.
public class p1753 {
	static List<EndVertex> graph[];
	static int[] distance;
	static boolean[] visited;
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		try {
			st = new StringTokenizer(br.readLine());
			int v = Integer.valueOf(st.nextToken());
			int e = Integer.valueOf(st.nextToken());
			// 1부터 시작 v까지 존재하는 그래프
			graph = new ArrayList[v + 1];
			distance = new int[v + 1];
			visited = new boolean[v + 1];
			int startPoint = Integer.valueOf(br.readLine());
			
			for(int i = 1; i <= v; i++) {	// 초기화
				graph[i] = new ArrayList<EndVertex>();
//				distance[i] = Integer.MAX_VALUE;	// 무한대 대신 이거로	
			}
			Arrays.fill(distance, Integer.MAX_VALUE);
			distance[startPoint] = 0;	// 시작 포인트만 0으로 설정
			
			for(int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.valueOf(st.nextToken());
				int to = Integer.valueOf(st.nextToken());
				int weight = Integer.valueOf(st.nextToken());
				EndVertex end = new EndVertex(to, weight);
				graph[from].add(end);
			}
			
			dijkstra(v, startPoint);	// v는 시작 vertex
			
			StringBuilder sb = new StringBuilder();
			for(int i = 1; i < distance.length; i++) {
				int element = distance[i];
				if(element == Integer.MAX_VALUE) 
					sb.append("INF").append("\n");
				else
					sb.append(element).append("\n");	
			}
			System.out.println(sb.toString());
		}
		catch (NumberFormatException e) {
			System.exit(0);
		}
		catch (IOException e) {
			System.exit(0);
		}
	}
	
	private static void dijkstra(int v, int startPoint) {
		PriorityQueue<EndVertex> queue = new PriorityQueue<>();	// comparable로 객체 우선순위 바꿈
		queue.offer(new EndVertex(startPoint, distance[startPoint]));
		
		while(!queue.isEmpty()) { 
			EndVertex startVertex = queue.poll();
			startPoint = startVertex.getEndPoint();
			visited[startPoint] = true;
			// 시작 점 빼고 모든 vertex를 방문하기 전까지 경로 탐색
			for(EndVertex vertex : graph[startPoint]) {
				int endPoint = vertex.getEndPoint();
				if(!visited[endPoint] && distance[endPoint] > distance[startPoint] + vertex.getWeight()) {
					distance[endPoint] = distance[startPoint] + vertex.getWeight();
					queue.offer(new EndVertex(endPoint, distance[endPoint]));	
				}
			}
		}
	}
}

class EndVertex implements Comparable<EndVertex> {
	private int endPoint;
	private int weight;
	
	
	EndVertex(int endPoint, int weight) {
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
	public int compareTo(EndVertex o) {
		if(this.weight < o.getWeight()) {
			return -1;
		}
		else
			return 1;
	}
	
}