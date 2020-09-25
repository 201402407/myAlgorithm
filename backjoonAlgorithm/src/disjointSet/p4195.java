package disjointSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

// Disjoint-set
// union-find 
public class p4195 {
	static int[] disjointSet;
	static HashMap<String, Integer> names;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int tc = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		while(tc --> 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			disjointSet = new int[(2 * n) + 1];
			Arrays.fill(disjointSet, -1);
			names = new HashMap<String, Integer>();
			int nowIndex = 1;
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				int size = 0;
				int aIndex, bIndex;
				
				if(names.containsKey(a)) {
					aIndex = names.get(a);
				}
				else {
					names.put(a, nowIndex);
					aIndex = nowIndex++;
				}
				
				if(names.containsKey(b)) {
					bIndex = names.get(b);
				}
				else {
					names.put(b, nowIndex);
					bIndex = nowIndex++;
				}
				
				size = -merge(aIndex, bIndex);
				sb.append(size).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	// 베스트 정답.
	// 초기 모든 노드 값을 -1로 설정
	// 부모 노드는 음수로서 자식노드들의 크기를 담고 있고, 자식 노드들은 부모 노드의 인덱스를 가지고 있음
	// 둘 다 부모 노드일 경우 값 비교를 통해 절댓값이 작은 부모 노드를 큰 부모 노드에 이어붙인다.
	// 굳이 자식 노드들 전부 변경하지 않고 부모 노드만 한 번 바꿔놓으면 어차피 find 함수를 통해 최상단 부모 노드를 찾을 수 있음
	// 그래서 크기 계산까지 가능하게 됨
	// 그저 갓.
	private static int merge(int aIndex, int bIndex) {
		aIndex = find(aIndex);
		bIndex = find(bIndex);
		
		if(aIndex != bIndex) {
			if(disjointSet[aIndex] > disjointSet[bIndex]) {
				disjointSet[bIndex] += disjointSet[aIndex];
				disjointSet[aIndex] = bIndex;
			}
			else {
				disjointSet[aIndex] += disjointSet[bIndex];
				disjointSet[bIndex] = aIndex;
			}
		}
		
		return disjointSet[aIndex] < 0 ? disjointSet[aIndex] : disjointSet[bIndex];
	}
	
	// 부모 노드 합치기
	// 하나의 노드가 신규 노드라고해도 정상 진행 가능하다.
//	static int union(int aIndex, int bIndex) {
//		aIndex = find(aIndex);
//		bIndex = find(bIndex);
//		int size = 0;
//		
//		if(aIndex != bIndex) {
//			// 모든 bIndex에 해당하는 노드의 부모 노드 변경
//			// 갯수 세기
//			for(int i = 1; i < disjointSet.length; i++) {
//				int parentIndex = disjointSet[i];
//				if(parentIndex == 0) {
//					break;
//				}
//				
//				if(parentIndex == aIndex) {
//					size++;
//				}
//				
//				if(parentIndex == bIndex) {
////					find(parentIndex);
//					disjointSet[i] = aIndex;
////					disjointSet.set(i, aIndex);
//					size++;
//				}
//			}
//		}
//		else {
//			// 갯수 세기
//			for(int i = 1; i < disjointSet.length; i++) {
//				int parentIndex = disjointSet[i];
//				
//				if(parentIndex == 0) {
//					break;
//				}
//				
//				if(parentIndex == aIndex) {
//					size++;
//				}
//			}
//		}
//		
//		return size;
//	}
	
	// 루트 노드를 찾는 함수
	static int find(int index) {
//		if(disjointSet[index] == index) {	// 부모 노드의 값이 개수(음수)이기 때문에 이 코드는 사용할 수 없음
//			return index;
//		}
		
		if(disjointSet[index] < 0) {	// 부모 노드의 값이 개수(음수)이기 때문에 이 코드는 사용할 수 없음
			return index;
		}
		return disjointSet[index] = find(disjointSet[index]);
	}
	
	
//	// 현재 이름이 이미 들어와있는지 체크
//	static int[] getIndicesOfName(String a, String b) {
//		int[] result = new int[2];
//		Arrays.fill(result, -1);
//		
//		if(names.containsKey(a)) {
//			result[0] = names.get(a);
//		}
//		if(names.containsKey(b)) {
//			result[1] = names.get(b);
//		}
//		
//		return result;
//	}
}