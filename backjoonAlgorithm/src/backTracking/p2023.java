package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// ��Ʈ��ŷ ����
// �ű��� �Ҽ�
// �����佺�׳׽��� ü 
public class p2023 {
	static int n;
	static StringBuilder sb;
	static String[] alwaysSosu = { "1" , "3" , "7" , "9" };
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());
		sb = new StringBuilder();
		
		// �� �ڸ��� �Ҽ��� �����Ǿ��̾��Ƿ�, �̰ź��� �����ϱ�
		String[] startSosu = {"2" , "3" , "5" , "7" };
		for(int i = 0; i < startSosu.length; i++) {
			backTracking(startSosu[i], 1);
		}
		
		System.out.print(sb.toString());
	}
	
	// ��Ʈ��ŷ
	// �ڸ� �� �ϳ��� ������ �� ����, �ش� �Լ� ���ȣ�� 
	static void backTracking(String sosu, int len) {
		if(len >= n) {
			sb.append(sosu).append("\n");
			return;
		}
		
		for(int i = 0; i < alwaysSosu.length; i++) {
			String nextSosu = sosu + alwaysSosu[i];
			int nextSosuNumber = Integer.valueOf(nextSosu);
			if(isSosu(nextSosuNumber)) {
				backTracking(nextSosu, len + 1);
			}
		}
	}
	
	// �ش� ���� �Ҽ����� üũ�ϱ�
	// �����佺�׳׽��� ü �̿� 
	static boolean isSosu(int num) {
		int sqrt = (int) Math.sqrt(num);
		for(int i = 2; i <= sqrt; i++) {
			if(num % i == 0) {
				return false;
			}
		}
		
		return true;
	}
}
