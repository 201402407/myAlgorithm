package disjointSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// �� û��
// Union - Find
public class p9938 {
	static boolean[] checked;
	static int[] drawerParent;
	static StringBuilder sb;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		
		checked = new boolean[l + 1]; // 1 ~ L
		drawerParent = new int[l + 1];
		
		sb = new StringBuilder();
		
		// parent ��� �ʱ�ȭ
		for(int i = 1; i <= l; i++) {
			drawerParent[i] = i;
		}
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if(!checked[a]) {  				// 1�� ���� : ���� a�� ����ִ� ���
				checked[a] = true;
				union(a, b);
			}
			else if(!checked[b]) {			// 2�� ���� : ���� b�� ����ִ� ���
				checked[b] = true;
				union(b, a);
			}
			else if(!checked[find(a)]) {	// 3�� ���� : ���� a�� ������ �ִ� ���
				checked[find(a)] = true;
				union(a, b);
			}
			else if(!checked[find(b)]) {	// 4�� ���� : ���� b�� ������ �ִ� ���
				checked[find(b)] = true;
				union(b, a);
			}
			else {	
				sb.append("SMECE").append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		drawerParent[a]	= b;
		sb.append("LADICA").append("\n");
	}
	
	static int find(int index) {
		if(drawerParent[index] == index) {
			return index;
		}
		return drawerParent[index] = find(drawerParent[index]);
	}
}
