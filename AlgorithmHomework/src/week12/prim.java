package week12;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// 201402407 이해원
// 프림 알고리즘
public class prim {
	static List<Vertex> graph[];
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	public static void main(String args[]) {
		int v = 9;
		int e = 14;
		visited = new boolean[v + 1];
		graph = new ArrayList[v + 1];
		for(int i = 0; i <= v; i++) {
			graph[i] = new ArrayList<Vertex>();
		}
		setGraphAndEdge();
		int result = prim(v);
		sb.append("\n");
		sb.append("w<MST> = ").append(result);
		System.out.println(sb.toString());
	}
	
	// 그래프에 직접 넣기
	static void setGraphAndEdge() {
		Vertex end;
		// Vertex A
		end = new Vertex(1, 2, 4);
		graph[1].add(end);
		end = new Vertex(1, 8, 8);
		graph[1].add(end);
		// Vertex B		
		end = new Vertex(2, 1, 4);
		graph[2].add(end);
		end = new Vertex(2, 9, 11);
		graph[2].add(end);
		end = new Vertex(2, 3, 8);
		graph[2].add(end);
		// Vertex C
		end = new Vertex(3, 2, 8);
		graph[3].add(end);
		end = new Vertex(3, 4, 7);
		graph[3].add(end);
		end = new Vertex(3, 6, 4);
		graph[3].add(end);
		end = new Vertex(3, 9, 2);
		graph[3].add(end);
		// Vertex D
		end = new Vertex(4, 3, 7);
		graph[4].add(end);
		end = new Vertex(4, 5, 9);
		graph[4].add(end);
		end = new Vertex(4, 6, 14);
		graph[4].add(end);
		// Vertex E
		end = new Vertex(5, 4, 9);
		graph[5].add(end);
		end = new Vertex(5, 6, 10);
		graph[5].add(end);
		// Vertex F
		end = new Vertex(6, 5, 10);
		graph[6].add(end);
		end = new Vertex(6, 4, 14);
		graph[6].add(end);
		end = new Vertex(6, 3, 4);
		graph[6].add(end);
		// Vertex G
		end = new Vertex(7, 6, 2);
		graph[7].add(end);
		end = new Vertex(7, 9, 6);
		graph[7].add(end);
		end = new Vertex(7, 8, 1);
		graph[7].add(end);
		// Vertex H
		end = new Vertex(8, 1, 8);
		graph[8].add(end);
		end = new Vertex(8, 7, 1);
		graph[8].add(end);
		end = new Vertex(8, 9, 7);
		graph[8].add(end);
		// Vertex I
		end = new Vertex(9, 3, 2);
		graph[9].add(end);
		end = new Vertex(9, 7, 6);
		graph[9].add(end);
		end = new Vertex(9, 8, 7);
		graph[9].add(end);
	}
	
	static int prim(int v) {
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		int start = 1;
		int result = 0;
		int count = 0;
		visited[start] = true;
		for(Vertex vertex : graph[start]) {
			queue.offer(vertex);
		}
		String startVertexStr = " ", nextVertexStr = changeVertexToAlphabet(start);
		sb.append("w<").append(startVertexStr).append(",").append(nextVertexStr).append("> = 0 \n");
		startVertexStr = nextVertexStr;
		
		while(!queue.isEmpty()) {
			Vertex nextVertex = queue.poll();
			int startV = nextVertex.getStartVertex();
			int nextV = nextVertex.getEndVertex();
			int nextWeight = nextVertex.getWeight();
			if(visited[nextV]) {
				continue;
			}
			else {
				startVertexStr = changeVertexToAlphabet(startV);
				nextVertexStr = changeVertexToAlphabet(nextV);				
				sb.append("w<").append(startVertexStr).append(",")
				.append(nextVertexStr).append("> = ").append(nextWeight).append("\n");

				visited[nextV] = true;
				count++;
				result += nextWeight;
				startVertexStr = nextVertexStr;
				
				if(count + 1 == v) {	// 종료 조건
					break;
				}
			}
			
			// 시작 점 빼고 모든 vertex를 방문하기 전까지 경로 탐색
			for(Vertex vertex : graph[nextV]) {
				queue.offer(vertex);
			}
		}
		return result;
	}
	
	static String changeVertexToAlphabet(int vertex) {
		switch(vertex) {
		case 1:		return "A";
		case 2:		return "B";
		case 3:		return "C";
		case 4:		return "D";
		case 5:		return "E";
		case 6:		return "F";
		case 7:		return "G";
		case 8:		return "H";
		case 9:		return "I";
		default: 	return " ";
		}
	}
}

class Vertex implements Comparable<Vertex> {
	int startVertex;
	int endVertex;
	int weight;
	
	Vertex(int startVertex, int endVertex, int weight) {
		this.startVertex = startVertex;
		this.endVertex = endVertex;
		this.weight = weight;
	}
	
	
	public int getStartVertex() {
		return startVertex;
	}


	public void setStartVertex(int startVertex) {
		this.startVertex = startVertex;
	}


	public int getEndVertex() {
		return endVertex;
	}


	public void setEndVertex(int endVertex) {
		this.endVertex = endVertex;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}

	// 우선순위 낮은 값부터
	@Override
	public int compareTo(Vertex o) {
		if(this.weight < o.weight) {
			return -1;
		}
		else
			return 1;
	}
}
