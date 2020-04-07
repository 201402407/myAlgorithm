package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 체스판 다시 칠하기 
// 시뮬레이션 
public class p1018 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		boolean[][] startB = new boolean[n][m]; // B로 시작할 때 다시 칠해야 하는 것
		boolean[][] startW = new boolean[n][m]; // W로 시작할 때 다시 칠해야 하는 것 
		char[][] map = new char[n][m];
		
		for(int i = 0; i < n; i++) {
			char[] line = br.readLine().toCharArray();
			map[i] = line;
		}
		
		// 다시 칠해야 할 부분 정리
		for(int y = 0; y < n; y++) {
			for(int x = 0; x < m; x++) {
				// 행 조건
				// 짝수 행 
				if(y % 2 == 0) { 
					if(map[y][x] == 'B') {
						if(x % 2 == 0) { // 홀수 열에서는 W로 시작할 때 B가 오면 다시 칠해야 한다.
							startW[y][x] = true;
						}
						else {	// 짝수 열에서는 B로 시작할 때 W가 오면 다시 칠해야 한다. 
							startB[y][x] = true;
						}
					}
					else {	// 'W'인 경우 
						if(x % 2 == 0) {	
							startB[y][x] = true;
						}
						else {	// 짝수 열에서는 W로 시작할 때 W가 오면 안된다.
							startW[y][x] = true;
						}
					}
				}
				// 홀수 행 
				else {
					if(map[y][x] == 'B') {
						if(x % 2 == 0) { // 홀수 열에서는 B로 시작할 때 B가 오면 다시 칠해야 한다.
							startB[y][x] = true;
						}
						else {	// 짝수 열에서는 W로 시작할 때 B가 오면 다시 칠해야 한다. 
							startW[y][x] = true;
						}
					}
					else {	// 'W'인 경우 
						if(x % 2 == 0) {	
							startW[y][x] = true;
						}
						else {	// 짝수 열에서는 B로 시작할 때 W가 오면 안된다.
							startB[y][x] = true;
						}
					}
				}
			}
		}
		int maxW = 0;
		int maxB = 0;
		int min = Integer.MAX_VALUE;
		for(int y = 0; y <= n - 8; y++) {
			for(int x = 0; x <= m - 8; x++) {
				int cntW = 0;
				int cntB = 0;
				for(int t = y; t < y + 8; t++) {
					for(int k = x; k < x + 8; k++) {
						if(startW[t][k]) {
							cntW++;
						}
						if(startB[t][k]) {
							cntB++;
						}
					}
				}
				min = Math.min(min, Math.min(cntW, cntB));
			}
		}
		
		System.out.println(min);
		
		
		
//		for(int y = 1; y <= n; y++) {
//				String line = br.readLine();
//				for(int x = 1; x <= m; x++) {
//					char chess = line.charAt(x - 1);
//					if(y % 2 == 1) {
//						// B 시작시
//						
//						// W 시작시
//						
//					}
//					else {
//						
//					}
//
//					
//					if(chess == 'W') {
//						
//					}
//					else {
//						
//					}
//			}
//		}
	}
}
