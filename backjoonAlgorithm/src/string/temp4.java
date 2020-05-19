package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 11586
public class temp4 {
	static char[][] map;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		map = new char[n][n];
		
		// 맵 input 받아오기
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String line = st.nextToken();
			map[i] = line.toCharArray();
		}
		
		st = new StringTokenizer(br.readLine());
		int k = Integer.valueOf(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		// 거울 그리기
		if(k == 1) {
			for(int i = 0; i < n; i++) {
				sb.append(map[i].toString());
				if(i + 1 < n) 
					sb.append("\n");
			}
		}
		if(k == 2) {
			for(int i = 0; i < n; i++) {
				for(int j = n - 1; j >= 0; j--) {
					sb.append(map[i][j]);
				}
				if(i + 1 < n) 
					sb.append("\n");
			}
		}
		if(k == 3) {
			for(int i = n - 1; i >= 0; i--) {
				for(int j = 0; j < n; j++) {
					sb.append(map[i][j]);
				}
				if(i > 0) 
					sb.append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}
