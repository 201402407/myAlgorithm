package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 서강그라운드
// 다익스트라 알고리즘
public class p14938 {
	static int n, m, r;
	static int[] items, results;
	static List<Vertex14938>[] graph;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());	// 수색 범위
		r = Integer.valueOf(st.nextToken());
	
		graph = new ArrayList[n + 1];
		items = new int[n + 1];	// index : 1 ~ n
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<Vertex14938>();
			items[i] = Integer.valueOf(st.nextToken());
		}
		
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());
			int weight = Integer.valueOf(st.nextToken());
			// 양방향 그래프
			graph[from].add(new Vertex14938(to, weight));
			graph[to].add(new Vertex14938(from, weight));
		}
		
		results = new int[n + 1];
		int result = 0;
		for(int i = 1; i <= n; i++) {
			Arrays.fill(results, Integer.MAX_VALUE);
			result = Math.max(result, dijkstra(i));
		}
		
		System.out.println(result);
	}
	
	static int dijkstra(int start) {
		boolean[] visited = new boolean[n + 1];
		PriorityQueue<Vertex14938> pq = new PriorityQueue<>();
		int result = 0;
		pq.offer(new Vertex14938(start, 0));
		results[start] = 0;
		
		while(!pq.isEmpty()) {
			Vertex14938 v = pq.poll();
			if(visited[v.end]) {
				continue;
			}
			
			if(v.weight > results[v.end]) {
				continue;
			}
			
			visited[v.end] = true;
			
			for(Vertex14938 nextV : graph[v.end]) {

				if(!visited[nextV.end] && nextV.weight + results[v.end] < results[nextV.end]) {
					results[nextV.end] = nextV.weight + results[v.end];
					pq.offer(new Vertex14938(nextV.end, results[nextV.end]));
				}
			}
		}
		
		for(int i = 1; i <= n; i++) {
			if(results[i] <= m) {
				result += items[i];
			}
		}
		
		return result;
	}
}

class Vertex14938 implements Comparable<Vertex14938> {
	int end;
	int weight;

	Vertex14938(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}
	
	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	// 오름차순
	@Override
	public int compareTo(Vertex14938 o) {
		if(this.weight < o.weight) {
			return -1;	
		}
		
		return 1;
	}
	
}