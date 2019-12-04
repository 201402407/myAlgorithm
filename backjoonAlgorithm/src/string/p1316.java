package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 그룹 단어 체커
// 문자열 문제
public class p1316 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int count = 0;
		for(int i = 0; i < n; i++) {
			int[] results = new int[26];	// a ~ z까지 26개.
			boolean[] checked = new boolean[26];
			st = new StringTokenizer(br.readLine());
			String word = st.nextToken();
			
			// 한 글자인 경우
			int prevCh = 0;
			boolean isGroup = true;	// 그룹 단어인지 여부 체크하는 bool 변수
			if(word.length() == 1) {
				count++;
				continue;
			}
			else {
				int ch = word.charAt(0) - 97;	// a가 0부터 시작하기 위함
				checked[ch] = true;
				prevCh = ch;
			}
			
			for(int x = 1; x < word.length(); x++) {
				int ch = word.charAt(x) - 97;	// a가 0부터 시작하기 위함
				if(checked[ch]) {	// 만약 이 전에 해당 문자를 사용한 경우
					if(prevCh != ch) {	// 바로 이전 문자와 다른 경우(연속되지 않은 경우)
						isGroup = false;
						break;
					}
				}
				else {
					checked[ch] = true;
					prevCh = ch;
				}
			}
			
			if(isGroup)
				count++;
		}
		System.out.println(count);
	}
}
