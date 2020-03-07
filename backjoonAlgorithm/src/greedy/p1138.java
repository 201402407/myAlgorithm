package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// �� �ٷ� ���� ����
// �׸��� �˰��� -> ���� ���ϱ�� ���?!
public class p1138 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		
		LinkedList<Integer> indexs = new LinkedList<Integer>();
		int[] line = new int[n];
		
		// LinkedList �ʱ� �� ����
		for(int i = 0; i < n; i++) {
			indexs.add(i);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			int people = Integer.valueOf(st.nextToken());
			int index = indexs.remove(people);
			line[index] = i;	// �ش��ϴ� Ű i�� ���� ����� index ��° �ٿ� ���ִ�.
		}
		for(int ele : line) {
			System.out.print(ele + " ");
		}
	}
}