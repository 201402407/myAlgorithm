package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

// 멀티탭 스케줄링
// 그리디 알고리즘
public class p1700 {
	static HashSet<Integer> multiTab = new HashSet<>();	// 1) set 멀티탭 구멍 선언
	static int[] flugs;
	static int tabUseSize = 0;
	static int count = 0;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		flugs = new int[k];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; i++) {
			flugs[i] = Integer.valueOf(st.nextToken());
		}
		

		// 가장 최적으로 빼는 기준?
		// -> 가장 나중에 재사용하거나 아예 안쓰는 경우에 지금 빼는게 낫지
		// -> 가장 빨리 재사용하는 경우에는 가장 늦게 빼는게 낫지
		//
		// 즉, 가장 나중에 재사용하거나 안쓰는 경우에 지금 빼는게 좋다.

//		2) set의 크기를 보고, N이면 멀티탭 전부 사용한것. N 이하이면 아직 남은것
		for(int i = 0; i < k; i++) {
			int flug = flugs[i];
			tabUseSize = multiTab.size();
			// 3-1) 아직 남았으면 set에 해당 멀티탭 담기(어차피 기존에 있어도 중복 제거되니까 상관없을듯)
			if(tabUseSize < n) {
				multiTab.add(flug);
				continue;
			}
			
			// 3-2) N으로 꽉차있으면
			if(!multiTab.contains(flug)) {	// set에서 get해서 해당하는게 나오면 패스
				// set 복사
				HashSet<Integer> tempSet = (HashSet<Integer>) multiTab.stream().collect(Collectors.toSet());

				boolean isRemoved = false;
				for(int j = i + 1; j < k; j++) {
					int nextFlug = flugs[j];
					if(tempSet.size() == 1) {	// 개수가 1개되면 해당 그거 get해서 그거 콘센트에서 뽑기
						int nowFlug = tempSet.iterator().next();
						isRemoved = true;
						multiTab.remove(nowFlug);
						break;
					}
					if(tempSet.contains(nextFlug)) {	// 다음 인덱스부터 for문 순회해서 해당 전자기기 있으면 set에서 제거
						tempSet.remove(nextFlug);
					}
				}
				
				if(!isRemoved) {	// 앞의 for문에서 제거하지 않은 경우
					int removeFlug = tempSet.iterator().next();
					multiTab.remove(removeFlug);
				}
				
				multiTab.add(flug);
				count++;
			}
		}
		
		System.out.println(count);
	}
}
