package anything;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 팰린드롬 문제
// 문자열 처리 분류와 유사. --> DP(동적 프로그래밍) 사용해야함
// 문자열 비교해보기
public class p10942 {
	static int[] arr;
	static boolean[][] dp;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		arr = new int[n + 1];	// 입력받는 인덱스가 1부터 시작하므로
		dp = new boolean[n + 1][n + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			int ele = Integer.valueOf(st.nextToken());
			arr[i] = ele;
		}
		
		st = new StringTokenizer(br.readLine());
		int tc = Integer.valueOf(st.nextToken());
		StringBuilder sb = new StringBuilder();
		getDP(n);
		while(tc --> 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.valueOf(st.nextToken());
			int e = Integer.valueOf(st.nextToken());
//			boolean result = palindrome(s, e, n);
			
			boolean result = dp[s][e];
			
			if(result) {
				sb.append(1);
			}
			else {
				sb.append(0);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	// 기본적인 풀이 방법
	private static boolean palindrome(int s, int e, int n) {
		int mid = (s + e) / 2;
		// i는 왼쪽에서 오른쪽 방향, j는 오른쪽에서 왼쪽 방향
		for(int i = s; i <= mid; i++) {
			int j = s + e - i;
			int left = arr[i];
			int right = arr[j];
			if(left != right) {
				return false;
			}
		}
		return true;
	}

	// DP를 활용한 풀이 방법
	private static void getDP(int n) {
		// S ~ E의 길이가 1일 때(즉, 한 개의 수만 선택했을 때)
		// 무조건 팰린드롬
		for(int i = 1; i <= n; i++) {
			dp[i][i] = true;
		}
		
		// S ~ E의 길이가 2일 때(즉, 두 개의 수만 선택했을 때)
		for(int i = 1; i <= n - 1; i++) {
			if(arr[i] == arr[i + 1]) {	// 두 수가 같아야지 팰린드롬
				dp[i][i + 1] = true;
			}
			else {
				dp[i][i + 1] = false;
			}
		}
		// S ~ E의 길이가 3 이상일 때
		// 1) 양 끝의 숫자가 같고, 2) 남은 안의 숫자들이 팰린드롬이면 이 길이의 숫자들도 팰린드롬이다.
		for(int i = 3; i <= n; i++) {	// i는 S ~ E의 길이. 최소가 3이므로 3부터 시작. = 끝 인덱스
			for(int j = 1; j <= n - i + 1; j++) { // i 만큼의 길이를 보고 팰린드롬을 찾기 위한 시작 인덱스.
				int k = j + i - 1;
				if(arr[j] == arr[k]) {	// 1)의 조건.
					if(dp[j + 1][k - 1]) {	// 2)의 조건.
						dp[j][k] = true;
					}
				}
			}
		}
	}
}
