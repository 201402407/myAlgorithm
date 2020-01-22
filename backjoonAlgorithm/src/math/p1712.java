package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 수학 문제
// 손익분기점 문제
// long을 사용하고, 계산 시 수입이 생산 비용보다 커졌을 때의 판매량 구하기
public class p1712 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long a = Long.valueOf(st.nextToken());
		long b = Long.valueOf(st.nextToken());
		long c = Long.valueOf(st.nextToken());
		
		if(b >= c) {	// 손익분기점이 발생할 수 없음
			System.out.println(-1);
		}
		else {
			int n = 0;	// 노트북 생산 개수
			long profit = c - b;
//			while((a + (b * n)) >= c * n) {
//				n++;
//			}	
			System.out.println((a / profit) + 1);
		}
	}
}
