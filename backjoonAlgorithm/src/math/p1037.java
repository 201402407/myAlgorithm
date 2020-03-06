package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 약수 문제
public class p1037 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		ArrayList<Integer> list = new ArrayList<Integer>();

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int ele = Integer.valueOf(st.nextToken());
			list.add(ele);
		}
		
//		for(int i = 2; i < 10)
	}
}
