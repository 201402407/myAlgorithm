package anything;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// 단어 정렬
public class p1181 {
	static List<String>[] list;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		list = new ArrayList[51];
		// 각 인덱스마다 객체 생성
		for(int i = 1; i < list.length; i++) {
			list[i] = new ArrayList<String>();
		}
		
		// 한 글자에 해당하는 길이를 인덱스로 삼아 ArrayList 배열에 넣는다.
		for(int j = 0; j < n; j++) {
			String value = br.readLine();
			list[value.length()].add(value);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int k = 1; k < list.length; k++) {
			if(list[k].size() == 0) {	// 빈 사이즈면 끝
				continue;
			}
			else {	// 하나라도 들어가있다면
				Collections.sort(list[k]);
				// 우선 가장 첫 번째 원소 넣고
				String prevElement = list[k].get(0);
				sb.append(prevElement).append("\n");
				for(int x = 1; x < list[k].size(); x++) {	// 중복 제거를 위한 반복문
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
