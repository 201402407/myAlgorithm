package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 로봇 청소기
// 시뮬레이션 문제
public class p14503 {
	static int[] moveX = { 0, 1, 0, -1 };
	static int[] moveY = { -1, 0, 1, 0 };
	static int[][] map;	// 0 : 빈칸.  1 : 벽.  2 : 청소 완료
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
			map[y][x] = 2;	// 청소 완료
			count++;
			
			for(int i = 0; i < moveX.length; i++) {	// 왼쪽방향부터 차례대로 탐색
				direction = direction == 0 ? moveX.length - 1 : direction - 1;
				int nextX = x + moveX[direction];
				int nextY = y + moveY[direction];
				
				if((x < 0 || x >= m) || (y < 0 || y >= n)) {	// 애초에 벗어나는 조건이 따로 없음.
					return -1;
				}
				
				if(map[nextY][nextX] == 0) { // 벽 또는 청소 완료상태
					x = nextX;
					y = nextY;
					break;
				}
				
				if(i == moveX.length - 1) {	// 한 바퀴 돈 이후
					// 후방 파악하기
					int backDirection = direction <= 1 ? moveX.length - Math.abs(direction - 2) : direction - 2;
					int backX = x + moveX[backDirection];
					int backY = y + moveY[backDirection];
					
					// 후방이 벽인 경우
					if(map[backY][backX] == 1) {
						return count;
					}
					
					// 초기화(뒤로 이동 후 재탐색)
					x = backX;
					y = backY;
					i = -1;
				}
			}
		}
	}
}
