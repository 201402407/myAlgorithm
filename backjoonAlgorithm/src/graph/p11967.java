package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 불켜기
// 그래프, 구현
public class p11967 {
	static int n;
	static boolean[][] switched, visited;	
	static int[] moveX = {1, 0, -1, 0};
	static int[] moveY = {0, 1, 0, -1};
	static ArrayList<Point1967>[][] graph;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		switched = new boolean[n][n]; // true : 불킴 / false : 불끔
		visited = new boolean[n][n];	// 이미 방을 방문해서 불을 켰는지 안켰는지 체크
		
		// 2차원 ArrayList 배열  사용
		// 초기화
		graph = new ArrayList[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				graph[i][j] = new ArrayList<Point1967>();
			}
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken()) - 1;
			int y = Integer.valueOf(st.nextToken()) - 1;
			int a = Integer.valueOf(st.nextToken()) - 1;
			int b = Integer.valueOf(st.nextToken()) - 1;
			
			// 그래프 생성
			Point1967 endPoint = new Point1967(a, b);
			graph[y][x].add(endPoint);	// 단방향 그래프
		}
		
		// 시작
		int count = bfs() + 1;	// (0,0) 불켜져있음
		
		System.out.println(count);
	}
	
	static boolean isNotRange(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= n;
	}
	
	// 무조건 0,0 부터 시작하는 BFS
	static int bfs() {
		int count = 0;
		Queue<Point1967> queue = new LinkedList<>();
		queue.offer(new Point1967(0, 0));
		// 방문 초기화
		for(int i = 0; i < n; i++) {
			Arrays.fill(visited[i], false);	
		}
		
		switched[0][0] = true;
		visited[0][0] = true;
		
		// 한 번 시작해서 BFS 탐색하는 경우 전부 탐색
		// 불 킨 적이 없을 때까지 BFS 함수 반복
		boolean turnOn = false;
		while(!queue.isEmpty()) {
			Point1967 p = queue.poll();
			
			for(Point1967 neighborPoint : graph[p.y][p.x]) {
				if(!switched[neighborPoint.y][neighborPoint.x]) {
					switched[neighborPoint.y][neighborPoint.x] = true;
					count++;
					turnOn = true;
				}
			}
			
			// 상하좌우 이동가능한지 체크
			for(int i = 0; i < moveX.length; i++) {
				int nextX = p.x + moveX[i];
				int nextY = p.y + moveY[i];
				if(isNotRange(nextX, nextY)) {
					continue;
				}
				
				// 불이 꺼져있거나 방문했던 적이 있으면
				if(!switched[nextY][nextX] || visited[nextY][nextX]) {
					continue;
				}
				
				queue.offer(new Point1967(nextX, nextY));
				visited[nextY][nextX] = true;
			}
		}
		
		// 불 하나라도 켰으면 0,0 부터 BFS 탐색. 이미 지나온 방 중 이어질 수 있으므로
		if(turnOn) {
			count += bfs();
		}
		
		return count;
	}
}

class Point1967 {
	int x;
	int y;
	
	Point1967(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
