package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// ���ڿ� ó��
// ũ�ξ�Ƽ�� ���ĺ�
// ����?
public class p2941 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String str = st.nextToken();
		Stack stack = new Stack();
		int count = 0;
		int len = str.length();
		for(int i = len - 1; i >= 0; i--) {	// ���ڿ� �� ������ ����
			count++;
			char ch = str.charAt(i);
			if(i == 0)
				break;
			switch(ch) {
			case '=':
				if(str.charAt(i - 1) == 'c' || str.charAt(i - 1) == 's') {
					i--;	// ���ĺ� �ϳ��� ġ�Ƿ� �ε��� 1�� �� ���ҽ�Ŵ
				}
				else if(str.charAt(i - 1) == 'z') {
					i--;
					if(i >= 1) {
						if(str.charAt(i - 1) == 'd') {
							i--;
						}
					}
				}
				break;
			case '-':
				if(str.charAt(i - 1) == 'c' || str.charAt(i - 1) == 'd') {
					i--;	// ���ĺ� �ϳ��� ġ�Ƿ� �ε��� 1�� �� ���ҽ�Ŵ
				}
				break;
			case 'j':
				if(str.charAt(i - 1) == 'l' || str.charAt(i - 1) == 'n') {
					i--;	// ���ĺ� �ϳ��� ġ�Ƿ� �ε��� 1�� �� ���ҽ�Ŵ
				}
				break;
				default:
					break;
			}
		}
		System.out.println(count);
	}
}
