package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// ���� ����
// ����Ž�� 
public class p3085 {
	static char[][] bomboni; 
	static int[] moveX = {0, 1, 0, -1};	// ��, ��, ��, ��  
	static int[] moveY = {-1, 0, 1, 0};	// ��, ��, ��, ��  
	static int n;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());
		bomboni = new char[n][n];
		
		// ���� ���� �Է¹ޱ� 
		for(int i = 0; i < n; i++) {
			bomboni[i] = br.readLine().toCharArray();
		}
		
		int max = 0;
		// 1. 2�� for�� ����
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				// �ٲ�� ������ �� �� üũ ���ֱ� 
				max = Math.max(max, Math.max(checkSameColor(j, i), binarySearch(j, i)));
			}
		}
		
		System.out.println(max);
	}
	
	static int binarySearch(int x, int y) {
		int max = 0;
		int nextX, nextY;
		char color = bomboni[y][x];
		
		// ��, �� ���� ���� �� -> ��ȯ -> ���� ���� ���� Ž�� 
		for(int i = 1; i <= 2; i++) {
			nextX = x + moveX[i];
			nextY = y + moveY[i];
			if((nextX >= 0 && nextX < n) && (nextY >= 0 && nextY < n)) {	// ���� ���� ���� ���;� ��. 
				if(color != bomboni[nextY][nextX]) {	// ������ ������ �޶�� ��.
//					System.out.println("asg");
					// SWAP
					swap(x, y, nextX, nextY); // ��ȯ 
					max = Math.max(max, Math.max(checkSameColor(x, y), checkSameColor(nextX, nextY)));
					swap(x, y, nextX, nextY); // ��ȯ �����·� ���� 
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
	
	// ��� �� �������� ���� ���� ���� �ľ��ϱ� 
	static int checkSameColor(int x, int y) {
		int max = 0;
		char nowColor = bomboni[y][x];
		int[] result = new int[moveX.length];
		
		for(int i = 0; i < moveX.length; i++) {
			int count = 0;
			int nextX = x + moveX[i];
			int nextY = y + moveY[i];
			
			if((nextX < 0 || nextX >= n) || (nextY < 0 || nextY >= n)) {	// ���� ���� ���� ���;� ��. 
				continue;
			}
			
			while(nowColor == bomboni[nextY][nextX]) {
//				System.out.println("assg");
				count++;
				nextX += moveX[i];
				nextY += moveY[i];
				
				if((nextX < 0 || nextX >= n) || (nextY < 0 || nextY >= n)) {	// ���� ���� ���� ���;� ��. 
					break;
				}
			}
			
			result[i] = count;
//			max = Math.max(max, count);
		}
		
		// ��(�� + ��) �� ��(�� + ��) ���� �� 
		return Math.max(result[0] + result[2] + 1, result[1] + result[3] + 1);
	}
}
