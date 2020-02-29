package anything;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// �Ӹ���� ����
// ���ڿ� ó�� �з��� ����. --> DP(���� ���α׷���) ����ؾ���
// ���ڿ� ���غ���
public class p10942 {
	static int[] arr;
	static boolean[][] dp;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		arr = new int[n + 1];	// �Է¹޴� �ε����� 1���� �����ϹǷ�
		dp = new boolean[n + 1][n + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			int ele = Integer.valueOf(st.nextToken());
			arr[i] = ele;
		}
		
		st = new StringTokenizer(br.readLine());
		int tc = Integer.valueOf(st.nextToken());
		StringBuilder sb = new StringBuilder();
		getDP(n);
		while(tc --> 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.valueOf(st.nextToken());
			int e = Integer.valueOf(st.nextToken());
//			boolean result = palindrome(s, e, n);
			
			boolean result = dp[s][e];
			
			if(result) {
				sb.append(1);
			}
			else {
				sb.append(0);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	// �⺻���� Ǯ�� ���
	private static boolean palindrome(int s, int e, int n) {
		int mid = (s + e) / 2;
		// i�� ���ʿ��� ������ ����, j�� �����ʿ��� ���� ����
		for(int i = s; i <= mid; i++) {
			int j = s + e - i;
			int left = arr[i];
			int right = arr[j];
			if(left != right) {
				return false;
			}
		}
		return true;
	}

	// DP�� Ȱ���� Ǯ�� ���
	private static void getDP(int n) {
		// S ~ E�� ���̰� 1�� ��(��, �� ���� ���� �������� ��)
		// ������ �Ӹ����
		for(int i = 1; i <= n; i++) {
			dp[i][i] = true;
		}
		
		// S ~ E�� ���̰� 2�� ��(��, �� ���� ���� �������� ��)
		for(int i = 1; i <= n - 1; i++) {
			if(arr[i] == arr[i + 1]) {	// �� ���� ���ƾ��� �Ӹ����
				dp[i][i + 1] = true;
			}
			else {
				dp[i][i + 1] = false;
			}
		}
		// S ~ E�� ���̰� 3 �̻��� ��
		// 1) �� ���� ���ڰ� ����, 2) ���� ���� ���ڵ��� �Ӹ�����̸� �� ������ ���ڵ鵵 �Ӹ�����̴�.
		for(int i = 3; i <= n; i++) {	// i�� S ~ E�� ����. �ּҰ� 3�̹Ƿ� 3���� ����. = �� �ε���
			for(int j = 1; j <= n - i + 1; j++) { // i ��ŭ�� ���̸� ���� �Ӹ������ ã�� ���� ���� �ε���.
				int k = j + i - 1;
				if(arr[j] == arr[k]) {	// 1)�� ����.
					if(dp[j + 1][k - 1]) {	// 2)�� ����.
						dp[j][k] = true;
					}
				}
			}
		}
	}
}
