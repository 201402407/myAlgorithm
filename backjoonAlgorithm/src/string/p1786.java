package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 찾기 문제
// 문자열 + KMP 알고리즘 (문자열 탐색) 
public class p1786 {
	static List<Integer> list;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String pattern = br.readLine();
		list = new ArrayList<Integer>();
		StringBuilder sb = new StringBuilder();
		
		// Pi 배열 구하기 (찾으려는 문자열의 접두사와 접미사 체크)
		int[] pi = getPi(pattern);
		kmp(pi, str, pattern);
		
		// 정답 출력을 위해 결과데이터 가공 작업 
		for(int ele : list) {
			sb.append(ele).append(" ");
		}
		
		System.out.println(list.size());
		System.out.println(sb.toString());
	}

	// KMP 알고리즘 사용하여 문자열 탐색하기 
	static void kmp(int[] pi, String str, String pattern) {
		int j = 0;
		int strLen = str.length();
		int patternLen = pattern.length();
		
		for(int i = 0; i < strLen; i++) {
			while(j > 0 && str.charAt(i) != pattern.charAt(j)) { // 다르면 같았던 다음 접미사로 바로 건너뛰기 
				j = pi[j - 1];
			}
			
			if(str.charAt(i) == pattern.charAt(j)) { // 인덱스가 같은 문자 두 개가 같다.
				if(j + 1 == patternLen) { // pattern 문자열 전부가 같다면
					list.add(i - patternLen + 2); // 전체 문자열 중 패턴 문자열과 같은 문자열의 시작 인덱스를 구해야 하므로(게다가 1부터 시작함)
					j = pi[j]; // 초기화를 시켜줘야 함. 자기 자신은 어차피 0이라 0으로 직접 설정해도 되긴 할듯 
				}
				else { // 바로 다음번째 문자를 가지고 비교해야 하므로 
					j++;
				}
			}
		}
	}
	
	static int[] getPi(String str) {
		int j = 0; // 접두사 시작 인덱스 
		int n = str.length();
		int[] pi = new int[n];
		
		for(int i = 1; i < n; i++) { // 접미사 시작 인덱스 
			while(j > 0 && str.charAt(j) != str.charAt(i)) { // j > 0 인 이유는 최소 두 글자부터 비교하는 것이 맞아
				j = pi[j - 1]; // 다르면 이전의 문자열에서 접두사 - 접미사 같은 최대 길로 이동시킨다.
			}
			
			if(str.charAt(j) == str.charAt(i)) { // 같으면 
				pi[i] = ++j;
			}
		}
		
		return pi;
	}
}
