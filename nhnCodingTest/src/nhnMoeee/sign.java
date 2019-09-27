package nhnMoeee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sign {
	static String[][] sign;
	static boolean changedSign[][];
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		try {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			if(n <= 1 || n > 100) {
				System.exit(0);
			}
			
			int w = Integer.valueOf(st.nextToken());
			if(w < -1000000000 || w > 1000000000) {
				System.exit(0);
			}
			
			sign = new String[n][n];	// 실제 인덱스는 n-1(즉, 문제에서는 1부터 시작이지만 여기서는 0부터 시작)
			changedSign = new boolean[n][n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					sign[i][j] = st.nextToken();
				}
			}
			
			rotate(sign, w);
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
						System.out.print(sign[i][j] + " ");
				}
				System.out.println();
			}
				
		} catch (IOException e) {
			System.exit(0);
		}
		catch (NumberFormatException e) {
			System.exit(0);
		}
		
	}
	
	// [y][x]
	private static void rotate(String[][] sign, int rotate) {
		/*
		 * A ㅡㅡㅡㅡㅡ B
		 * l		 l
		 * l		 l
		 * l		 l
		 * l		 l
		 * C ㅡㅡㅡㅡㅡ D
		 */
		int signSize = sign.length;
		Point startA = new Point(0, 0);	
		Point startB = new Point(signSize - 1, 0);
		Point startC = new Point(0, signSize - 1);
		Point startD = new Point(signSize - 1, signSize - 1);
		Point[] points = {startA, startB, startC, startD};
		boolean isClockwise = rotate > 0 ? true : false;
		
		while(startA.getX() < startB.getX()) {
			int rotateSignLength = startB.getX() - startA.getX();
			int direction = 4 * rotateSignLength;
			int realRotationStep = Math.abs(rotate) % direction; // 4 * (행렬 길이 - 1) 한 것이 한바퀴 로테이트 한 경우 
			String[] tempStr = new String[direction];
			tempStr[0] = sign[startA.getY()][startA.getX()].toString();
			
			// 이동 시작
			for(int i = 0; i < 4 * rotateSignLength; i++) {
				int nowDirection = i / rotateSignLength;
				int nowMoveStep = i % rotateSignLength;
				
				int nextRotationStep = ((realRotationStep + i) % (4 * rotateSignLength)); // 시작점 기준 해당하는 점에서 이동 해야하는 총 거리
				int nextDirection = nextRotationStep / rotateSignLength;
				int nextMoveStep = nextRotationStep % rotateSignLength;
				// 현재 위치와 바뀐 후 위치 포인트 객체 생성
				Point nowPoint = getRotatedPoint(points, nowDirection, nowMoveStep, isClockwise);
				Point nextPoint = getRotatedPoint(points, nextDirection, nextMoveStep, isClockwise);
				tempStr[nextRotationStep] = sign[nextPoint.getY()][nextPoint.getX()];

				if(tempStr[i] == null) {	// String null 체크
					tempStr[i] = sign[nowPoint.getY()][nowPoint.getX()];
				}
				sign[nextPoint.getY()][nextPoint.getX()] = tempStr[i];
			}
			startA = new Point(startA.getX() + 1, startA.getY() + 1);
			startB = new Point(startB.getX() - 1, startB.getY() + 1);
			startC = new Point(startC.getX() + 1, startC.getY() - 1);
			startD = new Point(startD.getX() - 1, startD.getY() - 1);
			points[0] = startA;
			points[1] = startB;
			points[2] = startC;
			points[3] = startD;
			isClockwise = isClockwise ? false : true;
		}
	}
	
	// points는 A, B, C, D 순서로 넣음
	// 시계 방향 : A - B - D - C
	// 시계 반대 방향 : A - C - D - B
	private static Point getRotatedPoint(Point[] points, int direction, int step, boolean isClockwise) {
		switch(direction) {
		case 0:
			if(isClockwise)
				return new Point(points[0].getX() + step, points[0].getY());
			else
				return new Point(points[0].getX(), points[0].getY() + step);
		case 1:
			if(isClockwise)
				return new Point(points[1].getX(), points[1].getY() + step);
			else
				return new Point(points[2].getX() + step, points[2].getY());
		case 2:
			if(isClockwise)
				return new Point(points[3].getX() - step, points[3].getY());
			else
				return new Point(points[3].getX(), points[3].getY() - step);
		case 3:
			if(isClockwise)
				return new Point(points[2].getX(), points[2].getY() - step);
			else
				return new Point(points[1].getX() - step, points[1].getY());
		}
		return null;
	}
}

class Point {
	private int x;
	private int y;
	Point() {
		
	}
	Point(int x, int y) {
		this.x = x;
		this.y = y;
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