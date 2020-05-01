package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 드래곤 커브
// 시뮬레이션(근데 DP 사용?) 문제
public class p15685 {
	// 위 - 오 - 아래 - 왼 순서
	static int[] moveX = { 0, 1, 0, -1 };
	static int[] moveY = { -1, 0, 1, 0 };
	static boolean[][] map = new boolean[101][101]; // True : 커브 그린 지점
	static boolean[][] squared = new boolean[100][100];
	
	
	static LinkedList<Integer>[] dragonCurves;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		dragonCurves = new LinkedList[11];
		
		// DP를 통해 K번 째 드래곤 커브 방향 List 계산
		// 임시로 시작 방향이 0인 경우를 계산해서 담음
		dragonCurves[0] = new LinkedList<Integer>();
		dragonCurves[0].offer(0);
		for(int i = 1; i < 11; i++) {
			dragonCurves[i] = new LinkedList<Integer>();
			
			// 앞에는 똑같이 담고
			// 뒤에는 90도 회전시킨 것을 반대로 담기
			for(int index = dragonCurves[i - 1].size() - 1; index >= 0; index--) {
				int nowDirection = dragonCurves[i - 1].get(index);
				dragonCurves[i].offerFirst(nowDirection);
				
				if(nowDirection <= 0) {
					nowDirection = 4;
				}
				dragonCurves[i].offerLast((nowDirection - 1) % 4);
			}
			
//			for(int k = 0; k < dragonCurves[i].size(); k++) {
//				System.out.print(dragonCurves[i].get(k) + " ");
//			}
//			System.out.println();
		}
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken());
			int y = Integer.valueOf(st.nextToken());
			int direction = getSavedDragonCurvesDirection(Integer.valueOf(st.nextToken()));
			int g = Integer.valueOf(st.nextToken());
			
			// 시작 지점 그리기
			map[y][x] = true;
			
			// 드래곤 커브 맵에 그리기
			for(int j = 0; j < dragonCurves[g].size(); j++) {
				int nowDirection = dragonCurves[g].get(j);
				x += moveX[(nowDirection + direction) % 4];
				y += moveY[(nowDirection + direction) % 4];
				map[y][x] = true;
			}
		}
		
//		for(int i = 0; i < 101; i++) {
//			for(int j = 0; j < 101; j++) {
//				if(map[i][j]) {
//					System.out.print(1 + " ");	
//				}
//				else {
//					System.out.print(0 + " ");
//				}
//				
//			}
//			System.out.println();
//		}
		
		int count = 0;
		// 네모를 그릴 수 있는지 판단하기
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(map[i][j]) {
					if(checkSquared(j, i)) {
						count++;
					}	
				}
				
			}
		}
		
		System.out.println(count);
	}
	
	/*
	 * 0: x좌표가 증가하는 방향 (→)
	 * 1: y좌표가 감소하는 방향 (↑)
	 * 2: x좌표가 감소하는 방향 (←)
	 * 3: y좌표가 증가하는 방향 (↓)
	 * 이 입력 값인데, 이 순서를 ↑, →, ↓, ← 로 변환시킨다.
	 */
	static int getSavedDragonCurvesDirection(int inputDirection) {
		switch (inputDirection) {
		case 0:
			return 1;
		case 1:
			return 0;
		case 2:
			return 3;
		case 3:
			return 2;
		default:
			return -1;
		}
	}
	
	static boolean checkSquared(int x, int y) {
		// 현재 점 -> 오른쪽 -> 아래 -> 왼쪽 순서로 진행하며 사각형을 그릴 수 있는지 각 점을 판단
		for(int i = 1; i < 4; i++) {	
			x += moveX[i];
			y += moveY[i];
			if(!map[y][x]) {
				return false;
			}
		}
		return true;
	}
}
