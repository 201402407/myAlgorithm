package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 크게 만들기
// 그리디
public class p2812 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		
		String number = br.readLine();
		
		// 1) n - k 개만큼 LinkedList에 쌓아두기
		LinkedList<Integer> list = new LinkedList<>();
		for(int i = 0; i < n - k; i++) {
			list.offer(Character.getNumericValue(number.charAt(i)));
		}
		
		// 2) 한개씩 꺼내 비교. List 앞에서부터 탐색
		// -> 현재 < 다음인 경우 현재값 제거
		// 		-> 하나라도 없으면 일의 자리와 크기 비교
		//			-> 일의 자리가 크면 새로 들어온 값을 제거
		//			-> 일의 자리가 작으면 일의 자리에 새로 들어온 값 넣기
		for(int i = n - k; i < n; i++) {
			int num = Character.getNumericValue(number.charAt(i));
			int changeIndex = getChangeIndex(list, num);
			if(changeIndex == -1) {
				// 일의 자리 비교
				int last = list.peekLast();
				if(last < num) {
					list.pollLast();
					list.offer(num);
				}
			}
			else {
				list.remove(changeIndex);
				list.offer(num);
//				list.set(changeIndex, num);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int num : list) {
			sb.append(num);
		}
		
		System.out.println(sb.toString());
	}
	
	// 현재값 < 다음값 인 현재 인덱스 구하기
	static int getChangeIndex(LinkedList<Integer> list, int num) {
		Iterator<Integer> iterators = list.iterator();
		int index = 0;
		boolean isBigger = false;
		int nowNum = iterators.next();
		
		while(iterators.hasNext()) {
			int nextNum = iterators.next();
			if(nowNum < nextNum) {
				isBigger = true;
				break;
			}
			
			index++;
			nowNum = nextNum;
		}
		
		return isBigger ? index : -1;
	}
}
