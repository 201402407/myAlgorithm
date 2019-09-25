package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

// ������
// �������� -> DAG �׷��� Ư���̾�� ��. DAG : ������ �ְ� ����Ŭ(��ȯ)�� �������� ����. ��, ���۰� ���� ��Ȯ�� �����ؾ� ��
public class p1766 {
	static LinkedList<Integer> graph[];
	static int indegree[]; // �������� �迭
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
			// �� �������� �ֱ� ����
			for(int i = 1; i <= n; i++) {
				graph[i] = new LinkedList<Integer>();
			}
			
			// �Է¹��� ������ ����ֱ�
			for(int j = 0; j < m; j++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.valueOf(st.nextToken());
				int to = Integer.valueOf(st.nextToken());
				graph[from].add(to);
				indegree[to]++;	// �������� 1 �߰�
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
		// ���������� 0�� ��带 ������� ť�� �ֱ�.
		// ���������� 0�̶�� ���� ���۳���� ��
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
				indegree[neighborNode]--;	// ���������Ƿ� �������� �ϳ� ����
				if(indegree[neighborNode] == 0) {
					addAndSortArrayList(neighborNode, n, ready);
//					addAndSortQueue(neighborNode, readyQ);
				}
			}
		}
	
//		while(!readyQ.isEmpty()) {
//			int nowNode = readyQ.poll(); // ���Գ�� 0�� ���� ť���� �ϳ� ������
////			result.add(nowNode);	// ��� ť�� �ִ´�. ���������� 0�̱� ������
//			System.out.print(nowNode + " ");
//			
//			for(int neighborNode : graph[nowNode]) {
//				indegree[neighborNode]--;	// ���������Ƿ� �������� �ϳ� ����
//				if(indegree[neighborNode] == 0) {
//					addAndSortQueue(neighborNode, readyQ);
//				}
//			}
//		}
	}
	
	private static ArrayList<Integer> addAndSortArrayList(int element, int n, ArrayList<Integer> ready) {
		boolean state = false; // true : �߰��� ���� �߰�	  false : �ش� ���Ұ� ���� ū ���
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
//		boolean state = false; // true : �߰��� ���� �߰�	  false : �ش� ���Ұ� ���� ū ���
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