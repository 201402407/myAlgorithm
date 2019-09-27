package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 음악프로그램
// 위상정렬  사용
public class p2623 {
	static List<Integer>[] graph;
	static int[] indegree;
	static List<Integer> result = new ArrayList<Integer>();
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			if(n < 1 || n > 1000) {
				System.exit(0);
			}
			graph = new LinkedList[n + 1];
			indegree = new int[n + 1];
			
			for(int x = 1; x <= n; x++) {
				graph[x] = new ArrayList<Integer>();
			}
			
			int m = Integer.valueOf(st.nextToken());
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int tokenSize = Integer.valueOf(st.nextToken());
				int startNum = Integer.valueOf(st.nextToken());
				for(int j = 1; j < tokenSize; j++) {
					int endNum = Integer.valueOf(st.nextToken());
					graph[startNum].add(endNum);
					indegree[endNum]++;
					startNum = endNum;
				}
			}
			
			setSingerOrder();
			StringBuilder sb = new StringBuilder();
			
			if(result.size() != n) {
				sb.append(0);		
			}
			else {
				for(int element : result) {	// 이 for문이 stream보다 훨씬 빠름
					sb.append(element).append("\n");
				}
//				result.stream().forEach(element -> sb.append(element).append("\n"));
			}
			System.out.print(sb.toString());
		}
		catch(NumberFormatException e) {
			System.exit(0);
		}
		catch(IOException e) {
			System.exit(0);
		}
	}
	
	private static void setSingerOrder() {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		for(int i = 1; i < graph.length; i++) {
			if(indegree[i] == 0) {
				queue.add(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int singer = queue.poll();
			result.add(singer);
			
			for(int element : graph[singer]) {
				indegree[element]--;
				if(indegree[element] == 0) {
					queue.add(element);
				}
			}
		}
	}
}
