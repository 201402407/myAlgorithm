package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 정수 삼각형
// 핵심 : result[i][j] = Math.max(result[i - 1][j - 1], result[i - 1][j]) + triangle[i][j];
// 베스트 : mAr[i][j] = Math.max(mAr[i-1][j] + ar[i][j] , mAr[i-1][j-1] + ar[i][j]); 이거 하나면 됨
// if문 어줍짢게 많이 쓰는 것보다 for문 잘 돌리는게 낫다.
public class p1932 {
	static int[][] triangle;
	static int[][] result;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		triangle = new int[n + 1][n + 1]; // 인덱스 : 1...n
		result = new int[n + 1][n + 1];
		int answer = 0;
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < i; j++) {
				int num = Integer.valueOf(st.nextToken());
				triangle[i][j] = num;
				if(i == 1) {  // n == 1인 경우
					result[i][j] = num;
					answer = result[i][j]; 
				}
				else {	// n이 2 이상
					if(j == 0) 	// 왼쪽 끝 원소
						result[i][j] = result[i - 1][j] + triangle[i][j];
					else if(j + 1 == i) 	// 오른쪽 끝 원소
						result[i][j] = result[i - 1][j - 1] + triangle[i][j];
					else 
						result[i][j] = Math.max(result[i - 1][j - 1], result[i - 1][j]) + triangle[i][j];
					
					if(i == n)  // 삼각형 마지막 줄인 경우
						answer = Math.max(answer, result[i][j]);
					
				}
			}
		}		
		System.out.println(answer);
	}
}