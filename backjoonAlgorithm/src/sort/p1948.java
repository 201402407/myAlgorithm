package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// �Ӱ��� ����
// ���� ���� ����
// ���� ������ �� ������ �ְ�, ��� ���� ��� �� ���� ���� �ɸ� �ð��� ��� ������ ����ϴ� ����
// ��� ������ ���ϱ� ���ؼ��� (�Ӱ� ��θ� ���ϱ� ���ؼ���) reverse�� �ؼ� cost�� ������ �Ѵ�.
// distance[prev] == distance[next] - weight[next]
// visited 2���� �迭�� �����ؼ� ���� �ϳ��ϳ� �湮 ���θ� üũ�ϰ�, �� ������ ������ ���� �� �湮 �ȵ� ������ ������ ���Ѵ�.
public class p1948 {
	static List<Road>[] graph, reverseGraph;
	static int[] distance, indegree, prevPoints;
	static boolean[][] visited;	// reverse�� from , to
	static int count = 0;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		graph = new ArrayList[n + 1];
		reverseGraph = new ArrayList[n + 1];
		distance = new int[n + 1];
		indegree = new int[n + 1];
		prevPoints = new int[n + 1];
		visited = new boolean[n + 1][n + 1];
		for(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<Road>();
			reverseGraph[i] = new ArrayList<Road>();
		}
	
		st = new StringTokenizer(br.readLine());
		int m = Integer.valueOf(st.nextToken());
		while(m --> 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());
			int weight = Integer.valueOf(st.nextToken());
			graph[from].add(new Road(to, weight));
			reverseGraph[to].add(new Road(from, weight));
			indegree[to]++;
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.valueOf(st.nextToken());
		int end = Integer.valueOf(st.nextToken());

		topologicalSort(start, end);
		
		// ��� �������� ���� �������� �ɸ��� �ð� �� ���� ���� �ɸ��� �ð�
		System.out.println(distance[end]);
		// ���� �ɸ��� �ð��� ��� ����
		System.out.println(count);
	}
	
	static void topologicalSort(int start, int end) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);	// ���� ���� �ֱ�
		
		// ���� ������ ���� ���� �� �ð� ã�Ƴ���
		while(!queue.isEmpty()) {
			int point = queue.poll();
			for(Road road : graph[point]) {
				indegree[road.end]--;
				if(distance[road.end] < distance[point] + road.weight) {
					distance[road.end] = distance[point] + road.weight;
					prevPoints[road.end] = point;
				}
				if(indegree[road.end] == 0)
					queue.add(road.end);
			}
		}
		
		// ������ �׷����� ���� ��� ��� ã��
		queue.add(end);
		while(!queue.isEmpty()) {
			int point = queue.poll();
			for(Road road : reverseGraph[point]) {
				if((distance[point] == distance[road.end] + road.weight) && !visited[point][road.end]) {
					visited[point][road.end] = true;
					count++;
					queue.add(road.end);
				}
			}
		}
	}
}

class Road {
	int end;
	int weight;
	
	Road(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}
}
