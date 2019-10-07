package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// ��ȥ�� ����
// BFS
// �й� 1���� ģ���� ģ���� ģ���� ����
// 1���� ����� �ֵ�, �³׿� ����� �ֵ鸸 ���
public class p5567 {
	static ArrayList<Integer>[] map;
	static boolean[] visited;
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int m = Integer.valueOf(st.nextToken());
			
			map = new ArrayList[n + 1];
			visited = new boolean[n + 1];
			for(int i = 1; i <= n; i++) {
				map[i] = new ArrayList<Integer>();
			}
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.valueOf(st.nextToken());
				int end = Integer.valueOf(st.nextToken());
				map[start].add(end);
				map[end].add(start);
			}
			int count = bfs();
			System.out.println(count);
		} catch (IOException e) {
			System.exit(0);
		}
	}
	
	public static int bfs() {
		// ���ΰ��� �й��� 1�̹Ƿ� 1���� ����
		Queue<Friend> queue = new LinkedList<Friend>();
		queue.offer(new Friend(1, 0));
		visited[1] = true;
		int count = 0;
		while(!queue.isEmpty()) {
			Friend friend = queue.poll();
			if(friend.getCount() >= 2) {	// ģ���� ģ���� ģ�����ʹ� �ȵǹǷ� ��
				continue;
			}
			
			for(int element : map[friend.getNumber()]) {
				if(!visited[element]) {	// ģ���� ģ�����ʹ� �ȵȴ�.
					visited[element] = true;
//					System.out.println(element + " in number : " + friend.getNumber() + ", count : " + friend.getCount());
					queue.offer(new Friend(element, friend.getCount() + 1));
					count++;
				}
			}
		}
		return count;
	}
}

class Friend {
	private int number;
	private int count;
	
	Friend(int number, int count) {
		this.number = number;
		this.count = count;
	}
	
	public int getNumber() {
		return this.number; 
	}
	
	public int getCount() {
		return this.count;
	}
}
