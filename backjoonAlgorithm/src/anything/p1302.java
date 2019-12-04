package anything;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;

// 베스트셀러
// 해시 맵 사용
public class p1302 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
//		HashMap<String, Integer> map = new HashMap<String, Integer>();
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String key = st.nextToken();
			Integer value = map.get(key);
			if(value == null) {	// 해시 맵에 저장된 Key(책 이름)이 없는 경우
				map.put(key, 1);
			}
			else {	// 기존에 한 번이라도 key(책 이름)의 책이 팔린 경우
				map.put(key, value.intValue() + 1);
			}
		}
		
		Integer max = Collections.max(map.values());
//		TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>(map);
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			if(map.get(key) == max) {
				System.out.println(key);
				break;
			}
		}
	}
}
