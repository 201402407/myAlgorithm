package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ����� ���� ����
// DFS
public class p1012 {
	static int[] moveX = {0, 0, -1, 1};	// ��, ��, ��, �� �̵�
	static int[] moveY = {-1, 1, 0, 0};	// ��, ��, ��, �� �̵�
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.valueOf(st.nextToken());
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < t; i++) {
				st = new StringTokenizer(br.readLine());
				int m = Integer.valueOf(st.nextToken());	// ���α���
				int n = Integer.valueOf(st.nextToken());	// ���α���
				int k = Integer.valueOf(st.nextToken());	// ������ ����
				int[][] map = new int[n][m];	// m x n �迭
				for(int j = 0; j < k; j++) {
					st = new StringTokenizer(br.readLine());
					int x = Integer.valueOf(st.nextToken());	// ����
					int y = Integer.valueOf(st.nextToken());	// ����
					map[y][x] = 1;
				}
				int count = 0;
				for(int a = 0; a < n; a++) {
					for(int b = 0; b < m; b++) {
						if(map[a][b] != 0) {
							dfs(map, a, b);
							count++;
						}
					}
				}
				sb.append(count).append("\n");
			}
			System.out.print(sb.toString());
		}
		catch (NumberFormatException e) {
			System.exit(0);
		}
		catch (IOException e) {
			System.exit(0);
		}
	}
	
	// continue ���ѹ����� ������ �Լ� ȣ�� �� ���̶� �� ���̷���
	// dfs�� ���� 0���� ����������.
	private static void dfs(int[][] map, int y, int x) {
//		if(map[y][x])
		map[y][x] = 0;
		for(int i = 0; i < moveX.length; i++) {
			int nextY = y + moveY[i];
			int nextX = x + moveX[i];
			// x�� y�� ���� ����� ���
			if((nextY >= map.length || nextY < 0) || (nextX >= map[0].length || nextX < 0)) {
				continue;
			}
			else {
				if(map[nextY][nextX] == 0) {
					continue;
				}
				else {
					dfs(map, nextY, nextX);
				}
			}
		}
	}
}
