package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// �����ɲ�
// BFS, ���ͽ�Ʈ��  
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

		// ��� ���� ��ġ�� ���������� BFS ����. �� ��, �Ÿ��� ���� �� �ִ� ���� ���� �� �ٸ� �������̹Ƿ� ť�� �ֱ� 
		while(!trees.isEmpty()) {
			Point2917 tree = trees.poll();
			int distance = tree.distance + 1;
			
			for(int i = 0; i < moveX.length; i++) {
				int nextX = tree.x + moveX[i];
				int nextY = tree.y + moveY[i];
				
				// �� �� ����� �ȵ� 
				if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) {
					continue;
				}
				
				// Ƚ���� ũ�� ���
				if(distance < dp[nextY][nextX]) {
					dp[nextY][nextX] = distance;
					trees.offer(new Point2917(nextX, nextY, distance));
				}
			}
		}
			
		// ���������� ���������� �� �� �ּ��� �� �ʱ� ���� 
		min = Math.min(dp[hyunwooY][hyunwooX], dp[hutY][hutX]);
		dijkstra();
		System.out.println(min);
	}
	
	// ���� ��ġ���� ��� ���������� �ּ� �Ÿ� ���ϴ� DFS
	// DFS ����ϸ� �ð� �ʰ� 
	// ���� count : 1
	static void calculateTreeMoving(int x, int y, int distance) {
		distance++;
		
		for(int i = 0; i < moveX.length; i++) {
			int nextX = x + moveX[i];
			int nextY = y + moveY[i];
			
			// �� �� ����� �ȵ� 
			if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) {
				continue;
			}
			
			// Ƚ���� ũ�� ���
			if(distance > dp[nextY][nextX]) {
				continue;
			}
			
			dp[nextY][nextX] = distance;
			calculateTreeMoving(nextX, nextY, distance);
		}
	}	
	
	// ���� ������ ���� ���� ��ǥ�� �̹� static���� �����س����Ƿ� ���� �Ķ���� ���� X 
	static void dijkstra() {
		PriorityQueue<Point2917> pq = new PriorityQueue<>();
		pq.offer(new Point2917(hyunwooX, hyunwooY, dp[hyunwooY][hyunwooX]));
		boolean[][] visited = new boolean[n][m];
		visited[hyunwooY][hyunwooX] = true;
		
		while(!pq.isEmpty()) {
			Point2917 point2917 = pq.poll();
			
			// �Ÿ� �ּڰ� ���� 
			if(point2917.distance < min) {
				min = point2917.distance;
			}
			
			for(int i = 0; i < moveX.length; i++) {
				int nextX = point2917.x + moveX[i];
				int nextY = point2917.y + moveY[i];
				
				// �� �� ����� �ȵ� 
				if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) {
					continue;
				}
				
				if(visited[nextY][nextX]) {	// �̹� �湮������ �н� 
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
	
	// �������� 
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
