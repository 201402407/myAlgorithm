package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ���� �� ����
// ���� �� �κп����� �̷������� �������
// �ִ��� �״�� ��� ���ϰ�, ������ ���� ������ �Ǹ� ���� �����ؼ� ���ϱ�
// �ֳ��ϸ�, a + b + ... n ���� ������ �� ����� ���� ������ �ش� ���� ������ �۱� ����.
public class p1912_bestAnswer {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int[] result = new int[n];
		st = new StringTokenizer(br.readLine());
		int max = Integer.MIN_VALUE;
		int sum = 0;
		for(int i = 0; i < n; i++) {
			result[i] = Integer.valueOf(st.nextToken());
			sum += result[i];
			max = Math.max(max, sum);
			if(sum < 0) {	// ������ ���� 0���� �ʱ�ȭ. ���� �� ° �ε������� ����
				sum = 0;
			}
		}
		System.out.println(max);
	}
}
