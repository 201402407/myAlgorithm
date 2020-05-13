package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 연구소 문제
// 시뮬레이션 + DFS
public class p14502 {
	static int n, m, maxCount;
	static int[][] map, spreadMap;
	static ArrayList<Virus> viruses;
	static int[] moveX = { 0, 1, 0, -1 };
	static int[] moveY = { -1, 0, 1, 0 };
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력 값 받기
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		
		map = new int[n][m];
		spreadMap = new int[n][m];
		int[][] tempMap = new int[n][m];
		viruses = new ArrayList<Virus>();
		
		// 맵(구역) 그리기
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				int place = Integer.valueOf(st.nextToken());
				map[i][j] = place;
				tempMap[i][j] = place;
				if(place == 2) {
					viruses.add(new Virus(j, i));
				}
			}
		}
		
		// 백트래킹
//		backTracking(0, 0, 0);
		backTracking(0, 0);
		System.out.println(maxCount);
	}
	
	/* 벽 3개 세우기 */
	static void backTracking(int wallCount, int index) {
		if(wallCount == 3) {
			arrayCopy(map, spreadMap); // 배열 복사
			
			// 바이러스 퍼뜨리기
			for(Virus virus : viruses) {
				spreadVirus(virus.x, virus.y);
			}
			
			// 안전영역 크기 구하기
			maxCount = Math.max(maxCount, getSafetyCount());
			return;
		}
		
		for(int i = index; i < n * m; i++) {
			int y = i / m;
            int x = i % m;

            if(map[y][x] == 0) { // 빈 공터이면
				// 벽 세우기
				map[y][x] = 1;
				backTracking(wallCount + 1, i + 1);	
				// 세우지 않기
				map[y][x] = 0;
			}
		}
	}
	
	/* 배열 COPY */
	static void arrayCopy(int[][] from, int[][] to) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				to[i][j] = from[i][j];
			}
		}
	}
	
	/* 바이러스 퍼뜨리기(DFS) */
	static void spreadVirus(int x, int y) {
		for(int i = 0; i < moveX.length; i++) {
			int nextX = x + moveX[i];
			int nextY = y + moveY[i];
			
			// 맵 벗어나면 패스
			if((nextX < 0 || nextX >= m) || (nextY < 0 || nextY >= n)) {
				continue;
			}
			
			if(spreadMap[nextY][nextX] == 0) {
				spreadMap[nextY][nextX] = 2;
				spreadVirus(nextX, nextY);
			}
		}
	}
	
	/* 안전 구역 세기 */
	static int getSafetyCount() {
		int count = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(spreadMap[i][j] == 0) {
					count++;
				}
			}
		}
		return count;
	}
}

class Virus {
	int x;
	int y;
	Virus(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
