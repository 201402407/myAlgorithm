package dynamic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// ��� ������ 1�� ����
public class p2579_bestAnswer {
	public static void main(String arg[])throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int map[] = new int[n+1];		// ��� �� ������ ������� �迭
		int d[][] = new int[n+1][3];	// ��� �� ��쿡 ���� ���� ������ ������� 2���� �迭.
		for(int i = 1 ; i<=n ; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		d[1][1] = map[1];	// 1�� ° ��ܿ� �����ؼ� �� ĭ�� �� �� �ö�� ���
		d[2][1] = map[2];	// 2�� ° ��ܿ� �����ؼ� �� ĭ�� �� �� �ö�� ���
		d[2][2] = map[1]+map[2];	// 2�� ° ��ܿ� �����ؼ� �� ĭ�� �� �� �ö�� ���
		for(int i=3 ; i<=n ; i++) {
			// i�� ° ��ܿ� �����ؼ� �� �� �ö�� ���
			// �̴� 2�� ° ��� ������ �� ĭ�� �ö�� ���ۿ� �����Ƿ�, �� ��� �� ���� ���� ������ �� i�� ° ����� ������ ���Ѵ�.
			d[i][1] = Math.max(d[i-2][1],d[i-2][2])+map[i];
			// i�� ° ��ܿ� �����ؼ� �� ĭ�� �� �� �ö�� ���
			d[i][2] = d[i-1][1]+map[i];		
		}
		System.out.println(Math.max(d[n][1], d[n][2]));
	}	
}
