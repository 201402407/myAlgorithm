package dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백조의호수
// BFS 문제 
public class p3197 {
	static int r, c;
	static int count;
	static char[][] map;
	static boolean[][] visited;
	static int[][] countMap;	// 해당 지점의 얼음이 몇 일 지나야 녹는지 체크하는 배열 
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
		
		// 입력받기 
		for(int i = 0; i < r; i++) {
			String line = br.readLine();
			Arrays.fill(countMap[i], Integer.MAX_VALUE);
			
			for(int j = 0; j < c; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'L') {	// 백조인 경우 
					visited[i][j] = true;
					countMap[i][j] = 0;
					if(!baekjoGetted) {
						baekjo1 = new Point3197(j, i, 0);
						baekjoGetted = true;
					}
					else {
						baekjo2 = new Point3197(j, i, 0);
					}
					
					// 백조 위치 또한 물이라고 생각해야함(백조는 물 위에 떠있으니까) 
					waterQueue.offer(new Point3197(j, i, 0));
					countMap[i][j] = 0;
				}
				
				if(map[i][j] == '.') {	// 물인 경우  
					waterQueue.offer(new Point3197(j, i, 0));
					countMap[i][j] = 0;
				}
			}
		}
		
		melt();	// 얼음 녹일 수 있는 시간 맵에 작성
		System.out.println(meetBaekjo());
		
//		checkMeltCount(baekjo1.x, baekjo1.y, 0);
//		// 백조끼리 만날 수 있는지 체크 
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
				
				// 맵밖 벗어나는지 확인
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
//					// 시작하자마자 얼음으로 간 적 없이 물에서 물로만 이동하는 경우 
//					if(countMap[nextY][nextX] == 0) {
//						pq.offer(new Point3197(nextX, nextY, 0));
//					}
//					else {	// 물 -> 얼음 으로 넘어가는 경우 
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
		
		return Integer.MAX_VALUE;	// 다른 백조로 가는 방법이 없으면 
	}
	
	static void melt() {
		while(!waterQueue.isEmpty()) {
			Point3197 nowWater = waterQueue.poll();
			
			for(int i = 0; i < moveX.length; i++) {
				int nextX = nowWater.x + moveX[i];
				int nextY = nowWater.y + moveY[i];
				
				// 맵밖 벗어나는지 확인
				if(nextX < 0 || nextX >= c || nextY < 0 || nextY >= r) {
					continue;
				}
				
				if(visited[nextY][nextX]) {	// 이미 다음에 녹일 수 있는 물인 경우 중복 방지를 위함 
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
