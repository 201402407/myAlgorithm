package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 세진이의 미팅
// 수학 - 페르마의 소정리
public class p15791 {
	static final long MOD_VALUE = 1000000007;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long n = Long.valueOf(st.nextToken());
		long m = Long.valueOf(st.nextToken());
		
		// 페르마의 소정리
		// 모듈러 연산
		// nCm을 구하는 문제
//		nCm = (n - (n - 1) - ... - (n - m) / m! -> n! / m! * (n - m)!
		// 분자 N!
		long numerator  = factorial(n);
		// 분모 (K! * (N-K)!) mod p
		long denominator = (factorial(m) * factorial(n - m)) % MOD_VALUE;
		// N! * 분모의 역원((K! * (N-K)!) 
//		nCm % MOD_VALUE = n! / m! * (n - m)! % MOD_VALUE = n! / (m! * (n - m)!)^MOD_VALUE-2 % MOD_VALUE 
		// https://st-lab.tistory.com/241 참고
		long result = numerator * pow(denominator, MOD_VALUE - 2) % MOD_VALUE;
		System.out.println(result);
	}
	
	private static long factorial(long n) {
		long fac = 1L;
		
		while(n > 1) {
			fac = (fac * n) % MOD_VALUE;
			n--;
		}
		
		return fac;
	}
	
	private static long pow(long base, long expo) {
	    long result = 1;    
		    
	    while (expo > 0) {
		    	
	    	// 지수가 홀 수면 밑을 한 번 더 곱하여 지수가 짝수가 되도록 한다.
	        if (expo % 2 == 1) {
	            result *= base;
	            result %= MOD_VALUE;
	        }
	        base = (base * base) % MOD_VALUE;
	        expo /= 2;			// 지수를 절반으로 나눔
	    }
	    return result;
	}
}
