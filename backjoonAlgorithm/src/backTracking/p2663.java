package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 좋은수열
// 백트래킹 문제
public class p2663 {
	static boolean endCheck = false;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		
		backTracking(n, 1, "");
	}
	
	public static void backTracking(int n, int size, String str) {
		if(endCheck) {
			return;
		}
		if(size - 1 == n) {
			System.out.println(str);
			endCheck = true;
			return;
		}
		
		for(int num = 1; num <= 3; num++) {
			String next = str + num;			
			int len = size / 2;
			boolean check = true;
			for(int i = 1; i <= len; i++) {
				String a = next.substring(size - i, size);
				String b = next.substring(size - i - i, size - i);
				if(a.equals(b)) {
					check = false;
					break;
				}
			}
			if(!check) {
				continue;
			}
			else {
				backTracking(n, size + 1, next);
			}
		}
	}
}
