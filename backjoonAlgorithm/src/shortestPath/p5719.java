package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// ���� �ִ� ���
public class p5719 {
	static StringBuilder sb = new StringBuilder();
	static List<Vertex>[] graph;
	static int[] distance;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		while((str = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(str);
			int n = Integer.valueOf(st.nextToken());
			int m = Integer.valueOf(st.nextToken());
			if(n == 0 && m == 0)	// ����
				break;
			// �ʱ� ����
			graph = new ArrayList[n];
			distance = new int[n];
			boolean[] visited = new boolean[n];	// ���� �ִ� ����̱� ������ �����Ҽ����ִ�.
			for(int i = 0; i < n; i++) {
				graph[i] = new ArrayList<Vertex>();
			}
			Arrays.fill(distance, Integer.MAX_VALUE);
			
			st = new StringTokenizer(br.readLine());
			int start = Integer.valueOf(st.nextToken());
			int end = Integer.valueOf(st.nextToken());
			distance[start] = 0;
			// �׷��� ����
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.valueOf(st.nextToken());
				int to = Integer.valueOf(st.nextToken());
				int weight = Integer.valueOf(st.nextToken());
				graph[from].add(new Vertex(to, weight));
			}
			find(start, end, n);
		}
		System.out.println(sb.toString());
	}
	
	// �ִ� ��� �� ���־� �ϳ�
	private static void find(int startPoint, int endPoint, int n) {
		int[] result = dijkstra(startPoint, endPoint, n);	// endPoint������ �ִ� ��� ����
		if(!deletePath(startPoint, endPoint, result)) {
			sb.append("-1").append("\n");
			return;
		}
		int minDistance = distance[endPoint];
		do {
			result = dijkstra(startPoint, endPoint, n);	// �� Ž��
			if(!deletePath(startPoint, endPoint, result)) {
				sb.append("-1").append("\n");
				return;
			}
		}
		while(minDistance == distance[endPoint]);
		sb.append(distance[endPoint]).append("\n");
	}
	
	// ��� ���� -> ������ : true. ���� ���� : false
	private static boolean deletePath(int startPoint, int endPoint, int[] result) {
		// ��� ����
		int endVertex = endPoint;
		while(endVertex != startPoint) {
			int startVertex = result[endVertex];

			if(startVertex == -1) { // ������������ ���� ��ΰ� ���� ���
				return false;
			}
			boolean isValid = false; // �׷��� ���� �̾����� Vertex�� �����ϴ� �� üũ
			for(Vertex v : graph[startVertex]) {
				if(v.getVertex() == endVertex) {
					graph[startVertex].remove(v);
					endVertex = startVertex;
					isValid = true;
					break;
				}
			}
			if(!isValid)
				return false;
		}
		return true;
	}
	
	// ó���� �� ������ ���� �ִ� ��θ� 
	private static int[] dijkstra(int startPoint, int endPoint, int n) {
		PriorityQueue<Vertex> queue = new PriorityQueue<>();	// comparable�� ��ü �켱���� �ٲ�
		int answer = Integer.MAX_VALUE;
		boolean[] visited = new boolean[n];
//		List<Integer> result = new ArrayList<Integer>();
		int[] result = new int[n];	// �ִ� ���
		Arrays.fill(distance, Integer.MAX_VALUE);
		Arrays.fill(result, -1);
		distance[startPoint] = 0;
		queue.offer(new Vertex(startPoint, distance[startPoint]));	// ����� �ֱ�
		
		while(!queue.isEmpty()) { 
			Vertex startVertex = queue.poll();
			startPoint = startVertex.getVertex();
			if(visited[startPoint])
				continue;
			visited[startPoint] = true;
			
			for(Vertex vertex : graph[startPoint]) {
				int endVertex = vertex.getVertex();
				if(distance[endVertex] > distance[startPoint] + vertex.getWeight()) {
					distance[endVertex] = distance[startPoint] + vertex.getWeight();
					queue.offer(new Vertex(endVertex, distance[endVertex]));
					result[endVertex] = startPoint;	// �ش� ����� ������ ���
				}
			}
		}
		return result;
	}
}

class Vertex implements Comparable<Vertex> {
	private int startVertex;
	private int vertex;
	private int weight;
	
	Vertex(int vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}
	
	Vertex(int vertex, int weight, int startVertex) {
		this.vertex = vertex;
		this.weight = weight;
		this.startVertex = startVertex;
	}
	
	public void setStartVertex(int startVertex) {
		this.startVertex = startVertex;
	}
	
	public int getStartVertex() {
		return this.startVertex;
	}
	
	public int getVertex() {
		return vertex;
	}
	public void setVertex(int vertex) {
		this.vertex = vertex;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	// ��������
	@Override
	public int compareTo(Vertex v) {
		if(this.vertex < v.getVertex()) {
			return -1;
		}
		else
			return 1;
	}
}
