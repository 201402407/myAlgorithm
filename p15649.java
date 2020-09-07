package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// N�� M(1)
// ��Ʈ��ŷ ����. �⺻
// ���� ����
public class p15649 {
	static int[] arr;
	static int n, m;
	static StringBuilder sb;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		sb = new StringBuilder();
		
		// ������ ���� ���� ���� �迭 ���� �� �ʱ�ȭ 
		arr = new int[n];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
		}
		
		permutation(0, new int[m], new boolean[n]);
		System.out.print(sb.toString());
	}
	
	// nPm ���� 
	// r : ���� ����
	// index : ���� �ε��� 
	static void permutation(int r, int[] eleArr, boolean[] checked) {
		if(r == m) {
			for(int ele : eleArr) {
				if(ele == 0) {
					continue;
				}
				sb.append(ele + " ");
			}
			sb.append("\n");
			return;
		}
		
		// �ٽ� ó������ ������ ��ȸ�ϸ� �̾ƾ� ��
		for(int i = 0; i < n; i++) {
			if(!checked[i]) { // ���ؾ� �ϱ� ���� 
				// ÷�� �̰� �Լ� ���ȣ��
				checked[i] = true;
				eleArr[r] = arr[i];
				permutation(r + 1, eleArr, checked);
				// �״��� ������ ���ͽ�Ű�� ������ �̾ƺ��� 
				checked[i] = false;
			}
		}
	}
}
