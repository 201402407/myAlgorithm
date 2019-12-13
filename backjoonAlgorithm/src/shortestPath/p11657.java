package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 타임머신 문제
// 벨만 포드 알고리즘 사용해보겠습니다
// 음의 가중치가 존재하기 때문에
public class p11657 {
	static List<Vertex11657> graph;	// 모든 vertex를 담는 리스트
	static int[] distance;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		graph = new ArrayList<Vertex11657>();
		distance = new int[n + 1];	// 1번부터 n번 도시
		for(int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.valueOf(st.nextToken());
			int end = Integer.valueOf(st.nextToken());
			int weight = Integer.valueOf(st.nextToken());
			graph.add(new Vertex11657(start, end, weight));
		}
		
		boolean isMinusCycle = bellman(n, m, 1);
		StringBuilder sb = new StringBuilder();
		if(!isMinusCycle) {
			sb.append("-1");
		}
		else {
			for(int i = 2; i <= n; i++) {
				if(distance[i] == Integer.MAX_VALUE) {
					sb.append("-1");
				}	
				else  {
					sb.append(distance[i]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	public static boolean bellman(int n, int m, int startPoint) {
		// 1단계 : 시작 점을 0, 나머지 점을 무한대로 만들기
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[startPoint] = 0;
		
		// 2단계 : 전체 점(n) - 1번 만큼 전체 Vertex를 탐색하기
		for(int i = 1; i < n; i++) {
			boolean isChange = false;	// 전체 간선을 돌면서 최단 경로가 새로 갱신되는지 구별하는 boolean 변수
			for(int j = 0; j < m; j++) {
				Vertex11657 vertex = graph.get(j);
				// 우선, 시작 점이 한 번도 방문하지 않았던 점이면 안되고(맨 처음 시작 점부터 해당 끝 점까지의 최단 가중치 경로를 찾아야 하기 때문에)
				// 다른 기존 경로보다 짧은 경우를 찾아야 하므로
				if(distance[vertex.getStart()] != Integer.MAX_VALUE && 
						distance[vertex.getStart()] + vertex.getWeight() < distance[vertex.getEnd()]) {
					distance[vertex.getEnd()] = distance[vertex.getStart()] + vertex.getWeight();
					isChange = true;
				}
			}
			if(!isChange) {	// 최단 경로 갱신 여부
				break;
			}
		}
		
		// 3단계 : 음수 가중치 사이클 점검
		for(int i = 0; i < m; i++) {
			Vertex11657 vertex = graph.get(i);
			// 우선, 시작 점이 한 번도 방문하지 않았던 점이면 안되고(맨 처음 시작 점부터 해당 끝 점까지의 최단 가중치 경로를 찾아야 하기 때문에)
			// 다른 기존 경로보다 짧은 경우를 찾아야 하므로
			if(distance[vertex.getStart()] != Integer.MAX_VALUE && 
					distance[vertex.getStart()] + vertex.getWeight() < distance[vertex.getEnd()]) {
				return false;	// 음의 가중치 존재
			}
		}
		
		return true;	// 음의 가중치 존재 X
	}
}

class Vertex11657 {
	private int start;
	private int end;
	private int weight;
	
	Vertex11657(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
}
