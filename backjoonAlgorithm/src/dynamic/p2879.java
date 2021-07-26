package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 구현
// 다이나믹 프로그래밍
// 코딩은 예쁘게
public class p2879 {
	static int[] nowTabs, resultTabs, gapTabs;	// 현재 줄에 있는 탭 수, 변경해야할 탭 수
	static int editCount;	// 편집 횟수
	static boolean isPlus = false;	// true : 탭 개수차이 plus
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		nowTabs = new int[n];
		resultTabs = new int[n];
		gapTabs = new int[n];
		
		// 현재 줄에 있는 탭 수
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int tab = Integer.valueOf(st.nextToken());
			nowTabs[i] = tab;
		}
		
		// 변경해야할 탭 수
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int tab = Integer.valueOf(st.nextToken());
			resultTabs[i] = tab;
		}
		
//		1) 정답과 처음 차이 값 저장
//		2) 첫 번째 +/- 여부와 차이의 절댓값만큼 값 초기 설정
//		3) 두 번째부터 for문 수행 
//		3-1) 이전 값과 다른 부호인 경우
//		3-2) 이전 값과 같은 부호인 경우
//		3-2-1) 이전 값보다 큰 경우 -> 현재 값 - 이전 값 만큼 탭 횟수 증가
//		3-2-2) 이전 값보다 작은 경우 -> PASS
		
		// 변경 - 현재 탭 수 차이 구하기
		for(int i = 0; i < n; i++) {
			gapTabs[i] = nowTabs[i] - resultTabs[i];
		}
		
		// N >= 1 이기 때문
		editCount = Math.abs(gapTabs[0]);
		isPlus = gapTabs[0] >= 0 ? true : false;
		
		for(int i = 1; i < n; i++) {
			boolean nowSignPlus = gapTabs[i] >= 0 ? true : false;
			int absNow = Math.abs(gapTabs[i]);
			int absPrev = Math.abs(gapTabs[i - 1]);
			
//			3-1) 이전 값과 다른 부호인 경우
			if(nowSignPlus != isPlus) {
				// 현재 탭 차이 수 추가
				editCount += absNow;
				isPlus = nowSignPlus;
				continue;
			}
			
//			3-2) 이전 값과 같은 부호인 경우
//			3-2-1) 이전 값보다 큰 경우 -> 현재 값 - 이전 값 만큼 탭 횟수 증가
			if(absNow > absPrev) {
				editCount += absNow - absPrev;
			}
			
//			3-2-2) 이전 값보다 작은 경우 -> PASS
		}
		
		System.out.println(editCount);
	}
}
