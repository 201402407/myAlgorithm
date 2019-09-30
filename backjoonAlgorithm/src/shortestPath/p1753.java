package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// �ִ� ��� ����
// ���ͽ�Ʈ�� �˰��� ?
// ���� Ž������ ���� ������� ã�� ��� ��ſ� �켱���� ť�� ����ؼ� �ִܰŸ� ���Ű� ���ÿ� ť�� ����.
public class p1753 {
	static List<EndVertex> graph[];
	static int[] distance;
	static boolean[] visited;
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		try {
			st = new StringTokenizer(br.readLine());
			int v = Integer.valueOf(st.nextToken());
			int e = Integer.valueOf(st.nextToken());
			// 1���� ���� v���� �����ϴ� �׷���
			graph = new ArrayList[v + 1];
			distance = new int[v + 1];
			visited = new boolean[v + 1];
			int startPoint = Integer.valueOf(br.readLine());
			
			for(int i = 1; i <= v; i++) {	// �ʱ�ȭ
				graph[i] = new ArrayList<EndVertex>();
//				distance[i] = Integer.MAX_VALUE;	// ���Ѵ� ��� �̰ŷ�	
			}
			Arrays.fill(distance, Integer.MAX_VALUE);
			distance[startPoint] = 0;	// ���� ����Ʈ�� 0���� ����
			
			for(int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.valueOf(st.nextToken());
				int to = Integer.valueOf(st.nextToken());
				int weight = Integer.valueOf(st.nextToken());
				EndVertex end = new EndVertex(to, weight);
				graph[from].add(end);
			}
			
			dijkstra(v, startPoint);	// v�� ���� vertex
			
			StringBuilder sb = new StringBuilder();
			for(int i = 1; i < distance.length; i++) {
				int element = distance[i];
				if(element == Integer.MAX_VALUE) 
					sb.append("INF").append("\n");
				else
					sb.append(element).append("\n");	
			}
			System.out.println(sb.toString());
		}
		catch (NumberFormatException e) {
			System.exit(0);
		}
		catch (IOException e) {
			System.exit(0);
		}
	}
	
	private static void dijkstra(int v, int startPoint) {
		PriorityQueue<EndVertex> queue = new PriorityQueue<>();	// comparable�� ��ü �켱���� �ٲ�
		queue.offer(new EndVertex(startPoint, distance[startPoint]));
		
		while(!queue.isEmpty()) { 
			EndVertex startVertex = queue.poll();
			startPoint = startVertex.getEndPoint();
			visited[startPoint] = true;
			// ���� �� ���� ��� vertex�� �湮�ϱ� ������ ��� Ž��
			for(EndVertex vertex : graph[startPoint]) {
				int endPoint = vertex.getEndPoint();
				if(!visited[endPoint] && distance[endPoint] > distance[startPoint] + vertex.getWeight()) {
					distance[endPoint] = distance[startPoint] + vertex.getWeight();
					queue.offer(new EndVertex(endPoint, distance[endPoint]));	
				}
			}
		}
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