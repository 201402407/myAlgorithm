package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 숫자고르기
// DFS
public class p2668 {
	static int[] cards;
	static boolean[] checked;
	static boolean isCycle;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		
		// input 값 배열에 저장 
		cards = new int[n + 1];
		checked = new boolean[n + 1]; // 해당 카드 뽑았는지 여부 확인
		for(int i = 1; i <= n; i++) {
			cards[i] = Integer.valueOf(br.readLine());
		}
		
		// 인덱스 - 값 이 같은 쌍 찾기 -> 무조건 각각의 집합 쌍에 영향을 주지 않기 때문에.
		// 두 값이 같으면 어떤 카드를 뽑든 이 카드를 뽑아야 최대 개수가 증가한다.
		for(int i = 1; i <= n; i++) {
			if(i == cards[i]) {
				checked[i] = true;
				continue;
			}
			
			if(checked[i]) {
				continue;
			}
			
			isCycle = false;
			
			// 백트래킹 
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
	
	// 사이클 찾아서 리스트에 저장하기 
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
