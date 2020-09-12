package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 문자열 처리
// 농구경기 
public class p1159 {
	static final int ASCII = 97;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int[] alphabet = new int[26];
		
		// 성씨 개수 파악 
		for(int i = 0; i < n; i++) {
			String name = br.readLine();
			char first = name.charAt(0);
			alphabet[first - ASCII]++;
		}

		StringBuilder sb = new StringBuilder();
		
		// 5개 이상 비교 
		for(int i = 0; i < alphabet.length; i++) {
			if(alphabet[i] >= 5) {
				sb.append((char) (i + ASCII));
			}
		}
		
		if(sb.length() == 0) {
			System.out.print("PREDAJA");
		}
		else {
			System.out.print(sb.toString());
		}
	}
}
