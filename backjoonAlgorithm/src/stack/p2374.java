package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 같은 수로 만들기
// 스택?
public class p2374 {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		
		Stack<Integer> mainStack = new Stack<>();
		
		int num = 0;	// 자연수니까 0은 없음
		// 입력받기
		for(int i = 0; i < n; i++) {
			int nowNum = Integer.valueOf(br.readLine());
			if(num != nowNum) {
				mainStack.push(nowNum);
				num = nowNum;
			}
		}
		
		int count = 0;
		int start = mainStack.pop();
		boolean isAllChecked = true;
		Stack<Integer> tempStack = new Stack<>();
		while(isAllChecked) {
			isAllChecked = false;
			while(!mainStack.isEmpty()) {
				int peek = mainStack.peek();
				if(peek > start) {
					count += peek - start;
					isAllChecked = true;
				}
				else if(peek == start) {
				}
				else {
					tempStack.push(start);
				}
				
				start = mainStack.pop();
			}
			
			// 더 이상 내림차순은 없고, 전부 오름차순이다.
//			if(!isChecked) {
//				isAllChecked = false;
//			}
			
			mainStack.push(start);
			moveStack(tempStack, mainStack);
			start = mainStack.peek();
		}
		
		
		int min = mainStack.firstElement();
		int max = mainStack.peek();
		count += max - min;
		System.out.println(count);
	}
	
	static void moveStack(Stack<Integer> srcStack, Stack<Integer> desStack) {
		while(!srcStack.isEmpty()) {
			desStack.push(srcStack.pop());
		}
	}
}
