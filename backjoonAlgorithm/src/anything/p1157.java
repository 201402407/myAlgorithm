package anything;

import java.io.IOException;

public class p1157 {
	public static void main(String args[]) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String str = br.readLine().toUpperCase();
//		if(str.length() > 1000000) {
//			System.exit(0);
//		}
		int[] count = new int[91]; // Z�� �ƽ�Ű�ڵ�� 90
		for(int i = System.in.read(); i >= 'A'; i = System.in.read()) {	// System.in.read�� �� ���ھ� �Է� ����
			if(i >= 'a') {	// ���� �ҹ����� ���
				i -= 32;
			}
			count[i]++;
		}
		
		boolean multiValue = false;
		int max = count[65];
		int index = 65;
		for(int j = 66; j <= 90; j++) {
			int element = count[j];
			if(element == 0) 
				continue;
			if(max == element) {
				multiValue = true;
				continue;
			}
			if(max < element) {
				max = element;
				index = j;
				multiValue = false;
			}
		}
		
		if(multiValue)
			System.out.print("?");
		else {
			System.out.print(Character.toChars(index));
		}
	}
}
