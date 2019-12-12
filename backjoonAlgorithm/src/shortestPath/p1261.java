package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 알고스팟
// 다익스트라 알고리즘
public class p1261 {
	static int[][] map;
	static int[][] breaks;
	static boolean[][] visited;
	static int[] moveX = { 0, 0, -1, 1 };	// 상, 하, 좌, 우
	static int[] moveY = { -1, 1, 0, 0 };	// 상, 하, 좌, 우
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int m = Integer.valueOf(st.nextToken());	// 가로
		int n = Integer.valueOf(st.nextToken());	// 세로
		map = new int[n + 2][m + 2];	// 1, 1부터 시작
		breaks = new int[n + 2][m + 2];	// 해당 경로까지의 최단 부숨 ㅏㅋ운트
		visited = new boolean[n + 2][m + 2];
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			String line = st.nextToken();
			for(int j = 1; j <= m; j++) {
				// 아스키 코드 값이 아닌 바로 숫자로 변경
				int num = Character.getNumericValue(line.charAt(j - 1));	
				map[i][j] = num;
			}
		}
		
		int result = dijkstra(1, 1, n, m);
		System.out.println(result);
	}
	
	// 탐색 시작
	// 시작 x점, 시작 y점, n(세로), m(가로)
	private static int dijkstra(int startX, int startY, int n, int m) {
		PriorityQueue<Moving> q = new PriorityQueue<Moving>();
		
	
		Moving firstMove = new Moving(startX, startY, 0);
		q.offer(firstMove);
		breaks[startY][startX] = 0;	// 시작 지점 0
		visited[startY][startX] = true;
		while(!q.isEmpty()) {
			Moving nowMove = q.poll();
			int nowX = nowMove.getX();
			int nowY = nowMove.getY();
			
			if(nowX == m && nowY == n) {
				return nowMove.getBreakCount();
			}
			for(int i = 0; i < moveX.length; i++) {
				int nextX = nowX + moveX[i];
				int nextY = nowY + moveY[i];
				int nowBreak = breaks[nowY][nowX];
				if((nextX <= 0 || nextY <= 0) || (nextX > m || nextY > n)) {
					continue;
				}	
				
				// 해당 지점이 1인 경우에는 문을 부셔야 하니까 break 1 추가.
				if(map[nextY][nextX] == 1) {
					nowBreak++;
				}
				// 다익스트라 알고리즘 핵심.
				// 다른 방향으로 시작해서 먼저 온 경우의 문 부순 횟수보다 더 작게 부수고 도착한 경우 갱신 후 큐에 삽입
				if(!visited[nextY][nextX] || breaks[nextY][nextX] > nowBreak) {					
					breaks[nextY][nextX] = nowBreak;
					visited[nextY][nextX] = true;
					q.offer(new Moving(nextX, nextY, nowBreak));
				}	
			}
		}
		return breaks[n][m];
	}
}

class Moving implements Comparable<Moving> {
	private int x;
	private int y;
	private int breakCount;
	
	Moving(int x, int y, int breakCount) {
		this.x = x;
		this.y = y;
		this.breakCount = breakCount;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getBreakCount() {
		return breakCount;
	}

	public void setBreakCount(int breakCount) {
		this.breakCount = breakCount;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int compareTo(Moving o) {
		if(this.breakCount < o.getBreakCount()) {
			return -1;
		}
		else
			return 1;
	}
}