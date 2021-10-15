package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 무기공학
// 백트래킹
public class p18430 {
	static int n, m;
	// 상 우 하 좌
	static int[] moveX = {0, 1, 0, -1};
	static int[] moveY = {-1, 0, 1, 0};
	static int[][] intensityMap;
	static int maxSumIntensity;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());	// 세로
		m = Integer.valueOf(st.nextToken());	// 가로
		intensityMap = new int[n][m];
		
		// 맵 그리기
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				intensityMap[i][j] = Integer.valueOf(st.nextToken());
			}
		}
		
		backTracking(0, 0, new boolean[n][m]);
		System.out.println(maxSumIntensity);
	}
	
	static void backTracking(int index, int intensity, boolean[][] visited) {
		if(index >= n * m) {	// 모든 나무재료 탐색 완료
			maxSumIntensity = Math.max(maxSumIntensity, intensity);
			return;
		}
		
		int x = index % m;
		int y = index / m;
		
		// 아무 부메랑도 안만드는 경우
		backTracking(index + 1, intensity, visited);
		
		if(visited[y][x]) {
			return;
		}
		
//		┘ └ ┌ ┐
		// 상
		int nextX0 = x + moveX[0];
		int nextY0 = y + moveY[0];
		// 우
		int nextX1 = x + moveX[1];
		int nextY1 = y + moveY[1];
		// 하
		int nextX2 = x + moveX[2];
		int nextY2 = y + moveY[2];
		// 좌
		int nextX3 = x + moveX[3];
		int nextY3 = y + moveY[3];
		
		// ┘ 부메랑 만들기
		if(!notPossibleMakeBoomerang(nextX0, nextY0, visited) && !notPossibleMakeBoomerang(nextX3, nextY3, visited)) {
			int nextIntensity = intensity + (2 * intensityMap[y][x]) + intensityMap[nextY0][nextX0] + intensityMap[nextY3][nextX3];
			visited[y][x] = true;
			visited[nextY0][nextX0] = true;
			visited[nextY3][nextX3] = true;
			backTracking(index + 1, nextIntensity, visited);
			visited[y][x] = false;
			visited[nextY0][nextX0] = false;
			visited[nextY3][nextX3] = false;
		}
		
		// └ 부메랑 만들기
		if(!notPossibleMakeBoomerang(nextX0, nextY0, visited) && !notPossibleMakeBoomerang(nextX1, nextY1, visited)) {
			int nextIntensity = intensity + (2 * intensityMap[y][x]) + intensityMap[nextY0][nextX0] + intensityMap[nextY1][nextX1];
			visited[y][x] = true;
			visited[nextY0][nextX0] = true;
			visited[nextY1][nextX1] = true;
			backTracking(index + 1, nextIntensity, visited);
			visited[y][x] = false;
			visited[nextY0][nextX0] = false;
			visited[nextY1][nextX1] = false;
		}
		
		// ┌ 부메랑 만들기
		if(!notPossibleMakeBoomerang(nextX2, nextY2, visited) && !notPossibleMakeBoomerang(nextX1, nextY1, visited)) {
			int nextIntensity = intensity + (2 * intensityMap[y][x]) + intensityMap[nextY2][nextX2] + intensityMap[nextY1][nextX1];
			visited[y][x] = true;
			visited[nextY2][nextX2] = true;
			visited[nextY1][nextX1] = true;
			backTracking(index + 1, nextIntensity, visited);
			visited[y][x] = false;
			visited[nextY2][nextX2] = false;
			visited[nextY1][nextX1] = false;
		}
		
		// ┐ 부메랑 만들기
		if(!notPossibleMakeBoomerang(nextX2, nextY2, visited) && !notPossibleMakeBoomerang(nextX3, nextY3, visited)) {
			int nextIntensity = intensity + (2 * intensityMap[y][x]) + intensityMap[nextY2][nextX2] + intensityMap[nextY3][nextX3];
			visited[y][x] = true;
			visited[nextY2][nextX2] = true;
			visited[nextY3][nextX3] = true;
			backTracking(index + 1, nextIntensity, visited);
			visited[y][x] = false;
			visited[nextY2][nextX2] = false;
			visited[nextY3][nextX3] = false;
		}
	}
	
	
	// true: 부메랑 못만듬
	static boolean notPossibleMakeBoomerang(int x, int y, boolean[][] visited) {
		if(x < 0 || x >= m || y < 0 || y >= n) {
			return true;
		}
		
		return visited[y][x];
	}
}
