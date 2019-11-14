package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 행렬 곱셈 순서
// 다이나믹 프로그래밍
// Matrix Multiplication 알고리즘
public class p11049 {
	static int[] dimension;
	static int[][] mm;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		dimension = new int[n + 1];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int d1 = Integer.valueOf(st.nextToken());
			int d2 = Integer.valueOf(st.nextToken());
			dimension[i] = d1;
			dimension[i + 1] = d2;
		}
		mm = new int[n + 1][n + 1];
		int result = matrixChainOrder(dimension);
		System.out.println(result);
	}
	
	static int matrixChainOrder(int[] p) {
		int n = p.length - 1;
		for(int i = 1; i <= n; i++) {
			mm[i][i] = 0;
		}
		
		for(int l = 2; l <= n; l++) {
			for(int i = 1; i <= n - l + 1; i++) {
				int j = i + l - 1;
				mm[i][j] = Integer.MAX_VALUE;
				for(int k = i; k <= j - 1; k++) {
					mm[i][j] = Math.min(mm[i][j], mm[i][k] + mm[k + 1][j] + (p[i - 1] * p[k] * p[j]));
				}
			}
		}
		int minValue = mm[1][n];
		return minValue;
	}
}
