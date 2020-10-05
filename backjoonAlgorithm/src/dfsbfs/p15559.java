package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// �� ������ �޾���
// DFS 
public class p15559 {
	static int n, m;	// n : ���� // m : ���� 
	static char[][] map;
	static int[][] mapLevel;	// ���� �������� ���� ���� 
	static int count, result;
	static Point15559 startPoint;
	// ��-��-��-�� 
	static int[] moveX = {0, 1, 0, -1};
	static int[] moveY = {-1, 0, 1, 0};
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		map = new char[n][m];
		mapLevel = new int[n][m];
		
		// �Է¹ޱ� 
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(mapLevel[i][j] == 0) {	// �湮 ������ �ƴ� ���
					count++;
					mapLevel[i][j] = count;
					dfs(new Point15559(j, i));
				}
			}
		}
		
		System.out.println(result);
		
	}
	
	// DFS �˰��� 
	static void dfs(Point15559 nowPoint) {
		int direction = getMovePoint(nowPoint.x, nowPoint.y);
		Point15559 nextPoint = new Point15559(nowPoint.x + moveX[direction], nowPoint.y + moveY[direction]);
		if(mapLevel[nextPoint.y][nextPoint.x] == 0) {
			mapLevel[nextPoint.y][nextPoint.x] = count;
			dfs(nextPoint);
		}
		else if(mapLevel[nextPoint.y][nextPoint.x] == count) {	// DFS �����ؼ� ���� �ִ� ������ �湮�� ��� ������ ����Ŭ ������
			result++;
		}
	}

	// �ش� ������ ������ ���� ���� ������ ����Ʈ ��ü ���� 
	static int getMovePoint(int x, int y) {
		char direction = map[y][x];
		switch(direction) {
		case 'N':
			return 0;
		case 'E':
			return 1;
		case 'S':
			return 2;
		case 'W':
			return 3;
		}
		
		return -1;
	}
}

class Point15559 {
	int x;
	int y;
	
	Point15559(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
