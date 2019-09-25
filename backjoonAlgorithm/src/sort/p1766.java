package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 문제집
// 위상정렬 -> DAG 그래프 특성이어야 함. DAG : 순서가 있고 사이클(순환)이 존재하지 않음. 즉, 시작과 끝이 명확히 존재해야 함
public class p1766 {
	static LinkedList<Integer> graph[];
	static int indegree[]; // 진입차수 배열
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		try {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			if(n < 1 || n > 32000) {
				System.exit(0);
			}
			int m = Integer.valueOf(st.nextToken());
			if(m < 1 || m > 100000) {
				System.exit(0);
			}
			graph = new LinkedList[n + 1];
			indegree = new int[n + 1];
			// 각 순서쌍을 넣기 위함
			for(int i = 1; i <= n; i++) {
				graph[i] = new LinkedList<Integer>();
			}
			
			// 입력받은 순서쌍 집어넣기
			for(int j = 0; j < m; j++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.valueOf(st.nextToken());
				int to = Integer.valueOf(st.nextToken());
				graph[from].add(to);
				indegree[to]++;	// 진입차수 1 추가
			}
			
			topologicalSort();
//			int resultSize = result.size();
//			for(int i = 0; i < resultSize; i++) {
//				System.out.print(result.poll() + " ");
//			}
		}
		catch (NumberFormatException e) {
			System.exit(0);
		}
		catch (IOException e) {
			System.exit(0);
		}
	}
	
	private static void topologicalSort() {
//		LinkedList<Integer> readyQ = new LinkedList<Integer>();
		ArrayList<Integer> ready = new ArrayList<Integer>();
		int graphSize = graph.length - 1;
		// 진입차수가 0인 노드를 순서대로 큐에 넣기.
		// 진입차수가 0이라는 뜻은 시작노드라는 뜻
		for(int i = 1; i <= graphSize; i++) {
			if(indegree[i] == 0) {
//				readyQ.add(i);
				ready.add(i);
			}
		}
		
		int n = 0;
		while(n < ready.size()) {
			int nowNode = ready.get(n);
			System.out.print(nowNode + " ");
			n++;
			for(int neighborNode : graph[nowNode]) {
				indegree[neighborNode]--;	// 진입했으므로 진입차수 하나 빼기
				if(indegree[neighborNode] == 0) {
					addAndSortArrayList(neighborNode, n, ready);
//					addAndSortQueue(neighborNode, readyQ);
				}
			}
		}
	
//		while(!readyQ.isEmpty()) {
//			int nowNode = readyQ.poll(); // 진입노드 0이 모인 큐에서 하나 꺼내고
////			result.add(nowNode);	// 결과 큐에 넣는다. 진입차수가 0이기 때문에
//			System.out.print(nowNode + " ");
//			
//			for(int neighborNode : graph[nowNode]) {
//				indegree[neighborNode]--;	// 진입했으므로 진입차수 하나 빼기
//				if(indegree[neighborNode] == 0) {
//					addAndSortQueue(neighborNode, readyQ);
//				}
//			}
//		}
	}
	
	private static ArrayList<Integer> addAndSortArrayList(int element, int n, ArrayList<Integer> ready) {
		boolean state = false; // true : 중간에 원소 추가	  false : 해당 원소가 가장 큰 경우
		int size = ready.size();
		for(int i = n; i < size; i++) {
			if(element < ready.get(i)) {
				ready.add(i, element);
				state = true;
				break;
			}
		}
		if(!state) {
			ready.add(element);
		}
		return ready;
		
	}
//	private static LinkedList<Integer> addAndSortQueue(int element, int n, LinkedList<Integer> ready) {
//		ListIterator<Integer> iterator = ready.listIterator();
//		boolean state = false; // true : 중간에 원소 추가	  false : 해당 원소가 가장 큰 경우
//		while(iterator.hasNext()) {
//			int temp = iterator.next();
//			if(element < temp) {
//				ready.add(iterator.nextIndex() - 1, element);
//				state = true;	
//				break;
//			}
//			else {
//				state = false;
//			}
//		}
//		if(!state) {
//			ready.addLast(element);
//		}
//		return ready;
//	}
}