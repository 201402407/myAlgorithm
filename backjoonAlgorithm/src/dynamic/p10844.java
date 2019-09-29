package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 쉬운 계단 수
// 오버플로우 조심. 
public class p10844 {
	static long[] answer = new long[10];
	static long remain = 1000000000;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		long[][] result = new long[n + 1][10];
		recursive(result, n);
		
		long p = 0;	// 정답
		for(long element : result[n]) {
			System.out.println(element + ",  p : " + p);
			p = (p + element) % remain;
		}
		System.out.println(p);
	}
	
	private static void recursive(long[][] result, int n) {
		if(n == 1) {
			result[1][0] = 0;
			for(int j = 1; j < 10; j++) {
				result[1][j] = 1;
			}
			return;
		}
		recursive(result, n - 1);
		for(int i = 0; i < 10; i++) {
			if(i == 0) {	// 0은 1에서밖에 못온다
				result[n][0] = result[n - 1][1] % remain;
			}
			else if(i == 9) {	// 9는 8에서밖에 못온다
				result[n][9] = result[n - 1][8] % remain;
			}
			else {
				result[n][i] = (result[n - 1][i - 1] % remain)+ (result[n - 1][i + 1] % remain);
			}
			result[n][i] %= remain;
		}
	}
}