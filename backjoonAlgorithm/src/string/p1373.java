package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2진수 8진수
// 문자열 문제 
public class p1373 {
	static int[] powNumbers = {1, 2, 4};
	static char[] eightCharArr;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String twoStr = br.readLine();
		char[] twoCharArr = twoStr.toCharArray();
		int len = twoCharArr.length;
		int index;
		if(len % 3 != 0) {
			index = (len / 3);
		}
		else {
			index = (len / 3) - 1;
		}
		
		eightCharArr = new char[index + 1];
		
		// 2진수 -> 8진수. 3자리씩 끊어서 구하기
		// 2진수 input의 최대 길이가 1000000 보다 작음. 즉, 10진수로 최대 입력 값은 2^999999 이다.
		int powNumber = 0, num = 0;
		
		while(--len >= 0) {
			num += (twoCharArr[len] - '0') * powNumbers[powNumber]; // 2진수 값 * powNumber 번 째 자리 
			
			if(powNumber == 2) {
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
//		// 2진수 -> 10진수
//		int len = twoCharArr.length;
//		int mul = len - 1;
//		int ten = 0;
//		for(int i = 0; i < len; i++) {
//			int num = twoCharArr[i] - '0';
//			ten += num * Math.pow(2, mul - i);
//		}
//		
//		System.out.println(ten);
//		
//		// 10진수 -> 8진수
//		String eight = "";
//		while(ten >= 8) { // 더이상 자리 수가 추가되지 않을 때 까지 반복 수행 
//			int remain = ten % 8;
//			eight = String.valueOf(remain) + eight;
//			ten = ten / 8;
//		}
//		
//		eight = String.valueOf(ten) + eight;
//		System.out.println(eight);
	}
}
