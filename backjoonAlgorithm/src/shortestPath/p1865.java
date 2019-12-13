package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// ��Ȧ ����
// ���� ����ġ�� �����Ƿ� ���� ���� �˰��� ���
public class p1865 {
	static int[] distance;
	static ArrayList<Vertex1865> graph;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int tc = Integer.valueOf(st.nextToken());	// �׽�Ʈ���̽�
		StringBuilder sb = new StringBuilder();
		for(int t = 0; t < tc; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			int m = Integer.valueOf(st.nextToken());
			int w = Integer.valueOf(st.nextToken());
			
			distance = new int[n + 1];
			graph = new ArrayList<Vertex1865>();
			
			// ����
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.valueOf(st.nextToken());
				int end = Integer.valueOf(st.nextToken());
				int weight = Integer.valueOf(st.nextToken());
				graph.add(new Vertex1865(start, end, weight));
				graph.add(new Vertex1865(end, start, weight));
			}
			
			// ��Ȧ
			for(int i = 0; i < w; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.valueOf(st.nextToken());
				int end = Integer.valueOf(st.nextToken());
				int weight = Integer.valueOf(st.nextToken());
				graph.add(new Vertex1865(start, end, weight * -1));
			}
			
			// ��� �������� �ٽ� ���ƿ��µ� -�� ��찡 �ִ��� ã���Ƿ� ��� ������ bellman �غ��� �Ѵ�.
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
		// 1�ܰ� : ���� ���� 0, �������� ���Ѵ�
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[startPoint] = 0;
		
		// 2�ܰ� : n - 1���� v ���� Ž��.
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
