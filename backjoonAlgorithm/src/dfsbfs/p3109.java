package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 빵집
//그리디 + 백트래킹 문제 
public class p3109 {
	static int r, c;
	static char[][] map;
	static int[] moveY = {-1, 0, 1};	// X좌표는 무조건 1 더하면 되므로 Y좌표 이동 배열만 생성 
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.valueOf(st.nextToken());
		c = Integer.valueOf(st.nextToken());
		map = new char[r][c];
		
		// 맵 입력받기 
		for(int i = 0; i < r; i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
		}
		
//		boolean[][] visited = new boolean[r][c];
		// 1열에서 출발하니까 
		int count = 0;
		for(int i = 0; i < r; i++) {
			if(backTracking(0, i)) {
				count++;
			}
		}
		
		System.out.println(count);
		
	}
	
	static boolean backTracking(int x, int y) {
		if(x + 1 == c) {
			return true;
		}
		
		// 파이프 연결을 위해 우로 이동하는 경우 3가지 
		int nextX = x + 1;
		for(int i = 0; i < 3; i++) {
			int nextY = y + moveY[i];
			if(nextY >= 0 && nextY < r) {	// 맵에 들어오는 경우
				if(map[nextY][nextX] == 'x') {
					continue;
				}
				
				// 백트래킹 시작
                map[y][x] = 'x';
				// 양 끝 파이프가 연결되었다는 뜻이므로 언능 종료시키기
                // 이 지점에서 다음에 연결할 파이프 자리가 없으면 마찬가지로
                // 다른 파이프가 이 지점에 와도 갈 곳이 없는 것
                // 그래서 True만 리턴시킨다.
				if(backTracking(nextX, nextY)) {
					return true;
				}
			}
		}
		
		// 갈 곳이 더이상 없는 경우
		return false;
	}
}