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
	
	// ����Ʈ ����.
	// �ʱ� ��� ��� ���� -1�� ����
	// �θ� ���� �����μ� �ڽĳ����� ũ�⸦ ��� �ְ�, �ڽ� ������ �θ� ����� �ε����� ������ ����
	// �� �� �θ� ����� ��� �� �񱳸� ���� ������ ���� �θ� ��带 ū �θ� ��忡 �̾���δ�.
	// ���� �ڽ� ���� ���� �������� �ʰ� �θ� ��常 �� �� �ٲ������ ������ find �Լ��� ���� �ֻ�� �θ� ��带 ã�� �� ����
	// �׷��� ũ�� ������ �����ϰ� ��
	// ���� ��.
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
	
	// �θ� ��� ��ġ��
	// �ϳ��� ��尡 �ű� ������ص� ���� ���� �����ϴ�.
//	static int union(int aIndex, int bIndex) {
//		aIndex = find(aIndex);
//		bIndex = find(bIndex);
//		int size = 0;
//		
//		if(aIndex != bIndex) {
//			// ��� bIndex�� �ش��ϴ� ����� �θ� ��� ����
//			// ���� ����
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
//			// ���� ����
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
	
	// ��Ʈ ��带 ã�� �Լ�
	static int find(int index) {
//		if(disjointSet[index] == index) {	// �θ� ����� ���� ����(����)�̱� ������ �� �ڵ�� ����� �� ����
//			return index;
//		}
		
		if(disjointSet[index] < 0) {	// �θ� ����� ���� ����(����)�̱� ������ �� �ڵ�� ����� �� ����
			return index;
		}
		return disjointSet[index] = find(disjointSet[index]);
	}
	
	
//	// ���� �̸��� �̹� �����ִ��� üũ
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