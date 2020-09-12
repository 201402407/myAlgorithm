package realization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// È¦¼öÀÏ±î Â¦¼öÀÏ±î 
// ±¸Çö 
public class p5988 {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(n --> 0) {
			String str = br.readLine();
			if(isOdd(str)) {
				sb.append("odd");
			}
			else {
				sb.append("even");
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
	}
	
	static boolean isOdd(String str) {
		char ch = str.charAt(str.length() - 1);
		switch(ch) {
		case '0':
		case '2':
		case '4':
		case '6':
		case '8':
			return false;
		case '1':
		case '3':
		case '5':
		case '7':
		case '9':
			return true;
		}
		
		return false;
	}
}
