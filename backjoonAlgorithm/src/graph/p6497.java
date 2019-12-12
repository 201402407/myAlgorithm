package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 전력난 문제
// MST(최소 스패닝 트리)
// 크루스칼 알고리즘
public class p6497 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.valueOf(st.nextToken());	// Node의 수
			int n = Integer.valueOf(st.nextToken());	// 간선의 수
			if(m == 0 && n == 0) {
				System.out.println(sb.toString());
				break;
			}
			
			PriorityQueue<Vertex6497> queue = new PriorityQueue<Vertex6497>();
			int[] parent = new int[m];	// 부모 노드 인덱스 배열
			for(int i = 0; i < m; i++) {
				parent[i] = i;
			}
			
			int allPrice = 0;
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.valueOf(st.nextToken());
				int end = Integer.valueOf(st.nextToken());
				int weight = Integer.valueOf(st.nextToken());
				allPrice += weight;
				queue.offer(new Vertex6497(start, end, weight));
			}
			
			int count = 0;
			int sum = 0;
			while(!queue.isEmpty()) {
				Vertex6497 vertex = queue.poll();
				int start = vertex.getStart();
				int end = vertex.getEnd();
				int weight = vertex.getWeight();
				
				if(!isSameParent(parent, start, end)) {
					count++;
					sum += weight;
					union(parent, start, end);
					if(count + 1 == m) {
						sb.append(allPrice - sum).append("\n");
						break;
					}
				}
				
			}
		}
	}
	
	// 두 부모 노드 같은 부모인지 탐색하는 함수
	public static boolean isSameParent(int[] parent, int x, int y) {
		x = find(parent, x);
		y = find(parent, y);
		return x == y ? true : false;
	}
	
	// 해당 노드들의 부모 노드를 같게 하는 함수
	public static void union(int[] parent, int x, int y) {
		x = find(parent, x);
		y = find(parent, y);
		if(x != y)
			parent[y] = x;
	}
	
	// 해당 노드의 부모 노드를 찾는 함수
	public static int find(int[] parent, int x) {
		if(parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent, parent[x]);
	}
}

class Vertex6497 implements Comparable<Vertex6497> {
	private int start;
	private int end;
	private int weight;
	
	Vertex6497(int start, int end, int weight) {
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

	@Override
	public int compareTo(Vertex6497 o) {
		if(this.weight < o.getWeight()) {
			return -1;
		}
		else
			return 1;
	}
	
	
}