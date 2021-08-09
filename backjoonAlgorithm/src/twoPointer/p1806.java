package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부분합
// 투포인터
public class p1806 {
	static int n, s;
	static int[] arr;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.valueOf(st.nextToken());
		s = Integer.valueOf(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			int num = Integer.valueOf(st.nextToken());
			arr[i] = num;
		}
		
//		1. 합이 S가 되기까지 end 포인트를 옮기면서 합을 더하고,
//		while문 시작(end가 N끝까지 올 때까지)
//		2. end 1 이동
//		3. S 이상이 되면 end - start 길이 구하기
//		4. start를 하나씩 앞으로 땡기면서 S 이상이 되는지 체크
//		-> 된다면, 최소 길이값 갱신
//		-> 안된다면, 거기서 끝내고 다음 while문 진행
		
//		1. 합이 S가 되기까지 end 포인트를 옮기면서 합을 더하고,
		int start = 0;
		int end = 0;
		int minLen = Integer.MAX_VALUE;
		int maxSum = arr[end];
		
		while(end < n && start < n) {
			if(maxSum >= s) {
//				System.out.println("START ::: " + start + " , END ::: " + end + " , SUM ::: " + maxSum);
				minLen = Math.min(minLen, end - start + 1);
				int startNum = arr[start++];
				maxSum -= startNum;
				continue;
			}
			
			if(start > end) {
				break;
			}
			if(end == n - 1) {
				break;
			}
			int num = arr[++end];
			maxSum += num;
		}
		
		System.out.println(minLen == Integer.MAX_VALUE ? 0 : minLen);
	}
}
