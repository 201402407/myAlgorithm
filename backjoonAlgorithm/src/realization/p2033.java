package realization;

import java.util.Scanner;

// ����, ���� 
// �ݿø� 
public class p2033 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		char[] chArr = str.toCharArray();
		int len = chArr.length;
		
		int nextAdd = 0;
		StringBuilder sb = new StringBuilder();
		// ���� �ڸ����� �Ž��� �ö󰡾� �ϹǷ� ���� Ž��
		for(int i = len - 1; i >= 1; i--) {
			int num = (int) chArr[i] - '0';
			int nowNum = num + nextAdd;
			nextAdd = nowNum >= 5 ? 1 : 0;
			sb.append(0);
		}
		
		// ���� �� ���ڸ����� �ݿø��� ��� ���
		sb.insert(0, (int)(chArr[0] - '0') + nextAdd);
		System.out.println(sb.toString());
		
		
		/** ���� ������ �����ϴ� ���
		 * @author devetude
		 // �� ���� �ʱ�ȭ
		int comp = 10;

		// ������ ���� ������ ���ǿ� �°� �ݿø� ����
		while (N > comp) {
			int nModComp = N % comp;

			if (nModComp * 10 / comp >= 5) {
				N += comp;
			}

			N -= nModComp;
			comp *= 10;
		}
		 */
	}
	
	
}
