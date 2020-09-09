package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 사탕 게임
// 완전탐색 
public class p3085 {
	static char[][] bomboni; 
	static int[] moveX = {0, 1, 0, -1};	// 상, 우, 하, 좌  
	static int[] moveY = {-1, 0, 1, 0};	// 상, 우, 하, 좌  
	static int n;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());
		bomboni = new char[n][n];
		
		// 사탕 색깔 입력받기 
		for(int i = 0; i < n; i++) {
			bomboni[i] = br.readLine().toCharArray();
		}
		
		int max = 0;
		// 1. 2중 for문 수행
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				// 바뀌기 전에도 한 번 체크 해주기 
				max = Math.max(max, Math.max(checkSameColor(j, i), binarySearch(j, i)));
			}
		}
		
		System.out.println(max);
	}
	
	static int binarySearch(int x, int y) {
		int max = 0;
		int nextX, nextY;
		char color = bomboni[y][x];
		
		// 우, 하 인접 색상 비교 -> 교환 -> 연속 색상 갯수 탐색 
		for(int i = 1; i <= 2; i++) {
			nextX = x + moveX[i];
			nextY = y + moveY[i];
			if((nextX >= 0 && nextX < n) && (nextY >= 0 && nextY < n)) {	// 맵의 범위 내에 들어와야 함. 
				if(color != bomboni[nextY][nextX]) {	// 인접한 색깔이 달라야 함.
//					System.out.println("asg");
					// SWAP
					swap(x, y, nextX, nextY); // 교환 
					max = Math.max(max, Math.max(checkSameColor(x, y), checkSameColor(nextX, nextY)));
					swap(x, y, nextX, nextY); // 교환 원상태로 복귀 
				}
				
				if(x == n - 1 && y == n - 1) {
					System.out.println(max);
				}
			}
		}

		
		return max;
	}
	
	static void swap(int x, int y, int nextX, int nextY) {
		char temp = bomboni[y][x];
		bomboni[y][x] = bomboni[nextY][nextX];
		bomboni[nextY][nextX] = temp;
	}
	
	// 행과 열 연속으로 같은 색깔 개수 파악하기 
	static int checkSameColor(int x, int y) {
		int max = 0;
		char nowColor = bomboni[y][x];
		int[] result = new int[moveX.length];
		
		for(int i = 0; i < moveX.length; i++) {
			int count = 0;
			int nextX = x + moveX[i];
			int nextY = y + moveY[i];
			
			if((nextX < 0 || nextX >= n) || (nextY < 0 || nextY >= n)) {	// 맵의 범위 내에 들어와야 함. 
				continue;
			}
			
			while(nowColor == bomboni[nextY][nextX]) {
//				System.out.println("assg");
				count++;
				nextX += moveX[i];
				nextY += moveY[i];
				
				if((nextX < 0 || nextX >= n) || (nextY < 0 || nextY >= n)) {	// 맵의 범위 내에 들어와야 함. 
					break;
				}
			}
			
			result[i] = count;
//			max = Math.max(max, count);
		}
		
		// 열(상 + 하) 와 행(좌 + 우) 개수 비교 
		return Math.max(result[0] + result[2] + 1, result[1] + result[3] + 1);
	}
}
