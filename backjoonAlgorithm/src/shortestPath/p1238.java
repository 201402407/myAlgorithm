package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// ��Ƽ ����
// ���ͽ�Ʈ�� �˰���
// i -> X, X -> i ���� �ִ� ��� ���ϱ�
public class p1238 {
	static List<Vertex1238>[] graph;
	static List<Vertex1238>[] reverseGraph;
	static boolean[] visited, reverseVisited;
	static int[] distance, reverseDistance;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		int x = Integer.valueOf(st.nextToken());
		
		visited = new boolean[n + 1];
		distance = new int[n + 1];
		reverseVisited = new boolean[n + 1];
		reverseDistance = new int[n + 1];
		graph = new ArrayList[n + 1];
		reverseGraph = new ArrayList[n + 1];
		for(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<Vertex1238>();
			reverseGraph[i] = new ArrayList<Vertex1238>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.valueOf(st.nextToken());
			int end = Integer.valueOf(st.nextToken());
			int weight = Integer.valueOf(st.nextToken());
			graph[start].add(new Vertex1238(end, weight));	// �ܹ��� ����
			reverseGraph[end].add(new Vertex1238(start, weight));	// ������ ���� 
		}
		
		// X���� �� �������� �ִܰŸ� ���
		dijkstra(n, x, distance, visited, graph);
		// �� ��������� X���� �ִܰ�θ� ����ϱ� ���ؼ� ������ ������ ���� �ٲ� �׷����� ���ͽ�Ʈ�� �����Ѵ�?!
		dijkstra(n, x, reverseDistance, reverseVisited, reverseGraph);
		
		int result = 0;
		for(int i = 1; i <= n; i++) {
			if(distance[i] == Integer.MAX_VALUE || reverseDistance[i] == Integer.MAX_VALUE) {
				System.out.println("���Ѵ밡�ִ�");
			}
			result = Math.max(result, distance[i] + reverseDistance[i]);
		}
		System.out.println(result);
	}
	
	public static void dijkstra(int n, int startPoint, int[] realDistance, boolean[] realVisited, List<Vertex1238>[] list) {
		PriorityQueue<Vertex1238> queue = new PriorityQueue<Vertex1238>();
		realVisited[startPoint] = true;
		Arrays.fill(realDistance, Integer.MAX_VALUE);
		realDistance[startPoint] = 0;
		queue.offer(new Vertex1238(startPoint, realDistance[startPoint]));
		
		while(!queue.isEmpty()) {
			Vertex1238 v = queue.poll();
			int start = v.end;
			realVisited[start] = true;
			for(Vertex1238 vertex : list[start]) {
				int end = vertex.end;
				if(!realVisited[end] && realDistance[end] > realDistance[start] + vertex.weight) {
					realDistance[end] = realDistance[start] + vertex.weight;
					queue.offer(new Vertex1238(end, realDistance[end]));
				}
			}
		}
	}
}

class Vertex1238 implements Comparable<Vertex1238> {
	int end;
	int weight;
	
	Vertex1238(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Vertex1238 o) {
		if(this.weight < o.weight) {
			return -1;
		}
		else
			return 1;
	}
}