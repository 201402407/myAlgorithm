package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 균형잡힌 세상 문제
// 문자열 문제
// 괄호 짝을 찾는다 -> 스택 사용
public class p4949 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str;
		StringBuilder sb = new StringBuilder();
		
		while(!(str = br.readLine()).equals(".")) {
//			System.out.println(str);
			Stack<Character> stack = new Stack<Character>();
			int len = str.length();
			boolean isBalance = true;
			for(int i = 0; i < len; i++) {
				char ch = str.charAt(i);
				if(ch == '(' || ch == '[') {
					stack.push(ch);
				}
				else if(ch == ')') {
					if(!stack.isEmpty() && stack.peek() == '(') {
						stack.pop();
					}
					else {
						isBalance = false;
						break;
					}
				}
				else if(ch == ']') {
					if(!stack.isEmpty() && stack.peek() == '[') {
						stack.pop();
					}
					else {
						isBalance = false;
						break;
					}
				}
			}
			if(!stack.isEmpty()) {
				isBalance = false;
			}
			
			if(isBalance) {
				sb.append("yes").append("\n");
			}
			else {
				sb.append("no").append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}
