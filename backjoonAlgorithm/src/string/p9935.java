package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// ���ڿ� ����
// ���� �˰��� + ���ڿ� ó��
// �� ��° ����� toCharArray()�� �迭 ����� ���� �ϴ� ����� ��µ�, �� ������ �޸𸮵� �Ǵ�.
// �׳� charAt()�� ������
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
			if(a == bombLastChar) { // �� ���ڶ� ������
				tempStack.push(nowStack.pop());
				boolean isEquals = true;
				for(int j = bombLen - 2; j >= 0; j--) {	// ��ź ���ڿ��� �������� Ž��
					char bombChar = bombCharArr[j];
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