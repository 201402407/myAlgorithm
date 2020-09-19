package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// 바이러스 문제
// DFS
public class p2606 {
	static List<Integer>[] map;
	static boolean[] visited;
	static int count = -1;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		map = new ArrayList[n + 1];	// 인덱스 1부터 시작
		visited = new boolean[n + 1];
		for(int i = 1; i <= n; i++) {
			map[i] = new ArrayList<Integer>();
		}
		
		st = new StringTokenizer(br.readLine());
		int m = Integer.valueOf(st.nextToken());
		for(int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.valueOf(st.nextToken());
			int end = Integer.valueOf(st.nextToken());
			map[start].add(end);
			map[end].add(start);
		}
		dfs(1);
		System.out.println(count);
	}
	
	static void dfs(int index) {
		if(visited[index])
			return;
		else {
			visited[index] = true;
			count++;
			for(int next : map[index]) {
				dfs(next);
			}
		}
	}
}
