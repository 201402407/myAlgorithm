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
	static int[] distance;
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
			distance = new int[n];
			
			for(int i = 0; i < n; i++) {
				graph[i] = new ArrayList<Vertex>();
			}
			
			st = new StringTokenizer(br.readLine());
			int start = Integer.valueOf(st.nextToken());
			int end = Integer.valueOf(st.nextToken());
			// 그래프 생성
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.valueOf(st.nextToken());
				int to = Integer.valueOf(st.nextToken());
				int weight = Integer.valueOf(st.nextToken());
				graph[from].add(new Vertex(to, weight));
			}
			find(start, end, n);
		}
		System.out.println(sb.toString());
	}
	
	// 최단 경로 다 없애야 하나
	// 1. 한 번 다익스트라를 해서 최단 경로 값을 알아냄
	// 2. 경로를 맨 끝 원소부터 제거하면서 재귀호출로 다익스트라 알고리즘을 사용, 최단 경로와 다르면 그냥 리턴하기
	// 3. 최단 경로 리턴
	private static void find(int startPoint, int endPoint, int n) {
		int[] result = dijkstra(startPoint, endPoint, n, Integer.MAX_VALUE);	// endPoint까지의 최단 경로 추출. 첫 함수 실행이라 최대값 임의로 대입
		if(deletePath(startPoint, endPoint, result, distance[endPoint])) {
			if(distance[endPoint] == Integer.MAX_VALUE) {
				sb.append(-1).append("\n");
			}
			else {
				sb.append(distance[endPoint]).append("\n");	
			}	
		}
		else {
			if(distance[endPoint] == Integer.MAX_VALUE) {
				sb.append(-1).append("\n");
			}
			else {
				sb.append(distance[endPoint]).append("\n");	
			}
		}
	}
	
	// 경로 삭제 -> 삭제함 : true. 삭제 못함 : false
	private static boolean deletePath(int startPoint, int endPoint, int[] result, int minDistance) {
		// 경로 삭제
		int endVertex = endPoint;
		while(endVertex != startPoint) {
			int startVertex = result[endVertex];
			if(startVertex == -1) {
				return false;	
			}
			boolean isValid = false; // 그래프 내에 이어지는 Vertex가 존재하는 지 체크
			for(Vertex v : graph[startVertex]) {
				if(v.getVertex() == endVertex) {
					graph[startVertex].remove(v);
					int[] temp = dijkstra(startPoint, endPoint, result.length, minDistance);
					if(temp != null) { // 다른 최단 거리가 또 있는 경우
						deletePath(startPoint, endPoint, temp, minDistance);
						return false;
					}
					else {
						endVertex = startVertex;
						isValid = true;
						break;
					}
					
				}
			}
		}
		return true;
	}
	
	// 처음에 싹 돌려서 나온 최단 경로를 
	private static int[] dijkstra(int startPoint, int endPoint, int n, int minDistance) {
		PriorityQueue<Vertex> queue = new PriorityQueue<>();	// comparable로 객체 우선순위 바꿈
		int[] result = new int[n];	// 최단 경로
		boolean[] visited = new boolean[n];
		Arrays.fill(distance, Integer.MAX_VALUE);
		Arrays.fill(result, -1);
		distance[startPoint] = 0;
		queue.offer(new Vertex(startPoint, distance[startPoint]));	// 출발점 넣기
		
		while(!queue.isEmpty()) { 
			Vertex startVertex = queue.poll();
			startPoint = startVertex.getVertex();
			if(startPoint == endPoint) {
				break;
			}
			if(visited[startPoint])
				continue;
			visited[startPoint] = true;
			
			for(Vertex vertex : graph[startPoint]) {
				int endVertex = vertex.getVertex();
				if(distance[endVertex] > distance[startPoint] + vertex.getWeight()) {
					distance[endVertex] = distance[startPoint] + vertex.getWeight();
					queue.offer(new Vertex(endVertex, distance[endVertex]));
//					System.out.println("startVertex : " + startPoint + ", endVertex : " + endVertex);
					result[endVertex] = startPoint;	// 해당 경로의 마지막 출력
//					System.out.println(distance[endVertex]);
				}
			}
		}
		if(minDistance < distance[endPoint]) { // 거의 최단 경로인 경우 널 리턴
			return null;
		}
		return result;
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
		if(this.weight < v.getWeight()) {
			return -1;
		}
		else
			return 1;
	}
}
