package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// �˰���
// ���ͽ�Ʈ�� �˰���
public class p1261 {
	static int[][] map;
	static int[][] breaks;
	static boolean[][] visited;
	static int[] moveX = { 0, 0, -1, 1 };	// ��, ��, ��, ��
	static int[] moveY = { -1, 1, 0, 0 };	// ��, ��, ��, ��
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int m = Integer.valueOf(st.nextToken());	// ����
		int n = Integer.valueOf(st.nextToken());	// ����
		map = new int[n + 2][m + 2];	// 1, 1���� ����
		breaks = new int[n + 2][m + 2];	// �ش� ��α����� �ִ� �μ� ������Ʈ
		visited = new boolean[n + 2][m + 2];
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			String line = st.nextToken();
			for(int j = 1; j <= m; j++) {
				// �ƽ�Ű �ڵ� ���� �ƴ� �ٷ� ���ڷ� ����
				int num = Character.getNumericValue(line.charAt(j - 1));	
				map[i][j] = num;
			}
		}
		
		int result = dijkstra(1, 1, n, m);
		System.out.println(result);
	}
	
	// Ž�� ����
	// ���� x��, ���� y��, n(����), m(����)
	private static int dijkstra(int startX, int startY, int n, int m) {
		PriorityQueue<Moving> q = new PriorityQueue<Moving>();
		
	
		Moving firstMove = new Moving(startX, startY, 0);
		q.offer(firstMove);
		breaks[startY][startX] = 0;	// ���� ���� 0
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
				
				// �ش� ������ 1�� ��쿡�� ���� �μž� �ϴϱ� break 1 �߰�.
				if(map[nextY][nextX] == 1) {
					nowBreak++;
				}
				// ���ͽ�Ʈ�� �˰��� �ٽ�.
				// �ٸ� �������� �����ؼ� ���� �� ����� �� �μ� Ƚ������ �� �۰� �μ��� ������ ��� ���� �� ť�� ����
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