package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 9370�� ���� 
// ȹ������ �ַ������ Ǯ���
// g-h ���δ� -1�� �ؼ� Ȧ���� �����, ������ ���δ� 2�� ���ؼ� ¦���� ����������
// �׷��� ���ͽ�Ʈ�� �������� ��, ������ �ĺ��� �� Ȧ���� ������ �ش� g-h ��θ� ���� ���� �ǹǷ� �������� �ǰ�, ¦���� �ƴѰ� �Ǵ°Ŵ�..!!
// ���ͽ�Ʈ�� �˰���
public class p9370_best {
	static int[] realDistance;
	static boolean[] realVisited;
	static ArrayList<Vertex9370>[] graph;
	static ArrayList<Integer> dests;
	static int betweenDistance;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.valueOf(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int testCase = 0; testCase < tc; testCase++) {
			// ù ° ��. ������, ����, ������ �ĺ� ����
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			int m = Integer.valueOf(st.nextToken());
			int t = Integer.valueOf(st.nextToken());
			graph = new ArrayList[n + 1];
			realDistance = new int[n + 1];
			realVisited = new boolean[n + 1];
			dests = new ArrayList<Integer>();
			
			for(int i = 1; i <= n; i++) {
				graph[i] = new ArrayList<Vertex9370>();
			}
			
			// �ι� ° ��. �����, g�� h����
			st = new StringTokenizer(br.readLine());
			int startPoint = Integer.valueOf(st.nextToken());
			int g = Integer.valueOf(st.nextToken());
			int h = Integer.valueOf(st.nextToken());
			
			// ����
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.valueOf(st.nextToken());
				int end = Integer.valueOf(st.nextToken());
				int weight = Integer.valueOf(st.nextToken());
				weight *= 2;
				if((start == g && end == h) || (start == h && end == g)) {	// g-h���θ� -1�� �ؼ� Ȧ���� �����
					weight--;
				}
				graph[start].add(new Vertex9370(end, weight));
				graph[end].add(new Vertex9370(start, weight));
			}
			
			// ������ �ĺ�
			for(int i = 0; i < t; i++) {
				st = new StringTokenizer(br.readLine());
				int dest = Integer.valueOf(st.nextToken());
				dests.add(dest);
			}
			
			Arrays.fill(realDistance, Integer.MAX_VALUE);
			realDistance[startPoint] = 0;
			dijkstra(startPoint, n);
			
			// Ư�� g-h ���θ� ������ ���� �ִ� ��ο� ���� �ִ� ��ΰ� ���ƾ� �������� �����ϴ�.
			for(int i = 0; i < dests.size(); i++) {
				int index = dests.get(i);
				if(realDistance[index] == Integer.MAX_VALUE || realDistance[index] % 2 == 0) {	// ¦���� �������� �ƴϹǷ� ����
					dests.remove(i);
					i--;
				}
			}
			Collections.sort(dests);
			
			for(int ele : dests) {
				sb.append(ele).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void dijkstra(int startPoint, int n) {
		PriorityQueue<Vertex9370> queue = new PriorityQueue<Vertex9370>();
		
		queue.offer(new Vertex9370(startPoint, realDistance[startPoint]));
		while(!queue.isEmpty()) {
			Vertex9370 vertex = queue.poll();
			int start = vertex.getEnd();
			realVisited[start] = true;
			
 			for(Vertex9370 nextVertex : graph[start]) {
				int end = nextVertex.getEnd();
				int dist = nextVertex.getDistance();
				if(!realVisited[end] && realDistance[end] > realDistance[start] + dist) {
					realDistance[end] = realDistance[start] + dist;
					queue.offer(new Vertex9370(end, realDistance[end]));
				}
			}
		}
	}
}

class Vertex9370 implements Comparable<Vertex9370> {
	private int end;
	private int distance;
	
	Vertex9370(int end, int distance) {
		this.end = end;
		this.distance = distance;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public int compareTo(Vertex9370 o) {
		if(this.distance < o.getDistance()) {
			return -1;
		}
		else
			return 1;
	}
}
