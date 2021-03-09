package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// !밀비 급일
// 문자열 문제
public class p11365 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringBuffer sb = new StringBuffer();
		
		while((line = br.readLine()) != null) {
			if(line.equals("END")) {
				System.out.println(sb.toString());
				return;
			}
			
			char[] arr = line.toCharArray();
			int len = arr.length;
			for(int i = len - 1; i >= 0; i--) {
				sb.append(arr[i]);
			}
			
			sb.append("\n");
		}
	}
}
