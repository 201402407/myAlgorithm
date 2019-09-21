package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ���� ����
// DFS ��� ���
// StringBuilder �� ��� -> �ð� ����
// int �迭���� boolean �迭 ��� -> �ð� ����
public class p4963 {
//	static List<Integer> result = new ArrayList<Integer>();
	// �ȹ����� �̵��ϱ� ���� �� �迭
	static int[] changeX = {0, 0, 1, -1, 1, 1, -1, -1};	
	static int[] changeY = {1, -1, 0, 0, 1, -1, -1, 1};
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		try {
			while(true) {
				st = new StringTokenizer(br.readLine());
				int w = Integer.valueOf(st.nextToken());
				int h = Integer.valueOf(st.nextToken());
				if(w == 0 && h == 0)
					break;
				
				int[][] map = new int[h + 2][w + 2];	// ��¥ ���� �׵θ��� 0���� ä���� NullPointerException �����ϱ�. ������ 0�̸� �ѱ��Ǵ�
				// �� ����
				for(int i = 1; i <= h; i++) {
					st = new StringTokenizer(br.readLine());
					for(int j = 1; j <= w; j++) {
						map[i][j] = Integer.valueOf(st.nextToken());
					}
				}
				// �� ã��
				int count = 0;
				for(int i = 1; i <= h; i++) {
					for(int j = 1; j <= w; j++) {
						if(map[i][j] == 0) {
							continue;
						}
						else {
							dfs(map, i, j);
							count++;
						}
					}
				}
				sb.append(count).append("\n");
//				result.add(count);
			}
			System.out.print(sb);
//			result.stream().forEach(element -> System.out.println(element));
		} catch (IOException e) {
			System.exit(0);
		}
	}
	
	// DFS ��� ����غ��ô�
	private static void dfs(int[][] map, int y, int x) {
		map[y][x] = 0;
		for(int i = 0; i < changeX.length; i++) {	// �ȹ����� ���� �����
			int nextY = y + changeY[i];
			int nextX = x + changeX[i];
			if(map[nextY][nextX] == 0)	// �Լ� ȣ�� ����
				continue;
			dfs(map, nextY, nextX);
		}
	}
}
