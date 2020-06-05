package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ����Ž��
public class p3079 {
	static int[] tables;
	static long maxHigh = 0;
	static long min = Long.MAX_VALUE;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		tables = new int[n];
		
		for(int i = 0; i < n; i++) {
			tables[i] = Integer.parseInt(br.readLine());
			maxHigh = Math.max(maxHigh, tables[i]);
		}
		
		getMinTime(n, m);
		System.out.println(min);
	}
	
	// �ּ� �ð� ��� �Լ�
	static void getMinTime(int n, int m) {
		long low = 0;
		long high = maxHigh * m;
		
		// ���� �ð� ������ ���ϱ� ���� ����Ž��
		while(low <= high) {
			long mid = (low + high) / 2;
			long sum = 0;
			
			for(int ele : tables) {
				long needPeopleCount = mid / ele;
				
				// ���� �����ؼ� 0�� ������ ��츸 break�� �ʿ� ���� 
				// sum >= m �� �Ǹ� ���غ��� �ǹ� �����Ƿ� �ٷ� �ݺ����� �����Ų��.
				if(sum >= m) {
					break;
				}
				
				sum += needPeopleCount;
			}
			
			
			
			if(sum >= m) {
				System.out.println(mid + ", " + sum);
				min = Math.min(min, mid);
				high = mid - 1;
			}
			else {
				low = mid + 1;
			}
		}
	}
}
