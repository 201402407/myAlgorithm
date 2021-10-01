package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 단어암기
// 문자열
public class p18119 {
	static final int ALPHABET_COUNT = 26;
	static final int A = 'a';
	static int n, m;
	static int[][] alphabetCountArr;	// 각 문자 별 기억하는 알파벳 별 개수 저장
	static int[] rememberCount;	// 각 문자 별 기억하는 알파벳 개수
	static int[] nowCount;	// 각 문자 별 현재 기억하는 알파벳 개수
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		alphabetCountArr = new int[n][ALPHABET_COUNT];
		rememberCount = new int[n];
		nowCount = new int[n];
		
		for(int i = 0; i < n; i++) {
			String str = br.readLine();
			for(int j = 0; j < str.length(); j++) {
				char ch = str.charAt(j);
				alphabetCountArr[i][ch - A]++;
			}
			
			rememberCount[i] = str.length();
			nowCount[i] = str.length();
		}
		
		while(m --> 0) {
			st = new StringTokenizer(br.readLine());
			int o = Integer.valueOf(st.nextToken());
			String x = st.nextToken();
			int xInt = x.charAt(0) - A;
			
			
		}
	}
	
	// 잊어버릴 때
	static void forget(int xInt) {
		// 모든 알파벳 조지기
		for(int i = 0; i < n; i++) {
			if(nowCount[i] == b)
		}
	}
	
	// 기억할 때
	static void remember(int xInt) {
		// 모든 알파벳 조지기
		for(int i = 0; i < n; i++) {
			if()
		}
	}
}
