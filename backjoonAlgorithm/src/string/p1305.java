package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 광고 문제
// 문자열 탐색(KMP 알고리즘)
public class p1305 {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		String str = br.readLine();
		
		// KMP 알고리즘으로 접두사 / 접미사 동일한 문자열 최대 길이 구하기
		int lastPi = getLastPi(str);
		System.out.println(n - lastPi);
	}
	
	static int getLastPi(String str) {
		int len = str.length();
		int j = 0, max = 0; // 접두사 탐색 시작 인덱스
		int[] pi = new int[len];
		
		for(int i = 1; i < len; i++) {
			while(j > 0 && str.charAt(j) != str.charAt(i)) {
				j = pi[j - 1];
			}
			
			if(str.charAt(j) == str.charAt(i)) {
				pi[i] = ++j;
//				max = Math.max(max, pi[i]);
			}
		}
		
		return pi[len - 1];
	}
}
