package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// �κ� û�ұ�
// �ùķ��̼� ����
public class p14503 {
	static int[] moveX = { 0, 1, 0, -1 };
	static int[] moveY = { -1, 0, 1, 0 };
	static int[][] map;	// 0 : ��ĭ.  1 : ��.  2 : û�� �Ϸ�
	static int n, m;
	static int direction;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		
		map = new int[n][m];
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.valueOf(st.nextToken());
		int c = Integer.valueOf(st.nextToken());
		direction = Integer.valueOf(st.nextToken());
		
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());
			}
		}
		
		int result = clean(r, c);
		System.out.println(result);
	}
	
	static int clean(int y, int x) {
		int count = 0;

		while(true) {
			map[y][x] = 2;	// û�� �Ϸ�
			count++;
			
			for(int i = 0; i < moveX.length; i++) {	// ���ʹ������ ���ʴ�� Ž��
				direction = direction == 0 ? moveX.length - 1 : direction - 1;
				int nextX = x + moveX[direction];
				int nextY = y + moveY[direction];
				
				if((x < 0 || x >= m) || (y < 0 || y >= n)) {	// ���ʿ� ����� ������ ���� ����.
					return -1;
				}
				
				if(map[nextY][nextX] == 0) { // �� �Ǵ� û�� �Ϸ����
					x = nextX;
					y = nextY;
					break;
				}
				
				if(i == moveX.length - 1) {	// �� ���� �� ����
					// �Ĺ� �ľ��ϱ�
					int backDirection = direction <= 1 ? moveX.length - Math.abs(direction - 2) : direction - 2;
					int backX = x + moveX[backDirection];
					int backY = y + moveY[backDirection];
					
					// �Ĺ��� ���� ���
					if(map[backY][backX] == 1) {
						return count;
					}
					
					// �ʱ�ȭ(�ڷ� �̵� �� ��Ž��)
					x = backX;
					y = backY;
					i = -1;
				}
			}
		}
	}
}
