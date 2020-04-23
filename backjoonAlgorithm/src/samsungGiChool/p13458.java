package samsungGiChool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시험 감독
// 삼성 기출
public class p13458 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int[] testPlaces = new int[n];
		
		long count = 0;
		st = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		int a = Integer.valueOf(st2.nextToken());
		int b = Integer.valueOf(st2.nextToken());
		
		for(int i = 0; i < n; i++) {
			int people = Integer.valueOf(st.nextToken());
			testPlaces[i] = people;
			
			// 총 감독관 배치
			int rest = testPlaces[i] - a;
			count++;
			
			// 부 감독관 배치
			if(rest > 0) {
				count += rest % b == 0 ? rest / b : (rest / b) + 1;  
			}
		}
		System.out.println(count);
	}
}
