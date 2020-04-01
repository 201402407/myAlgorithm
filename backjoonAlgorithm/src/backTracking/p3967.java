package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// ���� ��Ÿ
// ��Ʈ��ŷ
public class p3967 {
	static boolean[] usedAlphabet;	// �ش� ���ĺ��� ����ߴ��� �ľ��ϴ� boolean �迭
	static int[] alphabet; // �� ù��° �ٺ��� ������� ��� ���ĺ��� ���Ǿ����� �ľ��ϴ� int �迭. 0 : A, 11 : L
	static int[] result;
	static int[][] magicLine = {
			{1, 3, 6, 8},
			{2, 3, 4, 5},
			{2, 6, 9, 12},
			{8, 9, 10, 11},
			{5, 7, 10, 12},
			{1, 4, 7, 11}
	}; 
	static int MAX_LENGTH = 13;
	static int ASCII = 64;
	static boolean checked = false;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		usedAlphabet = new boolean[MAX_LENGTH];
		alphabet = new int[MAX_LENGTH];
		result = new int[MAX_LENGTH];
		
		// �Է� �� Ž��
		int index = 1;
		for(int i = 0; i < 5; i++) {
			String line = br.readLine();
			for(int j = 0; j < line.length(); j++) {
				char ch = line.charAt(j);
				if(ch == 'x') {
					index++;
				}
				else {
					if(ch != '.') {
						alphabet[index] = ch - ASCII;
						usedAlphabet[ch - ASCII] = true; // A�� �ƽ�Ű�ڵ� 65. �ε����� 1 ~ 12�̹Ƿ� 64�� ��.
						index++;
					}
				}
			}
		}
		
		backTracking(1);
		getMagicStar();
		
	}
	
	private static void backTracking(int index) {
		if(checked) {
			return;
		}
		if(index == MAX_LENGTH) {
			if(isAbleMagicStar()) {
				for(int i = 1; i < MAX_LENGTH; i++) {
					result[i] = alphabet[i];
				}	
				checked = true;
			}
			return;
		}
		// �Է� ������ �̹� Ȯ���Ǿ� �ִ� ���
		if(alphabet[index] != 0) {
			backTracking(index + 1);
		}
		else {
			// usedAlphabet Ž���ϴ� �ݺ���
			for(int i = 1; i < MAX_LENGTH; i++) {
				// �̹� ����� ������ ���
				if(usedAlphabet[i]) {
					continue;
				}
				// ������ ���
				alphabet[index] = i;
				usedAlphabet[i] = true;
				backTracking(index + 1);
				// �������� ���� ���
				alphabet[index] = 0;
				usedAlphabet[i] = false;
			}
		}
	}
	
	private static boolean isAbleMagicStar() {
		for(int i = 0; i < magicLine.length; i++) {
			int sum = 0;
			for(int j = 0; j < magicLine[0].length; j++) {
				sum += alphabet[magicLine[i][j]];
			}
			if(sum != 26) {
				return false;
			}
		}
		return true;
	}
	
	private static void getMagicStar() {
		int index = 1;
		for(int line = 0; line < 5; line++) {
			switch(line) {
			case 0: case 4:
				for(int i = 0; i < 9; i++) {
					if(i == 4) {
						System.out.print((char) (result[index++] + ASCII));
					}
					else {
						System.out.print(".");
					}
				}
				break;
			case 1: case 3:
				for(int i = 0; i < 9; i++) {
					if(i % 2 == 1) {
						System.out.print((char) (result[index++] + ASCII));
					}
					else {
						System.out.print(".");
					}
				}
				break;
			case 2:
				for(int i = 0; i < 9; i++) {
					if(i == 2 || i == 6) {
						System.out.print((char) (result[index++] + ASCII));
					}
					else {
						System.out.print(".");
					}
				}
				break;
			}
			System.out.println();
		}
	}
}
