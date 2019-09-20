package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// �����̴� �ö󰡰� �ʹ�
public class p2869 {
	static int A, B, V;
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = null;
		try {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());	// ù ��° ��
			B = Integer.parseInt(st.nextToken());	
			V = Integer.parseInt(st.nextToken());
			if((A < 1) || (B < 1) || (V < 1)) {
				System.exit(0);
			}
			if(B >= A) {
				System.exit(0);
			}
			if((A > V) || (B >= V)) {
				System.exit(0);
			}
		} 
		catch (IOException e) {
			System.exit(0);
		} 
		catch (NumberFormatException e) {
			System.exit(0);
		}
		
		// V <= n(A-B) + A
		// n >= (V - A) / (A - B)
		double x = V - A;
		double y = A - B;
		// ceil : �ݿø�
		// ���� ���� 0�̸� �Ϸ� ������ �ʰ� ���� ���̹Ƿ� 0���� ���
		// ���� ���� 0.~ �� �Ҽ��� ���ڰ� �����ϴ� ��� �Ϸ� ���� ������ ���
		int result = (int)Math.ceil(x / y) + 1;	
		System.out.println(result);
	}
}
