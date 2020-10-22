package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 늑대사냥꾼
// BFS, 다익스트라  
public class p2917 {
	static int n, m;
	static char[][] map;
	static int[][] dp;
	static int[] moveX = {0, 1, 0, -1};
	static int[] moveY = {-1, 0, 1, 0};
	static Queue<Point2917> trees;
	static int hyunwooX, hyunwooY, hutX, hutY;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		trees = new LinkedList<Point2917>();
		dp = new int[n][m];
		map = new char[n][m];
		
		for(int i = 0; i < n; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
			String line = br.readLine();
			
			for(int j = 0; j < line.length(); j++) {
				char ch = line.charAt(j);
				if(ch == '+') {
					dp[i][j] = 0;
					trees.add(new Point2917(j, i, 0));
				}
				if(ch == 'V') {
					hyunwooX = j;
					hyunwooY = i;
				}
				if(ch == 'J') {
					hutX = j;
					hutY = i;
				}
				
				map[i][j] = ch;
			}
		}

		// 모든 나무 위치를 시작점으로 BFS 진행. 이 때, 거리를 구할 수 있는 지점 또한 또 다른 시작점이므로 큐에 넣기 
		while(!trees.isEmpty()) {
			Point2917 tree = trees.poll();
			int distance = tree.distance + 1;
			
			for(int i = 0; i < moveX.length; i++) {
				int nextX = tree.x + moveX[i];
				int nextY = tree.y + moveY[i];
				
				// 맵 밖 벗어나면 안됨 
				if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) {
					continue;
				}
				
				// 횟수가 크면 통과
				if(distance < dp[nextY][nextX]) {
					dp[nextY][nextX] = distance;
					trees.offer(new Point2917(nextX, nextY, distance));
				}
			}
		}
			
		// 시작지점과 종료지점의 값 중 최소인 값 초기 설정 
		min = Math.min(dp[hyunwooY][hyunwooX], dp[hutY][hutX]);
		dijkstra();
		System.out.println(min);
	}
	
	// 나무 위치부터 모든 지점까지의 최소 거리 구하는 DFS
	// DFS 사용하면 시간 초과 
	// 시작 count : 1
	static void calculateTreeMoving(int x, int y, int distance) {
		distance++;
		
		for(int i = 0; i < moveX.length; i++) {
			int nextX = x + moveX[i];
			int nextY = y + moveY[i];
			
			// 맵 밖 벗어나면 안됨 
			if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) {
				continue;
			}
			
			// 횟수가 크면 통과
			if(distance > dp[nextY][nextX]) {
				continue;
			}
			
			dp[nextY][nextX] = distance;
			calculateTreeMoving(nextX, nextY, distance);
		}
	}	
	
	// 시작 지점과 종료 지점 좌표는 이미 static으로 저장해놨으므로 별도 파라미터 투입 X 
	static void dijkstra() {
		PriorityQueue<Point2917> pq = new PriorityQueue<>();
		pq.offer(new Point2917(hyunwooX, hyunwooY, dp[hyunwooY][hyunwooX]));
		boolean[][] visited = new boolean[n][m];
		visited[hyunwooY][hyunwooX] = true;
		
		while(!pq.isEmpty()) {
			Point2917 point2917 = pq.poll();
			
			// 거리 최솟값 수정 
			if(point2917.distance < min) {
				min = point2917.distance;
			}
			
			for(int i = 0; i < moveX.length; i++) {
				int nextX = point2917.x + moveX[i];
				int nextY = point2917.y + moveY[i];
				
				// 맵 밖 벗어나면 안됨 
				if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) {
					continue;
				}
				
				if(visited[nextY][nextX]) {	// 이미 방문했으면 패스 
					continue;
				}
				
				if(nextX == hutX && nextY == hutY) {
					return;
				}
				
				visited[nextY][nextX] = true;
				pq.offer(new Point2917(nextX, nextY, dp[nextY][nextX]));
			}
		}
	}
}

class Point2917 implements Comparable<Point2917>{
	int x;
	int y;
	int minCount;
	int distance;
	
	Point2917(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	Point2917(int x, int y, int distance) {
		this.x = x;
		this.y = y;
		this.distance = distance;
	}
	
	// 내림차순 
	@Override
	public int compareTo(Point2917 o) {
		if(this.distance > o.distance) {
			return -1;
		}
		else if(this.distance == o.distance) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
}
