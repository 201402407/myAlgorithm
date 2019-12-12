package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//도시 분할 계획
//최소 스패닝 트리
//크루스칼  알고리즘

public class p1647_kruskal {
	static PriorityQueue<Vertex1647_2> graph;
	static boolean[] visited;
	static int[] parent;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.valueOf(st.nextToken());
		int e = Integer.valueOf(st.nextToken());
		graph = new PriorityQueue<Vertex1647_2>();
		visited = new boolean[v + 1];
		parent = new int[v + 1];
		
		for(int i = 1; i <= v; i++) {
			parent[i] = i;	// 부모 노드
		}
		
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.valueOf(st.nextToken());
			int end = Integer.valueOf(st.nextToken());
			int weight = Integer.valueOf(st.nextToken());
			graph.offer(new Vertex1647_2(start, end, weight));
//			graph[start].add(new Vertex1647(start, end, weight));
//			graph[end].add(new Vertex1647(end, start, weight));
		}
		
		int count = 0;
		int sum = 0;
		while(!graph.isEmpty()) {
			Vertex1647_2 vertex = graph.poll();
			int start = vertex.getStart();
			int end = vertex.getEnd();
			if(!isSameParent(start, end)) {	// 두 노드의 부모가 다른 경우 (사이클 여부를 판단하기 위함)
				sum += vertex.getWeight();
				count++;
				union(start, end);
				if(count + 2 == v) {
					break;
				}
			}
		}
		System.out.println(sum);
		
		
	}
	
	// 두 노드의 부모 노드가 일치하는지 찾는 함수
	public static boolean isSameParent(int x, int y) {
		x = find(x);
		y = find(y);
		return x == y ? true : false;
	}
	
	// 두 노드를 합치는 함수
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x != y)
			parent[y] = x;
	}
	
	// 부모 노드를 찾는 함수
	public static int find(int index) {
		if(parent[index] == index) {
			return index;
		}
		return parent[index] = find(parent[index]);	// 부모의 부모를 탐색해서 parent를 변경시키고 리턴
	}
}

class Vertex1647_2 implements Comparable<Vertex1647_2> {
	private int start;
	private int end;
	private int weight;
	
	Vertex1647_2(int start, int end, int weight) {
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
	public int compareTo(Vertex1647_2 o) {
		if(this.weight < o.getWeight()) {
			return -1;
		}
		else
			return 1;
	}	
}