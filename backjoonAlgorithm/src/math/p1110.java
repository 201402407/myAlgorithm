package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 더하기 사이클
// 수학 문제
public class p1110 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int compareN = n;
		int count = 0;
		do {
			int sum = 0;
			int first = compareN / 10;
			int last = compareN % 10;
			if(compareN < 10) {
				sum = compareN;
			}
			else {
				sum = first + last; 
			}
			compareN = (last * 10) + (sum % 10);
			count++;
		}
		while(n != compareN);
		
		System.out.println(count);
	}
}
