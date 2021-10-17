package silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// 카드놓기
// ArrayDeque
public class p18115 {
	static int n;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());
		Deque<Integer> myCards = new ArrayDeque<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] skills = new int[n];
		
		for(int i = 0; i < n; i++) {
			skills[i] = Integer.valueOf(st.nextToken());
		}
		// 입력의 n-1부터 0번째까지 탐색
		// n-1 = 1, 0 = n 숫자로 가정하고 대입해서 하나씩 활용
		int num = 1;
		for(int i = n - 1; i >= 0; i--) {
			switch(skills[i]) {
			case 1:	// 맨 앞 add
				myCards.addFirst(num);
				break;
			case 2:	// 맨 앞에서 두번째 add
				int first = myCards.pollFirst();	// 맨 앞 pop하고
				myCards.addFirst(num);		// 두 번째 숫자 넣고
				myCards.addFirst(first);	// pop한 첫 번째 숫자 넣기
				break;
			case 3:	// 마지막 push
				myCards.addLast(num);
				break;
			}
			
			num++;
		}
		
		// 결과 출력
		StringBuilder sb = new StringBuilder();
		for(int ele : myCards) {
			sb.append(ele).append(" ");
		}
		
		System.out.println(sb.toString());
	}
}
