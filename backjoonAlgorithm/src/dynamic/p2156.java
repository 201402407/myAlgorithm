package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 포도주 시식
public class p2156 {
	static int[] wine;
	static int[] result;
	static int[][] answer;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		wine = new int[n];
		result = new int[n];
		answer = new int[n][2];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.valueOf(st.nextToken());
			wine[i] = weight;
		}
	
		answer[0][0] = wine[0];
		answer[0][1] = 0;
		int max = wine[0];
        if(n >= 2) {
            answer[1][0] = wine[1];
		    answer[1][1] = answer[0][0] + wine[1];
        }	
	}
}