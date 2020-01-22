package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// ���ڿ� ó��
// ����� ��� ����?
// �ܾ�� �߿��� �ش� �ܾ ������ �Ҹ��� ����ϴ� ����
public class p9536 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.valueOf(st.nextToken());
		for(int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			List<String> list = new LinkedList<String>();
			int tokenCount = st.countTokens();
			for(int j = 0; j < tokenCount; j++) {
				list.add(st.nextToken());
			}
			String str;
			while(!(str = br.readLine()).equals("what does the fox say?")) {
				String sound = str.split(" ")[2];	// �Ҹ��� ���� ���ڿ� ����	
				removeOtherSound(list, sound);	// ��ü �Ҹ����� ����
			}
			
			// ArrayList(�迭)���� for���� ������, �� �ܿ��� for-each�� ������! �迭���� �ε����� �ֱ� ������.
			for(String ele : list) {
				sb.append(ele).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void removeOtherSound(List<String> list, String sound) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).equals(sound)) {
				list.remove(i--);
			}
		}
	}
}
