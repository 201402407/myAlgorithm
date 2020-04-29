package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

// 괄호 추가하기 문제
// 백트래킹?
public class p16637 {
	static char[] mathExp;
	static int n;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.valueOf(br.readLine());
		String str = br.readLine();
		mathExp = str.toCharArray();
		
		// 선택 인덱스 초기 값 : -2. 이유는 처음 나오는 문자의 인덱스는 1이기 때문에
		backTracking(new boolean[n], 1, -2);
		System.out.println(max);
	}
  
	// 백트래킹
	static void backTracking(boolean[] selected, int index, int selectedIndex) {
		if(index >= n) {
			calculate(selected);
		}
		else {
			// 선택 가능할 때
			if(index > selectedIndex + 2) {
				// 선택
				selected[index] = true;
				backTracking(selected, index + 2, index);
				// 선택 X
				selected[index] = false;
				backTracking(selected, index + 2, selectedIndex);
			}
			else {
				backTracking(selected, index + 2, selectedIndex);
			}
		}
	}
	
	// 계산하는 함수
	static void calculate(boolean[] selected) {
		LinkedList<Integer> numStack = new LinkedList<Integer>();
		LinkedList<Character> charStack = new LinkedList<Character>();

		// 괄호 먼저 전부 계산
		for(int i = 0; i < n; i++) {
			char ch = mathExp[i];
			
			if(Character.isDigit(ch)) {
				numStack.offer(Character.getNumericValue(ch));
			}
			else {
				if(selected[i]) {
					int lastNum = numStack.pollLast();
					int nextNum = Character.getNumericValue(mathExp[++i]);
					int result = calculateOfBuho(lastNum, ch, nextNum);
					numStack.offer(result);
				}
				else {
					charStack.offer(ch);
				}
			}
		}
		
		// 왼쪽부터 순차적으로 계산
		while(!charStack.isEmpty() && numStack.size() >= 2) {
			int lastNum = numStack.pollFirst();
			int nextNum = numStack.pollFirst();
			char buho = charStack.pollFirst();
			numStack.push(calculateOfBuho(lastNum, buho, nextNum));
		}
		
		int result = numStack.pop();
		max = Math.max(max, result);
	} 
	
	// 부호 계산
	static int calculateOfBuho(int lastNum, char buho, int nextNum) {
		switch(buho) {
		case '+':
			return lastNum + nextNum;
		case '-':
			return lastNum - nextNum;
		case '*':
			return lastNum * nextNum;
		default:
			return Integer.MAX_VALUE;
		}
	}
}
