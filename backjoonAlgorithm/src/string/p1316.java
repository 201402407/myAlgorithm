package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// �׷� �ܾ� üĿ
// ���ڿ� ����
public class p1316 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int count = 0;
		for(int i = 0; i < n; i++) {
			int[] results = new int[26];	// a ~ z���� 26��.
			boolean[] checked = new boolean[26];
			st = new StringTokenizer(br.readLine());
			String word = st.nextToken();
			
			// �� ������ ���
			int prevCh = 0;
			boolean isGroup = true;	// �׷� �ܾ����� ���� üũ�ϴ� bool ����
			if(word.length() == 1) {
				count++;
				continue;
			}
			else {
				int ch = word.charAt(0) - 97;	// a�� 0���� �����ϱ� ����
				checked[ch] = true;
				prevCh = ch;
			}
			
			for(int x = 1; x < word.length(); x++) {
				int ch = word.charAt(x) - 97;	// a�� 0���� �����ϱ� ����
				if(checked[ch]) {	// ���� �� ���� �ش� ���ڸ� ����� ���
					if(prevCh != ch) {	// �ٷ� ���� ���ڿ� �ٸ� ���(���ӵ��� ���� ���)
						isGroup = false;
						break;
					}
				}
				else {
					checked[ch] = true;
					prevCh = ch;
				}
			}
			
			if(isGroup)
				count++;
		}
		System.out.println(count);
	}
}
