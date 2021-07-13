package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 가장 많은 글자
// 문자열문제
public class p1371 {
	static final char ZERO_CHAR = 'a';	// 알파벳소문자, 공백, 엔터 밖에 없음
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 문자열 for문해서 각 알파벳 별 int 배열에 문자 - '0'에 해당하는 인덱스에 ++
		int[] alphabetCount = new int[26];	// 32 : SP(스페이스바), 13 : CR(엔터)
		String line = "";
		while((line = br.readLine()) != null) {
			if(line.length() == 0) {
				break;
			}
			
			for(int i = 0; i < line.length(); i++) {
				char ch = line.charAt(i);
				if(ch == ' ') {	// 공백인 경우
					continue;
				}
				
				int index = ch - ZERO_CHAR;
				alphabetCount[index]++;
			}
		}
		
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < alphabetCount.length; i++) {
			if(alphabetCount[i] > max) {
				max = alphabetCount[i];
			}
		}
		
		for(int i = 0; i < alphabetCount.length; i++) {
			if(alphabetCount[i] == max) {
				System.out.print((char) (i + ZERO_CHAR));
			}
		}
	}
}

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//// 가장 많은 글자
//// 문자열문제
//public class Main {
//	static final char ZERO_CHAR = 'a';	// 알파벳소문자, 공백, 엔터 밖에 없음
//	public static void main(String args[]) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        
//
//       int[] alp = new int[26];
//        String s="";
//        while((s=br.readLine())!=null) {
//
////            if(s.length()==0)break;
//
//            for (int i = 0; i < s.length(); i++) {
//                char c = s.charAt(i);
//
//                if (c == ' ') continue;//공백이면 다음 문자로 넘어감
//
//                int j = c - ZERO_CHAR;
//                alp[j]++;
//
//            }
//
//        }
//
//
//
//
//
//        //반복문 두번 돌려서 max값 찾고, 가장 많이 나온 알파벳들 출력
//
//        int max =Integer.MIN_VALUE;
//        for(int i =0;i<alp.length;i++){
//            if(alp[i]>max) max=alp[i];
//        }
//
//        for(int i =0;i<alp.length;i++){
//            if(alp[i]==max) System.out.print((char)(i+ZERO_CHAR));
//        }
//	}
//}
