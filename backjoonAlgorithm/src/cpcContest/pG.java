package cpcContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//2019.09.28
//중앙대학교 프로그래밍 경진대회(CPC) 오픈 컨테스트
public class pG {
	static int[][] map;
	static int[][] visited;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		map = new int[n + 1][m + 2];
		visited = new int[n + 1][m + 2];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= m; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());
			}
		}
		
		int result = checked();
		System.out.println(result);
	}
	
	private static int checked() {
		Queue<Direction> queue = new LinkedList<Direction>();
		queue.add(new Direction(map[0][1], 1, 0, -1));
		queue.add(new Direction(map[0][2], 2, 0, -1));
		queue.add(new Direction(map[0][3], 3, 0, -1));
		queue.add(new Direction(map[0][4], 4, 0, -1));
		
		int result = Integer.MAX_VALUE;
		while(!queue.isEmpty()) {
			Direction nowDirection = queue.poll();
			if(nowDirection.getY() == map.length - 2) {
				System.out.println(nowDirection.getNowFuel());
				result = Math.min(result, nowDirection.getNowFuel());
				continue;
			}
			
			if(nowDirection.getState() != 0) {	// 왼쪽 대각선
				int nextX = nowDirection.getX() - 1;
				int nextY = nowDirection.getY() + 1;
				if(map[nextY][nextX] == 0) {
					continue;
				}
				int nextFuel = nowDirection.getNowFuel() + map[nextY][nextX];
				Direction nextDirection = new Direction(nextFuel, nextX, nextY, 0);
				queue.add(nextDirection);
			}
			
			if(nowDirection.getState() != 1) {	// 가운데
				int nextX = nowDirection.getX();
				int nextY = nowDirection.getY() + 1;
				if(map[nextY][nextX] == 0) {
					continue;
				}
				int nextFuel = nowDirection.getNowFuel() + map[nextY][nextX];
				Direction nextDirection = new Direction(nextFuel, nextX, nextY, 0);
				queue.add(nextDirection);
			}
			
			if(nowDirection.getState() != 2) {	// 오른쪽 대각선
				int nextX = nowDirection.getX() + 1;
				int nextY = nowDirection.getY() + 1;
				if(map[nextY][nextX] == 0) {
					continue;
				}
				int nextFuel = nowDirection.getNowFuel() + map[nextY][nextX];
				Direction nextDirection = new Direction(nextFuel, nextX, nextY, 0);
				queue.add(nextDirection);
			}
		}
		return result;
	}
}

class Direction {
	int nowFuel;
	int state;	// 0 : 왼쪽 1: 가운데 2: 오른쪽
	int x;
	int y;
	Direction(int nowFuel, int x, int y, int state) {
		this.nowFuel = nowFuel;
		this.x = x;
		this.y = y;
		this.state = state;
	}

	public int getNowFuel() {
		return nowFuel;
	}

	public void setNowFuel(int nowFuel) {
		this.nowFuel = nowFuel;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
