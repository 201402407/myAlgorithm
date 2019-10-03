package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// ���� �ִ� ���
public class p5719 {
	static StringBuilder sb = new StringBuilder();
	static List<Vertex>[] graph;
	static int[] distance;
	static int resultDistance;
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
			
			for(int i = 0; i < n; i++) {
				graph[i] = new ArrayList<Vertex>();
			}
			
			st = new StringTokenizer(br.readLine());
			int start = Integer.valueOf(st.nextToken());
			int end = Integer.valueOf(st.nextToken());
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
	// 1. �� �� ���ͽ�Ʈ�� �ؼ� �ִ� ��� ���� �˾Ƴ�
	// 2. ��θ� �� �� ���Һ��� �����ϸ鼭 ���ȣ��� ���ͽ�Ʈ�� �˰����� ���, �ִ� ��ο� �ٸ��� �׳� �����ϱ�
	// 3. �ִ� ��� ����
	private static void find(int startPoint, int endPoint, int n) {
		resultDistance = Integer.MAX_VALUE;
		int[] result = dijkstra(startPoint, endPoint, n, Integer.MAX_VALUE);	// endPoint������ �ִ� ��� ����. ù �Լ� �����̶� �ִ밪 ���Ƿ� ����
		if(result[endPoint] == -1) {	// �ּ� ��ΰ� �������� ����
			sb.append(-1).append("\n");
		}
		else {	// �ּ� ��ΰ� ������
			int minDistance = distance[endPoint]; // �ּ� ��� ����
			deletePath(startPoint, endPoint, result, minDistance);
			if(resultDistance == Integer.MAX_VALUE) {
				sb.append(-1).append("\n");
			}
			else {
				sb.append(resultDistance).append("\n");	
			}
//			if(deletePath(startPoint, endPoint, result, minDistance)) {
//				if(distance[endPoint] == Integer.MAX_VALUE) {
//					sb.append(-1).append("\n");
//				}
//				else {
//					sb.append(distance[endPoint]).append("\n");	
//				}	
//			}
//			else {
//				if(distance[endPoint] == Integer.MAX_VALUE) {
//					sb.append(-1).append("\n");
//				}
//				else {
//					sb.append(distance[endPoint]).append("\n");	
//				}
//			}	
		}
	}
	
	// ��� ���� -> ������ : true. ���� ���� : false
	private static boolean deletePath(int startPoint, int endPoint, int[] result, int minDistance) {
		// ��� ����
		int endVertex = endPoint;	// ���������� ������ Ž��
		while(endVertex != startPoint) {	// �������� ���������� ���� ����
			int startVertex = result[endVertex];	// ������ ���� ��带 ���������� ����
			if(startVertex == -1) {
				return false;	
			}
			boolean isValid = false; // �׷��� ���� �̾����� Vertex�� �����ϴ� �� üũ
			Iterator<Vertex> iter = graph[startVertex].iterator();
			final int tempVertex = endVertex;	// removeIf�� ���� �ӽ� final ����
			graph[startVertex].removeIf(v -> (v.getVertex() == tempVertex));	// ����
			int[] temp = dijkstra(startPoint, endPoint, result.length, minDistance); // �ٽ� Ž��
			if(temp != null) { // �ٸ� �ִ� �Ÿ��� �� �ִ� ���
				deletePath(startPoint, endPoint, temp, minDistance);
			}
//			else {
//			}
			endVertex = startVertex;
//			while(iter.hasNext()) {
//				Vertex v = iter.next();
//				if(v.getVertex() == endVertex) {
//					graph[startVertex].remove(v);
//					int[] temp = dijkstra(startPoint, endPoint, result.length, minDistance);
//					if(temp != null) { // �ٸ� �ִ� �Ÿ��� �� �ִ� ���
//						deletePath(startPoint, endPoint, temp, minDistance);
//						return false;
//					}
//					else { // ���� �ִ� �Ÿ��� ���� ���
//						return true;
//					}
////					else {
////						endVertex = startVertex;
////						isValid = true;
////						break;
////					}	
//				}
//			}
			
//			for(Vertex v : graph[startVertex]) {
//				if(v.getVertex() == endVertex) {
//					graph[startVertex].remove(v);
//					int[] temp = dijkstra(startPoint, endPoint, result.length, minDistance);
//					if(temp != null) { // �ٸ� �ִ� �Ÿ��� �� �ִ� ���
//						deletePath(startPoint, endPoint, temp, minDistance);
//						break;
//					}
////					else {
////						endVertex = startVertex;
////						isValid = true;
////						break;
////					}	
//				}
//			}
//			endVertex = startVertex;
		}
		return true;
	}
	
	// ó���� �� ������ ���� �ִ� ��θ� 
	private static int[] dijkstra(int startPoint, int endPoint, int n, int minDistance) {
		PriorityQueue<Vertex> queue = new PriorityQueue<>();	// comparable�� ��ü �켱���� �ٲ�
		int[] result = new int[n];	// �ִ� ���
		boolean[] visited = new boolean[n];
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
		if(minDistance < distance[endPoint]) { // ���� �ִ� ����� ��� ���� ���� ���� ������� �����ϰ� �� ����
			resultDistance = distance[endPoint];
			return null;
		}
		return result;
	}
}

class Vertex implements Comparable<Vertex> {
	private int vertex;
	private int weight;
	
	Vertex(int vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
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
		if(this.weight < v.getWeight()) {
			return -1;
		}
		else
			return 1;
	}
}
