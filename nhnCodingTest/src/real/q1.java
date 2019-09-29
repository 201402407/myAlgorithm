package real;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class q1 {
	static HashMap<String, Integer> map = new HashMap<String, Integer>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		st = new StringTokenizer(br.readLine());
		String temp = null;
		for(int i = 0; i < n; i++) {
			String element = st.nextToken();
			temp = element;
			if(map.containsKey(element)) {
				map.put(element, map.get(element) + 1);
				continue;
			}
			else { 
				map.put(element, 1);
			}
		}
		StringBuilder sb = new StringBuilder();
		int sortCount = 0;
		String changedIndex;
		boolean isChanged = false;
		boolean isFinish = false;
		boolean isOk = false;
		int prevCount = -1;
		
		List<String> sorted = getListOfSortedMapKey(map);
		
		for(int i = 0; i < sorted.size(); i++) {
			if(i + 1 == sorted.size()) {
				break;
			}
			int count = map.get(sorted.get(i));
			int nextCount = map.get(sorted.get(i + 1));
			if(count == nextCount) {
				continue;
			}
			if(count + 1 <= nextCount) {
				if(count + 1 == nextCount) {
					if(isChanged) {
						sb.append("N").append("\n");
						sb.append(n).append("\n");
						sb.append(sorted.size()).append("\n");
						isFinish = true;
						isOk = false;
						break;
					}
					if(i == 1) {
						isOk = true;
						isChanged = true;
						continue;
					}
					isChanged = true;
					continue;
				}
				if(count + 1 < nextCount) {
					sb.append("N").append("\n");
					sb.append(n).append("\n");
					sb.append(sorted.size()).append("\n");
					isFinish = true;
					break;
				}
			}
		}
		
		for(String key : sorted) {
			int count = map.get(key);
			if(sortCount == 0) {
				prevCount = count;
				sortCount++;
				continue;
			}
			if(prevCount != count) {
				if(prevCount + 1 == count) {
					
				}
			}
		}
		
		if(isChanged) {
			if(isFinish) {
				System.out.print(sb.toString());
			}
			else if(isOk) {
				sb.append("N").append("\n");
				sb.append(n + 1).append("\n");
				sb.append(sorted.size()).append("\n");
				System.out.print(sb.toString());
			}
			else {
				sb.append("Y").append("\n");
				sb.append(n).append("\n");
				sb.append(sorted.size()).append("\n");
				System.out.print(sb.toString());
			}
		}
		else {
			if(isFinish) {
				System.out.print(sb.toString());
			}
			else {
				sb.append("Y").append("\n");
				sb.append(n).append("\n");;
				sb.append(sorted.size()).append("\n");;
				System.out.print(sb.toString());
			}
		}
	}
	
	// 정렬하는 함수
    public static List<String> getListOfSortedMapKey(HashMap<String, Integer> map) {
        List<String> list = new ArrayList<String>();
        list.addAll(map.keySet());
        Collections.sort(list, new Comparator<String>() {
           @Override
           public int compare(String o1, String o2) {
              int a1 = map.get(o1);
              int a2 = map.get(o2);
               // 재생된 횟수는 다르다고 했으므로
              if(a1 < a2)
                  return -1;
              else if(a1 == a2)
                  return 0;
              else
                  return 1;    // -1이 이 순서대로 고정시키자! 뜻
          } 
        });
        // Collections.reverse(list);	// 역순 정렬
        return list;
    }
}
