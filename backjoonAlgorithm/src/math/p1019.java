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
		recusive(numStr);
		// 1 ~ 9 페이지 처리
		for(int i = 9; i >= 1; i--) {
			numCount[i]++;
		}
		
		for(int ele : numCount) {
			System.out.print(ele + " ");
		}
	}
	
	static int recusive(String num) {
		if(num.length() == 1) {	// 한 자리 수
			int numInt = Integer.valueOf(num);
			for(int i = numInt; i >= 0; i--) {
				numCount[i]++;
			}
			
			return numInt + 1;	// 개수
		}
		else {
			int len = num.length();
			int numInt = Character.getNumericValue(num.charAt(0));
			int minLen = numLen == len ? 1 : 0;	// 만약 입력받은 N의 자리 길이와 parameter로 받은 숫자의 자리 길이가 같으면 1까지, 아니면 0까지
			int count = 0;
			for(int i = numInt; i >= minLen; i--) {
				int tempCount = 0;
				
				if(len >= 3) {	// 최소 3자리라면 재귀호출
					if(i == numInt) {	// 가장 첫 번째(초기) 숫자인 경우 
						tempCount += recusive(num.substring(2, len));
					}
					else {
						tempCount += recusive(String.valueOf((10 * len - 2) - 1));	
					}
				}
				else {	// parameter가 두 자리인 경우
					if(i == numInt) {	// 가장 첫 번째(초기) 숫자인 경우
						tempCount += recusive(String.valueOf(num.charAt(len - 1)));
					}
					else {
						tempCount += recusive("9");	
					}
				}
				
				numCount[i] += tempCount;
				count += tempCount;
			}
			
			return count;
		}
	}
}
