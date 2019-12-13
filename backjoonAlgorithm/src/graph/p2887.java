package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 행성 터널 문제
// 최소 스패닝 트리 구하기
// 흠.. 프림 ? 크루스칼 ?
public class p2887 {
	static ArrayList<Vertex2887>[] graph;
	static ArrayList<Planet> universe;
	static boolean[] visited;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		graph = new ArrayList[n + 1];
		visited = new boolean[n + 1];
		universe = new ArrayList<Planet>();
		
		for(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<Vertex2887>();
		}
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken());
			int y = Integer.valueOf(st.nextToken());
			int z = Integer.valueOf(st.nextToken());
			Planet planet = new Planet(i, x, y, z);
			universe.add(planet);
		}
		
		// x축 정렬해서 간선 생성(양방향)
		Collections.sort(universe, new Comparator<Planet>() {
			@Override
			public int compare(Planet o1, Planet o2) {
				if(o1.x < o2.x) {
					return -1;
				}
				else
					return 1;
			}
		});
		Planet planetA = universe.get(0);
		for(int i = 1; i < n; i++) {
			Planet planetB = universe.get(i);
			int start = planetA.num;
			int end = planetB.num;
			int weight = Math.abs(planetA.x - planetB.x);
			graph[start].add(new Vertex2887(end, weight));
			graph[end].add(new Vertex2887(start, weight));
			planetA = planetB;	// 버블 정렬 식으로 진행
		}
		
		// y축 정렬해서 간선 생성(양방향)
		Collections.sort(universe, new Comparator<Planet>() {
			@Override
			public int compare(Planet o1, Planet o2) {
				if(o1.y < o2.y) {
					return -1;
				}
				else
					return 1;
			}
		});
		planetA = universe.get(0);
		for(int i = 1; i < n; i++) {
			Planet planetB = universe.get(i);
			int start = planetA.num;
			int end = planetB.num;
			int weight = Math.abs(planetA.y - planetB.y);
			graph[start].add(new Vertex2887(end, weight));
			graph[end].add(new Vertex2887(start, weight));
			planetA = planetB;	// 버블 정렬 식으로 진행
		}
		
		// z축 정렬해서 간선 생성(양방향)
		Collections.sort(universe, new Comparator<Planet>() {
			@Override
			public int compare(Planet o1, Planet o2) {
				if(o1.z < o2.z) {
					return -1;
				}
				else
					return 1;
			}
		});
		planetA = universe.get(0);
		for(int i = 1; i < n; i++) {
			Planet planetB = universe.get(i);
			int start = planetA.num;
			int end = planetB.num;
			int weight = Math.abs(planetA.z - planetB.z);
			graph[start].add(new Vertex2887(end, weight));
			graph[end].add(new Vertex2887(start, weight));
			planetA = planetB;	// 버블 정렬 식으로 진행
		}
		
		int result = prim(n, 1);
		System.out.println(result);
	}
	
	public static int prim(int n, int startPoint) {
		PriorityQueue<Vertex2887> queue = new PriorityQueue<Vertex2887>();
		visited[startPoint] = true;
		
		// 처음 시작하는 edge의 모든 간선을 큐에 넣음
		for(Vertex2887 v : graph[startPoint]) {
			queue.offer(v);
		}
		
		int count = 0;
		int sum = 0;
		while(!queue.isEmpty()) {
			Vertex2887 vertex = queue.poll();
			int start = vertex.end;
			if(visited[start]) {	// 이전에 방문한 경우
				continue;
			}
			else {	// 새로 방문하는 것이면
				count++;
				sum += vertex.weight;
				visited[start] = true;
				if(count + 1 == n) {
					return sum;
				}
				for(Vertex2887 v : graph[start]) {
					queue.offer(v);
				}
			}
		}
		return sum;
	}
}

class Planet {
	int x;
	int y;
	int z;
	int num;
	Planet(int num, int x, int y, int z) {
		this.num = num;
		this.x = x;
		this.y = y;
		this.z = z;
	}
}

class Vertex2887 implements Comparable<Vertex2887> {
	int end;
	int weight;
	Vertex2887(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}
	@Override
	public int compareTo(Vertex2887 o) {
		if(this.weight < o.weight) {
			return -1;
			
		}
		else
			return 1;
	}
}
