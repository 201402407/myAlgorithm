package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// ���� ����
// ���ڿ� Ž��(KMP �˰���)
public class p1305 {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		String str = br.readLine();
		
		// KMP �˰������� ���λ� / ���̻� ������ ���ڿ� �ִ� ���� ���ϱ�
		int lastPi = getLastPi(str);
		System.out.println(n - lastPi);
	}
	
	static int getLastPi(String str) {
		int len = str.length();
		int j = 0, max = 0; // ���λ� Ž�� ���� �ε���
		int[] pi = new int[len];
		
		for(int i = 1; i < len; i++) {
			while(j > 0 && str.charAt(j) != str.charAt(i)) {
				j = pi[j - 1];
			}
			
			if(str.charAt(j) == str.charAt(i)) {
				pi[i] = ++j;
//				max = Math.max(max, pi[i]);
			}
		}
		
		return pi[len - 1];
	}
}
