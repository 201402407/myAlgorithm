package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 설탕 배달 문제
// 수학
public class p2839 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		

		int count = -1;
		int quotient = n / 5;
		int remain = n % 5;
		for(int i = 0; i <= quotient; i++) {
			int realQuo = quotient - i;	// 5로 나눈 몫에서 5를 i만큼씩 꺼내고 남은 몫.
			int tempRemain = (i * 5) + remain;		// 5를 i만큼 꺼낸 값
			if(tempRemain % 3 == 0) {	// 3으로 나눌 수 있으면
				count = realQuo + (tempRemain / 3);	// 고것이 최소 개수.
				break;
			}
		}
		System.out.println(count);
	}
}
