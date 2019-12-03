package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// ��Ÿ��
// �׸��� �˰���
public class p1049 {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		int[] packs = new int[m];	// �귣�� ��Ű��
		int[] ones = new int[m];	// �귣�� ����
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int pack = Integer.valueOf(st.nextToken());
			int one = Integer.valueOf(st.nextToken());
			packs[i] = pack;
			ones[i] = one;
		}
		// �������� ����
		Arrays.sort(packs);
		Arrays.sort(ones);
		int min = 0;
		while(n > 0) {
			int minPack = packs[0];
			int minOne = ones[0];
			if(n >= 6) { // 6 ������ ����
				if(minPack > minOne * 6) 
					min += minOne * 6;
				else 
					min += minPack;

				n -= 6;
			}
			else {
				if(minPack > minOne * n)
					min += minOne * n;
				else 
					min += minPack;
				n -= n;
			}
		}
		System.out.println(min);
	}
}
