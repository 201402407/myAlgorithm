package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DFS + DP 
// 게임 문제 
public class p1103 {
	static int n, m, max; // n : 세로, m : 가로 
	static boolean isCycle = false;
	static int[][] dp; 
	static char[][] map;
	static boolean[][] visited;
	static int[] moveX = {0, 1, 0, -1};
	static int[] moveY = {-1, 0, 1, 0};
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		dp = new int[n][m];
		map = new char[n][m];
		visited = new boolean[n][m];
		
		// 입력받은 값 맵에 넣기 
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
		}
		
		visited[0][0] = true;
		dfs(0, 0, 1);
		if(isCycle) {
			System.out.println("-1");
		}
		else {
			System.out.println(max);
		}
	}
	
	/**
	 * DFS 알고리즘을 통해 최대 동전 게임 횟수 구하기
	 */
	static void dfs(int x, int y, int moveCount) {
		int moveSquareCount = Character.getNumericValue(map[y][x]);	// X만큼 이동해야할 때, 그 X를 가리킴
		dp[y][x] = moveCount;
		if(moveCount > max) {
			System.out.println(moveCount + " .. " + y + " , " + x);
			max = moveCount;
		}
		
		for(int i = 0; i < moveX.length; i++) {
			int nextX = x + (moveSquareCount * moveX[i]);
			int nextY = y + (moveSquareCount * moveY[i]);
			
			// 맵 밖을 벗어나면 게임 종료
			if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) {
				continue;
			}	
			
			// 구멍에 빠지면 게임 종료
			if(map[nextY][nextX] == 'H') {	
				continue;
			}
			
			// 이미 다음 지점까지 가기 위해 게임한 횟수가 현재 지점에서 한번 더 한 것보다 크면 어차피 작으므로 할 이유 없다.
			if(moveCount < dp[nextY][nextX]) { 
				continue;
			}
			
			// 사이클 발견한 경우 조기종료
			if(visited[nextY][nextX]) {
				isCycle = true;
				return;
			}

			visited[nextY][nextX] = true;
			dfs(nextX, nextY, moveCount + 1);
			visited[nextY][nextX] = false;
			
		}
	}
}
