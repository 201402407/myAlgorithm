package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 아이 러브 크로아티아 문제
// 시뮬레이션
public class p9517 {
	static int player, nowTime;	// player : 1 ~ 8
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		player = Integer.valueOf(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int question = Integer.valueOf(st.nextToken());
		
		for(int i = 0; i < question; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.valueOf(st.nextToken());
			String answer = st.nextToken();
			if(nowTime + time > 210) {	// 시간 초과될 경우
				break;
			}
			if(answer.equals("T")) {	// 정답 맞출 경우
				if(++player > 8) {
					player = 1;
				}
			}
			nowTime += time;
		}
		System.out.println(player);
	}
}
