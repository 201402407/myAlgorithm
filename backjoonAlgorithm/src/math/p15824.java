package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 너 봄에는 캡사이신이 맛있단다.
// 수학(조합) 문제
public class p15824 {
	static long[] arr;
	static long[] pows;	// pow 계산을 DP로 해두기 위함. n의 값을 고려했을 떄, %1000000007 까지 작업치기 위함 
	static long result;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		arr = new long[n + 1];
		pows = new long[n + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		pows[0] = 1;
		for(int i = 1; i <= n; i++) {
			arr[i] = Long.valueOf(st.nextToken());
			pows[i] = (pows[i - 1] * 2) % 1000000007;
		}
		
		// 정렬
		Arrays.parallelSort(arr);
		
		// 계산
		// 최대가 되는 경우의 모든 최대값 - 최소가 되는 경우의 모든 최소값 = 2^i-1 * arr[i] - (2^n-i * arr[i]) = (2^i-1 - 2^n-i) * arr[i]
		for(int i = 1; i <= n; i++) {
			result += (pows[i - 1] - pows[n - i]) * arr[i];
			result %= 1000000007;
		}
		
		System.out.println(result);
	}
}
