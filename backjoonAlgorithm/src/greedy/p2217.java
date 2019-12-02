package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// ���� ����
// �賶 �˰����� �׸��� ���
public class p2217 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		List<Integer> ropeList = new ArrayList<Integer>();
		int[] rope = new int[n];
		int[] newRope = new int[n];
		int[] dp = new int[n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			rope[i]	= Integer.valueOf(st.nextToken());
//			ropeList.add(Integer.valueOf(st.nextToken()));
		}
		
		// �������� ����
		Arrays.sort(rope);
		for(int i = 0; i < n; i++) {
			newRope[n - 1 - i] = rope[i];
		}
		
		// n�� 1�� ��� �ʱ�ȭ
		dp[0] = newRope[0];
		for(int i = 1; i < n; i++) {
			dp[i] = Math.max(dp[i - 1], newRope[i] * (i + 1));
		}
		int result = dp[n - 1];
		System.out.println(result);
	}
}
