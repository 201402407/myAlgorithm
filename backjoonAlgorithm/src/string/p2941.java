package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 문자열 처리
// 크로아티아 알파벳
// 스택?
public class p2941 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String str = st.nextToken();
		Stack stack = new Stack();
		int count = 0;
		int len = str.length();
		for(int i = len - 1; i >= 0; i--) {	// 문자열 맨 끝부터 시작
			count++;
			char ch = str.charAt(i);
			if(i == 0)
				break;
			switch(ch) {
			case '=':
				if(str.charAt(i - 1) == 'c' || str.charAt(i - 1) == 's') {
					i--;	// 알파벳 하나로 치므로 인덱스 1을 더 감소시킴
				}
				else if(str.charAt(i - 1) == 'z') {
					i--;
					if(i >= 1) {
						if(str.charAt(i - 1) == 'd') {
							i--;
						}
					}
				}
				break;
			case '-':
				if(str.charAt(i - 1) == 'c' || str.charAt(i - 1) == 'd') {
					i--;	// 알파벳 하나로 치므로 인덱스 1을 더 감소시킴
				}
				break;
			case 'j':
				if(str.charAt(i - 1) == 'l' || str.charAt(i - 1) == 'n') {
					i--;	// 알파벳 하나로 치므로 인덱스 1을 더 감소시킴
				}
				break;
				default:
					break;
			}
		}
		System.out.println(count);
	}
}
