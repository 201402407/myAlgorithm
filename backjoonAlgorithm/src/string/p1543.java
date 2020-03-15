package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 문서 검색
// 문자열 처리 문제
public class p1543 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(br.readLine());
		String str = br.readLine();
		
		int count = 0;
		int startIndex = 0;
		int len = str.length();
		while((startIndex = sb.toString().indexOf(str)) != -1) {
			sb.delete(0, startIndex + len);
			count++;
		}
		
		System.out.println(count);
	}
}
