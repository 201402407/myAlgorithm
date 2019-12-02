package week11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// 201402407 ���ؿ�
// ���ͽ�Ʈ�� �˰���
public class dijkstra {
	static List<EndVertex> graph[];
	static int[] distance, changeDistance;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	public static void main(String args[]) {
		int v = 5;	// Vertex A,B,C,D,E 5��
		int e = 9;	// 9���� edge
		
		// 1���� ���� v���� �����ϴ� �׷���
		graph = new ArrayList[v + 1];	// �ε��� 1 ~ 5����
		distance = new int[v + 1];
		changeDistance = new int[v + 1];
		visited = new boolean[v + 1];
		int startPoint = 1;	// A���� �����ϹǷ�
		for(int i = 1; i <= v; i++) {	// �ʱ�ȭ
			graph[i] = new ArrayList<EndVertex>();	
		}
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		Arrays.fill(changeDistance, -1);	// -1�� �ؼ� ���� ó��
		distance[startPoint] = 0;	// ���� ����Ʈ�� 0���� ����
		EndVertex end;
		// �׷����� ���� �ֱ�
		end = new EndVertex(2, 10);
		graph[1].add(end);
		end = new EndVertex(3, 3);
		graph[1].add(end);
		end = new EndVertex(3, 1);
		graph[2].add(end);
		end = new EndVertex(4, 2);
		graph[2].add(end);
		end = new EndVertex(2, 4);
		graph[3].add(end);
		end = new EndVertex(4, 8);
		graph[3].add(end);
		end = new EndVertex(5, 2);
		graph[3].add(end);
		end = new EndVertex(5, 7);
		graph[4].add(end);
		end = new EndVertex(4, 9);
		graph[5].add(end);
		
		dijkstra(v, startPoint);
		System.out.println(sb.toString());
	}
	
	private static void dijkstra(int v, int startPoint) {
		sb.append("Dijkstra Algorithm���� ����� ����� ������ �����ϴ�. \n");
		PriorityQueue<EndVertex> queue = new PriorityQueue<>();	// comparable�� ��ü �켱���� �ٲ�
		queue.offer(new EndVertex(startPoint, distance[startPoint]));
		
		int count = 0;
		while(!queue.isEmpty()) { 
			EndVertex startVertex = queue.poll();
			startPoint = startVertex.getEndPoint();
			if(!visited[startPoint]) {
				visited[startPoint] = true;
				sb.append("___________________________________________________________ \n");
				sb.append("S[").append(count++).append("] : d[").append(getAlphabetFromIndex(startPoint)).append("]")
				.append(" = ").append(distance[startPoint]).append("\n");
				sb.append("----------------------------------------------------------- \n");	
			}
			// ���� �� ���� ��� vertex�� �湮�ϱ� ������ ��� Ž��
			for(EndVertex vertex : graph[startPoint]) {
				int endPoint = vertex.getEndPoint();
				if(!visited[endPoint] && distance[endPoint] > distance[startPoint] + vertex.getWeight()) {
					changeDistance[endPoint] = distance[endPoint];
					distance[endPoint] = distance[startPoint] + vertex.getWeight();					
					queue.offer(new EndVertex(endPoint, distance[endPoint]));	
				}
			}
			
			int count2 = 0;
//			for(EndVertex end : queue) {
//				int endPoint = end.getEndPoint();
//				if(!visited[endPoint]) {
//					sb.append("Q[").append(count2++).append("] : d[").append(getAlphabetFromIndex(endPoint)).append("]");
//					if(changeDistance[endPoint] != -1) {
//						sb.append(" = ").append(changeDistance[endPoint]).append(" -> ")
//						.append("d[").append(getAlphabetFromIndex(endPoint)).append("] = ").append(distance[endPoint]).append("\n");
//					}
//					else {
//						sb.append(" = ").append(distance[endPoint]).append("\n");
//					}
//				}
//			}
			// �ܰ� �� �켱���� ť�� ����ִ� ��(= ���� Ž������ ���� ��)�� ����ϴ� �ڵ�
			for(int i = 1; i <= v; i++) {
				if(!visited[i]) {
					sb.append("Q[").append(count2++).append("] : d[").append(getAlphabetFromIndex(i)).append("]");
					if(changeDistance[i] != -1) {
						sb.append(" = ").append(changeDistance[i]).append(" -> ")
						.append("d[").append(getAlphabetFromIndex(i)).append("] = ").append(distance[i]).append("\n");
					}
					else {
						sb.append(" = ").append(distance[i]).append("\n");
					}
				}
			}
			sb.append("\n");
			Arrays.fill(changeDistance, -1);	// -1�� �ؼ� ���� ó��
		}
	}
	
	private static String getAlphabetFromIndex(int index) {
		switch(index) {
		case 1:
			return "A";
		case 2:
			return "B";
		case 3:
			return "C";
		case 4:
			return "D";
		case 5:
			return "E";
		}
		return "-";
	}
}

class EndVertex implements Comparable<EndVertex> {
	private int endPoint;
	private int weight;
	
	EndVertex(int endPoint, int weight) {
		this.weight = weight;
		this.endPoint = endPoint;
	}
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(int endPoint) {
		this.endPoint = endPoint;
	}

	// �̰ſ� ���� �켱���� ť�� �켱������ �ٲ�
	// �������� : �켱������ ���� �ź���
	// �������� : �켱������ ���� �ź���
	@Override
	public int compareTo(EndVertex o) {
		if(this.weight < o.getWeight()) {
			return -1;
		}
		else
			return 1;
	}
}

