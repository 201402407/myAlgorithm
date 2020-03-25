package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

// ����� ���ڿ�
// ��Ʈ��ŷ ����
public class p1342 {
	static HashSet<String> set;
	static boolean[] visited;
	static char[] sCharArr;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		sCharArr = s.toCharArray();
		
		set = new HashSet<String>();
		int len = s.length();
		
		// ���� �ϳ��� ����(����)
		for(int i = 0; i < len; i++) {
			visited = new boolean[len];
			StringBuilder sb = new StringBuilder();
			sb.append(sCharArr[i]);
			visited[i] = true;
			
			backTracking(sb, len);
		}
		System.out.println(set.size());
	}
	
	static void backTracking(StringBuilder sb, int n) {
		if(sb.length() == n) {	// ��� ���ڸ� �̾��� ���
			set.add(sb.toString());
			return;
		}
		else {
			for(int i = 0; i < n; i++) {	// ��� ���� Ž��
				if(!visited[i]) {	// ���� �������� ���� ���ڸ�
					if(sb.charAt(sb.length() - 1) != sCharArr[i]) {
						// �����ϴ� ���
						visited[i] = true;
						sb.append(sCharArr[i]);
						backTracking(sb, n);
						// �������� ���� ���
						sb.deleteCharAt(sb.length() - 1);
						visited[i] = false;
					}
				}
			}
		}
	}
}
