package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 피보나치 수열
// recursive, Bottom-up, recursive squaring 방식으로 구현
// 실행 시간도 같이 출력
// 출력 결과 작성 시 n은 90으로 설정하고 진행

public class Fibonacci {

	private static int method, n;

	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
		try {
			st = new StringTokenizer(br.readLine());
			method = Integer.parseInt(st.nextToken()); // 알고리즘 방법 선택
			n = Integer.parseInt(st.nextToken());	// 해당 번째 숫자 출력
		}
		catch (NumberFormatException e) {
			System.out.println("숫자가 아닌 문자를 입력받았습니다. NumberFormatException");
			System.exit(0);
		}
		catch (IOException e) {
			System.out.println("토큰 읽기 오류. IOException");
			System.exit(0);
		} 
    	
		Thread RecursionThread = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            // System.out.format("powering 결과 : %d %n", getPoweringNumber(a, n));
            System.out.format("Recursion 실행 시간 : %d ms%n%n", (System.currentTimeMillis() - startTime));
        });
		RecursionThread.start();
	}
}
