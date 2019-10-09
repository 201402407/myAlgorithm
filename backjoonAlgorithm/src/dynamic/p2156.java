package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 포도주 시식
// 연속 3잔은 X
// 연속 0잔 -> result[n] = max(result[n], result[n-1])
// 연속 1잔 -> result[n] = result[n-2] + wine[n]; -> 이전 잔은 무조건 안마시고, 이전이전잔은 상관 없음
// 연속 2잔 -> result[n] = result[n-3] + wine[n-1] + wine[n]; -> 이전잔과 현재잔은 무조건 마시고, 이전이전잔은 무조건 안미셔야함
public class p2156 {
	static int[] wine;
	static int[] result;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		wine = new int[n + 1];
		result = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.valueOf(st.nextToken());
			wine[i] = weight;
		}
	
		result[1] = wine[1];
		
        if(n >= 2) {
        	result[2] = result[1] + wine[2];
        }
        for(int i = 3; i <= n; i++) {
        	result[i] = Math.max(result[i - 2] + wine[i], result[i - 3] + wine[i - 1] + wine[i]);
        	result[i] = Math.max(result[i], result[i - 1]);
        }
        
        System.out.println(result[n]);
	}
}