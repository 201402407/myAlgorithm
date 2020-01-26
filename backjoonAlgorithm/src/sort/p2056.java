package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// ���� ���� ����
// �۾�.
// ���� ���� ��� -> ����ġ�� �־ �ּ� �ð��� ���ϱ� ���� �켱���� ť ����غ���.
public class p2056 {
	static List<Integer>[] graph;	// �ش� �ε������� ����ϴ� ������ ��� �׷���
	static int[] indegree;
	static int[] weights;
	static int[] distance;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		graph = new ArrayList[n + 1];
		indegree = new int[n + 1];
		weights = new int[n + 1];
		distance = new int[n + 1];
		// �׷��� �ʱ�ȭ
		for(int i = 0; i <= n; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.valueOf(st.nextToken());
			weights[i] = weight;
			int count = Integer.valueOf(st.nextToken());	// ����� ������ ����
			indegree[i] = count;
			for(int j = 0; j < count; j++) {
				int from = Integer.valueOf(st.nextToken());
				graph[from].add(i);	// ���� ����(from)���� ���� ����(i)���� ���� ���
//				indegree[i]++;
			}
		}
		
		int result = topologicalSort();
		System.out.println(result);
	}
	
	static int topologicalSort() {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		
		for(int i = 1; i < graph.length; i++) {
			if(indegree[i] == 0) {
				queue.add(i);
				distance[i] = Math.max(distance[i], weights[i]);	// ���� ���̹Ƿ� 0 + weight[i]�� �ش��Ѵ�.
			}
		}
		
		while(!queue.isEmpty()) {
			int point = queue.poll();
			for(int neighborElement : graph[point]) {
				indegree[neighborElement]--;
				// �� ���� �ɸ��� �ð��� �ֱ� = ��� �۾��� �ּҷ� �Ϸ��ϱ� ���ؼ��� ���� ���� �ɸ��� �ð����� ����ؾ� �Ѵ�.
				// ��, ���� �۾��� �ϱ� ���� ���� �۾��� �� ���� �ִ� �ð��� ���ؾ� ������ ��� ���� �۾��� ���´ٴ� ����� �ǹǷ� �ִ� �ð��� distance�� ���Ѵ�.
				distance[neighborElement] = Math.max(distance[neighborElement], distance[point] + weights[neighborElement]);
				if(indegree[neighborElement] == 0) {
					queue.add(neighborElement);
				}
			}
		}
		// ��� �۾��� �����ϴµ� �ɸ��� �ּ� �ð��̹Ƿ� distance�� ���� �߿��� ���� ū ���� �����ؾ� ��� �۾��� �����ϴ� �� �ɸ��� �ִ� �ð��� �ȴ�.
		int result = 0;
		for(int i = 1; i < distance.length; i++) {
			result = Math.max(result, distance[i]);
		}
		return result;
	}
}