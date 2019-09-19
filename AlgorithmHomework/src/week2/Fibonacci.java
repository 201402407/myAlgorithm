package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

// 피보나치 수열
// recursive, Bottom-up, recursive squaring 방식으로 구현
// 실행 시간도 같이 출력
// 출력 결과 작성 시 n은 90으로 설정하고 진행
// BigInteger : 무한 대의 정수 사용 가능
public class Fibonacci {

	private static int method, n;
	private static long startTime;
	private static boolean[] visited;
	
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
		try {
			st = new StringTokenizer(br.readLine());
			method = Integer.parseInt(st.nextToken()); // 알고리즘 방법 선택
			n = Integer.parseInt(st.nextToken());	// 해당 번째 숫자 출력
			visited = new boolean[n + 1];
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
            startTime = System.nanoTime();
            BigInteger result = recursion(n);
            System.out.format("Recursion (n = %d) 최종 실행 결과 값 : %d %n", n, result);
        });
		RecursionThread.start();
	}
	
	// 첫 번째 방법
	private static BigInteger recursion(int n) {
		if (n == 0) {
			if(!visited[n])
				printCurrentProgressTime(n, BigInteger.ZERO);
			return BigInteger.ZERO;
		}
		if (n == 1) {
			if(!visited[n])
				printCurrentProgressTime(n, BigInteger.ONE);
			return BigInteger.ONE;
		}
		BigInteger result = recursion(n - 2).add(recursion(n - 1));
		if(!visited[n])
			printCurrentProgressTime(n, result);
		return result;
		 
	}
	
	/*
	 * 현재 진행 시간 출력
	 * 전제 조건 : n은 방문한 적이 없는 경우
	 */
	private static void printCurrentProgressTime(int n, BigInteger result) {
		visited[n] = true;
		System.out.format("Recursion f[%d] = %d \t\t\t\t\t 실행 시간 : %f sec%n", n, result, (System.nanoTime() - startTime) / 1000000000.0);
	}
}
