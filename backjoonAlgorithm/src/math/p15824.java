package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// �� ������ ĸ���̽��� ���ִܴ�.
// ����(����) ����
public class p15824 {
	static long[] arr;
	static long[] pows;	// pow ����� DP�� �صα� ����. n�� ���� ������� ��, %1000000007 ���� �۾�ġ�� ���� 
	static long result;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		arr = new long[n + 1];
		pows = new long[n + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		pows[0] = 1;
		for(int i = 1; i <= n; i++) {
			arr[i] = Long.valueOf(st.nextToken());
			pows[i] = (pows[i - 1] * 2) % 1000000007;
		}
		
		// ����
		Arrays.parallelSort(arr);
		
		// ���
		// �ִ밡 �Ǵ� ����� ��� �ִ밪 - �ּҰ� �Ǵ� ����� ��� �ּҰ� = 2^i-1 * arr[i] - (2^n-i * arr[i]) = (2^i-1 - 2^n-i) * arr[i]
		for(int i = 1; i <= n; i++) {
			result += (pows[i - 1] - pows[n - i]) * arr[i];
			result %= 1000000007;
		}
		
		System.out.println(result);
	}
}
