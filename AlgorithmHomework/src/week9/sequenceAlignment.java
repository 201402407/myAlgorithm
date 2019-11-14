package week9;

import java.util.Scanner;

// Sequence Alignment �˰���
// 201402407 ���ؿ�
// ���� ���� �˰���
// �� ����� �Ǵ� ���ڰ� ���ٸ� +1
// �� ����� �Ǵ� ���ڰ� �ٸ��ٸ� -1
// �� ����� �Ǵ� ���� �� �ϳ��� �����̶�� -2
// �� ����� �Ǵ� ���ڰ� ��� �����̶�� 0
public class sequenceAlignment {
	static String strX;
	static String strY;
	static char[] arrX;
	static char[] arrY;
	static int[][] a;
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print("���� x�� �Է��ϼ��� : ");
		strY = sc.nextLine();
		System.out.print("���� Y�� �Է��ϼ��� : ");
		strX = sc.nextLine();
		arrX = strX.toCharArray();
		arrY = strY.toCharArray();
		alignment(-2); // �� ����� �Ǵ� ���� �� �ϳ��� �����̸� -2 �̹Ƿ�
		System.out.println("******* 201402407 ���ؿ� *******");
		System.out.println("*** ���� x, y �� ������ ��ķ� ǥ�� ***");
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	static int alignment(int mismatch_penalty) {
		int xLen = arrX.length;
		int yLen = arrY.length;
		a = new int[xLen + 1][yLen + 1];
		for(int i = 0; i <= xLen; i++) {
			a[i][0] = i * mismatch_penalty;
		}
		for(int j = 0; j <= yLen; j++) {
			a[0][j] = j * mismatch_penalty;
		}
		
		for(int i = 1; i <= xLen; i++) {
			System.out.println("======================");
			for(int j = 1; j <= yLen; j++) {
				System.out.println("======================");
				System.out.println("i : " + i + ", j : " + j);
				System.out.println(a[i - 1][j - 1] + getPenalty(i, j) + " || " + (a[i - 1][j] + mismatch_penalty));
				a[i][j] = Math.max(a[i][j - 1] + mismatch_penalty, a[i - 1][j - 1] + getPenalty(i, j));
				a[i][j] = Math.max(a[i][j], a[i - 1][j] + mismatch_penalty);
				System.out.println("------------------------");
				System.out.println((a[i][j - 1] + mismatch_penalty) + " || " + a[i][j]);
			}
		}
		return a[xLen][yLen];
	}
	
	static int getPenalty(int i, int j) {
		return arrX[i - 1] == arrY[j - 1] ? 1 : -1; 
	}
}
