package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// ���� �� ���ڿ�
// ���ڿ� + KMP �˰���
public class p3033 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		String str = br.readLine();
		int result;
		// �ش� ���ڿ��� �κ� ���ڿ����� pi Ž���ϱ�(���ڿ� ���� �ִ� ��)
		for(int i = 0; i < n; i++) {
			String subStr = str.substring(i, n);
			result = Math.max(result, getPi(subStr, n));
		}
		
		System.out.println(result);
	}
	
	public static int getPi(String str, int n) {
		int j = 0;
		int max = 0;
		int[] pi = new int[n];
		
		
		for(int i = 1; i < n; i++) {
			// ���� �� ���̻� �� ���ڿ� �ε�
			while(j > 0 && str.charAt(i) != str.charAt(j)) {
				j = pi[n - i];
			}
			
			if(str.charAt(i) == str.charAt(j)) {
				pi[i] = ++j;
				max = Math.max(max, pi[i]);
			}
		}
		
		return max;
	}
}
