package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 문자열 처리 + 정규표현식(DFA. 즉, 오토마타 상태 그래프 그려서 하나씩 나타내기)
// Contact 
public class p1013 {
	static StringBuilder sb;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		sb = new StringBuilder();
		
		while(n --> 0) {
			String jeonPa = br.readLine();
			
			int state = 0;
			for(int i = 0; i < jeonPa.length(); i++) {
				char num = jeonPa.charAt(i);
				state = getState(state, num);
				
				if(state == -1) {
					break;
				}
			}
			
			if(state == 0 || state == 3 || state == 6 || state == 7) {
				sb.append("YES");
			}
			else {
				sb.append("NO");
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
	}
	
	// 오토마타 상태 그래프에 맞게 출력 
	static int getState(int state, char ch) {
		if(ch == '0') {
			switch(state) {
			case 0:
				return 1;
			case 2:
				return 4;
			case 3:
				return 1;
			case 4:
				return 5;
			case 5:
				return 5;
			case 6:
				return 1;
			case 7:
				return 8;
			case 8:
				return 5;
			}
		}
		
		if(ch == '1') {
			switch(state) {
			case 0:
				return 2;
			case 1:
				return 3;
			case 3:
				return 2;
			case 5:
				return 6;
			case 6:
				return 7;
			case 7:
				return 7;
			case 8:
				return 0;
			}
		}
		
		return -1;
	}
}	
