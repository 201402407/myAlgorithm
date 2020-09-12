package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// ���ڿ� ����
// ���ڿ� ����
public class p4354 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		StringBuilder sb = new StringBuilder();
		while(!(str = br.readLine()).equals(".")) {
			int len = str.length();
			int max = 0;
			
//			for(int i = 0; i < len; i++) {
//				String subStr = str.substring(i, len);
//				max = Math.max(max, getMaxKMP(subStr));
//			}
			
//			max = max == 0 ? 1 : max;	//pi�� �ִ��� 0�̶�, ���� �ϳ� = ����^1 �̹Ƿ�, ������ 1�� ���´�.
			max = getMaxKMP(str);
			sb.append(max).append("\n");
		}
		
		System.out.print(sb.toString());
	}
	
	// kmp�˰������� pi�迭�� �ִ� ���ϱ� 
	static int getMaxKMP(String str) {
		int j = 0;
		int max = 0;
		int len = str.length();
		int[] pi = new int[len];
		
		for(int i = 1; i < len; i++) {
			while(j > 0 && str.charAt(i) != str.charAt(j)) {
				j = pi[j - 1];
			}
			if(str.charAt(i) == str.charAt(j)) {
				pi[i] = ++j;
				
//				max = Math.max(max, pi[i]);
			}
		}
		
		// len - pi[len - 1] �� �ᱹ ���ڿ����� ���� ū ������ ���� �� �ִ� ���� ���� ���ڿ��� �ȴ�.
		// �׷���, ���������� ���ϱ� ���� len / (len - pi[len - 1]) �� �ϸ� �� �� �ִ�.
		// ex. ababab -> len = 6, pi[len - 1] = 4, len - pi[len - 1] = 2 (ab���� �� �� �ִ�.)
		// 6 / 2 = 3 (ab�� 3�� �����ϸ� �ȴ�.)
		// �ٵ� ����, ababa�� ���� ���ڿ��� ���̰� Ȧ����, pi ���� 1���� ū ���(�̰� 3) ������������� Ȯ���ؾ��Ѵ�.
		// ���ڿ��� ������ Ȧ���ε� ������ ���� �����ϱ�! (aaaaa�� ������ �ǰ���)
		return len % (len - pi[len - 1]) != 0 ? 1 : len / (len - pi[len - 1]);
	}
}
