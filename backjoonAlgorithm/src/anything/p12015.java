package anything;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 가장 긴 증가하는 부분 수열 2
// LIS + 이분탐색 
public class p12015 {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		int[] arr = new int[n];
        // 처음 값 설정 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
        // 입력 받자마자 계산하기
		for(int i = 0; i < n; i++) {
			int ele = Integer.valueOf(st.nextToken());
			arr[i] = ele;
		}
		
		System.out.println(binarySearch(arr, n));
	}
	
	public static int binarySearch(int[] arr, int size) {
		int[] dp = new int[size + 1];	// 가장 긴 증가하는 부분 수열. 가장 뒤에 있는 값은 부분 수열 중 가장 최댓값 
		int dpLen = 0;
		dp[dpLen++] = arr[0];
		
		for(int i = 1; i < size; i++) {
			if(dp[dpLen - 1] < arr[i]) {	// 가장 긴 증가하는 부분순열의 최댓값보다 큰 경우 뒤에 삽입 
				dp[dpLen++] = arr[i];
			}
			else {
				// DP배열 이분탐색
				int start = 0;
				int end = dpLen;
				while(start <= end) {	
					int mid = (start + end) / 2;
					int midDpEle = dp[mid];
					
					if(midDpEle < arr[i]) {
						start = mid + 1;
					}
					else if(midDpEle >= arr[i]) {
						end = mid - 1;	
					}
				}
				
				// start < end 일 경우 start + 1, start <= end 일 경우 start 
				dp[start] = arr[i];
			}
		}
		
		return dpLen;
	}
}
