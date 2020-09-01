package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Cubeditor
// ���ڿ� ó�� ����
// KMP �˰��� 
public class p1701 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int len = str.length();
		int result = 0;
		
		// ��� �κй��ڿ� Ž��
		for(int i = 0; i < len; i++) {
			String subStr = str.substring(i, len);
			result = Math.max(result, getMaxBubunLength(subStr));
		}
		
		System.out.print(result);
	}
	
	// KMP �˰��� ��� 
	// �ش� ���ڿ� ���� �����ϴ� �κ� ���ڿ� �� ���λ�� ���̻簡 ���� ���ڿ��� �ִ� ���� ���ϱ�.
	// ������ pi�迭�� �����Ͽ� ���ڿ� Ž���� KMP �˰������� ���ȴ�.
	static int getMaxBubunLength(String str) {
		int j = 0; // j : ����(���λ�) �� ���ڿ� �ε��� 
		int n = str.length(), max = 0; 
		int pi[] = new int[n];
		
		for(int i = 1; i < n; i++) { // i : ����(���̻�) �� ���ڿ� �ε��� 
			while(j > 0 && str.charAt(i) != str.charAt(j)) {
				j = pi[j - 1];
			}
			
			if(str.charAt(i) == str.charAt(j)) {
				pi[i] = ++j;
				max = Math.max(max, pi[i]);
			}
		}
		
		return max;
	}
}
