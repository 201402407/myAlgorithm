package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// ���ڿ� ����
// ���� �˰��� + ���ڿ� ó��
public class p9935 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String bomb = br.readLine();
		int strLen = str.length();
		int bombLen = bomb.length();
		Stack<Character> nowStack = new Stack<Character>();
		Stack<Character> tempStack = new Stack<Character>();
		
		for(int i = 0; i < strLen; i++) {
			char a = str.charAt(i);
			nowStack.push(a);
			if(a == bomb.charAt(bombLen - 1)) { // �� ���ڶ� ������
				tempStack.push(nowStack.pop());
				boolean isEquals = true;
				for(int j = bombLen - 2; j >= 0; j--) {	// ��ź ���ڿ��� �������� Ž��
					char bombChar = bomb.charAt(j);
					if(!nowStack.isEmpty() && nowStack.peek() == bombChar) {
						tempStack.push(nowStack.pop());
					}
					else {
						isEquals = false;
						break;
					}
				}
				
				if(!isEquals) { // �ᱹ bomb�� ������� �ʾҴٸ�
					while(!tempStack.isEmpty()) {	// tempStack�� ����� ���� ���� ������ �ٽ� ����ֱ�
						nowStack.push(tempStack.pop());
					}
				} else {
					tempStack.clear();	// ����
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
