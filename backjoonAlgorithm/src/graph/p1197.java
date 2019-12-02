package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// �ּ� ���д� Ʈ��
// ���� �˰��� ���
public class p1197 {
	static List<Vertex>[] graph;
	static boolean[] visited;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.valueOf(st.nextToken());
		int e = Integer.valueOf(st.nextToken());
		graph = new ArrayList[v + 1];
		visited = new boolean[v + 1];
		for(int i = 0; i <= v; i++) {
			graph[i] = new ArrayList<Vertex>();
		}
		
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.valueOf(st.nextToken());
			int end = Integer.valueOf(st.nextToken());
			int weight = Integer.valueOf(st.nextToken());
			Vertex vertex1 = new Vertex(start, end, weight);
			Vertex vertex2 = new Vertex(end, start, weight);
			graph[start].add(vertex1);
			graph[end].add(vertex2);
		}
		int result = prim(v);
		System.out.println(result);
	}
	
	static int prim(int v) {
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		int result = 0;
		int count = 0;
		int start = 1;
		visited[start] = true;
		for(Vertex vertex : graph[start]) {
			queue.offer(vertex);
		}
		
		while(!queue.isEmpty()) {
			Vertex nextVertex = queue.poll();
			int startV = nextVertex.getStartVertex();
			int nextV = nextVertex.getEndVertex();
			int nextWeight = nextVertex.getWeight();
			if(visited[nextV]) {
				continue;
			}
			else {
				visited[nextV] = true;
				count++;
				result += nextWeight;
				if(count + 1 == v) {	// ���� ����
					break;
				}
			}
			// ���� �� ���� ��� vertex�� �湮�ϱ� ������ ��� Ž��
			for(Vertex vertex : graph[nextV]) {
				queue.offer(vertex);
			}
		}
		return result;
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

	// �켱���� ���� ������
	@Override
	public int compareTo(Vertex o) {
		if(this.weight < o.weight) {
			return -1;
		}
		else
			return 1;
	}
}
