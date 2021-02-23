package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 가장 긴 문자열
// 문자열 + KMP 알고리즘
public class p3033 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		String str = br.readLine();
		int result;
		// 해당 문자열의 부분 문자열마다 pi 탐색하기(문자열 길이 최대 값)
		for(int i = 0; i < n; i++) {
			String subStr = str.substring(i, n);
			result = Math.max(result, getPi(subStr, n));
		}
		
		System.out.println(result);
	}
	
	public static int getPi(String str, int n) {
		int j = 0;
		int max = 0;
		int[] pi = new int[n];
		
		
		for(int i = 1; i < n; i++) {
			// 우측 비교 접미사 비교 문자열 인덱
			while(j > 0 && str.charAt(i) != str.charAt(j)) {
				j = pi[n - i];
			}
			
			if(str.charAt(i) == str.charAt(j)) {
				pi[i] = ++j;
				max = Math.max(max, pi[i]);
			}
		}
		
		return max;
	}
}
