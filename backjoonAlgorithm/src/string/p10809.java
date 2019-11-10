package string;

import java.util.Arrays;
import java.util.Scanner;

// ���ĺ� ã��
// ���ڿ� ó�� �о� �˰��� ����
// �ƽ�Ű�ڵ� ���
public class p10809 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		
		int len = str.length();
		int[] results = new int[26];	// a ~ z���� 26��.
		Arrays.fill(results, -1);
		for(int i = 0; i < len; i++) {
			char alphabet = str.charAt(i);
			int index = alphabet - 97;
			if(results[index] == -1) {
				results[index] = i;	
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int result : results) {
			sb.append(result).append(" ");
		}
		System.out.println(sb.toString());
	}
}
