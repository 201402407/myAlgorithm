package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// ��������
// ��Ʈ��ŷ ����
public class p2663 {
	static int[] arr;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		
		arr = new int[n];
		arr[0] = 1;
		
		for(int i = 1; i < n; i++) {
//			System.out.println(i);
			backTracking(n, i);
		}
		
		System.out.println(Arrays.toString(arr).replaceAll("[^0-9]",""));
	}
	
	// ���� 2��°��(parameter + 1) 1���� ��° / 2�� �� ��(�ߺ� ����) �������� for������ ������ �ڷ� ����(������ ������ ������ ã�� ���̹Ƿ�) ������ Ȯ��.
	// ���� ���� ������ �� ���� ���� ���� ����
	public static void backTracking(int n, int index) {
		int len = (index + 1) / 2;
		boolean check = false;
		for(int num = 1; num <= 3; num++) {
//			System.out.println(num);
			if(check) {
				break;
			}
			if(arr[index - 1] == num) {
				continue;
			}
			arr[index] = num;	// �ӽ÷� �־�α�
			boolean isPossible = true;
			
			for(int i = 1; i < len; i++) {
				int[] nowElement = Arrays.copyOfRange(arr, index - i, index);
				int[] prevElement = Arrays.copyOfRange(arr, index - i - 1 - i, index - i - 1);
				if(Arrays.toString(nowElement).equals(Arrays.toString(prevElement).toString())) {	// �ε����� �����Ǳ� ���̹Ƿ�
					isPossible = false;
					break;
				}
			}
			
			if(isPossible) {
				break;
			}
		}
	}
}
