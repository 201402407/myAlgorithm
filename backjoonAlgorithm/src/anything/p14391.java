package anything;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 종이 조각
public class p14391 {
	static int result, n, m;
	static int[] paperSlice;	// n * m으로 1차원 배열 가능
	static boolean[] verticalHorizontal;	// n * m으로 1차원 배열 가능
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());	// 세로크기
		m = Integer.valueOf(st.nextToken());	// 가로크기
		int maxLen = n * m;
//		verticalHorizontal = new boolean[n][m];	
		paperSlice = new int[maxLen];	// 가로 : false / 세로 : true
		verticalHorizontal = new boolean[maxLen];	// 가로 : false / 세로 : true
//		char[][] paperSlice = new char[n][m];
		
		int index = 0;
		for(int i = 0; i < n; i++) {
			char[] charArr = br.readLine().toCharArray();
			for(char ch : charArr) {
				paperSlice[index++] = (int)(ch - '0');	// 입력 값 저장
			}
		}
		
		backTracking(0, maxLen);
		
//		int result = Math.max(horizontal(paperSlice, n, m), vertical(paperSlice, n, m));
		System.out.println(result);
	}
	
	static void backTracking(int index, int maxLen) {
		if(index == maxLen) {	// 백트래킹 전부 그림
			//
			result = Math.max(result, calculateSlice());
		}
		else {
			// 가로인 경우
			verticalHorizontal[index] = false;
			backTracking(index + 1, maxLen);
			// 세로인 경우
			verticalHorizontal[index] = true;
			backTracking(index + 1, maxLen);
		}
	}
	
	// 각 조각들 숫자계산
	static int calculateSlice() {
		int sum = 0;	// 전체합
		
		// 가로 경우 계산(왼쪽 -> 오른쪽)
		for(int y = 0; y < n; y++) {
			int tempSave = 0;
			for(int x = 0; x < m; x++) {
				int index = (y * m) + x;
				// 이는 왼쪽 -> 오른쪽 순서대로 index가 진행되기 때문에
				if(!verticalHorizontal[index]) {	// 가로인 경우만 값 계산
					tempSave *= 10;	// 한 칸씩 증가할수록 10씩 곱해진다.
					tempSave += paperSlice[index];
				}
				else {	// 세로인 경우는 얄짤없다
					sum += tempSave;
					tempSave = 0;	// 초기화(가로 연속된 조각 끊겼으므로)
				}
			}
			sum += tempSave;
		}
		
		// 세로경우 계산
		for(int x = 0; x < m; x++) {
			int tempSave = 0;
			for(int y = 0; y < n; y++) {
				int index = (y * m) + x;	// 인덱스 순서는 변함없다.
				if(verticalHorizontal[index]) {	// 세로인 경우만 값 계산
					tempSave *= 10;	// 한 칸씩 증가할수록 10씩 곱해진다.
					tempSave += paperSlice[index];
				}
				else {	// 세로인 경우는 얄짤없다
					sum += tempSave;
					tempSave = 0;	// 초기화(가로 연속된 조각 끊겼으므로)
				}
			}
			sum += tempSave;
		}
		
		return sum;
	}
}
