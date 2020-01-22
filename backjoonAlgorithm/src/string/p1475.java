package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ���ڿ� ó��
// �� ��ȣ ����
// �迭 ����غ��� -> 6�� 9 �����ϱ�. 6�� 9�� �� �� �־ �� ��Ʈ�� 6�� 9 �ΰ� ����
public class p1475 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String num = st.nextToken();
		int[] counts = new int[10];
		for(int i = 0; i < num.length(); i++) {
			char ch = num.charAt(i);
			int index = Character.getNumericValue(ch);
			counts[index]++;
		}
		int max = 0;
		int temp = counts[9] + counts[6];
		counts[6] = (temp / 2) + (temp % 2);
		for(int i = 0; i < 9; i++) {	// 6�̳� 9�� �����Ƿ� ������ 9�� ���� ī��Ʈ�� �κ��� 6�� ���Ѵ�.
			max = Math.max(max, counts[i]);
		}
		System.out.println(max);
	}
}
