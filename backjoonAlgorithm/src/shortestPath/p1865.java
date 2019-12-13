package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 웜홀 문제
// 음의 가중치가 있으므로 벨만 포드 알고리즘 사용
public class p1865 {
	static int[] distance;
	static ArrayList<Vertex1865> graph;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int tc = Integer.valueOf(st.nextToken());	// 테스트케이스
		StringBuilder sb = new StringBuilder();
		for(int t = 0; t < tc; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			int m = Integer.valueOf(st.nextToken());
			int w = Integer.valueOf(st.nextToken());
			
			distance = new int[n + 1];
			graph = new ArrayList<Vertex1865>();
			
			// 도로
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.valueOf(st.nextToken());
				int end = Integer.valueOf(st.nextToken());
				int weight = Integer.valueOf(st.nextToken());
				graph.add(new Vertex1865(start, end, weight));
				graph.add(new Vertex1865(end, start, weight));
			}
			
			// 웜홀
			for(int i = 0; i < w; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.valueOf(st.nextToken());
				int end = Integer.valueOf(st.nextToken());
				int weight = Integer.valueOf(st.nextToken());
				graph.add(new Vertex1865(start, end, weight * -1));
			}
			
			// 출발 지점에서 다시 돌아오는데 -인 경우가 있는지 찾으므로 모든 지점을 bellman 해봐야 한다.
			boolean check = true;
			int size = graph.size();
			for(int i = 1; i <= n; i++) {
				if(!bellman(n, size, i)) {
					check = false;
					break;
				}
			}
				
			if(check) {
				sb.append("NO");
			}
			else {
				sb.append("YES");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static boolean bellman(int n, int v, int startPoint) {
		// 1단계 : 시작 점만 0, 나머지는 무한대
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[startPoint] = 0;
		
		// 2단계 : n - 1까지 v 전부 탐색.
		for(int i = 1; i < n; i++) {
			boolean check = false;
			for(int j = 0; j < v; j++) {
				Vertex1865 vertex = graph.get(j);
				int start = vertex.getStart();
				int end = vertex.getEnd();
				int weight = vertex.getWeight();
				if(distance[start] != Integer.MAX_VALUE && distance[start] + weight < distance[end]) {
					distance[end] = distance[start] + weight;
					check = true;
				}
			}
			if(!check) {
				break;
			}
		}
		if(distance[startPoint] < 0) {
			return false;
		}
		return true;
	}
}

class Vertex1865 {
	private int start;
	private int end;
	private int weight;
	
	Vertex1865(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
}
