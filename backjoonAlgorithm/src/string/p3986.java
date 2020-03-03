package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 좋은 단어
// 문자열 처리 문제 -> Stack을 array로 사용
public class p3986 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int count = 0;
		for(int i = 0; i < n; i++) {
			String str = br.readLine();	
			int len = str.length();
			char[] stack = new char[len]; 
			int stackLen = 0;
			
			for(int j = 0; j < len; j++) {
				char nowCh = str.charAt(j);
					if(stackLen == 0) {
						stack[stackLen] = nowCh;
						stackLen++;
					}
					else if(stack[stackLen- 1] == nowCh) {	// 이전 문자와 현재 문자가 같으면
						stackLen--;	// pop
					}
					else {
						stack[stackLen] = nowCh;
						stackLen++;
					}
			}
			if(stackLen == 0) {
				count++;
			}
		}
		System.out.println(count);
	}
}


//package string;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Stack;
//import java.util.StringTokenizer;
//
//// 좋은 단어
//// 문자열 처리 문제 -> Stack 사용
//public class p3986 {
//	public static void main(String args[]) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		
//		int n = Integer.valueOf(st.nextToken());
//		int count = 0;
//		for(int i = 0; i < n; i++) {
//			String str = br.readLine();
//			Stack<Character> stack = new Stack<Character>();	
//			int len = str.length();
//			stack.push(str.charAt(0));
//			
//			for(int j = 1; j < len; j++) {
//				char nowCh = str.charAt(j);
//				if(!stack.isEmpty()) {
//					char prevCh = stack.peek();
//					if(prevCh == nowCh) {	// top의 문자와 같으면 pop
//						stack.pop();
//						continue;
//					}
//				}
//				stack.push(nowCh);
//			}
//			
//			if(stack.isEmpty()) {	// 좋은 단어
//				count++;
//			}
//		}
//		System.out.println(count);
//	}
//}
