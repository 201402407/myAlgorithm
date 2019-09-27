package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 줄 세우기
// 위상 정렬 + 우선순위 큐 사용
public class p2252 {
	static List<Integer>[] graph;
	static int[] indegree;
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			int m = Integer.valueOf(st.nextToken());
			graph = new ArrayList[n + 1];
			indegree = new int[n + 1];
			
			for(int i = 1; i <= n; i++) {
				graph[i] = new ArrayList<Integer>();
			}
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.valueOf(st.nextToken());
				int to = Integer.valueOf(st.nextToken());
				graph[from].add(to);
				indegree[to]++;
			}
			topologicalSort();
			
		} catch (IOException e) {
			System.exit(0);
		}
	}
	
	private static void topologicalSort() {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		
		for(int i = 1; i < graph.length; i++) {
			if(indegree[i] == 0) {
				queue.add(i);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!queue.isEmpty()) {
			int element = queue.poll();
			sb.append(element).append(" ");
			for(int neighborElement : graph[element]) {
				indegree[neighborElement]--;
				if(indegree[neighborElement] == 0) {
					queue.add(neighborElement);
				}
			}
		}
		System.out.print(sb.toString());
	}
}
