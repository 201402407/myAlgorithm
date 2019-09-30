package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2xn 타일링
// f(n) = f(n-1) + f(n-2) 인듯
public class p11726 {
	static int[] result = new int[1001];
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		result[1] = 1;
		result[2] = 2;
		for(int i = 3; i <= n; i++) {
			result[i] = (result[i - 1] + result[i - 2]) % 10007;
		}
		System.out.println(result[n]);
	}
}