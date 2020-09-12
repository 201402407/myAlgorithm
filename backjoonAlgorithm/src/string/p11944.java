package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// NN
// 문자열 문제 
public class p11944 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String n = st.nextToken();
		int numberN = Integer.valueOf(n);
		int m = Integer.valueOf(st.nextToken());
		int len = n.length();
		
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < numberN * len && i <= m; i += len) {
			sb.append(n);
		}
		
		if(sb.length() > m) {
			System.out.print(sb.toString().substring(0, m));
		}
		else {
			System.out.print(sb.toString());
		}
		
//		if(len * numberN > m) {
//			int max = m / len;
//			int remain = m % len;
//			for(int i = 0; i <= max; i++) {
//				sb.append(n);
//			}
//			
//			// 빠져나온 개수만큼 remove
//			for(int i = 0; i < len - remain; i++) {
//				sb.deleteCharAt(sb.length() - 1);
//			}
//		}
//		else {
//			for(int i = 0; i < numberN; i++) {
//				sb.append(n);
//			}
//		}
//		
//		System.out.print(sb.toString());
	}
}
