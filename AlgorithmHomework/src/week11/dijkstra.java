package week11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// 201402407 이해원
// 다익스트라 알고리즘
public class dijkstra {
	static List<EndVertex> graph[];
	static int[] distance, changeDistance;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	public static void main(String args[]) {
		int v = 5;	// Vertex A,B,C,D,E 5개
		int e = 9;	// 9개의 edge
		
		// 1부터 시작 v까지 존재하는 그래프
		graph = new ArrayList[v + 1];	// 인덱스 1 ~ 5까지
		distance = new int[v + 1];
		changeDistance = new int[v + 1];
		visited = new boolean[v + 1];
		int startPoint = 1;	// A부터 시작하므로
		for(int i = 1; i <= v; i++) {	// 초기화
			graph[i] = new ArrayList<EndVertex>();	
		}
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		Arrays.fill(changeDistance, -1);	// -1로 해서 예외 처리
		distance[startPoint] = 0;	// 시작 포인트만 0으로 설정
		EndVertex end;
		// 그래프에 직접 넣기
		end = new EndVertex(2, 10);
		graph[1].add(end);
		end = new EndVertex(3, 3);
		graph[1].add(end);
		end = new EndVertex(3, 1);
		graph[2].add(end);
		end = new EndVertex(4, 2);
		graph[2].add(end);
		end = new EndVertex(2, 4);
		graph[3].add(end);
		end = new EndVertex(4, 8);
		graph[3].add(end);
		end = new EndVertex(5, 2);
		graph[3].add(end);
		end = new EndVertex(5, 7);
		graph[4].add(end);
		end = new EndVertex(4, 9);
		graph[5].add(end);
		
		dijkstra(v, startPoint);
		System.out.println(sb.toString());
	}
	
	private static void dijkstra(int v, int startPoint) {
		sb.append("Dijkstra Algorithm으로 계산한 결과는 다음과 같습니다. \n");
		PriorityQueue<EndVertex> queue = new PriorityQueue<>();	// comparable로 객체 우선순위 바꿈
		queue.offer(new EndVertex(startPoint, distance[startPoint]));
		
		int count = 0;
		while(!queue.isEmpty()) { 
			EndVertex startVertex = queue.poll();
			startPoint = startVertex.getEndPoint();
			if(!visited[startPoint]) {
				visited[startPoint] = true;
				sb.append("___________________________________________________________ \n");
				sb.append("S[").append(count++).append("] : d[").append(getAlphabetFromIndex(startPoint)).append("]")
				.append(" = ").append(distance[startPoint]).append("\n");
				sb.append("----------------------------------------------------------- \n");	
			}
			// 시작 점 빼고 모든 vertex를 방문하기 전까지 경로 탐색
			for(EndVertex vertex : graph[startPoint]) {
				int endPoint = vertex.getEndPoint();
				if(!visited[endPoint] && distance[endPoint] > distance[startPoint] + vertex.getWeight()) {
					changeDistance[endPoint] = distance[endPoint];
					distance[endPoint] = distance[startPoint] + vertex.getWeight();					
					queue.offer(new EndVertex(endPoint, distance[endPoint]));	
				}
			}
			
			int count2 = 0;
//			for(EndVertex end : queue) {
//				int endPoint = end.getEndPoint();
//				if(!visited[endPoint]) {
//					sb.append("Q[").append(count2++).append("] : d[").append(getAlphabetFromIndex(endPoint)).append("]");
//					if(changeDistance[endPoint] != -1) {
//						sb.append(" = ").append(changeDistance[endPoint]).append(" -> ")
//						.append("d[").append(getAlphabetFromIndex(endPoint)).append("] = ").append(distance[endPoint]).append("\n");
//					}
//					else {
//						sb.append(" = ").append(distance[endPoint]).append("\n");
//					}
//				}
//			}
			// 단계 별 우선순위 큐에 들어있는 점(= 아직 탐색하지 않은 점)을 출력하는 코드
			for(int i = 1; i <= v; i++) {
				if(!visited[i]) {
					sb.append("Q[").append(count2++).append("] : d[").append(getAlphabetFromIndex(i)).append("]");
					if(changeDistance[i] != -1) {
						sb.append(" = ").append(changeDistance[i]).append(" -> ")
						.append("d[").append(getAlphabetFromIndex(i)).append("] = ").append(distance[i]).append("\n");
					}
					else {
						sb.append(" = ").append(distance[i]).append("\n");
					}
				}
			}
			sb.append("\n");
			Arrays.fill(changeDistance, -1);	// -1로 해서 예외 처리
		}
	}
	
	private static String getAlphabetFromIndex(int index) {
		switch(index) {
		case 1:
			return "A";
		case 2:
			return "B";
		case 3:
			return "C";
		case 4:
			return "D";
		case 5:
			return "E";
		}
		return "-";
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

