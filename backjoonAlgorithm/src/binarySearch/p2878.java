package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// ����Ž�� ? �׸��� ?
// ĵ��ĵ��
public class p2878 {
	static long[] wants;
	static final long REMAIN = (long) Math.pow(2, 64); 
	static long lack;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		wants = new long[n];
		
		long sum = 0;
		for(int i = 0; i < n; i++) {
			long num = Long.parseLong(br.readLine());
			wants[i] = num;
			sum += num;
		}
		
		lack = sum - m;
		
		long result = 0;
		Arrays.sort(wants);
		
		for(int i = 0; i < n; i++) {
			// �䱸�ϴ� ���� ������ŭ ��������? �ƴϸ� ���� ������̶� �Ȱ��� ��������?
			// �� �� �� ���� ���� �� �ִ� ���� ���� = �� �� �� ���� ���� ���޴´�.
			long removeCount = Math.min(wants[i], lack / (n - i));
			lack -= removeCount;
			result += removeCount * removeCount;	// Math.pow() �� int �����̶� �ȵ�. 
			result %= REMAIN;
		}
		
		System.out.println(result);
	}
}
