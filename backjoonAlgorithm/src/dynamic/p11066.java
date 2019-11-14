package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 파일 합치기
// 다이나믹 프로그래밍
// Matrix Multiplication 알고리즘 응용
public class p11066 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int t = Integer.valueOf(st.nextToken());
		for(int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			int[] dimension = new int[n + 1];	// 1 ~ n 까지
			int[][] mm = new int[n + 1][n + 1];	// 1 ~ n 까지
			int[] sum = new int[n + 1];	// 1 ~ n 까지
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				int d = Integer.valueOf(st.nextToken());
				dimension[j] = d;
				sum[j] = sum[j - 1] + d;
			}
			int result = matrixChainOrder(dimension, mm, sum);
			sb.append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static int matrixChainOrder(int[] dimension, int[][] mm, int[] sum) {
		int n = dimension.length - 1;
		
		// 이게 슬라이스로 내려가면서 탐색하는 for문
		for(int l = 2; l <= n; l++) {
			for(int i = 1; i <= n - l + 1; i++) {
				int j = i + l - 1;
				mm[i][j] = Integer.MAX_VALUE;
				for(int k = i; k <= j - 1; k++) {
					mm[i][j] = Math.min(mm[i][j], mm[i][k] + mm[k + 1][j] + sum[j] - sum[i - 1]);
				}
			}
		}
		return mm[1][n];
	}
	
	static void printMM(int n, int[][] mm) {
		System.out.println("-----------------------------------------------");
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				System.out.print(mm[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("-----------------------------------------------");
	}
}
