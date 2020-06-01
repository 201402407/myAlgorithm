package temp;

import java.io.*;
import java.util.*;

// 여행 가자
// Disjoint - Set ?
public class p1976 {
	static int[][] map;
	static ArrayList<Integer>[] graph;
	static ArrayList<Integer> route;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(br.readLine());
		
		route = new ArrayList<Integer>();
		graph = new ArrayList[n + 1];
		for(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		// Input 넣기
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 1) {
					graph[i].add(j);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			int dest = Integer.parseInt(st.nextToken());
			route.add(dest);
		}
		
//		for(int i = 1; i <= n; i++) {
//			for(int j = 0; j < graph[i].size(); j++) {
//				System.out.print(graph[i].get(j) + " ");
//			}
//			System.out.println();
//		}
		
		boolean check = true;
		
//		for(int i = 0; i < route.size() - 1; i++) {
//			if(!possibleRoute(n, route.get(i), route.get(i + 1))) {
//				check = false;
//				break;
//			}
//		}
		
		String print = possibleRoute(n, route.get(0)) ? "YES" : "NO";
		System.out.println(print);
	}
	
	static boolean possibleRoute(int n, int start) {
		boolean[] visited = new boolean[n + 1];
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start);
		
		while(!q.isEmpty()) {
			int ele = q.poll();
			
			if(visited[ele]) {
				continue;
			}
			
			visited[ele] = true;
			
			for(int i = 0; i < graph[ele].size(); i++) {
				int next = graph[ele].get(i);
				q.offer(next);
			}
		}
		
		// 경로에 한 번이라도 방문한 적이 있는지 파악하기
		// 중복 경로가 가능하고, 이전 길로도 되돌아갈 수 있어서 한 번이라도 방문했으면 어떤 순서건 간에 방문할 수 있다.
		for(int dest : route) {
			if(visited[dest]) {
				continue;
			}
			return false;
		}
		
		return true;
	}
}
