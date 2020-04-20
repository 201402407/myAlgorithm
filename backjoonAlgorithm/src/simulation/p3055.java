package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 탈출 문제
// 시뮬레이션
public class p3055 {
	static char[][] map;
	static boolean[][] notMoved;	// T : 이동 불가능, F : 이동 가능.
	static Queue<Point3055> waters;
	
	static int[] moveX = { 0, 1, 0, -1 };
	static int[] moveY = { -1, 0, 1, 0 };
	static int x, y;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		y = Integer.valueOf(st.nextToken());
		x = Integer.valueOf(st.nextToken());
		
		map = new char[y][x];
		notMoved = new boolean[y][x];
		waters = new LinkedList<Point3055>();
		
		// 무조건 D와 S는 하나씩만 주어진다고 했으므로 null로 초기화해도 상관없다.
		Point3055 startPoint = null;
		Point3055 endPoint = null;
		
		for(int i = 0; i < y; i++) {
			st = new StringTokenizer(br.readLine());
			String line = st.nextToken();
			map[i] = line.toCharArray();
			for(int j = 0; j < x; j++) {
				switch(map[i][j]) {
				case 'D':
					endPoint = new Point3055(j, i);
					notMoved[i][j] = true;
					break;
				case 'S':
					startPoint = new Point3055(j, i);
					break;
				case '*':
					waters.add(new Point3055(j, i));
					notMoved[i][j] = true;
					break;
				case 'X':
					notMoved[i][j] = true;
					break;
				}
			}
		}
		
		int result = escape(startPoint, endPoint);
		System.out.println(result == -1 ? "KAKTUS" : result);
	}
	
	// 고슴도치 탈출 함수
	static int escape(Point3055 start, Point3055 end) {
		int time = 0;
		Queue<Point3055> goSumDoChi = new LinkedList<Point3055>();
		goSumDoChi.offer(start);
		
		while(true) {
			if(goSumDoChi.isEmpty()) {
				return -1;
			}
			// 물 셋팅
			setWaterFlow();
			
			// 고슴도치 이동시키기
			Queue<Point3055> nextGoSumDoChi = new LinkedList<Point3055>();
			while(!goSumDoChi.isEmpty()) {
				Point3055 goSum = goSumDoChi.poll();
				for(int i = 0; i < moveX.length; i++) {
					int nextX = goSum.x + moveX[i];
					int nextY = goSum.y + moveY[i];
					// 고슴도치가 이동해서 동굴에 도착할 수 있다면
					if(nextX == end.x && nextY == end.y) {
						return time + 1;
					}
					
					if(validPoint(nextX, nextY)) {
						notMoved[nextY][nextX] = true;
						nextGoSumDoChi.add(new Point3055(nextX, nextY));
					}	
				}	
			}
			goSumDoChi = nextGoSumDoChi;
			time++;
		}
	}
	
	// 물 상하좌우로 흐르는 것을 notMoved boolean 배열에 나타내는 함수
	static void setWaterFlow() {
		Queue<Point3055> nextWaters = new LinkedList<Point3055>();
		
		while(!waters.isEmpty()) {
			Point3055 water = waters.poll();
			for(int i = 0; i < moveX.length; i++) {
				int nextX = water.x + moveX[i];
				int nextY = water.y + moveY[i];
				if(validPoint(nextX, nextY)) {
					notMoved[nextY][nextX] = true;
					nextWaters.add(new Point3055(nextX, nextY));
				}
			}
		}
		waters = nextWaters;
	}
	
	// 해당 지점에 이동할 수 있는지(물이든 고슴도치이든) 체크하는 함수
	static boolean validPoint(int pointX, int pointY) {
		if(pointX < 0 || pointX >= x) {
			return false;
		}
		if(pointY < 0 || pointY >= y) {
			return false;
		}
		if(notMoved[pointY][pointX]) {
			return false;
		}
		return true;
	}
}

class Point3055 {
	int x;
	int y;
	
	Point3055() {}
	Point3055(int x, int y) {
		this.x = x;
		this.y = y;
	}
}