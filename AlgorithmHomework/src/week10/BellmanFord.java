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

// 벨만-포드 알고리즘.
// 그래프 상에 존재하는 두 노드 간의 최단 거리를 구하고 싶을 때 이용하는 알고리즘.
// 음의 가중치도 계산 가능
public class BellmanFord {
	static int[] distance;
	static boolean[] visited;
	static List<Edge> tempEdges = new ArrayList<Edge>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String args[]) {
		String fileSrc = new java.io.File("").getAbsolutePath();
		Scanner sc = new Scanner(System.in);
		System.out.print("1 또는 2를 입력하세요. 숫자에 맞는 텍스트 파일을 읽겠습니다 : ");
		String menu = sc.next();
		String inputFileSrc =  fileSrc;	// 상대 경로 설정
		if(menu.equals("1")) {
			 inputFileSrc += "/src/data11_bellman_ford_1.txt";
		}
		else if(menu.equals("2")) {
			inputFileSrc += "/src/data11_bellman_ford_2.txt";
		}
		else {
			System.out.println("잘못 입력해서 종료합니다.");
			System.exit(0);
		}
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(inputFileSrc));
			StringTokenizer st = new StringTokenizer(br.readLine(), ",");
			int n = st.countTokens();
			
			visited = new boolean[n];
			
			
			// 바로 다음 두 번째 줄
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
			// 옮겨담기
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
			System.out.println("오류가 발생했습니다. 프로그램을 종료합니다.");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("오류가 발생했습니다. 프로그램을 종료합니다.");
			System.exit(0);
		}
	}
	
	static boolean bellmanFord(Graph graph, int startPoint) {
		
		int v = graph.getVertex();
		int edgeCount = graph.getEdgeCount();
		distance = new int[v];
		
		// 1단계. 시작 지점을 제와한 나머지 점을 무한대로 설정
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[startPoint] = 0;

		// 2단계. V - 1(과제에서는 V까지 다 수행함)까지 알고리즘 반복 수행
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
		
		// 3단계. 음수 가중치 사이클 점검
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
