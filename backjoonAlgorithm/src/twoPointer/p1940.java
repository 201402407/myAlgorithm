package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 주몽
// 투포인터
public class p1940 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		int m = Integer.valueOf(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] armors = new int[n];
		for(int i = 0; i < n; i++) {
			armors[i] = Integer.valueOf(st.nextToken());
		}
		
		// 오름차순 정렬
		Arrays.sort(armors);
		
		// 투포인터
		int start = 0;
		int end = n - 1;
		int count = 0;
		while(start < end) {
			int sum = armors[start] + armors[end];
			if(sum < m) {
				start++;
			}
			else if(sum > m) {
				end--;
			}
			else {	// sum == m
				count++;
				start++;
			}
		}
		
		System.out.println(count);
	}
}
