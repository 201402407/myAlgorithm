package silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 코스튬 파티
// 투포인터
public class p6159 {
	static int n, s;	// s: 코스튬 사이즈
	static int[] cows;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.valueOf(st.nextToken());
		s = Integer.valueOf(st.nextToken());
		cows = new int[n];
		
		for(int i = 0; i < n; i++) {
			cows[i] = Integer.valueOf(br.readLine());
		}
		
		Arrays.sort(cows);
		int result = twoPointer();
		System.out.println(result);
	}
	
	public static int twoPointer() {
		int count = 0;
		int left = 0;
		int right = n - 1;
		
		while(left < right) {
			int sum = cows[left] + cows[right];
			
			// 합이 무조건 큰 경우 한 칸 오른쪽으로
			if(sum > s) {
				right--;
			}
			else {
				// sum이 s이하인 경우를 전부 찾기
				// 그 다음 left 한 칸 이동
//				while(tLeft < tRight) {
//					int tSum = cows[left] + cows[right];
//					if(tSum <= s) {
//						count++;
//						tRight--;
//					}
//				}
				count += right - left;
				left++;
			}
		}
		
		return count;
	}
}
