package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

// 이친수
// 피보나치와 일치하지않나
public class p2193 {
	static BigInteger[][] result;	// 이친수의 개수
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int n = Integer.parseInt(br.readLine());
			if(n < 1 || n > 90)
				System.exit(0);
			result = new BigInteger[n + 1][2];
			BigInteger count = getCount(n);
			System.out.println(count);
		} catch (NumberFormatException e) {
			System.exit(0);
		} catch (IOException e) {
			System.exit(0);
		}
	}
	
	private static BigInteger getCount(int n) {
		if(n == 1)
			return BigInteger.ONE;
		if(n == 2)
			return BigInteger.ONE;
		
		result[1][0] = BigInteger.ZERO;
		result[1][1] = BigInteger.ONE;	// n이 1이면 1 하나만 이친수
		result[2][0] = BigInteger.ONE;	// n이 2이면 10 하나만 이친수
		result[2][1] = BigInteger.ZERO;
		for(int i = 3; i <= n; i++) {
			// 끝이 0인 경우 다음 자리는 0 또는 1이 올 수 있다. 끝이 1인 경우 다음 자리는 0만 가능하다.
			result[i][0] = result[i - 1][0].add(result[i - 1][1]);	
			result[i][1] = result[i - 1][0];
		}
		return result[n][0].add(result[n][1]);
	}
}
