package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ����� �賶
// ���� ���α׷���
// knapsack �˰��� ��뤽
public class p12865 {
	static int[][] items;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int bagWeight = Integer.valueOf(st.nextToken());
		items = new int[n + 1][2];	// n : 1 ~ n��° �ε���,  (n, 0) : weight,  (n, 1) : value
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.valueOf(st.nextToken());
			int value = Integer.valueOf(st.nextToken());
			items[i][0] = weight;
			items[i][1] = value;
		}
		
		int result = knapsack(n, bagWeight);
		System.out.println(result);
	}
	
	static int knapsack(int n, int bagWeight) {
		int[][] opt = new int[n + 1][bagWeight + 1];
		int max = 0;
		for(int i = 1; i < opt.length; i++) {
			for(int j = 0; j <= bagWeight; j++) {
				if(items[i][0] > j) {	// ���� W(i) > bagWeight�̸�
					opt[i][j] = opt[i - 1][j];
				}
				else {
					// ��, �ش� �ε����� opt ���� i - 1 �ε����� opt��(�ش� �������� ���� �ʾ��� ���� �ִ�)�� �ش� �������� �־��� ���� ��
					// �� ���ؼ� ū ���� �ȴ�.
					opt[i][j] = Math.max(opt[i - 1][j], opt[i - 1][j - items[i][0]] + items[i][1]);
				}
				max = Math.max(max, opt[i][j]);
			}
		}
		return max;
	}
}
