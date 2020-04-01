package realization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 팩토리얼 문제
// 구현
public class p10872 {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		
		long[] result = new long[13];
		
		result[0] = 1;
		result[1] = 1;
		for(int i = 2; i < 13; i++) {
			result[i] = i * result[i - 1]; 
		}
		System.out.println(result[n]);
	}
}
