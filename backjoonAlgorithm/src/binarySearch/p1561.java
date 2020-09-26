package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// �̺�Ž�� 
// ���� ���� ���� 	
public class p1561 {
	static int maxM;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long n = Long.valueOf(st.nextToken());	// ��ü �� ��  
		int m = Integer.valueOf(st.nextToken());	// ��ü ���̱ⱸ �� 
		
		int[] ridesTime = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			ridesTime[i] = Integer.valueOf(st.nextToken());
			maxM = Math.max(maxM, ridesTime[i]);
		}
		
		long result = binarySearch(ridesTime, n, m);
		
		// result - 1 ���� �� �� �ο� �� ���ϱ� 
		long prevTime = result - 1;
		long prevCount = m;	// ó�� 0���� �� ��� ���̱ⱸ�� ��������Ƿ� ��� ���̱ⱸ�� �ο� ���� (m��) 
		long[] prevRidesTime = new long[m];
		for(int i = 0; i < m ; i++) {
			prevRidesTime[i] = prevTime / ridesTime[i];
			prevCount += prevRidesTime[i];
		}
		
		// ������ �л��� Ÿ�� ���̱ⱸ ã��
		long nowNeedsCount = n - prevCount;
		long nowCount = 0;
		long ridesNum = n;	// ����, n < m �� ��쿡�� ó�� ������ �� n���� ž�½�ų�� �־� n��° ����� n�� ���̱ⱸ�� ź��.
		for(int i = 0; i < m; i++) {
			if(result / ridesTime[i] != prevRidesTime[i]) {
//				System.out.println(result / ridesTime[i] + " , " + prevRidesTime[i] + " , i �϶� : " + i);
				nowCount++;
				if(nowCount == nowNeedsCount) {
					ridesNum = i + 1;	// ���̱ⱸ�� ��ȣ�� 1 ~ M �� �����̹Ƿ� 
					break;
				}
			}
		}
		
		System.out.println(ridesNum);
	}
	
	static public long binarySearch(int[] ridesTime, long n, int m) {
		long left = 0;
		long right = (n / m) * maxM;	// �ִ� ���� �� �ִ� �ð� 
		long result = 0;
		
		// �̺�Ž�� ����
		// index : x���� �Ǿ��� ���� x��
		// value : x���� �Ǿ��� �� ������ �� �ο� ��
		// �ּ� N�� �̻��� �¿� �� �ִ� �ð��� ��� ���� ��� 
		while(left <= right) {
			long mid = (left + right) / 2; 
			long count = m;
			for(int i = 0; i < m ; i++) {
				count += mid / ridesTime[i];
			}
			
//			System.out.println(count + ", mid : " + mid + " ,,, left : " + left);
			if(n <= count) {
				result = mid;
				right = mid - 1;
			}
			else {
				left = mid + 1;
			}
		}
		
		return result;
	}
}
