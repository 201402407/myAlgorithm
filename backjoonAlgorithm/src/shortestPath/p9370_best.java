package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 9370번 문제 
// 획기적인 솔루션으로 풀어보기
// g-h 도로는 -1을 해서 홀수로 만들고, 나머지 도로는 2를 곱해서 짝수로 만들어버리기
// 그래서 다익스트라를 실행했을 때, 목적지 후보들 중 홀수가 나오면 해당 g-h 경로를 지난 것이 되므로 목적지가 되고, 짝수면 아닌게 되는거다..!!
// 다익스트라 알고리즘
public class p9370_best {
	static int[] realDistance;
	static boolean[] realVisited;
	static ArrayList<Vertex9370>[] graph;
	static ArrayList<Integer> dests;
	static int betweenDistance;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.valueOf(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int testCase = 0; testCase < tc; testCase++) {
			// 첫 째 줄. 교차로, 도로, 목적지 후보 개수
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			int m = Integer.valueOf(st.nextToken());
			int t = Integer.valueOf(st.nextToken());
			graph = new ArrayList[n + 1];
			realDistance = new int[n + 1];
			realVisited = new boolean[n + 1];
			dests = new ArrayList<Integer>();
			
			for(int i = 1; i <= n; i++) {
				graph[i] = new ArrayList<Vertex9370>();
			}
			
			// 두번 째 줄. 출발지, g와 h도로
			st = new StringTokenizer(br.readLine());
			int startPoint = Integer.valueOf(st.nextToken());
			int g = Integer.valueOf(st.nextToken());
			int h = Integer.valueOf(st.nextToken());
			
			// 도로
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.valueOf(st.nextToken());
				int end = Integer.valueOf(st.nextToken());
				int weight = Integer.valueOf(st.nextToken());
				weight *= 2;
				if((start == g && end == h) || (start == h && end == g)) {	// g-h도로만 -1을 해서 홀수를 만들기
					weight--;
				}
				graph[start].add(new Vertex9370(end, weight));
				graph[end].add(new Vertex9370(start, weight));
			}
			
			// 목적지 후보
			for(int i = 0; i < t; i++) {
				st = new StringTokenizer(br.readLine());
				int dest = Integer.valueOf(st.nextToken());
				dests.add(dest);
			}
			
			Arrays.fill(realDistance, Integer.MAX_VALUE);
			realDistance[startPoint] = 0;
			dijkstra(startPoint, n);
			
			// 특정 g-h 도로를 지나지 않은 최단 경로와 지난 최단 경로가 같아야 목적지에 적합하다.
			for(int i = 0; i < dests.size(); i++) {
				int index = dests.get(i);
				if(realDistance[index] == Integer.MAX_VALUE || realDistance[index] % 2 == 0) {	// 짝수면 목적지가 아니므로 제거
					dests.remove(i);
					i--;
				}
			}
			Collections.sort(dests);
			
			for(int ele : dests) {
				sb.append(ele).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void dijkstra(int startPoint, int n) {
		PriorityQueue<Vertex9370> queue = new PriorityQueue<Vertex9370>();
		
		queue.offer(new Vertex9370(startPoint, realDistance[startPoint]));
		while(!queue.isEmpty()) {
			Vertex9370 vertex = queue.poll();
			int start = vertex.getEnd();
			realVisited[start] = true;
			
 			for(Vertex9370 nextVertex : graph[start]) {
				int end = nextVertex.getEnd();
				int dist = nextVertex.getDistance();
				if(!realVisited[end] && realDistance[end] > realDistance[start] + dist) {
					realDistance[end] = realDistance[start] + dist;
					queue.offer(new Vertex9370(end, realDistance[end]));
				}
			}
		}
	}
}

class Vertex9370 implements Comparable<Vertex9370> {
	private int end;
	private int distance;
	
	Vertex9370(int end, int distance) {
		this.end = end;
		this.distance = distance;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public int compareTo(Vertex9370 o) {
		if(this.distance < o.getDistance()) {
			return -1;
		}
		else
			return 1;
	}
}
