package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// ���ڰ���
// DFS
public class p2668 {
	static List<Integer> sameCardIndex;
	static int[] cards;
	static boolean[] checked;
	static boolean isCycle;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		
		// input �� �迭�� ���� 
		cards = new int[n + 1];
		checked = new boolean[n + 1]; // �ش� ī�� �̾Ҵ��� ���� Ȯ��
		sameCardIndex = new ArrayList<Integer>();
		for(int i = 1; i <= n; i++) {
			cards[i] = Integer.valueOf(br.readLine());
		}
		
		// �ε��� - �� �� ���� �� ã�� -> ������ ������ ���� �ֿ� ������ ���� �ʱ� ������.
		// �� ���� ������ � ī�带 �̵� �� ī�带 �̾ƾ� �ִ� ������ �����Ѵ�.
//		for(int i = 1; i <= n; i++) {
//			if(i == cards[i]) {
//				checked[i] = true;
//			}
//		}
		
		for(int i = 1; i <= n; i++) {
			if(i == cards[i]) {
				checked[i] = true;
				continue;
			}
			
			if(checked[i]) {
				continue;
			}
			
			isCycle = false;
			
			// ��Ʈ��ŷ 
			checked[i] = true;
			getCycle(i, i, n);
			if(!isCycle) {
				checked[i] = false;	
			}
		}
		
		int count = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n; i++) {
			if(checked[i]) {
				count++;
				sb.append(i).append("\n");
			}
		}
		
		System.out.println(count);
		System.out.println(sb.toString());
	}
	
	
	static boolean[] copyBoolArr(int n) {
		boolean[] temp = new boolean[n + 1];
		for(int i = 0; i < sameCardIndex.size(); i++) {
			temp[sameCardIndex.get(i)] = true;
		}
		
		return temp;
	}
	
	// ����Ŭ ã�Ƽ� ����Ʈ�� �����ϱ� 
	// DFS
	static void getCycle(int index, int last, int n) {
		int ele = cards[index];
		if(!checked[ele]) {
			checked[ele] = true;
			getCycle(ele, last, n);
			
			if(!isCycle) {
				checked[ele] = false;	
			}
		}
		
		if(ele == last) {
			isCycle = true;
		}
//		int count = 0;	// �� �Լ��� ȣ���� �� ��ü�� ī�带 ���� �� �̹Ƿ� 
//		int ele = cards[index];
//		boolean isPossible = true;
//		
//		while(ele != index) {
//			// ���� �� ���� ī�峪, �̹� �̾Ҵ� ī�峪 ������ ���� index�� ������� ������ Ʋ�� ��. 
//			if(checked[ele]) {
//				isPossible = false;
//				break;
//			}
//			
//			checked[ele] = true;
//			ele = cards[ele];
//			list.add(ele);
////			count++;
//		}
	}
	
	static int getCountOfCards(int index, boolean[] checked) {
		int count = 0;	// �� �Լ��� ȣ���� �� ��ü�� ī�带 ���� �� �̹Ƿ� 
		int ele = cards[index];
		int firstIndex = index;
		boolean isPossible = true;
		
		while(ele != firstIndex) {
			// ���� �� ���� ī�峪, �̹� �̾Ҵ� ī�峪 ������ ���� index�� ������� ������ Ʋ�� ��. 
			if(checked[ele]) {
				isPossible = false;
				break;
			}
			checked[ele] = true;
			ele = cards[ele];
			count++;
		}
		
		return isPossible ? count : 0;
	}
}
