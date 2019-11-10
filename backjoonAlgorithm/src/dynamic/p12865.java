package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 평범한 배낭
// 동적 프로그래밍
// knapsack 알고리즘 사용ㅍ
public class p12865 {
	static int[][] items;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int bagWeight = Integer.valueOf(st.nextToken());
		items = new int[n + 1][2];	// n : 1 ~ n번째 인덱스,  (n, 0) : weight,  (n, 1) : value
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
				if(items[i][0] > j) {	// 만약 W(i) > bagWeight이면
					opt[i][j] = opt[i - 1][j];
				}
				else {
					// 즉, 해당 인덱스의 opt 값은 i - 1 인덱스의 opt값(해당 아이템을 넣지 않았을 때의 최댓값)과 해당 아이템을 넣었을 때의 값
					// 과 비교해서 큰 값이 된다.
					opt[i][j] = Math.max(opt[i - 1][j], opt[i - 1][j - items[i][0]] + items[i][1]);
				}
				max = Math.max(max, opt[i][j]);
			}
		}
		return max;
	}
}
