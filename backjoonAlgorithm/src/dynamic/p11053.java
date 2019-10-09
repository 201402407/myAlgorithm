package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ���� �� �����ϴ� �κ� ����
// ���� ���α׷���
public class p11053 {
	static int[] array;
	static int[] node;
	static int[] dp;
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		try {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			array = new int[n + 1];
			dp = new int[n + 1];
			node = new int[n + 1];
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= n; i++) {
				int val = Integer.valueOf(st.nextToken());
				array[i] = val;
			}
			int result = search(n);
			System.out.println(result);
		}
		catch (NumberFormatException e) {
			System.exit(0);
		}
		catch (IOException e) {
			System.exit(0);
		}
	}
	
	static int search(int n) {
		int result = 1;	// ���� �� �����ϴ� �����̴ϱ� �ּҴ� 1�ƴұ�?
		if(n == 1)	// �� ���� �ٷ� ����
			return result;
		dp[1] = 1;
		
		for(int i = 2; i <= n; i++) {
			int count = 0;
			for(int j = i - 1; j >= 1; j--) {
				if(array[i] > array[j]) {
					count = Math.max(count, dp[j]);	
				}
			}
			dp[i] = count + 1;
		}
		
		for(int ele : dp) {
			result = Math.max(result, ele);
		}
		return result;
	}
}

class Number {
	int num;
	int count;
	
	Number(int num, int count) {
		this.num = num;
		this.count = count;
	}
	
	public int getNum() {
		return this.num;
	}
	
	public int getCount() {
		return this.count;
	}
}