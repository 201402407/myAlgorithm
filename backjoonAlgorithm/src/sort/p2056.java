package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 위상 정렬 문제
// 작업.
// 위상 정렬 사용 -> 가중치가 있어서 최소 시간을 구하기 위해 우선순위 큐 사용해보기.
public class p2056 {
	static List<Integer>[] graph;	// 해당 인덱스에서 출발하는 간선이 담긴 그래프
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
		// 그래프 초기화
		for(int i = 0; i <= n; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.valueOf(st.nextToken());
			weights[i] = weight;
			int count = Integer.valueOf(st.nextToken());	// 연결된 간선의 개수
			indegree[i] = count;
			for(int j = 0; j < count; j++) {
				int from = Integer.valueOf(st.nextToken());
				graph[from].add(i);	// 선행 관계(from)에서 현재 지점(i)으로 오는 경우
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
				distance[i] = Math.max(distance[i], weights[i]);	// 시작 점이므로 0 + weight[i]에 해당한다.
			}
		}
		
		while(!queue.isEmpty()) {
			int point = queue.poll();
			for(int neighborElement : graph[point]) {
				indegree[neighborElement]--;
				// 더 오래 걸리는 시간을 넣기 = 모든 작업을 최소로 완료하기 위해서는 가장 오래 걸리는 시간으로 계산해야 한다.
				// 즉, 다음 작업을 하기 위한 선행 작업들 중 가장 최대 시간을 더해야 나머지 모든 선행 작업을 끝냈다는 계산이 되므로 최대 시간을 distance에 더한다.
				distance[neighborElement] = Math.max(distance[neighborElement], distance[point] + weights[neighborElement]);
				if(indegree[neighborElement] == 0) {
					queue.add(neighborElement);
				}
			}
		}
		// 모든 작업을 수행하는데 걸리는 최소 시간이므로 distance의 원소 중에서 가장 큰 값을 선택해야 모든 작업을 수행하는 데 걸리는 최대 시간이 된다.
		int result = 0;
		for(int i = 1; i < distance.length; i++) {
			result = Math.max(result, distance[i]);
		}
		return result;
	}
}