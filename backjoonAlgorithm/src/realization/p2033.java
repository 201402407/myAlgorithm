package realization;

import java.util.Scanner;

// 수학, 구현 
// 반올림 
public class p2033 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		char[] chArr = str.toCharArray();
		int len = chArr.length;
		
		int nextAdd = 0;
		StringBuilder sb = new StringBuilder();
		// 일의 자리부터 거슬러 올라가야 하므로 역순 탐색
		for(int i = len - 1; i >= 1; i--) {
			int num = (int) chArr[i] - '0';
			int nowNum = num + nextAdd;
			nextAdd = nowNum >= 5 ? 1 : 0;
			sb.append(0);
		}
		
		// 가장 맨 앞자리부터 반올림한 결과 얻기
		sb.insert(0, (int)(chArr[0] - '0') + nextAdd);
		System.out.println(sb.toString());
		
		
		/** 수학 식으로 접근하는 방법
		 * @author devetude
		 // 비교 변수 초기화
		int comp = 10;

		// 루프를 돌며 문제의 조건에 맞게 반올림 실행
		while (N > comp) {
			int nModComp = N % comp;

			if (nModComp * 10 / comp >= 5) {
				N += comp;
			}

			N -= nModComp;
			comp *= 10;
		}
		 */
	}
	
	
}
