package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// ���� ���ø鼭 �ɾ�� 
// �÷��̵� �˰��� (�ٵ� BFS �Ẹ��) 
public class p9205 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int t = Integer.valueOf(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		while(t --> 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			
			Point9205[] point = new Point9205[n + 2];
			boolean[][] map = new boolean[n + 2][n + 2]; // �Է¹��� ������� index�� �ű� ��, y, x �� y index -> x index �� �̵��� �� �������� �Ұ������� ��Ƶδ� �迭
			
			for(int i = 0; i < n + 2; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.valueOf(st.nextToken());
				int y = Integer.valueOf(st.nextToken());
				
				point[i] = new Point9205(x, y);
				
			}
			
			// �� �� �ִ��� üũ 
			for(int i = 0; i < n + 1; i++) {
				Point9205 p1 = point[i];
				int p1X = p1.x;
				int p1Y = p1.y;
				for(int j = i + 1; j < n + 2; j++) {
					Point9205 p2 = point[j];
					
					// i index �������� j index �������� ������ �Ÿ����� üũ -> �����ϸ� True
					if(Math.abs(p1X - p2.x) + Math.abs(p1Y - p2.y) <= 1000) {
						// ����� �׷��� 
						map[i][j] = true;
						map[j][i] = true;
					}
				}
			}
			
			if(bfs(map, n)) {
				sb.append("happy");
			}
			else {
				sb.append("sad");
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	// True: �湮 ����. False : �湮 �Ұ��� 
	static boolean bfs(boolean[][] map, int n) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[n + 2];
		visited[0] = true;
		queue.offer(0);	// ������ �ֱ�
		
		while(!queue.isEmpty()) {
			int nowIndex = queue.poll();
			
			for(int i = 1; i < n + 2; i++) {	// ��������� ���ư� �ʿ�� �����ϱ�. ���ʿ� ����. 
				if(map[nowIndex][i]) {	// �̵� ���� ����
					if(i == n + 1) {	// ���������̸� ���� 
						return true;
					}

					if(!visited[i]) {	// �̹� �ش� ���������� �湮�� ���� �ִ� ��� -> ���� �� �� �� ť�� �ְ� ��� üũ�� �ʿ䰡 ����. 
						visited[i] = true;
						queue.offer(i);
					}
				}
			}
		}
		
		return false;
	}
}

class Point9205 {
	int x;
	int y;
	
	Point9205(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
