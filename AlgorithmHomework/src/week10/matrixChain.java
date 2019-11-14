package week10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

// 연쇄 행렬 곱셈 알고리즘
// 201402407 이해원

public class matrixChain {
	static int[][] mm;	// matrix multiplication
	public static void main(String args[]) {
		String fileSrc = new java.io.File("").getAbsolutePath();
		String inputFileSrc =  fileSrc + "/src/data11_matrix_chain.txt";	// 상대 경로 설정
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(inputFileSrc));
			String str;
			int[] dimension = new int[7]; // 인덱스 : 0 ~ 6
			int n = 0;
			while((str = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(str, ",");
				int d1 = Integer.valueOf(st.nextToken());
				int d2 = Integer.valueOf(st.nextToken());
				dimension[n] = d1;
				dimension[n + 1] = d2;
				n++;
			}
			mm = new int[n + 1][n + 1];
			int result = matrixChainOrder(dimension);
			System.out.println(result);
			printMM(n);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 동적 프로그래밍
	static int matrixChainOrder(int[] p) {
		int minValue = Integer.MAX_VALUE;
		int n = p.length - 1; // mm의 개수는 p의 개수보다 1 작은데, 인덱스가 1 ~ n개임을 표시하기 위해서
		for(int i = 1; i <= n; i++) {
			mm[i][i] = 0;
		}
		for(int l = 2; l <= n; l++) {
			for(int i = 1; i <= n - l + 1; i++) {
				int j = i + l - 1;
				mm[i][j] = Integer.MAX_VALUE;
				for(int k = i; k <= j - 1; k++) {
					mm[i][j] = Math.min(mm[i][j], mm[i][k] + mm[k + 1][j] + (p[i - 1] * p[k] * p[j]));
					if(mm[i][j] != 0) {
						minValue = Math.min(mm[i][j], minValue);	
					}
				}
			}
		}
		return minValue;
	}
	
	static void printMM(int n) {
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
