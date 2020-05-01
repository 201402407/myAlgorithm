package anything;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 인구 이동 문제
// DFS ?
public class p16234 {
	static int[][] map;	// 각 구역 별 인구 수를 담은 맵
	static boolean[][] checked;	// 각 구역 방문 여부 판단하는 배열
	static int[][] unionMap; // 각 구역의 Level(연합)을 나타내는 맵
	static int[] newPeople;	// Level 별 평등하게 분배될 인구 수를 갖고 있는 배열
	static int dfsCount, n, l, r;
	static int[] moveX = { 0, 1, 0, -1 };
	static int[] moveY = { -1, 0, 1, 0 };
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.valueOf(st.nextToken());
		l = Integer.valueOf(st.nextToken());
		r = Integer.valueOf(st.nextToken());
		
		map = new int[n][n];
		checked = new boolean[n][n];
		unionMap = new int[n][n];
		newPeople = new int[n * n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(unionMap[i], -1); // 기본 값 초기화 : -1
			for(int j = 0; j < n; j++) {
				int people = Integer.valueOf(st.nextToken());
				map[i][j] = people;
			}
		}
		
		boolean isOpened = false;
		// while문을 만족하면 하나라도 국경선을 열 수 있으므로 시작은 -1로 해야 0부터 시작된다.
		int day = 0;	
		
		do {
			int unionIndex = 0;
			isOpened = false;
			
			for(int y = 0; y < n; y++) {
				for(int x = 0; x < n; x++) {
					if(unionMap[y][x] == -1) {
						dfsCount = 0;	// 초기화
						newPeople[unionIndex] = 0;	// 초기화
						open(x, y, unionIndex);
						
						// 연합이 하나라도 있으면
						if(dfsCount >= 2) {
							isOpened = true;
							
							// 평등화할 인구 구하기
							newPeople[unionIndex] = (int) Math.floor(newPeople[unionIndex] / dfsCount);
						}
						
						unionIndex++;
					}
				}
			}
			// 경계를 열은 적이 없을 때 까지 반복
			if(isOpened) {
				day++;
				setNewPeople();
			}
		}
		while(isOpened);
		
		System.out.println(day);
	}
	
	// DFS. 경계문을 여는 함수
	static void open(int x, int y, int unionIndex) {
		if(unionMap[y][x] != -1) {
			return;
		}
		
		dfsCount++;
		newPeople[unionIndex] += map[y][x];
		
		unionMap[y][x] = unionIndex;
		
		for(int i = 0; i < moveX.length; i++) {
			int nextX = x + moveX[i];
			int nextY = y + moveY[i];
			
			// 맵 범위 예외 처리
			if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) {
				continue;
			}
			
			// 아직 한 번도 방문하지 않았던 구역 찾기
			if(unionMap[nextY][nextX] == -1) {
				int gap = Math.abs(map[y][x] - map[nextY][nextX]);
				if(gap >= l && gap <= r) {
					open(nextX, nextY, unionIndex);	// DFS	
				}
			}
		}
	}
	
	// 연합에 따라 인구 이동 함수 및 unionMap 배열 초기화
	static void setNewPeople() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				map[i][j] = newPeople[unionMap[i][j]];
				unionMap[i][j] = -1;
			}
		}
	}
}

class Country {
	int x;
	int y;
	
	public Country(int x, int y) {
		this.x = x;
		this.y = y;
	}
}