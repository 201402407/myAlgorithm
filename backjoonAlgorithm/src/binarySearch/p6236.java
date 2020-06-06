package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ���� Ž��
// �뵷 ����
public class p6236 {
	static int[] pays;
	static int maxHigh;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		pays = new int[n];
		
		for(int i = 0; i < n; i++) {
			pays[i] = Integer.parseInt(br.readLine());
			maxHigh = Math.max(maxHigh, pays[i]);
		}
		
		// ����Ž�� ����
		int low = 1;
		int high = maxHigh * m;
		
		while(low <= high) {
			int mid = (low + high) / 2;
			int payResult = possiblePay(mid, n, m);
			
			if(payResult == -1) {	// �ƿ� �ش� ������ �����δ� ���� �Ұ���
				low = mid + 1;	// ���� �� �ø���
			}
			
			if(payResult == 0) {	// m Ƚ���� �ʰ�
				low = mid + 1;	
			}
			
			if(payResult == 1) {	// ��Ȯ�� m Ƚ��
				high = mid - 1;
			}
			
			if(payResult == 2) {	// m Ƚ������ ����
				high = mid - 1;
			}
		}
		
		System.out.println(low);
	}
	
	// M Ƚ�� ���� ���� �������� �Ǵ��ϴ� �Լ�
	// 0 : M Ƚ���� �ʰ�
	// 1 : ��Ȯ�� M Ƚ��
	// 2 : M Ƚ������ ����
	static int possiblePay(int withdraw, int n, int m) {
		int nowMoney = 0;
		int nowCount = 0;
		
		for(int i = 0; i < n; i++) {
			if(withdraw < pays[i]) {	// ������ ������ ������ ū ��� ���� �Ұ���.
				return -1;
			}
			
			if(nowMoney < pays[i]) {
				nowCount++;
				nowMoney = withdraw;
				if(nowCount > m) {
					return 0;
				}
			}
			
			nowMoney -= pays[i];
		}
		
		return nowCount == m ? 1 : 2;
	}
}
