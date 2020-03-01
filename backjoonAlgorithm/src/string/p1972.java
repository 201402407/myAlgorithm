package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// ���� ���ڿ� ����
// ���ڿ� ó��
// **** �ٸ� ���(�� ����� �� ����)
// ==> HashSet�� ����ؼ� �ߺ� üũ�ϱ� -> �ߺ��� ���ڿ��� �ϳ��� �����Ƿ� ���� �ϳ��� ���� �� ���� counting�ؼ� hashSet�� size�� count ���ϸ� ��.
public class p1972 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String inputStr;	// �Է¹޴� ��
		StringBuilder sb = new StringBuilder();
		while(!(inputStr = br.readLine()).equals("*")) {
			ArrayList<String> list = new ArrayList<String>();	// �� �� �� ���� �� �ִ� ���ڸ� ��� ArrayList
			int len = inputStr.length();	// �Է¹��� ���ڿ��� ����
			boolean isSurprise = true;	// ���� ���ڿ����� �ƴ��� �Ǻ��ϴ� boolean
			
			for(int i = 1; i < len; i++) {	// 0 ~ n - 2�� �� ��Ÿ���� ��
				list.clear(); // �ʱ�ȭ�ؾ��� ���� �� ���� ����
				for(int j = 0; j < len - i; j++) {	// ���� �ε���
					String str = Character.toString(inputStr.charAt(j)) + Character.toString(inputStr.charAt(j + i));
//					String str = inputStr.charAt(j) + "" + inputStr.charAt(j + i);
					list.add(str);
				}
				// ���� ���ڰ� �ִ��� Ȯ���ϱ� ���� ���� �� �ش��ϴ� �ε����� ���ڿ� �ٷ� ���� �ε����� ���ڸ� ���Ѵ�
				Collections.sort(list);	
				for(int k = 0; k < list.size() - 1; k++) {
					if(list.get(k).equals(list.get(k + 1))) {
						isSurprise = false;
						break;
					}
				}
				if(!isSurprise) 
					break;	
			}
			
			if(isSurprise) {
				sb.append(inputStr).append(" is surprising.");
			}
			else {
				sb.append(inputStr).append(" is NOT surprising.");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
