package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 동전 0
// 그리디 알고리즘
public class p11047 {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		int[] coins = new int[n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.valueOf(st.nextToken());
			coins[i] = num;
		}
		
		int count = 0;
		// 동전 탐색
		for(int i = n - 1; i >= 0; i--) {
			if(k < coins[i]) {
				continue;
			}
			if(k == coins[i]) {
				count++;
				break;
			}
			count += k / coins[i];
			k %= coins[i];
		}
		
		System.out.println(count);
	}
}