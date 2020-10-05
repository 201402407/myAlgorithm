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
		
//		for(int i = 0; i < n; i++) {
//			for(int j = 0; j < m; j++) {
//				System.out.print(mapLevel[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		System.out.println(result);
		
	}
	
	// DFS �˰��� 
	static void dfs(Point15559 nowPoint) {
		Point15559 nextPoint = getMovePoint(nowPoint.x, nowPoint.y);
		if(mapLevel[nextPoint.y][nextPoint.x] == 0) {
			mapLevel[nextPoint.y][nextPoint.x] = count;
			dfs(nextPoint);
		}
		else if(mapLevel[nextPoint.y][nextPoint.x] == count) {	// DFS �����ؼ� ���� �ִ� ������ �湮�� ��� ������ ����Ŭ ������
			result++;
		}
	}
	
	// �ش� ������ ������ ���� ���� ������ ����Ʈ ��ü ���� 
	static Point15559 getMovePoint(int x, int y) {
		char direction = map[y][x];
		switch(direction) {
		case 'N':
			return new Point15559(x, y - 1);
		case 'E':
			return new Point15559(x + 1, y);
		case 'S':
			return new Point15559(x, y + 1);
		case 'W':
			return new Point15559(x - 1, y);
		}
		
		return null;
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
