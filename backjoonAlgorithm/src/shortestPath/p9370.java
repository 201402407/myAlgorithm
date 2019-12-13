package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// ��Ȯ�� ������
// ���� ������ ���ͽ�Ʈ�� �˰��� ���°�
public class p9370 {
	static int[] distance;
	static int[] realDistance;
	static boolean[] visited;
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
			distance = new int[n + 1];
			realDistance = new int[n + 1];
			visited = new boolean[n + 1];
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
				if((start == g && end == h) || (start == h && end == g)) {	// �ش� ���θ� �׷����� ���� �ʱ�
					betweenDistance = weight;
					continue;
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
		
			Arrays.fill(distance, Integer.MAX_VALUE);
			distance[startPoint] = 0;
			dijkstra(startPoint, g, h, n);	// Ư�� g-h ���θ� ������ �ִ� ���
			
			graph[g].add(new Vertex9370(h, betweenDistance));
			graph[h].add(new Vertex9370(g, betweenDistance));
			Arrays.fill(realDistance, Integer.MAX_VALUE);
			realDistance[startPoint] = 0;
			dijkstra(startPoint, n);
			
//			System.out.println(distance[5] + ", " + realDistance[5]);
			// Ư�� g-h ���θ� ������ ���� �ִ� ��ο� ���� �ִ� ��ΰ� ���ƾ� �������� �����ϴ�.
			for(int i = 0; i < dests.size(); i++) {
				int index = dests.get(i);
				if(distance[index] == Integer.MAX_VALUE && realDistance[index] == Integer.MAX_VALUE) {
					dests.remove(i);
					i--;
				}
				if(distance[index] != realDistance[index]) {
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
	
	public static void dijkstra(int startPoint, int g, int h, int n) {
		PriorityQueue<Vertex9370> queue = new PriorityQueue<Vertex9370>();
		
		queue.offer(new Vertex9370(startPoint, distance[startPoint]));
		boolean takeSmell = false;
		while(!queue.isEmpty()) {
			Vertex9370 vertex = queue.poll();
			int start = vertex.getEnd();
			
			visited[start] = true;
			if(start == g) {	// ���������� �� ���
				if(!takeSmell) {
					queue.clear();
					distance[h] = distance[start] + betweenDistance;
					queue.offer(new Vertex9370(h, distance[h]));
					takeSmell = true;
					continue;	
				}
			}
			
			if(start == h) {	// ���������� �� ���
				if(!takeSmell) {
					queue.clear();
					distance[g] = distance[start] + betweenDistance;
					queue.offer(new Vertex9370(g, distance[g]));
					takeSmell = true;
					continue;	
				}
			}
			System.out.println(start);
			for(int a : distance) {
				System.out.print(a + " , ");
			}
			System.out.println();
 			for(Vertex9370 nextVertex : graph[start]) {
				int end = nextVertex.getEnd();
				int dist = nextVertex.getDistance();
				System.out.println("end : " + end + ", dist : " + dist);
				if(!visited[end] && distance[end] > distance[start] + dist) {
					distance[end] = distance[start] + dist;
					queue.offer(new Vertex9370(end, distance[end]));
				}
			}
		}
	}
}
