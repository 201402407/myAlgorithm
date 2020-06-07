package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// K��° ��
// �̺�Ž��
public class p1300 {
	static int n, k;
	static long result = 0;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		
		long low = 1;
		long high = k;	// n * n�� �ȵǰ� k�� �Ǵ� ���� ? -> ������ ���� ã������ ���� K���� �۱� ����
		// ���� n * n�� ����ϱ� ���ؼ��� Math.min()���� mid�� �� �ּڰ��� ã�ƾ� �Ѵ�.
		
		while(low <= high) {
			long mid = (low + high) / 2;
			long count = getPossibleCount(mid);
			if(count >= k) {	// K ���� �ʰ��ϸ� ���ڸ� ���纸��. (���ƾ��� ������ �۾����Ƿ�)
				result = mid;
				high = mid - 1;
			}
			else {	// �̴��� ���
				low = mid + 1;	
			}
			
		}
		System.out.println(result);
	}
	
	static long getPossibleCount(long mid) {
		long count = 0;
		for(int i = 1; i <= n; i++) {
			// ���� ���� ���� n���� ũ�� ���� ��� ���Ұ� ���ų� �۴ٶ�� ���̹Ƿ� ��� ������ ������ n�� �����ش�.
			count += Math.min(n, mid / i);
		}
		
		return count;
	}
}
