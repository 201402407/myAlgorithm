package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 뒤집기 문제
// 쉬움
public class p1439 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		char[] chars = str.toCharArray();
		int n = chars.length;
		int ascii = 48;
		int[] count = new int[2];	// 0: zeroCount, 1: oneCount 
		char lastCh = chars[0];	// 임시 시작 문자 -> 안쓰는 9로 설정
		
		for(int i = 1; i < n; i++) {
			if(lastCh != chars[i]) {
				count[lastCh - ascii]++;
				lastCh = chars[i];
			}
		}
		
		// 연속된 문자의 끝을 파악해서 count + 1 해야 함
		count[lastCh - ascii]++;
		
		System.out.println(Math.min(count[0], count[1]));
	}
}
