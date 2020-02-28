package dfsnfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 나이트의 이동 문제
// BFS 사용하기
public class p7562 {
	static int[][] distanceMap;
	static boolean[][] visited;
	static int[] moveX = {1, 2, 2, 1, -1, -2, -2, -1};	// 시계 방향 순서
	static int[] moveY = {-2, -1, 1, 2, 2, 1, -1, -2};
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int tc = Integer.valueOf(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		while(tc --> 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			distanceMap = new int[n][n];
			visited = new boolean[n][n];
			
			st = new StringTokenizer(br.readLine());
			int startX = Integer.valueOf(st.nextToken());
			int startY = Integer.valueOf(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int endX = Integer.valueOf(st.nextToken());
			int endY = Integer.valueOf(st.nextToken());
			
			int result = bfs(n, startX, startY, endX, endY);
			sb.append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
	private static int bfs(int n, int startX, int startY, int endX, int endY) {
		PriorityQueue<Point7562> queue = new PriorityQueue<Point7562>();
		queue.offer(new Point7562(startX, startY, 0));
		visited[startY][startX] = true;
		
		while(!queue.isEmpty()) {
			Point7562 currentPoint = queue.poll();
			int currentX = currentPoint.x;
			int currentY = currentPoint.y;
			if(currentX == endX && currentY == endY) {
				return currentPoint.count;
			}
			
			for(int i = 0; i < moveX.length; i++) {
				int nextX = currentX + moveX[i];
				int nextY = currentY + moveY[i];
				if((nextX < 0 || nextX >= n) || (nextY < 0 || nextY >= n)) {	// 체스 판 범위 내 여야 함.
					continue;
				}
				if(visited[nextY][nextX])
					continue;
				// 한 번도 방문 안한 좌표 점의 count
				if(distanceMap[nextY][nextX] == 0 || currentPoint.count + 1 < distanceMap[nextY][nextX]) {
					distanceMap[nextY][nextX] = currentPoint.count + 1;
					visited[nextY][nextX] = true;
					queue.offer(new Point7562(nextX, nextY, distanceMap[nextY][nextX]));
				}
			}
		}
		return distanceMap[endY][endX];
	}
}

class Point7562 implements Comparable<Point7562> {
	int x;
	int y;
	int count;
	
	Point7562(int x, int y, int count) {
		this.x = x;
		this.y = y;
		this.count = count;
	}

	@Override
	public int compareTo(Point7562 o) {
		if(this.count < o.count ) {	// 오름차순
			return -1;
		}
		else
			return 1;
	}
	
}
