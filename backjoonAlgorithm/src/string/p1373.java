package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2���� 8����
// ���ڿ� ���� 
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
		
		// 2���� -> 8����. 3�ڸ��� ��� ���ϱ�
		// 2���� input�� �ִ� ���̰� 1000000 ���� ����. ��, 10������ �ִ� �Է� ���� 2^999999 �̴�.
		int powNumber = 0, num = 0;
		
		while(--len >= 0) {
			num += (twoCharArr[len] - '0') * powNumbers[powNumber]; // 2���� �� * powNumber �� ° �ڸ� 
			
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
//		// 2���� -> 10����
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
//		// 10���� -> 8����
//		String eight = "";
//		while(ten >= 8) { // ���̻� �ڸ� ���� �߰����� ���� �� ���� �ݺ� ���� 
//			int remain = ten % 8;
//			eight = String.valueOf(remain) + eight;
//			ten = ten / 8;
//		}
//		
//		eight = String.valueOf(ten) + eight;
//		System.out.println(eight);
	}
}
