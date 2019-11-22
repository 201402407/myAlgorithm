package week10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

// ����-���� �˰���.
// �׷��� �� �����ϴ� �� ��� ���� �ִ� �Ÿ��� ���ϰ� ���� �� �̿��ϴ� �˰���.
// ���� ����ġ�� ��� ����
public class BellmanFord {
	static int[] distance;
	static boolean[] visited;
	static List<Edge> tempEdges = new ArrayList<Edge>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String args[]) {
		String fileSrc = new java.io.File("").getAbsolutePath();
		Scanner sc = new Scanner(System.in);
		System.out.print("1 �Ǵ� 2�� �Է��ϼ���. ���ڿ� �´� �ؽ�Ʈ ������ �аڽ��ϴ� : ");
		String menu = sc.next();
		String inputFileSrc =  fileSrc;	// ��� ��� ����
		if(menu.equals("1")) {
			 inputFileSrc += "/src/data11_bellman_ford_1.txt";
		}
		else if(menu.equals("2")) {
			inputFileSrc += "/src/data11_bellman_ford_2.txt";
		}
		else {
			System.out.println("�߸� �Է��ؼ� �����մϴ�.");
			System.exit(0);
		}
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(inputFileSrc));
			StringTokenizer st = new StringTokenizer(br.readLine(), ",");
			int n = st.countTokens();
			
			visited = new boolean[n];
			
			
			// �ٷ� ���� �� ��° ��
			String str;
			while((str = br.readLine()) != null) {
				st = new StringTokenizer(str, ",");
				int from = Integer.valueOf(st.nextToken());
				int to = Integer.valueOf(st.nextToken());
				int weight = Integer.valueOf(st.nextToken());
				Edge e = new Edge(from, to, weight);
				tempEdges.add(e);
			}
			
			Graph graph = new Graph(n, tempEdges.size());
			// �Űܴ��
			for(int i = 0; i < tempEdges.size(); i++) {
				Edge e = tempEdges.get(i);
				graph.setEdge(i, e);
			}
			
			if(bellmanFord(graph, 0)) {
				sb.append("Final distance : ");
				setAllDistanceToSB();
			}
			else {
				sb.append("The graph has negative cycle");
			}
			System.out.println(sb.toString());
			
		} catch (FileNotFoundException e) {
			System.out.println("������ �߻��߽��ϴ�. ���α׷��� �����մϴ�.");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("������ �߻��߽��ϴ�. ���α׷��� �����մϴ�.");
			System.exit(0);
		}
	}
	
	static boolean bellmanFord(Graph graph, int startPoint) {
		
		int v = graph.getVertex();
		int edgeCount = graph.getEdgeCount();
		distance = new int[v];
		
		// 1�ܰ�. ���� ������ ������ ������ ���� ���Ѵ�� ����
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[startPoint] = 0;

		// 2�ܰ�. V - 1(���������� V���� �� ������)���� �˰��� �ݺ� ����
		for(int i = 0; i < v; ++i) {
			sb.append("-------------------------- Iteration ").append(i).append(" -------------------------- \n");
			for(int j = 0; j < edgeCount; ++j) {
				int start = graph.getEdge(j).getStart();
				int end = graph.getEdge(j).getEnd();
				int weight = graph.getEdge(j).getWeight();
				if(distance[start] != Integer.MAX_VALUE && distance[start] + weight < distance[end]) {
					int temp = distance[end];
					distance[end] = distance[start] + weight;
					sb.append("Update distance of ").append(end).append(" from ");
					if(temp == Integer.MAX_VALUE) 
						sb.append("inf");	
					else 
						sb.append(temp);
					sb.append(" to ").append(distance[end]).append("\n");
				}
			}
			sb.append("Iteration ").append(i).append(" distance : ");
			setAllDistanceToSB();
		}
		
		// 3�ܰ�. ���� ����ġ ����Ŭ ����
//		boolean check = true;
		for(int j = 0; j < edgeCount; ++j) {
			int start = graph.getEdge(j).getStart();
			int end = graph.getEdge(j).getEnd();
			int weight = graph.getEdge(j).getWeight();
			if(distance[start] != Integer.MAX_VALUE && distance[start] + weight < distance[end]) {
//				check = false;
				return false;
			}
		}
//		sb.append("-------------------------- Iteration ").append(v - 1).append(" -------------------------- \n");
//		sb.append("Iteration ").append(v - 1).append(" distance : ");
//		setAllDistanceToSB();
//		return check;
		return true;
	}
	
	static void setAllDistanceToSB() {
		sb.append("[");
		for(int i = 0; i < distance.length; i++) {
			if(i + 1 == distance.length) {
				sb.append(distance[i]);
			}
			else {
				sb.append(distance[i]).append(", ");	
			}
		}
		sb.append("] \n\n");
	}
}
class Graph {
	private int vertex, edgeCount;
	private Edge[] edges;

	Graph(int vertex, int edgeCount) {
		this.vertex = vertex;
		this.edgeCount = edgeCount;
		edges = new Edge[edgeCount];
		for(int i = 0; i < edgeCount; i++) {
			edges[i] = new Edge();
		}
	}

	public int getVertex() {
		return vertex;
	}

	public void setVertex(int vertex) {
		this.vertex = vertex;
	}

	public int getEdgeCount() {
		return edgeCount;
	}

	public void setEdgeCount(int edgeCount) {
		this.edgeCount = edgeCount;
	}
	
	public void setEdge(int i, Edge edge) {
		this.edges[i] = edge;
	}
	
	public Edge getEdge(int i) {
		return this.edges[i];
	}
	
	public Edge[] getEdges() {
		return this.edges;
	}
}

class Edge {
	private int start, end, weight;
	
	Edge() {
		start = end = weight = 0;
	}
	Edge(int start, int end, int weight) {
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
