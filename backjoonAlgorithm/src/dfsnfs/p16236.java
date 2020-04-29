package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 16236
public class p16236 {
	static Fish targetFish = null;
	static int n, sharkX, sharkY, sharkSize, sharkEatCount;
	static int[][] map;
	static boolean[][] visited;
	// 이동 순서 : 위 - 왼 - 오 - 아래
	static int[] moveX = { 0, -1, 1, 0 };
	static int[] moveY = { -1, 0, 0, 1 };
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.valueOf(st.nextToken());
		map = new int[n][n];
		visited = new boolean[n][n];
		
		// 맵 그리기
		for(int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < n; x++) {
				int space = Integer.valueOf(st.nextToken());
				map[y][x] = space;
				if(space == 9) {
					sharkX = x;
					sharkY = y;
					sharkSize = 2;
				}
			}
		}
		
		int time = bfs();
		System.out.println(time);
	}
	
	static int bfs() {
		int time = 0;
		PriorityQueue<Fish> q = new PriorityQueue<Fish>();
		
		while(true) {
			q.add(new Fish(sharkX, sharkY));
			
			while(!q.isEmpty()) {
				Fish fish = q.poll();
				visited[fish.y][fish.x] = true;
				
				if(map[fish.y][fish.x] < sharkSize && map[fish.y][fish.x] > 0) {
					if(targetFish == null) {	
						targetFish = fish;
					}
					else {
						// 물고기 비교해서 이전 목표 물고기보다 더 나은 조건이면 바꾸기
						if(targetFish.compareTo(fish) == -1) {
							targetFish = fish;
						}
					}
					break;
				}
				
				for(int i = 0; i < moveX.length; i++) {
					int nextX = fish.x + moveX[i];
					int nextY = fish.y + moveY[i];
					
					// 맵 범위 조건 체크
					if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) {
						continue;
					}
					
					// 방문 여부 체크
					if(visited[nextY][nextX]) {
						continue;
					}
					
					// 상어 자기 자리
//					if(map[nextY][nextX] == 9) {
//						continue;
//					}
					
					// 물고기가 아니면(또는, 크기가 같으면) 통과
					if(map[nextY][nextX] == sharkSize || map[nextY][nextX] == 0) {
						visited[nextY][nextX] = true;
						q.add(new Fish(nextX, nextY, fish.distance + 1));
						continue;
					}
					
					// 물고기 크기 비교
					if(map[nextY][nextX] < sharkSize && map[nextY][nextX] > 0) {
						visited[nextY][nextX] = true;
						q.add(new Fish(nextX, nextY, fish.distance + 1));
//						if(targetFish == null) {	
//							targetFish = myFish;
//							break;
//						}
//						else {
//							// 물고기 비교해서 이전 목표 물고기보다 더 나은 조건이면 바꾸기
//							if(targetFish.compareTo(myFish) == -1) {
//								targetFish = myFish;
//							}
//						}
					}
				}
			}
			
			// 잡아먹기
			if(targetFish == null) {
				return time;
			}
			
			// 지난 시간, 상어 위치, 크기, 잡아먹은 개수 변경
			map[targetFish.y][targetFish.x] = 9;
			map[sharkY][sharkX] = 0;
			sharkX = targetFish.x;
			sharkY = targetFish.y;
			time += targetFish.distance;
			
			if(++sharkEatCount == sharkSize) {
				sharkSize++;
				sharkEatCount = 0;
			}
			
			// 초기화
			targetFish = null;
			resetVisited();
			q.clear();
		}
	}
	
	static void resetVisited() {
		for(int y = 0; y < n; y++) {
			Arrays.fill(visited[y], false);
		}
	}
}

class Fish implements Comparable<Fish> {
	int x;
	int y;
	int distance;
	
	public Fish(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int compareTo(Fish myFish) {
		if(this.distance < myFish.distance) {
			return -1;
		}
		else if(this.distance == myFish.distance) {
			if(this.y < myFish.y) {
				return -1;
			}
			else if(this.y == myFish.y) {
				if(this.x < myFish.x) {
					return -1;
				}
				else if(this.x == myFish.x) {
					return 0;
				}
				else {
					return 1;
				}
			}
			else {
				return 1;
			}
		}
		else {
			return 1;
		}
	}

	public Fish(int x, int y, int distance) {
		this.x = x;
		this.y = y;
		this.distance = distance;
	}
}