package week9;

import java.util.Scanner;

// Sequence Alignment 알고리즘
// 201402407 이해원
// 서열 정렬 알고리즘
// 비교 대상이 되는 문자가 같다면 +1
// 비교 대상이 되는 문자가 다르다면 -1
// 비교 대상이 되는 문자 중 하나가 공백이라면 -2
// 비교 대상이 되는 문자가 모두 공백이라면 0
public class sequenceAlignment {
	static String strX;
	static String strY;
	static char[] arrX;
	static char[] arrY;
	static int[][] a;
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print("서열 x를 입력하세요 : ");
		strY = sc.nextLine();
		System.out.print("서열 Y를 입력하세요 : ");
		strX = sc.nextLine();
		arrX = strX.toCharArray();
		arrY = strY.toCharArray();
		alignment(-2); // 비교 대상이 되는 문자 중 하나가 공백이면 -2 이므로
		System.out.println("******* 201402407 이해원 *******");
		System.out.println("*** 서열 x, y 간 조합을 행렬로 표현 ***");
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
