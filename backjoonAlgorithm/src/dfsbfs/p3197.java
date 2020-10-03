package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// ������ȣ��
// BFS ���� 
public class p3197 {
	static int r, c;
	static int count;
	static char[][] map;
	static boolean[][] visited;
	static int[][] countMap;	// �ش� ������ ������ �� �� ������ ����� üũ�ϴ� �迭 
	static int[] moveX = {0, 1, 0, -1};
	static int[] moveY = {-1, 0, 1, 0};
	static Point3197 baekjo1, baekjo2;
	static PriorityQueue<Point3197> waterQueue;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.valueOf(st.nextToken());
		c = Integer.valueOf(st.nextToken());
		waterQueue = new PriorityQueue<Point3197>();
		map = new char[r][c];
		countMap = new int[r][c];
		visited = new boolean[r][c];
		boolean baekjoGetted = false;
		
		// �Է¹ޱ� 
		for(int i = 0; i < r; i++) {
			String line = br.readLine();
			Arrays.fill(countMap[i], Integer.MAX_VALUE);
			
			for(int j = 0; j < c; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'L') {	// ������ ��� 
					visited[i][j] = true;
					countMap[i][j] = 0;
					if(!baekjoGetted) {
						baekjo1 = new Point3197(j, i, 0);
						baekjoGetted = true;
					}
					else {
						baekjo2 = new Point3197(j, i, 0);
					}
					
					// ���� ��ġ ���� ���̶�� �����ؾ���(������ �� ���� �������ϱ�) 
					waterQueue.offer(new Point3197(j, i, 0));
					countMap[i][j] = 0;
				}
				
				if(map[i][j] == '.') {	// ���� ���  
					waterQueue.offer(new Point3197(j, i, 0));
					countMap[i][j] = 0;
				}
			}
		}
		
		melt();	// ���� ���� �� �ִ� �ð� �ʿ� �ۼ�
		System.out.println(meetBaekjo());
		
//		checkMeltCount(baekjo1.x, baekjo1.y, 0);
//		// �������� ���� �� �ִ��� üũ 
////		while(!possibleMeetBaekjo()) {
////			melt();
////			count++;
////		}
//		
//		System.out.println(getCountOfMeetBaekjo());
	}
	
	static int meetBaekjo() {
		boolean[][] tempVisited = new boolean[r][c];
		PriorityQueue<Point3197> pq = new PriorityQueue<Point3197>();
		pq.offer(baekjo1);
		tempVisited[baekjo1.y][baekjo1.x] = true;
		
		while(!pq.isEmpty()) {
			Point3197 nowP = pq.poll();
			
			if(nowP.x == baekjo2.x && nowP.y == baekjo2.y) {
				return nowP.count;
			}
			
			for(int i = 0; i < moveX.length; i++) {
				int nextX = nowP.x + moveX[i];
				int nextY = nowP.y + moveY[i];
				
				// �ʹ� ������� Ȯ��
				if(nextX < 0 || nextX >= c || nextY < 0 || nextY >= r) {
					continue;
				}
				
				if(tempVisited[nextY][nextX]) {
					continue;
				}
				
				tempVisited[nextY][nextX] = true;
				if(countMap[nextY][nextX] > nowP.count) {
					pq.offer(new Point3197(nextX, nextY, countMap[nextY][nextX]));
				}
				else {
					pq.offer(new Point3197(nextX, nextY, nowP.count));
				}
//				if(nowP.count == 0) {
//					// �������ڸ��� �������� �� �� ���� ������ ���θ� �̵��ϴ� ��� 
//					if(countMap[nextY][nextX] == 0) {
//						pq.offer(new Point3197(nextX, nextY, 0));
//					}
//					else {	// �� -> ���� ���� �Ѿ�� ��� 
//						pq.offer(new Point3197(nextX, nextY, 1));
//					}
//				}
//				else {
//					if(countMap[nextY][nextX] > nowP.count) {
//						pq.offer(new Point3197(nextX, nextY, countMap[nextY][nextX]));
//					}
//					else {
//						pq.offer(new Point3197(nextX, nextY, nowP.count));
//					}
//				}
			}
		}
		
		return Integer.MAX_VALUE;	// �ٸ� ������ ���� ����� ������ 
	}
	
	static void melt() {
		while(!waterQueue.isEmpty()) {
			Point3197 nowWater = waterQueue.poll();
			
			for(int i = 0; i < moveX.length; i++) {
				int nextX = nowWater.x + moveX[i];
				int nextY = nowWater.y + moveY[i];
				
				// �ʹ� ������� Ȯ��
				if(nextX < 0 || nextX >= c || nextY < 0 || nextY >= r) {
					continue;
				}
				
				if(visited[nextY][nextX]) {	// �̹� ������ ���� �� �ִ� ���� ��� �ߺ� ������ ���� 
					continue;
				}
				
				if(map[nextY][nextX] == 'X') {
					if(countMap[nextY][nextX] > nowWater.count + 1) {
						visited[nextY][nextX] = true;
						countMap[nextY][nextX] = nowWater.count + 1;
						waterQueue.offer(new Point3197(nextX, nextY, nowWater.count + 1));
					}
				}
			}
		}
	}
}

class Point3197 implements Comparable<Point3197> {
	int x;
	int y;
	int count;
	
	Point3197(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	Point3197(int x, int y, int count) {
		this.x = x;
		this.y = y;
		this.count = count;
	}

	@Override
	public int compareTo(Point3197 o) {
		if(this.count < o.count) {
			return -1;
		}
		else {
			return 1;
		}
	}
}
