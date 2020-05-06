package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 원판 돌리기 문제
// 시뮬레이션 + DFS 같음
public class p17822 {
	static int[][] circle;
	static int n, m;
	static boolean haveSameNumber;
	static LinkedList<Moving> movings;
	static int[] moveX = { 0, 1, 0, -1 };
	static int[] moveY = { -1, 0, 1, 0 };
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		int t = Integer.valueOf(st.nextToken());
		
		circle = new int[n + 1][m];	// 반지름 : 1 ~ N
		movings = new LinkedList<Moving>();
		
		// 원판 그리기
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				circle[i][j] = Integer.valueOf(st.nextToken());
			}
		}
		
		// 회전에 필요한 값들 별도 저장
		while(t --> 0) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.valueOf(st.nextToken());
			int distance = Integer.valueOf(st.nextToken());
			int moveCount = Integer.valueOf(st.nextToken());
			movings.offer(new Moving(r, distance, moveCount));
		}
		
		while(!movings.isEmpty()) {
			Moving moving = movings.poll();
			changeCircleAfterMove(moving);
			
			// 같은 수 찾기
			haveSameNumber = false;
			int sum = 0;
			int count = 0;
			for(int i = 1; i <= n; i++) {
				for(int j = 0; j < m; j++) {
					if(circle[i][j] > 0) {
						sum += circle[i][j];
						count++;
						search(j, i, circle[i][j]);
					}
				}
			}
		
			if(!haveSameNumber) {
				double avg = (double) sum / count;
				if(sum == 0) {
					continue;
				}
				
				for(int i = 1; i <= n; i++) {
					for(int j = 0; j < m; j++) {
						if(circle[i][j] > 0) {
							if(circle[i][j] > avg) {
								circle[i][j]--;
								
							}
							else if(circle[i][j] < avg) {
								circle[i][j]++;
							}
						}
					}
				}
			}
		}
		
		int sum = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = 0; j < m; j++) {
				sum += circle[i][j];
			}
		}
		
		System.out.println(sum);
	}
	
	// 원판 돌리고, 돌리고 난 이후 값 변경
	static void changeCircleAfterMove(Moving moving) {
		int baesuNum = 1;
		
		// 배수에 해당하는 원판들 돌리기
		while(moving.r * baesuNum <= n) {
			
			int index = moving.r * baesuNum;
			int count = 0;
			int start = 0;
			int nextValue = circle[index][start];
			
			// 해당 반지름 원판 돌리기
			int[] tempArray = new int[m];
			for(int i = 0; i < m; i++) {
				int nextIndex = i;
				if(moving.distance == 0) {
					nextIndex = (nextIndex + moving.moveCount) % m;
				}
				// 반시계 방향
				if(moving.distance == 1) {
					nextIndex = nextIndex - moving.moveCount < 0 ? 
							m - (moving.moveCount - nextIndex) : nextIndex - moving.moveCount;
				}
				
				tempArray[nextIndex] = circle[index][i];
			}
			
			circle[index] = tempArray;
			
			// 안조은방법 찾기
//			while(count < m) {
//				System.out.println(start + " is now start");
//				// 시계 방향
//				if(moving.distance == 0) {
//					start = (start + moving.moveCount) % m;
//				}
//				// 반시계 방향
//				if(moving.distance == 1) {
//					start = start - moving.moveCount < 0 ? 
//							m - (moving.moveCount - start) : start - moving.moveCount;
//				}
//				
//				int temp = circle[index][start];
//				circle[index][start] = nextValue;
//				nextValue = temp;
//				System.out.println(start + " is next start");
//				System.out.println(temp);
//				count++;
//			}
			baesuNum++;
		}
	}
	
	// 원판 인접한 수 찾아 변경 진행. dfs
	static void search(int x, int y, int number) {
		for(int i = 0; i < moveX.length; i++) {
			int nextX;
			int nextY = y + moveY[i];
			
			if(x + moveX[i] < 0) {
				nextX = m - 1;
			}
			else if(x + moveX[i] >= m) {
				nextX = 0;
			}
			else {
				nextX = x + moveX[i];
			}
			
			if(nextY <= 0 || nextY > n) {
				continue;
			}
			
			if(circle[nextY][nextX] == number) {
				circle[y][x] = 0;
				circle[nextY][nextX] = 0;
				haveSameNumber = true;
				search(nextX, nextY, number);
			}
		}
	}
}

class Moving {
	int r;
	int distance;
	int moveCount;
	
	Moving(int r, int distance, int moveCount) {
		this.r = r;
		this.distance = distance;
		this.moveCount = moveCount;
	}
}