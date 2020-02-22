package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 알파벳 개수 문제
// 문자열 처리. charAt로 계산해서 array에 저장
public class p10808 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String line = st.nextToken();
		int[] arr = new int[26];
		for(int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			arr[ch - 97]++; 
		}
		
		StringBuilder sb = new StringBuilder();
		for(int ele : arr) {
			sb.append(ele).append(" ");
		}
		System.out.println(sb.toString());
	}
}