package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DFS + DP 
// ���� ���� 
public class p1103 {
	static int n, m, max; // n : ����, m : ���� 
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
		
		// �Է¹��� �� �ʿ� �ֱ� 
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
	 * DFS �˰����� ���� �ִ� ���� ���� Ƚ�� ���ϱ�
	 */
	static void dfs(int x, int y, int moveCount) {
		int moveSquareCount = Character.getNumericValue(map[y][x]);	// X��ŭ �̵��ؾ��� ��, �� X�� ����Ŵ
		dp[y][x] = moveCount;
		if(moveCount > max) {
			System.out.println(moveCount + " .. " + y + " , " + x);
			max = moveCount;
		}
		
		for(int i = 0; i < moveX.length; i++) {
			int nextX = x + (moveSquareCount * moveX[i]);
			int nextY = y + (moveSquareCount * moveY[i]);
			
			// �� ���� ����� ���� ����
			if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) {
				continue;
			}	
			
			// ���ۿ� ������ ���� ����
			if(map[nextY][nextX] == 'H') {	
				continue;
			}
			
			// �̹� ���� �������� ���� ���� ������ Ƚ���� ���� �������� �ѹ� �� �� �ͺ��� ũ�� ������ �����Ƿ� �� ���� ����.
			if(moveCount < dp[nextY][nextX]) { 
				continue;
			}
			
			// ����Ŭ �߰��� ��� ��������
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
