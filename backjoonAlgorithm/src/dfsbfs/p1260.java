	package dfsbfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class p1260 {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		int v = scan.nextInt();
		
		ArrayList<Integer>[] arrayList = (ArrayList<Integer>[]) new ArrayList[n + 1]; // 1부터 그래프가 시작하므로 1 추가
		for(int i = 0; i <= n; i++) {
			arrayList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			int start = scan.nextInt();
			int end = scan.nextInt();
			arrayList[start].add(end);
			arrayList[end].add(start);
		}

		for(int i = 1; i <= n; i++) {
			Collections.sort(arrayList[i]);
		}
		
		boolean[] boolArray = new boolean[n + 1]; // 이미 false로 초기화
		DFS(arrayList, boolArray, v);
		System.out.println();
		Arrays.fill(boolArray, false);
		BFS(arrayList, boolArray, v);
		
	}
	
	// Depth First Search(깊이 우선 탐색)
	public static void DFS(ArrayList<Integer>[] temp, boolean[] boolTemp, int vertex) {
		if(boolTemp[vertex]) { // 이미 탐색 완료된 노드면 종료
			return;
		}
		
		boolTemp[vertex] = true;
		System.out.print(vertex + " ");
		
		ArrayList<Integer> tempList = temp[vertex];
		for(int nextCnt = 0; nextCnt < tempList.size(); nextCnt++) {
			if(!boolTemp[tempList.get(nextCnt)]) {	// 하나부터 깊게 파고들기 위해 재귀로 더 깊게 들어간다.
				DFS(temp, boolTemp, tempList.get(nextCnt));
			}
		}
	}
	
	// Breadth First Search(너비 우선 탐색)
	public static void BFS(ArrayList<Integer>[] temp, boolean[] boolTemp, int vertex) {
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(vertex);
		boolTemp[vertex] = true;
		
		while(!queue.isEmpty()) {
			vertex = queue.poll();
			System.out.print(vertex + " ");
			
			ArrayList<Integer> tempList = temp[vertex];
			for(int nextCnt = 0; nextCnt < tempList.size(); nextCnt++) {
				int element = tempList.get(nextCnt);
				if(!boolTemp[element]) { // 같은 레벨의 노드들을 탐색하기 위함.
					queue.add(element);
					boolTemp[element] = true;
				}
			}
		}
	}
}
