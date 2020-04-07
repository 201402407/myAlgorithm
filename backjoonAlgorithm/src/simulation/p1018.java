package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ü���� �ٽ� ĥ�ϱ� 
// �ùķ��̼� 
public class p1018 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		boolean[][] startB = new boolean[n][m]; // B�� ������ �� �ٽ� ĥ�ؾ� �ϴ� ��
		boolean[][] startW = new boolean[n][m]; // W�� ������ �� �ٽ� ĥ�ؾ� �ϴ� �� 
		char[][] map = new char[n][m];
		
		for(int i = 0; i < n; i++) {
			char[] line = br.readLine().toCharArray();
			map[i] = line;
		}
		
		// �ٽ� ĥ�ؾ� �� �κ� ����
		for(int y = 0; y < n; y++) {
			for(int x = 0; x < m; x++) {
				// �� ����
				// ¦�� �� 
				if(y % 2 == 0) { 
					if(map[y][x] == 'B') {
						if(x % 2 == 0) { // Ȧ�� �������� W�� ������ �� B�� ���� �ٽ� ĥ�ؾ� �Ѵ�.
							startW[y][x] = true;
						}
						else {	// ¦�� �������� B�� ������ �� W�� ���� �ٽ� ĥ�ؾ� �Ѵ�. 
							startB[y][x] = true;
						}
					}
					else {	// 'W'�� ��� 
						if(x % 2 == 0) {	
							startB[y][x] = true;
						}
						else {	// ¦�� �������� W�� ������ �� W�� ���� �ȵȴ�.
							startW[y][x] = true;
						}
					}
				}
				// Ȧ�� �� 
				else {
					if(map[y][x] == 'B') {
						if(x % 2 == 0) { // Ȧ�� �������� B�� ������ �� B�� ���� �ٽ� ĥ�ؾ� �Ѵ�.
							startB[y][x] = true;
						}
						else {	// ¦�� �������� W�� ������ �� B�� ���� �ٽ� ĥ�ؾ� �Ѵ�. 
							startW[y][x] = true;
						}
					}
					else {	// 'W'�� ��� 
						if(x % 2 == 0) {	
							startW[y][x] = true;
						}
						else {	// ¦�� �������� B�� ������ �� W�� ���� �ȵȴ�.
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
//						// B ���۽�
//						
//						// W ���۽�
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
