package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ��ƼĿ
// ���� ���α׷��� -> RGB ������ ����?! -> Ǯ�� ���� ��
// ���� for������ 2���� �迭 Ž�� �� y�ຸ�� x���� �������� Ž���ϴ� ���� �� ȿ����
public class p9465 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.valueOf(st.nextToken());
		for(int tc = 0; tc < t; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			int[][] sticker = new int[2][n + 1];
			int[][] dp = new int[2][n + 1];	// [][0] : �� ��ƼĿ���� �������� ���� �ִ� ��. [][1] : �� ��ƼĿ���� �������� ���� �ִ� ��.
			// �Է�
			// ù ��° ��
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= n; i++) {
				int upElement = Integer.valueOf(st.nextToken());
				sticker[0][i] = upElement;
			}
			// �� ��° ��
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= n; i++) {
				int downElement = Integer.valueOf(st.nextToken());
				sticker[1][i] = downElement;
			}
			
			// �ʱ� �� ����
			dp[0][1] = sticker[0][1];
			dp[1][1] = sticker[1][1];
			// dp ���
			for(int i = 2; i <= n; i++) {
				// 1��° ������ ������ ���ϱ� ������ 2��° ������ �ƹ��ų� �����ص� ����� ����.
				// ����           /  ����
				// ����           /  ����
				// �� �� ������ ��� �� ū ���� dp�� ����
				dp[0][i] = Math.max(dp[1][i - 2], dp[1][i - 1]) + sticker[0][i];
				dp[1][i] = Math.max(dp[0][i - 2], dp[0][i - 1]) + sticker[1][i];
			}
//			System.out.println(Math.max(dp[n][0], dp[n][1]));
			sb.append(Math.max(dp[0][n], dp[1][n])).append("\n");
		}
		System.out.println(sb.toString());
	}
}
