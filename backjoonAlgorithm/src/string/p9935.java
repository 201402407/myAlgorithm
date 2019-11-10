package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 문자열 폭발
// 스택 알고리즘 + 문자열 처리
// 두 번째 방법인 toCharArray()로 배열 만들어 놓고 하는 방법을 썼는데, 더 느리고 메모리도 컸다.
// 그냥 charAt()가 나을듯
public class p9935 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String bomb = br.readLine();
		int strLen = str.length();
		int bombLen = bomb.length();
		Stack<Character> nowStack = new Stack<Character>();
		Stack<Character> tempStack = new Stack<Character>();
		char[] strCharArr = str.toCharArray(), bombCharArr = bomb.toCharArray();
		char bombLastChar = bombCharArr[bombLen - 1];
		
		for(int i = 0; i < strLen; i++) {
			char a = strCharArr[i];
			nowStack.push(a);
			if(a == bombLastChar) { // 끝 문자랑 같으면
				tempStack.push(nowStack.pop());
				boolean isEquals = true;
				for(int j = bombLen - 2; j >= 0; j--) {	// 폭탄 문자열을 역순으로 탐색
					char bombChar = bombCharArr[j];
					if(!nowStack.isEmpty() && nowStack.peek() == bombChar) {
						tempStack.push(nowStack.pop());
					}
					else {
						isEquals = false;
						break;
					}
				}
				
				if(!isEquals) { // 결국 bomb이 들어있지 않았다면
					while(!tempStack.isEmpty()) {	// tempStack에 저장된 값들 전부 꺼내서 다시 집어넣기
						nowStack.push(tempStack.pop());
					}
				} else {
					tempStack.clear();	// 비우기
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!nowStack.isEmpty()) {
			sb.append(nowStack.pop());
		}
		if(sb.length() == 0) 
			System.out.println("FRULA");
		else
			System.out.println(sb.reverse().toString());
	}
}