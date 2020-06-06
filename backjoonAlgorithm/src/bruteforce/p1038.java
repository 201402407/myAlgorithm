package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 감소하는 수
// 브루트 포스
public class p1038 {
	static int dp[][];
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		if(n < 10) {	// 한 자리 수
			System.out.println(n);
		}
		else {
			dp = new int[11][10];
			Arrays.fill(dp[1], 1);
			
			for(int i = 2; i < dp.length; i++) {
				for(int j = 1; j < dp[1].length; j++) {
					dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
				}
			}
			
			String result = findNumber(n);
			result = result == null ? "-1" : result;
			System.out.println(result);
		}
	}
	
	// n번 째에 해당하는 숫자 찾기
	static String findNumber(int n) {
		int sum = 0;
		for(int i = 1; i < dp.length; i++) {
			for(int j = 1; j < dp[1].length; j++) {
				if(sum + dp[i][j] >= n) {
					return String.valueOf(j) + getDecreaseNumber(i - 1, n - sum);
				}
				sum += dp[i][j];
			}
		}
		
		return null;
	}
	
	// 재귀함수
	// 한 자리 씩 감소하면서 자리에 맞는 숫자 찾기
	static String getDecreaseNumber(int k, int sum) {
		if(sum == 0) {
			return "0";
		}
		
		int nowSum = 0;
		for(int j = 0; j < dp[1].length; j++) {
			if(nowSum + dp[k][j] >= sum) {
				if(k == 1) {
					return String.valueOf(j);
				}
				return String.valueOf(j) + getDecreaseNumber(k - 1, sum - nowSum);
			}
			else {
				nowSum += dp[k][j];
			}
		}
		
		return null;
	}
}
