package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 동적 프로그래밍 2
// 2 x n 타일링 2
// 끝에 새로 채우는 방법은 ||와 =, ㅁ 가 있는데, =와 ㅁ는 i-2에서 2를 곱한 방법이고, ||는 i-1에서 | 하나만 추가한 경우와 같으므로 이 둘을 곱한 값이 정답이다.
public class p11727 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		long[] dp = new long[n + 1];
		dp[1] = 1;
		if(n >= 2) {
			dp[2] = 3;	
		}
		for(int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + (dp[i - 2] * 2);
			dp[i] %= 10007;
		}
		System.out.println(dp[n]);
	}
}
