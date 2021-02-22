package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 30
// ���ڿ� ����
// �ð����� : 1��
public class p10610 {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String nStr = br.readLine();
		int sum = 0;

		// �ش� ���� ���ġ ����
		char[] charArr = nStr.toCharArray();
		Arrays.sort(charArr);	// �������� ����
		int len = charArr.length;

		StringBuilder sb = new StringBuilder();
		// �������� �����̹Ƿ�, �� �� ���Һ��� �ݴ�� Ž��
		for(int i = len - 1; i >= 0; i--) {
			int num = charArr[i] - '0';
			sum += num;
			sb.append(num);
			
		}
				
		// 30�� ������� �Ǵ��ϱ� ���� ���� ����
		if(charArr[0] != '0' || sum % 3 != 0) {	// ���� 3�� ����� �ƴ϶��
			System.out.println(-1);
			return;
		}
		
		System.out.println(sb.toString());
	}
}
