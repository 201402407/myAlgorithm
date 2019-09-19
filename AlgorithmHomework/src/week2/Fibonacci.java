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
	private static boolean[] visited1, visited2, visited3;
	private static BigInteger[] arrayResult;
	
	public static void main(String args[]) {
		System.out.println("두 개의 값을 입력하세요. (첫 번째 값 : 수행 방법.   두 번째 값 : n)");
		System.out.println("방법 -> 1: Recursion,  2: Array,  3: Recursive squaring");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
		try {
			st = new StringTokenizer(br.readLine());
			method = Integer.parseInt(st.nextToken()); // 알고리즘 방법 선택
			n = Integer.parseInt(st.nextToken());	// 해당 번째 숫자 출력
			visited1 = new boolean[n + 1];
			visited2 = new boolean[n + 1];
			visited3 = new boolean[n + 1];
			arrayResult = new BigInteger[n + 1];
		}
		catch (NumberFormatException e) {
			System.out.println("숫자가 아닌 문자를 입력받았습니다. NumberFormatException");
			System.exit(0);
		}
		catch (IOException e) {
			System.out.println("토큰 읽기 오류. IOException");
			System.exit(0);
		} 
    	
		// 방법 1을 수행하는 쓰레드
		Thread RecursionThread = new Thread(() -> {
            startTime = System.nanoTime();
            BigInteger result = recursion(n);
            System.out.format("Recursion (n = %d) 최종 실행 결과 값 : %d %n", n, result);
        });
		// 방법 2를 수행하는 쓰레드
		Thread ArrayThread = new Thread(() -> {
            startTime = System.nanoTime();
            BigInteger result = arrayFibonacci(n);
            System.out.format("Recursion (n = %d) 최종 실행 결과 값 : %d %n", n, result);
        });
		
		switch(method) {
		case 1:
			RecursionThread.start();
			break;
		case 2:
			ArrayThread.start();
			break;
			default:
				System.out.println("방법 선택을 잘못 하셨습니다.");
				System.exit(0);
				break;
		}
		
	}
	
	// 첫 번째 방법
	private static BigInteger recursion(int n) {
		if (n == 0) {
			if(!visited1[n])
				printCurrentProgressTime(n, BigInteger.ZERO);
			return BigInteger.ZERO;
		}
		if (n == 1) {
			if(!visited1[n])
				printCurrentProgressTime(n, BigInteger.ONE);
			return BigInteger.ONE;
		}
		BigInteger result = recursion(n - 2).add(recursion(n - 1));
		if(!visited1[n])
			printCurrentProgressTime(n, result);
		return result;
		 
	}
	// 두 번째 방법
	private static BigInteger arrayFibonacci(int n) {
		if(visited2[n])
			return arrayResult[n];
		else {
			if(n == 0) {
				printCurrentProgressTime(n, BigInteger.ZERO);
				arrayResult[n] = BigInteger.ZERO;
			}
			else if(n == 1) {
				printCurrentProgressTime(n, BigInteger.ONE);
				arrayResult[n] = BigInteger.ONE;
			}
			else {
				BigInteger result = arrayFibonacci(n - 2).add(arrayFibonacci(n - 1));
				printCurrentProgressTime(n, result);
				arrayResult[n] = result;
			}
			return arrayResult[n];
		}
	}
	// 세 번째 방법
	// 행렬 거듭제곱 방법 + Powering a number 방법 사용
	// Strassen Algorithm(슈트라센 알고리즘) 사용
	private static BigInteger squaring() {
		
	}
	
	/*
	 * 현재 진행 시간 출력
	 * 전제 조건 : n은 방문한 적이 없는 경우
	 */
	private static void printCurrentProgressTime(int n, BigInteger result) {
		switch(method) {
		case 1:
			visited1[n] = true;
			break;
		case 2:
			visited2[n] = true;
			break;
		case 3:
			visited3[n] = true;
			break;
			default:
				break;
		}
		System.out.format("Recursion f[%d] = %d \t\t\t\t\t\t 실행 시간 : %f sec%n", n, result, (System.nanoTime() - startTime) / 1000000000.0);
	}
}
