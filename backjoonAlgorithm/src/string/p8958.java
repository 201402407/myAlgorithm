package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// OX퀴즈
// 문자열
public class p8958 {
	static int sum;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		
		for(int i = 0; i < n; i++) {
			sum = 0;	// 초기화
			String line = br.readLine();
			getCount(line);
			System.out.println(sum);
		}
	}
	
	static void getCount(String line) {
		int len = line.length();
		int start = 0;
		for(int i = 0; i < len; i++) {
			if(line.charAt(i) == 'O') {
				sum += ++start;
			}
			else {
				start = 0;
			}
		}
	}
}
