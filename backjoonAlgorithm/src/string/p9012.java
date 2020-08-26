package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 괄호
// 문자열 처리 
public class p9012 {
	static StringBuilder sb;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		int n = Integer.valueOf(st.nextToken());
		
		while(n --> 0) {
			int checkCount = 0;
			String gualhoStr = br.readLine();
			char[] gualhoCharArr = gualhoStr.toCharArray();
			boolean vps = true;
			
			// 괄호가 담긴 문자열 괄호 탐색 
			for(int i = 0; i < gualhoCharArr.length; i++) {
				char gualho = gualhoCharArr[i];
				if(gualho == '(') {
					checkCount++;
				}
				else {
					checkCount--;
					if(checkCount < 0) {
						vps = false;
						break;
					}
				}
			}
			
			// VPS 체크 조건
			// 중간에 음수가 되지 않고 탐색 및 갯수 확인 결과 0이 되어야 함.
			if(vps && checkCount == 0) {
				sb.append("YES").append("\n");
			}
			else {
				sb.append("NO").append("\n");
			}
		}
		
		System.out.print(sb.toString());
	}
}
