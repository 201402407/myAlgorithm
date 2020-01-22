package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ���� ����
// ���ͺб��� ����
// long�� ����ϰ�, ��� �� ������ ���� ��뺸�� Ŀ���� ���� �Ǹŷ� ���ϱ�
public class p1712 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long a = Long.valueOf(st.nextToken());
		long b = Long.valueOf(st.nextToken());
		long c = Long.valueOf(st.nextToken());
		
		if(b >= c) {	// ���ͺб����� �߻��� �� ����
			System.out.println(-1);
		}
		else {
			int n = 0;	// ��Ʈ�� ���� ����
			long profit = c - b;
//			while((a + (b * n)) >= c * n) {
//				n++;
//			}	
			System.out.println((a / profit) + 1);
		}
	}
}
