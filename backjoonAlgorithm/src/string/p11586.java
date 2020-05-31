package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 지영 공주님의 마법 거울
// 문자열 처리
public class p11586 {
	static String[] mirror;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		mirror = new String[n];
		
		// 맵 input 받아오기
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String line = st.nextToken();
			mirror[i] = line;
		}
		
		st = new StringTokenizer(br.readLine());
		int k = Integer.valueOf(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		// 거울 그리기
		if(k == 1) {
			for(int i = 0; i < n; i++) {
				sb.append(mirror[i]);
				if(i + 1 < n) 
					sb.append("\n");
			}
		}
		if(k == 2) {
			for(int i = 0; i < n; i++) {
				sb.append(new StringBuffer(mirror[i]).reverse().toString());
				if(i + 1 < n) 
					sb.append("\n");
			}
		}
		if(k == 3) {
			for(int i = n - 1; i >= 0; i--) {
				sb.append(mirror[i]);
				if(i > 0) 
					sb.append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}