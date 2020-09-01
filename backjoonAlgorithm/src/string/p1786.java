package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// ã�� ����
// ���ڿ� + KMP �˰��� (���ڿ� Ž��) 
public class p1786 {
	static List<Integer> list;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String pattern = br.readLine();
		list = new ArrayList<Integer>();
		StringBuilder sb = new StringBuilder();
		
		// Pi �迭 ���ϱ� (ã������ ���ڿ��� ���λ�� ���̻� üũ)
		int[] pi = getPi(pattern);
		kmp(pi, str, pattern);
		
		// ���� ����� ���� ��������� ���� �۾� 
		for(int ele : list) {
			sb.append(ele).append(" ");
		}
		
		System.out.println(list.size());
		System.out.println(sb.toString());
	}

	// KMP �˰��� ����Ͽ� ���ڿ� Ž���ϱ� 
	static void kmp(int[] pi, String str, String pattern) {
		int j = 0;
		int strLen = str.length();
		int patternLen = pattern.length();
		
		for(int i = 0; i < strLen; i++) {
			while(j > 0 && str.charAt(i) != pattern.charAt(j)) { // �ٸ��� ���Ҵ� ���� ���̻�� �ٷ� �ǳʶٱ� 
				j = pi[j - 1];
			}
			
			if(str.charAt(i) == pattern.charAt(j)) { // �ε����� ���� ���� �� ���� ����.
				if(j + 1 == patternLen) { // pattern ���ڿ� ���ΰ� ���ٸ�
					list.add(i - patternLen + 2); // ��ü ���ڿ� �� ���� ���ڿ��� ���� ���ڿ��� ���� �ε����� ���ؾ� �ϹǷ�(�Դٰ� 1���� ������)
					j = pi[j]; // �ʱ�ȭ�� ������� ��. �ڱ� �ڽ��� ������ 0�̶� 0���� ���� �����ص� �Ǳ� �ҵ� 
				}
				else { // �ٷ� ������° ���ڸ� ������ ���ؾ� �ϹǷ� 
					j++;
				}
			}
		}
	}
	
	static int[] getPi(String str) {
		int j = 0; // ���λ� ���� �ε��� 
		int n = str.length();
		int[] pi = new int[n];
		
		for(int i = 1; i < n; i++) { // ���̻� ���� �ε��� 
			while(j > 0 && str.charAt(j) != str.charAt(i)) { // j > 0 �� ������ �ּ� �� ���ں��� ���ϴ� ���� �¾�
				j = pi[j - 1]; // �ٸ��� ������ ���ڿ����� ���λ� - ���̻� ���� �ִ� ��� �̵���Ų��.
			}
			
			if(str.charAt(j) == str.charAt(i)) { // ������ 
				pi[i] = ++j;
			}
		}
		
		return pi;
	}
}
