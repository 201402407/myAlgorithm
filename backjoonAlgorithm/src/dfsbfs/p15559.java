package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 내 선물을 받아줘
// DFS 
public class p15559 {
	static int n, m;	// n : 세로 // m : 가로 
	static char[][] map;
	static int[][] mapLevel;	// 같은 마을끼리 같은 레벨 
	static int count, result;
	static Point15559 startPoint;
	// 상-좌-하-우 
	static int[] moveX = {0, 1, 0, -1};
	static int[] moveY = {-1, 0, 1, 0};
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		map = new char[n][m];
		mapLevel = new int[n][m];
		
		// 입력받기 
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(mapLevel[i][j] == 0) {	// 방문 지점이 아닌 경우
					count++;
					mapLevel[i][j] = count;
					dfs(new Point15559(j, i));
				}
			}
		}
		
		System.out.println(result);
		
	}
	
	// DFS 알고리즘 
	static void dfs(Point15559 nowPoint) {
		int direction = getMovePoint(nowPoint.x, nowPoint.y);
		Point15559 nextPoint = new Point15559(nowPoint.x + moveX[direction], nowPoint.y + moveY[direction]);
		if(mapLevel[nextPoint.y][nextPoint.x] == 0) {
			mapLevel[nextPoint.y][nextPoint.x] = count;
			dfs(nextPoint);
		}
		else if(mapLevel[nextPoint.y][nextPoint.x] == count) {	// DFS 시작해서 돌고 있던 지점을 방문한 경우 무조건 사이클 생성됨
			result++;
		}
	}

	// 해당 지점의 방향을 보고 다음 지점의 포인트 객체 리턴 
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
