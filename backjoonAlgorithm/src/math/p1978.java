package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// �Ҽ� ã��
// ���� ����
public class p1978 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		
		boolean[] sosu = new boolean[1001];	// 1���� 1000���� �� �� �Ҽ��� ���� true
		// �Ҽ��� �� ã��
		for(int i = 2; i < sosu.length; i++) {
			boolean check = true;
			for(int j = 2; j < i; j++) {
				if(i % j == 0) {	// �������� ���� �ִٸ�
					check = false;	// �Ҽ��� �ƴϴ�
					break;
				}
			}
			sosu[i] = check;
		}
		
		st = new StringTokenizer(br.readLine());
		int result = 0;
		for(int i = 0; i < n; i++) {
			int num = Integer.valueOf(st.nextToken());
			if(sosu[num]) {
				result++;
			}
		}
		
		System.out.println(result);
	}
}
