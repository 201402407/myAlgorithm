package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 1509번 : 팰린드롬 분할
public class p1509 {
	static String str;
	static char[] strCharArr;
	static boolean[][] palindromes;
	static int[] dp;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		strCharArr = str.toCharArray(); // 사용 인덱스 : 0 ~ n - 1
		int n = str.length();
		palindromes = new boolean[n + 1][n + 1]; // 사용 인덱스 : 1 ~ n
		dp = new int[n + 1]; // 사용 인덱스 : 1 ~ n
		
		// 팰린드롬 설정
		setPalindromes(n);
		palindrome(n);
		System.out.println(dp[n]);
	}
	
	// 팰린드롬
	// DP[i] => Math.min(DP[i], DP[j - 1] + 1)
	// DP[i] => 0 ~ i - 1 번째까지의 문자들 중 팰린드롬 분할 최소 개수
	// j ~ i 인덱스까지 문자들의 분할 최소 개수 : DP
	static void palindrome(int n) {
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= i; j++) {
				if(palindromes[j][i]) {
					// 여기서 dp[i] == 0 인 경우에는 아직 해당 문자열을 탐색한 경우가 없다는 뜻으로 초기 설정이 필요하다.
					if(dp[i] > dp[j - 1] + 1 || dp[i] == 0) {
						dp[i] = dp[j - 1] + 1;
					}
				}
			}
		}
	}
	
	// DP를 활용한 풀이 방법
	private static void setPalindromes(int n) {
		for(int i = 1; i <= n; i++) {
			// S ~ E의 길이가 1일 때(즉, 한 개의 수만 선택했을 때) 무조건 팰린드롬
			palindromes[i][i] = true;
			
			// S ~ E의 길이가 2일 때(즉, 두 개의 수만 선택했을 때)
			if(i < n) {
				if(strCharArr[i - 1] == strCharArr[i]) {
					palindromes[i][i + 1] = true;
					palindromes[i + 1][i] = true;
				}
			}
		}
		
		// S ~ E의 길이가 3 이상일 때
		// 1) 양 끝의 숫자가 같고, 2) 남은 안의 숫자들이 팰린드롬이면 이 길이의 숫자들도 팰린드롬이다.
		for(int i = 3; i <= n; i++) {	// i는 S ~ E의 길이. 최소가 3이므로 3부터 시작. = 끝 인덱스
			for(int j = 1; j < n - i + 1; j++) { // i 만큼의 길이를 보고 팰린드롬을 찾기 위한 시작 인덱스.
				int k = j + i - 1;
				if(strCharArr[j - 1] == strCharArr[k - 1]) {	// 1)의 조건.
					if(palindromes[j + 1][k - 1]) {	// 2)의 조건.
						palindromes[j][k] = true;
						palindromes[k][j] = true;
					}
				}
			}
		}
	}
}