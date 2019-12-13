package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// Ÿ�Ӹӽ� ����
// ���� ���� �˰��� ����غ��ڽ��ϴ�
// ���� ����ġ�� �����ϱ� ������
public class p11657 {
	static List<Vertex11657> graph;	// ��� vertex�� ��� ����Ʈ
	static int[] distance;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		graph = new ArrayList<Vertex11657>();
		distance = new int[n + 1];	// 1������ n�� ����
		for(int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.valueOf(st.nextToken());
			int end = Integer.valueOf(st.nextToken());
			int weight = Integer.valueOf(st.nextToken());
			graph.add(new Vertex11657(start, end, weight));
		}
		
		boolean isMinusCycle = bellman(n, m, 1);
		StringBuilder sb = new StringBuilder();
		if(!isMinusCycle) {
			sb.append("-1");
		}
		else {
			for(int i = 2; i <= n; i++) {
				if(distance[i] == Integer.MAX_VALUE) {
					sb.append("-1");
				}	
				else  {
					sb.append(distance[i]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	public static boolean bellman(int n, int m, int startPoint) {
		// 1�ܰ� : ���� ���� 0, ������ ���� ���Ѵ�� �����
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[startPoint] = 0;
		
		// 2�ܰ� : ��ü ��(n) - 1�� ��ŭ ��ü Vertex�� Ž���ϱ�
		for(int i = 1; i < n; i++) {
			boolean isChange = false;	// ��ü ������ ���鼭 �ִ� ��ΰ� ���� ���ŵǴ��� �����ϴ� boolean ����
			for(int j = 0; j < m; j++) {
				Vertex11657 vertex = graph.get(j);
				// �켱, ���� ���� �� ���� �湮���� �ʾҴ� ���̸� �ȵǰ�(�� ó�� ���� ������ �ش� �� �������� �ִ� ����ġ ��θ� ã�ƾ� �ϱ� ������)
				// �ٸ� ���� ��κ��� ª�� ��츦 ã�ƾ� �ϹǷ�
				if(distance[vertex.getStart()] != Integer.MAX_VALUE && 
						distance[vertex.getStart()] + vertex.getWeight() < distance[vertex.getEnd()]) {
					distance[vertex.getEnd()] = distance[vertex.getStart()] + vertex.getWeight();
					isChange = true;
				}
			}
			if(!isChange) {	// �ִ� ��� ���� ����
				break;
			}
		}
		
		// 3�ܰ� : ���� ����ġ ����Ŭ ����
		for(int i = 0; i < m; i++) {
			Vertex11657 vertex = graph.get(i);
			// �켱, ���� ���� �� ���� �湮���� �ʾҴ� ���̸� �ȵǰ�(�� ó�� ���� ������ �ش� �� �������� �ִ� ����ġ ��θ� ã�ƾ� �ϱ� ������)
			// �ٸ� ���� ��κ��� ª�� ��츦 ã�ƾ� �ϹǷ�
			if(distance[vertex.getStart()] != Integer.MAX_VALUE && 
					distance[vertex.getStart()] + vertex.getWeight() < distance[vertex.getEnd()]) {
				return false;	// ���� ����ġ ����
			}
		}
		
		return true;	// ���� ����ġ ���� X
	}
}

class Vertex11657 {
	private int start;
	private int end;
	private int weight;
	
	Vertex11657(int start, int end, int weight) {
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
