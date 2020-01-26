package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// ���� ����
// ���� ���� ����
// �����۾� �ְ�, �� �ǹ��� �ϼ��Ǳ������ �ּ� �ð� -> ���� �۾� �ִ� ��� distance�� �����ϱ�.
public class p1516 {
	static List<Integer>[] graph;
	static int[] distance;
	static int[] indegree;
	static int[] weights;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		distance = new int[n + 1];
		weights = new int[n + 1];
		indegree = new int[n + 1];
		graph = new ArrayList[n + 1];
		for(int i = 0; i <= n; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.valueOf(st.nextToken());
			weights[i] = weight;
			int count = st.countTokens() - 1;
			indegree[i] = count;	// countTokens ���� Ȯ���غ���
			while(count --> 0) {
				int ele = Integer.valueOf(st.nextToken());
				graph[ele].add(i);
			}
		}
		
		topologicalSort();
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n; i++) {
			sb.append(distance[i]).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void topologicalSort() {
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i = 1; i < graph.length; i++) {	// �����۾��� ���� �۾��� ť�� �ֱ�
			if(indegree[i] == 0) {
				queue.add(i);
				distance[i] = weights[i];
			}
		}
		if(queue.isEmpty()) {	// ���� ������ �Ұ������� ������ ����
			
		}
		while(!queue.isEmpty()) {
			int point = queue.poll();
			for(int nextPoint : graph[point]) {
				indegree[nextPoint]--;
				// ù ���ۺ��� distance�� ���߱� ������ ���� ������ weight�� ���ϸ� �ȴ�.
				distance[nextPoint] = Math.max(distance[nextPoint], distance[point] + weights[nextPoint]);
				if(indegree[nextPoint] == 0) {
					queue.add(nextPoint);
				}
			}
		}
	}
}
