package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// ?��?��고르�?
// DFS
public class p2668 {
	static int[] cards;
	static boolean[] checked;
	static boolean isCycle;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		
		// input �? 배열?�� ???�� 
		cards = new int[n + 1];
		checked = new boolean[n + 1]; // ?��?�� 카드 뽑았?���? ?���? ?��?��
		for(int i = 1; i <= n; i++) {
			cards[i] = Integer.valueOf(br.readLine());
		}
		
		// ?��?��?�� - �? ?�� 같�? ?�� 찾기 -> 무조�? 각각?�� 집합 ?��?�� ?��?��?�� 주�? ?���? ?��문에.
		// ?�� 값이 같으�? ?��?�� 카드�? 뽑든 ?�� 카드�? 뽑아?�� 최�? 개수�? 증�??��?��.
		for(int i = 1; i <= n; i++) {
			if(i == cards[i]) {
				checked[i] = true;
				continue;
			}
			
			if(checked[i]) {
				continue;
			}
			
			isCycle = false;
			
			// 백트?��?�� 
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
	
	// ?��?��?�� 찾아?�� 리스?��?�� ???��?���? 
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
	}
}