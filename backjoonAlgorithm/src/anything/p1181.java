package anything;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// �ܾ� ����
public class p1181 {
	static List<String>[] list;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		list = new ArrayList[51];
		// �� �ε������� ��ü ����
		for(int i = 1; i < list.length; i++) {
			list[i] = new ArrayList<String>();
		}
		
		// �� ���ڿ� �ش��ϴ� ���̸� �ε����� ��� ArrayList �迭�� �ִ´�.
		for(int j = 0; j < n; j++) {
			String value = br.readLine();
			list[value.length()].add(value);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int k = 1; k < list.length; k++) {
			if(list[k].size() == 0) {	// �� ������� ��
				continue;
			}
			else {	// �ϳ��� ���ִٸ�
				Collections.sort(list[k]);
				// �켱 ���� ù ��° ���� �ְ�
				String prevElement = list[k].get(0);
				sb.append(prevElement).append("\n");
				for(int x = 1; x < list[k].size(); x++) {	// �ߺ� ���Ÿ� ���� �ݺ���
					String newElement = list[k].get(x);
					if(newElement.equals(prevElement)) {
						continue;
					}
					prevElement = newElement;
					sb.append(newElement).append("\n");
				}
			}
		}
		
		System.out.print(sb.toString());
	}
}
