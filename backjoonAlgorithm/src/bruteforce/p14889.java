package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 스타트와 링크
// 브루트 포스
public class p14889 {
	static int[][] map;
	static int n;
	static int gap = Integer.MAX_VALUE;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.valueOf(st.nextToken());
		map = new int[n + 1][n + 1];
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				int stat = Integer.valueOf(st.nextToken());
				map[i][j] = stat;
			}
		}
		
		// 팀을 구분지을 필요가 없어 첫 번째를 True로 놓고 두 번째부터 진행
		// ex) T ~ / F ~ 두 가지 전부 같은 결과 값을 가짐.
		boolean[] team = new boolean[n + 1];
		team[1] = true;
		backTracking(team, 2, (n / 2) - 1);
		System.out.println(gap);
	}
	
	static void backTracking(boolean[] team, int index, int count) {
		if(index > n) {
			if(count == 0) {
				countMinStats(team);
			}
			return;
		}
		
		if(count == 0) {
			countMinStats(team);
		}
		else {
			team[index] = true;
			backTracking(team, index + 1, count - 1);
			team[index] = false;
			backTracking(team, index + 1, count);
		}
	}
	
	// 점수 계산하는 함수
	static void countMinStats(boolean[] team) {
		int countA = 0;
		int countB = 0;
		for(int y = 1; y < n; y++) {
			boolean firstCheck = team[y];
			for(int x = y + 1; x <= n; x++) {
				boolean secondCheck = team[x];
				if(firstCheck == secondCheck) {
					int stat = map[y][x] + map[x][y];
					// 팀 능력치 계산
					if(firstCheck) {
						countA += stat;
					}
					else {
						countB += stat;
					}
				}
			}
		}
//		System.out.println(countA + " , " + countB);
		gap = Math.min(gap, Math.abs(countA - countB));
	}
}
