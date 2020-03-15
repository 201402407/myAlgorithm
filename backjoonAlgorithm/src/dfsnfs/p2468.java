package dfsnfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 안전 영역
// DFS 사용
public class p2468 {
	static int[][] map;
	static boolean[][] checked;
	static int n;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.valueOf(st.nextToken());
		
		int[][] visited = new int[n][n];	// 0: 아직 멀쩡. 1: checked  2: 물에 잠김
		map = new int[n][n];
		
		int max = 0;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				int height = Integer.valueOf(st.nextToken());
				map[i][j] = height;
				max = Math.max(max, height);
			}
		}
		
		int maxCount = 0;
		
		for(int i = 1; i <= max; i++) {
			int iCount = 0;
			checked = new boolean[n][n];
			
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < n; k++) {
					if(!checked[j][k] && map[j][k] > i) {
//						System.out.println("asd " + j + ", " + k);
						dfs(j, k, i, checked);
						iCount++;
					}
				}
			}
			
//			System.out.println(iCount + ", " + i);
			maxCount = Math.max(iCount, maxCount);
		}
		
		System.out.println(maxCount);
	}
	
	public static void dfs(int y, int x, int height, boolean[][] checked) {
		if((y < 0 || y >= n) || (x < 0 || x >= n)) {
			return;
		}
		
		if(map[y][x] <= height || checked[y][x]) {
			return;
		}
		
		checked[y][x] = true;
		
		dfs(y - 1, x, height, checked);
		dfs(y + 1, x, height, checked);
		dfs(y, x + 1, height, checked);
		dfs(y, x - 1, height, checked);
	}
}
