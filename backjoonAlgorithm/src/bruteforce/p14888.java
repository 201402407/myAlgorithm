package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 연산자 끼워넣기
// 브루트 포스
public class p14888 {
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int[] numArr;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		numArr = new int[n];
		int[] buhoArr = new int[4];
		
		// 숫자들 입력 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			numArr[i] = num;
		}
		
		// 문자들 입력받기
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {	// 0: +, 1: -, 2: *, 3: / 
			int buhoCount = Integer.parseInt(st.nextToken());
			buhoArr[i] = buhoCount;
		}
		
		permutation(n, 1, buhoArr, numArr[0]);
		System.out.println(max);
		System.out.println(min);
	}
	
	// 순열(부호 순서있게 뽑기)
	static void permutation(int n, int index, int[] buhoArr, int now) {
		if(index == n) {
			max = max > now ? max : now;
			min = min < now ? min : now;
			return;
		}
		else {
			for(int i = 0; i < buhoArr.length; i++) {
				if(buhoArr[i] > 0) {
					buhoArr[i]--;
					permutation(n, index + 1, buhoArr, calculate(now, numArr[index], i));
					buhoArr[i]++;
				}
			}
		}
	}
	
	// 계산하는 함수
	static void calculation(LinkedList<Integer> resultArr) {
		int i = 1;
		int result = numArr[0];
		for(int buho : resultArr) {
			result = calculate(result, numArr[i++], buho);
		}
		
		max = max > result ? max : result;
		min = min < result ? min : result;
	}
	
	// 부호에 맞게 연산해서 결과값을 리턴하는 함수
	static int calculate(int a, int b, int buho) {
		switch(buho) {
		case 0:	// 덧셈
			return a + b;
		case 1:	// 뺄셈
			return a - b;
		case 2:	// 곱셈
			return a * b;
		case 3: // 나눗셈
			return a / b;
		}
		
		return 0;
	}
}
