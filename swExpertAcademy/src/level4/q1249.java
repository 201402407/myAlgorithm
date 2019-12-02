package level4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 4일차 - 보급로 문제
// DFS?
public class q1249 {
	static int[] moveX = {0, 0, -1, 1};	// 상, 하, 좌, 우
	static int[] moveY = {-1, 1, 0, 0};	// 상, 하, 좌, 우
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int c = Integer.valueOf(st.nextToken());	// 테스트 케이스 전체 경우
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i <= c; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			sb.append("#").append(i).append(" ");
			int[][] map = new int[n + 2][n + 2];	// 1부터 n까지
			int[][] distance = new int[n + 2][n + 2];	// 1부터 n까지
			for(int j = 1; j <= n; j++) {
				String str = new String(br.readLine());
				for(int k = 0; k < str.length(); k++) {
					map[j][k + 1] = str.charAt(k) - '0';	// 아스키 코드의 char이므로 아스키 코드 문자 0을 빼면 됨 
					distance[j][k + 1] = Integer.MAX_VALUE;
				}
			}
			int result = bfs(n, map, distance);
			sb.append(result).append("\n");
		}
		System.out.println(sb.toString());
		
	}
	
	static int bfs(int n, int[][] map, int[][] distance) {
		Point startPoint = new Point(1, 1, 0);	// 시작점
		PriorityQueue<Point> q = new PriorityQueue<Point>();
		q.offer(startPoint);
		while(!q.isEmpty()) {
			Point currentPoint = q.poll();
			int currentX = currentPoint.getX();
			int currentY = currentPoint.getY();
			int currentWeight = currentPoint.getWeight();
			if(currentX == n && currentY == n) {
				return currentWeight;
			}
			
			for(int i = 0; i < moveX.length; i++) {	// 상하좌우
				int nextX = currentX + moveX[i];
				int nextY = currentY + moveY[i];
				int nextWeight = currentWeight + map[nextY][nextX];
				if(nextX <= 0 || nextX >= n + 1 || nextY <= 0 || nextY >= n + 1) {	// 맵 벗어나면
					continue;
				}
				else {
					// 최단 거리로 이동한 경우와 아직 해당 좌표에 진입하지 않은 경우에 갱신과 큐에 넣기 위함.
					if(nextWeight < distance[nextY][nextX]) {
						Point nextPoint = new Point(nextX, nextY, nextWeight);
						distance[nextY][nextX] = nextWeight;
						q.offer(nextPoint);
					}
				}
			}
		}
		return distance[n][n];
		
	}
}

class Point implements Comparable<Point> {
	int x;
	int y;
	int weight;
	
	Point(int x, int y, int weight) {
		this.x = x;
		this.y = y;
		this.weight = weight;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int compareTo(Point o) {
		if(this.weight < o.weight) {
			return -1;
		}
		else {
			return 1;
		}
	}
}
