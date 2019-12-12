package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// knapsack 0-1 �˰���
// ����ġ�� ����
public class p14728 {
	static int[][] items;
	static int[][] opt;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int t = Integer.valueOf(st.nextToken());
		
		items = new int[n + 1][2];	// 1 ~ n��.. [0] : �ð� .. [1] : ����(����) 
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.valueOf(st.nextToken());
			int s = Integer.valueOf(st.nextToken());
			items[i][0] = k;
			items[i][1] = s;
		}
		opt = new int[n + 1][t + 1];
		getOPT(n, t);
		int result = searchOPT(n, t);
		System.out.println(result);
	}
	
	// ���� ���α׷���
	// Bottop-Up ���
	// [0] : �ð�(x��), [1] : ����(y��)
	// if i == 0 -> 0
	// if Wi > w -> opt(i-1, w)
	// otherWise max{opt(i - 1, w), Vi(���⼭�� ����) + opt(i - 1, w - Wi)}
	public static void getOPT(int n, int t) {
		for(int i = 1; i <= n; i++) {
			for(int w = 0; w <= t; w++) {
				if(items[i][0] > w) {
					opt[i][w] = opt[i - 1][w];
				}
				else {
					opt[i][w] = Math.max(opt[i - 1][w], opt[i - 1][w - items[i][0]] + items[i][1]);
				}
			}
		}
	}
	
	public static int searchOPT(int n, int t) {
		int result = 0;
		for(int i = 1; i <= n; i++) {
			for(int w = 0; w <= t; w++) {
				result = Math.max(result, opt[i][w]);
			}
		}
		return result;
	}
}
