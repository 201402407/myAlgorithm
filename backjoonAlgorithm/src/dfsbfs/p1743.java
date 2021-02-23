package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 음식물 피하기
// DFS
public class p1743 {
	static char[][] map;
	static boolean[][] visited;
	static int[] moveX = {0, 1, 0, -1};
	static int[] moveY = {-1, 0, 1, 0};
	static int n, m;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		
		// index 1부터 시작.
		map = new char[n + 1][m + 1];	// 세로 * 가로
		visited = new boolean[n + 1][m + 1];
		
		// 맵그리기 
		for(int i = 1; i <= n; i++) {
			Arrays.fill(map[i], '.');
		}
		
		while(k --> 0) {
			st = new StringTokenizer(br.readLine());
			int garbageN = Integer.valueOf(st.nextToken());
			int garbageM = Integer.valueOf(st.nextToken());
			map[garbageN][garbageM] = '#';
		}
		
//		for(int i = 1; i <= n; i++) {
//			for(int j = 1; j <= m; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		// DFS 시작
		int result = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				if(map[i][j] == '#') {
					map[i][j] = '.';
					result = Math.max(result, dfs(i, j));
				}
			}
		}
		
		System.out.println(result);
	}
	
	static int dfs(int y, int x) {
		int cnt = 1;
		
		for(int i = 0; i < moveX.length; i++) {
			int nextX = x + moveX[i];
			int nextY = y + moveY[i];
			
			if(nextX <= 0 || nextX > m || nextY <= 0 || nextY > n) {
				continue;
			}
			
			if(map[nextY][nextX] == '#') {
				map[nextY][nextX] = '.';
				cnt += dfs(nextY, nextX);
			}
		}
		
		return cnt;
	}
}
