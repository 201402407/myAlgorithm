package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 연속합문제
// 이건 시간복잡도가 O(n^2)이므로 손해

public class p1912 {

	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			int[] result = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				result[i] = Integer.valueOf(st.nextToken());
			}
			int resultSum = Integer.MIN_VALUE;
			for(int a = 0; a < n; a++) {
				int aIndexSum = result[a];
				resultSum = Math.max(resultSum, aIndexSum);
				for(int b = a + 1; b < n; b++) {
					aIndexSum += result[b];
					resultSum = Math.max(resultSum, aIndexSum);
				}
			}
			
			System.out.println(resultSum);
		}
		catch(NumberFormatException e) {
			System.exit(0);
		}
		catch(IOException e) {
			System.exit(0);
		}
	}
}
