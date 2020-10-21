package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// �� ��ȣ
// ���ڿ�, �׸��� 
public class p1082 {
	static int[] prices;
	static int n, money;
	static int min = Integer.MAX_VALUE, minIndex = 0;
	static StringBuilder sb;
	static String max = "";
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());
		prices = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		// index : ���� , value : �ش� ���ڸ� �� �� �ִ� ���� 
		for(int i = 0; i < n; i++) {
			prices[i] = Integer.valueOf(st.nextToken());
			if(prices[i] <= min) {	// �ּҺ������ �� �� �ִ� ���� ã�� 
				min = prices[i];
				minIndex = i;
			}
		}
		
		money = Integer.valueOf(br.readLine());
		sb = new StringBuilder();
		
		// �ִ� ���� ���� ��� 
		int maxLen = getMaxNumberLength();
		if(minIndex != 0) {	// �ּ� ����� ���ڰ� 0�� �ƴϸ�
			buyBigNumber(maxLen, money, false);
		}
		else {	// �ּ� ����� ���ڰ� 0�̸� 
			buyBigNumber(maxLen, money, true);
		}
		
		System.out.println(sb.toString());
	}
	
	// �ּ� ����� ���ڸ� �ִ� �� �� ��� �ִ��� ã�� 
	static int getMaxNumberLength() {
		int len = 0;
		while(money >= min) {
			money -= min;
			len++;
		}
		
		return len;
	}
	
	// �ּ� ����� ���ڰ� 0�� ��, ���� 0���� ��ٸ�
	// isZero : ���� ���� �������� üũ�ϴ� FLAG
	static void buyBigNumber(int maxLen, int money, boolean isZero) {
		String str = "";
		
		// �ִ��� ���� 0�� �ȾƼ�, �� ���� ���ڸ� ����ϴϱ� 
		while(maxLen --> 0) {
			// �ּ� ���� �ϳ��� ������ ���� ���
			money += min;
			boolean getNumber = false;
			
			for(int i = n - 1; i >= 0; i--) {
				if(i == minIndex) {	// �� �ڷδ� ���� ������ ���ڰ� �����ϴ��� ���ݺ��� ���� �����̹Ƿ� ���� Ž���� �ʿ� ����.
					break;
				}
				
				if(prices[i] <= money) {	// ���� ������ �ִ� ������ ����� ũ�� �н� 
					getNumber = true;
					isZero = false;
					money -= prices[i];
					sb.append(i);
					break;
				}
			}
			
			// �ϳ��� �ּ� ���ڸ� �Ⱦ��� ��, �ƹ��͵� �� �� ���ٸ� �ϳ��� �ּ� ���ڸ� �� �ȾƼ� �ٸ� ���ڸ� ��°� �ƴ϶� �� ���� �ٽ� �缭 ����°� ���� ū ���ڰ� �ȴ�.
			if(!getNumber) {
				if(isZero) {	// ���� ���� 0�� ���¿��� �ƹ��͵� �� �� ���ٸ� �ϳ� �� �ȾƼ��� �ٸ� ���ڸ� ����Ѵ�.	
					continue;
				}
				else {	
					maxLen++; // �ȾҴ� �ּҼ��� ȸ���ϱ�
					break;	
				}
			}
		}
		
		// ���� 0�ۿ� ���ٸ�(�� ��쿡�� 0�ϳ��� �����ϴ� ���)
		if(isZero) {
			sb.append(0);
		}
		while(maxLen --> 0) {
			sb.append(minIndex);
		}
	}
}
