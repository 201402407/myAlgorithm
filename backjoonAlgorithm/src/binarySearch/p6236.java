package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이진 탐색
// 용돈 관리
public class p6236 {
	static int[] pays;
	static int maxHigh;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		pays = new int[n];
		
		for(int i = 0; i < n; i++) {
			pays[i] = Integer.parseInt(br.readLine());
			maxHigh = Math.max(maxHigh, pays[i]);
		}
		
		// 이진탐색 시작
		int low = 1;
		int high = maxHigh * m;
		
		while(low <= high) {
			int mid = (low + high) / 2;
			int payResult = possiblePay(mid, n, m);
			
			if(payResult == -1) {	// 아예 해당 인출한 돈으로는 지출 불가능
				low = mid + 1;	// 인출 돈 올리기
			}
			
			if(payResult == 0) {	// m 횟수를 초과
				low = mid + 1;	
			}
			
			if(payResult == 1) {	// 정확히 m 횟수
				high = mid - 1;
			}
			
			if(payResult == 2) {	// m 횟수보다 작음
				high = mid - 1;
			}
		}
		
		System.out.println(low);
	}
	
	// M 횟수 내에 지불 가능한지 판단하는 함수
	// 0 : M 횟수를 초과
	// 1 : 정확히 M 횟수
	// 2 : M 횟수보다 작음
	static int possiblePay(int withdraw, int n, int m) {
		int nowMoney = 0;
		int nowCount = 0;
		
		for(int i = 0; i < n; i++) {
			if(withdraw < pays[i]) {	// 인출한 돈보다 지출이 큰 경우 성립 불가능.
				return -1;
			}
			
			if(nowMoney < pays[i]) {
				nowCount++;
				nowMoney = withdraw;
				if(nowCount > m) {
					return 0;
				}
			}
			
			nowMoney -= pays[i];
		}
		
		return nowCount == m ? 1 : 2;
	}
}
