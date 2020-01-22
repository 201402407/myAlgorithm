package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// 문자열 처리
// 여우는 어떻게 울지?
// 단어들 중에서 해당 단어를 제외한 소리를 출력하는 문제
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
				String sound = str.split(" ")[2];	// 소리만 따로 문자열 추출	
				removeOtherSound(list, sound);	// 전체 소리에서 삭제
			}
			
			// ArrayList(배열)에는 for문이 빠르고, 그 외에는 for-each가 빠르다! 배열에는 인덱스가 있기 때문에.
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
