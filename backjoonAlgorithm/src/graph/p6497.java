package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// ���³� ����
// MST(�ּ� ���д� Ʈ��)
// ũ�罺Į �˰���
public class p6497 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.valueOf(st.nextToken());	// Node�� ��
			int n = Integer.valueOf(st.nextToken());	// ������ ��
			if(m == 0 && n == 0) {
				System.out.println(sb.toString());
				break;
			}
			
			PriorityQueue<Vertex6497> queue = new PriorityQueue<Vertex6497>();
			int[] parent = new int[m];	// �θ� ��� �ε��� �迭
			for(int i = 0; i < m; i++) {
				parent[i] = i;
			}
			
			int allPrice = 0;
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.valueOf(st.nextToken());
				int end = Integer.valueOf(st.nextToken());
				int weight = Integer.valueOf(st.nextToken());
				allPrice += weight;
				queue.offer(new Vertex6497(start, end, weight));
			}
			
			int count = 0;
			int sum = 0;
			while(!queue.isEmpty()) {
				Vertex6497 vertex = queue.poll();
				int start = vertex.getStart();
				int end = vertex.getEnd();
				int weight = vertex.getWeight();
				
				if(!isSameParent(parent, start, end)) {
					count++;
					sum += weight;
					union(parent, start, end);
					if(count + 1 == m) {
						sb.append(allPrice - sum).append("\n");
						break;
					}
				}
				
			}
		}
	}
	
	// �� �θ� ��� ���� �θ����� Ž���ϴ� �Լ�
	public static boolean isSameParent(int[] parent, int x, int y) {
		x = find(parent, x);
		y = find(parent, y);
		return x == y ? true : false;
	}
	
	// �ش� ������ �θ� ��带 ���� �ϴ� �Լ�
	public static void union(int[] parent, int x, int y) {
		x = find(parent, x);
		y = find(parent, y);
		if(x != y)
			parent[y] = x;
	}
	
	// �ش� ����� �θ� ��带 ã�� �Լ�
	public static int find(int[] parent, int x) {
		if(parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent, parent[x]);
	}
}

class Vertex6497 implements Comparable<Vertex6497> {
	private int start;
	private int end;
	private int weight;
	
	Vertex6497(int start, int end, int weight) {
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

	@Override
	public int compareTo(Vertex6497 o) {
		if(this.weight < o.getWeight()) {
			return -1;
		}
		else
			return 1;
	}
	
	
}