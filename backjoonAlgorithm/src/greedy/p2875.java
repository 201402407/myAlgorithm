package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ��ȸ or ����
// �׸��� �˰��� 
public class p2875 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		
		int max = 0;
		// i : ���л����� ���� ���� ��
		for(int i = 0; i <= k; i++) {
			int j = k - i;	// ���л����� ���Ϻ��� ��
			int nowN = n - j;
			int nowM = m - i;
			if(nowN < 0 || nowM < 0) {
				continue;
			}
			
			if(nowN / 2 >= nowM) {
				max = Math.max(max, nowM);
			}
			else {
				max = Math.max(max, nowN / 2);
			}
//			System.out.println(nowN + " , " + nowM + ", " + max);
		}
		System.out.println(max);
	}
}
