package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 단어암기
// 비트마스킹

public class p18119 {
	static int n, m;
	static int queryBits = (1 << 27) - 1;	// 111111111111111111111111(2)
	static int[] alphabetBits;
	static final int A = 'a';
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		alphabetBits = new int[n];
		
		// 알파벳 입력
		for(int i = 0; i < n; i++) {
			String str = br.readLine();
			for(int j = 0; j < str.length(); j++) {
				char ch = str.charAt(j);
				alphabetBits[i] |= (1 << (ch - A));	// OR연산. ch 알파벳이 존재하면 1이므로 OR연산을 통해 1로 채워넣어야 한다.
			}
		}
		
		// 쿼리 연산
		StringBuilder sb = new StringBuilder();
		while(m --> 0) {
			st = new StringTokenizer(br.readLine());
			int o = Integer.valueOf(st.nextToken());
			String x = st.nextToken();
			int xInt = x.charAt(0) - A;
			// o(쿼리 연산 - 잊혀지는지, 기억하는지) 에 따라 queryBits 변경
			// 잊혀진다
			if(o == 1) {
				queryBits &= ~(1 << xInt);	// ~(NOT) 한 비트와 &(AND) 연산 -> 해당 알파벳만 0(1011)으로 바꾼 뒤 AND를 통해 잊게 함
			}
			else {	// 기억한다
				queryBits |= 1 << xInt;	// |(OR) 연산 -> 해당 알파벳만 1(0100)로 바꾼 뒤 OR를 통해 기억시킴 
			}
			
			// 쿼리로 개수 찾기
			sb.append(find()).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static int find() {
		return (int) Arrays.stream(alphabetBits).filter((ele) -> (ele & queryBits) == ele).count();
	}
}


//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//// 단어암기
//// 문자열
//public class p18119 {
//	static final int ALPHABET_COUNT = 26;
//	static final int A = 'a';
//	static int n, m;
//	static int[][] alphabetCountArr;	// 각 문자 별 현재 기억하는 알파벳 별 개수 저장
//	static int[][] nowAlphabetCountArr;	// 각 문자 별 기억하는 알파벳 별 개수 저장
//	static int[] rememberCount;	// 각 문자 별 기억하는 알파벳 개수
//	static int[] nowCount;	// 각 문자 별 현재 기억하는 알파벳 개수
//	
//	public static void main(String args[]) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		n = Integer.valueOf(st.nextToken());
//		m = Integer.valueOf(st.nextToken());
//		alphabetCountArr = new int[n][ALPHABET_COUNT];
//		nowAlphabetCountArr = new int[n][ALPHABET_COUNT];
//		rememberCount = new int[n];
//		nowCount = new int[n];
//		
//		for(int i = 0; i < n; i++) {
//			String str = br.readLine();
//			for(int j = 0; j < str.length(); j++) {
//				char ch = str.charAt(j);
//				alphabetCountArr[i][ch - A]++;
//				nowAlphabetCountArr[i][ch - A]++;
//			}
//			
//			rememberCount[i] = str.length();
//			nowCount[i] = str.length();
//		}
//		
//		StringBuilder sb = new StringBuilder();
//		while(m --> 0) {
//			st = new StringTokenizer(br.readLine());
//			int o = Integer.valueOf(st.nextToken());
//			String x = st.nextToken();
//			int xInt = x.charAt(0) - A;
//			int result = o == 1 ? forget(xInt) : remember(xInt);	// 쿼리는 누적 적용되니까
//			sb.append(result).append("\n");
//		}
//		
//		System.out.println(sb.toString());
//	}
//	
//	// 잊어버릴 때
//	static int forget(int xInt) {
//		// 모든 알파벳 조지기
//		int result = 0;
//		for(int i = 0; i < n; i++) {
//			if(nowAlphabetCountArr[i][xInt] > 0) { // 잊을 게 있으면
//				nowAlphabetCountArr[i][xInt]--;
//				nowCount[i]--;
//			}
//			
//			result += rememberCount[i] == nowCount[i] ? 1 : 0;
//		}
//		
//		return result;
//	}
//	
//	// 기억할 때
//	static int remember(int xInt) {
//		// 모든 알파벳 조지기
//		int result = 0;
//		for(int i = 0; i < n; i++) {
//			if(alphabetCountArr[i][xInt] > nowAlphabetCountArr[i][xInt]) { // 잊을 게 있으면
//				nowAlphabetCountArr[i][xInt]++;
//				nowCount[i]++;
//			}
//			
//			result += rememberCount[i] == nowCount[i] ? 1 : 0;
//		}
//		
//		return result;
//	}
//}
