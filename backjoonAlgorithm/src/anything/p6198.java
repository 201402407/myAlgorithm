package anything;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 옥상 정원 꾸미기
public class p6198 {
	static long[][] dp;
	static int[] dpIndex;
	static int n;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());
		dp = new long[n][2];	// 0 : 바라보는 빌딩 가능 개수 , 1: 막힌 빌딩의 높이
		dpIndex = new int[n];	// 막힌 빌딩의 index
		
		// 입력값 저장
		int[] buildings = new int[n];
		for(int i = 0; i < n; i++) {
			buildings[i] = Integer.valueOf(br.readLine());
		}
		
		dpIndex[n - 1] = -1;	// 가장 끝점에 대한 인덱스 표시
		for(int i = n - 2; i >= 0; i--) {
			int j = i + 1;
			long count = 0;
			boolean isTop = false;
			
			while(true) {
				if(j == -1) {	// 더이상 나보다 높은 빌딩은 없다.
					isTop = true;
					break;
				}
				if(buildings[j] >= buildings[i]) {
					break;
				}
				
				count++;	// j빌딩 한 개 추가
				
				// DP 탐색
				count += dp[j][0];
				j = dpIndex[j];
			}
			dp[i][0] = count;	// i빌딩이 바라볼 수 있는 빌딩 개수
			if(isTop) {
				dpIndex[i] = -1;
			}
			else {
				dp[i][1] = buildings[j];
				dpIndex[i] = j;
			}
		}
		
		long result = 0;
		for(int i = 0; i < n; i++) {
			result += dp[i][0];
		}
		
		System.out.println(result);
	}
}
