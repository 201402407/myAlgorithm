package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 민균이의 비밀번호
// 문자열 처리. 같은 길이의 문자열을 양 끝에서부터 같은지 탐색하기
public class p9933 {
	static ArrayList<String> pwds;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		pwds = new ArrayList<String>();
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			pwds.add(st.nextToken());
		}
		
		int getIndex = getSameStrIndex(n);
		String result = pwds.get(getIndex);
		System.out.println(result.length() + " " + result.charAt(result.length() / 2));
	}
	
	private static int getSameStrIndex(int n) {
		/*
		if(n == 1) {
			String str = pwds.get(0);
			if(compare(str, str, str.length())) {
				return 0;
			}
		}
		*/
		for(int i = 0; i < n; i++) {
			String a = pwds.get(i);
			for(int j = i; j < n; j++) {
				String b = pwds.get(j);
				if(a.length() == b.length()) {
					if(compare(a, b, a.length())) {
						return i;
					}
				}
			}
		}
		return -1;	// 없는 경우
	}
	// 두 개의 문자를 양 끝에서 비교하는 함수
	private static boolean compare(String a, String b, int len) {
		for(int i = 0; i < len; i++) {
			int j = len - i - 1;
			char aChar = a.charAt(i);
			char bChar = b.charAt(j);
			if(aChar != bChar) {
				return false;
			}
		}
		return true;
	}
}
