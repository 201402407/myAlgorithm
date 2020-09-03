package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// �Ӹ���Ҽ�
// ���ڿ� ���� 	
public class p1259 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str;
		StringBuilder sb = new StringBuilder();
		while((str = br.readLine()) != null) {
			if(str.equals("0")) {
				break;
			}
			
			int len = str.length();
			int start = 0;
			int end = len - 1;
			boolean isPal = true;
			// �� Ž�� �ε����� ������ �� ���� ���� Ž��
			while(start <= end) {
				if(str.charAt(start) != str.charAt(end)) {
					isPal = false;
					break;
				}
				
				start++;
				end--;
			}
			
			if(isPal) {
				sb.append("yes");
			}
			else {
				sb.append("no");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
