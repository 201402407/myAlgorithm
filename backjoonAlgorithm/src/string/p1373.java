package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2진수 8진수
// 문자열 문제 
public class p1373 {
	static int[] powNumbers = {1, 2, 4}; // 2^0, 2^1, 2^2
	static char[] eightCharArr;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String twoStr = br.readLine();
		char[] twoCharArr = twoStr.toCharArray();
		int len = twoCharArr.length;
		int index;
		
		// 3으로 나누어떨어지지 않으면, 한 자리가 추가된다. 
		if(len % 3 != 0) {
			index = (len / 3);
		}
		else {
			index = (len / 3) - 1;
		}
		
		eightCharArr = new char[index + 1]; // String에 append보다 배열에 문자를 집어넣는 방식으로하여 메모리 및 속도향상 
		
		// 2진수 -> 8진수. 3자리씩 끊어서 구하기
		// 2진수 input의 최대 길이가 1000000 보다 작음. 즉, 10진수로 최대 입력 값은 2^999999 이다.
		int powNumber = 0, num = 0;
		
		while(--len >= 0) {
			num += (twoCharArr[len] - '0') * powNumbers[powNumber]; // 2진수 값 * powNumber 번 째 자리 
			
			if(powNumber == 2) { // 2진수 3자리를 전부 계산했으면 문자열에 추가
				eightCharArr[index] = (char) (num + '0');
				index--;
				powNumber = 0;
				num = 0;
			}
			else {
				powNumber++;
			}
		}
		
		if(index == 0) {
			eightCharArr[index] = (char) (num + '0');
		}
		
		System.out.println(new String(eightCharArr));
	}
}
// end