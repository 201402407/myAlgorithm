package dynamic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 계단 오르기 1등 정답
public class p2579_bestAnswer {
	public static void main(String arg[])throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int map[] = new int[n+1];		// 계단 별 점수를 적어놓은 배열
		int d[][] = new int[n+1][3];	// 계단 별 경우에 따른 누적 점수를 적어놓은 2차원 배열.
		for(int i = 1 ; i<=n ; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		d[1][1] = map[1];	// 1번 째 계단에 연속해서 한 칸씩 한 번 올라온 경우
		d[2][1] = map[2];	// 2번 째 계단에 연속해서 한 칸씩 한 번 올라온 경우
		d[2][2] = map[1]+map[2];	// 2번 째 계단에 연속해서 한 칸씩 두 번 올라온 경우
		for(int i=3 ; i<=n ; i++) {
			// i번 째 계단에 연속해서 한 번 올라온 경우
			// 이는 2번 째 계단 전에서 두 칸을 올라온 경우밖에 없으므로, 두 경우 중 높은 값을 선택한 뒤 i번 째 계단의 점수를 더한다.
			d[i][1] = Math.max(d[i-2][1],d[i-2][2])+map[i];
			// i번 째 계단에 연속해서 한 칸씩 두 번 올라온 경우
			d[i][2] = d[i-1][1]+map[i];		
		}
		System.out.println(Math.max(d[n][1], d[n][2]));
	}	
}
