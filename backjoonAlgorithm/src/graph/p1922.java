package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 네트워크 연결
// 최소 스패닝 트리
// 프림 알고리즘
public class p1922 {
	static List<Vertex1922>[] graph;
	static boolean[] visited;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.valueOf(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int e = Integer.valueOf(st.nextToken());
		graph = new ArrayList[v + 1];	// 인덱스 1 ~ v까지
		visited = new boolean[v + 1];
		for(int i = 0; i <= v; i++) {	// 초기화
			graph[i] = new ArrayList<Vertex1922>();
		}
		
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.valueOf(st.nextToken());
			int end = Integer.valueOf(st.nextToken());
			int weight = Integer.valueOf(st.nextToken());
			// 양방향 Vertex이기 때문
			graph[start].add(new Vertex1922(start, end, weight));
			graph[end].add(new Vertex1922(end, start, weight));
		}
		
		int result = prim(1, v);
		System.out.println(result);
	}
	
	public static int prim(int start, int v) {
		PriorityQueue<Vertex1922> queue = new PriorityQueue<Vertex1922>();
		int count = 0;
		int weightSum = 0;
		visited[start] = true;
		for(Vertex1922 vertex : graph[start]) {
			queue.offer(vertex);
		}
		
		while(!queue.isEmpty()) {
			Vertex1922 vertex = queue.poll();
			int startVertex = vertex.getStart();
			int endVertex = vertex.getEnd();
			int weight = vertex.getWeight();
			if(visited[endVertex]) {	// 끝 점을 한 번 방문했던 경우라면
				continue;
			}
			else {
				visited[endVertex] = true;
				count++;
				weightSum += weight;
				if(count + 1 == v) {	// 가장 최소 경우인 전체 Vertex보다 1 적게 연결한 경우
					break;
				}
			}
			
			// 끝 점부터 시작되는 모든 간선을 큐에 집어넣기
			for(Vertex1922 nextVertex : graph[endVertex]) {
				queue.offer(nextVertex);
			}
		}
		return weightSum;
	}
}

class Vertex1922 implements Comparable<Vertex1922> {
	private int start;
	private int end;
	private int weight;
	
	Vertex1922(int start, int end, int weight) {
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
	public int compareTo(Vertex1922 o) {
		if(this.weight < o.getWeight()) {
			return -1;
		}
		else
			return 1;
	}
	
	
}