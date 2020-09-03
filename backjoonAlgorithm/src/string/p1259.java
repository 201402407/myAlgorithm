package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 팰린드롬수
// 문자열 문제 	
public class p1259 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str;
		StringBuilder sb = new StringBuilder();
		while((str = br.readLine()) != null) {
			if(str.equals("0")) {
				break;
			}
			
			int len = str.length();
			int start = 0;
			int end = len - 1;
			boolean isPal = true;
			// 두 탐색 인덱스가 교차할 때 까지 순차 탐색
			while(start <= end) {
				if(str.charAt(start) != str.charAt(end)) {
					isPal = false;
					break;
				}
				
				start++;
				end--;
			}
			
			if(isPal) {
				sb.append("yes");
			}
			else {
				sb.append("no");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
