package dfsnfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// �丶�� ����
// BFS ����
public class p7576 {
	static int[][] box;
	static Queue<Point7576> nowQ;
	// �����¿�
	static int[] moveX = { 0, 0, -1, 1 };
	static int[] moveY = { -1, 1, 0, 0 };
	static int zeroCount = 0;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int m = Integer.valueOf(st.nextToken());	// ������ ���� ��
		int n = Integer.valueOf(st.nextToken());	// ������ ���� ��
		box = new int[n][m];
		nowQ = new LinkedList<Point7576>();	// ���� �� ť
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				int k = Integer.valueOf(st.nextToken());
				box[i][j] = k;
				if(k == 0) {
					zeroCount++;
				}
				if(k == 1) {
					nowQ.offer(new Point7576(j, i));
				}
			}
		}
		int day = bfs(n, m);
		System.out.println(day);
	}
	
	public static int bfs(int n, int m) {
		int result = 1;	// ������ ���� �� -1�� ���� ����. �ʱ� ���� �ϼ��� 1�� �� ���� �����ؼ� 1�� �����س�.
		while(!nowQ.isEmpty()) {
			Point7576 p = nowQ.poll();
			for(int i = 0; i < 4; i++) {	// �����¿� ��ĵ
				int nextX = p.x + moveX[i];
				int nextY = p.y + moveY[i];
				if((nextX >= m || nextX < 0) || (nextY >= n || nextY < 0)) {
					continue;
				}
				if(box[nextY][nextX] == 0) {
					nowQ.add(new Point7576(nextX, nextY));
					box[nextY][nextX] = box[p.y][p.x] + 1;
					zeroCount--;
					result = Math.max(result, box[nextY][nextX]);
				}
			}
		}
		if(zeroCount != 0) {
			return -1;
		}
		return result - 1;
	}
}

class Point7576 {
	int x;
	int y;
	
	Point7576(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
