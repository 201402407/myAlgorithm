package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 책 페이지
// 수학
public class p1019 {
	static int[] numCount = new int[10];	// 0 ~ 9
	static int num, numLen;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String numStr = br.readLine();
		num = Integer.valueOf(numStr);
		numLen = numStr.length();
		
		int digit = 1;
		int start = 1;
		int end = num;
		
		// https://mygumi.tistory.com/180
		// 핵심은 start(A)는 일의 자리를 0으로, end(B)는 일의 자리를 9로 맞춰줘야 한다.
		// 그래야, 공식(B / 10 - A / 10 + 1) * 자리 수 만큼 0~9 개수를  counting 할 수 있다.
		while(start <= end) {
			// 1) start(A) 일의 자리 수 0 맞출때까지 ++하기
			while(start % 10 != 0 && start <= end) {
				addCount(start, digit);
				start++;
			}
			
			// 2) end(B) 일의 자리 수 9 맞출때까지 --하기
			while(end % 10 != 9 && start <= end) {
				addCount(end, digit);
				end--;
			}
			
			if(start > end) {
				break;
			}
			
			// 2) 마지막 자리수 제거하기(위에서 자리수에 맞게 개수 Counting 했으니까)
			start /= 10;
			end /= 10;
			
			// 3) start ~ end 사이의 0~9 개수를 모두 센다.
			// 현재 자리 수 만큼 곱해주기
			for(int i = 0; i <= 9; i++) {
				// 공식(B / 10 - A / 10 + 1) * 자리 수 
				numCount[i] += (end - start + 1) * digit;
			}
			
			// 자리 수를 높인다.
			digit *= 10;
		}
		
		for(int ele : numCount) {
			System.out.print(ele + " ");
		}
		
	}
	
	// 해당 자리 수에 맞게 0~9 개수 더하기
	// ex) num = 5, digit = 1 -> 5 1개
	// num = 45, digit = 1 -> 4 1개, 5 1개
	// num = 45, digit = 2 -> 4 10개, 5 10개 (450 ~ 460 이니까 4 10개, 5 10개 들어간다)
	private static void addCount(int num, int digits) {
		while(num > 0) {
			numCount[num % 10] += digits;
			num /= 10;
		}
	}
}
