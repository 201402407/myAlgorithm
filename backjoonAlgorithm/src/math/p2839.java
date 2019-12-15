package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ���� ��� ����
// ����
public class p2839 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		

		int count = -1;
		int quotient = n / 5;
		int remain = n % 5;
		for(int i = 0; i <= quotient; i++) {
			int realQuo = quotient - i;	// 5�� ���� �򿡼� 5�� i��ŭ�� ������ ���� ��.
			int tempRemain = (i * 5) + remain;		// 5�� i��ŭ ���� ��
			if(tempRemain % 3 == 0) {	// 3���� ���� �� ������
				count = realQuo + (tempRemain / 3);	// ����� �ּ� ����.
				break;
			}
		}
		System.out.println(count);
	}
}
