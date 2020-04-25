package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 가르침
// 시뮬레이션
public class p1062 {
	static int ASCII = 97;
	static boolean[] usedAlphabet;
	static ArrayList<String> list;
	static int result = 0;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		
		// anta + * + tica 인 문자들로 이루어져있기 때문에, 최소 5개의 글자를 가르쳐야지 단어를 읽을 수 있다.
		if(k < 5) {	
			System.out.println(0);
		}
		else if(k == 26) {
			System.out.println(n);
		}
		else {
			boolean[] alphabet = new boolean[26];
			usedAlphabet = new boolean[26];

			// 필수 단어에 들어가는 문자 5개 true
			alphabet['a' - ASCII] = true;
			alphabet['t' - ASCII] = true;
			alphabet['n' - ASCII] = true;
			alphabet['i' - ASCII] = true;
			alphabet['c' - ASCII] = true;
			k -= 5;
			
			list = new ArrayList<String>();
			int count = 0;
			// 알파벳 사용 빈도 체크하기
			for(int i = 0; i < n; i++) {
				String word = br.readLine();
				String wordSubStr = word.substring(4, word.length() - 4);
				list.add(wordSubStr);

				// 사용 횟수 count하기
				for(int j = 0; j < wordSubStr.length(); j++) {
					char ch = wordSubStr.charAt(j);
					int index = ch - ASCII;
					if(!usedAlphabet[index] && !sameNeedAlphabet(ch)) {
						usedAlphabet[index] = true;
						count++;
					}
				}
			}
			
			// 가르칠 수 있는 문자 개수가 단어를 읽기 위해 필요한 문자 개수보다 같거나 많으면 다 읽을 수 있다.
			if(k >= count) {
				System.out.println(n);
				return;
			}
			
			backTracking(alphabet, 0, k);
			
			System.out.println(result);
		}
	}
	static void backTracking(boolean[] alphabet, int index, int k) {
		if(k == 0) {
			getCountOfRead(alphabet);
		}
		else {
			if(index == alphabet.length) {
				return;
			}
			
			// 필수 단어인 경우 바로 다음 문자 탐색
			if(sameNeedAlphabet((char) (index + ASCII))) {
				backTracking(alphabet, index + 1, k);
			}
			else {
				if(usedAlphabet[index]) {
					alphabet[index] = true;
					backTracking(alphabet, index + 1, k - 1);
					alphabet[index] = false;
					backTracking(alphabet, index + 1, k);	
				}
				else {	// 사용이 필요없는 단어인경우
					backTracking(alphabet, index + 1, k);
				}
			}
		}
	}
	
	static void getCountOfRead(boolean[] alphabet) {
		int count = 0;
		for(String word : list) {
			boolean check = true;
			for(int i = 0; i < word.length(); i++) {
				char ch = word.charAt(i);
				if(!alphabet[ch - ASCII]) {
					check = false;
					break;
				}
			}
			
			if(check) {
				count++;
			}
		}
		result = Math.max(result, count);
	}
	
	static boolean sameNeedAlphabet(char ch) {
		if(ch == 'a' || ch == 't' || ch == 'n' || ch == 'i' || ch == 'c') {
			return true;
		}
		return false;
	}
}