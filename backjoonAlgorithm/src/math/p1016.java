package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 제곱 ㄴㄴ 수
public class p1016 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long min = Long.valueOf(st.nextToken());
		long max = Long.valueOf(st.nextToken());
		
		int result = (int) (max - min + 1);
//		int count = 0;
//		ArrayList<Long> squared = new ArrayList<Long>();	// 인덱스 0이 2
		int sqrt = ((int) Math.sqrt(max));
		boolean[] isSosu = new boolean[sqrt + 1];
		Arrays.fill(isSosu, true);
		// 소수 구하기
//		int sqrt2 = (int) Math.floor(Math.sqrt(sqrt));
//		for(int i = 2; i <= sqrt2; i++) {
////			int num = i;
//			if(!isSosu[i])
//				continue;
//			for(int j = i + 1; j <= sqrt; j++) {
//				if(!isSosu[j]) 
//					continue;
//				if(j % i == 0) 
//					isSosu[j] = false;
//			}
//		}
		
		// 제곱수 구하기
//		for(int i = 2; i <= sqrt; i++) {
//			if(isSosu[i]) // 소수인 수만 제곱수로 넣기
//				squared.add((long) i * i);	
//		}
		
		boolean[] checks = new boolean[result]; // 제곱 ㄴㄴ수가 아님을 체크. false : 제곱ㄴㄴ수, true : 제곱ㄴㄴ수가 아님.
		long[] num = new long[result];
		long temp = min;
		
		// 배열 내에 어떤 수 X를 넣기
		for(int i = 0; i < result; i++) {
			num[i] = temp++;
		}
		 
		// 에라토스체네스의 체
		// 제곱ㄴㄴ수를 체크하기
//		for(int i = 2; i <= sqrt; i++) {
////			if(isSosu[i]) {	// 소수인 수만 제곱수로 넣기 
//				long squared = i * i;
//				int moc = (int) (min % squared == 0 ? 0 : min % squared);	// index
//				for(int j = moc; j < result; j += squared) {	// 몫을 1씩 증가시킴
//					if(!checks[j])
//						checks[j] = true;
//				}
////			}
//		}
		
		for(int i = 2; i <= sqrt; i++) { 
				long squared = i * i;
				long start = ((min - 1) / squared + 1) * squared;
//				int moc = (int) (min % squared == 0 ? 0 : min % squared);	// index
				for(long j = start; j <= max; j += squared) {	// 몫을 1씩 증가시킴
					if(!checks[(int) (j - min)])
						checks[(int) (j - min)] = true;
				}
		}
		
		
//		for(int i = 0; i < result; i++) {
//			if(!checks[i]) {
//				for(long ele : squared) {
//					if(num[i] % ele == 0) {
//						checks[i] = true;
//						break;
//					}
//				}	
//			}
//		}
		
		// 제곱ㄴㄴ수 개수 counting
		int count = 0;
		for(int i = 0; i < result; i++) {
			if(!checks[i])
				count++;
		}
		
		System.out.println(count);
//		while(temp <= max) {
//			for(long ele : squared) {
//				if(temp % ele == 0) {
//					result--;
//					break;
//				}
//			}
//			temp++;
//		}
		
	}
}
