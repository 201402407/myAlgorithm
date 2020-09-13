package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// �÷��̵� �˰��� ���
// �÷��̵� ����
// ��� ������ ��������� �Ͽ� ��� ������ �������� ���� ���� �ִܰ�θ� ���ϴ� �˰���
public class p11404 {
	static int[][] map;
	static final int MAX_VALUE = 10000001;
	
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int m = Integer.valueOf(st.nextToken());
			map = new int[n][n];	// ���� ��� �ε������� -1 ���ֱ�

			for(int i = 0; i < n; i++) {
				Arrays.fill(map[i], MAX_VALUE);	
				map[i][i] = 0;
			}
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.valueOf(st.nextToken()) - 1;	// 0���� ����
				int end = Integer.valueOf(st.nextToken()) - 1;	// 0���� ����
				int weight = Integer.valueOf(st.nextToken());
				if(map[start][end] > weight) {
					map[start][end] = weight;	
				}
			}
			
			floyd(n);
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(map[i][j] == MAX_VALUE) {
						map[i][j] = 0;
					}
					sb.append(map[i][j]).append(" ");
				}
				sb.append("\n");
			}
			
			System.out.println(sb.toString());
		}
		catch (NumberFormatException e) {
			System.exit(0);
		}
		catch (IOException e) {
			System.exit(0);
		}
	}
	
	// �÷��̵� �˰���
	public static void floyd(int n) {
		for(int k = 0; k < n; k++) { // ��� ���� 
			for(int i = 0; i < n; i++) { // ���� ����
				if(k == i) {
					continue;
				}
				for(int j = 0; j < n; j++) { // ���� ���� 
					if(i != j && i != j) {
						if(map[i][j] > map[i][k] + map[k][j]) {
							map[i][j] = map[i][k] + map[k][j];
						}
					}
				}
			}
		}
	}
}