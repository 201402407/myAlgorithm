package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

// ��ģ��
// �Ǻ���ġ�� ��ġ�����ʳ�
public class p2193 {
	static BigInteger[][] result;	// ��ģ���� ����
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int n = Integer.parseInt(br.readLine());
			if(n < 1 || n > 90)
				System.exit(0);
			result = new BigInteger[n + 1][2];
			BigInteger count = getCount(n);
			System.out.println(count);
		} catch (NumberFormatException e) {
			System.exit(0);
		} catch (IOException e) {
			System.exit(0);
		}
	}
	
	private static BigInteger getCount(int n) {
		if(n == 1)
			return BigInteger.ONE;
		if(n == 2)
			return BigInteger.ONE;
		
		result[1][0] = BigInteger.ZERO;
		result[1][1] = BigInteger.ONE;	// n�� 1�̸� 1 �ϳ��� ��ģ��
		result[2][0] = BigInteger.ONE;	// n�� 2�̸� 10 �ϳ��� ��ģ��
		result[2][1] = BigInteger.ZERO;
		for(int i = 3; i <= n; i++) {
			// ���� 0�� ��� ���� �ڸ��� 0 �Ǵ� 1�� �� �� �ִ�. ���� 1�� ��� ���� �ڸ��� 0�� �����ϴ�.
			result[i][0] = result[i - 1][0].add(result[i - 1][1]);	
			result[i][1] = result[i - 1][0];
		}
		return result[n][0].add(result[n][1]);
	}
}
