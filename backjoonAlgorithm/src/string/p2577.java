package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// ������ ����
// ���ڿ� 
public class p2577 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.valueOf(br.readLine());
		int b = Integer.valueOf(br.readLine());
		int c = Integer.valueOf(br.readLine());
		
		String str = String.valueOf(a*b*c);
		int[] count = new int[10];
		char[] charArr = str.toCharArray();
		for(char ch : charArr) {
			count[ch - '0']++;
		}
		
		for(int ele : count) {
			System.out.println(ele);
		}
	}
}
