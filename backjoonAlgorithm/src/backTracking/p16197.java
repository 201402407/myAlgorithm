package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 두 동전
// 백트래킹 알고리즘 	
public class p16197 {
	static char[][] map;
	static int count = Integer.MAX_VALUE;
	static int n, m;
	static boolean isAble = false;
	static int[] moveX = {-1, 1, 0, 0};
	static int[] moveY = {0, 0, -1, 1};
	static int x1, x2, y1, y2;
	static boolean getFirst;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		map = new char[n][m];
		int coinNum = 0;
		
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			for(int j = 0; j < m; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'o') {	// 동전 위치 별도로담기 
					if(getFirst) {
						y2 = i;
						x2 = j;
					}
					else {
						y1 = i;
						x1 = j;
						getFirst = true;
					}
				}
			}
		}
		
		backTracking(new Coin16197(y1, x1, y2, x2), 0);
		
		if(count == Integer.MAX_VALUE) {	// 절대 떨어뜨리지 못하는 경우
			System.out.println(-1);
		}
		else {
			System.out.println(count);
		}
	}
	
	static public void backTracking(Coin16197 coins, int btnCount) {
		if(btnCount >= count || btnCount >= 10) {
			return;
		}
		
		for(int i = 0; i < moveX.length; i++) {
			int outSideCnt = 0;
			int nextX1 = coins.x1 + moveX[i];
			int nextY1 = coins.y1 + moveY[i];
			int nextX2 = coins.x2 + moveX[i];
			int nextY2 = coins.y2 + moveY[i];
			
			if(nextX1 < 0 || nextX1 >= m || nextY1 < 0 || nextY1 >= n) {
				outSideCnt++;
			}
			if(nextX2 < 0 || nextX2 >= m || nextY2 < 0 || nextY2 >= n) {
				outSideCnt++;
			}
			
			// 두 동전 중 하나만 빠져나가는지 체크			
			if(outSideCnt == 2) {	// 두 동전 둘 다 빠져나가면 안되므로 pass
				continue;
			}
			if(outSideCnt == 1) {
				count = Math.min(count, btnCount + 1);
				return;
			}
						
			if(map[nextY1][nextX1] == '#') { // 벽이면 
				nextX1 = coins.x1;
				nextY1 = coins.y1;
			}
			if(map[nextY2][nextX2] == '#') { // 벽이면 
				nextX2 = coins.x2;
				nextY2 = coins.y2;
			}
			
			// 두 동전이 이전과 같은 위치이면 걍 종료(이동 불가능 판단)
//			int menu = 0;
//			for(int j = 0; j < coins.length; j++) {
//				if(coins[j].x == newCoins[j].x && coins[j].y == newCoins[j].y) {
//					menu++;
//				}
//			}
			backTracking(new Coin16197(nextY1, nextX1, nextY2, nextX2), btnCount + 1);
//			if(menu != 2) {
//				backTracking(newCoins, btnCount + 1);
//			}
		}
	}
}

class Coin16197 {
	int y1;
	int x1;
	int y2;
	int x2;
	
	Coin16197(int y1, int x1, int y2, int x2) {
		this.y1 = y1;
		this.x1 = x1;
		this.y2 = y2;
		this.x2 = x2;
	}
}