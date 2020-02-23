package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 녹색 옷 입은 애가 젤다지? 문제
// 다익스트라 알고리즘 사용(또는 BFS도 되지 않을까?
public class p4485 {
	// 상,하,좌,우
	static int[] moveX = { 0, 0, -1, 1 };
	static int[] moveY = { -1, 1, 0, 0 };
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		StringBuilder sb = new StringBuilder();
		int count = 0;
		while(true) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			if(n == 0) {
				break;
			}
			count++;
			int[][] map = new int[n][n]; // 0 ~ n-1 까지라고 해서
			int[][] resultMap = new int[n][n];
			boolean[][] visited = new boolean[n][n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				Arrays.fill(resultMap[i], Integer.MAX_VALUE);
				for(int j = 0; j < n; j++) {
					int num = Integer.valueOf(st.nextToken());
					map[i][j] = num;
				}
			}			
			int result = bfs(resultMap, map, visited, n);
			sb.append("Problem ").append(count).append(": ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	// bfs + 다익스트라 합쳐서 사용
	private static int bfs(int[][] resultMap, int[][] map, boolean[][] visited, int n) {
		PriorityQueue<Point> queue = new PriorityQueue<Point>();
		Point p = new Point(0, 0, map[0][0]);
		resultMap[0][0] = map[0][0];
		queue.add(p);
		
		while(!queue.isEmpty()) {
			Point prevPoint = queue.poll();
			int x = prevPoint.x;
			int y = prevPoint.y;
			int weight = prevPoint.weightSum;
			for(int i = 0; i < 4; i++) {
				int nextX = x + moveX[i];
				int nextY = y + moveY[i];
				if((nextX >= 0 && nextX < n) && (nextY >= 0 && nextY < n)) {
					if(weight + map[nextY][nextX] < resultMap[nextY][nextX]) {
						resultMap[nextY][nextX] = weight + map[nextY][nextX];
						queue.add(new Point(nextX, nextY, weight + map[nextY][nextX]));
					}
				}
			}
		}
		return resultMap[n - 1][n - 1];
	}

}

class Point implements Comparable<Point> {
	int x;
	int y;
	int weightSum;
	
	Point(int x, int y, int weightSum) {
		this.x = x;
		this.y = y;
		this.weightSum =weightSum;
	}

	@Override
	public int compareTo(Point o) {
		if(this.weightSum < o.weightSum) {
			return -1;
		}
		else
			return 1;
	}
}