package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// ������ ����
// ��������
// �켱���� ť ��� -> �ѹ��� �ذ�..
// indegree ���
public class p1766_bestAnswer {
	static ArrayList<Integer>[] graph;
	static int[] indegree;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		if(n < 1 || n > 32000) {
			System.exit(0);
		}
		int m = Integer.valueOf(st.nextToken());
		if(m < 1 || m > 100000) {
			System.exit(0);
		}
		graph = new ArrayList[n + 1];
		indegree = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		// �Է¹��� ������ ����ֱ�
		for(int j = 0; j < m; j++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());
			graph[from].add(to);
			indegree[to]++;	// �������� 1 �߰�
		}
		
		topologicalSort(n);
	}
	
	private static void topologicalSort(int n) {
		PriorityQueue<Integer> readyQ = new PriorityQueue<Integer>();
		for(int i = 1; i <= n; i++) {
			if(indegree[i] == 0) {
				readyQ.add(i);
			}
		}
		
		while(!readyQ.isEmpty()) {
			int nowQuestion = readyQ.poll();
			System.out.print(nowQuestion + " ");
			
			for(int neighborQuestion : graph[nowQuestion]) {
				indegree[neighborQuestion]--;
				if(indegree[neighborQuestion] == 0) {
					readyQ.add(neighborQuestion);
				}
			}
		}
	}
}
