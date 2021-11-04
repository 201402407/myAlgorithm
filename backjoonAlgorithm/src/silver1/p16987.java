package silver1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 계란으로 계란치기
// 백트래킹(또는 DFS로 풀어도 됨. DFS가 더 빠르대!)
public class p16987 {
	static int n;
	static int[] ad;	// 공격력(무게)
	static int maxCount = 0;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());
		int[] hp = new int[n];	// 체력(내구도)
		ad = new int[n];
		StringTokenizer st;
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			hp[i] = Integer.valueOf(st.nextToken());
			ad[i] = Integer.valueOf(st.nextToken());
		}
		
		backTracking(hp, 0);
		System.out.println(maxCount);
	}
	
	// 백트래킹 수행
	static void backTracking(int[] nowHp, int index) {
		if(index >= n) {	// 이미 한 바퀴 계란을 다 손으로 잡은 경우
			maxCount = Math.max(maxCount, getCountOfBreakedEggs(nowHp));
			return;
		}
		
		// 손에 든 계란이 깨져있으면 바로 오른쪽 계란 선택
		if(nowHp[index] <= 0) {
			backTracking(nowHp, index + 1);
			return;
		}
		
		boolean isBreak = false;	// 계란을 깼었는지 안깼었는지 체크
		for(int i = 0; i < n; i++) {
			// 현재 들고 있는 계란인 경우
			if(i == index) {
				continue;
			}
			
			// 이미 깨진 계란인 경우
			if(nowHp[i] <= 0) {
				continue;
			}
			
			isBreak = true;
			nowHp[i] -= ad[index];
			nowHp[index] -= ad[i];
			backTracking(nowHp, index + 1);
			nowHp[i] += ad[index];
			nowHp[index] += ad[i];
		}
		
		if(!isBreak) {
			backTracking(nowHp, index + 1);
		}
	}
	
	// 깨진 계란 개수 세기
	static int getCountOfBreakedEggs(int[] hp) {
		int count = 0;
		for(int ele : hp) {
			if(ele <= 0) {
				count++;
			}
		}
		
		return count;
	}
}
