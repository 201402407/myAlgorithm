package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 도시 분할 계획
// 최소 스패닝 트리
// 프림 알고리즘
public class p1647 {
	static List<Vertex1647>[] graph;
	static boolean[] visited;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.valueOf(st.nextToken());
		int e = Integer.valueOf(st.nextToken());
		graph = new ArrayList[v + 1];
		visited = new boolean[v + 1];
		
		for(int i = 0; i <= v; i++) {
			graph[i] = new ArrayList<Vertex1647>();
		}
		
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.valueOf(st.nextToken());
			int end = Integer.valueOf(st.nextToken());
			int weight = Integer.valueOf(st.nextToken());
			graph[start].add(new Vertex1647(start, end, weight));
			graph[end].add(new Vertex1647(end, start, weight));
		}
		
		int result = prim(1, v);
		System.out.println(result);
	}
	
	public static int prim(int start, int v) {
		PriorityQueue<Vertex1647> queue = new PriorityQueue<Vertex1647>();
		int count = 0;
		int weightSum = 0;
		visited[start] = true;
		
		// 시작과 연결된 간선 전부 넣기
		for(Vertex1647 vertex : graph[start]) {
			queue.offer(vertex);
		}
		
		while(!queue.isEmpty()) {
			Vertex1647 nowVertex = queue.poll();
			int startVertex = nowVertex.getStart();
			int endVertex = nowVertex.getEnd();
			int weight = nowVertex.getWeight();
			if(visited[endVertex]) {
				continue;
			}
			else {
				visited[endVertex] = true;
				count++;
				weightSum += weight;
				if(count + 2 == v) {	// 남은 간선 하나는 다른 분할된 하나의 마을을 위해서.
					break;
				}
			}
			
			// 끝 점과 연결된 간선 전부 넣기
			for(Vertex1647 vertex : graph[endVertex]) {
				queue.offer(vertex);
			}
		}
		return weightSum;
	}
}

class Vertex1647 implements Comparable<Vertex1647> {
	private int start;
	private int end;
	private int weight;
	
	Vertex1647(int start, int end, int weight) {
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

	@Override
	public int compareTo(Vertex1647 o) {
		if(this.weight < o.getWeight()) {
			return -1;
		}
		else
			return 1;
	}	
}
