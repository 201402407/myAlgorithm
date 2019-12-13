package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 기업 투자 문제
// 0-1 knapsack 알고리즘
// 동적 프로그래밍 -> 분할이 안되므로
public class p2662 {
	static int[][] company;
	static int[][] opt;
	static int[][] investment;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		company = new int[m + 1][n + 1];	// y : 돈 단위, x : 기업 순서
		opt = new int[m + 1][n + 1];	// y : 기업 수, x : 돈 단위
		investment = new int[m + 1][n + 1];	// 해당 지점에 어느 기업에 투자해서 이윤남겼는지 적음
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int index = Integer.valueOf(st.nextToken());
			for(int j = 1; j <= m; j++) {
				int value = Integer.valueOf(st.nextToken());
				company[j][index] = value;
			}
		}
		
		getOPT(n, m);
		getInvestmentCompany(n, m);
		
	}
	
	// OPT 구하는 함수
	public static void getOPT(int n, int m) {
		for(int i = 1; i <= m; i++) {	// A회사, A와 B 회사, A와 B와 C회사 ---
			for(int j = 1; j <= n; j++) {	// 1만원부터 n만원까지
				opt[i][j] = opt[i - 1][j];	// 초기화
//				investment[i][j] = 0;	// 초기 설정인데 어차피 0으로 설정되어 있으므로 제거
				// 해당 j 만원부터 0원까지 반복. 최대 j만원일 때, j - 1만원 과  i회사의 1만원을 선택한 경우와 i - 1회사의 j만원일 떄와 비교
				for(int k = j; k >= 0; k--) {	
					// 해당 i 회사의 j만원을 투자한 이윤과 i - 1회사까지 중 j만원을 투자하기 전 이윤과 더하기.
					// 그래서 나온 값과  i 회사에 아예 투자를 안했을 경우와 최대 비교.
					// 이를 opt[i][j]와 비교해서 큰 값으로 변경.
					int betterValue = company[i][k] + opt[i - 1][j - k];
					if(opt[i][j] < betterValue) {
						opt[i][j] = betterValue;
						investment[i][j] = k;	// 초기 설정. 
					}
				}
			}
		}
		int result = 0;
		for(int i = 1; i <= m; i++) {
			result = Math.max(result, opt[i][n]);
		}
		System.out.println(result);
	}
	
	// 어느 회사에 얼마 투자했는지 파악하는 함수
	public static void getInvestmentCompany(int n, int m) {
		int[] result = new int[m + 1];
		StringBuilder sb = new StringBuilder();
		for(int i = m; i >= 1; i--) {
			if(investment[i][n] != 0 && n >= 0) {
				result[i] = investment[i][n];
				n -= investment[i][n];
			}
		}
		
		for(int i = 1; i <= m; i++) {
			sb.append(result[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
}
